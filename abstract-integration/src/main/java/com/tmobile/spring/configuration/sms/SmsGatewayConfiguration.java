package com.tmobile.spring.configuration.sms;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tmobile.http.FileSslCertificate;
import com.tmobile.http.services.HttpClientAdapter;

@Configuration
public class SmsGatewayConfiguration {
	@Value("${smssender.jksKeyStoreFile}")
	File jksKeyStoreFile = null;
	
	@Value("${smssender.jksPassword}")
	String jksPassword = null;
			
	
	@Bean(name="smsGateWayClient")
	public HttpClientAdapter createSmsGatewayAdapter() {
		HttpClientAdapter ca = new HttpClientAdapter();
		if(jksKeyStoreFile!=null) {
			FileSslCertificate fssl = new FileSslCertificate();
			fssl.setKeyStoreFile(jksKeyStoreFile);
			fssl.setKeyStorePassword(jksPassword);
			ca.setDelegate(fssl);
		}
		return  ca;
	}
}
