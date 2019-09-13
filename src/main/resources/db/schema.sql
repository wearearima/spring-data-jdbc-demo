DROP TABLE city IF EXISTS;
DROP TABLE country IF EXISTS;

CREATE TABLE country (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(50),
  population INTEGER
);

CREATE TABLE city (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(50),
  population INTEGER,
  country INTEGER,
  FOREIGN KEY(country) REFERENCES country(id)
);