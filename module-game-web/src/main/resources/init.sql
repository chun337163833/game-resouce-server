INSERT INTO role(id, code) VALUES (nextval('role_id_seq'), 'ROLE_ADMIN');
INSERT INTO role(id, code) VALUES (nextval('role_id_seq'), 'ROLE_CUSTOMER');
INSERT INTO role(id, code) VALUES (nextval('role_id_seq'), 'ROLE_DRIVER');
INSERT INTO role(id, code) VALUES (nextval('role_id_seq'), 'ROLE_DISPATCHER');

INSERT INTO tx_user(dtype, id, email, first_name, last_name, password, user_name, version) VALUES('OtherUser', nextval('tx_user_id_seq'), 'admin@localhost', 'admin', 'admin', 'af403d2b497aa62dadfddf02282b19fd40496898d4643e273ba52a9d467653b2', 'admin', 0);
INSERT INTO tx_user_roles(tx_user, roles) VALUES ((select id from tx_user where user_name='admin'), (select id from role where code='ROLE_ADMIN'));

commit