-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2020 at 07:20 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ezvan`
--
CREATE DATABASE IF NOT EXISTS `ezvan` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ezvan`;

-- --------------------------------------------------------

--
-- Table structure for table `absence_date`
--

CREATE TABLE `absence_date` (
  `child_no` varchar(10) NOT NULL,
  `parent_NIC_no` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `abesence_in_the_evening` tinyint(1) NOT NULL,
  `absence_in_the_morning` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `absence_date`:
--

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE `assign` (
  `driver_NIC_no` varchar(10) NOT NULL,
  `owner_NIC_no` varchar(10) NOT NULL,
  `vehicle_no` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `assign`:
--

-- --------------------------------------------------------

--
-- Table structure for table `child`
--

CREATE TABLE `child` (
  `child_no` varchar(10) NOT NULL,
  `parent_NIC_no` varchar(10) NOT NULL,
  `grade` varchar(5) NOT NULL,
  `school` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `pickup_location` varchar(50) NOT NULL,
  `dropoff_location` varchar(50) NOT NULL,
  `vehicle_no` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `child`:
--   `vehicle_no`
--       `vehicle` -> `number`
--

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

CREATE TABLE `expense` (
  `key_id` int(11) NOT NULL,
  `amount` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(50) NOT NULL,
  `vehicle_no` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `expense`:
--   `vehicle_no`
--       `vehicle` -> `number`
--

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE `insurance` (
  `insurance_no` varchar(20) NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `policy_no` varchar(20) NOT NULL,
  `vehicle_no` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `insurance`:
--   `vehicle_no`
--       `vehicle` -> `number`
--

-- --------------------------------------------------------

--
-- Table structure for table `license`
--

CREATE TABLE `license` (
  `license_no` varchar(20) NOT NULL,
  `renewal_fee` varchar(5) NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `vehicle_no` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `license`:
--   `vehicle_no`
--       `vehicle` -> `number`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `NIC_no` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `login`:
--

-- --------------------------------------------------------

--
-- Table structure for table `notify`
--

CREATE TABLE `notify` (
  `parent_NIC_no` varchar(10) NOT NULL,
  `driver_NIC_no` varchar(10) NOT NULL,
  `message` int(100) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `notify`:
--

-- --------------------------------------------------------

--
-- Table structure for table `parent_owner_driver`
--

CREATE TABLE `parent_owner_driver` (
  `NIC_no` varchar(10) NOT NULL,
  `parent_flag` tinyint(1) NOT NULL,
  `driver_flag` tinyint(1) NOT NULL,
  `owner_flag` tinyint(1) NOT NULL,
  `license_no` varchar(20) DEFAULT NULL,
  `location` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `parent_owner_driver`:
--

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `req_id` int(10) NOT NULL,
  `status` varchar(15) NOT NULL,
  `parent_NIC_no` varchar(10) NOT NULL,
  `vehicle_no` varchar(15) NOT NULL,
  `owner_NIC_no` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `request`:
--   `owner_NIC_no`
--       `parent_owner_driver` -> `NIC_no`
--   `vehicle_no`
--       `vehicle` -> `number`
--   `parent_NIC_no`
--       `parent_owner_driver` -> `NIC_no`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `NIC_no` varchar(10) NOT NULL,
  `contact_no` int(10) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `middle_name` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `user`:
--

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `NIC_no` varchar(10) NOT NULL,
  `user_role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `user_role`:
--

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `number` varchar(15) NOT NULL,
  `no_of_seats_available` int(5) NOT NULL,
  `total_no_of_seats` int(5) NOT NULL,
  `model` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `permit_no` varchar(20) NOT NULL,
  `A/C_non-A/C` tinyint(1) NOT NULL,
  `caretaker` tinyint(1) NOT NULL,
  `owner_NIC_no` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONSHIPS FOR TABLE `vehicle`:
--   `owner_NIC_no`
--       `parent_owner_driver` -> `NIC_no`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absence_date`
--
ALTER TABLE `absence_date`
  ADD PRIMARY KEY (`child_no`,`parent_NIC_no`);

--
-- Indexes for table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`driver_NIC_no`,`owner_NIC_no`,`vehicle_no`);

--
-- Indexes for table `child`
--
ALTER TABLE `child`
  ADD PRIMARY KEY (`parent_NIC_no`,`child_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`key_id`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`insurance_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `license`
--
ALTER TABLE `license`
  ADD PRIMARY KEY (`license_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`NIC_no`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `notify`
--
ALTER TABLE `notify`
  ADD PRIMARY KEY (`parent_NIC_no`,`driver_NIC_no`);

--
-- Indexes for table `parent_owner_driver`
--
ALTER TABLE `parent_owner_driver`
  ADD PRIMARY KEY (`NIC_no`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`req_id`),
  ADD KEY `parent_NIC_no` (`parent_NIC_no`,`vehicle_no`,`owner_NIC_no`),
  ADD KEY `owner_NIC_no` (`owner_NIC_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`NIC_no`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`NIC_no`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`number`),
  ADD KEY `owner_NIC_no` (`owner_NIC_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `key_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `child`
--
ALTER TABLE `child`
  ADD CONSTRAINT `child_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON UPDATE CASCADE;

--
-- Constraints for table `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `expense_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON UPDATE CASCADE;

--
-- Constraints for table `insurance`
--
ALTER TABLE `insurance`
  ADD CONSTRAINT `insurance_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON UPDATE CASCADE;

--
-- Constraints for table `license`
--
ALTER TABLE `license`
  ADD CONSTRAINT `license_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`owner_NIC_no`) REFERENCES `parent_owner_driver` (`NIC_no`) ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`parent_NIC_no`) REFERENCES `parent_owner_driver` (`NIC_no`) ON UPDATE CASCADE;

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`owner_NIC_no`) REFERENCES `parent_owner_driver` (`NIC_no`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
