package com.feisuo.sds.base.server.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BodyReaderHttpServletRequestWrapper extends
		HttpServletRequestWrapper {
	private static Log log =LogFactory.getLog(BodyReaderHttpServletRequestWrapper.class);
	private final byte[] body;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
			throws IOException {
		super(request);
		body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream bais = new ByteArrayInputStream(body);

		return new ServletInputStream() {
			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

	public static String getBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream,
					Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			log.info("json请求内容:"+ sb.toString());
		} catch (IOException e) {
			log.error("IOException: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error("IOException: " + e);
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error("IOException: " + e);
				}
			}
		}
		return sb.toString();
	}
}
