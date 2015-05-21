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

CREATE INDEX IXFK_skill_model_attribute_type
	ON model.skill (attribute_type);
ALTER TABLE model.skill ADD CONSTRAINT PK_skill_model 
	PRIMARY KEY (id);


ALTER TABLE model.skill ADD CONSTRAINT FK_skill_model_attribute_type 
	FOREIGN KEY (attribute_type) REFERENCES model.attribute_type (id);

