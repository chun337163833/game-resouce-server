package com.nex.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;

import com.nex.annotation.Logger;

@Logger
public class FileSslCertificate implements  SSLCertificateDelegate {
	private String keyStoreTypeString = "JKS";
	private File keyStoreFile;
	private String keyStorePassword;
	
	public String getKeyStoreTypeString() {
		return keyStoreTypeString;
	}

	public void setKeyStoreTypeString(String keyStoreTypeString) {
		this.keyStoreTypeString = keyStoreTypeString;
	}

	public File getKeyStoreFile() {
		return keyStoreFile;
	}

	public void setKeyStoreFile(File keyStoreFile) {
		this.keyStoreFile = keyStoreFile;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	@Override
	public KeyStore keystoreType() throws KeyStoreException {
		return KeyStore.getInstance(keyStoreTypeString);
	}

	@Override
	public InputStream keystoreStream() {
		try {
			return new FileInputStream(keyStoreFile);
		} catch (FileNotFoundException e) {
			log.error(String.format("cannot open file %s", keyStoreFile.getAbsolutePath()), e);
			return null;
		}
	}

	@Override
	public String password() {
		return keyStorePassword;
	}
}
