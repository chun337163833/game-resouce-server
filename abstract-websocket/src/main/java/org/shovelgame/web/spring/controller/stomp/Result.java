package org.shovelgame.web.spring.controller.stomp;

public class Result {
	private String result;
    public Result(String result) {
        this.result = result;
    }
	public String getResult() {
		return result;
	}
}
