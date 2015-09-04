create database shopping_cart;

use shopping_cart;

create table users ( id int NOT NULL AUTO_INCREMENT, username varchar(100) NOT NULL, password varchar(30) NOT NULL, fname varchar(30) NOT NULL, lname varchar(30),address varchar(255) NOT NULL, contact_no int NOT NULL,enabled tinyint NOT NULL, PRIMARY KEY(id));
insert into users(username, password, fname, lname, address, contact_no, enabled) values
("sky@gmail.com", "sky123", "Sky", "Blue","Parbani,Maharashtra", 1234567890, true),
("jack@gmail.com", "jack123", "Jack", "Wilson","Gadag, Karnataka", 1234565416, true),
("robert@gmail.com", "rob123", "Robert", "Patrick","Jalgaon,MadhyaPradesh", 7987491156, true),
("andriya@gmail.com", "andy123", "Andriya", "Floresce","Indore,MadhyaPradesh", 7987491156, true);


create table user_roles ( id int NOT NULL AUTO_INCREMENT,role varchar(50) NOT NULL, user_id int NOT NULL, PRIMARY KEY(id), FOREIGN KEY(user_id) REFERENCES users(id));

insert into user_roles(role,user_id) values
("ROLE_ADMIN",1),
("ROLE_USER",2),
("ROLE_USER",3),
("ROLE_USER",4);

create table items(id int NOT NULL AUTO_INCREMENT, item_name varchar(255) NOT NULL, price double(8,2) NOT NULL, quantity int NOT NULL, category varchar(100) NOT NULL, PRIMARY KEY(id));

insert into items(item_name,price,quantity,category) value("Micromax Yuphoria",6999.00,11,"Mobiles");
insert into items(item_name,price,quantity,category) value("Micromax Yureka Plus",9999.00,11,"Mobiles");
insert into items(item_name,price,quantity,category) value("Xiaomi Mi4i",12999.00,11,"Mobiles");
insert into items(item_name,price,quantity,category) value("Lenovo K Note",9999.00,11,"Mobiles");
insert into items(item_name,price,quantity,category) value("Asus Zenfone 2",12999.00,11,"Mobiles");
insert into items(item_name,price,quantity,category) value("One Plus 2",24999.00,11,"Mobiles");
insert into items(item_name,price,quantity,category) value("Let Us C",350.00,11,"Books");
insert into items(item_name,price,quantity,category) value("Spring in action",515.00,11,"Books");
insert into items(item_name,price,quantity,category) value("Wings on fire",175.00,11,"Books");
insert into items(item_name,price,quantity,category) value("Web Technologies",480.00,11,"Books");
insert into items(item_name,price,quantity,category) value("Clean Code",380.00,11,"Books");
insert into items(item_name,price,quantity,category) value("Design Principles",725.00,11,"Books");
insert into items(item_name,price,quantity,category) value("Mens Brown Shirt",1299.00,11,"Clothing");
insert into items(item_name,price,quantity,category) value("Mens Black Casual Trousers",1699.00,11,"Clothing");
insert into items(item_name,price,quantity,category) value("Mens Blue Shirt",1399.00,11,"Clothing");
insert into items(item_name,price,quantity,category) value("Mens White Formal Shirt",1499.00,11,"Clothing");
insert into items(item_name,price,quantity,category) value("Mens Blue Denim",1999.00,11,"Clothing");
insert into items(item_name,price,quantity,category) value("Mens White Tshirt",499.00,11,"Clothing");
insert into items(item_name,price,quantity,category) value("Puma Mens White Sports Shoes",999.00,11,"Footwear");
insert into items(item_name,price,quantity,category) value("Puma Mens Black Sports Shoes",2999.00,11,"Footwear");
insert into items(item_name,price,quantity,category) value("Reebok Mens Blue Sports Sandal",1999.00,11,"Footwear");
insert into items(item_name,price,quantity,category) value("Addidas Mens Grey Casual Shoes",1999.00,11,"Footwear");
insert into items(item_name,price,quantity,category) value("Converse Mens Olive Sneakers",1999.00,11,"Footwear");
insert into items(item_name,price,quantity,category) value("Levis Mens Blue Jeans",2499.00,11,"Footwear");
insert into items(item_name,price,quantity,category) value("Sandisk 8 GB Pendrive USB 2.0",499.00,11,"Computers");
insert into items(item_name,price,quantity,category) value("Dell USB Keyboard",899.00,11,"Computers");
insert into items(item_name,price,quantity,category) value("Dell 21 inch Monitor",6999.00,11,"Computers");
insert into items(item_name,price,quantity,category) value("Apple Macbook Pro 13 inch",69999.00,11,"Computers");
insert into items(item_name,price,quantity,category) value("Dell Inspiron laptop",49999.00,11,"Computers");
insert into items(item_name,price,quantity,category) value("Logitech Gaming Mouse",1999.00,11,"Computers");


create table orders(id int NOT NULL AUTO_INCREMENT, order_date DATE NOT NULL, user_id int NOT NULL, total_amount double(9,2) NOT NULL,  payment_type varchar(50) NOT NULL, PRIMARY KEY(id), FOREIGN KEY(user_id) REFERENCES users(id));

create table ordered_items(id int NOT NULL AUTO_INCREMENT, order_id int NOT NULL, item_id int NOT NULL, PRIMARY KEY(id), FOREIGN KEY(order_id) REFERENCES orders(id),  FOREIGN KEY(item_id) REFERENCES items(id));


select * from items;

select * from users;
select * from user_roles;

select * from orders;
select * from ordered_items;