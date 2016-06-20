package com.example.administrator.test.simallutil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyHttp {
	public static String getHttp(String path){
		String result = null;
		HttpURLConnection huc = null;
		try {
			URL url = new URL(path);
			huc = (HttpURLConnection) url.openConnection();
			huc.setConnectTimeout(2000);
			huc.connect();
			if (huc.getResponseCode()==200) {
				InputStream is = huc.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int len = 0;
				byte[] buff = new byte[1024];
				while((len=is.read(buff))!=-1){
					baos.write(buff, 0, len);
					baos.flush();
				}
				byte[] data = baos.toByteArray();
				result = new String(data, 0, data.length);
				baos.close();
				is.close();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (huc!=null) {
					huc.disconnect();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return result;
	}
}
