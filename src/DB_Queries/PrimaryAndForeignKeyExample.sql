-- create
CREATE TABLE EMPLOYEE (
  empId int not null primary key,
  name varchar(15),
  dept varchar(10)
);

CREATE TABLE DEPARTMENT (
  depId varchar(15) not null primary key,
  empId int not null,
  description varchar(15),
  constraint fk_employee foreign key (empId) references EMPLOYEE (empId)
  on delete cascade
);
Create TABLE enroll(
  empId int,
  depId varchar(15),
  constraint enroll_pk primary key (empId, depId),
  constraint enroll_fk_emp foreign key (empId) references EMPLOYEE (empId),
  constraint enroll_fk_dep foreign key (depId) references DEPARTMENT (depId)
  on delete cascade
)
-- Enroll table has empId and depId as composite key
-- Adding column via alter table
Alter table enroll add description varchar(10);

INSERT INTO EMPLOYEE(empId,name,dept) VALUES (1, 'Clark', 'Sales');
INSERT INTO EMPLOYEE(empId,name,dept) VALUES (2, 'Dave', 'Accounting');
INSERT INTO EMPLOYEE(empId,name,dept) VALUES (3, 'Ava', 'Sales');
INSERT INTO DEPARTMENT(depId,empId,description)
VALUES ('Sales', 3,'sales dept');
INSERT INTO enroll VALUES (3, 'Sales')
-- fetch
SELECT * FROM EMPLOYEE;
SELECT * FROM DEPARTMENT;
SELECT * FROM enroll;

-- SELECT emp.name, dep.empId, dep.description from EMPLOYEE as emp,
-- DEPARTMENT as dep where emp.empId= dep.empId;

-- alter table EMPLOYEE add constraint pk_empId primary key (empId);
delete from EMPLOYEE where empId = 3;
SELECT * FROM EMPLOYEE;
SELECT * FROM DEPARTMENT;
SELECT * FROM enroll;
GO