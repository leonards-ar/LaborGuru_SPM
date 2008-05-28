CREATE TABLE tbl_users
(
  id integer NOT NULL,
  username character varying(50) NOT NULL,
  "password" character varying(45) NOT NULL,
  "name" character varying(50),
  surname character varying(100),
  CONSTRAINT tbl_users_pkey PRIMARY KEY (id)
)

INSERT INTO tbl_users VALUES (1, 'mcapurro', 'java1234', 'Mariano', 'Capurro');
INSERT INTO tbl_users VALUES (2, 'cnunez', 'java1234', 'Cristian', 'Nunez');
INSERT INTO tbl_users VALUES (3, 'fbarrera', 'java1234', 'Federico', 'Barrera Oro');
INSERT INTO tbl_users VALUES (4, 'igoris', 'java1234', 'Ignacio', 'Goris');
commit;

UPDATE tbl_users SET "password"='1234';