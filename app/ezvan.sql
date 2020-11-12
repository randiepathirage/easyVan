-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2020 at 02:43 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE `assign` (
  `driver_NIC_no` varchar(10) NOT NULL,
  `owner_NIC_no` varchar(10) NOT NULL,
  `vehicle_no` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `vehicle_no` varchar(15) NOT NULL,
  `start_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Table structure for table `license`
--

CREATE TABLE `license` (
  `license_no` varchar(20) NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `vehicle_no` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `NIC_no` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`NIC_no`, `username`, `password`, `email`) VALUES
('345', 'akalanka', '554ee7797d4b8e3958a7049a5681a742', 'aky@gmail.com'),
('3453', 'akaldanka', '0775a3feb59a9ea5ef65b39aa5a68a16', 'wewra@gmail.com'),
('986760296v', 'randie', 'dfc0d64b587784fb2de6a4d14bede2a2', 'randiepathirage@gmai');

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

-- --------------------------------------------------------

--
-- Table structure for table `parent_owner_driver`
--

CREATE TABLE `parent_owner_driver` (
  `NIC_no` varchar(10) NOT NULL,
  `parent_flag` tinyint(1) NOT NULL,
  `driver_flag` tinyint(1) NOT NULL,
  `owner_flag` tinyint(1) NOT NULL,
  `admin_flag` tinyint(1) NOT NULL,
  `license_no` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parent_owner_driver`
--

INSERT INTO `parent_owner_driver` (`NIC_no`, `parent_flag`, `driver_flag`, `owner_flag`, `admin_flag`, `license_no`) VALUES
('345', 1, 0, 0, 0, '0'),
('3453', 0, 0, 1, 0, '0'),
('8675743v', 1, 0, 0, 0, ' 0'),
('986760296v', 1, 0, 0, 0, ' 0');

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

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE `school` (
  `school` varchar(50) NOT NULL,
  `vehicle_no` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `town`
--

CREATE TABLE `town` (
  `town` varchar(50) NOT NULL,
  `vehicle_no` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `NIC_no` varchar(10) NOT NULL,
  `contact_no` int(10) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`NIC_no`, `contact_no`, `last_name`, `first_name`, `address`) VALUES
('345', 54321, 'mihisara', 'akalanka', 'ertyujkjnhbgvfc'),
('3453', 546784343, 'mihisara', 'akalanka', 'uytrew'),
('986760296v', 714446662, 'pathirage', 'randie', 'homagama,godagama\n');

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
  `start_location` varchar(300) NOT NULL,
  `owner_NIC_no` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`number`, `no_of_seats_available`, `total_no_of_seats`, `model`, `type`, `permit_no`, `A/C_non-A/C`, `caretaker`, `start_location`, `owner_NIC_no`) VALUES
('CBF-7375', 8, 20, 'nissan', 'van', '089654v', 1, 0, '', '986760296v'),
('DF-9864', 10, 30, 'rosa', 'bus', '23v', 1, 0, '', '8675743v');

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
-- Indexes for table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`school`,`vehicle_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `town`
--
ALTER TABLE `town`
  ADD PRIMARY KEY (`town`,`vehicle_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
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
-- Constraints for table `school`
--
ALTER TABLE `school`
  ADD CONSTRAINT `school_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `town`
--
ALTER TABLE `town`
  ADD CONSTRAINT `town_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`owner_NIC_no`) REFERENCES `parent_owner_driver` (`NIC_no`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
