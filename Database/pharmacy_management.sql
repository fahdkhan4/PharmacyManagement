-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220616.7a6bd9eb57
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2022 at 05:45 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pharmacy_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE `adminlogin` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`username`, `password`) VALUES
('abc', 'abc1'),
('fahd', 'fahd1');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `product_barcode` int(11) DEFAULT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `product_varient` varchar(50) DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `price_unit` double DEFAULT NULL,
  `product_qty` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `product_barcode`, `product_name`, `product_varient`, `product_price`, `price_unit`, `product_qty`, `order_id`) VALUES
(3, 66666666, 'flexo', '200mg', 1200, 4800.044, 4, 2),
(4, 555555555, 'panadol', '22', 250, 500, 2, 2),
(5, 66666666, 'flexo', '200mg', 1200, 4800.044, 4, 3),
(6, 555555555, 'panadol', '22', 250, 250, 1, 3),
(7, 555555555, 'panadol', '22', 250, 1250, 5, 4),
(8, 66666666, 'flexo', '200mg', 1200, 4800.044, 4, 4),
(11, 66666666, 'flexo', '200mg', 1200, 4800.044, 4, 6),
(16, 555555555, 'panadol', '22', 250, 500, 2, 10),
(17, 66666666, 'flexo', '200mg', 1200, 4800.044, 4, 10),
(20, 555555555, 'panadol', '22', 250, 250, 1, 13),
(21, 7888888, 'ogmentan', '120mg', 350, 700, 2, 13),
(25, 66666666, 'flexo', '200mg', 1200, 2400, 2, 16);

-- --------------------------------------------------------

--
-- Table structure for table `employeelogin`
--

