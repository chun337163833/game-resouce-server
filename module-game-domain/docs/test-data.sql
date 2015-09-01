--create version of application
insert into data.version (value) values (1.0);
--create texture groups
insert into data.texture_group (id) values('classes');
insert into data.texture_group (id) values('backgrounds');
insert into data.texture_group (id) values('skills');
insert into data.texture_group (id) values('icons');
insert into data.texture_group (id) values('hud');

insert into data.texture (id, texture_group, version) values ('Zola', 'classes', 1);
insert into data.texture (id, texture_group, version) values ('Armodius', 'classes', 1);
insert into data.texture (id, texture_group, version) values ('Tera', 'classes', 1);
insert into data.texture (id, texture_group, version) values ('nature', 'backgrounds', 1);
insert into data.texture (id, texture_group, version) values ('loading', 'backgrounds', 1);

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
insert into model.skill (id, alg, attribute_type, icon_name, power, cooldown, ticks, type, skill_id) values (nextval('model.skill_id_seq'), 'Damage', 'Health', 'skill1', 5, 0, 0, 'PhysicalPower', 'testSkill');
insert into model.skill (id, alg, icon_name, cooldown, skill_id) values (nextval('model.skill_id_seq'), 'Swap', 'skill1', 3, 'swapSkill');

--1
insert into model.trait (id, type, affected_attribute_type, alg, power, icon_name, trait_id) values (nextval('model.trait_id_seq'), 'Percentage', 'Health', 'Increase', 0.1, 'health', 'healthBoost');
--2
insert into model.trait (id, type, affected_attribute_type, alg, power, icon_name, trait_id) values (nextval('model.trait_id_seq'), 'Addition', 'PhysicalPower', 'Increase', 20, 'health', 'attackBoost');
--3
insert into model.trait (id, type, affected_attribute_type, alg, power, icon_name, trait_id) values (nextval('model.trait_id_seq'), 'Percentage', 'Health', 'Decrease', 0.05, 'health', 'healthWeakness');
--4
insert into model.trait (id, type, affected_skill_alg, alg, power, icon_name, trait_id) values (nextval('model.trait_id_seq'), 'Percentage', 'Damage', 'Increase', 0.5, 'health', 'damageSkillBoost');

--test healer minion
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'HealerBot', 'Healer', 'Zola', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);

insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 1, 1);
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Bot');
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 20);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);

--test damage minion1
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot1', 'Damage', 'Tera', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 150);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);
insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 2, 1);
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Mid');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Top');
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 1);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);

--test damage minion2
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot2', 'Damage', 'Armodius', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 150);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);
insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 3, 1);
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Bot');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Top');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Mid');

insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);

--test tank minion2
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'TankBot', 'Tank', 'Zola', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);
insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 2, 1);
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Bot');
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);
insert into data.team (id, leader, minion_bot, minion_mid, minion_top) values (nextval('data.team_id_seq'), 2, 3, 1, 4);

insert into model.mission (id, team, background) values (nextval('model.mission_id_seq'), currval('data.team_id_seq'), 'nature');

insert into model.mission_reward (id, mission, value, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), 5, 0.25, 'Diamond');
insert into model.mission_reward (id, mission, item, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), currval('model.item_model_id_seq'), 0.25, 'Item');
insert into model.mission_reward (id, mission, minion, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), currval('model.minion_model_id_seq'), 0.25, 'Minion');
insert into model.mission_reward (id, mission, value, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), 1000, 0.25, 'Gold');


--Player test
--test healer minion
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'HealerBot', 'Healer', 'Zola', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 50);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);
insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 2, 1);
insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 4, 1);

insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 20);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 2, 1);

--test damage minion1
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot1', 'Damage', 'Zola', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 150);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);
insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 3, 1);
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Leader');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Mid');
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 1);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);

--test damage minion2
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'DamageBot2', 'Damage', 'Tera', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 300);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);
insert into model.minion_trait (id, minion_model, trait, required_level) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 1, 1);
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Bot');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Top');
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);

--test tank minion2
insert into model.minion_model (id, name, specialization, texture, rarity) values (nextval('model.minion_model_id_seq'), 'TankBot', 'Damage', 'Armodius', 'Common');
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Health', 400);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'Mana', 200);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPower', 10);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritChance', 0.3);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalPenetration', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalResistance', 0.2);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'PhysicalCritResistance', 0.05);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPower', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritChance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellPenetration', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'SpellCritResistance', 100);
insert into model.minion_attribute (id, minion_model, type, value) values (nextval('model.minion_attribute_id_seq'), currval('model.minion_model_id_seq'), 'CriticalDamage', 0.5);
insert into model.minion_trait (id, minion_model, trait, required_level, override_power) values (nextval('model.minion_trait_id_seq'), currval('model.minion_model_id_seq'), 1, 1, 50);
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Top');
insert into model.trait_target (id, trait, position) values (nextval('model.trait_target_id_seq'), currval('model.minion_trait_id_seq'), 'Mid');
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 30);
insert into model.minion_skill (minion_model, skill, required_level) values (currval('model.minion_model_id_seq'), 1, 1);
insert into data.team (id, leader, minion_bot, minion_mid, minion_top) values (nextval('data.team_id_seq'), 5, 6, 7, 8);


insert into data.player (id, user_name, password, team) values (nextval('data.player_id_seq'), 'test', 'c7c15778e6211c00178b73bdbb977181f5d1d3605ee2ec16a8f4214c3c1b2a79', currval('data.team_id_seq'));
insert into data.role (id, code) values (nextval('data.role_id_seq'), 'ROLE_PLAYER');
insert into data.rights (role, player) values (currval('data.role_id_seq'), currval('data.player_id_seq'));