DROP TABLE IF EXISTS i18n.l_hero_type_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.l_hero_type_description_id_seq;
DROP TABLE IF EXISTS i18n.l_language CASCADE;
DROP TABLE IF EXISTS model.m_char_attribute CASCADE;
DROP SEQUENCE IF EXISTS model.m_char_attribute_id_seq;
DROP TABLE IF EXISTS model.m_grade CASCADE;
DROP TABLE IF EXISTS model.m_hero CASCADE;
DROP SEQUENCE IF EXISTS model.m_hero_id_seq;
DROP TABLE IF EXISTS model.m_hero_type CASCADE;
DROP SEQUENCE IF EXISTS model.m_hero_type_id_seq;
DROP TABLE IF EXISTS model.m_minion CASCADE;
DROP SEQUENCE IF EXISTS model.m_minion_id_seq;
DROP TABLE IF EXISTS model.m_seeker CASCADE;
DROP SEQUENCE IF EXISTS model.m_seeker_id_seq;
DROP TABLE IF EXISTS model.m_seeker_specialization CASCADE;
DROP SEQUENCE IF EXISTS model.m_seeker_specialization_id_seq;
DROP TABLE IF EXISTS model.m_specialization_type CASCADE;
DROP SEQUENCE IF EXISTS model.m_specialization_type_id_seq;

CREATE SEQUENCE i18n.l_hero_type_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.l_hero_type_description ( 
	id bigint DEFAULT nextval(('i18n.l_hero_type_description_id_seq'::text)::regclass) NOT NULL,
	m_hero_type_id bigint NOT NULL,
	value text NOT NULL,
	lang char(2) NOT NULL
);

CREATE TABLE i18n.l_language ( 
	id char(2) NOT NULL,
	description varchar(50) NOT NULL
);

CREATE SEQUENCE model.m_char_attribute_id_seq INCREMENT 1 START 1;

CREATE TABLE model.m_char_attribute ( 
	id bigint DEFAULT nextval(('model.m_char_attribute_id_seq'::text)::regclass) NOT NULL,
	health decimal(10,3) DEFAULT 0 NOT NULL
);

CREATE TABLE model.m_grade ( 
	id char(1) NOT NULL
);

CREATE SEQUENCE model.m_hero_id_seq INCREMENT 1 START 1;

