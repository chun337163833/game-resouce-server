DROP TABLE IF EXISTS model.attribute CASCADE;
DROP SEQUENCE IF EXISTS model.attribute_id_seq;
DROP TABLE IF EXISTS model.attribute_type CASCADE;
DROP TABLE IF EXISTS i18n.attribute_type_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.attribute_type_description_id_seq;
DROP TABLE IF EXISTS model.enchantment_type CASCADE;
DROP SEQUENCE IF EXISTS model.enchantment_type_id_seq;
DROP TABLE IF EXISTS i18n.enchantment_type_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.enchantment_type_description_id_seq;
DROP TABLE IF EXISTS data.hero CASCADE;
DROP SEQUENCE IF EXISTS data.hero_id_seq;
DROP TABLE IF EXISTS model.hero_model CASCADE;
DROP SEQUENCE IF EXISTS model.hero_model_id_seq;
DROP TABLE IF EXISTS model.hero_skill CASCADE;
DROP TABLE IF EXISTS model.hero_trait CASCADE;
DROP TABLE IF EXISTS model.hero_type CASCADE;
DROP SEQUENCE IF EXISTS model.hero_type_id_seq;
DROP TABLE IF EXISTS i18n.hero_type_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.hero_type_description_id_seq;
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
DROP TABLE IF EXISTS model.minion_model CASCADE;
DROP SEQUENCE IF EXISTS model.minion_model_id_seq;
DROP TABLE IF EXISTS model.minion_skill CASCADE;
DROP TABLE IF EXISTS model.minion_trait CASCADE;
DROP TABLE IF EXISTS model.mission CASCADE;
DROP SEQUENCE IF EXISTS model.mission_id_seq;
DROP TABLE IF EXISTS i18n.mission_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.mission_description_id_seq;
DROP TABLE IF EXISTS model.mission_reward CASCADE;
DROP SEQUENCE IF EXISTS model.mission_reward_id_seq;
DROP TABLE IF EXISTS model.quality_grade CASCADE;
DROP TABLE IF EXISTS model.seeker_model CASCADE;
DROP SEQUENCE IF EXISTS model.seeker_model_id_seq;
DROP TABLE IF EXISTS model.seeker_specialization CASCADE;
DROP SEQUENCE IF EXISTS model.seeker_specialization_id_seq;
DROP TABLE IF EXISTS model.skill CASCADE;
DROP SEQUENCE IF EXISTS model.skill_id_seq;
DROP TABLE IF EXISTS i18n.skill_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.skill_description_id_seq;
DROP TABLE IF EXISTS i18n.specialization_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.specialization_description_id_seq;
DROP TABLE IF EXISTS data.team CASCADE;
DROP TABLE IF EXISTS model.trait CASCADE;
DROP SEQUENCE IF EXISTS model.trait_id_seq;
DROP TABLE IF EXISTS i18n.trait_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.trait_description_id_seq;
DROP TABLE IF EXISTS model.trait_target CASCADE;
DROP SEQUENCE IF EXISTS model.trait_target_id_seq;

CREATE SEQUENCE model.attribute_id_seq INCREMENT 1 START 1;

CREATE TABLE model.attribute ( 
	id bigint DEFAULT nextval(('model.attribute_id_seq'::text)::regclass) NOT NULL,
	type varchar(50) NOT NULL,
	value decimal(10,3) NOT NULL
);

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

CREATE SEQUENCE data.hero_id_seq INCREMENT 1 START 1;

CREATE TABLE data.hero ( 
	id bigint DEFAULT nextval(('data.hero_id_seq'::text)::regclass) NOT NULL,
	hero_model bigint NOT NULL,
	team bigint,
	owner bigint,
	level integer DEFAULT 1 NOT NULL
);

