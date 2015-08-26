package org.shovelgame.junit;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.CommandInputStreamHelper;
import org.shovelgame.engine.io.CommandOutputStreamHelper;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.CommandStatus;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.enumeration.TraitType;
import org.shovelgame.http.HttpResponse;
import org.shovelgame.http.oauth.OAuthClient;
import org.shovelgame.http.oauth.OAuthClient.Token;

public class BattleTest {
	private String srv = "http://localhost:8080";
	private String client = Base64.getEncoder().encodeToString("abcd:dcba".getBytes());
	
	@Test
	public void testBase64() throws UnsupportedEncodingException {
		String testJSON = "65:79:4a:75:59:57:31:6c:49:6a:6f:69:55:33:6c:75:59:31:52:6c:59:57:30:69:4c:43:4a:7a:64:47:46:30:64:58:4d:69:4f:69:4a:50:61:79:49:73:49:6e:4a:6c:63:33:42:76:62:6e:4e:6c:49:6a:70:30:63:6e:56:6c:4c:43:4a:6c:64:6d:56:75:64:43:49:36:5a:6d:46:73:63:32:55:73:49:6e:42:68:63:6d:46:74:5a:58:52:6c:63:6e:4d:69:4f:6d:35:31:62:47:77:73:49:6d:52:68:64:47:45:69:4f:69:4a:37:58:48:4a:63:62:69:41:67:58:43:4a:74:61:57:35:70:62:32:35:7a:58:43:49:67:4f:69:42:37:58:48:4a:63:62:69:41:67:49:43:42:63:49:6b:31:70:5a:46:77:69:49:44:6f:67:65:31:78:79:58:47:34:67:49:43:41:67:49:43:42:63:49:6e:4e:30:59:58:52:7a:58:43:49:67:4f:69:42:62:49:48:74:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6e:52:35:63:47:56:63:49:69:41:36:49:46:77:69:53:47:56:68:62:48:52:6f:58:43:49:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:74:59:58:68:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:54:51:31:4d:7a:55:75:4d:44:41:77:4d:44:41:77:4d:44:41:77:4c:46:78:79:58:47:34:67:49:43:41:67:49:43:41:67:49:46:77:69:5a:47:56:6d:59:58:56:73:64:46:5a:68:62:48:56:6c:58:43:49:67:4f:69:41:7a:4d:44:41:75:4d:44:41:77:4c:46:78:79:58:47:34:67:49:43:41:67:49:43:41:67:49:46:77:69:59:33:56:79:63:6d:56:75:64:46:5a:68:62:48:56:6c:58:43:49:67:4f:69:41:78:4e:44:55:7a:4e:53:34:77:4d:44:41:77:4d:44:41:77:4d:44:42:63:63:6c:78:75:49:43:41:67:49:43:41:67:66:53:77:67:65:31:78:79:58:47:34:67:49:43:41:67:49:43:41:67:49:46:77:69:64:48:6c:77:5a:56:77:69:49:44:6f:67:58:43:4a:4e:59:57:35:68:58:43:49:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:74:59:58:68:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:6a:41:77:4c:6a:41:77:4d:43:78:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6d:52:6c:5a:6d:46:31:62:48:52:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:6a:41:77:4c:6a:41:77:4d:43:78:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6d:4e:31:63:6e:4a:6c:62:6e:52:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:6a:41:77:4c:6a:41:77:4d:46:78:79:58:47:34:67:49:43:41:67:49:43:42:39:4c:43:42:37:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:30:65:58:42:6c:58:43:49:67:4f:69:42:63:49:6c:42:6f:65:58:4e:70:59:32:46:73:55:47:39:33:5a:58:4a:63:49:69:78:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6d:31:68:65:46:5a:68:62:48:56:6c:58:43:49:67:4f:69:41:78:4d:43:34:77:4d:44:41:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:6b:5a:57:5a:68:64:57:78:30:56:6d:46:73:64:57:56:63:49:69:41:36:49:44:45:77:4c:6a:41:77:4d:43:78:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6d:4e:31:63:6e:4a:6c:62:6e:52:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:54:41:75:4d:44:41:77:58:48:4a:63:62:69:41:67:49:43:41:67:49:48:30:73:49:48:74:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6e:52:35:63:47:56:63:49:69:41:36:49:46:77:69:55:47:68:35:63:32:6c:6a:59:57:78:44:63:6d:6c:30:51:32:68:68:62:6d:4e:6c:58:43:49:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:74:59:58:68:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:43:34:7a:4d:44:41:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:6b:5a:57:5a:68:64:57:78:30:56:6d:46:73:64:57:56:63:49:69:41:36:49:44:41:75:4d:7a:41:77:4c:46:78:79:58:47:34:67:49:43:41:67:49:43:41:67:49:46:77:69:59:33:56:79:63:6d:56:75:64:46:5a:68:62:48:56:6c:58:43:49:67:4f:69:41:77:4c:6a:4d:77:4d:46:78:79:58:47:34:67:49:43:41:67:49:43:42:39:4c:43:42:37:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:30:65:58:42:6c:58:43:49:67:4f:69:42:63:49:6c:42:6f:65:58:4e:70:59:32:46:73:55:47:56:75:5a:58:52:79:59:58:52:70:62:32:35:63:49:69:78:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6d:31:68:65:46:5a:68:62:48:56:6c:58:43:49:67:4f:69:41:77:4c:6a:41:31:4d:43:78:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6d:52:6c:5a:6d:46:31:62:48:52:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:43:34:77:4e:54:41:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:6a:64:58:4a:79:5a:57:35:30:56:6d:46:73:64:57:56:63:49:69:41:36:49:44:41:75:4d:44:55:77:58:48:4a:63:62:69:41:67:49:43:41:67:49:48:30:73:49:48:74:63:63:6c:78:75:49:43:41:67:49:43:41:67:49:43:42:63:49:6e:52:35:63:47:56:63:49:69:41:36:49:46:77:69:55:47:68:35:63:32:6c:6a:59:57:78:53:5a:58:4e:70:63:33:52:68:62:6d:4e:6c:58:43:49:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:74:59:58:68:57:59:57:78:31:5a:56:77:69:49:44:6f:67:4d:43:34:79:4d:44:41:73:58:48:4a:63:62:69:41:67:49:43:41:67:49:43:41:67:58:43:4a:6b:5a:57:5a:68:64:57:78:30:56:6d:46:73:64:57:56:63:49:69:41:36";
		testJSON = testJSON.replace(":", "-");
		testJSON = new String(convertToByteArray(testJSON));
		String wut = "eyJuYW1lIjoiTWlzc2lvbiIsInN0YXR1cyI6Ik9rIiwicmVzcG9uc2UiOnRydWUsImV2ZW50IjpmYWxzZSwicGFyYW1ldGVycyI6W10sImRhdGEiOm51bGx9eyJuYW1lIjoiRXZ0VGVhbUlkQXNzb2NpYXRpb24iLCJzdGF0dXMiOiJPayIsInJlc3BvbnNlIjpmYWxzZSwiZXZlbnQiOnRydWUsInBhcmFtZXRlcnMiOlsidGVhbTEiXSwiZGF0YSI6bnVsbH0=eyJuYW1lIjoiRXZ0U3RhcnRUdXJuIiwic3RhdHVzIjoiT2siLCJyZXNwb25zZSI6ZmFsc2UsImV2ZW50Ijp0cnVlLCJwYXJhbWV0ZXJzIjpbXSwiZGF0YSI6bnVsbH0=";
		byte[] encoded = Base64.getDecoder().decode(wut.replaceAll("[=\0]", "").getBytes());
		String decoded = new String(encoded);
		System.out.println(decoded);
		
	}
	private static byte[] convertToByteArray(String hexastring) {
		if (hexastring == null)
			return null;
		String[] hexas = hexastring.trim().split("-");
		byte[] bytes = new byte[hexas.length];
		for (int i = 0; i < hexas.length; i++) {
			bytes[i] = new BigInteger(hexas[i], 16).byteValue();
			// Convert.ToByte(hexas[i], 16);
		}
		return bytes;
	}
	@Test
	public void testAttributeBoost() {
		BigDecimal val = new BigDecimal(400);
		BigDecimal cv = val;
		cv = cv.add(TraitType.Percentage.add(cv, BigDecimal.valueOf(10)));
		cv = cv.add(TraitType.Percentage.add(cv, BigDecimal.valueOf(10)));
		cv = cv.subtract(TraitType.Percentage.add(cv, BigDecimal.valueOf(5)));
		Assert.assertEquals(BigDecimal.valueOf(459.8).doubleValue(), cv.doubleValue(), .0);
	}
	
