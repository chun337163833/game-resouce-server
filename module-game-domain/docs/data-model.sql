DROP TABLE IF EXISTS model.attribute_type CASCADE;
DROP TABLE IF EXISTS i18n.attribute_type_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.attribute_type_description_id_seq;
DROP TABLE IF EXISTS model.enchantment_type CASCADE;
DROP SEQUENCE IF EXISTS model.enchantment_type_id_seq;
DROP TABLE IF EXISTS i18n.enchantment_type_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.enchantment_type_description_id_seq;
DROP TABLE IF EXISTS data.item CASCADE;
DROP SEQUENCE IF EXISTS data.item_id_seq;
DROP TABLE IF EXISTS model.item_enchantment CASCADE;
DROP SEQUENCE IF EXISTS model.item_enchantment_id_seq;
DROP TABLE IF EXISTS model.item_model CASCADE;
DROP SEQUENCE IF EXISTS model.item_model_id_seq;
DROP TABLE IF EXISTS i18n.item_model_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.item_model_description_id_seq;
DROP TABLE IF EXISTS i18n.language CASCADE;
DROP TABLE IF EXISTS data.minion CASCADE;
DROP SEQUENCE IF EXISTS data.minion_id_seq;
DROP TABLE IF EXISTS model.minion_attribute CASCADE;
DROP SEQUENCE IF EXISTS model.minion_attribute_id_seq;
DROP TABLE IF EXISTS model.minion_model CASCADE;
DROP SEQUENCE IF EXISTS model.minion_model_id_seq;
DROP TABLE IF EXISTS model.minion_skill CASCADE;
DROP TABLE IF EXISTS model.minion_specialization CASCADE;
DROP SEQUENCE IF EXISTS model.minion_specialization_id_seq;
DROP TABLE IF EXISTS i18n.minion_specialization_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.minion_specialization_description_id_seq;
DROP TABLE IF EXISTS model.minion_trait CASCADE;
DROP TABLE IF EXISTS model.mission CASCADE;
DROP SEQUENCE IF EXISTS model.mission_id_seq;
DROP TABLE IF EXISTS i18n.mission_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.mission_description_id_seq;
DROP TABLE IF EXISTS model.mission_reward CASCADE;
DROP SEQUENCE IF EXISTS model.mission_reward_id_seq;
DROP TABLE IF EXISTS data.player CASCADE;
DROP SEQUENCE IF EXISTS data.player_id_seq;
DROP TABLE IF EXISTS data.seeker CASCADE;
DROP SEQUENCE IF EXISTS data.seeker_id_seq;
DROP TABLE IF EXISTS model.seeker_model CASCADE;
DROP SEQUENCE IF EXISTS model.seeker_model_id_seq;
DROP TABLE IF EXISTS model.seeker_specialization CASCADE;
DROP SEQUENCE IF EXISTS model.seeker_specialization_id_seq;
DROP TABLE IF EXISTS i18n.seeker_specialization_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.seeker_specialization_description_id_seq;
DROP TABLE IF EXISTS model.skill CASCADE;
DROP SEQUENCE IF EXISTS model.skill_id_seq;
DROP TABLE IF EXISTS i18n.skill_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.skill_description_id_seq;
DROP TABLE IF EXISTS i18n.skill_name CASCADE;
DROP SEQUENCE IF EXISTS i18n.skill_name_id_seq;
DROP TABLE IF EXISTS data.team CASCADE;
DROP SEQUENCE IF EXISTS data.team_id_seq;
DROP TABLE IF EXISTS model.trait CASCADE;
DROP SEQUENCE IF EXISTS model.trait_id_seq;
DROP TABLE IF EXISTS i18n.trait_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.trait_description_id_seq;
DROP TABLE IF EXISTS i18n.trait_name CASCADE;
DROP SEQUENCE IF EXISTS i18n.trait_name_id_seq;
DROP TABLE IF EXISTS model.trait_target CASCADE;
DROP SEQUENCE IF EXISTS model.trait_target_id_seq;

