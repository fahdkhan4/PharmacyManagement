CREATE TABLE adminLogin(
username VARCHAR(25) PRIMARY KEY,
password VARCHAR(25)
);

INSERT INTO adminlogin VALUES ("fahd","fahd1"),("abc","abc1");


CREATE TABle employeeLogin(
employeeName VARCHAR(25) PRIMARY KEY NOT NULL,
emp_password VARCHAR(25)
);

INSERT INTO employeelogin VALUES ("fahd","fahd1"),("emp","emp1");

SELECT * FROM adminlogin;
desc employeeLogin;
CREATE TABLE Products(
barcode int PRIMARY KEY NOT NULL,
product_name  varchar(50) ,
product_varient varchar(50),
cost_price double,
sell_price double,
product_qty int
);

CREATE TABLE ProductOrder(
id int PRIMARY KEY auto_increment,
user_name VARCHAR(50),
order_date date,
state VARCHAR(50)
);

CREATE TABLE Cart(
id int PRIMARY KEY auto_increment,
product_barcode int,
product_name varchar(50),
product_varient varchar(50),
product_price double ,
price_unit double,
product_qty int,
order_id int NOT NULL,
foreign key (order_id) references ProductOrder(id),
foreign key (product_barcode) references products(barcode)
);

CREATE TABLE Invoice(
id int primary key auto_increment,
order_id int,
emp_name VARCHAR(25),
invoice_date date,
FOREIGN KEY (order_id) references productorder(id)
);

CREATE TABLE invoice_line(
invoice_id int,
order_id int,
item_name varchar(50),
item_varient varchar(50),
product_price double,
itemQty_price double,
item_qty int,
foreign key (invoice_id) references Invoice(id),
foreign key (order_id) references productorder(id)
); 

CREATE TABLE Sales(
id int PRIMARY KEY auto_increment,
order_id int,
emp_name VARCHAR(25),
order_date date,
cost_price double,
sell_price double,
profit double,
foreign key (order_id) references productorder(id)
);



select * from adminlogin;
SELECT * from cart;
select * from productorder;
select * from products;
SELECT * FROM invoice;
SELECT * FROM cart where order_id = 92; 
SELECT * FROM invoice_line;
Select * from employeelogin;
SELECT * from sales;



SELECT SUM(price_unit) FROM cart WHERE order_id = 10;

SELECT e.id , e.order_id ,c.product_name ,c.product_varient , c.price_unit, product_qty FROM cart c
INNER JOIN invoice e ON e.order_id = c.order_id where e.order_id= 55; 

DROP table cart;

ALTER TABLE productorder CHANGE COLUMN userproductorder_name  user_name varchar(100);
SELECT * FROM productorder;

INSERT INTO employeelogin VALUES ("fahd","fahd1"),("anus","anus1"),("emp","emp1");

INSERT INTO products VALUES (99991,"water","50ml",20,40,12),(7122,"starp","50ml",20,40,12),(176,"road","50ml",20,40,12)
,(1414,"gator","50ml",20,40,12),(9199,"bitter","50ml",20,40,12),(81,"haj","50ml",20,40,12),(16666,"sand","50ml",20,40,12);

SELECT o.id , o.user_name , o.order_date , SUM(c.price_unit)AS  priceSell , SUM(p.cost_price * c.product_qty)  costPrice  , (SUM(c.price_unit) - SUM(p.cost_price * c.product_qty)) AS profit  FROM ((cart c
INNER JOIN productorder o ON o.id = c.order_id)
INNER JOIN products p ON p.barcode = c.product_barcode)
 WHERE o.state = "Completed" GROUP BY o.id;