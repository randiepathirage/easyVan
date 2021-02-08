-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2020 at 06:13 PM
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

--
-- Dumping data for table `assign`
--

INSERT INTO `assign` (`driver_NIC_no`, `owner_NIC_no`, `vehicle_no`) VALUES
('3', '2', 'DF-9864'),
('6760296v', '986760294v', 'CBF-7375');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `child_no` int(11) NOT NULL,
  `date` date NOT NULL,
  `evening` tinyint(1) NOT NULL,
  `morning` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `child`
--

CREATE TABLE `child` (
  `child_no` int(11) NOT NULL,
  `parent_NIC_no` varchar(20) NOT NULL,
  `grade` varchar(5) NOT NULL,
  `school` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `pickup_location` varchar(50) NOT NULL,
  `dropoff_location` varchar(50) NOT NULL,
  `vehicle_no` varchar(15) NOT NULL,
  `start_date` date NOT NULL,
  `monthly_fee` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `child`
--

INSERT INTO `child` (`child_no`, `parent_NIC_no`, `grade`, `school`, `first_name`, `last_name`, `pickup_location`, `dropoff_location`, `vehicle_no`, `start_date`, `monthly_fee`) VALUES
(1, '986760296v', '4', 'Royal collage', 'mihisara', 'wijethunga', 'homagama', 'homagama', 'CBF-7375', '2020-07-06', '5000.00'),
(2, '986760296v', '9', 'Musaeus collage', 'hemna', 'pathirage', 'meegoda', 'homagama', 'CBF-7375', '2019-09-09', '4500.00'),
(3, '4', '6', 'Devi balika', 'nethmi', 'perera', 'wallawatta', 'wallawatta', 'DF-9864', '2020-09-08', '3500.00');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_message`
--

CREATE TABLE `emergency_message` (
  `parent_NIC_no` varchar(20) NOT NULL,
  `driver_NIC_no` varchar(20) NOT NULL,
  `message` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL
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
-- Table structure for table `fee`
--

CREATE TABLE `fee` (
  `no` int(10) NOT NULL,
  `child_no` int(20) NOT NULL,
  `amount` decimal(50,0) NOT NULL,
  `paid_date` date NOT NULL,
  `month` varchar(50) NOT NULL
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
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`NIC_no`, `username`, `password`, `email`) VALUES
('1', 'admin', '202cb962ac59075b964b07152d234b70', 'admin@gmail.com'),
('2', 'owner', '202cb962ac59075b964b07152d234b70', 'owner@gmail.com'),
('3', 'driver', '202cb962ac59075b964b07152d234b70', 'driver@gmail.com'),
('34352v', 'erre', 'dfc0d64b587784fb2de6a4d14bede2a2', 'werhhgfd@gmail.com'),
('4', 'parent', '202cb962ac59075b964b07152d234b70', 'parent@gmail.com'),
('976760266v', 'devinda', 'd7a4272f8c32203354b040db6d4ffbb9', 'devinda@gmail.com'),
('9860546v', 'anuki', 'b1c301b0a40676b400ef4c417dcb94db', 'anuki@gmail.com'),
('986760294v', 'nimal', 'c177c76fa135dffda6d86ff076a8ddbb', 'nimal@gmail.com'),
('986760296v', 'randie', 'dfc0d64b587784fb2de6a4d14bede2a2', 'randiepathirage@gmail.com'),
('9876543v', 'sdfghj', ' Randie@123', ' oerere@gmail.com');

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
('1', 0, 0, 0, 1, NULL),
('2', 0, 0, 1, 0, NULL),
('3', 0, 1, 0, 0, NULL),
('34352v', 0, 0, 1, 0, '0'),
('345', 1, 0, 0, 0, '42252'),
('3453', 0, 0, 1, 0, '0'),
('4', 1, 0, 0, 0, NULL),
('5', 0, 0, 0, 1, '0'),
('6760296v', 0, 1, 0, 0, '45-8665'),
('8675743v', 1, 0, 0, 0, ' 0'),
('976760266v', 0, 0, 1, 0, '0'),
('9860546v', 0, 0, 1, 0, '0'),
('986760294v', 0, 0, 1, 0, '0'),
('986760296v', 1, 0, 0, 0, ' 0');

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `parent_NIC` varchar(20) NOT NULL,
  `driver_NIC` varchar(20) NOT NULL,
  `review` varchar(100) NOT NULL,
  `time` time(6) NOT NULL,
  `date` date NOT NULL,
  `rate` decimal(20,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

--
-- Dumping data for table `school`
--

INSERT INTO `school` (`school`, `vehicle_no`) VALUES
('Ananda collage', 'HQ-3431'),
('Devi balika', 'DF-9864'),
('Devi balika', 'PE-6544'),
('DS collage', 'CBF-7375'),
('Royal collage', 'CBF-7375'),
('Royal collage', 'DF-9864'),
('Royal collage', 'GF-6754');

-- --------------------------------------------------------

--
-- Table structure for table `town`
--

CREATE TABLE `town` (
  `town` varchar(50) NOT NULL,
  `vehicle_no` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `town`
--

INSERT INTO `town` (`town`, `vehicle_no`) VALUES
('bathtaramulla', 'DF-9864'),
('bathtaramulla', 'GF-6754'),
('kandana', 'PE-6544'),
('maharagama', 'CBF-7375'),
('maharagama', 'DF-9864'),
('meegamuwa', 'PE-6544'),
('meegoda', 'HQ-3431'),
('meepe', 'CBF-7375'),
('pannipitiya', 'HQ-3431'),
('rajagiriya', 'GF-6754');

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
('1', 713731752, 'wijethunga', 'edivid', 'maththegoda'),
('3', 423535, 'driver', 'driver', 'wqrqrccvrge'),
('34352v', 123456789, 'sdfsf', 'dssdf', 'wertyjkjhgfacv'),
('3453', 546784343, 'mihisara', 'akalanka', 'uytrew'),
('6760296v', 715554443, 'abethunga', 'jagath', 'madulawa,padukka'),
('976760266v', 712344322, 'kularathna', 'devinda', '23,madulawa ,padukka'),
('9860546v', 2147483647, 'perera', 'anuki', '61/2,palpolawatte,godagama'),
('986760294v', 712751254, 'perera', 'nimal', '61/2,palpolawatta,godagama'),
('986760296v', 713731752, 'pathirage', 'randie', '61,homagama,godagama');

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
  `AC_nonAC` tinyint(1) NOT NULL,
  `caretaker` tinyint(1) NOT NULL,
  `start_location` varchar(20) NOT NULL,
  `owner_NIC_no` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`number`, `no_of_seats_available`, `total_no_of_seats`, `model`, `type`, `permit_no`, `AC_nonAC`, `caretaker`, `start_location`, `owner_NIC_no`) VALUES
('CBF-7375', 8, 20, 'nissan', 'van', '089654v', 1, 0, 'Malabe', '986760296v'),
('DF-9864', 10, 30, 'rosa', 'bus', '23v', 1, 0, 'Nugegoda', '8675743v'),
('GF-6754', 7, 15, 'nissan', 'van', '23v', 1, 0, 'koswatta', '345'),
('HQ-3431', 10, 25, 'tata', 'bus', '089654v', 0, 1, 'Homagama', '8675743v'),
('PE-6544', 8, 25, 'tata', 'van', '3242V33', 1, 1, 'wattala', '5');

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
  ADD PRIMARY KEY (`owner_NIC_no`),
  ADD KEY `driver_NIC_no` (`driver_NIC_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`child_no`,`date`);

