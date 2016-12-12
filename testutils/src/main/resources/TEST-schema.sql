----------------------------------------------------------------------------------------------------
-- test tables
-- TEST-schema.sql
----------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS ORDER_STATUS;
DROP SEQUENCE IF EXISTS SEQ_ORDER_STATUS;

CREATE TABLE ORDER_STATUS (
  ID                  NUMERIC NOT NULL PRIMARY KEY,
  ORDER_STATUS        VARCHAR(25) NOT NULL,
  DESCRIPTION         VARCHAR(100) NOT NULL
);

CREATE SEQUENCE SEQ_ORDER_STATUS
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;


DROP TABLE IF EXISTS ORDER_TYPES;
DROP SEQUENCE IF EXISTS SEQ_ORDER_TYPES;

CREATE TABLE ORDER_TYPES (
  ID                  NUMERIC NOT NULL PRIMARY KEY,
  ORDER_TYPE          VARCHAR(25) NOT NULL,
  DESCRIPTION         VARCHAR(100) NOT NULL
);

CREATE SEQUENCE SEQ_ORDER_TYPES
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;


DROP TABLE IF EXISTS STOCK_ORDER;
DROP SEQUENCE IF EXISTS SEQ_STOCK_ORDER;

CREATE TABLE STOCK_ORDER (
  ID                  NUMERIC NOT NULL PRIMARY KEY,
  STOCK_SYMBOL        VARCHAR(10) NOT NULL,
  ACTION              VARCHAR(4) NOT NULL, -- BUY, SELL
  QUANTITY            NUMERIC NOT NULL,
  PRICE               NUMERIC NOT NULL,
  ORDER_DATE          TIMESTAMP NOT NULL,
  ORDER_TYPE          VARCHAR(25) NOT NULL,
  ORDER_STATUS        VARCHAR(25) NOT NULL
);

CREATE SEQUENCE SEQ_STOCK_ORDER
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;


DROP TABLE IF EXISTS STOCK_PRICE;
DROP SEQUENCE IF EXISTS SEQ_STOCK_PRICE;

CREATE TABLE STOCK_PRICE (
  ID                  NUMERIC NOT NULL PRIMARY KEY,
  STOCK_SYMBOL        VARCHAR(10) NOT NULL,
  CURRENT_PRICE       NUMERIC NOT NULL
);

CREATE SEQUENCE SEQ_STOCK_PRICE
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;


DROP TABLE IF EXISTS TRANSACTION_LOG;
DROP SEQUENCE IF EXISTS SEQ_TRANSACTION_LOG;

CREATE TABLE TRANSACTION_LOG (
  ID                  NUMERIC NOT NULL PRIMARY KEY,
  LOG_DATE_TIME       TIMESTAMP NOT NULL,
  TRANSACTION_TYPE    VARCHAR(25) NOT NULL,
  TRANSACTION_DATA    VARCHAR(500)
);

CREATE SEQUENCE SEQ_TRANSACTION_LOG
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;


DROP TABLE IF EXISTS TRANSACTION_TYPES;
DROP SEQUENCE IF EXISTS SEQ_TRANSACTION_TYPES;

CREATE TABLE TRANSACTION_TYPES (
  ID                  NUMERIC NOT NULL PRIMARY KEY,
  TRANSACTION_TYPE    VARCHAR(25) NOT NULL,
  DESCRIPTION         VARCHAR(100) NOT NULL
);

CREATE SEQUENCE SEQ_TRANSACTION_TYPES
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;


DROP TABLE IF EXISTS APPLICATION_MESSAGES;
DROP SEQUENCE IF EXISTS SEQ_APPLICATION_MESSAGES;

CREATE TABLE APPLICATION_MESSAGES (
  ID          NUMERIC                          NOT NULL PRIMARY KEY,
  MESSAGE_KEY VARCHAR(100)                       NOT NULL,
  MESSAGE     VARCHAR(500)                       NOT NULL
);

CREATE SEQUENCE SEQ_APPLICATION_MESSAGES
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;


DROP TABLE IF EXISTS APPLICATION_PARAMETERS;
DROP SEQUENCE IF EXISTS SEQ_APPLICATION_PARAMETERS;

CREATE TABLE APPLICATION_PARAMETERS (
  ID         NUMERIC                           NOT NULL PRIMARY KEY,
  KEY        VARCHAR(100)                        NOT NULL,
  PROPERTY   VARCHAR(500)                        NOT NULL,
  ENABLED    CHAR(1) DEFAULT 'Y'                 NOT NULL,
  CREATED    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE SEQUENCE SEQ_APPLICATION_PARAMETERS
  AS BIGINT
    START WITH 1
    INCREMENT BY 1;

----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
COMMIT;
----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------