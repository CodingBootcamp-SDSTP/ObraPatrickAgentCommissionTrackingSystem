DROP TABLE IF EXISTS agent;
DROP TABLE IF EXISTS commtbl;

CREATE TABLE agent(
	agentid VARCHAR(15) NOT NULL,
	firstName VARCHAR(100),
	lastName VARCHAR(100),
	email VARCHAR(100),
	age INT,
	PRIMARY KEY(agentid)
	);

INSERT INTO agent (agentid,firstName,lastName,email,age) VALUES ('001','Homer','Simpson','hsimpson@acts.com',45);
INSERT INTO agent (agentid,firstName,lastName,email,age) VALUES ('002','Marge','Simpson','msimpson@acts.com',40);
INSERT INTO agent (agentid,firstName,lastName,email,age) VALUES ('003','Bart','Simpson','bsimpson@acts.com',22);


CREATE TABLE commtbl(
	agentid VARCHAR(15) NOT NULL,
	saleDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	itemSold VARCHAR(100),
	itemAmount INT,
	comm_rate INT,
	total_comm INT
	PRIMARY KEY(agentid)
);

INSERT INTO commtbl (agentid,saleDate,itemSold,itemAmount,comm_rate,total_comm) VALUES ('001','','HOVERDRONE',10000,5,500);
INSERT INTO commtbl (agentid,saleDate,itemSold,itemAmount,comm_rate,total_comm) VALUES ('002','','ALPINEROVER',10000,5,500);
INSERT INTO commtbl (agentid,saleDate,itemSold,itemAmount,comm_rate,total_comm) VALUES ('003','','SPLASHGRILL',10000,5,500);
INSERT INTO commtbl (agentid,saleDate,itemSold,itemAmount,comm_rate,total_comm) VALUES ('004','','PROPELLER SHAFT',10000,5,500);
