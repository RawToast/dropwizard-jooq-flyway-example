# backend-poc

Dropwizard backend proof of concept!!!


Postgres Setup
==============

CREATE USER pocuser WITH PASSWORD 'pocpassword';
CREATE DATABASE pocdb;

\c pocdb

CREATE SCHEMA coopjooqpoc;
CREATE TABLE coopjooqpoc.stores( 
store_id SERIAL PRIMARY KEY,                                                                                                       name varchar(120) NOT NULL,                                                                                                        postcode varchar(10) NOT NULL);

CREATE TABLE coopjooqpoc.members (
	member_id integer primary key,
	first_name varchar(30) NOT NULL,
	last_name varchar(30) NOT NULL,
	postcode varchar(10) NOT NULL,
	reward_points integer DEFAULT 0,
	favourite_store integer references coopjooqpoc.stores(store_id));

GRANT ALL PRIVILEGES ON DATABASE pocdb to pocuser;
GRANT ALL ON ALL TABLES IN SCHEMA coopjooqpoc TO pocuser;
GRANT USAGE ON SCHEMA coopjooqpoc TO pocuser;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA coopjooqpoc to pocuser;

INSERT INTO coopjooqpoc.stores(name, postcode)                                                                             VALUES                                                                                                                             ('Leeds - East', 'LS9 8AX'),                                                                                                       ('Leeds - Centre', 'LS1 1TW'),                                                                                                     ('Manchester - Centre', 'M1 1AB');




Organizing Your Project
=======================

In general, we recommend you separate your projects into three Maven modules: 

* ``project-api`` : should contain your JSON representations and service interfaces
* ``project-client`` : should contain client code used to get data from external rest service. Can be excluded, if you don't have any.
* ``project-application`` : should provide the actual application implementation, including resources

Modules are often broken down like this:

* ``com.example.myapplication``:

  * ``api``: JSON Representations
  * ``client``: Client implementation for your application
  * ``core``: Domain implementation
  * ``jdbi``: DB Access
  * ``health``: Healthchecks
  * ``resources``: Resources
  * ``MyApplication``: The application class
  * ``MyApplicationConfiguration``:The configuration class

