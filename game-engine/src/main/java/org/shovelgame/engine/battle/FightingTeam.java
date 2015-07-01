package org.shovelgame.engine.battle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import org.shovelgame.engine.session.command.BigData;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.Trait;
import org.shovelgame.game.domain.model.TraitTarget;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FightingTeam implements BigData {
	
	private Map<MinionPosition, FightingMinion> minions;
	
	@JsonIgnore
	private OpponentTeamDelegate opponentTeamDelegate;
	
	@JsonIgnore
	private Team team;
	
	@JsonIgnore
	private Battleground instance;
	
	public FightingTeam(Team team, Battleground instance) {
		this.team = team;
		this.instance = instance;
		this.minions = new HashMap<>();
		this.minions.put(MinionPosition.Leader, new FightingMinion(MinionPosition.Leader, this));
		this.minions.put(MinionPosition.Top, new FightingMinion(MinionPosition.Top, this));
		this.minions.put(MinionPosition.Mid, new FightingMinion(MinionPosition.Mid, this));
		this.minions.put(MinionPosition.Bot, new FightingMinion(MinionPosition.Bot, this));
	}

	public void initializeTraits(OpponentTeamDelegate delegate) {
		this.opponentTeamDelegate = delegate;
		this.updateTraits();
	}
	
	public void initializeStats() {
		this.minions.forEach(new BiConsumer<MinionPosition, FightingMinion>() {
			@Override
			public void accept(MinionPosition t, FightingMinion u) {
				u.initializeStats();
			}
		});
	}
	public void updateTraits() {
		this.minions.forEach(new BiConsumer<MinionPosition, FightingMinion>() {
			@Override
			public void accept(MinionPosition t, FightingMinion u) {
				u.updateTraits();
			}
		});
	}

	public Team getTeam() {
		return team;
	}
	
	public Set<MinionTrait> findTraitsForPosition(MinionPosition position) {
		Set<MinionTrait> traits = new HashSet<>();
		this.fillTraits(this.minions, traits, position, false);
		this.fillTraits(this.opponentTeamDelegate.getTeam().getMinions(), traits, position, true);
		return traits;
	}
	
	private void fillTraits(Map<MinionPosition, FightingMinion> minions, Set<MinionTrait> traits, MinionPosition position, boolean isOpponent) {
		minions.forEach(new BiConsumer<MinionPosition, FightingMinion>() {
			@Override
			public void accept(MinionPosition t, FightingMinion u) {
				if(!u.isDied())
				traits.addAll(findTraitsForPosition(u, position, isOpponent));
			}
		});
	}
	
	private Set<MinionTrait> findTraitsForPosition(FightingMinion minion, MinionPosition position, boolean isOpponent) {
		Set<MinionTrait> traits = new HashSet<>();
		for(MinionTrait mtrait: minion.getMinion().getMinionModel().getMinionTraits()) {
			Trait trait = mtrait.getTrait();
			if(isOpponent!=trait.getAlg().isForOpponent()) {
				continue;
			}
			Set<TraitTarget> targets = trait.getTraitTargets();
			if(CollectionUtils.isEmpty(targets) && minion.getPosition().equals(position)) {
				traits.add(mtrait);
				continue;
			}
			for(TraitTarget target: targets) {
				if(target.getPosition().equals(position)) {
					traits.add(mtrait);
				}
			}
		}
		return traits;
	}
	public Map<MinionPosition, FightingMinion> getMinions() {
		return minions;
	}
	public Battleground getInstance() {
		return instance;
	}
	public OpponentTeamDelegate getOpponentTeamDelegate() {
		return opponentTeamDelegate;
	}
}
