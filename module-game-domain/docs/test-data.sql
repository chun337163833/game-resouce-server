insert into model.minion_specialization (id, type) values (nextval('model.minion_specialization_id_seq'), 'Healer');
insert into model.minion_model (id, name, specialization, image_bundle_name) values (nextval('model.minion_model_id_seq'), 'Zola', currval('model.minion_specialization_id_seq'), 'Zola');
insert into model.attribute_type (id) values ('Health');
insert into model.item_model (id, attribute_type, value, max_enchant, type, icon_name) values (nextval('model.item_model_id_seq'), 'Health', 5, 5, 'Talisman', 'item');
insert into data.minion (id, minion_model, level) values (nextval('data.minion_id_seq'), currval('model.minion_model_id_seq'), 1);
insert into data.team (id, leader, minion_bot, minion_mid, minion_top) values (nextval('data.team_id_seq'), currval('data.minion_id_seq'), currval('data.minion_id_seq'), currval('data.minion_id_seq'), currval('data.minion_id_seq'));

insert into model.mission (id, team) values (nextval('model.mission_id_seq'), currval('data.team_id_seq'));

insert into model.mission_reward (id, mission, value, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), 5, 0.25, 'Diamond');
insert into model.mission_reward (id, mission, item, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), currval('model.item_model_id_seq'), 0.25, 'Item');
insert into model.mission_reward (id, mission, minion, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), currval('model.minion_model_id_seq'), 0.25, 'Minion');

insert into model.mission_reward (id, mission, value, chance, dtype) values (nextval('model.mission_reward_id_seq'), currval('model.mission_id_seq'), 1000, 0.25, 'Gold');

insert into data.team (id, leader, minion_bot, minion_mid, minion_top) values (nextval('data.team_id_seq'), currval('data.minion_id_seq'), currval('data.minion_id_seq'), currval('data.minion_id_seq'), currval('data.minion_id_seq'));
insert into data.player (id, user_name, password, team) values (nextval('data.player_id_seq'), 'test', 'c7c15778e6211c00178b73bdbb977181f5d1d3605ee2ec16a8f4214c3c1b2a79', currval('data.team_id_seq'));
insert into data.role (id, code) values (nextval('data.role_id_seq'), 'ROLE_PLAYER');
insert into data.rights (role, player) values (currval('data.role_id_seq'), currval('data.player_id_seq'));

