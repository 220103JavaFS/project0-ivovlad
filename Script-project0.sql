DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
	id integer PRIMARY KEY,
	first_name varchar (20),
	last_name varchar (20),
	duty varchar (20)
	);

INSERT INTO employees(id, first_name, last_name, duty)
VALUES (1001, 'John', 'Smith','admin'),
		(1002, 'Tom', 'Collins','teller'),
		(1003, 'Ava','Rogers', 'teller'),
		(1004, 'Lynn', 'Roth', 'teller');

DROP TABLE IF EXISTS clients;
	
CREATE TABLE clients (
	ssn integer PRIMARY KEY,
	first_name varchar (20),
	last_name varchar (20),
	address varchar(50),
	bank_teller_id integer REFERENCES employees(id)
);

INSERT INTO clients (ssn, first_name, last_name, address, bank_teller_id)
VALUES (1002221111, 'Don', 'Harper', '1 W33 str. NY', 1002),
		(1002221112, 'Donna', 'Harper', '1 W33 str. NY', 1002),
		(1002221113, 'Tom', 'Pernot', '12 W23 str. NY', 1003),
		(1002221114, 'Rachel', 'Arams', '51 W55 str. NY', 1003),
		(1002221115, 'Lima', 'Romero', '11 W66 str. NY', 1004),
		(1002221116, 'Fatma', 'Grillo', '41 W73 str. NY', 1004),
		(1002221117, 'Rick', 'Harper', '1 W99 str. NY', 1004);

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts(
	acc_number SERIAL PRIMARY KEY,
	client_id integer REFERENCES clients(ssn),
	balance NUMERIC CHECK (balance>=0),
	acc_type varchar(10)
);

INSERT INTO accounts (client_id, balance,acc_type)
VALUES (1002221111, 100.22, 'checking'),
		(1002221111, 130.22, 'savings'),
		(1002221111, 432.22, 'checking'),
		(1002221112, 100.22, 'checking'),
		(1002221112, 100.22, 'savings'),
		(1002221113, 100.22, 'checking'),
		(1002221113, 100.22, 'savings'),
		(1002221114, 100.22, 'savings'),
		(1002221114, 100.22, 'checking'),
		(1002221115, 100.22, 'checking'),
		(1002221115, 100.22, 'savings'),
		(1002221116, 100.22, 'checking'),
		(1002221116, 100.22, 'savings'),
		(1002221117, 100.22, 'checking'),
		(1002221117, 100.22, 'savings'),
		(1002221117, 100.22, 'checking'),
		(1002221115, 100.22, 'savings'),
		(1002221113, 100.22, 'savings'),
		(1002221116, 100.22, 'checking');
		
		
-- clients can see only their own information
	
	SELECT * FROM accounts INNER JOIN clients ON accounts.client_id =clients.ssn WHERE clients.ssn =1002221115;
SELECT * FROM accounts WHERE accounts.client_id =1002221114;

-- tellers can see only accounts, assigned to them

SELECT * FROM (SELECT * FROM accounts INNER JOIN clients ON accounts.client_id =clients.ssn) AS q INNER JOIN employees
	ON q.bank_teller_id = employees.id
	WHERE employees.id =1003;

-- transfering moneey from one account to another
BEGIN;
UPDATE accounts SET balance = balance+50.00 WHERE acc_number = 10;

UPDATE accounts SET balance = balance-50.00 WHERE acc_number = 11;
COMMIT; 

-- admins can delete records
DELETE  FROM  accounts 
WHERE acc_number = 17;

-- admints can approve new accounts

SELECT * FROM accounts;

