DROP TABLE city IF EXISTS;
DROP TABLE country IF EXISTS;

CREATE TABLE country (
  c_id INTEGER IDENTITY PRIMARY KEY,
  c_name VARCHAR(50),
  c_population INTEGER
);

CREATE TABLE city (
  ci_id INTEGER IDENTITY PRIMARY KEY,
  ci_name VARCHAR(50),
  ci_population INTEGER,
  ci_country INTEGER,
  FOREIGN KEY(ci_country) REFERENCES country(c_id)
);
