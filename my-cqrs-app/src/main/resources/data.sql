DROP TABLE IF EXISTS Order_Summary;
DROP TABLE IF EXISTS Product_Summary;
 
CREATE TABLE Product_Summary (
  id VARCHAR(250) AUTO_INCREMENT  PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
  price DOUBLE  default 0.0,
  stock INTEGER DEFAULT 0
);

CREATE TABLE Order_Summary (
  id VARCHAR(250) AUTO_INCREMENT  PRIMARY KEY,
  product_id VARCHAR(250) NOT NULL,
  price DOUBLE DEFAULT 0.0,
  number INTEGER DEFAULT 0
);


insert into Product_Summary(id, description, price, stock) values('1', 'Woman shoes', '50.3', 100);
insert into Product_Summary(id, description, price, stock) values('2', 'Sports wears', '100.5', 100);
insert into Product_Summary(id, description, price, stock) values('3', 'KTG Snickers', '80.50', 100);
insert into Product_Summary(id, description, price, stock) values('4', 'KTG Winter Coat', '100.5', 100);