	@Test
	public void testBattle() throws Exception {
		
		StringBuffer errors = new StringBuffer();
		Token token = getToken(errors);
		Socket socket = new Socket("localhost", 8888);
		
		CommandOutputStreamHelper cos = new CommandOutputStreamHelper(socket.getOutputStream());
		CommandInputStreamHelper cis = new CommandInputStreamHelper(socket.getInputStream());
		
		
		//authenticate
		cos.send(CommandName.Authentication.createCommand(token.getAccessToken()));
		Command command = cis.read();
		Assert.assertEquals(CommandStatus.Ok, command.getStatus());
		
		//begin mission
		cos.send(CommandName.Mission.createCommand("1"));
		command = cis.read();
		Assert.assertEquals(CommandStatus.Ok, command.getStatus());
		
		Command ev = null;
		String teamId = null;
		while((ev = cis.read()) != null) {
			if(CommandName.EvtSkillUsed.equals(ev.getName())) {
				System.out.println(String.format("Using skill %s", ev.getData()));
			} else if(CommandName.EvtStartTurn.equals(ev.getName())) {
				cos.send(CommandName.UseSkill.createCommand(Battleground.TEAM1, "swapSkill", MinionPosition.Top.name()));
			} else if(CommandName.EvtGameEnd.equals(ev.getName())) {
				if(ev.getParameters()[0].equals(teamId)) {
					System.out.println("Im winner");
				} else {
					System.out.println("Im looser");	
				}
			} else if(CommandName.EvtTeamIdAssociation.equals(ev.getName())) {
				teamId = ev.getParameters()[0];
				cos.send(CommandName.SyncTeam.createCommand(Battleground.TEAM1));
			} else if(CommandName.SyncTeam.equals(ev.getName())) {
				System.out.println(ev.getData());
			} else if(CommandName.UseSkill.equals(ev.getName())) {
				System.out.println(ev.getData());
			}
		}
		
		//sync teams
		cos.send(CommandName.SyncTeam.createCommand(Battleground.TEAM1));
		command = cis.read();
		Assert.assertEquals(CommandStatus.Ok, command.getStatus());
		cos.send(CommandName.SyncTeam.createCommand(Battleground.TEAM2));
		command = cis.read();
		Assert.assertEquals(CommandStatus.Ok, command.getStatus());
		
//		//use skills

		
//		cos.send(CommandName.TestDamage.createCommand(MinionPosition.Bot.name(), MinionPosition.Top.name(), MinionPosition.Mid.name(), MinionPosition.Leader.name()));
//		command = cis.read();
//		Assert.assertEquals(CommandStatus.Ok, command.getStatus());
//		
//		cos.send(CommandName.TestKill.createCommand(MinionPosition.Top.name()));
//		command = cis.read();
//		Assert.assertEquals(CommandStatus.Ok, command.getStatus());
		
//		cos.send(CommandName.SyncTeam.createCommand(TeamType.My.name()));
//		command = cis.read();
//		Assert.assertEquals(CommandStatus.Ok, command.getStatus());
		
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
