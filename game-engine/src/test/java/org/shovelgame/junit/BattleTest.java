package org.shovelgame.junit;

import java.math.BigDecimal;
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
			} if(CommandName.EvtStartTurn.equals(ev.getName())) {
				cos.send(CommandName.UseSkill.createCommand(Battleground.TEAM1, MinionPosition.Leader.name(), "testSkill"));
				command = cis.read();
				Assert.assertEquals(CommandStatus.Ok, command.getStatus());
			} else if(CommandName.EvtGameEnd.equals(ev.getName())) {
				if(ev.getParameters()[0].equals(teamId)) {
					System.out.println("Im winner");
				} else {
					System.out.println("Im looser");	
				}
			} else if(CommandName.EvtTeamIdAssociation.equals(ev.getName())) {
				teamId = ev.getParameters()[0];
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
