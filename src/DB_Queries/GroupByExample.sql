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
  products varchar(100), -- products should be product_id and stored in multiple rows.
  -- following Normalised forms of DB
  -- status varchar(100),
  constraint users_orders_fk foreign key (user_id) references USERS (id)
  -- foreign key of products id
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
SELECT u.id, SUM(o.total_price) AS total_spent, count(o.id) as order_count
FROM Users AS u
JOIN Orders AS o ON o.user_id = u.id
GROUP BY u.id
ORDER BY order_count DESC LIMIT 1;

-- fetch most expensive user
SELECT u.id, SUM(o.total_price) AS total_spent, count(o.id) as order_count
FROM Users AS u
JOIN Orders AS o ON o.user_id = u.id
GROUP BY u.id
ORDER BY total_spent DESC LIMIT 1;

-- SQL Query to Identify Top 5 Products:

SELECT
    u.username,
    SUM(p.price * o.quantity) AS customer_lifetime_value
FROM
    users AS u
JOIN
    orders AS o ON u.id = o.user_id
JOIN
    products AS p ON o.product_id = p.id
GROUP BY
    u.id, u.username -- why 2 keys
    -- The fundamental rule in SQL is that any non-aggregated column in the SELECT list must also be present in the GROUP BY list.
ORDER BY
    customer_lifetime_value DESC;

-----------------------
SELECT
    u.username,
    u.email,
    SUM(p.price * o.quantity) AS customer_lifetime_value
FROM
    users AS u
JOIN
    orders AS o ON u.id = o.user_id
JOIN
    products AS p ON o.product_id = p.id
GROUP BY
    u.id, u.username, u.email
ORDER BY
    customer_lifetime_value DESC;
