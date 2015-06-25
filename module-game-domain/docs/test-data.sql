--atrribute types
insert into model.attribute_type (id) values ('Health');
insert into model.attribute_type (id) values ('Mana');
insert into model.attribute_type (id) values ('PhysicalPower');
insert into model.attribute_type (id) values ('PhysicalCritChance');
insert into model.attribute_type (id) values ('PhysicalPenetration');
insert into model.attribute_type (id) values ('PhysicalResistance');
insert into model.attribute_type (id) values ('PhysicalCritResistance');
insert into model.attribute_type (id) values ('SpellPower');
insert into model.attribute_type (id) values ('SpellCritChance');
insert into model.attribute_type (id) values ('SpellPenetration');
insert into model.attribute_type (id) values ('SpellResistance');
insert into model.attribute_type (id) values ('SpellCritResistance');
insert into model.attribute_type (id) values ('CriticalDamage');

--items
insert into model.item_model (id, attribute_type, value, max_enchant, type, icon_name, rarity) values (nextval('model.item_model_id_seq'), 'Health', 5, 5, 'Talisman', 'item', 'Common');
--insert into data.item (id, item_model, team) values (nextval('data.item_id_seq'), currval('model.item_model_id_seq'), 1);


--AI test
--test skill
insert into model.skill (id, alg, attribute_type, icon_name, power, cooldown, ticks, type) values (nextval('model.skill_id_seq'), 'Damage', 'Health', 'skill1', 5, 0, 0, 'PhysicalPower');

--test healer minion
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'HealerBot', 'Healer', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'Mana', 'Increase', 10, 'health');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Bot');
insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 20);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);

--test damage minion1
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot1', 'Damage', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'Health', 'Decrease', 10, 'health');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Mid');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Top');
insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 1);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);

--test damage minion2
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot2', 'Damage', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'SpellPower', 'Decrease', 4, 'health');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Bot');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Top');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Mid');

insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);

--test tank minion2
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'TankBot', 'Tank', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'Health', 'Increase', 5, 'health');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Bot');
insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);
insert into data.team (id, leader, minion_bot, minion_mid, minion_top) values (nextval('data.team_id_seq'), 1, 2, 3, 4);

insert into model.mission (id, team) values (nextval('model.mission_id_seq'), currval('data.team_id_seq'));

insert into model.mission_reward (id, mission, value, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), 5, 0.25, 'Diamond');
insert into model.mission_reward (id, mission, item, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), currval('model.item_model_id_seq'), 0.25, 'Item');
insert into model.mission_reward (id, mission, minion, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), currval('model.minion_model_id_seq'), 0.25, 'Minion');
insert into model.mission_reward (id, mission, value, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), 1000, 0.25, 'Gold');


--Player test
--test healer minion
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'HealerBot', 'Healer', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'SpellPower', 'Increase', 10, 'health');
insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 20);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);

--test damage minion1
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot1', 'Damage', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'Health', 'Decrease', 2, 'health');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Mid');
insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 1);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);

--test damage minion2
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot2', 'Damage', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'PhysicalPower', 'Decrease', 5, 'health');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Bot');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Top');
insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);

--test tank minion2
insert into model.minion_model (id, name, specialization, image_bundle_name, rarity) values (nextval('model.minion_model_id_seq'), 'TankBot', 'Damage', 'Zola', 'Common');
insert into model.trait (id, affected_attribute_type, alg, power, icon_name) values (nextval('model.trait_id_seq'), 'Health', 'Increase', 10, 'health');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Top');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.trait_id_seq'), 'Mid');
insert into model.minion_trait (minion_model, trait, required_level) values (currval('model.minion_model_id_seq'), currval('model.trait_id_seq'), 1);
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), currval('model.skill_id_seq'), 1);
insert into data.team (id, leader, minion_bot, minion_mid, minion_top) values (nextval('data.team_id_seq'), 5, 6, 7, 8);


insert into data.player (id, user_name, password, team) values (nextval('data.player_id_seq'), 'test', 'c7c15778e6211c00178b73bdbb977181f5d1d3605ee2ec16a8f4214c3c1b2a79', currval('data.team_id_seq'));
insert into data.role (id, code) values (nextval('data.role_id_seq'), 'ROLE_PLAYER');
insert into data.rights (role, player) values (currval('data.role_id_seq'), currval('data.player_id_seq'));

