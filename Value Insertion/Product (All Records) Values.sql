USE ECommerceProject;

INSERT INTO category (categoryID, category_name) VALUES
(1, 'Electronics'),
(2, 'Home Appliances'),
(3, 'Books'),
(4, 'Clothing'),
(5, 'Sports Equipment');

INSERT INTO vendor (vendorID, vendor_name) VALUES
(1, 'SamSings Distributors'),
(2, 'Home Essentials Co.'),
(3, 'AKLAT Ltd.'),
(4, 'Fashionista Inc.'),
(5, 'Niek Sports Supplies');

INSERT INTO product (productID, product_name, price, categoryID, qty_instock, EOQ) VALUES
(750936, 'Mansanas Smartphone XYZ', 24999.00, 1, 150, 20),
(705898, 'Aysus Laptop', 49999.00, 1, 80, 15),
(881948, 'Wireless Earbuds NC', 2999.00, 1, 200, 30),
(309602, '4K Smart TV', 35000.00, 1, 60, 10),
(203495, 'Microwave Oven', 6000.00, 2, 100, 25),
(263793, 'NutriBlender Pro', 1500.00, 2, 250, 50),
(104510, 'Very Big Refrigerator 300L', 20000.00, 2, 40, 5),
(265503, 'Washing Machine', 15000.00, 2, 35, 8),
(873939, 'Hari Pooter and The New Bilibid Prison', 500.00, 3, 500, 100),
(693868, 'Ant Cooking Recipe Book', 350.00, 3, 300, 60),
(349933, 'Philippine History Textbook', 1500.00, 3, 100, 20),
(772199, 'Sherlock Homey: The Mystery of Taft Avenue', 600.00, 3, 150, 40),
(105655, 'White T-Shirt Plain', 150.00, 4, 1000, 200),
(956988, 'JAGGER Denim Jeans', 1000.00, 4, 300, 50),
(567916, 'Colorful Summer Dress', 800.00, 4, 150, 30),
(468303, 'SUPLADO Jacket', 1500.00, 4, 200, 40),
(327185, 'Soccer Ball', 600.00, 5, 400, 70),
(100636, 'Basketball', 800.00, 5, 350, 60),
(931087, 'Yoga Mat', 500.00, 5, 500, 100),
(126003, 'Tennis Racket', 3000.00, 5, 150, 20),
(602407, 'Sonny Headphones Premium', 5000.00, 1, 100, 15),
(272238, 'Shaolin Air Purifier', 10000.00, 2, 90, 10),
(105703, 'Electric Kettle', 1200.00, 2, 300, 50),
(415144, 'Razor Gaming Keyboard', 3500.00, 1, 120, 25),
(815881, 'Edgar Allan POGO: The Series', 2000.00, 3, 200, 40),
(917362, 'Waterproof Raincoat', 900.00, 4, 180, 30),
(537191, 'Hiking Boots', 4000.00, 5, 120, 20),
(102414, 'Gym Dumbbells Set', 3500.00, 5, 50, 10),
(321489, 'DyAnak Vacuum Cleaner', 7000.00, 2, 80, 15),
(892358, 'Jay Bee El Bluetooth Speaker', 2500.00, 1, 180, 25);

INSERT INTO purchase_order (POID, productID, vendorID, total, PODetails, PODate) VALUES
(0001, 750936, 1, 3749850.00, '150x Initial stock for Mansanas Smartphone XYZ', '2024-04-01'),
(0002, 203495, 2, 600000.00, '100x Microwave Oven restock', '2024-05-09'),
(0003, 873939, 3, 250000.00, '500x Hari Pooter and The New Bilibid Prison bulk purchase', '2024-07-04'),
(0004, 105655, 4, 150000.00, '1,000x White T-Shirt Plain restock', '2024-09-08'),
(0005, 327185, 5, 240000.00, '400x Soccer Ball bulk purchase', '2024-09-15'),
(0006, 309602, 1, 2100000.00, '60x 4K Smart TV order', '2024-09-21'),
(0007, 104510, 2, 800000.00, '40x Very Big Refrigerator 300L new stock', '2024-10-02'),
(0008, 693868, 3, 105000.00, '300x Ant Cooking Recipe Book additional stock', '2024-10-08'),
(0009, 468303, 4, 300000.00, '200x SUPLADO Jacket Warm order for typhoon season', '2024-10-24'),
(0010, 100636, 5, 280000.00, '350x Basketball restock', '2024-11-10'),
(0011, 705898, 1, 3999920.00, '80x Bulk purchase of Aysus Laptop', '2024-11-11'),
(0012, 263793, 2, 375000.00, '250x NutriBlender Pro restock', '2024-11-23'),
(0013, 126003, 5, 450000.00, '150x Tennis Racket bulk purchase', '2024-11-25'),
(0014, 602407, 1, 500000.00, '100x Sonny Headphones Premium restock', '2024-12-03'),
(0015, 105703, 2, 360000.00, '300x Electric Kettle restock', '2024-12-07');