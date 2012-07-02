package org.symagic.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class RecommandService {

	private String host = "localhost:8080";
	/**
	 * easyrec的API KEY
	 */
	private String apikey;
	/**
	 * easyrec的注册的tenantID
	 */
	private String tenantid;

	/**
	 * 提交view操作,对于未登陆用户userName为null
	 * 
	 * @param sessionID
	 * @param itemID
	 * @param itemDescription
	 * @param itemURL
	 * @param userName
	 * @return
	 */
	public boolean view(String sessionID, String itemID,
			String itemDescription, String itemURL, String userName) {

		String url = "http://" + host + "/easyrec-web/api/1.0/json/view";

		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		parameters.put("sessionid", sessionID);
		parameters.put("itemid", itemID);
		parameters.put("itemdescription", itemDescription);
		parameters.put("itemurl", itemURL);
		if (userName != null)
			parameters.put("userid", userName);

		String reponse = this.sendGetRequest(url, parameters);
		try {
			if (reponse == null)
				return false;

			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);
			if (object == null || object.containsKey("error"))
				return false;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	/**
	 * 提交buy操作,对于未登陆用户userName为null
	 * 
	 * @param sessionID
	 * @param itemID
	 * @param itemDescription
	 * @param itemURL
	 * @param userName
	 * @return
	 */
	public boolean buy(String sessionID, String itemID, String itemDescription,
			String itemURL, String userName) {
		String url = "http://" + host + "/easyrec-web/api/1.0/json/buy";
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		parameters.put("sessionid", sessionID);
		parameters.put("itemid", itemID);
		parameters.put("itemdescription", itemDescription);
		parameters.put("itemurl", itemURL);
		if (userName != null)
			parameters.put("userid", userName);

		String reponse = this.sendGetRequest(url, parameters);
		try {
			if (reponse == null)
				return false;
			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);
			if (object == null || object.containsKey("error"))
				return false;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 提交rate操作,对于未登陆用户userName为null
	 * 
	 * @param sessionID
	 * @param rateValue
	 * @param itemID
	 * @param itemDescription
	 * @param itemURL
	 * @param userName
	 * @return
	 */
	public boolean rate(String sessionID, String rateValue, String itemID,
			String itemDescription, String itemURL, String userName) {
		String url = "http://" + host + "/easyrec-web/api/1.0/json/rate";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		parameters.put("sessionid", sessionID);
		parameters.put("itemid", itemID);
		parameters.put("ratingvalue", rateValue);
		parameters.put("itemdescription", itemDescription);
		parameters.put("itemurl", itemURL);
		if (userName != null)
			parameters.put("userid", userName);

		String reponse = this.sendGetRequest(url, parameters);
		try {
			if (reponse == null)
				return false;
			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);
			if (object == null || object.containsKey("error"))
				return false;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据当前用户浏览的商品获得推荐的商品项,,对于未登陆用户userName为null
	 * 
	 * @param itemID
	 * @param userName
	 * @param requireNumber
	 * @return
	 */
	public List<Integer> otherUsersAlsoViewed(String itemID, String userName,
			Integer requireNumber) {
		List<Integer> result = new ArrayList<Integer>();

		String url = "http://" + host
				+ "/easyrec-web/api/1.0/json/otherusersalsoviewed";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		parameters.put("itemid", itemID);
		if (userName != null)
			parameters.put("userid", userName);
		if (requireNumber != null)
			parameters.put("numberOfResults", requireNumber.toString());

		String reponse = this.sendGetRequest(url, parameters);
		if (reponse == null)
			return null;

		try {

			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);

			if (object == null)
				return null;

			JSONObject recommended = object.getJSONObject("recommendeditems");
			JSON items = (JSON) recommended.get("item");
			if (items.isArray()) {
				JSONArray list = (JSONArray) items;
				for (int i = 0; i < list.size(); i++) {
					JSONObject temp = (JSONObject) list.get(i);
					result.add(temp.getInt("id"));
				}
			} else {
				JSONObject temp = (JSONObject) items;
				result.add(temp.getInt("id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	/**
	 * 根据当前用户购买的商品获得推荐的商品项,,对于未登陆用户userName为null
	 * 
	 * @param itemID
	 * @param userName
	 * @param requireNumber
	 * @return
	 */
	public List<Integer> otherUsersAlsoBought(String itemID, String userName,
			Integer requireNumber) {
		List<Integer> result = new ArrayList<Integer>();

		String url = "http://" + host
				+ "/easyrec-web/api/1.0/json/otherusersalsobought";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		parameters.put("itemid", itemID);
		if (userName != null)
			parameters.put("userid", userName);
		if (requireNumber != null)
			parameters.put("numberOfResults", requireNumber.toString());

		String reponse = this.sendGetRequest(url, parameters);
		if (reponse == null)
			return null;
		try {
			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);

			if (object == null)
				return null;

			JSONObject recommended = object.getJSONObject("recommendeditems");
			JSON items = (JSON) recommended.get("item");
			if (items.isArray()) {
				JSONArray list = (JSONArray) items;
				for (int i = 0; i < list.size(); i++) {
					JSONObject temp = (JSONObject) list.get(i);
					result.add(temp.getInt("id"));
				}
			} else {
				JSONObject temp = (JSONObject) items;
				result.add(temp.getInt("id"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	/**
	 * 对指定用户进行推荐
	 * 
	 * @param userName
	 * @param requireNumber
	 * @return
	 */
	public List<Integer> recommendationsForUser(String userName,
			Integer requireNumber) {
		List<Integer> result = new ArrayList<Integer>();

		String url = "http://" + host
				+ "/easyrec-web/api/1.0/json/recommendationsforuser";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		parameters.put("userid", userName);
		if (requireNumber != null)
			parameters.put("numberOfResults", requireNumber.toString());

		String reponse = this.sendGetRequest(url, parameters);
		try {
			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);
			if (reponse == null)
				return null;
			if (object == null)
				return null;

			JSONObject recommended = object.getJSONObject("recommendeditems");
			JSON items = (JSON) recommended.get("item");
			if (items.isArray()) {
				JSONArray list = (JSONArray) items;
				for (int i = 0; i < list.size(); i++) {
					JSONObject temp = (JSONObject) list.get(i);
					result.add(temp.getInt("id"));
				}
			} else {
				JSONObject temp = (JSONObject) items;
				result.add(temp.getInt("id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	/**
	 * 获取热销商品
	 * 
	 * @param requireNumber
	 * @return
	 */
	public List<Integer> mostBoughtItems(Integer requireNumber) {
		List<Integer> result = new ArrayList<Integer>();

		String url = "http://" + host
				+ "/easyrec-web/api/1.0/json/mostboughtitems";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		if (requireNumber != null)
			parameters.put("numberOfResults", requireNumber.toString());

		String reponse = this.sendGetRequest(url, parameters);
		if (reponse == null)
			return null;
		try {
			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);

			if (object == null)
				return null;

			JSONObject recommended = object.getJSONObject("recommendeditems");

			JSON items = (JSON) recommended.get("item");
			if (items.isArray()) {
				JSONArray list = (JSONArray) items;
				for (int i = 0; i < list.size(); i++) {
					JSONObject temp = (JSONObject) list.get(i);
					result.add(temp.getInt("id"));
				}
			} else {
				JSONObject temp = (JSONObject) items;
				result.add(temp.getInt("id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	/**
	 * 获取浏览最多的商品
	 * 
	 * @param requireNumber
	 * @return
	 */
	public List<Integer> mostViewedItems(Integer requireNumber) {
		List<Integer> result = new ArrayList<Integer>();

		String url = "http://" + host
				+ "/easyrec-web/api/1.0/json/mostvieweditems";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("apikey", apikey);
		parameters.put("tenantid", tenantid);
		if (requireNumber != null)
			parameters.put("numberOfResults", requireNumber.toString());

		String reponse = this.sendGetRequest(url, parameters);
		try {
			JSONObject object = (JSONObject) JSONSerializer.toJSON(reponse);

			if (object == null)
				return null;

			JSONObject recommended = object.getJSONObject("recommendeditems");
			JSON items = (JSON) recommended.get("item");
			if (items.isArray()) {
				JSONArray list = (JSONArray) items;
				for (int i = 0; i < list.size(); i++) {
					JSONObject temp = (JSONObject) list.get(i);
					result.add(temp.getInt("id"));
				}
			} else {
				JSONObject temp = (JSONObject) items;
				result.add(temp.getInt("id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	/**
	 * 发送http请求
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	protected String sendGetRequest(String url, Map<String, String> parameters) {

		String result = null;
		String getURL = null;

		if (url.startsWith("http://")) {

			getURL = url;

			if (parameters != null && !parameters.isEmpty()) {
				getURL += '?';
				Iterator<Entry<String, String>> iterator = parameters
						.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, String> entry = iterator.next();
					try {
						getURL += URLEncoder.encode(entry.getKey(), "UTF-8")
								+ "="
								+ URLEncoder.encode(entry.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
					if (iterator.hasNext())
						getURL += '&';
				}
			}

			try {
				URLConnection conn = (new URL(getURL)).openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestProperty("Charset", "UTF-8");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "UTF-8"));
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line);
				}
				reader.close();
				result = stringBuffer.toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

		}

		return result;

	}

}
