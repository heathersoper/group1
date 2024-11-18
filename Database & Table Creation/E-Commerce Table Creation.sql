CREATE DATABASE ECommerceProject;

USE ECommerceProject;

CREATE TABLE category (
    categoryID 		INT PRIMARY KEY,
    category_name 	VARCHAR(255) NOT NULL
);

CREATE TABLE vendor (
    vendorID 		INT PRIMARY KEY,
    vendor_name 	VARCHAR(255) NOT NULL
);

CREATE TABLE payment_method (
    paymentmethodID INT PRIMARY KEY,
    method_name 	VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    productID 		INT PRIMARY KEY,
    product_name 	VARCHAR(255) NOT NULL,
    price 			DECIMAL(10, 2) NOT NULL,
    categoryID 		INT,
    qty_instock 	INT NOT NULL,
    EOQ 			INT NOT NULL,
    FOREIGN KEY (categoryID) REFERENCES category(categoryID)
);

CREATE TABLE discount (
    discountID 		INT PRIMARY KEY,
    discount_name 	VARCHAR(100) NOT NULL,
    description 	TEXT,
    minim_spend 	DECIMAL(10, 2),
    discount_percent DECIMAL(5, 2),
    active 			BOOLEAN,
    paymentID 		INT
);

CREATE TABLE customer (
    customerID 		INT PRIMARY KEY,
    last_name 		VARCHAR(100) NOT NULL,
    first_name 		VARCHAR(100) NOT NULL,
    phone_number 	VARCHAR(20),
    email 			VARCHAR(100) UNIQUE,
    street 			VARCHAR(255),
    city 			VARCHAR(100),
    state 			VARCHAR(50),
    postal_code 	VARCHAR(20),
    country 		VARCHAR(50)
);

CREATE TABLE payment (
    paymentID 		INT PRIMARY KEY,
    paymentmethodID INT,
    payment_date 	DATE NOT NULL,
    amount 			DECIMAL(10, 2) NOT NULL,
    discountID 		INT,
    FOREIGN KEY (paymentmethodID) REFERENCES payment_method(paymentmethodID),
    FOREIGN KEY (discountID) REFERENCES discount(discountID)
);

CREATE TABLE purchase_order (
    POID 			INT PRIMARY KEY,
    productID 		INT,
    vendorID 		INT,
    total 			DECIMAL(10, 2) NOT NULL,
    PODetails 		TEXT,
    PODate 			DATE NOT NULL,
    FOREIGN KEY (productID) REFERENCES product(productID),
    FOREIGN KEY (vendorID) REFERENCES vendor(vendorID)
);

CREATE TABLE orders (
    orderID 		INT PRIMARY KEY,
    order_total 	DECIMAL(10, 2) NOT NULL,
    customerID 		INT,
    paymentID 		INT,
    order_date 		DATE NOT NULL,
    productID 		INT,
    FOREIGN KEY (customerID) REFERENCES customer(customerID),
    FOREIGN KEY (paymentID) REFERENCES payment(paymentID),
    FOREIGN KEY (productID) REFERENCES product(productID)
);



CREATE TABLE order_items (
    order_itemsID 	INT PRIMARY KEY,
    orderID 		INT,
    productID 		INT,
    order_quantity 	INT NOT NULL,
    item_total 		DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (orderID) REFERENCES orders(orderID),
    FOREIGN KEY (productID) REFERENCES product(productID)
);

CREATE TABLE review (
    reviewID 		INT PRIMARY KEY,
    customerID 		INT,
    order_itemsID 	INT,
    review_date 	DATE NOT NULL,
    rating 			INT CHECK (rating BETWEEN 1 AND 5),
    FOREIGN KEY (customerID) REFERENCES customer(customerID),
    FOREIGN KEY (order_itemsID) REFERENCES order_items(order_itemsID)
);
