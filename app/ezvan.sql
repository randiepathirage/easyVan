-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 22, 2021 at 03:03 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

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
  `date` date NOT NULL,
  `abesence_in_the_evening` tinyint(1) NOT NULL DEFAULT 1,
  `absence_in_the_morning` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `absence_date`
--

INSERT INTO `absence_date` (`child_no`, `date`, `abesence_in_the_evening`, `absence_in_the_morning`) VALUES
('2', '2021-03-23', 1, 0),
('3', '2021-03-27', 0, 1);

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
('333333333d', '986760294v', 'CBF-7375');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `child_no` int(10) NOT NULL,
  `date` date NOT NULL,
  `evening` tinyint(1) NOT NULL,
  `morning` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `child`
--

CREATE TABLE `child` (
  `child_no` int(10) NOT NULL,
  `parent_NIC_no` varchar(20) NOT NULL,
  `grade` varchar(5) NOT NULL,
  `school` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `pickup_location` varchar(50) NOT NULL,
  `dropoff_location` varchar(50) NOT NULL,
  `vehicle_no` varchar(11) DEFAULT NULL,
  `start_date` varchar(10) DEFAULT NULL,
  `fees` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `child`
--

INSERT INTO `child` (`child_no`, `parent_NIC_no`, `grade`, `school`, `first_name`, `last_name`, `pickup_location`, `dropoff_location`, `vehicle_no`, `start_date`, `fees`) VALUES
(2, '986760296v', '4', 'abc collage', 'mihisara', 'wijethunga', 'wrre', 'rerer', 'CBF-7375', 'NULL', '0.00'),
(3, '986760296v', '5', 'musaes collage', 'hemna', 'kithsaranie', 'homagama', 'homagama', 'DF-9864', NULL, NULL),
(4, '986760296v', '3', 'abc collage', 'devin', 'wijeshinghe', 'wallaatta', 'wallawatta', NULL, NULL, NULL),
(11, '986760296v', '5', 'afafas', 'fafafa', 'afafa', 'qwtjnb', 'gaba', NULL, NULL, NULL),
(12, '986760296v', '4', 'sdfs', 'sdsd', 'fsdfsdf', 'sdfsdgf', 'gdfgsg', NULL, NULL, '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_message`
--

CREATE TABLE `emergency_message` (
  `msg_no` int(10) NOT NULL,
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

--
-- Dumping data for table `fee`
--

INSERT INTO `fee` (`no`, `child_no`, `amount`, `paid_date`, `month`) VALUES
(2, 2, '1000', '2021-03-21', 'January'),
(3, 4, '600', '2021-03-21', 'fafafa'),
(4, 4, '600', '2021-03-21', 'fafafa');

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
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `vehicle_no` varchar(15) NOT NULL,
  `longitude` varchar(10) NOT NULL,
  `latitude` varchar(10) NOT NULL,
  `time` varchar(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`vehicle_no`, `longitude`, `latitude`, `time`, `date`) VALUES
('CBF-7375', '79.8611517', '6.902205', '10:17:26pm', '2021-03-21'),
('DF-9864', '80.0347', '6.8457126', '1.06 p.m', '2021-03-09');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `NIC_no` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`NIC_no`, `username`, `password`, `salt`, `email`) VALUES
('222222222o', 'owner1', '707707b910d1fbe56b3085b364464b1e75cc115edaebc37d4bcd01e416dfaff9', 'yN?8bSZGL29P9BRT7}`s', 'owner1@gmail.com'),
('3', 'driver', '255075bc0a379f78218746b6e8a32e913fa817480242bdf0cb025ea791b76047', 'G)J3ihYUBb3o0J&8$2KP', 'driver@gmail.com'),
('333333333d', 'driver1', '903823eb37fe0668026be76d19a7e831a03aa45d483843c4c616d6cc4b3a3596', '[7}e#7g#xXN(`?9)5GW6', 'driver1@gmail.com'),
('33765ere5v', 'resasdfvb', 'b71b87f50e23b389722d7e57e2234d3d658a842c25090473184b5e71d282b9ad', '}yeU+{~a(ZX8]/}_&Uk4', 'rfsvvcd'),
('986760296v', 'randie', '896672d5223046cf7ab868b06dbf983be9a248a3590f00fdfb2946424a231836', 'gn|bggFtG1Dn*xP}]HH4', 'randiepathirage@gmail.com'),
('98765ere5v', 'reeresasdfvb', '93368c037289956fff51a3b8b0aed53d1d893243182324f9d40f983b3b456c24', '%w)MHQ4P%(OH2cAp~!l%', 'rfgvvffsvvcd');

-- --------------------------------------------------------

--
-- Table structure for table `notify`
--

CREATE TABLE `notify` (
  `no` int(10) NOT NULL,
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
('222222222o', 0, 0, 1, 0, '0'),
('333333333d', 0, 1, 0, 0, '0'),
('33765ere5v', 0, 0, 1, 0, '0'),
('345p', 1, 0, 0, 0, '42252'),
('8675743p', 1, 0, 0, 0, ' 0'),
('986760294v', 0, 0, 1, 0, '0'),
('986760296v', 1, 0, 0, 0, '0');

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `parent_NIC` varchar(20) NOT NULL,
  `driver_NIC` varchar(20) NOT NULL,
  `review` varchar(100) NOT NULL,
  `time` varchar(11) NOT NULL,
  `date` varchar(11) NOT NULL,
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
  `child_no` int(100) NOT NULL,
  `vehicle_no` varchar(15) NOT NULL,
  `owner_NIC_no` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`req_id`, `status`, `parent_NIC_no`, `child_no`, `vehicle_no`, `owner_NIC_no`, `date`, `time`) VALUES
(5, 'accepted', '986760296v', 2, 'CBF-7375', '986760294v', '2021-03-17', '05:07:41pm'),
(15, 'pending', '986760296v', 2, 'CBF-7375', '986760294v', '2021-03-17', '07:06:23pm'),
(16, 'pending', '986760296v', 3, 'CBF-7375', '986760294v', '2021-03-19', '12:11:08am'),
(17, 'pending', '986760296v', 12, 'CBF-7375', '986760294v', '2021-03-21', '07:14:17pm');

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
('maharagama', 'CBF-7375'),
('maharagama', 'DF-9864'),
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
  `contact_no` int(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`NIC_no`, `contact_no`, `last_name`, `first_name`, `address`) VALUES
('222222222o', 712222222, 'owner1', 'owner1', 'qwertredfg'),
('3', 712345436, 'driver', 'driver', 'hethetgwlrt'),
('333333333d', 713333333, 'driver1', 'driver1', 'wewwrws'),
('33765ere5v', 432265456, 'A', 'A', 'A'),
('986760294v', 423535, 'pathirage', 'kulaathana', ';lkjuhygtrfedcvb '),
('986760296v', 713731752, 'Pathirage', 'randie', 'godagama  homagama');

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
('CBF-7375', 8, 20, 'nissan', 'van', '089654v', 1, 0, 'Malabe', '986760294v'),
('DF-9864', 10, 30, 'rosa', 'bus', '23v', 1, 0, 'Nugegoda', '8675743p'),
('GF-6754', 7, 15, 'nissan', 'van', '23v', 1, 0, 'koswatta', '345p'),
('HQ-3431', 10, 25, 'tata', 'bus', '089654v', 0, 1, 'Homagama', '8675743p');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absence_date`
--
ALTER TABLE `absence_date`
  ADD PRIMARY KEY (`child_no`,`date`);

--
-- Indexes for table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`driver_NIC_no`,`owner_NIC_no`,`vehicle_no`),
  ADD KEY `assign_ibfk_2` (`owner_NIC_no`),
  ADD KEY `assign_ibfk_3` (`vehicle_no`),
  ADD KEY `driver_NIC_no` (`driver_NIC_no`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`child_no`,`date`);

--
-- Indexes for table `child`
--
ALTER TABLE `child`
  ADD PRIMARY KEY (`child_no`,`parent_NIC_no`);

--
-- Indexes for table `emergency_message`
--
ALTER TABLE `emergency_message`
  ADD PRIMARY KEY (`msg_no`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`key_id`,`vehicle_no`);

--
-- Indexes for table `fee`
--
ALTER TABLE `fee`
  ADD PRIMARY KEY (`no`,`child_no`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`insurance_no`,`vehicle_no`);

--
-- Indexes for table `license`
--
ALTER TABLE `license`
  ADD PRIMARY KEY (`license_no`,`vehicle_no`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`vehicle_no`);

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
  ADD PRIMARY KEY (`no`);

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
  ADD KEY `vehicle_no` (`vehicle_no`),
  ADD KEY `child_no` (`child_no`);

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
  ADD PRIMARY KEY (`number`,`owner_NIC_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `child`
--
ALTER TABLE `child`
  MODIFY `child_no` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `emergency_message`
--
ALTER TABLE `emergency_message`
  MODIFY `msg_no` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `key_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `fee`
--
ALTER TABLE `fee`
  MODIFY `no` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `notify`
--
ALTER TABLE `notify`
  MODIFY `no` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `req_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `assign_ibfk_1` FOREIGN KEY (`driver_NIC_no`) REFERENCES `user` (`NIC_no`) ON UPDATE CASCADE,
  ADD CONSTRAINT `assign_ibfk_2` FOREIGN KEY (`owner_NIC_no`) REFERENCES `user` (`NIC_no`) ON UPDATE CASCADE,
  ADD CONSTRAINT `assign_ibfk_3` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON UPDATE CASCADE;

--
-- Constraints for table `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `expense_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`owner_NIC_no`) REFERENCES `user` (`NIC_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`vehicle_no`) REFERENCES `vehicle` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`parent_NIC_no`) REFERENCES `user` (`NIC_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_4` FOREIGN KEY (`child_no`) REFERENCES `child` (`child_no`) ON DELETE CASCADE ON UPDATE CASCADE;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
