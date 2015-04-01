DROP TABLE IF EXISTS i18n.skill_description CASCADE;
DROP SEQUENCE IF EXISTS i18n.skill_description_id_seq;
CREATE SEQUENCE i18n.skill_description_id_seq INCREMENT 1 START 1;

CREATE TABLE i18n.skill_description ( 
	id bigint DEFAULT nextval(('i18n.skill_description_id_seq'::text)::regclass) NOT NULL,
	skill bigint NOT NULL,
	lang char(2) NOT NULL,
	value text NOT NULL
);

CREATE INDEX IXFK_skill_model_descirption_language
	ON i18n.skill_description (lang);
CREATE INDEX IXFK_skill_model_descirption_skill_model
	ON i18n.skill_description (skill);
ALTER TABLE i18n.skill_description ADD CONSTRAINT PK_skill_model_descirption 
	PRIMARY KEY (id);


ALTER TABLE i18n.skill_description ADD CONSTRAINT FK_skill_model_descirption_language 
	FOREIGN KEY (lang) REFERENCES i18n.language (id);

ALTER TABLE i18n.skill_description ADD CONSTRAINT FK_skill_model_descirption_skill_model 
	FOREIGN KEY (skill) REFERENCES model.skill (id);

