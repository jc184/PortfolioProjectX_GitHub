-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2017 at 11:49 AM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moravian`
--
CREATE DATABASE IF NOT EXISTS `moravian` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `moravian`;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `category_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_name`) VALUES
(1, 'Mountain Bikes'),
(2, 'Road Racers'),
(3, 'Head Gear'),
(4, 'Clothing'),
(5, 'Accessories'),
(6, 'Spare Parts');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `firstname` varchar(225) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `addressline1` varchar(225) NOT NULL,
  `addressline2` varchar(225) NOT NULL,
  `city` varchar(225) NOT NULL,
  `postcode` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `company` varchar(225) NOT NULL,
  `creditcardexpiry` varchar(225) NOT NULL,
  `creditcardnumber` varchar(225) NOT NULL,
  `creditcardtype` varchar(225) NOT NULL,
  `emailaddress` varchar(255) NOT NULL,
  `loginpassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `firstname`, `lastname`, `addressline1`, `addressline2`, `city`, `postcode`, `country`, `company`, `creditcardexpiry`, `creditcardnumber`, `creditcardtype`, `emailaddress`, `loginpassword`) VALUES
(1, 'john', 'smith', '1 new street', 'newington', 'Newtown', 'AB10 6QD', 'United Kingdom', 'JS & Co', '2019-09-09', '4111222233334444', 'VISA', 'john.smith@gmail.com', 'password');

-- --------------------------------------------------------

--
-- Table structure for table `customer_order`
--

DROP TABLE IF EXISTS `customer_order`;
CREATE TABLE `customer_order` (
  `id` int(11) UNSIGNED NOT NULL,
  `amount` decimal(6,2) NOT NULL,
  `orderdate` datetime NOT NULL,
  `confirmation_number` int(11) NOT NULL,
  `customer_customerid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ordered_product`
--

DROP TABLE IF EXISTS `ordered_product`;
CREATE TABLE `ordered_product` (
  `customer_order_id` int(11) UNSIGNED NOT NULL,
  `product_code` int(11) UNSIGNED NOT NULL,
  `quantity` smallint(4) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 KEY_BLOCK_SIZE=1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `code` int(11) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`code`, `name`, `price`, `description`, `category_id`) VALUES
(1, 'Puch Mountain Bike 1200', 200, 'Steel Framed 36-gear Mountain Bike', 1),
(2, 'Puch Road Racer 1000', 250, 'Aluminium Alloy Framed Road Racer', 2),
(3, 'Barracuda Cycle Helmet', 25, 'Polystyrene Body / Polyethylene Shell cycle helmet', 3),
(4, 'RayBan sun glasses', 37.5, 'Mirror glass plastic rayban sun glasses', 3),
(5, 'Berghaus Cycle Shorts', 27.5, 'Dark blue nylon Beghaus cycle shotrs', 4),
(6, 'Plastic Water Bottle', 8.5, 'Red Polythene Water Bottle', 5),
(7, 'Water Bottle holder', 16.75, 'Stainless Steel Wire Water bottle holder', 5),
(8, 'Gulf Bike Seat', 23.45, 'steel tubing / plastic seat', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_order`
--
ALTER TABLE `customer_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_customer1_idx` (`customer_customerid`);

--
-- Indexes for table `ordered_product`
--
ALTER TABLE `ordered_product`
  ADD PRIMARY KEY (`customer_order_id`,`product_code`),
  ADD KEY `fk_ordered_product_customer_order_idx` (`customer_order_id`),
  ADD KEY `fk_ordered_product_product_idx` (`product_code`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`code`),
  ADD KEY `fk_product_category1_idx` (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `customer_order`
--
ALTER TABLE `customer_order`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `code` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_order`
--
ALTER TABLE `customer_order`
  ADD CONSTRAINT `fk_order_customer1` FOREIGN KEY (`customer_customerid`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ordered_product`
--
ALTER TABLE `ordered_product`
  ADD CONSTRAINT `fk_ordered_product_customer_order` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ordered_product_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
