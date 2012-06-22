package org.symagic.common.utilty.captcha;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

import org.apache.commons.collections.FastHashMap;

import com.octo.captcha.Captcha;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.gimpy.DefaultGimpyEngine;
import com.octo.captcha.service.AbstractCaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.ImageCaptchaService;

public class SymagicCaptchaService extends AbstractCaptchaService implements
		ImageCaptchaService {

	private int minGuarantedStorageDelayInSeconds;
	private int captchaStoreMaxSize;

	private int captchaStoreSizeBeforeGarbageCollection;

	private int numberOfGeneratedCaptchas = 0;
	private int numberOfCorrectResponse = 0;
	private int numberOfUncorrectResponse = 0;
	private int numberOfGarbageCollectedCaptcha = 0;

	private FastHashMap times;

	private long oldestCaptcha = 0;// OPTIMIZATION STUFF!

	protected SymagicCaptchaService(CaptchaStore captchaStore,
			com.octo.captcha.engine.CaptchaEngine captchaEngine,
			int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize) {
		super(captchaStore, captchaEngine);

		this.setCaptchaStoreMaxSize(maxCaptchaStoreSize);
		this.setMinGuarantedStorageDelayInSeconds(minGuarantedStorageDelayInSeconds);
		this.setCaptchaStoreSizeBeforeGarbageCollection((int) Math
				.round(0.8 * maxCaptchaStoreSize));
		times = new FastHashMap();
	}

	protected SymagicCaptchaService(CaptchaStore captchaStore,
			com.octo.captcha.engine.CaptchaEngine captchaEngine,
			int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize,
			int captchaStoreLoadBeforeGarbageCollection) {
		this(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds,
				maxCaptchaStoreSize);
		if (maxCaptchaStoreSize < captchaStoreLoadBeforeGarbageCollection)
			throw new IllegalArgumentException(
					"the max store size can't be less than garbage collection size. if you want to disable garbage"
							+ " collection (this is not recommended) you may set them equals (max=garbage)");
		this.setCaptchaStoreSizeBeforeGarbageCollection(captchaStoreLoadBeforeGarbageCollection);

	}

	/**
	 * Construct a new ImageCaptchaService with a
	 * {@link FastHashMapCaptchaStore} and a {@link GMailCaptchaEngine}
	 * minGuarantedStorageDelayInSeconds = 180s maxCaptchaStoreSize = 100000
	 * captchaStoreLoadBeforeGarbageCollection=75000
	 */
	public SymagicCaptchaService() {
		this(new FastHashMapCaptchaStore(), new GMailCaptchaEngine(), 180,
				100000, 75000);
	}

	/**
	 * Construct a new ImageCaptchaService with a
	 * {@link FastHashMapCaptchaStore} and a {@link DefaultGimpyEngine}
	 * 
	 * @param minGuarantedStorageDelayInSeconds
	 * @param maxCaptchaStoreSize
	 * @param captchaStoreLoadBeforeGarbageCollection
	 */
	public SymagicCaptchaService(int minGuarantedStorageDelayInSeconds,
			int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection) {
		this(new FastHashMapCaptchaStore(), new DefaultGimpyEngine(),
				minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize,
				captchaStoreLoadBeforeGarbageCollection);
	}

	/**
	 * Get the fully qualified class name of the concrete CaptchaEngine used by
	 * the service.
	 * 
	 * @return the fully qualified class name of the concrete CaptchaEngine used
	 *         by the service.
	 */
	public String getCaptchaEngineClass() {
		return this.engine.getClass().getName();
	}

	/**
	 * Set the fully qualified class name of the concrete CaptchaEngine used by
	 * the service
	 * 
	 * @param theClassName
	 *            the fully qualified class name of the CaptchaEngine used by
	 *            the service
	 * 
	 * @throws IllegalArgumentException
	 *             if className can't be used as the service CaptchaEngine,
	 *             either because it can't be instanciated by the service or it
	 *             is not a ImageCaptchaEngine concrete class.
	 */
	public void setCaptchaEngineClass(String theClassName)
			throws IllegalArgumentException {
		try {
			Object engine = Class.forName(theClassName).newInstance();
			if (engine instanceof com.octo.captcha.engine.CaptchaEngine) {
				this.engine = (com.octo.captcha.engine.CaptchaEngine) engine;
			} else {
				throw new IllegalArgumentException(
						"Class is not instance of CaptchaEngine! "
								+ theClassName);
			}
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (RuntimeException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * @return the engine served by this service
	 */
	public CaptchaEngine getEngine() {
		return this.engine;
	}

	/**
	 * Updates the engine served by this service
	 */
	public void setCaptchaEngine(CaptchaEngine engine) {
		this.engine = engine;
	}

	/**
	 * Get the minimum delay (in seconds) a client can be assured that a captcha
	 * generated by the service can be retrieved and a response to its challenge
	 * tested
	 * 
	 * @return the maximum delay in seconds
	 */
	public int getMinGuarantedStorageDelayInSeconds() {
		return minGuarantedStorageDelayInSeconds;
	}

	/**
	 * set the minimum delay (in seconds)a client can be assured that a captcha
	 * generated by the service can be retrieved and a response to its challenge
	 * tested
	 * 
	 * @param theMinGuarantedStorageDelayInSeconds
	 *            the minimum guaranted delay
	 */
	public void setMinGuarantedStorageDelayInSeconds(
			int theMinGuarantedStorageDelayInSeconds) {
		this.minGuarantedStorageDelayInSeconds = theMinGuarantedStorageDelayInSeconds;
	}

	/**
	 * Get the number of captcha generated since the service is up WARNING :
	 * this value won't be significant if the real number is > Long.MAX_VALUE
	 * 
	 * @return the number of captcha generated since the service is up
	 */
	public long getNumberOfGeneratedCaptchas() {
		return numberOfGeneratedCaptchas;
	}

	/**
	 * Get the number of correct responses to captcha challenges since the
	 * service is up. WARNING : this value won't be significant if the real
	 * number is > Long.MAX_VALUE
	 * 
	 * @return the number of correct responses since the service is up
	 */
	public long getNumberOfCorrectResponses() {
		return numberOfCorrectResponse;
	}

	/**
	 * Get the number of uncorrect responses to captcha challenges since the
	 * service is up. WARNING : this value won't be significant if the real
	 * number is > Long.MAX_VALUE
	 * 
	 * @return the number of uncorrect responses since the service is up
	 */
	public long getNumberOfUncorrectResponses() {
		return numberOfUncorrectResponse;
	}

	/**
	 * Get the curent size of the captcha store
	 * 
	 * @return the size of the captcha store
	 */
	public int getCaptchaStoreSize() {
		return this.store.getSize();
	}

	/**
	 * Get the number of captchas that can be garbage collected in the captcha
	 * store
	 * 
	 * @return the number of captchas that can be garbage collected in the
	 *         captcha store
	 */
	public int getNumberOfGarbageCollectableCaptchas() {
		return getGarbageCollectableCaptchaIds(System.currentTimeMillis())
				.size();
	}

	/**
	 * Get the number of captcha garbage collected since the service is up
	 * WARNING : this value won't be significant if the real number is >
	 * Long.MAX_VALUE
	 * 
	 * @return the number of captcha garbage collected since the service is up
	 */
	public long getNumberOfGarbageCollectedCaptcha() {
		return numberOfGarbageCollectedCaptcha;
	}

	/**
	 * @return the max captchaStore load before garbage collection of the store
	 */
	public int getCaptchaStoreSizeBeforeGarbageCollection() {
		return captchaStoreSizeBeforeGarbageCollection;
	}

	/**
	 * max captchaStore size before garbage collection of the store
	 */
	public void setCaptchaStoreSizeBeforeGarbageCollection(
			int captchaStoreSizeBeforeGarbageCollection) {
		if (this.captchaStoreMaxSize < captchaStoreSizeBeforeGarbageCollection)
			throw new IllegalArgumentException(
					"the max store size can't be less than garbage collection "
							+ "size. if you want to disable garbage"
							+ " collection (this is not recommended) you may "
							+ "set them equals (max=garbage)");

		this.captchaStoreSizeBeforeGarbageCollection = captchaStoreSizeBeforeGarbageCollection;
	}

	/**
	 * This max size is used by the service : it will throw a
	 * CaptchaServiceException if the store is full when a client ask for a
	 * captcha.
	 */
	public void setCaptchaStoreMaxSize(int size) {
		if (size < this.captchaStoreSizeBeforeGarbageCollection)
			throw new IllegalArgumentException("the max store size can't "
					+ "be less than garbage collection size. if you want "
					+ "to disable garbage"
					+ " collection (this is not recommended) you may "
					+ "set them equals (max=garbage)");
		this.captchaStoreMaxSize = size;
	}

	/**
	 * @return the desired max size of the captcha store
	 */
	public int getCaptchaStoreMaxSize() {
		return this.captchaStoreMaxSize;
	}

	/**
	 * Garbage collect the captcha store, means all old capthca (captcha in the
	 * store wich has been stored more than the MinGuarantedStorageDelayInSecond
	 */
	public void garbageCollectCaptchaStore() {
		// this may cause a captcha disparition if a new captcha is asked
		// between
		// this call and the effective removing from the store!
		long now = System.currentTimeMillis();
		long limit = now - 1000 * minGuarantedStorageDelayInSeconds;

		// construct a new collection in order to avoid iterations
		// synchronization pbs :
		@SuppressWarnings("rawtypes")
		Iterator ids = getGarbageCollectableCaptchaIds(now).iterator();
		while (ids.hasNext()) {
			String id = ids.next().toString();
			if (((Long) times.get(id)).longValue() < limit) {
				// remove from times
				times.remove(id);
				// remove from ids
				store.removeCaptcha(id);
				// update stats
				this.numberOfGarbageCollectedCaptcha++;
			}
		}
	}

	/**
	 * Empty the Store
	 */
	public void emptyCaptchaStore() {
		// empty the store
		this.store.empty();
		// And the timestamps
		this.times = new FastHashMap();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection getGarbageCollectableCaptchaIds(long now) {

		// construct a new collection in order to avoid iterations
		HashSet garbageCollectableCaptchas = new HashSet();

		// the time limit under which captchas are collectable
		long limit = now - 1000 * getMinGuarantedStorageDelayInSeconds();
		if (limit > oldestCaptcha) {
			// iterate to find out if the captcha is perimed
			Iterator ids = new HashSet(times.keySet()).iterator();
			while (ids.hasNext()) {
				String id = (String) ids.next();
				long captchaDate = ((Long) times.get(id)).longValue();
				oldestCaptcha = Math.min(captchaDate,
						oldestCaptcha == 0 ? captchaDate : oldestCaptcha);
				if (captchaDate < limit) {
					garbageCollectableCaptchas.add(id);
				}
			}
		}
		return garbageCollectableCaptchas;
	}

	// *******
	// /Overriding business methods to add some stats and store management hooks
	// /****

	protected Captcha generateAndStoreCaptcha(Locale locale, String ID) {
		long now = System.currentTimeMillis();

		// if the store is full try to garbage collect
		if (isCaptchaStoreFull()) {
			// see if possible
			if (getGarbageCollectableCaptchaIds(now).size() > 0) {
				// possible collect an rerun
				garbageCollectCaptchaStore();
				return this.generateAndStoreCaptcha(locale, ID);
			} else {
				// impossible ! has to wait
				throw new CaptchaServiceException(
						"Store is full, try to increase CaptchaStore Size or"
								+ "to dercrease time out, or to decrease CaptchaStoreSizeBeforeGrbageCollection");
			}
		}

		if (isCaptchaStoreQuotaReached()) {
			// then garbage collect
			garbageCollectCaptchaStore();
		}
		return generateCountTimeStampAndStoreCaptcha(ID, locale);
	}

	private Captcha generateCountTimeStampAndStoreCaptcha(String ID,
			Locale locale) {
		// update stats
		numberOfGeneratedCaptchas++;
		// mark as now
		Long now = new Long(System.currentTimeMillis());
		// store in my timestampeds ids
		this.times.put(ID, now);
		// retrieve and store cpatcha
		Captcha captcha = super.generateAndStoreCaptcha(locale, ID);
		return captcha;
	}

	private boolean isCaptchaStoreFull() {
		return getCaptchaStoreMaxSize() == 0 ? false
				: getCaptchaStoreSize() >= getCaptchaStoreMaxSize();
	}

	private boolean isCaptchaStoreQuotaReached() {
		return getCaptchaStoreSize() >= getCaptchaStoreSizeBeforeGarbageCollection();
	}

	/**
	 * Method to validate a response to the challenge corresponding to the given
	 * ticket.(由于AJAX的需要修正去除captcha仅在验证正确的时候)
	 * 
	 * 
	 * 
	 * @param ID
	 *            the ticket provided by the buildCaptchaAndGetID method
	 * 
	 * @return true if the response is correct, false otherwise.
	 * 
	 * @throws CaptchaServiceException
	 *             if the ticket is invalid
	 */
	public Boolean validateResponseForID(String ID, Object response)
			throws CaptchaServiceException {

		Boolean valid = false;

		if (!store.hasCaptcha(ID)) {
			throw new CaptchaServiceException(
					"Invalid ID, could not validate unexisting or already validated captcha");
		} else {
			valid = store.getCaptcha(ID).validateResponse(response);
		}
		// update stats
		if (valid.booleanValue()) {
			numberOfCorrectResponse++;
		} else {
			numberOfUncorrectResponse++;
		}
		return valid;
	}

	/**
	 * Method to retrive the image challenge corresponding to the given ticket.
	 * 
	 * @param ID
	 *            the ticket
	 * 
	 * @return the challenge
	 * 
	 * @throws com.octo.captcha.service.CaptchaServiceException
	 *             if the ticket is invalid
	 */
	public BufferedImage getImageChallengeForID(String ID)
			throws CaptchaServiceException {
		return (BufferedImage) this.getChallengeForID(ID);
	}

	/**
	 * Method to retrive the image challenge corresponding to the given ticket.
	 * 
	 * @param ID
	 *            the ticket
	 * 
	 * @return the challenge
	 * 
	 * @throws com.octo.captcha.service.CaptchaServiceException
	 *             if the ticket is invalid
	 */
	public BufferedImage getImageChallengeForID(String ID, Locale locale)
			throws CaptchaServiceException {
		return (BufferedImage) this.getChallengeForID(ID, locale);
	}

	/**
	 * This method must be implemented by sublcasses and : Retrieve the
	 * challenge from the captcha Make and return a clone of the challenge
	 * Return the clone It has be design in order to let the service dipose the
	 * challenge of the captcha after rendering. It should be implemented for
	 * all captcha type (@see ImageCaptchaService implementations for exemple)
	 * 
	 * @return a Challenge Clone
	 */
	protected Object getChallengeClone(Captcha captcha) {
		BufferedImage challenge = (BufferedImage) captcha.getChallenge();
		BufferedImage clone = new BufferedImage(challenge.getWidth(),
				challenge.getHeight(), challenge.getType());

		clone.getGraphics().drawImage(challenge, 0, 0, clone.getWidth(),
				clone.getHeight(), null);
		clone.getGraphics().dispose();

		return clone;
	}

}
