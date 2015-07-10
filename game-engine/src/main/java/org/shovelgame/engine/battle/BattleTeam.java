package org.shovelgame.engine.battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import org.shovelgame.engine.collection.ValuePrioritySet;
import org.shovelgame.engine.session.command.BigData;
import org.shovelgame.engine.session.communication.Communicator;
import org.shovelgame.game.domain.data.Item;
import org.shovelgame.game.domain.data.Team;
import org.shovelgame.game.domain.enumeration.MinionPosition;
import org.shovelgame.game.domain.enumeration.SkillAlgorithm;
import org.shovelgame.game.domain.model.MinionTrait;
import org.shovelgame.game.domain.model.Trait;
import org.shovelgame.game.domain.model.TraitTarget;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BattleTeam implements BigData {

	private Map<MinionPosition, BattleMinion> minions;

	private String teamId;
	
	
	private BattleItem[] items;
	
	@JsonIgnore
	private OpponentTeamDelegate opponentTeamDelegate;

	@JsonIgnore
	private Team team;
	
	@JsonIgnore
	private MinionPosition[] order = {MinionPosition.Leader, MinionPosition.Top, MinionPosition.Mid, MinionPosition.Bot};

	@JsonIgnore
	private Battleground instance;

	public BattleTeam(Team team, Battleground instance, String teamId) {
		this.team = team;
		this.instance = instance;
		this.teamId = teamId;
		List<BattleItem> items = new ArrayList<>();
		team.getItems().forEach((Item i) -> items.add(new BattleItem(i)));
		this.items = items.toArray(new BattleItem[team.getItems().size()]);
		this.minions = new HashMap<>();
		this.minions.put(MinionPosition.Leader, new BattleMinion(this));
		this.minions.put(MinionPosition.Top, new BattleMinion(this));
		this.minions.put(MinionPosition.Mid, new BattleMinion(this));
		this.minions.put(MinionPosition.Bot, new BattleMinion(this));
		this.minions.forEach((MinionPosition p, BattleMinion m) -> m.build());
	}

	public Communicator getCommunicator() {
		return this.instance.getCommunicatorByTeam(this);
	}
	
	public void setOpponentDelegate(OpponentTeamDelegate delegate) {
		this.opponentTeamDelegate = delegate;
	}

	public void update() {
		this.minions.forEach(new BiConsumer<MinionPosition, BattleMinion>() {
			@Override
			public void accept(MinionPosition t, BattleMinion u) {
				u.update();
			}
		});
	}

	public MinionPosition getPositionForMinion(BattleMinion minion) {
		for (Map.Entry<MinionPosition, BattleMinion> entry : this.minions
				.entrySet()) {
			if (entry.getValue().equals(minion)) {
				return entry.getKey();
			}
		}
		throw new IllegalStateException("Minion not matched with minions map");
	}

	public Team getTeam() {
		return team;
	}

	public Set<BattleTrait> findTraitsForSkill(MinionPosition position,
			SkillAlgorithm skillAlg) {
		Set<BattleTrait> traits = new ValuePrioritySet<>();
		this.fillTraits(this.minions, traits, new TraitAssignProperties(
				skillAlg, position, false));
		this.fillTraits(this.opponentTeamDelegate.getTeam().getMinions(),
				traits, new TraitAssignProperties(skillAlg, position, true));
		return traits;
	}

	public Set<BattleTrait> findTraitsForStats(MinionPosition position) {
		Set<BattleTrait> traits = new ValuePrioritySet<>();
		this.fillTraits(this.minions, traits, new TraitAssignProperties(
				position, false));
		this.fillTraits(this.opponentTeamDelegate.getTeam().getMinions(),
				traits, new TraitAssignProperties(position, true));
		return traits;
	}

	private void fillTraits(Map<MinionPosition, BattleMinion> minions,
			Set<BattleTrait> traits, TraitAssignProperties props) {
		minions.forEach(new BiConsumer<MinionPosition, BattleMinion>() {
			@Override
			public void accept(MinionPosition t, BattleMinion u) {
				if (!u.isDied())
					traits.addAll(findTraits(u, props));
			}
		});
	}

	private Set<BattleTrait> findTraits(BattleMinion minion,
			TraitAssignProperties props) {
		Set<BattleTrait> traits = new ValuePrioritySet<>();
		for (MinionTrait mtrait : minion.getMinion().getMinionModel()
				.getMinionTraits()) {
			Trait trait = mtrait.getTrait();
			if (props.isOpponent != trait.getAlg().isForOpponent()) {
				continue;
			}

			boolean isStatTrait = props.skill == null
					&& trait.getAffectedAttributeType() != null;
			boolean isSkillTrait = props.skill != null
					&& trait.getAffectedSkillAlg() != null
					&& trait.getAffectedSkillAlg().equals(props.skill);
			boolean isPossibleTrait = isSkillTrait || isStatTrait;
			Set<TraitTarget> targets = mtrait.getTraitTargets();
			if (CollectionUtils.isEmpty(targets)
					&& minion.getPosition().equals(props.position)
					&& isPossibleTrait) {
				traits.add(new BattleTrait(mtrait, minion));
				continue;
			}
			for (TraitTarget target : targets) {
				if (target.getPosition().equals(props.position)
						&& isPossibleTrait) {
					traits.add(new BattleTrait(mtrait, minion));
					break;
				}
			}
		}
		return traits;
	}

	public Map<MinionPosition, BattleMinion> getMinions() {
		return minions;
	}

	public Battleground getInstance() {
		return instance;
	}

	public OpponentTeamDelegate getOpponentTeamDelegate() {
		return opponentTeamDelegate;
	}

	class TraitAssignProperties {
		public SkillAlgorithm skill;
		public MinionPosition position;
		public boolean isOpponent;

		public TraitAssignProperties(SkillAlgorithm skill, boolean isOpponent) {
			super();
			this.skill = skill;
			this.isOpponent = isOpponent;
		}

		public TraitAssignProperties(MinionPosition position, boolean isOpponent) {
			super();
			this.position = position;
			this.isOpponent = isOpponent;
		}

		public TraitAssignProperties(SkillAlgorithm skill,
				MinionPosition position, boolean isOpponent) {
			super();
			this.skill = skill;
			this.position = position;
			this.isOpponent = isOpponent;
		}

	}
	
	public String getTeamId() {
		return teamId;
	}
	public MinionPosition[] getOrder() {
		return order;
	}
	public BattleItem[] getItems() {
		return items;
	}
}
