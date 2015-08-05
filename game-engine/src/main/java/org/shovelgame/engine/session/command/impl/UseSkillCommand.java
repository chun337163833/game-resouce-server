package org.shovelgame.engine.session.command.impl;
import org.shovelgame.annotation.Logger;
import org.shovelgame.engine.battle.Battleground;
import org.shovelgame.engine.io.ClientDelegate;
import org.shovelgame.engine.io.ClientStreamException;
import org.shovelgame.engine.session.command.BattleCommandProcessor;
import org.shovelgame.engine.session.command.Command;
import org.shovelgame.engine.session.command.CommandException;
import org.shovelgame.engine.session.command.CommandName;
import org.shovelgame.engine.session.command.parameters.UseSkillParameters;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.engine.skill.SkillResult;
import org.shovelgame.engine.skill.SkillUsageException;
@Logger
public class UseSkillCommand extends BattleCommandProcessor {
	@Override
	public void process(Command command, ClientDelegate delegate) throws CommandException {
		UseSkillParameters parameters = UseSkillParameters.read(command.getParameters());
		Battleground bg = battleDelegate.getBattleground();
		try {
			SkillResult result = bg.useSkill(parameters, delegate.getClient());
			result.setMetadata(parameters);
			bg.update();
			Communicator comm = bg.getCommunicatorByClient(delegate.getClient());
			comm.send(CommandName.UseSkill.createCommand().asResponse());
			bg.getSession().sendAll(CommandName.EvtSkillUsed.createCommand(result));
			bg.nextTurn();
		} catch (SkillUsageException e) {
			log.error("", e);
			try {
				delegate.getClient().sendError(command.asResponse(), e.getMessage());
			} catch (ClientStreamException e1) {
				log.error("", e1);
			}
		}
	}
}