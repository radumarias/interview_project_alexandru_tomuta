create table LOCATION (
  ID int not null,
  NAME varchar(80) not null,
  LATITUDE real not null,
  LONGITUDE real not null,
  PRIMARY KEY (ID)
);

create table PLACE (
  ID int not null,
  NAME varchar(80) not null,
  FAVOURITE boolean,
  TYPE varchar(20),
  ADDRESS varchar(100),
  LATITUDE real not null,
  LONGITUDE real not null,
  LOCATION_ID int not null REFERENCES LOCATION(ID),
  PICTURE_URL varchar(200),
  COMMENT varchar(200),

  PRIMARY KEY (ID)

)