CREATE SEQUENCE model.hero_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.hero_model ( 
	id bigint DEFAULT nextval(('model.hero_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	quality_grade char(1) NOT NULL,
	attributes bigint NOT NULL,
	hero_type bigint NOT NULL,
	image_bundle_name varchar(50) NOT NULL
);

CREATE TABLE model.hero_skill ( 
	hero_model bigint NOT NULL,
	skill bigint NOT NULL,
	required_level integer NOT NULL,
	override_power decimal(10,3) DEFAULT 0
);

CREATE TABLE model.hero_trait ( 
	hero_model bigint NOT NULL,
	trait bigint NOT NULL,
	required_level integer DEFAULT 1,
	override_power decimal(10,3)
);

CREATE SEQUENCE model.hero_type_id_seq INCREMENT 1 START 1;

CREATE TABLE model.hero_type ( 
	id bigint DEFAULT nextval(('model.hero_type_id_seq'::text)::regclass) NOT NULL,
	code varchar(50) NOT NULL
);

CREATE SEQUENCE i18n.hero_type_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.hero_type_description ( 
	id bigint DEFAULT nextval(('i18n.hero_type_description_id_seq'::text)::regclass) NOT NULL,
	hero_type bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE data.item_id_seq INCREMENT 1 START 1;

CREATE TABLE data.item ( 
	id bigint DEFAULT nextval(('data.item_id_seq'::text)::regclass) NOT NULL,
	item_model bigint NOT NULL,
	team bigint,
	owner bigint,
	enchant integer
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
	quality_grade char(1) NOT NULL,
	value decimal(10,5) DEFAULT 0 NOT NULL,
	max_enchant integer DEFAULT 10,
	type varchar(10) NOT NULL,
	icon_name varchar(50) NOT NULL
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
	team bigint,
	owner bigint,
	position varchar(50) NOT NULL,
	level integer DEFAULT 1 NOT NULL
);

CREATE SEQUENCE model.minion_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.minion_model ( 
	id bigint DEFAULT nextval(('model.minion_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	quality_grade char(1) NOT NULL,
	attributes bigint NOT NULL,
	image_bundle_name varchar(50) NOT NULL
);

CREATE TABLE model.minion_skill ( 
	minion_model bigint NOT NULL,
	skill bigint NOT NULL,
	required_level integer NOT NULL,
	override_power decimal(10,3) DEFAULT 0
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
	mission bigint NOT NULL
);

CREATE TABLE model.quality_grade ( 
	id char(1) NOT NULL
);

CREATE SEQUENCE model.seeker_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.seeker_model ( 
	id bigint DEFAULT nextval(('model.seeker_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	price integer NOT NULL,
	quality_grade char(1) NOT NULL,
	image_bundle_name varchar(50) NOT NULL
);

CREATE SEQUENCE model.seeker_specialization_id_seq INCREMENT 1 START 1;

CREATE TABLE model.seeker_specialization ( 
	id bigint DEFAULT nextval(('model.seeker_specialization_id_seq'::text)::regclass) NOT NULL,
	seeker_model bigint NOT NULL,
	type varchar(10) NOT NULL
);

CREATE SEQUENCE model.skill_id_seq INCREMENT 1 START 1;

CREATE TABLE model.skill ( 
	id bigint DEFAULT nextval(('model.skill_id_seq'::text)::regclass) NOT NULL,
	alg varchar(50) NOT NULL,
	attribute_type varchar(50),
	power decimal(10,3),
	icon_name varchar(50) NOT NULL
);

CREATE SEQUENCE i18n.skill_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.skill_description ( 
	id bigint DEFAULT nextval(('i18n.skill_description_id_seq'::text)::regclass) NOT NULL,
	skill bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE SEQUENCE i18n.specialization_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.specialization_description ( 
	id bigint DEFAULT nextval(('i18n.specialization_description_id_seq'::text)::regclass) NOT NULL,
	seeker_specialization bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE TABLE data.team ( 
	id bigint NOT NULL,
	owner bigint,
	hero bigint NOT NULL
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

CREATE SEQUENCE model.trait_target_id_seq INCREMENT 1 START 1;

CREATE TABLE model.trait_target ( 
	id bigint DEFAULT nextval(('model.trait_target_id_seq'::text)::regclass) NOT NULL,
	trait bigint NOT NULL,
	position varchar(50) NOT NULL
);


CREATE INDEX IXFK_attributes_attribute_type
	ON model.attribute (type);
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
CREATE INDEX IXFK_hero_hero_model
	ON data.hero (hero_model);
CREATE INDEX IXFK_hero_team
	ON data.hero (team);
CREATE INDEX IXFK_hero_model_attributes
	ON model.hero_model (attributes);
CREATE INDEX IXFK_hero_model_hero_type
	ON model.hero_model (hero_type);
CREATE INDEX IXFK_hero_skill_hero_model
	ON model.hero_skill (hero_model);
CREATE INDEX IXFK_hero_skill_skill_model
	ON model.hero_skill (skill);
CREATE INDEX IXFK_hero_trait_trait_model
	ON model.hero_trait (trait);
CREATE INDEX IXFK_hero_trait_hero_model
	ON model.hero_trait (hero_model);
ALTER TABLE model.hero_type
	ADD CONSTRAINT UQ_hero_type_code UNIQUE (code);
CREATE INDEX IXFK_hero_type_description_hero_type
	ON i18n.hero_type_description (hero_type);
CREATE INDEX IXFK_hero_type_description_language
	ON i18n.hero_type_description (lang);
CREATE INDEX IXFK_item_item_model
	ON data.item (item_model);
CREATE INDEX IXFK_item_team
	ON data.item (team);
CREATE INDEX IXFK_enchantment_model_enchantment_type
	ON model.item_enchantment (enchantment_type);
CREATE INDEX IXFK_item_enchantment_item_model
	ON model.item_enchantment (item_model);
CREATE INDEX IXFK_item_model_quality_grade
	ON model.item_model (quality_grade);
CREATE INDEX IXFK_item_model_attribute_type
	ON model.item_model (attribute_type);
CREATE INDEX IXFK_item_model_description_language
	ON i18n.item_model_description (lang);
CREATE INDEX IXFK_item_model_description_item_model
	ON i18n.item_model_description (item_model);
CREATE INDEX IXFK_minion_minion_model
	ON data.minion (minion_model);
CREATE INDEX IXFK_minion_team
	ON data.minion (team);
CREATE INDEX IXFK_minion_model_attributes
	ON model.minion_model (attributes);
CREATE INDEX IXFK_minion_model_grade
	ON model.minion_model (quality_grade);
CREATE INDEX IXFK_minion_skill_minion_model
	ON model.minion_skill (minion_model);
CREATE INDEX IXFK_minion_skill_skill_model
	ON model.minion_skill (skill);
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
CREATE INDEX IXFK_mission_reward_mission
	ON model.mission_reward (mission);
CREATE INDEX IXFK_seeker_model_grade
	ON model.seeker_model (quality_grade);
CREATE INDEX IXFK_seeker_specialization_specialization_type
	ON model.seeker_specialization (type);
CREATE INDEX IXFK_seeker_specialization_seeker_model
	ON model.seeker_specialization (seeker_model);
CREATE INDEX IXFK_skill_model_attribute_type
	ON model.skill (attribute_type);
CREATE INDEX IXFK_skill_model_descirption_skill_model
	ON i18n.skill_description (skill);
CREATE INDEX IXFK_skill_model_descirption_language
	ON i18n.skill_description (lang);
CREATE INDEX IXFK_specialization_type_desc_specialization_type
	ON i18n.specialization_description (seeker_specialization);
CREATE INDEX IXFK_specialization_type_desc_l_language
	ON i18n.specialization_description (lang);
CREATE INDEX IXFK_trait_model_attribute_type
	ON model.trait (affected_attribute_type);
CREATE INDEX IXFK_trait_model_description_trait_model
	ON i18n.trait_description (trait);
CREATE INDEX IXFK_trait_model_description_language
	ON i18n.trait_description (lang);
CREATE INDEX IXFK_trait_target_trait
	ON model.trait_target (trait);
ALTER TABLE model.attribute ADD CONSTRAINT PK_attributes 
	PRIMARY KEY (id);


ALTER TABLE model.attribute_type ADD CONSTRAINT PK_attribute_type 
	PRIMARY KEY (id);


ALTER TABLE i18n.attribute_type_description ADD CONSTRAINT PK_attribute_type_description 
	PRIMARY KEY (id);


ALTER TABLE model.enchantment_type ADD CONSTRAINT PK_enchantment_type 
	PRIMARY KEY (id);


ALTER TABLE i18n.enchantment_type_description ADD CONSTRAINT PK_enchantment_type_description 
	PRIMARY KEY (id);


ALTER TABLE data.hero ADD CONSTRAINT PK_hero 
	PRIMARY KEY (id);


ALTER TABLE model.hero_model ADD CONSTRAINT PK_hero_model 
	PRIMARY KEY (id);


ALTER TABLE model.hero_type ADD CONSTRAINT PK_hero_type 
	PRIMARY KEY (id);


ALTER TABLE i18n.hero_type_description ADD CONSTRAINT PK_hero_type_description 
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


ALTER TABLE model.minion_model ADD CONSTRAINT PK_minion_model 
	PRIMARY KEY (id);


ALTER TABLE model.mission ADD CONSTRAINT PK_mission 
	PRIMARY KEY (id);


ALTER TABLE i18n.mission_description ADD CONSTRAINT PK_mission_description 
	PRIMARY KEY (id);


ALTER TABLE model.mission_reward ADD CONSTRAINT PK_mission_reward 
	PRIMARY KEY (id);


ALTER TABLE model.quality_grade ADD CONSTRAINT PK_grade 
	PRIMARY KEY (id);


ALTER TABLE model.seeker_model ADD CONSTRAINT PK_seeker_model 
	PRIMARY KEY (id);


ALTER TABLE model.seeker_specialization ADD CONSTRAINT PK_seeker_specialization 
	PRIMARY KEY (id);


ALTER TABLE model.skill ADD CONSTRAINT PK_skill_model 
	PRIMARY KEY (id);


ALTER TABLE i18n.skill_description ADD CONSTRAINT PK_skill_model_descirption 
	PRIMARY KEY (id);


ALTER TABLE i18n.specialization_description ADD CONSTRAINT PK_specialization_type_desc 
	PRIMARY KEY (id);


ALTER TABLE data.team ADD CONSTRAINT PK_team 
	PRIMARY KEY (id);


ALTER TABLE model.trait ADD CONSTRAINT PK_trait_model 
	PRIMARY KEY (id);


ALTER TABLE i18n.trait_description ADD CONSTRAINT PK_trait_model_description 
	PRIMARY KEY (id);


ALTER TABLE model.trait_target ADD CONSTRAINT PK_trait_target 
	PRIMARY KEY (id);




ALTER TABLE model.attribute ADD CONSTRAINT FK_attributes_attribute_type 
	FOREIGN KEY (type) REFERENCES model.attribute_type (id);

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

ALTER TABLE data.hero ADD CONSTRAINT FK_hero_hero_model 
	FOREIGN KEY (hero_model) REFERENCES model.hero_model (id);

ALTER TABLE data.hero ADD CONSTRAINT FK_hero_team 
	FOREIGN KEY (team) REFERENCES data.team (id);

ALTER TABLE model.hero_model ADD CONSTRAINT FK_hero_model_grade 
	FOREIGN KEY (quality_grade) REFERENCES model.quality_grade (id);

ALTER TABLE model.hero_model ADD CONSTRAINT FK_hero_model_attributes 
	FOREIGN KEY (attributes) REFERENCES model.attribute (id);

ALTER TABLE model.hero_model ADD CONSTRAINT FK_hero_model_hero_type 
	FOREIGN KEY (hero_type) REFERENCES model.hero_type (id);

ALTER TABLE model.hero_skill ADD CONSTRAINT FK_hero_skill_hero_model 
	FOREIGN KEY (hero_model) REFERENCES model.hero_model (id);

ALTER TABLE model.hero_skill ADD CONSTRAINT FK_hero_skill_skill_model 
	FOREIGN KEY (skill) REFERENCES model.skill (id);

ALTER TABLE model.hero_trait ADD CONSTRAINT FK_hero_trait_trait_model 
	FOREIGN KEY (trait) REFERENCES model.trait (id);

ALTER TABLE model.hero_trait ADD CONSTRAINT FK_hero_trait_hero_model 
	FOREIGN KEY (hero_model) REFERENCES model.hero_model (id);

ALTER TABLE i18n.hero_type_description ADD CONSTRAINT FK_hero_type_description_hero_type 
	FOREIGN KEY (hero_type) REFERENCES model.hero_type (id);

ALTER TABLE i18n.hero_type_description ADD CONSTRAINT FK_hero_type_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE data.item ADD CONSTRAINT FK_item_item_model 
	FOREIGN KEY (item_model) REFERENCES model.item_model (id);

ALTER TABLE data.item ADD CONSTRAINT FK_item_team 
	FOREIGN KEY (team) REFERENCES data.team (id);

ALTER TABLE model.item_enchantment ADD CONSTRAINT FK_enchantment_model_enchantment_type 
	FOREIGN KEY (enchantment_type) REFERENCES model.enchantment_type (id);

ALTER TABLE model.item_enchantment ADD CONSTRAINT FK_item_enchantment_item_model 
	FOREIGN KEY (item_model) REFERENCES model.item_model (id);

ALTER TABLE model.item_model ADD CONSTRAINT FK_item_model_attribute_type 
	FOREIGN KEY (attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE model.item_model ADD CONSTRAINT FK_item_model_quality_grade 
	FOREIGN KEY (quality_grade) REFERENCES model.quality_grade (id);

ALTER TABLE i18n.item_model_description ADD CONSTRAINT FK_item_model_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.item_model_description ADD CONSTRAINT FK_item_model_description_item_model 
	FOREIGN KEY (item_model) REFERENCES model.item_model (id);

ALTER TABLE data.minion ADD CONSTRAINT FK_minion_minion_model 
	FOREIGN KEY (minion_model) REFERENCES model.minion_model (id);

ALTER TABLE data.minion ADD CONSTRAINT FK_minion_team 
	FOREIGN KEY (team) REFERENCES data.team (id);

ALTER TABLE model.minion_model ADD CONSTRAINT FK_minion_model_attributes 
	FOREIGN KEY (attributes) REFERENCES model.attribute (id);

ALTER TABLE model.minion_model ADD CONSTRAINT FK_minion_model_grade 
	FOREIGN KEY (quality_grade) REFERENCES model.quality_grade (id);

ALTER TABLE model.minion_skill ADD CONSTRAINT FK_minion_skill_minion_model 
	FOREIGN KEY (minion_model) REFERENCES model.minion_model (id);

ALTER TABLE model.minion_skill ADD CONSTRAINT FK_minion_skill_skill_model 
	FOREIGN KEY (skill) REFERENCES model.skill (id);

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

ALTER TABLE model.mission_reward ADD CONSTRAINT FK_mission_reward_mission 
	FOREIGN KEY (mission) REFERENCES model.mission (id);

ALTER TABLE model.seeker_model ADD CONSTRAINT FK_seeker_model_grade 
	FOREIGN KEY (quality_grade) REFERENCES model.quality_grade (id);

ALTER TABLE model.seeker_specialization ADD CONSTRAINT FK_seeker_specialization_seeker_model 
	FOREIGN KEY (seeker_model) REFERENCES model.seeker_model (id);

ALTER TABLE model.skill ADD CONSTRAINT FK_skill_model_attribute_type 
	FOREIGN KEY (attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE i18n.skill_description ADD CONSTRAINT FK_skill_model_descirption_skill_model 
	FOREIGN KEY (skill) REFERENCES model.skill (id);

ALTER TABLE i18n.skill_description ADD CONSTRAINT FK_skill_model_descirption_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.specialization_description ADD CONSTRAINT FK_specialization_type_desc_l_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.specialization_description ADD CONSTRAINT FK_specialization_desc_seeker_specialization 
	FOREIGN KEY (seeker_specialization) REFERENCES model.seeker_specialization (id);

ALTER TABLE model.trait ADD CONSTRAINT FK_trait_model_attribute_type 
	FOREIGN KEY (affected_attribute_type) REFERENCES model.attribute_type (id);

ALTER TABLE i18n.trait_description ADD CONSTRAINT FK_trait_model_description_trait_model 
	FOREIGN KEY (trait) REFERENCES model.trait (id);

ALTER TABLE i18n.trait_description ADD CONSTRAINT FK_trait_model_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE model.trait_target ADD CONSTRAINT FK_trait_target_trait 
	FOREIGN KEY (trait) REFERENCES model.trait (id);
