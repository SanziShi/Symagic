package org.symagic.common.utilty.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class JCaptcha implements Captcha {

	private SymagicCaptchaService jcaptchaImageServer;

	public SymagicCaptchaService getJcaptchaImageServer() {
		return jcaptchaImageServer;
	}

	public void setJcaptchaImageServer(SymagicCaptchaService jcaptchaImageServer) {
		this.jcaptchaImageServer = jcaptchaImageServer;
	}

	@Override
	public InputStream generateImageCaptcha(String ID) {

		// 从captchaServer中获得Image
		BufferedImage image = jcaptchaImageServer.getImageChallengeForID(ID);

		// JPEG编码器
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		JPEGImageEncoder jpegEncoder = JPEGCodec
				.createJPEGEncoder(jpegOutputStream);

		// 将图片编码成为JPEG
		try {
			jpegEncoder.encode(image);
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(jpegOutputStream.toByteArray());
	}

	@Override
	public String getCaptchaContentType() {
		return "image/jpeg";
	}

	@Override
	public boolean validateCaptcha(String ID, String captcha) {
		try {
			String change = captcha.toUpperCase();
			return jcaptchaImageServer.validateResponseForID(ID, change);
		} catch (CaptchaServiceException ex) {
			return false;
		}
	}

	@Override
	public boolean ajaxValidateCaptcha(String ID, String captcha) {
		try {
			String change = captcha.toUpperCase();
			return jcaptchaImageServer.ajaxValidateResponseForID(ID, change);
		} catch (CaptchaServiceException ex) {
			return false;
		}

	}

}
