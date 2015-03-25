package org.shovelgame.http;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;

public interface SSLCertificateDelegate {
	public KeyStore keystoreType() throws KeyStoreException ;
	public InputStream keystoreStream();
	public String password();
}
