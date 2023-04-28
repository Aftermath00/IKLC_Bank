-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2023 at 05:24 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank_account`
--

CREATE TABLE `bank_account` (
  `customerId` int(11) NOT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `accountNumber` int(11) DEFAULT NULL,
  `balance` decimal(10,0) DEFAULT NULL,
  `registrationDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `motherName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bank_account`
--

INSERT INTO `bank_account` (`customerId`, `customerName`, `accountNumber`, `balance`, `registrationDate`, `motherName`) VALUES
(3, 'Rizky Azmi Swandy', 475561, '2328547', '2023-04-27 10:35:01', 'Endang Wahyuni'),
(4, 'Alif Prasetyo Wibowo', 855521, '3721453', '2023-04-27 10:52:14', 'Sumiati'),
(5, 'Abdillah', 445900, '500000', '2023-04-28 06:11:54', 'Sulistiawati');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionId` int(11) NOT NULL,
  `transactionDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `senderAccount` int(11) DEFAULT NULL,
  `transferAmount` decimal(10,0) DEFAULT NULL,
  `recipientAccount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transactionId`, `transactionDate`, `senderAccount`, `transferAmount`, `recipientAccount`) VALUES
(1, '2023-04-27 18:09:40', 475561, '21453', 855521),
(2, '2023-04-28 06:15:43', 445900, '1000000', 475561),
(3, '2023-04-28 14:31:46', 475561, '1000000', 855521),
(4, '2023-04-28 14:48:28', 445900, '500000', 475561),
(5, '2023-04-28 14:51:59', 475561, '2000000', 475561),
(6, '2023-04-28 14:52:23', 475561, '-500000', 475561),
(7, '2023-04-28 14:53:56', 475561, '-100000', 475561);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank_account`
--
ALTER TABLE `bank_account`
  ADD PRIMARY KEY (`customerId`),
  ADD UNIQUE KEY `accountNumber` (`accountNumber`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bank_account`
--
ALTER TABLE `bank_account`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
