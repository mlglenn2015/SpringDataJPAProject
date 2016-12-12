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


ALTER TABLE USERS DROP CONSTRAINT users_fk01;
ALTER TABLE GROUPS DROP CONSTRAINT groups_fk01;

DROP TABLE USERS;
DROP TABLE GROUPS;
--DROP SEQUENCE SEQ_USERS;
--DROP SEQUENCE SEQ_GROUPS;

CREATE TABLE USERS (
  ID          NUMBER(10)                          NOT NULL PRIMARY KEY,
  --USER_ID     VARCHAR2(30)                        NOT NULL,
  USER_NAME   VARCHAR2(100)                       NOT NULL,
  GROUP_ID    NUMBER(10)                          NOT NULL
);

--CREATE INDEX users_idx01 ON  USERS (USER_ID);
CREATE INDEX users_idx01 ON  USERS (GROUP_ID);

--CREATE SEQUENCE SEQ_USERS
--MINVALUE 1
--MAXVALUE 999999999999999999999999999
--START WITH 1
--INCREMENT BY 1
--CACHE 20;

CREATE TABLE GROUPS (
  ID          NUMBER(10)                          NOT NULL PRIMARY KEY,
  --GROUP_ID    VARCHAR2(30)                        NOT NULL,
  GROUP_NAME  VARCHAR2(100)                       NOT NULL,
  USER_ID     NUMBER(10)                          NOT NULL
);

--CREATE INDEX groups_idx01 ON  GROUPS (GROUP_ID);
CREATE INDEX groups_idx01 ON  GROUPS (USER_ID);

--CREATE SEQUENCE SEQ_GROUPS
--MINVALUE 1
--MAXVALUE 999999999999999999999999999
--START WITH 1
--INCREMENT BY 1
--CACHE 20;

COMMIT;

----------------------------------------------------------------------------------------------------
INSERT INTO USERS (ID, USER_NAME, GROUP_ID) VALUES (1, 'Mark User1', 1);
INSERT INTO USERS (ID, USER_NAME, GROUP_ID) VALUES (2, 'John User2', 1);
INSERT INTO USERS (ID, USER_NAME, GROUP_ID) VALUES (3, 'Dave User3', 2);

INSERT INTO GROUPS (ID, GROUP_NAME, USER_ID) VALUES (1, 'Administrators', 1);
INSERT INTO GROUPS (ID, GROUP_NAME, USER_ID) VALUES (2, 'Administrators', 2);
INSERT INTO GROUPS (ID, GROUP_NAME, USER_ID) VALUES (3, 'Deployers', 3);

COMMIT;

ALTER TABLE USERS ADD CONSTRAINT users_fk01 FOREIGN KEY (GROUP_ID) REFERENCES GROUPS (ID);
ALTER TABLE GROUPS ADD CONSTRAINT groups_fk01 FOREIGN KEY (USER_ID) REFERENCES USERS (ID);

COMMIT;
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------

