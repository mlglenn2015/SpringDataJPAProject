----------------------------------------------------------------------------------------------------
-- Hypothetical example of a many-to-many relationship in JPA.
--
-- Though this is not an efficient way of representing Users and Groups from a normalized
-- database perspective, it demonstrates how JPA handles the CRUD operations on entities that have
-- this type of many-to-many relationship.
--
-- create_user_group_data.sql
----------------------------------------------------------------------------------------------------

ALTER SESSION SET CURRENT_SCHEMA=STOCKS;


ALTER TABLE USERS_GROUPS DROP CONSTRAINT users_groups_fk01;
ALTER TABLE USERS_GROUPS DROP CONSTRAINT users_groups_fk02;

DROP TABLE USERS;
DROP TABLE GROUPS;
DROP TABLE USERS_GROUPS;

DROP SEQUENCE SEQ_USERS_GROUPS;


CREATE TABLE USERS (
  --ID          NUMBER(10)                          NOT NULL PRIMARY KEY,
  USER_ID     VARCHAR2(30)                        NOT NULL PRIMARY KEY,
  USER_NAME   VARCHAR2(100)                       NOT NULL
);

--CREATE UNIQUE INDEX users_idx01 ON  USERS (USER_ID);

--CREATE SEQUENCE SEQ_USERS
--MINVALUE 1
--MAXVALUE 999999999999999999999999999
--START WITH 1
--INCREMENT BY 1
--CACHE 20;

CREATE TABLE GROUPS (
  --ID          NUMBER(10)                          NOT NULL PRIMARY KEY,
  GROUP_ID    VARCHAR2(30)                        NOT NULL PRIMARY KEY,
  GROUP_NAME  VARCHAR2(100)                       NOT NULL
);

--CREATE UNIQUE INDEX groups_idx01 ON  GROUPS (GROUP_ID);

--CREATE SEQUENCE SEQ_GROUPS
--MINVALUE 1
--MAXVALUE 999999999999999999999999999
--START WITH 1
--INCREMENT BY 1
--CACHE 20;

CREATE TABLE USERS_GROUPS (
  ID          NUMBER(10)                          NOT NULL PRIMARY KEY,
  USER_ID     VARCHAR2(30)                        NOT NULL,
  GROUP_ID    VARCHAR2(30)                        NOT NULL
);

CREATE INDEX groups_idx01 ON  USERS_GROUPS (USER_ID);
CREATE INDEX groups_idx02 ON  USERS_GROUPS (GROUP_ID);

CREATE SEQUENCE SEQ_USERS_GROUPS
MINVALUE 1
MAXVALUE 999999999999999999999999999
START WITH 1
INCREMENT BY 1
CACHE 20;

COMMIT;

----------------------------------------------------------------------------------------------------
INSERT INTO USERS (USER_ID, USER_NAME) VALUES ('G124807', 'Mark UserG124807');
INSERT INTO USERS (USER_ID, USER_NAME) VALUES ('G124808', 'John UserG124808');
INSERT INTO USERS (USER_ID, USER_NAME) VALUES ('G124809', 'Dave UserG124809');

INSERT INTO GROUPS (GROUP_ID, GROUP_NAME) VALUES ('00001', 'Administrators');
INSERT INTO GROUPS (GROUP_ID, GROUP_NAME) VALUES ('00002', 'Deployers');
INSERT INTO GROUPS (GROUP_ID, GROUP_NAME) VALUES ('00003', 'Testers');

INSERT INTO USERS_GROUPS (ID, USER_ID, GROUP_ID) VALUES (SEQ_USERS_GROUPS.nextval, 'G124807', '00001'); -- Administrator
INSERT INTO USERS_GROUPS (ID, USER_ID, GROUP_ID) VALUES (SEQ_USERS_GROUPS.nextval, 'G124808', '00001'); -- Administrator
INSERT INTO USERS_GROUPS (ID, USER_ID, GROUP_ID) VALUES (SEQ_USERS_GROUPS.nextval, 'G124809', '00002'); -- Deployer
INSERT INTO USERS_GROUPS (ID, USER_ID, GROUP_ID) VALUES (SEQ_USERS_GROUPS.nextval, 'G124809', '00003'); -- Tester

COMMIT;

-- ON DELETE CASCADE referenced
-- http://stackoverflow.com/questions/1082095/how-to-remove-entity-with-manytomany-relationship-in-jpa-and-corresponding-join?rq=1
--ALTER TABLE USERS ADD CONSTRAINT users_fk01 FOREIGN KEY (GROUP_ID) REFERENCES GROUPS (ID) ON DELETE CASCADE;
--ALTER TABLE GROUPS ADD CONSTRAINT groups_fk01 FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE;

ALTER TABLE USERS_GROUPS ADD CONSTRAINT users_groups_fk01 FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE;
ALTER TABLE USERS_GROUPS ADD CONSTRAINT users_groups_fk02 FOREIGN KEY (GROUP_ID) REFERENCES GROUPS (GROUP_ID) ON DELETE CASCADE;

COMMIT;
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

