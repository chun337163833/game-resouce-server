package org.shovelgame.engine.ai;

import org.shovelgame.engine.battle.BattleMinion;
import org.shovelgame.engine.battle.BattleSkill;
import org.shovelgame.engine.battle.BattleTeam;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.ClientConnection;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;

public class AI implements ClientConnection {

	private Battleground battleground;
	private BattleTeam team;

	public AI(Battleground battleground) {
		super();
		this.battleground = battleground;
	}
	
	public void send(Command command) throws ClientStreamException {
		try {
			this.battleground.getSession().received(command, () -> this);
		} catch (CommandException e) {
			throw new ClientStreamException(e);
		}
	}

	@Override
	public void sendError(Command unproceseedCommand, String message) throws ClientStreamException {
		
	}
	
	public void received(Command command) throws ClientStreamException {
		if(CommandName.EvtTeamIdAssociation.equals(command.getName())) {
			this.team = battleground.getTeam(command.getParameters()[0]);
		} else if(CommandName.EvtStartTurn.equals(command.getName()) && !battleground.getSession().isGameEnd()) {
			this.play(MinionPosition.valueOf(command.getParameters()[0]));
		}
	}

	private void play(MinionPosition position) throws ClientStreamException {
		BattleMinion minion = this.team.getMinions().get(position);
		BattleMinion minion2 = this.battleground.getQueue().getCurrent();
		if(!minion.equals(minion2)) {
			return;
		}
		for(BattleSkill skill:minion.getSkills()) {
			if(skill.getMinionSkill().getSkill().getAlg().equals(SkillAlgorithm.Damage)) {
				String opponentTeam = this.team.getOpponentTeamDelegate().getTeam().getTeamId();
				MinionPosition[] positions = skill.getAvailablePositions().get(opponentTeam);
				this.send(CommandName.UseSkill.createCommand(opponentTeam, skill.getSkillId(), positions[0].name()));
				break;	
			}
		}
	}
	
}
