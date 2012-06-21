package org.symagic.common.utility.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class JCaptcha implements Captcha {

	private ImageCaptchaService jcaptchaImageServer;

	public ImageCaptchaService getJcaptchaImageServer() {
		return jcaptchaImageServer;
	}

	public void setJcaptchaImageServer(ImageCaptchaService jcaptchaImageServer) {
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
			return jcaptchaImageServer.validateResponseForID(ID, captcha);
		} catch (CaptchaServiceException ex) {
			return false;
		}
	}

}
