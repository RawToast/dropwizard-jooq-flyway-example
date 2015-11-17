CREATE TABLE poc.stores(
    store_id SERIAL PRIMARY KEY,
    name varchar(120) NOT NULL,
    postcode varchar(10) NOT NULL);

CREATE TABLE poc.members (
	member_id integer primary key,
	first_name varchar(30) NOT NULL,
	last_name varchar(30) NOT NULL,
	postcode varchar(10) NOT NULL,
	reward_points integer DEFAULT 0,
	favourite_store integer references poc.stores(store_id));

INSERT INTO poc.stores(name, postcode)
    VALUES  ('Leeds - East', 'LS9 8AX'),
            ('Leeds - Centre', 'LS1 1TW'),
            ('Manchester - Centre', 'M1 1AB');