CREATE TABLE model.m_hero ( 
	id bigint DEFAULT nextval(('model.m_hero_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	m_grade_id char(1) NOT NULL,
	m_attribute_id bigint NOT NULL,
	m_hero_type bigint NOT NULL
);

CREATE SEQUENCE model.m_hero_type_id_seq INCREMENT 1 START 1;

CREATE TABLE model.m_hero_type ( 
	id bigint DEFAULT nextval(('model.m_hero_type_id_seq'::text)::regclass) NOT NULL,
	code varchar(50) NOT NULL
);

CREATE SEQUENCE model.m_minion_id_seq INCREMENT 1 START 1;

CREATE TABLE model.m_minion ( 
	id bigint DEFAULT nextval(('model.m_minion_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	m_grade_id char(1) NOT NULL,
	m_attribute_id bigint NOT NULL
);

CREATE SEQUENCE model.m_seeker_id_seq INCREMENT 1 START 1;

CREATE TABLE model.m_seeker ( 
	id bigint DEFAULT nextval(('model.m_seeker_id_seq'::text)::regclass) NOT NULL,
	name varchar(50) NOT NULL,
	price integer NOT NULL,
	m_grade_id char(1) NOT NULL
);

CREATE SEQUENCE model.m_seeker_specialization_id_seq INCREMENT 1 START 1;

CREATE TABLE model.m_seeker_specialization ( 
	id bigint DEFAULT nextval(('model.m_seeker_specialization_id_seq'::text)::regclass) NOT NULL,
	m_seeker_id bigint NOT NULL,
	m_specialization_type_id bigint NOT NULL
);

CREATE SEQUENCE model.m_specialization_type_id_seq INCREMENT 1 START 1;

CREATE TABLE model.m_specialization_type ( 
	id bigint DEFAULT nextval(('model.m_specialization_type_id_seq'::text)::regclass) NOT NULL,
	type varchar(50) NOT NULL
);


CREATE INDEX IXFK_l_hero_type_description_m_hero_type
	ON i18n.l_hero_type_description (m_hero_type_id);
CREATE INDEX IXFK_l_hero_type_description_m_language
	ON i18n.l_hero_type_description (lang);
CREATE INDEX IXFK_m_hero_m_grade
	ON model.m_hero (m_grade_id);
CREATE INDEX IXFK_m_hero_m_char_attribute
	ON model.m_hero (m_attribute_id);
CREATE INDEX IXFK_m_hero_m_hero_type
	ON model.m_hero (m_hero_type);
ALTER TABLE model.m_hero_type
	ADD CONSTRAINT UQ_m_hero_type_code UNIQUE (code);
CREATE INDEX IXFK_m_minion_m_char_attribute
	ON model.m_minion (m_attribute_id);
CREATE INDEX IXFK_m_minion_m_grade
	ON model.m_minion (m_grade_id);
CREATE INDEX IXFK_m_seeker_m_grade
	ON model.m_seeker (m_grade_id);
CREATE INDEX IXFK_m_seeker_specialization_m_seeker
	ON model.m_seeker_specialization (m_seeker_id);
CREATE INDEX IXFK_m_seeker_specialization_m_specialization_type
	ON model.m_seeker_specialization (m_specialization_type_id);
ALTER TABLE i18n.l_hero_type_description ADD CONSTRAINT PK_l_hero_type_description 
	PRIMARY KEY (id);


ALTER TABLE i18n.l_language ADD CONSTRAINT PK_m_language 
	PRIMARY KEY (id);


ALTER TABLE model.m_char_attribute ADD CONSTRAINT PK_m_char_attributes 
	PRIMARY KEY (id);


ALTER TABLE model.m_grade ADD CONSTRAINT PK_m_grade 
	PRIMARY KEY (id);


ALTER TABLE model.m_hero ADD CONSTRAINT PK_m_hero 
	PRIMARY KEY (id);


ALTER TABLE model.m_hero_type ADD CONSTRAINT PK_m_hero_type 
	PRIMARY KEY (id);


ALTER TABLE model.m_minion ADD CONSTRAINT PK_m_minion 
	PRIMARY KEY (id);


ALTER TABLE model.m_seeker ADD CONSTRAINT PK_seeker 
	PRIMARY KEY (id);


ALTER TABLE model.m_seeker_specialization ADD CONSTRAINT PK_m_seeker_specialization 
	PRIMARY KEY (id);


ALTER TABLE model.m_specialization_type ADD CONSTRAINT PK_m_specialization_type 
	PRIMARY KEY (id);




ALTER TABLE i18n.l_hero_type_description ADD CONSTRAINT FK_l_hero_type_description_m_hero_type 
	FOREIGN KEY (m_hero_type_id) REFERENCES model.m_hero_type (id);

ALTER TABLE i18n.l_hero_type_description ADD CONSTRAINT FK_l_hero_type_description_m_language 
	FOREIGN KEY (lang) REFERENCES i18n.l_language (id);

ALTER TABLE model.m_hero ADD CONSTRAINT FK_m_hero_m_grade 
	FOREIGN KEY (m_grade_id) REFERENCES model.m_grade (id);

ALTER TABLE model.m_hero ADD CONSTRAINT FK_m_hero_m_char_attribute 
	FOREIGN KEY (m_attribute_id) REFERENCES model.m_char_attribute (id);

ALTER TABLE model.m_hero ADD CONSTRAINT FK_m_hero_m_hero_type 
	FOREIGN KEY (m_hero_type) REFERENCES model.m_hero_type (id);

ALTER TABLE model.m_minion ADD CONSTRAINT FK_m_minion_m_char_attribute 
	FOREIGN KEY (m_attribute_id) REFERENCES model.m_char_attribute (id);

ALTER TABLE model.m_minion ADD CONSTRAINT FK_m_minion_m_grade 
	FOREIGN KEY (m_grade_id) REFERENCES model.m_grade (id);

ALTER TABLE model.m_seeker ADD CONSTRAINT FK_m_seeker_m_grade 
	FOREIGN KEY (m_grade_id) REFERENCES model.m_grade (id);
