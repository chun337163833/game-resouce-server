package org.shovelgame.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.shovelgame.utils.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpResponse {

	private HttpURLConnection conn;
	private int code; 
	public HttpResponse(HttpURLConnection conn) throws IOException {
		super();
		this.conn = conn;
		this.code = this.conn.getResponseCode();
	}

	public int getResponseCode() throws IOException {
		return code;
	}
	private StringBuffer result;
	
	public StringBuffer getResult() throws IOException {
		return getResult(false);
	}
	
	public StringBuffer getResult(boolean keepLines) throws IOException {
		if(result == null) {
			BufferedReader in = null;
			try {
				String encoding = this.conn.getContentEncoding();
				if(StringUtils.isEmpty(encoding)) {
					in = new BufferedReader(new InputStreamReader(resolveStream()));
				} else {
					in = new BufferedReader(new InputStreamReader(resolveStream(), encoding));
				}
				String inputLine;
				StringBuffer response = new StringBuffer();
		
				while ((inputLine = in.readLine()) != null) {
					if(keepLines && response.length()>0) {
						response.append("\n");
					}					
					response.append(inputLine);
				}
				result = response;
			} finally {
				if(in!=null)
					in.close();
			}
		}
		return result;
	}

	private InputStream resolveStream() throws IOException {
		if(this.getResponseCode() == 200) {
			return this.conn.getInputStream();
		} else {
			return this.conn.getErrorStream();
		}
	}
	
	public Map<String, Object> getResultAsJsonMap() throws IOException {
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
		};
		Map<String, Object> values = mapper.readValue(getResult().toString(),
				typeRef);
		return values;
	}

	public <T> T getResultAsJson(Class<T> type) throws IOException {
		StringBuffer buffer = getResult();
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		return mapper.readValue(buffer.toString(), type);
	}

}
