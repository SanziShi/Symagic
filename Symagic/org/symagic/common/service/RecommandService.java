package org.symagic.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RecommandService {
	
	public boolean view( String sessionID, String itemID, String itemDescription, String userName){
		
		
		
		return true;
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
					getURL += entry.getKey() + "=" + entry.getValue();
					if (iterator.hasNext())
						getURL += '&';
				}
			}

			try {
				URLConnection conn = (new URL(getURL)).openConnection();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line);
				}
				reader.close();
				result = stringBuffer.toString();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		return result;

	}

}
