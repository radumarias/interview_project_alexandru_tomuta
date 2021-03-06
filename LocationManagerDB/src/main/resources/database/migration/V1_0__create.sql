create table LOCATION (
  ID int not null,
  NAME varchar(80) NOT NULL UNIQUE,
  LATITUDE real NOT NULL ,
  LONGITUDE real not null,
  PRIMARY KEY (ID)
);

create table PLACE (
  ID int NOT NULL ,
  NAME varchar(80) NOT NULL UNIQUE,
  TYPE varchar(100),
  RATING real,
  ADDRESS varchar(100),
  LATITUDE real NOT NULL ,
  LONGITUDE real NOT NULL ,
  LOCATION_ID int NOT NULL REFERENCES LOCATION(ID),
  PICTURE_REF varchar(200),
  USER_EDITED boolean NOT NULL ,
  GOOGLE_PLACE_ID VARCHAR(80) NOT NULL ,

  PRIMARY KEY (ID)

);

create table PHOTO (
  ID int NOT NULL ,
  PICTURE_REF varchar(200) ,
  PLACE_ID int NOT NULL REFERENCES PLACE(ID),

  PRIMARY KEY (ID)
);

CREATE SEQUENCE HIBERNATE_SEQUENCE;