--
-- Indexes for table `child`
--
ALTER TABLE `child`
  ADD PRIMARY KEY (`child_no`,`parent_NIC_no`),
  ADD KEY `vehicle_no` (`vehicle_no`),
  ADD KEY `parent_NIC_no` (`parent_NIC_no`);

--
-- Indexes for table `emergency_message`
--
ALTER TABLE `emergency_message`
  ADD PRIMARY KEY (`parent_NIC_no`,`driver_NIC_no`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`key_id`,`vehicle_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `fee`
--
ALTER TABLE `fee`
  ADD PRIMARY KEY (`no`,`child_no`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`vehicle_no`),
  ADD KEY `vehicle_no` (`vehicle_no`);

--
-- Indexes for table `license`
--
ALTER TABLE `license`
  ADD PRIMARY KEY (`license_no`,`vehicle_no`),
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
-- Indexes for table `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`parent_NIC`,`driver_NIC`);

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
  ADD PRIMARY KEY (`number`,`owner_NIC_no`),
  ADD KEY `owner_NIC_no` (`owner_NIC_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `child`
--
ALTER TABLE `child`
  MODIFY `child_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `key_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `assign_ibfk_1` FOREIGN KEY (`driver_NIC_no`) REFERENCES `parent_owner_driver` (`NIC_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `assign_ibfk_2` FOREIGN KEY (`owner_NIC_no`) REFERENCES `parent_owner_driver` (`NIC_no`),
  ADD CONSTRAINT `assign_ibfk_3` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`);

--
-- Constraints for table `child`
--
ALTER TABLE `child`
  ADD CONSTRAINT `child_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON UPDATE CASCADE,
  ADD CONSTRAINT `child_ibfk_2` FOREIGN KEY (`parent_NIC_no`) REFERENCES `login` (`NIC_no`) ON UPDATE CASCADE;

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
