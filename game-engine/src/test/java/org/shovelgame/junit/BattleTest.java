package org.shovelgame.junit;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.shovelgame.engine.io.LineReader;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.http.HttpResponse;
import org.shovelgame.http.oauth.OAuthClient;
import org.shovelgame.http.oauth.OAuthClient.Token;

public class BattleTest {
	private String srv = "http://localhost:8080";
	private String client = Base64.getEncoder().encodeToString("abcd:dcba".getBytes());
	@Test
	public void testBattle() throws Exception {
		StringBuffer errors = new StringBuffer();
		Token token = getToken(errors);
		Socket socket = new Socket("localhost", 8888);
		OutputStream os = socket.getOutputStream();
		//authenticate
		os.write(CommandName.Authentication.createCommand(token.getAccessToken()).toJson().getBytes());
		os.write("\n".getBytes());
		LineReader reader = new LineReader(socket.getInputStream());
		reader.readLine();
		//begin mission
		os.write(CommandName.Mission.createCommand("1").toJson().getBytes());
		os.write("\n".getBytes());
		reader.readLine();
		
		os.write(CommandName.UseSkill.createCommand("1").toJson().getBytes());
		os.write("\n".getBytes());
		reader.readLine();
		
		socket.close();
	}
	private Token getToken(StringBuffer errors) throws Exception {
		OAuthClient cl = new OAuthClient(srv+"/oauth-server/oauth/token");		
		cl.setEncodedClientAuthorization(client).setUsername("test").setPassword("a");
		Map<String, String> headers = new HashMap<String, String>();
		acceptLanguage(headers);
		HttpResponse response = cl.requestToken(headers);
		
		if (response.getResponseCode() == 200) {
			return cl.getToken();
		} else {
			errors.append(response.getResult());
		}
		return null;
	}
	private void acceptLanguage(Map<String, String> headers) {
		headers.put("Accept-Language", "de");
	}
}
