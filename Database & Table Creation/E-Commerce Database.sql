-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerceproject
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `categoryID` int NOT NULL,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Electronics'),(2,'Home Appliances'),(3,'Books'),(4,'Clothing'),(5,'Sports Equipment');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerID` int NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (202401,'Santos','Juan','09171234567','juan.santos@yipee.com','123 Rizal St.','Quezon City','Metro Manila','1101','Philippines'),(202402,'Reyes','Maria','09281234567','maria.reyes@geezmail.com','456 Mabini St.','Makati','Metro Manila','1200','Philippines'),(202403,'Cruz','Jose','09391234567','jose.cruz@geezmail.com','789 Bonifacio Ave.','Pasig','Metro Manila','1600','Philippines'),(202404,'Bautista','Ana','09181234568','ana.bautista@geezmail.com','101 San Juan St.','San Juan','Metro Manila','1500','Philippines'),(202405,'Garcia','Pedro','09291234568','pedro.garcia@geezmail.com','202 Del Pilar St.','Taguig','Metro Manila','1630','Philippines'),(202406,'Dela Cruz','Elena','09481234567','elena.delacruz@geezmail.com','303 Recto Ave.','Manila','Metro Manila','1000','Philippines'),(202407,'Ramos','Luis','09171234569','luis.ramos@geezmail.com','404 Aguinaldo Blvd.','Caloocan','Metro Manila','1400','Philippines'),(202408,'Villanueva','Carmen','09281234569','carmen.villanueva@yipee.com','505 Katipunan Rd.','Quezon City','Metro Manila','1105','Philippines'),(202409,'Lopez','Carlos','09391234569','carlos.lopez@yipee.com','606 EDSA','Mandaluyong','Metro Manila','1550','Philippines'),(202410,'Mendoza','Rosa','09181234570','rosa.mendoza@firemail.com','707 Ayala Ave.','Makati','Metro Manila','1226','Philippines'),(202411,'Aquino','Noli','09291234570','noli.aquino@yipee.com','808 Roxas Blvd.','Pasay','Metro Manila','1300','Philippines'),(202412,'Torres','Isabel','09481234568','isabel.torres@yipee.com','909 Osmena Hwy.','Manila','Metro Manila','1012','Philippines'),(202413,'Domingo','Jorge','09171234571','jorge.domingo@geezmail.com','111 Mabuhay St.','Marikina','Metro Manila','1800','Philippines'),(202414,'Gonzales','Marvin','09281234571','marvin.gonzales@geezmail.com','222 Commonwealth Ave.','Quezon City','Metro Manila','1121','Philippines'),(202415,'Fernandez','Lito','09391234571','lito.fernandez@geezmail.com','333 Shaw Blvd.','Mandaluyong','Metro Manila','1552','Philippines'),(202416,'Salazar','Daisy','09181234572','daisy.salazar@geezmail.com','444 Kalayaan Ave.','Taguig','Metro Manila','1631','Philippines'),(202417,'Silva','Antonio','09291234572','antonio.silva@geezmail.com','555 Taft Ave.','Manila','Metro Manila','1004','Philippines'),(202418,'Mercado','Diana','09481234569','diana.mercado@geezmail.com','666 Sucat Rd.','Paranaque','Metro Manila','1700','Philippines'),(202419,'Luna','Miguel','09171234573','miguel.luna@firemail.com','777 Alabang-Zapote Rd.','Las Pinas','Metro Manila','1740','Philippines'),(202420,'Rivera','Gloria','09281234573','gloria.rivera@firemail.com','888 Coastal Rd.','Bacoor','Cavite','4102','Philippines'),(202421,'Pascual','Eduardo','09391234573','eduardo.pascual@firemail.com','999 Zapote St.','Muntinlupa','Metro Manila','1770','Philippines'),(202422,'Soriano','Vince','09181234574','vince.soriano@yipee.com','1010 Marcos Hwy.','Antipolo','Rizal','1870','Philippines'),(202423,'Lim','Patricia','09291234574','patricia.lim@yipee.com','2020 Aurora Blvd.','Quezon City','Metro Manila','1113','Philippines'),(202424,'Chua','Henry','09481234570','henry.chua@yipee.com','3030 Pioneer St.','Mandaluyong','Metro Manila','1554','Philippines'),(202425,'Ocampo','Estela','09171234575','estela.ocampo@geezmail.com','4040 Macapagal Blvd.','Pasay','Metro Manila','1308','Philippines'),(202426,'Palma','Rafael','09281234575','rafael.palma@geezmail.com','5050 Banawe St.','Quezon City','Metro Manila','1104','Philippines'),(202427,'Perez','Clarissa','09391234575','clarissa.perez@geezmail.com','6060 Rizal Ave.','Manila','Metro Manila','1013','Philippines'),(202428,'Rosario','Sofia','09181234576','sofia.rosario@geezmail.com','7070 Quirino Ave.','Paranaque','Metro Manila','1702','Philippines'),(202429,'Aguilar','Fernando','09291234576','fernando.aguilar@geezmail.com','8080 McKinley Rd.','Taguig','Metro Manila','1632','Philippines'),(202430,'Balboa','Tomas','09481234571','tomas.balboa@geezmail.com','9090 Ninoy Aquino Ave.','Pasay','Metro Manila','1301','Philippines'),(202431,'Ortiz','Bella','09171234577','bella.ortiz@geezmail.com','10101 Timog Ave.','Quezon City','Metro Manila','1103','Philippines'),(202432,'Delos Santos','Lorena','09281234577','lorena.delossantos@geezmail.com','20202 Mindanao Ave.','Quezon City','Metro Manila','1116','Philippines'),(202433,'Tan','Vincent','09391234577','vincent.tan@geezmail.com','30303 P. Tuazon Blvd.','Cubao','Metro Manila','1109','Philippines'),(202434,'Garcia','Nina','09181234578','nina.garcia@geezmail.com','40404 Boni Ave.','Mandaluyong','Metro Manila','1555','Philippines'),(202435,'Roxas','Leon','09291234578','leon.roxas@geezmail.com','50505 Ortigas Ave.','San Juan','Metro Manila','1502','Philippines'),(202436,'Quiambao','Arlene','09481234572','arlene.quiambao@geezmail.com','60606 C5 Rd.','Taguig','Metro Manila','1634','Philippines'),(202437,'Zamora','Renato','09171234579','renato.zamora@yipee.com','70707 Commonwealth Ave.','Quezon City','Metro Manila','1115','Philippines'),(202438,'Santiago','Celia','09281234579','celia.santiago@yipee.com','80808 Katipunan Ext.','Quezon City','Metro Manila','1106','Philippines'),(202439,'Ramos','Felix','09391234579','felix.ramos@yipee.com','90909 Batasan Rd.','Quezon City','Metro Manila','1126','Philippines'),(202440,'Lagman','Minda','09181234580','minda.lagman@yipee.com','101010 Roxas St.','Manila','Metro Manila','1002','Philippines'),(202441,'Morales','Diego','09291234580','diego.morales@yipee.com','202020 Maginhawa St.','Quezon City','Metro Manila','1101','Philippines'),(202442,'Ponce','Tina','09481234573','tina.ponce@yipee.com','303030 Salcedo St.','Makati','Metro Manila','1227','Philippines'),(202443,'Gatchalian','Oscar','09171234581','oscar.gatchalian@yipee.com','404040 Tomas Morato','Quezon City','Metro Manila','1103','Philippines'),(202444,'Lorenzo','Esteban','09281234581','esteban.lorenzo@geezmail.com','505050 Legarda St.','Manila','Metro Manila','1008','Philippines'),(202445,'Tamayo','Carla','09391234581','carla.tamayo@yipee.com','606060 Yakal St.','Makati','Metro Manila','1203','Philippines'),(202446,'Villamor','Armando','09181234582','armando.villamor@geezmail.com','707070 Anonas St.','Quezon City','Metro Manila','1113','Philippines'),(202447,'Alvarez','Helena','09291234582','helena.alvarez@geezmail.com','808080 Boni Serrano','San Juan','Metro Manila','1504','Philippines'),(202448,'Martinez','Joel','09481234574','joel.martinez@geezmail.com','909090 Kalaw St.','Manila','Metro Manila','1000','Philippines'),(202449,'Navarro','Fely','09171234583','fely.navarro@geezmail.com','101010 Loyola St.','Marikina','Metro Manila','1801','Philippines'),(202450,'Reyes','Samuel','09281234583','samuel.reyes@geezmail.com','202020 Tandang Sora Ave.','Quezon City','Metro Manila','1116','Philippines');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discountID` int NOT NULL,
  `discount_name` varchar(100) NOT NULL,
  `description` text,
  `minim_spend` decimal(10,2) DEFAULT NULL,
  `discount_percent` decimal(5,2) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `paymentID` int DEFAULT NULL,
  PRIMARY KEY (`discountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (10001,'Holiday Sale','10% off on orders above 1000',1000.00,10.00,1,1001),(10002,'Welcome Discount','5% off for new users',500.00,5.00,1,1002),(10003,'Loyalty Discount','15% off for returning customers',2000.00,15.00,1,1004),(10004,'Flash Sale','20% off limited-time offer',500.00,20.00,0,1006),(10005,'Clearance Sale','30% off on clearance items',100.00,30.00,1,1010),(10006,'Birthday Bonus','Special 25% discount for birthdays',300.00,25.00,1,NULL),(10007,'Black Friday Deal','50% off sitewide',5000.00,50.00,1,1007),(10008,'Cyber Monday','40% discount on electronics',5000.00,40.00,0,1008),(10009,'Student Discount','10% off with student ID',200.00,10.00,1,1009),(10010,'End of Season','25% off clearance items',1000.00,25.00,1,NULL);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_itemsID` int NOT NULL,
  `orderID` int DEFAULT NULL,
  `productID` int DEFAULT NULL,
  `order_quantity` int NOT NULL,
  `item_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_itemsID`),
  KEY `orderID` (`orderID`),
  KEY `productID` (`productID`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderID`),
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (400001,300001,750936,3,7497.00),(400002,300002,705898,1,29999.00),(400003,300003,881948,2,3498.00),(400004,300004,309602,2,70000.00),(400005,300005,203495,1,7500.00),(400006,300006,263793,6,9000.00),(400007,300007,104510,1,20000.00),(400008,300008,265503,1,15000.00),(400009,300009,873939,3,1500.00),(400010,300010,693868,3,1050.00),(400011,300011,349933,1,1500.00),(400012,300012,772199,5,3000.00),(400013,300013,105655,2,300.00),(400014,300014,956988,2,2000.00),(400015,300015,567916,3,2400.00),(400016,300016,468303,3,4500.00),(400017,300017,327185,3,1800.00),(400018,300018,100636,3,2400.00),(400019,300019,931087,3,1500.00),(400020,300020,705898,1,75000.00);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderID` int NOT NULL,
  `order_total` decimal(10,2) NOT NULL,
  `customerID` int DEFAULT NULL,
  `paymentID` int DEFAULT NULL,
  `order_date` date NOT NULL,
  `productID` int DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `customerID` (`customerID`),
  KEY `paymentID` (`paymentID`),
  KEY `productID` (`productID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`paymentID`) REFERENCES `payment` (`paymentID`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (300001,7497.00,202401,1001,'2024-01-10',750936),(300002,29999.00,202402,1002,'2024-01-11',705898),(300003,3498.00,202403,1003,'2024-01-12',881948),(300004,70000.00,202404,1004,'2024-01-15',309602),(300005,7500.00,202405,1005,'2024-01-16',203495),(300006,9000.00,202406,1006,'2024-01-18',263793),(300007,20000.00,202407,1007,'2024-01-20',104510),(300008,15000.00,202408,1008,'2024-01-21',265503),(300009,1500.00,202409,1009,'2024-01-25',873939),(300010,1050.00,202410,1010,'2024-01-26',693868),(300011,1500.00,202411,1011,'2024-01-28',349933),(300012,3000.00,202412,1012,'2024-01-30',772199),(300013,300.00,202413,1013,'2024-02-01',105655),(300014,2000.00,202414,1014,'2024-02-03',956988),(300015,2400.00,202415,1015,'2024-02-05',567916),(300016,4500.00,202416,1016,'2024-02-06',468303),(300017,1800.00,202417,1017,'2024-02-08',327185),(300018,2400.00,202418,1018,'2024-02-10',100636),(300019,1500.00,202419,1019,'2024-02-12',931087),(300020,75000.00,202420,1020,'2024-02-15',705898);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `paymentID` int NOT NULL,
  `paymentmethodID` int DEFAULT NULL,
  `payment_date` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `discountID` int DEFAULT NULL,
  PRIMARY KEY (`paymentID`),
  KEY `paymentmethodID` (`paymentmethodID`),
  KEY `discountID` (`discountID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`paymentmethodID`) REFERENCES `payment_method` (`paymentmethodID`),
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`discountID`) REFERENCES `discount` (`discountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1001,1,'2024-11-01',2500.00,10001),(1002,2,'2024-11-02',1200.00,10002),(1003,3,'2024-11-03',500.00,NULL),(1004,4,'2024-11-04',3000.00,10003),(1005,5,'2024-11-05',800.00,NULL),(1006,6,'2024-11-06',1500.00,10004),(1007,1,'2024-11-07',10000.00,10007),(1008,5,'2024-11-08',2000.00,10008),(1009,4,'2024-11-09',250.00,10009),(1010,3,'2024-11-10',5000.00,10005),(1011,6,'2024-11-11',8000.00,NULL),(1012,1,'2024-11-12',4500.00,NULL),(1013,2,'2024-11-13',1200.00,10002),(1014,4,'2024-11-14',1500.00,NULL),(1015,5,'2024-11-15',3000.00,10006),(1016,3,'2024-11-16',250.00,NULL),(1017,2,'2024-11-17',7000.00,10007),(1018,4,'2024-11-18',800.00,NULL),(1019,5,'2024-11-19',6000.00,10008),(1020,1,'2024-11-20',1000.00,NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `paymentmethodID` int NOT NULL,
  `method_name` varchar(100) NOT NULL,
  PRIMARY KEY (`paymentmethodID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Credit Card'),(2,'Debit Card'),(3,'Cash on Delivery'),(4,'Bank Transfer'),(5,'GCash'),(6,'Installment Plan');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` int NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `categoryID` int DEFAULT NULL,
  `qty_instock` int NOT NULL,
  `EOQ` int NOT NULL,
  PRIMARY KEY (`productID`),
  KEY `categoryID` (`categoryID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (100636,'Basketball',800.00,5,350,60),(102414,'Gym Dumbbells Set',3500.00,5,50,10),(104510,'Very Big Refrigerator 300L',20000.00,2,40,5),(105655,'White T-Shirt Plain',150.00,4,1000,200),(105703,'Electric Kettle',1200.00,2,300,50),(126003,'Tennis Racket',3000.00,5,150,20),(203495,'Microwave Oven',6000.00,2,100,25),(263793,'NutriBlender Pro',1500.00,2,250,50),(265503,'Washing Machine',15000.00,2,35,8),(272238,'Shaolin Air Purifier',10000.00,2,90,10),(309602,'4K Smart TV',35000.00,1,60,10),(321489,'DyAnak Vacuum Cleaner',7000.00,2,80,15),(327185,'Soccer Ball',600.00,5,400,70),(349933,'Philippine History Textbook',1500.00,3,100,20),(415144,'Razor Gaming Keyboard',3500.00,1,120,25),(468303,'SUPLADO Jacket',1500.00,4,200,40),(537191,'Hiking Boots',4000.00,5,120,20),(567916,'Colorful Summer Dress',800.00,4,150,30),(602407,'Sonny Headphones Premium',5000.00,1,100,15),(693868,'Ant Cooking Recipe Book',350.00,3,300,60),(705898,'Aysus Laptop',49999.00,1,80,15),(750936,'Mansanas Smartphone XYZ',24999.00,1,150,20),(772199,'Sherlock Homey: The Mystery of Taft Avenue',600.00,3,150,40),(815881,'Edgar Allan POGO: The Series',2000.00,3,200,40),(873939,'Hari Pooter and The New Bilibid Prison',500.00,3,500,100),(881948,'Wireless Earbuds NC',2999.00,1,200,30),(892358,'Jay Bee El Bluetooth Speaker',2500.00,1,180,25),(917362,'Waterproof Raincoat',900.00,4,180,30),(931087,'Yoga Mat',500.00,5,500,100),(956988,'JAGGER Denim Jeans',1000.00,4,300,50);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order` (
  `POID` int NOT NULL,
  `productID` int DEFAULT NULL,
  `vendorID` int DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `PODetails` text,
  `PODate` date NOT NULL,
  PRIMARY KEY (`POID`),
  KEY `productID` (`productID`),
  KEY `vendorID` (`vendorID`),
  CONSTRAINT `purchase_order_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
  CONSTRAINT `purchase_order_ibfk_2` FOREIGN KEY (`vendorID`) REFERENCES `vendor` (`vendorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
INSERT INTO `purchase_order` VALUES (1,750936,1,3749850.00,'150x Initial stock for Mansanas Smartphone XYZ','2024-04-01'),(2,203495,2,600000.00,'100x Microwave Oven restock','2024-05-09'),(3,873939,3,250000.00,'500x Hari Pooter and The New Bilibid Prison bulk purchase','2024-07-04'),(4,105655,4,150000.00,'1,000x White T-Shirt Plain restock','2024-09-08'),(5,327185,5,240000.00,'400x Soccer Ball bulk purchase','2024-09-15'),(6,309602,1,2100000.00,'60x 4K Smart TV order','2024-09-21'),(7,104510,2,800000.00,'40x Very Big Refrigerator 300L new stock','2024-10-02'),(8,693868,3,105000.00,'300x Ant Cooking Recipe Book additional stock','2024-10-08'),(9,468303,4,300000.00,'200x SUPLADO Jacket Warm order for typhoon season','2024-10-24'),(10,100636,5,280000.00,'350x Basketball restock','2024-11-10'),(11,705898,1,3999920.00,'80x Bulk purchase of Aysus Laptop','2024-11-11'),(12,263793,2,375000.00,'250x NutriBlender Pro restock','2024-11-23'),(13,126003,5,450000.00,'150x Tennis Racket bulk purchase','2024-11-25'),(14,602407,1,500000.00,'100x Sonny Headphones Premium restock','2024-12-03'),(15,105703,2,360000.00,'300x Electric Kettle restock','2024-12-07');
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `reviewID` int NOT NULL,
  `customerID` int DEFAULT NULL,
  `order_itemsID` int DEFAULT NULL,
  `review_date` date NOT NULL,
  `rating` int DEFAULT NULL,
  PRIMARY KEY (`reviewID`),
  KEY `customerID` (`customerID`),
  KEY `order_itemsID` (`order_itemsID`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`order_itemsID`) REFERENCES `order_items` (`order_itemsID`),
  CONSTRAINT `review_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (500001,202401,400001,'2024-01-11',5),(500002,202402,400002,'2024-01-12',4),(500003,202403,400003,'2024-01-13',5),(500004,202404,400004,'2024-01-17',3),(500005,202405,400005,'2024-01-18',5),(500006,202406,400006,'2024-01-19',4),(500007,202407,400007,'2024-01-21',4),(500008,202408,400008,'2024-01-22',5),(500009,202409,400009,'2024-01-26',5),(500010,202410,400010,'2024-01-27',4),(500011,202411,400011,'2024-01-29',5),(500012,202412,400012,'2024-01-31',3),(500013,202413,400013,'2024-02-02',5),(500014,202414,400014,'2024-02-04',5),(500015,202415,400015,'2024-02-06',4),(500016,202416,400016,'2024-02-07',3),(500017,202417,400017,'2024-02-09',4),(500018,202418,400018,'2024-02-11',4),(500019,202419,400019,'2024-02-13',5),(500020,202420,400020,'2024-02-16',5);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor` (
  `vendorID` int NOT NULL,
  `vendor_name` varchar(255) NOT NULL,
  PRIMARY KEY (`vendorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (1,'SamSings Distributors'),(2,'Home Essentials Co.'),(3,'AKLAT Ltd.'),(4,'Fashionista Inc.'),(5,'Niek Sports Supplies');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-17 19:42:16
