drop schema if exists udata cascade;
drop schema if exists model cascade;
drop schema if exists i18n cascade;

create schema udata;
comment on schema udata is 'all tables contains user data such as hero, minions, items, seekers states, levels and etc.';
alter schema udata owner to game;

create schema model;
comment on schema model is 'all tables contains model definitions for game logic, such as skills, heroes and minions default attributes and etc.';
alter schema model owner to game;

create schema i18n;
comment on schema i18n is 'all tables contains localized values';
alter schema i18n owner to game;