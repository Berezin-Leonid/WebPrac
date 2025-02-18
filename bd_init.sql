DROP TABLE IF EXISTS employee_post_division;
DROP TABLE IF EXISTS post_division;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS division;



CREATE TABLE employee (
    index SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    home_address VARCHAR(200),
    education VARCHAR(100),
    start_year DATE
);

CREATE TABLE post(
	index SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	respons TEXT NOT NULL
	
);

CREATE TABLE division(
	index SERIAL PRIMARY KEY,
	subsid_array INT[],
	parent_id INT,
	FOREIGN KEY (parent_id) REFERENCES division(index)
);


CREATE TABLE post_division(
	index SERIAL PRIMARY KEY,
	post_id INT,
	division_id INT,

	FOREIGN KEY (post_id) REFERENCES post(index),
	FOREIGN KEY (division_id) REFERENCES division(index)
);

CREATE TABLE employee_post_division(
	index SERIAL PRIMARY KEY,
	post_division_id INT,
	employee_id INT,
	hire_date DATE,
	retire_date DATE,

	FOREIGN KEY (post_division_id) REFERENCES post_division(index),
	FOREIGN KEY (employee_id) REFERENCES employee(index)
);




SET enable_nestloop=0;SELECT 'postgresql' AS dbms,t.table_catalog,t.table_schema,t.table_name,c.column_name,c.ordinal_position,c.data_type,c.character_maximum_length,n.constraint_type,k2.table_schema,k2.table_name,k2.column_name FROM information_schema.tables t NATURAL LEFT JOIN information_schema.columns c LEFT JOIN(information_schema.key_column_usage k NATURAL JOIN information_schema.table_constraints n NATURAL LEFT JOIN information_schema.referential_constraints r)ON c.table_catalog=k.table_catalog AND c.table_schema=k.table_schema AND c.table_name=k.table_name AND c.column_name=k.column_name LEFT JOIN information_schema.key_column_usage k2 ON k.position_in_unique_constraint=k2.ordinal_position AND r.unique_constraint_catalog=k2.constraint_catalog AND r.unique_constraint_schema=k2.constraint_schema AND r.unique_constraint_name=k2.constraint_name WHERE t.TABLE_TYPE='BASE TABLE' AND t.table_schema NOT IN('information_schema','pg_catalog');