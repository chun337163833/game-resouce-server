drop schema if exists data cascade;
drop schema if exists model cascade;
drop schema if exists i18n cascade;

create schema data;
comment on schema data is 'all tables contains data definition for AI and Users such as hero, minions, items, seekers states, levels and etc.';
alter schema data owner to game;

create schema model;
comment on schema model is 'all tables contains model definitions for game logic, such as skills, heroes and minions default attributes and etc.';
alter schema model owner to game;

create schema i18n;
comment on schema i18n is 'all tables contains localized values';
alter schema i18n owner to game;