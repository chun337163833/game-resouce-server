package com.tmobile.smspay;

import java.util.List;

public interface AbstractSmsRequestParser {
	
	public List<InitRequest> parseInitRequest(int count, String keyword, String data);
	
	public ConfirmRequest parseConfirmRequest(String id, String date, String status, String text);
}
