DROP TABLE IF EXISTS model.attributes CASCADE;
DROP SEQUENCE IF EXISTS model.attributes_id_seq;
DROP TABLE IF EXISTS model.grade CASCADE;
DROP TABLE IF EXISTS model.hero_model CASCADE;
DROP SEQUENCE IF EXISTS model.hero_model_id_seq;
DROP TABLE IF EXISTS model.hero_type CASCADE;
DROP SEQUENCE IF EXISTS model.hero_type_id_seq;
DROP TABLE IF EXISTS i18n.hero_type_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.hero_type_description_id_seq;
DROP TABLE IF EXISTS i18n.language CASCADE;
DROP TABLE IF EXISTS model.minion_model CASCADE;
DROP SEQUENCE IF EXISTS model.minion_model_id_seq;
DROP TABLE IF EXISTS model.seeker_model CASCADE;
DROP SEQUENCE IF EXISTS model.seeker_model_id_seq;
DROP TABLE IF EXISTS model.seeker_specialization CASCADE;
DROP SEQUENCE IF EXISTS model.seeker_specialization_id_seq;
DROP TABLE IF EXISTS i18n.specialization_desc CASCADE;
DROP SEQUENCE IF EXISTS i18n.specialization_desc_id_seq;

CREATE SEQUENCE model.attributes_id_seq INCREMENT 1 START 1;

CREATE TABLE model.attributes ( 
	id bigint DEFAULT nextval(('model.attributes_id_seq'::text)::regclass) NOT NULL,
	health decimal(10,3) DEFAULT 0 NOT NULL
);

CREATE TABLE model.grade ( 
	id char(1) NOT NULL
);

CREATE SEQUENCE model.hero_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.hero_model ( 
	id bigint DEFAULT nextval(('model.hero_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	grade_id char(1) NOT NULL,
	attribute_id bigint NOT NULL,
	hero_type bigint NOT NULL
);

CREATE SEQUENCE model.hero_type_id_seq INCREMENT 1 START 1;

CREATE TABLE model.hero_type ( 
	id bigint DEFAULT nextval(('model.hero_type_id_seq'::text)::regclass) NOT NULL,
	code varchar(50) NOT NULL
);

CREATE SEQUENCE i18n.hero_type_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.hero_type_description ( 
	id bigint DEFAULT nextval(('i18n.hero_type_description_id_seq'::text)::regclass) NOT NULL,
	hero_type_id bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE TABLE i18n.language ( 
	id char(2) NOT NULL,
	description varchar(50) NOT NULL
);

CREATE SEQUENCE model.minion_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.minion_model ( 
	id bigint DEFAULT nextval(('model.minion_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	grade_id char(1) NOT NULL,
	attribute_id bigint NOT NULL
);

CREATE SEQUENCE model.seeker_model_id_seq INCREMENT 1 START 1;

CREATE TABLE model.seeker_model ( 
	id bigint DEFAULT nextval(('model.seeker_model_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	price integer NOT NULL,
	grade_id char(1) NOT NULL
);

CREATE SEQUENCE model.seeker_specialization_id_seq INCREMENT 1 START 1;

CREATE TABLE model.seeker_specialization ( 
	id bigint DEFAULT nextval(('model.seeker_specialization_id_seq'::text)::regclass) NOT NULL,
	seeker_id bigint NOT NULL,
	type varchar(10) NOT NULL
);

CREATE SEQUENCE i18n.specialization_desc_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.specialization_desc ( 
	id bigint DEFAULT nextval(('i18n.specialization_desc_id_seq'::text)::regclass) NOT NULL,
	specialization_id bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);


CREATE INDEX IXFK_hero_model_attributes
	ON model.hero_model (attribute_id);
CREATE INDEX IXFK_hero_model_hero_type
	ON model.hero_model (hero_type);
ALTER TABLE model.hero_type
	ADD CONSTRAINT UQ_hero_type_code UNIQUE (code);
CREATE INDEX IXFK_hero_type_description_language
	ON i18n.hero_type_description (lang);
CREATE INDEX IXFK_minion_model_attributes
	ON model.minion_model (attribute_id);
CREATE INDEX IXFK_minion_model_grade
	ON model.minion_model (grade_id);
CREATE INDEX IXFK_seeker_model_grade
	ON model.seeker_model (grade_id);
CREATE INDEX IXFK_seeker_specialization_specialization_type
	ON model.seeker_specialization (type);
CREATE INDEX IXFK_seeker_specialization_seeker_model
	ON model.seeker_specialization (seeker_id);
CREATE INDEX IXFK_specialization_type_desc_specialization_type
	ON i18n.specialization_desc (specialization_id);
CREATE INDEX IXFK_specialization_type_desc_l_language
	ON i18n.specialization_desc (lang);
ALTER TABLE model.attributes ADD CONSTRAINT PK_attributes 
	PRIMARY KEY (id);


ALTER TABLE model.grade ADD CONSTRAINT PK_grade 
	PRIMARY KEY (id);


ALTER TABLE model.hero_model ADD CONSTRAINT PK_hero_model 
	PRIMARY KEY (id);


ALTER TABLE model.hero_type ADD CONSTRAINT PK_hero_type 
	PRIMARY KEY (id);


ALTER TABLE i18n.hero_type_description ADD CONSTRAINT PK_hero_type_description 
	PRIMARY KEY (id);


ALTER TABLE i18n.language ADD CONSTRAINT PK_m_language 
	PRIMARY KEY (id);


ALTER TABLE model.minion_model ADD CONSTRAINT PK_minion_model 
	PRIMARY KEY (id);


ALTER TABLE model.seeker_model ADD CONSTRAINT PK_seeker_model 
	PRIMARY KEY (id);


ALTER TABLE model.seeker_specialization ADD CONSTRAINT PK_seeker_specialization 
	PRIMARY KEY (id);


ALTER TABLE i18n.specialization_desc ADD CONSTRAINT PK_specialization_type_desc 
	PRIMARY KEY (id);




ALTER TABLE model.hero_model ADD CONSTRAINT FK_hero_model_grade 
	FOREIGN KEY (grade_id) REFERENCES model.grade (id);

ALTER TABLE model.hero_model ADD CONSTRAINT FK_hero_model_attributes 
	FOREIGN KEY (attribute_id) REFERENCES model.attributes (id);

ALTER TABLE model.hero_model ADD CONSTRAINT FK_hero_model_hero_type 
	FOREIGN KEY (hero_type) REFERENCES model.hero_type (id);

ALTER TABLE i18n.hero_type_description ADD CONSTRAINT FK_hero_type_description_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE model.minion_model ADD CONSTRAINT FK_minion_model_attributes 
	FOREIGN KEY (attribute_id) REFERENCES model.attributes (id);

ALTER TABLE model.minion_model ADD CONSTRAINT FK_minion_model_grade 
	FOREIGN KEY (grade_id) REFERENCES model.grade (id);

ALTER TABLE model.seeker_model ADD CONSTRAINT FK_seeker_model_grade 
	FOREIGN KEY (grade_id) REFERENCES model.grade (id);

ALTER TABLE model.seeker_specialization ADD CONSTRAINT FK_seeker_specialization_seeker_model 
	FOREIGN KEY (seeker_id) REFERENCES model.seeker_model (id);

ALTER TABLE i18n.specialization_desc ADD CONSTRAINT FK_specialization_type_desc_l_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.specialization_desc ADD CONSTRAINT FK_specialization_desc_seeker_specialization 
	FOREIGN KEY (specialization_id) REFERENCES model.seeker_specialization (id);
