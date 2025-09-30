DROP TABLE IF EXISTS employee_post_division;
DROP TABLE IF EXISTS post_division;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS division;

CREATE TABLE employee (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	birth_day DATE
);

CREATE TABLE post(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	respons TEXT NOT NULL
	
);

CREATE TABLE division(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	parent_id INT,
	FOREIGN KEY (parent_id) REFERENCES division(id)
);


CREATE TABLE post_division(
	id SERIAL PRIMARY KEY,
	post_id INT,
	division_id INT,

	FOREIGN KEY (post_id) REFERENCES post(id),
	FOREIGN KEY (division_id) REFERENCES division(id)
);

CREATE TABLE employee_post_division(
	id SERIAL PRIMARY KEY,
	post_division_id INT,
	employee_id INT,
	hire_date DATE,
	retire_date DATE,

	FOREIGN KEY (post_division_id) REFERENCES post_division(id),
	FOREIGN KEY (employee_id) REFERENCES employee(id)
);