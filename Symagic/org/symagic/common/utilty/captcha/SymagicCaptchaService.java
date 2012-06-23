package org.symagic.common.utilty.captcha;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class SymagicCaptchaService extends DefaultManageableImageCaptchaService{
	
	public Boolean ajaxValidateResponseForID(String ID, Object response)
			throws CaptchaServiceException {

		Boolean valid = false;

		if (!store.hasCaptcha(ID)) {
			throw new CaptchaServiceException(
					"Invalid ID, could not validate unexisting or already validated captcha");
		} else {
			valid = store.getCaptcha(ID).validateResponse(response);
		}
		
		return valid;
	}

}
