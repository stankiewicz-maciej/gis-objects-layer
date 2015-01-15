DROP TABLE IF EXISTS CABINETS CASCADE;
DROP TABLE IF EXISTS SEGMENT_CTRL CASCADE;
DROP TABLE IF EXISTS FIXTURES CASCADE;
DROP TABLE IF EXISTS DRIVERS CASCADE;
DROP TABLE IF EXISTS SENSORS CASCADE;


CREATE TABLE CABINETS
(
  ID SERIAL NOT NULL
, LOCATION_ID BIGINT NOT NULL
, NUMBER VARCHAR NOT NULL
);

ALTER TABLE CABINETS ADD CONSTRAINT PK_CABINETS
	PRIMARY KEY (ID)
;

CREATE TABLE SEGMENT_CTRL
(
  ID SERIAL NOT NULL
, CABINET_ID BIGINT NOT NULL
--todo: Segment id FK (group 4)
);

ALTER TABLE SEGMENT_CTRL ADD CONSTRAINT PK_SEGMENT_CTRL
	PRIMARY KEY (ID)
;
ALTER TABLE SEGMENT_CTRL  ADD CONSTRAINT FK_SEGMENT_CTRL_CABINET 
	FOREIGN KEY (CABINET_ID) REFERENCES CABINETS (ID) ON DELETE CASCADE
;


CREATE TABLE FIXTURES
(
  ID SERIAL NOT NULL
-- , SEGMENT_CTRL_ID BIGINT NOT NULL
--todo: location FK (group 1)
);

ALTER TABLE FIXTURES ADD CONSTRAINT PK_FIXTURES
	PRIMARY KEY (ID)
;
-- ALTER TABLE FIXTURES  ADD CONSTRAINT FK_FIXTURES_SEGMENT_CTRL
-- 	FOREIGN KEY (SEGMENT_CTRL_ID) REFERENCES SEGMENT_CTRL (ID) ON DELETE CASCADE
-- ;

CREATE TABLE DRIVERS
(
  ID SERIAL NOT NULL
, FIXTURE_ID BIGINT NOT NULL
--todo: data
);

ALTER TABLE DRIVERS ADD CONSTRAINT PK_DRIVERS
	PRIMARY KEY (ID)
;
ALTER TABLE DRIVERS  ADD CONSTRAINT FK_DRIVERS_FIXTURES
	FOREIGN KEY (FIXTURE_ID) REFERENCES FIXTURES (ID) ON DELETE CASCADE
;

CREATE TABLE SENSORS
(
  ID SERIAL NOT NULL
-- , SEGMENT_CTRL_ID BIGINT NOT NULL
--??
--todo: location FK (group 1)
--todo: sensor id FK (group 3)
);

ALTER TABLE SENSORS ADD CONSTRAINT PK_SENSORS
	PRIMARY KEY (ID)
;
-- ALTER TABLE SENSORS  ADD CONSTRAINT FK_SENSORS_SEGMENT_CTRL
-- 	FOREIGN KEY (SEGMENT_CTRL_ID) REFERENCES SEGMENT_CTRL (ID) ON DELETE CASCADE
-- ;



