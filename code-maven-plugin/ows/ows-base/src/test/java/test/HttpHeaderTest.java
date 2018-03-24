package test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.base.share.util.RestfulClient;

public class HttpHeaderTest {
	
	public static void main(String[] args) {
		
		HttpHeaders headers = new HttpHeaders();  
		headers.set(FrontendConstants.API_USER_CODE, "admin1");
		HttpEntity<String> request = new HttpEntity<String>("",
				headers);
		RestfulClient.getClient().put("http://127.0.0.1:8081/metertick-web/user/login", request);
		
	}

}