CREATE TABLE model.attribute_type ( 
	id varchar(50) NOT NULL
);

CREATE SEQUENCE i18n.attribute_type_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.attribute_type_description ( 
	id bigint DEFAULT nextval(('i18n.attribute_type_description_id_seq'::text)::regclass) NOT NULL,
	attribute_type varchar(50) NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE model.enchantment_type_id_seq INCREMENT 1 START 1;

CREATE TABLE model.enchantment_type ( 
	id bigint DEFAULT nextval(('model.enchantment_type_id_seq'::text)::regclass) NOT NULL,
	attribute_type varchar(50),
	skill_model bigint
);

CREATE SEQUENCE i18n.enchantment_type_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.enchantment_type_description ( 
	id bigint DEFAULT nextval(('i18n.enchantment_type_description_id_seq'::text)::regclass) NOT NULL,
	enchantment_type bigint NOT NULL,
	lang char(2),
	value text
);

CREATE SEQUENCE data.item_id_seq INCREMENT 1 START 1;

CREATE TABLE data.item ( 
	id bigint DEFAULT nextval(('data.item_id_seq'::text)::regclass) NOT NULL,
	item_model bigint NOT NULL,
	team bigint,
	owner bigint,
	enchant integer DEFAULT 0 NOT NULL
);

CREATE SEQUENCE model.item_enchantment_id_seq INCREMENT 1 START 1;

CREATE TABLE model.item_enchantment ( 
	id bigint DEFAULT nextval(('model.item_enchantment_id_seq'::text)::regclass) NOT NULL,
	item_model bigint NOT NULL,
	enchantment_type bigint NOT NULL,
	base_value decimal(10,3) NOT NULL
);

CREATE SEQUENCE model.item_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.item_model ( 
	id bigint DEFAULT nextval(('model.item_model_id_seq'::text)::regclass) NOT NULL,
	attribute_type varchar(50) NOT NULL,
	value decimal(10,5) DEFAULT 0 NOT NULL,
	max_enchant integer DEFAULT 10,
	type varchar(10) NOT NULL,
	icon_name varchar(50) NOT NULL,
	price integer
);

CREATE SEQUENCE i18n.item_model_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.item_model_description ( 
	id bigint DEFAULT nextval(('i18n.item_model_description_id_seq'::text)::regclass) NOT NULL,
	item_model bigint NOT NULL,
	lang char(2),
	value text NOT NULL
);

CREATE TABLE i18n.language ( 
	id char(2) NOT NULL,
	description varchar(50) NOT NULL
);

CREATE SEQUENCE data.minion_id_seq INCREMENT 1 START 1;

CREATE TABLE data.minion ( 
	id bigint DEFAULT nextval(('data.minion_id_seq'::text)::regclass) NOT NULL,
	minion_model bigint NOT NULL,
	owner bigint,
	level integer DEFAULT 1 NOT NULL
);

CREATE SEQUENCE model.minion_attribute_id_seq INCREMENT 1 START 1;

CREATE TABLE model.minion_attribute ( 
	id bigint DEFAULT nextval(('model.minion_attribute_id_seq'::text)::regclass) NOT NULL,
	minion_model bigint NOT NULL,
	type varchar(50) NOT NULL,
	value decimal(10,3) NOT NULL
);

CREATE SEQUENCE model.minion_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.minion_model ( 
	id bigint DEFAULT nextval(('model.minion_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	image_bundle_name varchar(50) NOT NULL,
	specialization bigint NOT NULL,
	price integer
);

CREATE TABLE model.minion_skill ( 
	minion_model bigint NOT NULL,
	skill bigint NOT NULL,
	required_level integer NOT NULL,
	override_power decimal(10,3) DEFAULT 0
);

CREATE SEQUENCE model.minion_specialization_id_seq INCREMENT 1 START 1;

CREATE TABLE model.minion_specialization ( 
	id bigint DEFAULT nextval(('model.minion_specialization_id_seq'::text)::regclass) NOT NULL,
	type varchar(50) NOT NULL
);

CREATE SEQUENCE i18n.minion_specialization_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.minion_specialization_description ( 
	id bigint DEFAULT nextval(('i18n.minion_specialization_description_id_seq'::text)::regclass) NOT NULL,
	specialization bigint NOT NULL,
	lang char(2) NOT NULL,
	text text NOT NULL
);

CREATE TABLE model.minion_trait ( 
	minion_model bigint NOT NULL,
	trait bigint NOT NULL,
	required_level integer DEFAULT 1,
	override_power decimal(10,3)
);

CREATE SEQUENCE model.mission_id_seq INCREMENT 1 START 1;

CREATE TABLE model.mission ( 
	id bigint DEFAULT nextval(('model.mission_id_seq'::text)::regclass) NOT NULL,
	team bigint
);

CREATE SEQUENCE i18n.mission_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.mission_description ( 
	id bigint DEFAULT nextval(('i18n.mission_description_id_seq'::text)::regclass) NOT NULL,
	mission bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE model.mission_reward_id_seq INCREMENT 1 START 1;

CREATE TABLE model.mission_reward ( 
	id bigint DEFAULT nextval(('model.mission_reward_id_seq'::text)::regclass) NOT NULL,
	dtype varchar(100) NOT NULL,
	mission bigint NOT NULL,
	minion bigint,
	item bigint,
	seeker bigint,
	chance decimal(3,2),
	value integer
);

CREATE SEQUENCE data.player_id_seq INCREMENT 1 START 1;

CREATE TABLE data.player ( 
	id bigint DEFAULT nextval(('data.player_id_seq'::text)::regclass) NOT NULL
);

CREATE SEQUENCE data.seeker_id_seq INCREMENT 1 START 1;

CREATE TABLE data.seeker ( 
	id bigint DEFAULT nextval(('data.seeker_id_seq'::text)::regclass) NOT NULL,
	seeker_model bigint NOT NULL,
	owner bigint NOT NULL,
	level integer DEFAULT 1 NOT NULL
);

CREATE SEQUENCE model.seeker_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.seeker_model ( 
	id bigint DEFAULT nextval(('model.seeker_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	price integer NOT NULL,
	image_bundle_name varchar(50) NOT NULL
);

CREATE SEQUENCE model.seeker_specialization_id_seq INCREMENT 1 START 1;

CREATE TABLE model.seeker_specialization ( 
	id bigint DEFAULT nextval(('model.seeker_specialization_id_seq'::text)::regclass) NOT NULL,
	seeker_model bigint NOT NULL,
	type varchar(10) NOT NULL
);

CREATE SEQUENCE i18n.seeker_specialization_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.seeker_specialization_description ( 
	id bigint DEFAULT nextval(('i18n.seeker_specialization_description_id_seq'::text)::regclass) NOT NULL,
	seeker_specialization bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE model.skill_id_seq INCREMENT 1 START 1;

CREATE TABLE model.skill ( 
	id bigint DEFAULT nextval(('model.skill_id_seq'::text)::regclass) NOT NULL,
	alg varchar(50) NOT NULL,
	attribute_type varchar(50),
	icon_name varchar(50) NOT NULL,
	power decimal(10,3),
	cooldown integer NOT NULL,
	ticks integer
);

CREATE SEQUENCE i18n.skill_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.skill_description ( 
	id bigint DEFAULT nextval(('i18n.skill_description_id_seq'::text)::regclass) NOT NULL,
	skill bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE i18n.skill_name_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.skill_name ( 
	id bigint DEFAULT nextval(('i18n.skill_name_id_seq'::text)::regclass) NOT NULL,
	skill bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE data.team_id_seq INCREMENT 1 START 1;

CREATE TABLE data.team ( 
	id bigint DEFAULT nextval(('data.team_id_seq'::text)::regclass) NOT NULL,
	leader bigint NOT NULL,
	minion_top bigint NOT NULL,
	minion_mid bigint NOT NULL,
	minion_bot bigint NOT NULL,
	owner bigint
);

CREATE SEQUENCE model.trait_id_seq INCREMENT 1 START 1;

CREATE TABLE model.trait ( 
	id bigint DEFAULT nextval(('model.trait_id_seq'::text)::regclass) NOT NULL,
	affected_attribute_type varchar(50),
	affected_skill_alg varchar(50),
	alg varchar(50) NOT NULL,
	power decimal(10) DEFAULT 0,
	icon_name varchar(50) NOT NULL
);

CREATE SEQUENCE i18n.trait_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.trait_description ( 
	id bigint DEFAULT nextval(('i18n.trait_description_id_seq'::text)::regclass) NOT NULL,
	trait bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE i18n.trait_name_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.trait_name ( 
	id bigint DEFAULT nextval(('i18n.trait_name_id_seq'::text)::regclass) NOT NULL,
	trait bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE model.trait_target_id_seq INCREMENT 1 START 1;

CREATE TABLE model.trait_target ( 
	id bigint DEFAULT nextval(('model.trait_target_id_seq'::text)::regclass) NOT NULL,
	trait bigint NOT NULL,
	position varchar(50) NOT NULL
);


CREATE INDEX IXFK_attribute_type_description_attribute_type
	ON i18n.attribute_type_description (attribute_type);
CREATE INDEX IXFK_attribute_type_description_language
	ON i18n.attribute_type_description (lang);
CREATE INDEX IXFK_enchantment_type_attribute_type
	ON model.enchantment_type (attribute_type);
CREATE INDEX IXFK_enchantment_type_skill_model
	ON model.enchantment_type (skill_model);
CREATE INDEX IXFK_enchantment_type_description_language
	ON i18n.enchantment_type_description (lang);
CREATE INDEX IXFK_enchantment_type_description_enchantment_type
	ON i18n.enchantment_type_description (enchantment_type);
CREATE INDEX IXFK_item_item_model
	ON data.item (item_model);
CREATE INDEX IXFK_item_team
	ON data.item (team);
CREATE INDEX IXFK_item_player
	ON data.item (owner);
CREATE INDEX IXFK_enchantment_model_enchantment_type
	ON model.item_enchantment (enchantment_type);
CREATE INDEX IXFK_item_enchantment_item_model
	ON model.item_enchantment (item_model);
CREATE INDEX IXFK_item_model_attribute_type
	ON model.item_model (attribute_type);
CREATE INDEX IXFK_item_model_description_language
	ON i18n.item_model_description (lang);
CREATE INDEX IXFK_item_model_description_item_model
	ON i18n.item_model_description (item_model);
CREATE INDEX IXFK_minion_minion_model
	ON data.minion (minion_model);
CREATE INDEX IXFK_minion_player
	ON data.minion (owner);
CREATE INDEX IXFK_minion_minion_model_02
	ON data.minion (minion_model);
CREATE INDEX IXFK_minion_attribute_attribute_type
	ON model.minion_attribute (type);
CREATE INDEX IXFK_minion_attribute_minion_model
	ON model.minion_attribute (minion_model);
CREATE INDEX IXFK_minion_model_minion_specialization
	ON model.minion_model (specialization);
CREATE INDEX IXFK_minion_skill_minion_model
	ON model.minion_skill (minion_model);
CREATE INDEX IXFK_minion_skill_skill_model
	ON model.minion_skill (skill);
ALTER TABLE model.minion_specialization
	ADD CONSTRAINT UQ_hero_type_code UNIQUE (type);
CREATE INDEX IXFK_minion_specialization_description_language
	ON i18n.minion_specialization_description (lang);
CREATE INDEX IXFK_minion_specialization_description_specialization
	ON i18n.minion_specialization_description (specialization);
CREATE INDEX IXFK_minion_trait_trait_model
	ON model.minion_trait (trait);
CREATE INDEX IXFK_minion_trait_minion_model
	ON model.minion_trait (minion_model);
CREATE INDEX IXFK_mission_team
	ON model.mission (team);
CREATE INDEX IXFK_mission_description_language
	ON i18n.mission_description (lang);
CREATE INDEX IXFK_mission_description_mission
	ON i18n.mission_description (mission);
CREATE INDEX IXFK_mission_reward_seeker_model
	ON model.mission_reward (seeker);
CREATE INDEX IXFK_mission_reward_minion_model
	ON model.mission_reward (minion);
CREATE INDEX IXFK_mission_reward_item_model
	ON model.mission_reward (item);
CREATE INDEX IXFK_mission_reward_mission
	ON model.mission_reward (mission);
CREATE INDEX IXFK_seeker_seeker_model
	ON data.seeker (seeker_model);
CREATE INDEX IXFK_seeker_player
	ON data.seeker (owner);
CREATE INDEX IXFK_seeker_specialization_specialization_type
	ON model.seeker_specialization (type);
CREATE INDEX IXFK_seeker_specialization_seeker_model
	ON model.seeker_specialization (seeker_model);
CREATE INDEX IXFK_specialization_type_desc_specialization_type
	ON i18n.seeker_specialization_description (seeker_specialization);
CREATE INDEX IXFK_specialization_type_desc_l_language
	ON i18n.seeker_specialization_description (lang);
CREATE INDEX IXFK_skill_model_attribute_type
	ON model.skill (attribute_type);
CREATE INDEX IXFK_skill_model_descirption_skill_model
	ON i18n.skill_description (skill);
CREATE INDEX IXFK_skill_model_descirption_language
	ON i18n.skill_description (lang);
CREATE INDEX IXFK_skill_name_language
	ON i18n.skill_name (lang);
CREATE INDEX IXFK_skill_name_skill
	ON i18n.skill_name (skill);
CREATE INDEX IXFK_team_minion_04
	ON data.team (leader);
CREATE INDEX IXFK_team_hero
	ON data.team (leader);
CREATE INDEX IXFK_team_minion
	ON data.team (minion_top);
CREATE INDEX IXFK_team_minion_02
	ON data.team (minion_mid);
CREATE INDEX IXFK_team_minion_03
	ON data.team (minion_bot);
CREATE INDEX IXFK_team_player
	ON data.team (owner);
CREATE INDEX IXFK_trait_model_attribute_type
	ON model.trait (affected_attribute_type);
CREATE INDEX IXFK_trait_model_description_trait_model
	ON i18n.trait_description (trait);
CREATE INDEX IXFK_trait_model_description_language
	ON i18n.trait_description (lang);
CREATE INDEX IXFK_trait_name_trait
	ON i18n.trait_name (trait);
CREATE INDEX IXFK_trait_name_language
	ON i18n.trait_name (lang);
CREATE INDEX IXFK_trait_target_trait
	ON model.trait_target (trait);
ALTER TABLE model.attribute_type ADD CONSTRAINT PK_attribute_type 
	PRIMARY KEY (id);


ALTER TABLE i18n.attribute_type_description ADD CONSTRAINT PK_attribute_type_description 
	PRIMARY KEY (id);


ALTER TABLE model.enchantment_type ADD CONSTRAINT PK_enchantment_type 
	PRIMARY KEY (id);


ALTER TABLE i18n.enchantment_type_description ADD CONSTRAINT PK_enchantment_type_description 
	PRIMARY KEY (id);


ALTER TABLE data.item ADD CONSTRAINT PK_item 
	PRIMARY KEY (id);


ALTER TABLE model.item_enchantment ADD CONSTRAINT PK_enchantment_model 
	PRIMARY KEY (id);


ALTER TABLE model.item_model ADD CONSTRAINT PK_item_model 
	PRIMARY KEY (id);


ALTER TABLE i18n.item_model_description ADD CONSTRAINT PK_item_model_description 
	PRIMARY KEY (id);


ALTER TABLE i18n.language ADD CONSTRAINT PK_m_language 
	PRIMARY KEY (id);


ALTER TABLE data.minion ADD CONSTRAINT PK_minion 
	PRIMARY KEY (id);


ALTER TABLE model.minion_attribute ADD CONSTRAINT PK_minion_attribute 
	PRIMARY KEY (id);


ALTER TABLE model.minion_model ADD CONSTRAINT PK_minion_model 
	PRIMARY KEY (id);


ALTER TABLE model.minion_skill ADD CONSTRAINT PK_minion_skill 
	PRIMARY KEY (minion_model, skill);


ALTER TABLE model.minion_specialization ADD CONSTRAINT PK_hero_type 
	PRIMARY KEY (id);


ALTER TABLE i18n.minion_specialization_description ADD CONSTRAINT PK_minion_specialization_description 
	PRIMARY KEY (id);


ALTER TABLE model.minion_trait ADD CONSTRAINT PK_minion_trait 
	PRIMARY KEY (minion_model, trait);


ALTER TABLE model.mission ADD CONSTRAINT PK_mission 
	PRIMARY KEY (id);


ALTER TABLE i18n.mission_description ADD CONSTRAINT PK_mission_description 
	PRIMARY KEY (id);


ALTER TABLE model.mission_reward ADD CONSTRAINT PK_mission_reward 
	PRIMARY KEY (id);


ALTER TABLE data.player ADD CONSTRAINT PK_player 
	PRIMARY KEY (id);


ALTER TABLE data.seeker ADD CONSTRAINT PK_seeker 
	PRIMARY KEY (id);


ALTER TABLE model.seeker_model ADD CONSTRAINT PK_seeker_model 
	PRIMARY KEY (id);


ALTER TABLE model.seeker_specialization ADD CONSTRAINT PK_seeker_specialization 
	PRIMARY KEY (id);


ALTER TABLE i18n.seeker_specialization_description ADD CONSTRAINT PK_specialization_type_desc 
	PRIMARY KEY (id);


ALTER TABLE model.skill ADD CONSTRAINT PK_skill_model 
	PRIMARY KEY (id);


ALTER TABLE i18n.skill_description ADD CONSTRAINT PK_skill_model_descirption 
	PRIMARY KEY (id);


ALTER TABLE i18n.skill_name ADD CONSTRAINT PK_skill_name 
	PRIMARY KEY (id);


ALTER TABLE data.team ADD CONSTRAINT PK_team 
	PRIMARY KEY (id);


ALTER TABLE model.trait ADD CONSTRAINT PK_trait_model 
	PRIMARY KEY (id);


ALTER TABLE i18n.trait_description ADD CONSTRAINT PK_trait_model_description 
	PRIMARY KEY (id);


ALTER TABLE i18n.trait_name ADD CONSTRAINT PK_trait_name 
	PRIMARY KEY (id);


ALTER TABLE model.trait_target ADD CONSTRAINT PK_trait_target 
	PRIMARY KEY (id);




ALTER TABLE i18n.attribute_type_description ADD CONSTRAINT FK_attribute_type_description_attribute_type 
	FOREIGN KEY (attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE i18n.attribute_type_description ADD CONSTRAINT FK_attribute_type_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE model.enchantment_type ADD CONSTRAINT FK_enchantment_type_attribute_type 
	FOREIGN KEY (attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE model.enchantment_type ADD CONSTRAINT FK_enchantment_type_skill_model_02 
	FOREIGN KEY (skill_model) REFERENCES model.skill (id);

ALTER TABLE i18n.enchantment_type_description ADD CONSTRAINT FK_enchantment_type_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.enchantment_type_description ADD CONSTRAINT FK_enchantment_type_description_enchantment_type 
	FOREIGN KEY (enchantment_type) REFERENCES model.enchantment_type (id);

ALTER TABLE data.item ADD CONSTRAINT FK_item_item_model 
	FOREIGN KEY (item_model) REFERENCES model.item_model (id);

ALTER TABLE data.item ADD CONSTRAINT FK_item_team 
	FOREIGN KEY (team) REFERENCES data.team (id);

ALTER TABLE data.item ADD CONSTRAINT FK_item_player 
	FOREIGN KEY (owner) REFERENCES data.player (id);

ALTER TABLE model.item_enchantment ADD CONSTRAINT FK_enchantment_model_enchantment_type 
	FOREIGN KEY (enchantment_type) REFERENCES model.enchantment_type (id);

ALTER TABLE model.item_enchantment ADD CONSTRAINT FK_item_enchantment_item_model 
	FOREIGN KEY (item_model) REFERENCES model.item_model (id);

ALTER TABLE model.item_model ADD CONSTRAINT FK_item_model_attribute_type 
	FOREIGN KEY (attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE i18n.item_model_description ADD CONSTRAINT FK_item_model_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.item_model_description ADD CONSTRAINT FK_item_model_description_item_model 
	FOREIGN KEY (item_model) REFERENCES model.item_model (id);

ALTER TABLE data.minion ADD CONSTRAINT FK_minion_player 
	FOREIGN KEY (owner) REFERENCES data.player (id);

ALTER TABLE data.minion ADD CONSTRAINT FK_minion_minion_model_02 
	FOREIGN KEY (minion_model) REFERENCES model.minion_model (id)
ON UPDATE NO ACTION;

ALTER TABLE model.minion_attribute ADD CONSTRAINT FK_minion_attribute_attribute_type 
	FOREIGN KEY (type) REFERENCES model.attribute_type (id);

ALTER TABLE model.minion_attribute ADD CONSTRAINT FK_minion_attribute_minion_model 
	FOREIGN KEY (minion_model) REFERENCES model.minion_model (id);

ALTER TABLE model.minion_model ADD CONSTRAINT FK_minion_model_minion_specialization 
	FOREIGN KEY (specialization) REFERENCES model.minion_specialization (id)
ON UPDATE NO ACTION;

ALTER TABLE model.minion_skill ADD CONSTRAINT FK_minion_skill_minion_model 
	FOREIGN KEY (minion_model) REFERENCES model.minion_model (id);

ALTER TABLE model.minion_skill ADD CONSTRAINT FK_minion_skill_skill_model 
	FOREIGN KEY (skill) REFERENCES model.skill (id);

ALTER TABLE i18n.minion_specialization_description ADD CONSTRAINT FK_minion_specialization_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id)
ON UPDATE NO ACTION;

ALTER TABLE i18n.minion_specialization_description ADD CONSTRAINT FK_minion_specialization_description_specialization 
	FOREIGN KEY (specialization) REFERENCES model.minion_specialization (id)
ON UPDATE NO ACTION;

ALTER TABLE model.minion_trait ADD CONSTRAINT FK_minion_trait_trait_model 
	FOREIGN KEY (trait) REFERENCES model.trait (id);

ALTER TABLE model.minion_trait ADD CONSTRAINT FK_minion_trait_minion_model 
	FOREIGN KEY (minion_model) REFERENCES model.minion_model (id);

ALTER TABLE model.mission ADD CONSTRAINT FK_mission_team 
	FOREIGN KEY (team) REFERENCES data.team (id);

ALTER TABLE i18n.mission_description ADD CONSTRAINT FK_mission_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.mission_description ADD CONSTRAINT FK_mission_description_mission 
	FOREIGN KEY (mission) REFERENCES model.mission (id);

ALTER TABLE model.mission_reward ADD CONSTRAINT FK_mission_reward_seeker_model 
	FOREIGN KEY (seeker) REFERENCES model.seeker_model (id);

ALTER TABLE model.mission_reward ADD CONSTRAINT FK_mission_reward_minion_model 
	FOREIGN KEY (minion) REFERENCES model.minion_model (id);

ALTER TABLE model.mission_reward ADD CONSTRAINT FK_mission_reward_item_model 
	FOREIGN KEY (item) REFERENCES model.item_model (id);

ALTER TABLE model.mission_reward ADD CONSTRAINT FK_mission_reward_mission 
	FOREIGN KEY (mission) REFERENCES model.mission (id);

ALTER TABLE data.seeker ADD CONSTRAINT FK_seeker_seeker_model 
	FOREIGN KEY (seeker_model) REFERENCES model.seeker_model (id);

ALTER TABLE data.seeker ADD CONSTRAINT FK_seeker_player 
	FOREIGN KEY (owner) REFERENCES data.player (id);

ALTER TABLE model.seeker_specialization ADD CONSTRAINT FK_seeker_specialization_seeker_model 
	FOREIGN KEY (seeker_model) REFERENCES model.seeker_model (id);

ALTER TABLE i18n.seeker_specialization_description ADD CONSTRAINT FK_specialization_type_desc_l_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.seeker_specialization_description ADD CONSTRAINT FK_specialization_desc_seeker_specialization 
	FOREIGN KEY (seeker_specialization) REFERENCES model.seeker_specialization (id);

ALTER TABLE model.skill ADD CONSTRAINT FK_skill_model_attribute_type 
	FOREIGN KEY (attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE i18n.skill_description ADD CONSTRAINT FK_skill_model_descirption_skill_model 
	FOREIGN KEY (skill) REFERENCES model.skill (id);

ALTER TABLE i18n.skill_description ADD CONSTRAINT FK_skill_model_descirption_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.skill_name ADD CONSTRAINT FK_skill_name_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.skill_name ADD CONSTRAINT FK_skill_name_skill 
	FOREIGN KEY (skill) REFERENCES model.skill (id);

ALTER TABLE data.team ADD CONSTRAINT FK_team_minion_04 
	FOREIGN KEY (leader) REFERENCES data.minion (id)
ON UPDATE NO ACTION;

ALTER TABLE data.team ADD CONSTRAINT FK_team_minion 
	FOREIGN KEY (minion_top) REFERENCES data.minion (id);

ALTER TABLE data.team ADD CONSTRAINT FK_team_minion_02 
	FOREIGN KEY (minion_mid) REFERENCES data.minion (id);

ALTER TABLE data.team ADD CONSTRAINT FK_team_minion_03 
	FOREIGN KEY (minion_bot) REFERENCES data.minion (id);

ALTER TABLE data.team ADD CONSTRAINT FK_team_player 
	FOREIGN KEY (owner) REFERENCES data.player (id);

ALTER TABLE model.trait ADD CONSTRAINT FK_trait_model_attribute_type 
	FOREIGN KEY (affected_attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE i18n.trait_description ADD CONSTRAINT FK_trait_model_description_trait_model 
	FOREIGN KEY (trait) REFERENCES model.trait (id);

ALTER TABLE i18n.trait_description ADD CONSTRAINT FK_trait_model_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.trait_name ADD CONSTRAINT FK_trait_name_trait 
	FOREIGN KEY (trait) REFERENCES model.trait (id);

ALTER TABLE i18n.trait_name ADD CONSTRAINT FK_trait_name_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE model.trait_target ADD CONSTRAINT FK_trait_target_trait 
	FOREIGN KEY (trait) REFERENCES model.trait (id);
