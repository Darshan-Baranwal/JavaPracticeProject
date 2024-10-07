CREATE TABLE USERS (
  id int primary key,
  name varchar(15),
  email varchar(10) unique
);
CREATE TABLE Products (
  id int primary key,
  name varchar(15),
  price int
);
create table Orders (
  id int primary key,
  user_id int,
  total_price int,
  products varchar(100),
  -- status varchar(100),
  constraint users_orders_fk foreign key (user_id) references USERS (id)
);
INSERT INTO users VALUES (1, 'A', 'sfds');
INSERT INTO users VALUES (2, 'B', '123');
INSERT INTO users VALUES (3, 'C', 'sf12ds');

INSERT INTO products VALUES (1, 'Z', 12);
INSERT INTO products VALUES (2, 'X', 11);
INSERT INTO products VALUES (3, 'Y', 10);

INSERT INTO Orders VALUES (1, 1, 23, "Z,X");
INSERT INTO Orders VALUES (2, 1, 10, "Y");
INSERT INTO Orders VALUES (3, 2, 10, "Y");
INSERT INTO Orders VALUES (4, 3, 20, "Y");
-- fetch user with maximum number of Orders
select TOP 1 u.id, SUM(o.total_price) from
Users as u inner JOIN Orders as o on u.id = o.user_id
group by u.id order by SUM(o.total_price) desc

-- fetch most expensive user
SELECT TOP 1 * from Orders as o order by total_price desc
GO