CREATE TABLE `employeelogin` (
  `employeeName` varchar(25) NOT NULL,
  `emp_password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employeelogin`
--

INSERT INTO `employeelogin` (`employeeName`, `emp_password`) VALUES
('emp', 'emp1'),
('fahd', 'fahd1');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `emp_name` varchar(25) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id`, `order_id`, `emp_name`, `invoice_date`) VALUES
(1, 2, 'fahd', '2022-04-24'),
(2, 3, 'abc', '2022-05-10'),
(3, 4, 'fahd', '2022-05-10'),
(4, 6, 'fahd', '2022-06-28'),
(5, 10, 'emp', '2022-08-21'),
(6, 16, 'emp', '2022-08-29');

-- --------------------------------------------------------

--
-- Table structure for table `invoice_line`
--

CREATE TABLE `invoice_line` (
  `invoice_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `item_varient` varchar(50) DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `itemQty_price` double DEFAULT NULL,
  `item_qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice_line`
--

INSERT INTO `invoice_line` (`invoice_id`, `order_id`, `item_name`, `item_varient`, `product_price`, `itemQty_price`, `item_qty`) VALUES
(1, 2, 'flexo', '200mg', 1200, 4800, 4),
(1, 2, 'panadol', '22', 250, 500, 2),
(2, 3, 'flexo', '200mg', 1200, 1200, 1),
(2, 3, 'panadol', '22', 250, 250, 1),
(3, 4, 'panadol', '22', 250, 1250, 5),
(3, 4, 'flexo', '200mg', 1200, 2400, 2),
(4, 6, 'flexo', '200mg', 1200, 1200, 1),
(5, 10, 'panadol', '22', 250, 500, 2),
(5, 10, 'flexo', '200mg', 1200, 3600, 3),
(6, 16, 'flexo', '200mg', 1200, 2400, 2);

-- --------------------------------------------------------

--
-- Table structure for table `productorder`
--

CREATE TABLE `productorder` (
  `id` int(11) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `productorder`
--

INSERT INTO `productorder` (`id`, `user_name`, `order_date`, `state`) VALUES
(2, 'fahd', '2022-04-24', 'Completed'),
(3, 'abc', '2022-05-10', 'Completed'),
(4, 'fahd', '2022-05-10', 'Completed'),
(6, 'fahd', '2022-06-28', 'Completed'),
(10, 'emp', '2022-08-21', 'Completed'),
(11, 'emp', '2022-08-21', 'Draft'),
(12, 'fahd', '2022-08-27', 'Draft'),
(13, 'emp', '2022-08-29', 'Draft'),
(15, 'emp', '2022-08-29', 'Draft'),
(16, 'emp', '2022-08-29', 'Completed');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `barcode` int(11) NOT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `product_varient` varchar(50) DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `sell_price` double DEFAULT NULL,
  `product_qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`barcode`, `product_name`, `product_varient`, `cost_price`, `sell_price`, `product_qty`) VALUES
(232323, 'hajmola', '120mg', 10, 12, 12),
(666666, 'sweet medicine', '120mg', 500, 550, 20),
(999999, 'strapzels', '100gm', 20, 30, 200),
(7888888, 'ogmentan', '120mg', 300, 350, 8),
(8888888, 'panadol-extra', '200mg', 500, 550, 20),
(66666666, 'flexo', '200mg', 1000, 1200, 8),
(555555555, 'panadol', '22', 200, 250, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `emp_name` varchar(25) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `sell_price` double DEFAULT NULL,
  `profit` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`id`, `order_id`, `emp_name`, `order_date`, `cost_price`, `sell_price`, `profit`) VALUES
(1, 2, 'fahd', '2022-04-24', 4400, 5300, 900),
(2, 2, 'fahd', '2022-04-24', 4400, 5300, 900),
(3, 3, 'abc', '2022-05-10', 1200, 1450, 250),
(5, 2, 'fahd', '2022-04-24', 4400, 5300, 900),
(6, 3, 'abc', '2022-05-10', 1200, 1450, 250),
(7, 4, 'fahd', '2022-05-10', 3000, 3650, 650),
(8, 2, 'fahd', '2022-04-24', 4400, 5300, 900),
(9, 3, 'abc', '2022-05-10', 1200, 1450, 250),
(10, 4, 'fahd', '2022-05-10', 3000, 3650, 650),
(11, 6, 'fahd', '2022-06-28', 1000, 1200, 200),
(12, 2, 'fahd', '2022-04-24', 4400, 5300, 900),
(13, 3, 'abc', '2022-05-10', 1200, 1450, 250),
(14, 4, 'fahd', '2022-05-10', 3000, 3650, 650),
(15, 6, 'fahd', '2022-06-28', 1000, 1200, 200),
(16, 10, 'emp', '2022-08-21', 3400, 4100, 700),
(17, 2, 'fahd', '2022-04-24', 4400, 5300.044, 900.0439999999999),
(18, 3, 'abc', '2022-05-10', 4200, 5050.044, 850.0439999999999),
(19, 4, 'fahd', '2022-05-10', 5000, 6050.044, 1050.0439999999999),
(20, 6, 'fahd', '2022-06-28', 4000, 4800.044, 800.0439999999999),
(21, 10, 'emp', '2022-08-21', 4400, 5300.044, 900.0439999999999),
(22, 16, 'emp', '2022-08-29', 2000, 2400, 400);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adminlogin`
--
ALTER TABLE `adminlogin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_barcode` (`product_barcode`);

--
-- Indexes for table `employeelogin`
--
ALTER TABLE `employeelogin`
  ADD PRIMARY KEY (`employeeName`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `invoice_line`
--
ALTER TABLE `invoice_line`
  ADD KEY `invoice_id` (`invoice_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `productorder`
--
ALTER TABLE `productorder`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`barcode`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `productorder`
--
ALTER TABLE `productorder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `productorder` (`id`),
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_barcode`) REFERENCES `products` (`barcode`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `productorder` (`id`);

--
-- Constraints for table `invoice_line`
--
ALTER TABLE `invoice_line`
  ADD CONSTRAINT `invoice_line_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  ADD CONSTRAINT `invoice_line_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `productorder` (`id`);

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `productorder` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



