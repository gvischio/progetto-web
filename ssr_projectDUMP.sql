-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Set 08, 2019 alle 21:05
-- Versione del server: 10.3.15-MariaDB-1
-- Versione PHP: 7.3.4-2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ssr_project`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `category`
--

CREATE TABLE `category` (
  `id_categ` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `category`
--

INSERT INTO `category` (`id_categ`, `name`) VALUES
(2, 'Esami generali e specialistici'),
(3, 'Biologia molecolare'),
(4, 'Oncologia molecolare'),
(5, 'Genetica molecolare'),
(6, 'Anatomia ed Istologia Patologica'),
(7, 'Prelievi'),
(8, 'Esami radiologici apparato cardiocircolatorio'),
(9, 'Esami radiologici apparato digerente'),
(10, 'Esami radiologici apparato genitale femminile'),
(11, 'Esami radiologici apparato osteoarticolare e tessuti molli'),
(12, 'Ecografia - Doppler - Ecodoppler - Colordoppler'),
(13, 'Densitometria mineralometria ossea MOC'),
(14, 'Tomografia computerizzata TAC'),
(15, 'Risonanza magnetica'),
(16, 'Medicina nucleare apparato cardiocircolatorio'),
(17, 'Medicina nucleare sistema nervoso'),
(18, 'Medicina nucleare traccianti immunologici'),
(19, 'Diagnostica strumentale cardiologia'),
(20, 'Diagnostica strumentale gastroenterologia'),
(21, 'Diagnostica strumentale ginecologia e ostetricia'),
(22, 'Prestazioni in gravidanza');

-- --------------------------------------------------------

--
-- Struttura della tabella `chs`
--

CREATE TABLE `chs` (
  `id_chs` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `cdistrict` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `chs`
--

INSERT INTO `chs` (`id_chs`, `name`, `cdistrict`) VALUES
(1, 'NULL', 1),
(107, 'ULSS di Alessandria', 2),
(108, 'ULSS di Asti', 6),
(109, 'ULSS di Belluno', 10),
(110, 'ULSS di Bergamo', 12),
(111, 'ULSS di Biella', 13),
(112, 'ULSS di Bologna', 14),
(113, 'ULSS di Bolzano', 15),
(114, 'ULSS di Brescia', 16),
(115, 'ULSS di Como', 26),
(116, 'ULSS di Cremona', 28),
(117, 'ULSS di Cuneo', 30),
(118, 'ULSS di Ferrara', 33),
(119, 'ULSS di Lecco', 47),
(120, 'ULSS di Lodi', 49),
(121, 'ULSS di Mantova', 52),
(122, 'ULSS di Milano', 57),
(123, 'ULSS di Modena', 58),
(124, 'ULSS di Monza e Brianza', 59),
(125, 'ULSS di Novara', 61),
(126, 'ULSS di Padova', 66),
(127, 'ULSS di Parma', 68),
(128, 'ULSS di Pavia', 69),
(129, 'ULSS di Piacenza', 73),
(130, 'ULSS di Pordenone', 76),
(131, 'ULSS di Ravenna', 80),
(132, 'ULSS di Reggio Emilia', 82),
(133, 'ULSS di Rimini', 84),
(134, 'ULSS di Rovigo', 86),
(135, 'ULSS di Sondrio', 92),
(136, 'ULSS di Torino', 96),
(137, 'ULSS di Trento', 98),
(138, 'ULSS di Treviso', 99),
(139, 'ULSS di Trieste', 100),
(140, 'ULSS di Udine', 101),
(141, 'ULSS di Aosta', 102),
(142, 'ULSS di Varese', 103),
(143, 'ULSS di Venezia', 104),
(144, 'ULSS di Vercelli', 106),
(145, 'ULSS di Verona', 107),
(146, 'ULSS di Vicenza', 109);

-- --------------------------------------------------------

--
-- Struttura della tabella `disposition`
--

CREATE TABLE `disposition` (
  `id_disp` int(11) NOT NULL,
  `patient` int(11) NOT NULL,
  `familydoctor` int(11) NOT NULL,
  `visit` int(11) NOT NULL,
  `paid` tinyint(1) DEFAULT 0,
  `ticket` int(11) DEFAULT 0,
  `type` char(1) NOT NULL,
  `madedate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `disposition`
--

INSERT INTO `disposition` (`id_disp`, `patient`, `familydoctor`, `visit`, `paid`, `ticket`, `type`, `madedate`) VALUES
(2, 2, 51, 3, 1, 11, 'E', '2017-04-07 00:00:00'),
(3, 2, 51, 4, 1, 11, 'E', '2018-12-13 00:00:00'),
(4, 4, 53, 5, 0, 3, 'P', '2019-06-28 00:00:00'),
(5, 4, 53, 6, 1, 3, 'P', '2019-09-05 00:00:00'),
(6, 4, 53, 7, 1, 3, 'P', '2019-06-17 00:00:00'),
(7, 4, 53, 8, 1, 11, 'E', '2016-05-25 00:00:00'),
(8, 4, 53, 9, 1, 50, 'S', '2015-09-01 00:00:00'),
(9, 4, 53, 10, 1, 3, 'P', '2014-02-06 00:00:00'),
(10, 5, 54, 11, 0, 11, 'E', '2016-07-12 00:00:00'),
(11, 5, 54, 12, 0, 11, 'E', '2019-01-18 00:00:00'),
(12, 6, 55, 13, 1, 11, 'E', '2018-03-04 00:00:00'),
(13, 7, 56, 14, 0, 11, 'E', '2019-06-18 00:00:00'),
(14, 7, 56, 15, 0, 11, 'E', '2017-05-15 00:00:00'),
(15, 8, 57, 16, 1, 11, 'E', '2018-06-30 00:00:00'),
(16, 9, 58, 17, 1, 11, 'E', '2014-01-03 00:00:00'),
(17, 10, 59, 18, 1, 11, 'E', '2015-12-23 00:00:00'),
(18, 11, 60, 19, 0, 11, 'E', '2019-07-10 00:00:00'),
(19, 11, 60, 20, 1, 11, 'E', '2013-09-19 00:00:00'),
(20, 11, 60, 21, 1, 11, 'E', '2019-04-14 00:00:00'),
(21, 13, 62, 22, 1, 11, 'E', '2015-04-02 00:00:00'),
(22, 13, 62, 23, 0, 11, 'E', '2014-08-18 00:00:00'),
(23, 13, 62, 24, 0, 11, 'E', '2013-11-04 00:00:00'),
(24, 14, 63, 25, 0, 11, 'E', '2017-02-17 00:00:00'),
(25, 14, 63, 26, 0, 11, 'E', '2015-03-04 00:00:00'),
(26, 14, 63, 27, 0, 11, 'E', '2019-04-21 00:00:00'),
(27, 14, 63, 28, 1, 11, 'E', '2018-04-25 00:00:00'),
(28, 14, 63, 29, 0, 11, 'E', '2017-06-09 00:00:00'),
(29, 14, 63, 30, 1, 11, 'E', '2011-04-09 00:00:00'),
(30, 15, 64, 31, 0, 11, 'E', '2012-06-29 00:00:00'),
(31, 15, 64, 32, 0, 11, 'E', '2019-03-04 00:00:00'),
(32, 15, 64, 33, 0, 11, 'E', '2017-06-17 00:00:00'),
(33, 16, 65, 34, 1, 11, 'E', '2019-05-14 00:00:00'),
(34, 17, 66, 35, 0, 11, 'E', '2012-08-01 00:00:00'),
(35, 17, 66, 36, 0, 11, 'E', '2016-02-23 00:00:00'),
(36, 17, 66, 37, 1, 11, 'E', '2016-05-07 00:00:00'),
(37, 17, 66, 38, 1, 11, 'E', '2018-11-18 00:00:00'),
(38, 17, 66, 39, 1, 11, 'E', '2018-06-03 00:00:00'),
(39, 18, 67, 40, 0, 11, 'E', '2014-01-31 00:00:00'),
(40, 20, 69, 41, 0, 11, 'E', '2017-06-28 00:00:00'),
(41, 21, 70, 42, 1, 11, 'E', '2019-09-08 13:03:14'),
(42, 22, 51, 43, 0, 11, 'E', '2011-08-11 00:00:00'),
(43, 22, 51, 44, 0, 11, 'E', '2016-03-30 00:00:00'),
(44, 22, 51, 45, 0, 11, 'E', '2015-04-12 00:00:00'),
(45, 23, 52, 46, 0, 11, 'E', '2013-12-25 00:00:00'),
(46, 23, 52, 47, 1, 11, 'E', '2014-03-14 00:00:00'),
(47, 24, 53, 48, 0, 11, 'E', '2018-02-11 00:00:00'),
(48, 25, 54, 49, 1, 11, 'E', '2019-05-29 00:00:00'),
(49, 26, 55, 50, 0, 11, 'E', '2016-03-31 00:00:00'),
(50, 27, 56, 51, 1, 11, 'E', '2015-07-13 00:00:00'),
(51, 28, 57, 52, 0, 11, 'E', '2013-09-02 00:00:00'),
(52, 29, 58, 53, 1, 11, 'E', '2012-11-06 00:00:00'),
(53, 30, 59, 54, 0, 11, 'E', '2018-01-15 00:00:00'),
(54, 31, 60, 55, 1, 11, 'E', '2017-03-16 00:00:00'),
(55, 31, 60, 56, 0, 11, 'E', '2019-04-07 00:00:00'),
(56, 31, 60, 57, 1, 11, 'E', '2016-08-09 00:00:00'),
(57, 32, 61, 58, 0, 11, 'E', '2015-06-17 00:00:00'),
(58, 33, 62, 59, 0, 11, 'E', '2014-07-16 00:00:00'),
(59, 33, 62, 60, 0, 11, 'E', '2018-12-03 00:00:00'),
(60, 34, 63, 61, 1, 11, 'E', '2014-03-26 00:00:00'),
(61, 35, 64, 62, 0, 11, 'E', '2012-07-24 00:00:00'),
(62, 36, 65, 63, 0, 11, 'E', '2013-02-03 00:00:00'),
(63, 36, 65, 64, 1, 11, 'E', '2014-12-16 00:00:00'),
(64, 36, 65, 65, 0, 11, 'E', '2018-11-24 00:00:00'),
(65, 36, 65, 66, 1, 11, 'E', '2019-06-08 00:00:00'),
(66, 37, 66, 67, 0, 11, 'E', '2017-05-17 00:00:00'),
(67, 38, 67, 68, 0, 11, 'E', '2018-07-26 00:00:00'),
(68, 39, 68, 69, 1, 11, 'E', '2016-03-04 00:00:00'),
(69, 40, 69, 70, 0, 11, 'E', '2017-09-12 00:00:00'),
(70, 40, 69, 71, 1, 11, 'E', '2018-01-24 00:00:00'),
(71, 40, 69, 72, 1, 3, 'P', '2019-08-31 17:56:23'),
(72, 40, 69, 73, 1, 3, 'P', '2014-05-12 00:00:00'),
(73, 40, 69, 74, 1, 3, 'P', '2013-08-27 00:00:00'),
(74, 40, 69, 75, 1, 3, 'P', '2019-08-31 18:34:09'),
(75, 40, 69, 76, 1, 3, 'P', '2014-11-15 00:00:00'),
(76, 41, 70, 77, 1, 3, 'P', '2017-01-29 00:00:00'),
(77, 41, 70, 78, 1, 3, 'P', '2019-09-07 16:52:07'),
(78, 41, 70, 79, 0, 3, 'P', NULL),
(79, 42, 51, 80, 1, 3, 'P', '2019-02-24 00:00:00'),
(80, 42, 51, 81, 1, 3, 'P', '2018-04-03 00:00:00'),
(81, 42, 51, 82, 0, 3, 'P', NULL),
(82, 42, 51, 83, 0, 3, 'P', NULL),
(83, 43, 52, 84, 1, 3, 'P', '2014-11-04 00:00:00'),
(84, 45, 54, 85, 1, 3, 'P', '2018-12-13 00:00:00'),
(85, 45, 54, 86, 0, 3, 'P', NULL),
(86, 45, 54, 87, 1, 3, 'P', '2019-02-01 00:00:00'),
(87, 45, 54, 88, 0, 3, 'P', NULL),
(88, 45, 54, 89, 1, 3, 'P', '2015-06-08 00:00:00'),
(89, 46, 55, 90, 1, 3, 'P', '2018-07-27 00:00:00'),
(90, 46, 55, 91, 1, 3, 'P', '2015-06-06 00:00:00'),
(91, 46, 55, 92, 0, 3, 'P', NULL),
(92, 47, 56, 93, 1, 3, 'P', '2018-02-28 00:00:00'),
(93, 48, 57, 94, 1, 3, 'P', '2019-09-01 18:40:52'),
(94, 48, 57, 95, 1, 3, 'P', '2019-05-03 00:00:00'),
(95, 50, 59, 96, 1, 3, 'P', '2016-04-30 00:00:00'),
(96, 51, 52, 97, 1, 3, 'P', '2013-11-30 00:00:00'),
(97, 51, 52, 98, 0, 3, 'P', NULL),
(98, 51, 52, 99, 0, 3, 'P', NULL),
(99, 51, 52, 100, 1, 3, 'P', '2019-01-23 00:00:00'),
(100, 51, 52, 101, 1, 3, 'P', '2018-07-05 00:00:00'),
(101, 51, 52, 102, 0, 3, 'P', NULL),
(102, 51, 52, 103, 1, 3, 'P', '2016-05-16 00:00:00'),
(103, 51, 52, 104, 0, 3, 'P', NULL),
(104, 51, 52, 105, 1, 3, 'P', '2015-11-05 00:00:00'),
(105, 51, 52, 106, 1, 3, 'P', '2018-02-26 00:00:00'),
(106, 51, 52, 107, 1, 3, 'P', '2015-04-21 00:00:00'),
(107, 51, 52, 108, 1, 3, 'P', '2014-03-20 00:00:00'),
(108, 51, 52, 109, 0, 3, 'P', NULL),
(109, 51, 52, 110, 1, 3, 'P', '2017-03-08 00:00:00'),
(110, 51, 52, 111, 0, 3, 'P', NULL),
(111, 51, 52, 112, 0, 3, 'P', NULL),
(112, 51, 52, 113, 1, 3, 'P', '2018-04-25 00:00:00'),
(113, 51, 52, 114, 1, 3, 'P', '2015-05-26 00:00:00'),
(114, 51, 52, 115, 1, 3, 'P', '2013-08-01 00:00:00'),
(115, 51, 52, 116, 1, 3, 'P', '2012-07-05 00:00:00'),
(116, 51, 52, 117, 0, 3, 'P', NULL),
(117, 51, 52, 118, 1, 3, 'P', '2014-03-06 00:00:00'),
(118, 51, 52, 119, 0, 3, 'P', NULL),
(119, 52, 53, 120, 0, 3, 'P', NULL),
(120, 52, 53, 121, 0, 3, 'P', NULL),
(121, 53, 54, 122, 1, 3, 'P', '2015-06-23 00:00:00'),
(122, 53, 54, 123, 1, 3, 'P', '2016-02-01 00:00:00'),
(123, 53, 54, 124, 1, 3, 'P', '2017-05-11 00:00:00'),
(124, 53, 54, 125, 0, 3, 'P', NULL),
(125, 53, 54, 126, 0, 3, 'P', NULL),
(126, 54, 55, 127, 1, 3, 'P', '2017-03-31 00:00:00'),
(127, 54, 55, 128, 1, 3, 'P', '2015-02-05 00:00:00'),
(128, 54, 55, 129, 1, 3, 'P', '2019-09-07 16:41:49'),
(129, 54, 55, 130, 1, 3, 'P', '2015-01-16 00:00:00'),
(130, 55, 56, 131, 1, 3, 'P', '2012-03-10 00:00:00'),
(131, 55, 56, 132, 1, 3, 'P', '2019-09-02 18:17:38'),
(132, 56, 57, 133, 0, 3, 'P', '2019-09-01 12:00:00'),
(133, 57, 58, 134, 1, 3, 'P', '2018-11-28 00:00:00'),
(134, 58, 59, 135, 1, 3, 'P', '2019-05-07 00:00:00'),
(135, 58, 59, 136, 0, 3, 'P', NULL),
(136, 61, 62, 137, 0, 3, 'P', NULL),
(137, 62, 63, 138, 1, 3, 'P', '2015-05-04 00:00:00'),
(138, 62, 63, 139, 1, 3, 'P', '2014-10-16 00:00:00'),
(139, 57, 63, 140, 1, 3, 'P', '2016-06-24 00:00:00'),
(140, 62, 63, 141, 0, 3, 'P', NULL),
(141, 63, 64, 142, 1, 3, 'P', '2014-12-31 00:00:00'),
(142, 63, 64, 143, 1, 3, 'P', '2013-10-04 00:00:00'),
(143, 64, 65, 144, 0, 3, 'P', NULL),
(144, 65, 66, 145, 1, 3, 'P', '2018-08-10 00:00:00'),
(145, 66, 67, 146, 0, 3, 'P', NULL),
(146, 67, 68, 147, 1, 3, 'P', '2019-01-16 00:00:00'),
(147, 67, 68, 148, 1, 3, 'P', '2017-05-08 00:00:00'),
(148, 67, 68, 149, 1, 3, 'P', '2014-11-09 00:00:00'),
(149, 67, 68, 150, 0, 3, 'P', NULL),
(150, 68, 69, 151, 1, 3, 'P', '2017-08-23 00:00:00'),
(151, 68, 69, 152, 1, 3, 'P', '2018-02-01 00:00:00'),
(152, 69, 69, 153, 0, 50, 'S', '2017-02-05 00:00:00'),
(153, 69, 70, 154, 0, 50, 'S', '2016-04-13 00:00:00'),
(154, 69, 70, 155, 0, 50, 'S', '2015-06-24 00:00:00'),
(155, 69, 70, 156, 1, 50, 'S', '2018-07-08 00:00:00'),
(156, 69, 70, 157, 0, 50, 'S', '2019-02-16 00:00:00'),
(157, 70, 51, 158, 0, 50, 'S', '2015-11-04 00:00:00'),
(158, 70, 51, 159, 1, 50, 'S', '2013-09-25 00:00:00'),
(159, 71, 51, 160, 0, 50, 'S', '2015-03-19 00:00:00'),
(160, 72, 52, 161, 0, 50, 'S', '2016-05-03 00:00:00'),
(161, 73, 53, 162, 1, 50, 'S', '2013-12-06 00:00:00'),
(162, 74, 54, 163, 0, 50, 'S', '2012-06-18 00:00:00'),
(163, 75, 55, 164, 0, 50, 'S', '2016-10-29 00:00:00'),
(164, 75, 55, 165, 0, 50, 'S', '2015-08-04 00:00:00'),
(165, 75, 55, 166, 1, 50, 'S', '2019-07-05 00:00:00'),
(166, 75, 55, 167, 1, 50, 'S', '2018-10-16 00:00:00'),
(167, 75, 55, 168, 1, 50, 'S', '2015-05-04 00:00:00'),
(168, 76, 56, 169, 0, 50, 'S', '2018-04-18 00:00:00'),
(169, 76, 56, 170, 0, 50, 'S', '2016-03-07 00:00:00'),
(170, 76, 56, 171, 0, 50, 'S', '2018-10-16 00:00:00'),
(171, 76, 56, 172, 0, 50, 'S', '2014-09-23 00:00:00'),
(172, 77, 57, 173, 1, 50, 'S', '2016-11-24 00:00:00'),
(173, 77, 57, 174, 1, 50, 'S', '2013-02-01 00:00:00'),
(174, 77, 57, 175, 0, 50, 'S', '2018-01-15 00:00:00'),
(175, 78, 58, 176, 0, 50, 'S', '2015-03-24 00:00:00'),
(176, 78, 58, 177, 0, 50, 'S', '2019-08-03 00:00:00'),
(177, 78, 58, 178, 1, 50, 'S', '2013-12-01 00:00:00'),
(178, 78, 58, 179, 0, 50, 'S', '2016-02-14 00:00:00'),
(179, 79, 59, 180, 0, 50, 'S', '2014-04-21 00:00:00'),
(180, 80, 60, 181, 1, 50, 'S', '2019-06-02 00:00:00'),
(181, 80, 60, 182, 0, 50, 'S', '2016-10-20 00:00:00'),
(182, 81, 61, 183, 1, 50, 'S', '2018-06-16 00:00:00'),
(183, 82, 62, 184, 0, 50, 'S', '2014-08-09 00:00:00'),
(184, 83, 63, 185, 1, 50, 'S', '2018-09-08 00:00:00'),
(185, 83, 63, 186, 0, 50, 'S', '2014-10-16 00:00:00'),
(186, 83, 63, 187, 0, 50, 'S', '2018-03-24 00:00:00'),
(187, 83, 63, 188, 1, 50, 'S', '2016-10-05 00:00:00'),
(188, 84, 64, 189, 0, 50, 'S', '2017-06-27 00:00:00'),
(189, 84, 64, 190, 0, 50, 'S', '2019-05-03 00:00:00'),
(190, 84, 64, 191, 0, 50, 'S', '2017-11-09 00:00:00'),
(191, 84, 64, 192, 0, 50, 'S', '2018-04-15 00:00:00'),
(192, 84, 64, 193, 1, 50, 'S', '2016-09-26 00:00:00'),
(193, 85, 65, 194, 0, 50, 'S', '2015-04-23 00:00:00'),
(194, 85, 65, 195, 1, 50, 'S', '2018-05-04 00:00:00'),
(195, 85, 65, 196, 0, 50, 'S', '2015-04-08 00:00:00'),
(196, 85, 65, 197, 0, 50, 'S', '2019-03-16 00:00:00'),
(197, 85, 65, 198, 0, 50, 'S', '2018-05-24 00:00:00'),
(198, 85, 65, 199, 1, 50, 'S', '2017-07-09 00:00:00'),
(199, 85, 65, 200, 1, 50, 'S', '2018-01-17 00:00:00'),
(200, 85, 65, 201, 1, 50, 'S', '2017-05-27 00:00:00'),
(201, 85, 65, 202, 1, 50, 'S', '2019-09-06 21:51:28'),
(202, 85, 65, 203, 0, 50, 'S', '2019-05-24 00:00:00'),
(203, 85, 65, 204, 1, 50, 'S', '2018-12-15 00:00:00'),
(204, 85, 65, 205, 0, 50, 'S', '2019-03-06 00:00:00'),
(205, 85, 65, 206, 0, 50, 'S', '2016-10-04 00:00:00'),
(206, 85, 65, 207, 0, 50, 'S', '2017-06-08 00:00:00'),
(207, 85, 65, 208, 1, 50, 'S', '2014-08-04 00:00:00'),
(208, 85, 65, 209, 0, 50, 'S', '2017-04-16 00:00:00'),
(209, 85, 65, 210, 0, 50, 'S', '2015-06-17 00:00:00'),
(210, 85, 65, 211, 1, 50, 'S', '2018-03-05 00:00:00'),
(211, 85, 65, 212, 1, 50, 'S', '2014-05-04 00:00:00'),
(214, 5, 53, 11, 0, 3, 'P', NULL),
(220, 53, 54, 124, 0, 11, 'E', NULL),
(221, 17, 66, 226, 1, 11, 'E', '2019-09-04 15:15:42'),
(227, 17, 66, 226, 1, 3, 'P', '2019-09-03 16:56:06'),
(230, 17, 66, 227, 0, 11, 'E', NULL),
(231, 17, 66, 227, 0, 11, 'E', NULL),
(232, 17, 66, 227, 0, 11, 'E', NULL),
(233, 17, 66, 227, 0, 11, 'E', '2019-09-20 16:30:00'),
(234, 17, 66, 227, 0, 11, 'E', NULL),
(235, 17, 66, 227, 0, 11, 'E', NULL),
(236, 17, 66, 227, 0, 11, 'E', NULL),
(237, 17, 66, 227, 0, 11, 'E', NULL),
(238, 17, 66, 227, 0, 11, 'E', '2019-09-18 15:30:00'),
(239, 17, 66, 227, 0, 11, 'E', NULL),
(240, 65, 66, 228, 1, 11, 'E', '2019-09-04 15:25:11'),
(241, 17, 66, 227, 0, 11, 'E', NULL),
(242, 17, 66, 227, 0, 11, 'E', NULL),
(243, 54, 55, 130, 0, 0, 'P', NULL),
(256, 17, 66, 257, 0, 11, 'E', NULL),
(257, 17, 66, 257, 0, 3, 'P', NULL),
(258, 149, 150, 258, 1, 11, 'E', '2019-09-08 18:32:49'),
(259, 149, 150, 258, 1, 3, 'P', '2019-09-07 22:58:29'),
(260, 149, 150, 258, 0, 50, 'S', NULL),
(273, 5, 52, 271, 0, 50, 'S', '2019-09-08 14:30:00'),
(283, 32, 137, 281, 0, 0, 'S', NULL),
(284, 5, 137, 282, 0, 0, 'E', NULL),
(285, 5, 137, 283, 0, 0, 'E', NULL),
(286, 5, 137, 284, 0, 0, 'S', NULL),
(287, 5, 137, 285, 0, 0, 'S', NULL),
(289, 37, 66, 286, 0, 3, 'P', NULL),
(290, 37, 66, 286, 0, 50, 'S', '2019-09-13 11:30:00'),
(291, 37, 66, 286, 0, 3, 'P', NULL),
(292, 37, 66, 286, 0, 50, 'S', NULL),
(293, 37, 66, 286, 0, 3, 'P', NULL),
(298, 151, 150, 287, 1, 11, 'E', '2019-09-08 16:06:50'),
(299, 151, 150, 287, 1, 3, 'P', '2019-09-08 15:52:15'),
(300, 151, 150, 287, 0, 50, 'S', NULL),
(301, 151, 150, 287, 0, 3, 'P', NULL),
(302, 148, 150, 288, 0, 3, 'P', NULL),
(303, 148, 150, 288, 0, 11, 'E', NULL),
(304, 148, 150, 288, 0, 50, 'S', NULL),
(305, 149, 150, 292, 0, 11, 'E', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `district`
--

CREATE TABLE `district` (
  `id_dist` int(11) NOT NULL,
  `city` varchar(35) NOT NULL,
  `acronym` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `district`
--

INSERT INTO `district` (`id_dist`, `city`, `acronym`) VALUES
(1, 'Agrigento', 'AG'),
(2, 'Alessandria', 'AL'),
(3, 'Ancona', 'AN'),
(4, 'Arezzo', 'AR'),
(5, 'Ascoli Piceno', 'AP'),
(6, 'Asti', 'AT'),
(7, 'Avellino', 'AV'),
(8, 'Bari', 'BA'),
(9, 'Barletta-Andria-Trani', 'BT'),
(10, 'Belluno', 'BL'),
(11, 'Benevento', 'BN'),
(12, 'Bergamo', 'BG'),
(13, 'Biella', 'BI'),
(14, 'Bologna', 'BO'),
(15, 'Bolzano', 'BZ'),
(16, 'Brescia', 'BS'),
(17, 'Brindisi', 'BR'),
(18, 'Cagliari', 'CA'),
(19, 'Caltanissetta', 'CL'),
(20, 'Campobasso', 'CB'),
(21, 'Carbonia-Iglesias', 'CI'),
(22, 'Caserta', 'CE'),
(23, 'Catania', 'CT'),
(24, 'Catanzaro', 'CZ'),
(25, 'Chieti', 'CH'),
(26, 'Como', 'CO'),
(27, 'Cosenza', 'CS'),
(28, 'Cremona', 'CR'),
(29, 'Crotone', 'KR'),
(30, 'Cuneo', 'CN'),
(31, 'Enna', 'EN'),
(32, 'Fermo', 'FM'),
(33, 'Ferrara', 'FE'),
(34, 'Firenze', 'FI'),
(35, 'Foggia', 'FG'),
(36, 'Forlì-Cesena', 'FC'),
(37, 'Frosinone', 'FR'),
(38, 'Genova', 'GE'),
(39, 'Gorizia', 'GO'),
(40, 'Grosseto', 'GR'),
(41, 'Imperia', 'IM'),
(42, 'Isernia', 'IS'),
(43, 'LAquila', 'AQ'),
(44, 'La Spezia', 'SP'),
(45, 'Latina', 'LT'),
(46, 'Lecce', 'LE'),
(47, 'Lecco', 'LC'),
(48, 'Livorno', 'LI'),
(49, 'Lodi', 'LO'),
(50, 'Lucca', 'LU'),
(51, 'Macerata', 'MC'),
(52, 'Mantova', 'MN'),
(53, 'Massa e Carrara', 'MS'),
(54, 'Matera', 'MT'),
(55, 'Medio Campidano', 'VS'),
(56, 'Messina', 'ME'),
(57, 'Milano', 'MI'),
(58, 'Modena', 'MO'),
(59, 'Monza e Brianza', 'MB'),
(60, 'Napoli', 'NA'),
(61, 'Novara', 'NO'),
(62, 'Nuoro', 'NU'),
(63, 'Ogliastra', 'OG'),
(64, 'Olbia-Tempio', 'OT'),
(65, 'Oristano', 'OR'),
(66, 'Padova', 'PD'),
(67, 'Palermo', 'PA'),
(68, 'Parma', 'PR'),
(69, 'Pavia', 'PV'),
(70, 'Perugia', 'PG'),
(71, 'Pesaro e Urbino', 'PU'),
(72, 'Pescara', 'PE'),
(73, 'Piacenza', 'PC'),
(74, 'Pisa', 'PI'),
(75, 'Pistoia', 'PT'),
(76, 'Pordenone', 'PN'),
(77, 'Potenza', 'PZ'),
(78, 'Prato', 'PO'),
(79, 'Ragusa', 'RG'),
(80, 'Ravenna', 'RA'),
(81, 'Reggio Calabria(metropolitana)', 'RC'),
(82, 'Reggio Emilia', 'RE'),
(83, 'Rieti', 'RI'),
(84, 'Rimini', 'RN'),
(85, 'Roma', 'RM'),
(86, 'Rovigo', 'RO'),
(87, 'Salerno', 'SA'),
(88, 'Sassari', 'SS'),
(89, 'Savona', 'SV'),
(90, 'Siena', 'SI'),
(91, 'Siracusa', 'SR'),
(92, 'Sondrio', 'SO'),
(93, 'Taranto', 'TA'),
(94, 'Teramo', 'TE'),
(95, 'Terni', 'TR'),
(96, 'Torino', 'TO'),
(97, 'Trapani', 'TP'),
(98, 'Trento', 'TN'),
(99, 'Treviso', 'TV'),
(100, 'Trieste', 'TS'),
(101, 'Udine', 'UD'),
(102, 'Aosta', 'AO'),
(103, 'Varese', 'VA'),
(104, 'Venezia', 'VE'),
(105, 'Verbano-Cusio-Ossola', 'VB'),
(106, 'Vercelli', 'VC'),
(107, 'Verona', 'VR'),
(108, 'Vibo Valentia', 'VV'),
(109, 'Vicenza', 'VI'),
(110, 'Viterbo', 'VT');

-- --------------------------------------------------------

--
-- Struttura della tabella `doctor`
--

CREATE TABLE `doctor` (
  `id_doc` int(11) NOT NULL,
  `doc_reg_numb` varchar(20) NOT NULL,
  `city` varchar(30) NOT NULL,
  `type` char(1) NOT NULL,
  `specialization` varchar(100) DEFAULT 'primary',
  `work_district` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `doctor`
--

INSERT INTO `doctor` (`id_doc`, `doc_reg_numb`, `city`, `type`, `specialization`, `work_district`) VALUES
(1, 'NULL', 'NULL', 'X', '', 98),
(51, 'SK62217600', 'Trento', 'P', 'primary', 98),
(52, 'QC53926164', 'Trento', 'P', 'primary', 98),
(53, 'DG76398774', 'Borgo Valsugana', 'P', 'primary', 98),
(54, 'OH75302001', 'Grigno', 'P', 'primary', 98),
(55, 'NW39547643', 'Asti', 'P', 'primary', 6),
(56, 'BY73599600', 'Asti', 'P', 'primary', 6),
(57, 'MS76352603', 'Rovereto', 'P', 'primary', 98),
(58, 'KW64830786', 'Levico', 'P', 'primary', 98),
(59, 'DR71678384', 'Levico', 'P', 'primary', 98),
(60, 'KY84667521', 'Pergine', 'P', 'primary', 98),
(61, 'QU50507226', 'Pergine', 'P', 'primary', 98),
(62, 'LO58688744', 'Pergine', 'P', 'primary', 98),
(63, 'XB32392820', 'Mezzocorona', 'P', 'primary', 98),
(64, 'NM53445492', 'Mezzocorona', 'P', 'primary', 98),
(65, 'UE11530311', 'Lavis', 'P', 'primary', 98),
(66, 'QU83327117', 'Lavis', 'P', 'primary', 98),
(67, 'SF55591070', 'Riva del Garda', 'P', 'primary', 98),
(68, 'RC31369761', 'Riva del Garda', 'P', 'primary', 98),
(69, 'BD19425012', 'Trento', 'P', 'primary', 98),
(70, 'FN22754962', 'Trento', 'P', 'primary', 98),
(71, 'UI89267874', 'Trento', 'S', 'Oncologia', 98),
(72, 'SN51657721', 'Trento', 'S', 'Cardiologia', 98),
(73, 'NW91628199', 'Trento', 'S', 'Radiologia', 98),
(74, 'LL15014757', 'Trento', 'S', 'Ortopedia', 98),
(75, 'CZ16929607', 'Trento', 'S', 'Dermatologia', 98),
(76, 'XW26719133', 'Trento', 'S', 'Urologia', 98),
(77, 'GJ84941077', 'Trento', 'S', 'Otorinolaringoiatria', 98),
(78, 'LE48919877', 'Rovereto', 'S', 'Chirurgia', 98),
(79, 'AG51811720', 'Rovereto', 'S', 'Reumatologia ', 98),
(80, 'DN26003071', 'Riva del Garda', 'S', 'Malattie Infettive e Tropicali ', 98),
(81, 'JA60983478', 'Riva del Garda', 'S', 'Ematologia ', 98),
(82, 'QR39015130', 'Rovereto', 'S', 'Medicina dello sport e dell’esercizio fisico', 98),
(83, 'SY92614524', 'Borgo Valsugana', 'S', ' Medicina d’emergenza-urgenza ', 98),
(84, 'FB39728390', 'Pergine', 'S', 'Neurologia ', 98),
(85, 'EC29243950', 'Levico', 'S', 'Medicina nucleare ', 98),
(150, 'AK56422884', 'Thiene', 'P', 'primary', 109),
(151, 'SR45845689', 'Thiene', 'S', 'Ortopedia', 109);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `doctor_ext`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `doctor_ext` (
`id_doc` int(11)
,`doc_reg_numb` varchar(20)
,`city` varchar(30)
,`type` char(1)
,`specialization` varchar(100)
,`work_district` int(11)
,`id_pat` int(11)
,`ssn` char(16)
,`firstname` varchar(30)
,`lastname` varchar(30)
,`birthday` datetime
,`birthplace` varchar(30)
,`sex` char(1)
,`familydoctor` int(11)
,`pdistrict` int(11)
);

-- --------------------------------------------------------

--
-- Struttura della tabella `drug`
--

CREATE TABLE `drug` (
  `id_drug` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `drug`
--

INSERT INTO `drug` (`id_drug`, `name`, `description`) VALUES
(2, 'ABBA', ' Principio attivo: AMOXICILLINA TRIIDRATA/POTASSIO CLAVULANATO '),
(3, 'ABIS', ' Principio attivo: QUINAPRIL CLORIDRATO '),
(4, 'ACCUPRIN', ' Principio attivo: AMOXICILLINA TRIIDRATA/POTASSIO CLAVULANATO '),
(5, 'ACETAMOL', ' Principio attivo: PARACETAMOLO '),
(6, 'ACETILCISTEINA', ' Principio attivo: ACETILCISTEINA'),
(7, 'ACIDO ASCORBICO', ' Principio attivo: ACIDO ACETILSALICILICO/ACIDO ASCORBICO '),
(8, 'ACIDO ALENDRONICO', ' Principio attivo: ACIDO ALENDRONICO SALE SODICO '),
(9, 'ACQUA PI', ' Principio attivo: ACQUA PER PREPARAZIONI INIETTABILI '),
(10, 'ADRENALINA', ' Principio attivo:	ADRENALINA '),
(11, 'ALIFLUS', ' Principio attivo:	SALMETEROLO XINAFOATO/FLUTICASONE PROPIONATO '),
(12, 'BABYRINOLO', ' Principio attivo: PARACETAMOLO/CLORFENAMINA MALEATO '),
(13, 'ZIAGEN', ' Principio attivo: Abacavir '),
(14, 'ORENCIA', ' Principio attivo: Abatacept '),
(15, 'REOPRO', ' Principio attivo: Abciximab '),
(16, 'ACAMPROSATO', ' Principio attivo: Acamprosato'),
(17, 'PRECOSE', ' Principio attivo: Acarbosio '),
(18, 'SECTRAL', ' Principio attivo: Acebutololo '),
(19, 'TACHIPIRINA', ' Principio attivo: Paracetamolo '),
(20, 'DIAMOX', ' Principio attivo: Acetazolamide '),
(21, 'ACETADOTE', ' Principio attivo: Acetilcisteina '),
(22, 'ZOVIRAX', ' Principio attivo: Aciclovir '),
(23, 'HUMIRA', ' Principio attivo: Adalimumab '),
(24, 'DIFFERIN', ' Principio attivo: Adapalene '),
(25, 'HEPSERA', ' Principio attivo: Adefovir '),
(26, 'ADENOCARD', ' Principio attivo: Adenosina'),
(27, 'BACIIM', ' Principio attivo: Bacitracina '),
(28, 'NEOSPORIN', ' Principio attivo: Bacitracina '),
(29, 'LIORESAL', ' Principio attivo: Baclofene '),
(30, 'COLAZAL', ' Principio attivo: Balsalazide '),
(31, 'SIMULECT', ' Principio attivo: Basiliximab '),
(32, 'BECONASE', ' Principio attivo: Beclometasone '),
(33, 'LOTENSIN', ' Principio attivo: Benazepril '),
(34, 'TREANDA', ' Principio attivo: Bendamustina '),
(35, 'ANBESOL', ' Principio attivo: Benzocaina '),
(36, 'MYLERAN', ' Principio attivo: Busulfan'),
(37, 'COMETRIQ', ' Principio attivo: Cabozantinib '),
(38, 'MIACALCIN', ' Principio attivo: Calcitonina '),
(39, 'ROCALTROL', ' Principio attivo: Calcitriolo '),
(40, 'SEROMICINA', ' Principio attivo: Cicloserina '),
(41, 'EURAX', ' Principio attivo: Crotamitone '),
(42, 'PRADAXA', ' Principio attivo: Dabrafenib '),
(43, 'COSMEGEN', ' Principio attivo: Dactinomicina '),
(44, 'DALVANCE', ' Principio attivo: Dalbavancina '),
(45, 'ACZONE', ' Principio attivo: Dapsone '),
(46, 'SPRYCEL', ' Principio attivo: Dasatinib'),
(47, 'FERRIPROX', ' Principio attivo: Deferiprone '),
(48, 'RESCRIPTOR', ' Principio attivo: Delavirdina '),
(49, 'PROLIA', ' Principio attivo: Denosumab '),
(50, 'CLARINEX', ' Principio attivo: Desloratadina '),
(51, 'TIKOSYN', ' Principio attivo: Dofetilide '),
(52, 'COMTAN', ' Principio attivo: Entacapone '),
(53, 'ELLENCE', ' Principio attivo: Epirubicina '),
(54, 'INSPRA', ' Principio attivo: Eplerenone '),
(55, 'INTELENCE', ' Principio attivo: Etravirina '),
(56, 'POTIGA', ' Principio attivo: Ezogabina'),
(57, 'ULORIC', ' Principio attivo: Febuxostat '),
(58, 'NALFON', ' Principio attivo: Delavirdina '),
(59, 'ACTIQ', ' Principio attivo: Fentanil '),
(60, 'TAMBOCOR', ' Principio attivo: Flecainide '),
(61, 'LESCOL', ' Principio attivo: Fluvastatina '),
(62, 'IRESSA', ' Principio attivo: Gefitinib '),
(63, 'FACTIVE', ' Principio attivo: Gemifloxacina '),
(64, 'GENOPTIC', ' Principio attivo: Gentamicina '),
(65, 'DIABETA', ' Principio attivo: Gliburide '),
(66, 'SIMPONI', ' Principio attivo: Golimumab'),
(67, 'ZOLADEX', ' Principio attivo: Goserelin '),
(68, 'TENEX', ' Principio attivo: Guanfacina '),
(69, 'VANTAS', ' Principio attivo: Istrelina '),
(70, 'MICROZIDE', ' Principio attivo: Idroclorotiazide '),
(71, 'DILAUDID', ' Principio attivo: Idromorfone '),
(72, 'ANTIVERT', ' Principio attivo: Meclizina '),
(73, 'PROVERA', ' Principio attivo: Medrossiprogesterone '),
(74, 'MEGACE', ' Principio attivo: Megestrol '),
(75, 'CYTOTEC', ' Principio attivo: Misoprostolo '),
(76, 'UNIVASC', ' Principio attivo: Moexipril'),
(77, 'SINGULAIR', ' Principio attivo: Montelukast '),
(78, 'AVELOX', ' Principio attivo: Moxifloxacina '),
(79, 'SYNAREL', ' Principio attivo: Nafarelina '),
(80, 'REVIA', ' Principio attivo: Naltrexone '),
(81, 'BYSTOLIC', ' Principio attivo: Nebivololo '),
(82, 'NORFLEX', ' Principio attivo: Orfenadrina '),
(83, 'TAMIFLU', ' Principio attivo: Oseltamivir '),
(84, 'DIBENZYLINE', ' Principio attivo: Fenossibenzamina '),
(85, 'FELDENE', ' Principio attivo: Piroxicam '),
(86, 'LIVALO', ' Principio attivo: Pitavastatina'),
(87, 'DORAL', ' Principio attivo: Quazepam '),
(88, 'ACCUPRIL', ' Principio attivo: Quinapril '),
(89, 'QUALAQUIN', ' Principio attivo: Chinina '),
(90, 'ALTACE', ' Principio attivo: Ramipril '),
(91, 'LUCENTIS', ' Principio attivo: Ranibizumab '),
(92, 'PRIFTIN', ' Principio attivo: Rifapentina '),
(93, 'EDURANT', ' Principio attivo: Rilpivirina '),
(94, 'FLUMADINE', ' Principio attivo: Rimantadina '),
(95, 'EXELON', ' Principio attivo: Rivastigmina '),
(96, 'REQUIP', ' Principio attivo: Ropinirolo'),
(97, 'NEUPRO', ' Principio attivo: Rotigotina '),
(98, 'INVIRASE', ' Principio attivo: Saquinavir '),
(99, 'ZOLOFT', ' Principio attivo: Sertralina '),
(100, 'ZOCOR', ' Principio attivo: Simvastatina '),
(101, 'XYREM', ' Principio attivo: Sodio oxibato '),
(102, 'VOLTAREN', 'Principio attivo: volta renne'),
(103, 'LEVOCETRIZINA', 'Principio attivo: cetrizina 10mg'),
(104, 'FLUIFORT', 'Principio attivo: brufen');

-- --------------------------------------------------------

--
-- Struttura della tabella `drugstore`
--

CREATE TABLE `drugstore` (
  `id_drst` int(11) NOT NULL,
  `piva` varchar(12) NOT NULL,
  `city` varchar(30) NOT NULL,
  `ddistrict` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `drugstore`
--

INSERT INTO `drugstore` (`id_drst`, `piva`, `city`, `ddistrict`) VALUES
(1, 'NULL', 'NULL', 98),
(86, '10170320013', 'TORINO', 96),
(87, '11090630010', 'VENEZIA', 104),
(88, '7141030010', 'MEL', 10),
(89, '5326350013', 'COLLEGNO', 96),
(90, '2099060028', 'BORGOSESIA', 106),
(91, '1595780022', 'VALDUGGIA', 106),
(92, '624890075', 'HONE', 102),
(93, '1022060071', 'NUS', 102),
(94, '3083750160', 'BERGAMO', 12),
(95, '3074160163', 'ZANICA', 12),
(96, '4089520169', 'PONTIDA', 12),
(97, '2315040218', 'FALZES', 15),
(98, '1718290222', 'LIVO', 26),
(99, '1124330257', 'PONTE NELLE ALPI', 10),
(100, '3925210241', 'TEZZE SUL BRENTA', 109),
(101, '1245360266', 'REVINE LAGO', 99),
(102, '1255650325', 'TRIESTE', 100),
(103, '1357320934', 'AZZANO DECIMO', 76),
(104, '1204120099', 'SAVONA', 89),
(105, '2019780341', 'VALMOZZOLA', 68),
(106, '2300260367', 'MONTESE', 58),
(152, '15216457', 'Thiene', 109);

-- --------------------------------------------------------

--
-- Struttura della tabella `exam`
--

CREATE TABLE `exam` (
  `id_exam` int(11) NOT NULL,
  `examcode` int(11) NOT NULL,
  `result` text DEFAULT NULL,
  `providedby` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `exam`
--

INSERT INTO `exam` (`id_exam`, `examcode`, `result`, `providedby`) VALUES
(2, 6, ' ', 126),
(3, 23, ' ', 114),
(7, 125, ' ', 146),
(11, 238, ' ', 121),
(12, 250, ' ', 127),
(13, 56, ' ', 108),
(14, 34, ' ', 109),
(15, 87, ' ', 121),
(16, 147, ' ', 124),
(17, 236, ' ', 127),
(18, 164, ' ', 131),
(19, 199, ' ', 130),
(20, 163, ' ', 139),
(21, 134, ' ', 142),
(22, 26, ' ', 142),
(23, 76, ' ', 145),
(24, 36, ' ', 126),
(25, 24, ' ', 123),
(26, 13, ' ', 120),
(27, 146, ' ', 108),
(28, 196, ' ', 110),
(29, 213, ' ', 109),
(30, 157, ' ', 125),
(31, 140, ' ', 127),
(32, 124, ' ', 139),
(33, 76, NULL, 124),
(34, 54, ' ', 122),
(35, 37, ' ', 109),
(36, 97, ' ', 132),
(37, 188, ' ', 145),
(38, 157, ' ', 142),
(39, 234, ' ', 124),
(40, 253, ' ', 138),
(41, 68, 'La paziente sta per morire, diagnosi 3gg.\r\nUPDATE: 2 ore di vita rimaste', 137),
(42, 23, ' ', 136),
(43, 90, ' ', 132),
(44, 96, ' ', 131),
(45, 46, ' ', 130),
(46, 169, ' ', 143),
(47, 134, ' ', 142),
(48, 76, ' ', 136),
(49, 35, ' ', 132),
(50, 242, ' ', 128),
(51, 212, ' ', 129),
(52, 137, ' ', 109),
(53, 111, ' ', 108),
(54, 110, ' ', 121),
(55, 65, ' ', 120),
(56, 46, ' ', 115),
(57, 97, NULL, 114),
(58, 258, ' ', 111),
(59, 45, ' ', 116),
(60, 160, ' ', 118),
(61, 231, ' ', 126),
(62, 211, ' ', 113),
(63, 200, ' ', 112),
(64, 169, ' ', 128),
(65, 126, ' ', 113),
(66, 222, ' ', 119),
(67, 76, ' ', 120),
(68, 34, ' ', 109),
(69, 94, ' ', 117),
(70, 34, ' ', 142),
(220, 2, NULL, 1),
(221, 2, 'Tutto a posto', 137),
(230, 74, NULL, 1),
(231, 58, NULL, 1),
(232, 5, NULL, 1),
(233, 6, NULL, 108),
(234, 2, NULL, 1),
(235, 9, NULL, 1),
(236, 145, NULL, 1),
(237, 17, NULL, 1),
(238, 23, NULL, 137),
(239, 5, NULL, 1),
(240, 15, 'Tutto a posto', 137),
(241, 16, NULL, 1),
(256, 2, NULL, 1),
(258, 110, 'Tutto a posto', 137),
(284, 3, NULL, 1),
(285, 76, NULL, 1),
(298, 2, 'Il paziente in carico non presenta alcuna anomalia da evidenziare. Rimandiamo al proprio medico di base per ulteriori esiti.', 146),
(303, 4, NULL, 1),
(305, 11, NULL, 1);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `examforlist`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `examforlist` (
`id_disp` int(11)
,`patient` int(11)
,`cardname` varchar(100)
,`prescrby` varchar(61)
,`madeby` varchar(40)
,`prescrdate` datetime
,`madedate` datetime
);

-- --------------------------------------------------------

--
-- Struttura della tabella `examlist`
--

CREATE TABLE `examlist` (
  `id_exlis` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `category` int(11) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `examlist`
--

INSERT INTO `examlist` (`id_exlis`, `name`, `category`, `description`) VALUES
(2, 'Acido folico', 2, ''),
(3, 'Acido lattico', 2, ''),
(4, 'Adrenalina plasmatica', 2, ''),
(5, 'Adrenalica urinaria', 2, ''),
(6, 'Anticorpi anti recettore TSH', 2, 'Dosaggio per singola determinazione'),
(7, 'Anticorpi anti rickettsie', 2, 'Dosaggio per singola determinazione'),
(8, 'Anticorpi anti testicolo', 2, 'Dosaggio per singola determinazione'),
(9, 'Bacillo di Koch', 2, ''),
(10, 'Bicarbonati', 2, ''),
(11, 'Breath test', 2, ''),
(12, 'Calcitonina', 2, ''),
(13, 'Calcolo renale', 2, ''),
(14, 'Calcio ionizzato', 2, ''),
(15, 'Dopamina urinaria', 2, 'Dosaggio per singola determinazione'),
(16, 'Emoglobina', 2, ''),
(17, 'Fosfatasi alcalina', 2, ''),
(18, 'Gamma glutamil transpeptidasi', 2, ''),
(19, 'Immunoglobuline A secretorie in altri liquidi biologici', 2, ''),
(20, 'Immunoglobuline A', 2, ''),
(21, 'Immunoglobuline A', 2, ''),
(22, 'Immunoglobuline D', 2, ''),
(23, 'Immunoglobuline E', 2, 'Rimborsabile solo previa identificazione dell’allergene con test cutaneo o dichiarazione anamnestica da parte dell’immunologo/allergologo/dermatologo e specialista di medicina interna. Il numero massimo concedibile di allergeni da ricercare è di 10 e solo essi sono ripetibili per eventuale controllo dell’effetto della terapia iposensibilizzante una volta all’anno (01/01 - 31/12) per massimo 4 anni'),
(24, 'Immunoglobuline G', 2, ''),
(25, 'Immunoglobuline G', 2, ''),
(26, 'Immunoglobuline G', 2, ''),
(27, 'Immunoglobuline F', 2, ''),
(28, 'Tireoglobulina', 2, ''),
(29, 'Trigliceridi', 2, ''),
(30, 'Zinco', 3, ''),
(31, 'Altri batteri eseguibili con metodica PCR', 3, ''),
(32, 'Altri virus eseguibili con metodica PCR', 3, ''),
(33, 'CMV', 3, 'Citomegalovirus'),
(34, 'EBV', 3, 'Epstein Barrvirus'),
(35, 'HBV DNA qualitativo', 3, ''),
(36, 'HBV DNA quantitativo', 3, ''),
(37, 'HBV DNA Polimerasi', 3, ''),
(38, 'HCV qualitativo', 3, 'Epatite C'),
(39, 'HCV quantitativo', 3, 'Epatite C'),
(40, 'HCV Genotipo', 3, 'Epatite C'),
(41, 'Helicobacter Pylori Amplificazione Genica', 3, ''),
(42, 'HIV rna qualitativo', 3, ''),
(43, 'HIV rna qualitativo', 3, ''),
(44, 'HPV - B19 Genotipoo', 3, ''),
(45, 'HPV - B19', 3, 'Parvo Virus'),
(46, 'Pneumocistis carini', 3, ''),
(47, 'BRAF', 4, ''),
(48, 'CISH', 4, 'Solo su prescrizione di specialista oncologo o chirurgo presso struttura pubblica'),
(49, 'EGFR', 4, ''),
(50, 'FISH', 4, 'Fluorescent In Situ Hybridization - solo su prescrizione di specialista oncologo o chirurgo operante presso struttura pubblica'),
(51, 'KRAS', 4, ''),
(52, 'Ricerca di una specifica mutazione', 4, ''),
(53, 'Cariotipo su sangue periferico', 5, 'Mappa cromosomica'),
(54, 'FISH su liquido seminale', 5, 'Deve essere accompagnate da una prescrizione da parte dello specialista genetista in cui sia specificata la motivazione per cui l’esame è necessario e non sono ripetibili.'),
(55, 'Ricerca dele mutazione del gene', 5, 'Acondroplasia, Anemia Falciforme, Aneuploidie Molecolari, Atassie Spinocerebellari,Atassia di Friedreich, Atassia Telangectasia, Distonia Primaria, Distrofia Miotonica, Distrofia Muscolare, Emocromatosi, Huntington, MCDA Deficit, Sordità congenita, SBMA, X Fragile -- può essere prescritta anche da specialista in ginecologia, andrologia, cardiologia, ematologia e medicina interna.'),
(56, 'Ricerca del pannello di mutazioni per Trombofilia/Ipertension', 5, 'Deve essere accompagnate da una prescrizione da parte dello specialista genetista in cui sia specificata la motivazione per cui l’esame è necessario e non sono ripetibili.'),
(57, 'Ricerca di mutazioni di geni per Trombofilia', 5, 'Deve essere accompagnate da una prescrizione da parte dello specialista genetista in cui sia specificata la motivazione per cui l’esame è necessario e non sono ripetibili.'),
(58, 'Ricerca genetica per anomalie cromosomiche', 5, 'Deve essere accompagnate da una prescrizione da parte dello specialista genetista in cui sia specificata la motivazione per cui l’esame è necessario e non sono ripetibili.'),
(59, 'SRY', 5, 'Sex determining Region'),
(60, 'Recettore Androgenico AR', 5, ''),
(61, 'Esame citologico per diagnostica ormonale', 6, ''),
(62, 'Esame citologico per diagnostica tumorale', 6, ''),
(63, 'Esame istologico con inclusione', 6, ''),
(64, 'Esame istologico con inclusione: ogni Inclusione in più', 6, ''),
(65, 'Esame istologico estemporaneo', 6, 'Max 3 inclusioni'),
(66, 'Esame istologico estemporaneo', 6, 'Ogni inclusione successiva oltre il terzo'),
(67, 'Immuno fenotipo di popolazioni cellulari neoplastiche ', 6, 'Per ogni anticorpo'),
(68, 'Immuno fenotipo di popolazioni linfocitarie', 6, ''),
(69, 'Paptest', 6, 'Prelievo + esame citologico'),
(70, 'Recettori ormonali', 6, ''),
(71, 'ThinPrep', 6, 'Esame citologico vaginale su strato sottile in fase liquida'),
(72, 'Prelievo arterioso', 7, 'Domiciliare'),
(73, 'Prelievo arterioso', 7, 'Ambulatoriale'),
(74, 'Prelievo venoso o capillare', 7, 'Domiciliare'),
(75, 'Telecuore', 8, ''),
(76, 'Telecuore con esofago baritato', 8, ''),
(77, 'Clisma opaco', 9, ''),
(78, 'Clisma opaco a doppio contrasto', 9, ''),
(79, 'Colecistografia', 9, ''),
(80, 'Defecografia', 9, ''),
(81, 'RX addome', 9, ''),
(82, 'RX esofago con contrasto opaco', 9, ''),
(83, 'RX esofago', 9, 'Esame diretto'),
(84, 'RX faringe', 9, 'Esame diretto'),
(85, 'RX fegato, vie bilari, pancreas', 9, 'Esame diretto'),
(86, 'RX ghiandole salivari', 9, 'Esame diretto'),
(87, 'RX per studio selettivo di ultima ansa', 9, ''),
(88, 'RX piccoo intestino con doppio contrasto', 9, ''),
(89, 'RX stomaco doppio contrasto', 9, ''),
(90, 'RX tenue a doppio contrasto con studio selettivo', 9, ''),
(91, 'RX tenue seriato', 9, ''),
(92, 'RX tubo digerente', 9, 'Completo compreso esofago con contrasto'),
(93, 'RX tubo digerente', 9, 'Prime vie - esofago, stomaco, duodeno'),
(94, 'RX tubo digerente', 9, 'Seconde vie - tenue, colon'),
(95, 'Isterosalpingografia', 10, ''),
(96, 'RX A.T.M.', 11, ''),
(97, 'RX anca', 11, ''),
(98, 'RX arti inferiori sotto carico con bacino', 11, ''),
(99, 'RX assiali rotule', 11, ''),
(100, 'RX addome', 11, '30°/60°/90°'),
(101, 'RX avambraccio', 11, ''),
(102, 'RX avampiede', 11, ''),
(103, 'RX bacino', 11, ''),
(104, 'RX colonna vertebrale cervicale', 11, ''),
(105, 'RX colonna vertebrale completa', 11, ''),
(106, 'RX colonna vertebrale completa più bacino sotto carico', 11, ''),
(107, 'RX colonna vertebrale dorsale', 11, ''),
(108, 'RX colonna vertebralelombo-sacrale', 11, ''),
(109, 'RX colonna vertebrale sacro-coccigea', 11, ''),
(110, 'RX coscia', 11, ''),
(111, 'RX cranio', 11, ''),
(112, 'RX dita avampiede', 11, ''),
(113, 'RX dita mano', 11, ''),
(114, 'RX emimandibola', 11, ''),
(115, 'RX femore', 11, ''),
(116, 'RX forami ottici', 11, ''),
(117, 'RX gamba', 11, ''),
(118, 'RX ginocchio', 11, ''),
(119, 'RX gomito', 11, ''),
(120, 'RX piede', 11, ''),
(121, 'RX mano', 11, ''),
(122, 'RX omero', 11, ''),
(123, 'RX mastoide', 11, ''),
(124, 'RX polso', 11, ''),
(125, 'RX retropiede', 11, ''),
(126, 'RX scapola', 11, ''),
(127, 'RX spalla', 11, ''),
(128, 'RX sterno', 11, ''),
(129, 'RX torace e arto superiore monolaterale', 11, ''),
(130, 'Agoaspirato mammario ecoguidato', 12, ''),
(131, 'Biopsia ecoguidata', 12, 'Escluso esame citoistologico'),
(132, 'Core Biopsy', 12, ''),
(133, 'Ecocardiogramma', 12, ''),
(134, 'Ecografia addome completo', 12, ''),
(135, 'Ecocolordoppler tronchi sovraortici ', 12, ''),
(136, 'Ecografia endovasale', 12, ''),
(137, 'Ecografia mammaria bilaterale', 12, ''),
(138, 'Ecografia peniena dinamica', 12, 'Farmacologica'),
(139, 'Ecografia prostatica sovrapubica', 12, ''),
(140, 'Ecografia transrettale', 12, ''),
(141, 'Ecografia transesofagea', 12, ''),
(142, 'Firboscan', 12, ''),
(143, 'Flussimetria + ecografia', 12, ''),
(144, 'Isteroecosalpingografia', 12, ''),
(145, 'Monitoraggio ovulazione', 12, '1 ciclo'),
(146, 'Screening per displasia delle anche', 12, ''),
(147, 'Tempo svuotamento gastrico ecografico', 12, ''),
(148, 'Desitometria ortopedica periprotesica', 13, ''),
(149, 'Densitometria ossea total body', 13, ''),
(150, 'Densitometria ossea vertebrale e/o femorale', 13, ''),
(151, 'Angio TC distretti esocranici', 14, ''),
(152, 'Angio TC distretti intracranici', 14, ''),
(153, 'Biopsia TAC guidata', 14, 'escluso esame citoistologico'),
(154, 'Contrasto per tomografia assiale', 14, ''),
(155, 'Coronaro TC', 14, ''),
(156, 'Dentalscan 1 arcata', 14, ''),
(157, 'Dentalscan 2 arcate', 14, ''),
(158, 'Porto TC', 14, ''),
(159, 'Scialo TC', 14, ''),
(160, 'TC - RM', 14, ''),
(161, 'TC a fascio conico 3D', 14, ''),
(162, 'TC A.T.M. 3D bilaterale', 14, ''),
(163, 'TC A.T.M. monolaterale', 14, ''),
(164, 'TC addome completo', 14, ''),
(165, 'TC Calcium Score', 14, ''),
(166, 'TC cardiaca completa', 14, ''),
(167, 'TC colonna due metameri', 14, ''),
(168, 'TC cuore', 14, ''),
(169, 'TC per endoscopie virtuale', 14, ''),
(170, 'TC per fusione', 14, ''),
(171, 'TC per ogni organo', 14, ''),
(172, 'TC torace', 14, ''),
(173, 'TC Total Body', 14, ''),
(174, 'Angio RM grandi articolazioni', 15, 'Compreso contrasto'),
(175, 'Cine RM cuore', 15, ''),
(176, 'Colangio - RM', 15, ''),
(177, 'Contrasto paramgnetico', 15, ''),
(178, 'MRI Fetale', 15, ''),
(179, 'RM A.T.M.', 15, ''),
(180, 'RM arti o articolazioni', 15, ''),
(181, 'RM arto o articolazione aggiuntiva stessa seduta', 15, ''),
(182, 'RM cuore', 15, ''),
(183, 'RM distretto vascolare', 15, ''),
(184, 'RM multiparametrica della prostata', 15, ''),
(185, 'RM rachide', 15, 'Per segmento: cervicale, dorsale, lombare, sacro coccigeo'),
(186, 'Spettroscopia in MRI', 15, ''),
(187, 'Studio perfusionale', 15, ''),
(188, 'Trattografia MNR', 15, ''),
(189, 'Angiocardioscintigrafia all’equilibrio con calcolo parametri funzionali', 16, ''),
(190, 'Angiocardioscintigrafia primo transito con calcolo parametri funzionali', 16, ''),
(191, 'Scintigrafia miocardica con indicatori di lesione', 16, ''),
(192, 'Scintigrafia miocardica di perfusione a riposo e dopo test', 16, ''),
(193, 'Scintigrafia miocardica di perfusione a riposo', 16, ''),
(194, 'Tomoscintigrafia (SPET) del pool ematico cardiaco', 16, ''),
(195, 'Tomoscintigrafia (SPET) miocardica di perfusone a riposo e dopo test', 16, ''),
(196, 'Tomoscintigrafia (SPET) miocardica', 16, ''),
(197, 'Angioscintigrafia cerebrale con calcolo di indici emodinamici', 17, ''),
(198, 'Angioscintigrafia più scintigrafia cerebrale', 17, ''),
(199, 'Determinazione del flusso regionale quantitativo', 17, ''),
(200, 'Scintigrafia cerebrale con tracciante recettoriale (DATSCAN)', 17, ''),
(201, 'Tomoscintigrafia (SPET) cerebrale', 17, ''),
(202, 'Biopsia del linfonodo sentinella', 18, ''),
(203, 'PET', 18, 'Positron Emission Tomography per organo, distretto o apparato'),
(204, 'Ricerca del linfonodo sentinella e punto di repere', 18, ''),
(205, 'Scintigrafia totale corporea', 18, ''),
(206, 'Assistenza cardiologica intra-operatoria', 19, ''),
(207, 'Defibrillazione o cardioversione elettrica', 19, ''),
(208, 'Ecocardiogramma transesofageo ColorDoppler', 19, ''),
(209, 'Ecocardiogramma transtoracico M-B e ColorDoppler', 19, ''),
(210, 'Eco-stress', 19, ''),
(211, 'Elettrocardiogramma basale', 19, ''),
(212, 'Elettrocardiogramma dinamico secondo Holter 24 ore', 19, ''),
(213, 'Monitoraggio dinamico pressorio delle 24 ore', 19, ''),
(214, 'Pace-maker: controllo elettronico', 19, ''),
(215, 'Pace-maker: impianto temporaneo', 19, ''),
(216, 'Rianimazione cardiocircolatoria: massaggio cardiaco esterno', 19, ''),
(217, 'Studio elettrofisiologico trans-esofageo', 19, ''),
(218, 'Test ergometrico', 19, ''),
(219, 'Agobiopsia epatica percutanea', 20, ''),
(220, 'Anestesia/ sedazione per endoscopia', 20, ''),
(221, 'Enteroscopia con videocapsula', 20, ''),
(222, 'Esofagogastroduodenoscopia e test rapido per h. pylori', 20, ' Prestazione eseguinile in ambulatorio chirurgico, per le quali la Cassa non riconosce la applicazione della tariffa di sala operatoria'),
(223, 'Impedenzometria', 20, ''),
(224, 'Manometria esofagea', 20, ''),
(225, 'Ph impedenzometria esofagea', 20, '24 ore'),
(226, 'Ph metria esofagea', 20, '24 ore'),
(227, 'Rettosigmoidoscopia diagnostica', 20, 'Con biopsia. Prestazione eseguibile in ambulatorio chirurgico, per le quali la Cassa non riconosce la applicazione della tariffa di sala operatoria'),
(228, 'Test idrogeno respiratorio per proliferazione batterica nel tenue', 20, ''),
(229, 'Agoaspirati o biopsie di lesioni superficiali escluso esame citoistologico', 21, ''),
(230, 'Clamydia su striscio vaginale: prelievo per ricerca', 21, ''),
(231, 'Colposcopia più biopsia', 21, ''),
(232, 'Colposcopia più pap-test', 21, ''),
(233, 'Duo test', 21, ''),
(234, 'Herpes su striscio vaginale: prelievo per ricerca', 21, ''),
(235, 'HPV', 21, 'Materiale biologico - DNA test compreso prelievo'),
(236, 'Isteroscopia diagnostica con biopsia', 21, ''),
(237, 'Laparoscopia diagnostica e/o salpingocromoscopia', 21, ''),
(238, 'Pap net', 21, ''),
(239, 'Pap test', 21, 'Prelievo con esame citologico'),
(240, 'Striscio vaginale per ricerca epstein Bar virus', 21, ''),
(241, 'V.A.B.R.A. a scopo diagnostico', 21, ''),
(242, 'Amniocentesi', 22, 'Cariotipo su liquido amniotico compreso prelievo'),
(243, 'Anticorpi antifosfolipidici', 22, ''),
(244, 'Cardiotocografia', 22, ''),
(245, 'Cariotipo su aborto', 22, ''),
(246, 'Cariotipo su sangue periferico', 22, ''),
(247, 'Cromatina sessuale', 22, ''),
(248, 'Ecocardiografia fetale', 22, 'Non ripetibile nel corso della gravidanza'),
(249, 'Ecocolordoppler fetale', 22, ''),
(250, 'Ecografia fetale', 22, 'Massimo 4 per ciclo di gravidanza'),
(251, 'Ecografia morfologica', 22, 'Non ripetibile nel corso della gravidanza'),
(252, 'Estriolo plasmatico', 22, ''),
(253, 'Estriolo urinario', 22, ''),
(254, 'Estrone', 22, ''),
(255, 'Gonadotropina corionica', 22, ''),
(256, 'Ricerca DNA fetale nel sangue materno', 22, ''),
(257, 'Translucenza nucale - ultrascreen', 22, ''),
(258, 'Tri-test', 22, 'Esclusa ecografia'),
(259, 'Villocentesi', 22, ''),
(260, 'Assistenza cardiologica a parte', 16, '');

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `exam_all`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `exam_all` (
);

-- --------------------------------------------------------

--
-- Struttura della tabella `notification`
--

CREATE TABLE `notification` (
  `id_notify` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

--
-- Dump dei dati per la tabella `notification`
--

INSERT INTO `notification` (`id_notify`, `user`, `title`, `content`) VALUES
(6, 5, 'Prenotazione confermata', 'La tua prenotazione è stata confermata'),
(7, 5, 'Prenotazione confermata', 'La tua prenotazione è stata confermata'),
(9, 37, 'Nuova prescrizione creata', 'E\' stata prescritta una nuova visita specialistica per te: Visita cariotipo'),
(10, 148, 'Nuova prescrizione creata', 'Ti e\' stata prescritta una nuova ricetta per il medicinale HUMIRA'),
(11, 148, 'Nuova prescrizione creata', 'E\' stato prescritto un nuovo esame per te: Adrenalina plasmatica'),
(12, 148, 'Nuova prescrizione creata', 'E\' stata prescritta una nuova visita specialistica per te: Visita FISH'),
(13, 37, 'Prenotazione confermata', 'La tua prenotazione è stata confermata');

-- --------------------------------------------------------

--
-- Struttura della tabella `patient`
--

CREATE TABLE `patient` (
  `id_pat` int(11) NOT NULL,
  `ssn` char(16) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `birthday` datetime NOT NULL,
  `birthplace` varchar(30) NOT NULL,
  `sex` char(1) NOT NULL,
  `familydoctor` int(11) NOT NULL DEFAULT 0,
  `pdistrict` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `patient`
--

INSERT INTO `patient` (`id_pat`, `ssn`, `firstname`, `lastname`, `birthday`, `birthplace`, `sex`, `familydoctor`, `pdistrict`) VALUES
(1, 'null', 'null', 'null', '1970-01-01 00:00:00', 'null', 'M', 1, 1),
(2, 'SCLLND53B61G337D', 'Iolanda', 'Siciliani', '1953-02-21 00:00:00', 'Parma', 'F', 61, 98),
(3, 'FRNMRN34C23D086F', 'Marino', 'Fiorentino', '1934-03-23 00:00:00', 'Cosenza', 'M', 52, 98),
(4, 'LROSFN74D61L750O', 'Stefania', 'Lori', '1974-04-21 00:00:00', 'Thiene', 'F', 150, 109),
(5, 'RSSVNT99R06I829T', 'Violante', 'Rossi', '1999-10-26 00:00:00', 'Sondrio', 'M', 52, 98),
(6, 'FRRVCN81T19E506J', 'Vincenzo', 'Ferri', '1981-12-19 00:00:00', 'Lecce', 'M', 55, 6),
(7, 'PSNLRN40L42E783B', 'Lorna', 'Pisano', '1940-07-02 00:00:00', 'Macerata', 'F', 56, 6),
(8, 'LGGLNE05L31D542U', 'Eliano', 'Loggia', '2005-07-31 00:00:00', 'Fermo', 'M', 57, 98),
(9, 'SGSMCL23B49C352G', 'Marcella', 'Sagese', '1923-02-09 00:00:00', 'Catanzaro', 'F', 58, 98),
(10, 'TRNLNS69P01A859Q', 'Alfonso', 'Trentini', '1969-09-01 00:00:00', 'Biella', 'M', 59, 98),
(11, 'MNNFTN35H56C632B', 'Fortunata', 'Manna', '1935-06-16 00:00:00', 'Chieti', 'F', 60, 98),
(12, 'DLLTTR91S06E202F', 'Ettore', 'Dellucci', '1991-11-06 00:00:00', 'Grosseto', 'M', 61, 98),
(13, 'GLLPIA88E54A182X', 'Pia', 'Gallo', '1988-05-14 00:00:00', 'Alessandria', 'F', 62, 98),
(14, 'CLMPRN07R67G388T', 'Pierina', 'Colombo', '2007-10-27 00:00:00', 'Pavia', 'F', 63, 98),
(15, 'BCCPRZ72D51F257O', 'Patrizia', 'Bucco', '1972-04-11 00:00:00', 'Modena', 'F', 64, 98),
(16, 'DLLFNZ27R49F979P', 'Fiorenza', 'Dellucci', '1927-10-09 00:00:00', 'Nuoro', 'F', 65, 98),
(17, 'RCCVNT95C04E648B', 'Valente', 'Ricci', '1995-03-04 00:00:00', 'Lodi', 'M', 66, 98),
(18, 'LCCLVR64E51G482G', 'Elvira', 'Lucchesi', '1964-05-11 00:00:00', 'Pescara', 'F', 67, 98),
(19, 'TRVRCC74T27G113D', 'Rocco', 'Trevisan', '1974-12-27 00:00:00', 'Oristano', 'M', 68, 98),
(20, 'BCCRMN55M16H199O', 'Erminio', 'Bucci', '1955-08-16 00:00:00', 'Ravenna', 'M', 69, 98),
(21, 'LRNMLD01H51H163A', 'Mafalda', 'Laurentis', '2001-06-11 00:00:00', 'Ragusa', 'F', 70, 98),
(22, 'MNCLCN64L69H282K', 'Luciana', 'Mancini', '1964-07-29 00:00:00', 'Rieti', 'F', 51, 98),
(23, 'MNCGLL75A43L750V', 'Guglielmi', 'Mancini', '1975-01-03 00:00:00', 'Vercelli', 'F', 52, 98),
(24, 'SCHMRC61P30L117B', 'Marco', 'Schiavone', '1961-09-30 00:00:00', 'Terni', 'M', 53, 98),
(25, 'MZZMRZ89B14L103L', 'Maurizio', 'Mazzanti', '1989-02-14 00:00:00', 'Teramo', 'M', 54, 98),
(26, 'RCRDLF47C24L049V', 'Delfio', 'Arcuri', '1947-03-24 00:00:00', 'Taranto', 'M', 55, 6),
(27, 'DRSLSU39P51E472Y', 'Luisa', 'DeRose', '1939-09-11 00:00:00', 'Latina', 'F', 56, 6),
(28, 'BRSCNL77S01E507C', 'Cornelio', 'Barese', '1977-11-01 00:00:00', 'Lecco', 'M', 57, 98),
(29, 'BRSRNR94A19D548O', 'Raniero', 'Barese', '1994-01-19 00:00:00', 'Ferrara', 'M', 58, 98),
(30, 'SBAVNR57T53B963X', 'Venera', 'Saba', '1957-12-13 00:00:00', 'Caserta', 'F', 59, 98),
(31, 'MGGNNA03M47A509T', 'Anna', 'Meggiorini', '2003-08-07 00:00:00', 'Avellino', 'F', 60, 98),
(32, 'BJRFRC98M31D562F', 'Federico', 'Bajardo', '1998-08-31 00:00:00', 'Fiano', 'M', 61, 98),
(33, 'BNOSTR86H46E202S', 'Ester', 'Boni', '1986-06-06 00:00:00', 'Grosseto', 'F', 62, 98),
(34, 'TNAGNE63D28E069G', 'Eugenio', 'Tani', '1963-04-28 00:00:00', 'Glorenza', 'M', 63, 98),
(35, 'TRTSMN72D12D270M', 'Simone', 'Tartari', '1972-04-12 00:00:00', 'Dello', 'M', 64, 98),
(36, 'VSCSIA83A01L317Z', 'Isaia', 'Vischio', '1983-01-01 00:00:00', 'Trabia', 'M', 65, 98),
(37, 'BRNLNZ48B18G743I', 'Lorenza', 'Brunelli', '1948-02-18 00:00:00', 'Pocenia', 'F', 66, 98),
(38, 'MZZGBB57P26A055C', 'Giacobbe', 'Mazzini', '1957-09-26 00:00:00', 'Adelfia', 'M', 67, 98),
(39, 'PRRMRM71C45F253S', 'Miriam', 'Porricino', '1971-03-05 00:00:00', 'Moano', 'F', 68, 98),
(40, 'SVARNN97S11C667J', 'Aronne', 'Savoia', '1997-11-11 00:00:00', 'Ciago', 'M', 69, 98),
(41, 'CHCGTT94B02I775B', 'Giuditta', 'Checchini', '1994-02-02 00:00:00', 'Soave', 'F', 70, 98),
(42, 'GCPSRA36E64H097N', 'Sara', 'Giacopuzzi', '1936-05-24 00:00:00', 'Quadrelle', 'F', 51, 98),
(43, 'DGSSIA44H03D689Q', 'Isaia', 'DeAgostini', '1944-06-03 00:00:00', 'Forano', 'M', 52, 98),
(44, 'ZMBQLT69R09E227Z', 'Qoelet', 'Zamboni', '1969-10-09 00:00:00', 'Guagnano', 'M', 53, 98),
(45, 'MRZCHR80A41M184R', 'Chiara', 'Marzotto', '1980-01-01 00:00:00', 'Zogno', 'F', 54, 98),
(46, 'BNDMCH82L27L317U', 'Michea', 'Benedetti', '1982-07-27 00:00:00', 'Trabbia', 'M', 55, 6),
(47, 'SPSPQL04P70C352C', 'Pasqualina', 'Esposito', '2004-09-30 00:00:00', 'Catanzaro', 'F', 56, 6),
(48, 'ZNNRNN88C16D361O', 'Aronne', 'Zanoni', '1988-03-16 00:00:00', 'Dragoni', 'M', 57, 98),
(49, 'LVTCLL79S47H280G', 'Camilla', 'Lovato', '1979-11-07 00:00:00', 'Riese', 'F', 58, 98),
(50, 'FLLZHL62B07L525Y', 'Ezechiele', 'Fallaci', '1962-02-07 00:00:00', 'Vacone', 'M', 59, 98),
(51, 'LDAGRC47F17M152Y', 'Alide', 'Greco', '1947-03-17 00:00:00', 'Fasano', 'F', 52, 98),
(52, 'FDNMLN39M03D057M', 'Fidenzio', 'Milanesi', '1939-01-03 00:00:00', 'Quinto', 'M', 53, 98),
(53, 'LGEMRC72M02M111K', 'Eligio', 'Marchesi', '1972-09-02 00:00:00', 'Pavia', 'M', 54, 98),
(54, 'RLACCC49S08l112M', 'Aurelia', 'Cocci', '1949-11-08 00:00:00', 'Sueglio', 'F', 55, 98),
(55, 'CRMPLM61G04A002X', 'Carmine', 'Palerma', '1961-07-04 00:00:00', 'Merana', 'M', 56, 6),
(56, 'JLONOI93P03R300R', 'Jole', 'Onio', '1993-10-03 00:00:00', 'Negrar', 'F', 55, 6),
(57, 'MRCMND43F25P091D', 'Marco', 'Monaldo', '1943-05-25 00:00:00', 'Collecroce', 'M', 58, 98),
(58, 'FLBCLR45G17B209G', 'Filiberta', 'Calabresi', '1945-07-17 00:00:00', 'Acquasanta', 'F', 59, 98),
(59, 'FRNMLN69B04S123D', 'Fernando', 'Milani', '1969-02-04 00:00:00', 'Moiarello', 'M', 60, 98),
(60, 'MRZTRT83B07F161S', 'Maurizio', 'Trentini', '1983-02-07 00:00:00', 'Mestrino', 'M', 61, 98),
(61, 'TTLRCC34D18T199D', 'Attilio', 'Ricci', '1934-04-18 00:00:00', 'Moriago', 'M', 62, 98),
(62, 'GLLDNS88M30Q167F', 'Galileo', 'Udinesi', '1988-09-30 00:00:00', 'Lusiana', 'M', 63, 98),
(63, 'FRZLMB55P09K112K', 'Fiorenza', 'Lombardi', '1955-10-09 00:00:00', 'Amatrice', 'F', 64, 98),
(64, 'GNDPGN79A26A060D', 'Guendalina', 'Pagnotto', '1979-01-26 00:00:00', 'Novara', 'F', 65, 98),
(65, 'SLVLNG71G22G123F', 'Silvia', 'Logno', '1971-08-22 00:00:00', 'Sovramonte', 'F', 66, 98),
(66, 'MRCGRD77G16L157Y', 'Marco', 'Giordano', '1977-07-16 00:00:00', 'Marengo', 'M', 67, 98),
(67, 'BRNDVG75S24R129A', 'Bruno', 'Edvige', '1975-11-25 00:00:00', 'Castelseprio', 'M', 68, 98),
(68, 'LBRTRV45T21D225P', 'Liberata', 'Trevisani', '1945-12-21 00:00:00', 'Monte San Giovanni', 'F', 69, 98),
(69, 'RBNLGG81F31l199K', 'Urbano', 'Loggia', '1981-05-31 00:00:00', 'Forno', 'M', 70, 98),
(70, 'LVSCRM68M12H278M', 'Alvise', 'Cremonesi', '1968-08-28 00:00:00', 'Gagliato', 'M', 51, 98),
(71, 'PPLMLN85B04M212H', 'Ippolito', 'Milani', '1985-02-04 00:00:00', 'Lugugnana', 'M', 51, 98),
(72, 'RTMSAL59P21J001A', 'Artemisia', 'Sal', '1959-10-21 00:00:00', 'Sangineto', 'F', 52, 98),
(73, 'NSLMMN38N13A321D', 'Anselmo', 'Mancini', '1938-09-13 00:00:00', 'Retorbido', 'M', 53, 98),
(74, 'CRSTRV85B23D336L', 'Cristoforo', 'Trevisan', '1985-02-23 00:00:00', 'Roma', 'M', 54, 98),
(75, 'RSSBRS53F13G281L', 'Rossana', 'Barese', '1953-05-13 00:00:00', 'Fiascherino', 'F', 55, 98),
(76, 'VNIMNC88R18F256K', 'Ivano', 'Mancini', '1988-11-18 00:00:00', 'Lusiana', 'M', 63, 98),
(77, 'VTTSCH65N26R291U', 'Vittoria', 'Schiavone', '1965-09-26 00:00:00', 'Marcialla', 'F', 57, 98),
(78, 'RLRPNT57M26E222P', 'Raul', 'Pinto', '1957-08-26 00:00:00', 'Cadipietra', 'M', 58, 98),
(79, 'CRMPLR80L29X190H', 'Carmen', 'Palerma', '1980-06-29 00:00:00', 'Rio NellElba', 'F', 59, 98),
(80, 'GRTGRD48P18D123G', 'Greta', 'Giordano', '1949-10-18 00:00:00', 'Cevo', 'F', 60, 98),
(81, 'NNNFLL59A11D232F', 'Nino', 'Folliero', '1959-01-11 00:00:00', 'Firenze', 'M', 61, 98),
(82, 'SMNFLL57D04F321L', 'Simone', 'Fallaci', '1957-04-04 00:00:00', 'Castel dAcquila', 'M', 62, 98),
(83, 'BNGRSS85B07J299L', 'Benigno', 'Rossi', '1985-02-07 00:00:00', 'Su Pinu', 'M', 63, 98),
(84, 'NRNGNV59R08G333M', 'Nerea', 'Genovese', '1958-11-08 00:00:00', 'Frusci', 'F', 64, 98),
(85, 'MTTFLL83C08X200U', 'Matteo', 'Cancaro', '1983-03-08 00:00:00', 'Arena Veronese', 'M', 65, 98),
(148, 'TRNMHL12M111AAD', 'Michele', 'Tran', '1998-02-11 14:28:19', 'Thiene', 'M', 150, 109),
(149, 'FRNPET12A231YAW', 'Francesco', 'Petroli', '1998-09-11 19:09:21', 'Negrar', 'M', 150, 109),
(150, 'FRRMTT97B169ANM', 'Matteo', 'Frare', '1997-09-11 19:01:21', 'Revine Lago', 'M', 69, 98),
(151, 'VSCGCM98A192NAS', 'Giacomo', 'Vischio', '1998-02-12 19:28:12', 'Thiene', 'F', 150, 109);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `patient4chs3doct`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `patient4chs3doct` (
`id_pat` int(11)
,`district` int(11)
,`ssn` char(16)
,`patname` varchar(61)
,`id_doc` int(11)
,`docname` varchar(61)
,`birthday` datetime
,`lastvisit` datetime
,`examstodo` bigint(21)
);

-- --------------------------------------------------------

--
-- Struttura della tabella `photo`
--

CREATE TABLE `photo` (
  `id_photo` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `path` varchar(100) NOT NULL,
  `data` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `photo`
--

INSERT INTO `photo` (`id_photo`, `user`, `path`, `data`) VALUES
(1, 2, '/data/IolandaSiciliani/foto001.jpeg', '2019-05-07 00:43:15'),
(2, 2, '/data/IolandaSiciliani/foto002.jpeg', '2019-07-31 16:14:51'),
(3, 2, '/data/IolandaSiciliani/foto003.jpeg', '2019-08-12 06:19:15'),
(5, 4, '/data/StefaniaLori/foto001.jpeg', '2019-04-15 02:05:29'),
(6, 4, '/data/StefaniaLori/foto002.jpeg', '2019-05-14 05:33:10'),
(7, 5, '/data/ViolanteRossi/foto001.jpeg', '2019-06-11 12:15:44'),
(8, 5, '/data/ViolanteRossi/foto002.jpeg', '2019-08-15 15:00:59'),
(9, 5, '/data/ViolanteRossi/foto003.jpeg', '2019-08-29 05:12:59'),
(10, 6, '/data/VincenzoFerri/foto001.jpeg', '2000-01-01 00:00:00'),
(11, 7, '/data/LornaPisano/foto001.jpeg', '2019-05-13 13:19:06'),
(12, 7, '/data/LornaPisano/foto002.jpeg', '2019-08-28 08:38:28'),
(13, 8, '/data/ElianoLoggia/foto001.jpeg', '2018-11-19 06:35:30'),
(14, 8, '/data/ElianoLoggia/foto002jpeg', '2019-08-20 22:37:25'),
(15, 9, '/data/MarcellaSagese/foto001.jpeg', '2019-08-21 14:07:35'),
(16, 10, '/data/AlfonsoTrentini/foto001.jpeg', '2019-08-12 17:28:16'),
(17, 11, '/data/FortunataManna/foto001.jpeg', '2019-08-27 02:59:24'),
(18, 12, '/data/EttoreDellucci/foto001.jpeg', '2019-05-06 05:39:20'),
(19, 13, '/data/PiaGallo/foto001.jpeg', '2019-08-28 06:47:34'),
(20, 13, '/data/PiaGallo/foto002.jpeg', '2019-08-30 16:24:26'),
(21, 14, '/data/PierinaColombo/foto001.jpeg', '2019-09-02 06:51:46'),
(22, 15, '/data/PatriziaBucco/foto001.jpeg', '2019-03-04 07:40:35'),
(23, 16, '/data/FiorenzaDellucci/foto001.jpeg', '2019-02-26 19:36:25'),
(24, 17, '/data/ValenteRicci/foto001.jpeg', '2017-09-13 15:27:50'),
(25, 17, '/data/ValenteRicci/foto002.jpeg', '2019-01-22 06:31:30'),
(27, 17, '/data/ValenteRicci/foto003.jpeg', '2019-09-03 09:31:22'),
(28, 18, '/data/ElviraLucchesi/foto001.jpeg', '2019-05-23 14:23:12'),
(29, 19, '/data/RoccoTrevisan/foto001.jpeg', '2019-08-20 15:08:29'),
(30, 20, '/data/ErminioBucci/foto001.jpeg', '2019-03-28 21:39:17'),
(31, 21, '/data/MafaldaLaurentis/foto001.jpeg', '2019-08-13 16:00:24'),
(32, 22, '/data/LucianaMancini/foto001.jpeg', '2019-08-19 04:31:45'),
(33, 23, '/data/GuglielmiMancini/foto001.jpeg', '2019-08-26 02:44:25'),
(34, 24, '/data/MarcoSchiavone/foto001.jpeg', '2019-08-20 04:49:29'),
(35, 25, '/data/MaurizioMazzanti/foto001.jpeg', '2018-10-25 16:13:32'),
(36, 25, '/data/MaurizioMazzanti/foto002.jpeg', '2019-08-20 17:54:59'),
(37, 26, '/data/DelfioArcuri/foto001.jpeg', '2019-08-20 04:08:24'),
(38, 27, '/data/LuisaDeRose/foto001.jpeg', '2019-02-14 06:40:23'),
(39, 28, '/data/CornelloBarese/foto001.jpeg', '2019-03-18 00:59:06'),
(40, 29, '/data/RanieroBarese/foto001.jpeg', '2019-06-04 00:36:26'),
(41, 30, '/data/VeneraSaba/foto001.jpeg', '2019-08-22 23:19:06'),
(42, 31, '/data/AnaMeggiorini/foto001.jpeg', '2019-08-14 03:59:06'),
(43, 32, '/data/FedericoBajardo/foto001.jpeg', '2019-06-11 00:36:23'),
(44, 33, '/data/EsterBoni/foto001.jpeg', '2019-02-11 04:36:13'),
(45, 33, '/data/EsterBoni/foto002.jpeg', '2019-06-18 20:19:06'),
(46, 33, '/data/EsterBoni/foto003.jpeg', '2019-08-12 00:00:00'),
(47, 34, '/data/EugenioTani/foto001.jpeg', '2019-08-14 00:59:06'),
(48, 35, '/data/SimoneTartari/foto001.jpeg', '2019-08-16 23:59:59'),
(49, 36, '/data/IsaiaVischio/foto001.jpeg', '2019-05-15 06:40:22'),
(50, 37, '/data/LorenzaBrunelli/foto001.jpeg', '2019-08-12 23:56:06'),
(51, 38, '/data/GiacobbeMazzini/foto001.jpeg', '2019-08-01 15:19:06'),
(52, 39, '/data/MiriamPorricino/foto001.jpeg', '2019-05-20 00:40:18'),
(53, 40, '/data/AronneSavoia/foto001.jpeg', '2018-12-04 00:59:06'),
(54, 41, '/data/GiudittaChecchini/foto001.jpeg', '2018-02-22 07:41:29'),
(55, 41, '/data/GiudittaChecchini/foto002.jpeg', '2019-05-07 17:26:30'),
(56, 41, '/data/GiudittaChecchini/foto003.jpeg', '2019-08-29 04:19:30'),
(57, 42, '/data/SaraGiacopuzzi/foto001.jpeg', '2018-07-26 00:38:18'),
(58, 42, '/data/SaraGiacopuzzi/foto002.jpeg', '2019-04-17 10:19:51'),
(59, 43, '/data/IsaiaDeAgostini/foto001.jpeg', '2019-08-12 01:19:06'),
(60, 44, '/data/QoeletZamboni/foto001.jpeg', '2015-05-15 18:38:28'),
(61, 44, '/data/QoeletZamboni/foto002.jpeg', '2017-12-12 00:19:06'),
(62, 45, '/data/ChiaraMarzotto/foto001.jpeg', '2019-02-12 10:28:06'),
(63, 46, '/data/MicheaBenedetti/foto001.jpeg', '2019-08-13 10:19:06'),
(64, 47, '/data/PasqualinaEsposito/foto001.jpeg', '2018-03-06 20:19:06'),
(65, 47, '/data/PasqualinaEsposito/foto002.jpeg', '2018-06-05 07:19:06'),
(66, 47, '/data/PasqualinaEsposito/foto003.jpeg', '2018-06-05 18:19:06'),
(67, 48, '/data/AronneZanoni/foto001.jpeg', '2019-04-16 10:19:42'),
(68, 49, '/data/CamillaLovato/foto001.jpeg', '2019-08-06 00:29:06'),
(69, 50, '/data/EzechieleFallaci/foto001.jpeg', '2019-08-21 22:28:13'),
(70, 51, '/data/AlideGreco/foto001.jpeg', '2019-04-01 01:29:24'),
(71, 51, '/data/AlideGreco/foto002.jpeg', '2019-08-30 10:50:55'),
(72, 52, '/data/FidenzioMilanesi/foto001.jpeg', '2019-08-30 10:50:55'),
(73, 53, '/data/EligioMarchesi/foto001.jpeg', '2019-08-30 10:50:55'),
(74, 54, '/data/AureliaCocci/foto001.jpeg', '2019-08-30 10:50:55'),
(75, 55, '/data/CarminePalerma/foto001.jpeg', '2018-11-14 04:50:55'),
(76, 55, '/data/CarminePalerma/foto002.jpeg', '2019-08-30 10:50:55'),
(77, 56, '/data/JoleOnio/foto001.jpeg', '2019-08-30 10:50:55'),
(78, 57, '/data/MarcoMonaldo/foto001.jpeg', '2019-08-30 10:50:55'),
(79, 58, '/data/FilibertaCalabresi/foto001.jpeg', '2018-10-17 13:09:27'),
(80, 58, '/data/FilibertaCalabresi/foto002.jpeg', '2019-08-30 10:50:55'),
(81, 59, '/data/FernandoMilani/foto001.jpeg', '2019-08-30 10:50:55'),
(82, 60, '/data/MaurizioTrentini/foto001.jpeg', '2019-08-30 10:50:55'),
(83, 61, '/data/AttilioRicci/foto001.jpeg', '2019-08-30 10:50:55'),
(84, 62, '/data/GalileoUdinesi/foto001.jpeg', '2019-08-30 10:50:55'),
(85, 63, '/data/FiorenzaLombardi/foto001.jpeg', '2019-08-30 10:50:55'),
(86, 64, '/data/GuendalinaPagnotto/foto001.jpeg', '2019-08-30 10:50:55'),
(87, 65, '/data/SilviaLogno/foto001.jpeg', '2019-08-30 10:50:55'),
(88, 66, '/data/MarcoGiordano/foto001.jpeg', '2019-08-30 10:50:55'),
(89, 67, '/data/BrunoEdvige/foto001.jpeg', '2019-08-30 10:50:55'),
(90, 68, '/data/LiberataTrevisani/foto001.jpeg', '2019-08-30 10:50:55'),
(91, 69, '/data/UrbanoLoggia/foto001.jpeg', '2019-08-30 10:50:55'),
(92, 70, '/data/AlviseCremonesi/foto001.jpeg', '2016-12-22 20:00:15'),
(93, 70, '/data/AlviseCremonesi/foto002.jpeg', '2019-04-16 23:50:55'),
(94, 70, '/data/AlviseCremonesi/foto003.jpeg', '2019-08-30 10:50:55'),
(95, 71, '/data/IppolitoMilani/foto001.jpeg', '2019-08-30 10:50:55'),
(96, 72, '/data/ArtemisiaSal/foto001.jpeg', '2019-08-30 10:50:55'),
(97, 73, '/data/AnselmoMancini/foto001.jpeg', '2019-08-30 10:50:55'),
(98, 74, '/data/CristoforoTrevisan/foto001.jpeg', '2019-08-30 10:50:55'),
(99, 75, '/data/RossanaBarese/foto001.jpeg', '2019-08-30 10:50:55'),
(100, 76, '/data/IvanoMancini/foto001.jpeg', '2019-08-30 10:50:55'),
(101, 77, '/data/VittoriaSchiavone/foto001.jpeg', '2019-08-30 10:50:55'),
(102, 78, '/data/RaulPinto/foto001.jpeg', '2019-08-30 10:50:55'),
(103, 79, '/data/CarmenPalerma/foto001.jpeg', '2019-08-30 10:50:55'),
(104, 80, '/data/GretaGiordano/foto001.jpeg', '2018-12-11 18:17:50'),
(105, 80, '/data/GretaGiordano/foto002.jpeg', '2019-08-30 10:50:55'),
(106, 81, '/data/NinoFolliero/foto001.jpeg', '2019-08-30 10:50:55'),
(107, 82, '/data/SimoneFallaci/foto001.jpeg', '2019-08-30 10:50:55'),
(108, 83, '/data/BenignoRossi/foto001.jpeg', '2019-08-30 10:50:55'),
(109, 84, '/data/NereaGenovese/foto001.jpeg', '2019-08-30 10:50:55'),
(110, 85, '/data/MatteoCancaro/foto001.jpeg', '2019-08-30 10:50:55'),
(138, 3, '/data/MarinoFiorentino/foto001.jpeg', '2019-09-01 10:45:18'),
(142, 17, '/data/ValenteRicci/foto004.jpeg', '2019-09-03 13:07:12'),
(143, 17, '/data/ValenteRicci/foto005.jpeg', '2019-09-03 13:07:20'),
(149, 149, '/data/FrancescoPetroli/foto001.jpeg', '2019-09-07 19:06:50');

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `prescrforlist`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `prescrforlist` (
`id_disp` int(11)
,`patient` int(11)
,`cardname` varchar(100)
,`prescrby` varchar(61)
,`madeby` varchar(30)
,`prescrdate` datetime
,`madedate` datetime
);

-- --------------------------------------------------------

--
-- Struttura della tabella `prescription`
--

CREATE TABLE `prescription` (
  `id_pres` int(11) NOT NULL,
  `drug` int(11) NOT NULL,
  `quantity` int(11) DEFAULT 1,
  `active` int(11) DEFAULT 1,
  `drugstore` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `prescription`
--

INSERT INTO `prescription` (`id_pres`, `drug`, `quantity`, `active`, `drugstore`) VALUES
(4, 19, 1, 0, 152),
(5, 102, 2, 1, 152),
(6, 104, 1, 1, 152),
(9, 103, 3, 1, 152),
(71, 3, 1, 0, 86),
(72, 4, 5, 0, 96),
(73, 8, 4, 0, 106),
(74, 24, 3, 0, 86),
(75, 76, 2, 0, 98),
(76, 87, 3, 0, 95),
(77, 34, 1, 0, 87),
(78, 98, 4, 1, 1),
(79, 23, 3, 0, 100),
(80, 9, 2, 0, 93),
(81, 87, 1, 1, 1),
(82, 90, 2, 1, 1),
(83, 89, 3, 0, 95),
(84, 54, 1, 0, 100),
(85, 87, 4, 1, 1),
(86, 45, 3, 0, 88),
(87, 87, 1, 1, 1),
(88, 34, 3, 0, 96),
(89, 65, 2, 0, 92),
(90, 23, 3, 0, 95),
(91, 54, 2, 1, 1),
(92, 76, 2, 0, 96),
(93, 34, 2, 0, 86),
(94, 87, 1, 0, 91),
(95, 97, 1, 0, 89),
(96, 99, 2, 0, 96),
(97, 56, 3, 1, 1),
(98, 23, 3, 1, 1),
(99, 25, 2, 0, 95),
(100, 34, 2, 0, 100),
(101, 65, 1, 1, 1),
(102, 45, 1, 0, 99),
(103, 43, 1, 1, 1),
(104, 76, 1, 0, 97),
(105, 98, 3, 0, 95),
(106, 54, 3, 0, 89),
(107, 43, 3, 0, 89),
(108, 23, 3, 1, 1),
(109, 97, 2, 0, 97),
(110, 99, 2, 1, 1),
(111, 87, 2, 1, 1),
(112, 65, 1, 0, 86),
(113, 45, 1, 0, 100),
(114, 67, 1, 0, 88),
(115, 34, 2, 0, 87),
(116, 65, 2, 1, 1),
(117, 33, 3, 0, 88),
(118, 87, 4, 1, 1),
(119, 55, 4, 1, 1),
(120, 43, 4, 1, 1),
(121, 23, 2, 0, 93),
(122, 87, 1, 0, 100),
(123, 54, 2, 0, 92),
(124, 98, 4, 1, 1),
(125, 93, 3, 1, 1),
(126, 34, 2, 0, 89),
(127, 12, 3, 0, 98),
(128, 3, 1, 0, 87),
(129, 8, 3, 0, 87),
(130, 7, 2, 0, 89),
(131, 56, 3, 0, 86),
(132, 54, 1, 0, 93),
(133, 13, 4, 0, 100),
(134, 65, 3, 0, 92),
(135, 34, 2, 1, 1),
(136, 96, 3, 1, 1),
(137, 45, 1, 0, 86),
(138, 76, 1, 0, 98),
(139, 34, 2, 0, 89),
(140, 66, 1, 1, 1),
(141, 33, 2, 0, 88),
(142, 87, 3, 0, 98),
(143, 56, 3, 1, 1),
(144, 98, 3, 0, 97),
(145, 34, 2, 1, 1),
(146, 65, 1, 0, 99),
(147, 38, 2, 0, 87),
(148, 74, 1, 0, 89),
(149, 37, 2, 1, 1),
(150, 63, 1, 0, 93),
(151, 86, 0, 0, 97),
(214, 19, 3, 1, 1),
(227, 19, 3, 0, 86),
(257, 53, 3, 1, 1),
(259, 19, 4, 0, 152),
(289, 19, 3, 1, 1),
(291, 5, 1, 1, 1),
(293, 9, 1, 1, 1),
(299, 5, 1, 0, 152),
(301, 104, 3, 1, 1),
(302, 23, 1, 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `psw_temporanee`
--

CREATE TABLE `psw_temporanee` (
  `id_psw` int(11) NOT NULL,
  `id_utente` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  `stringacasuale` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `psw_temporanee`
--

INSERT INTO `psw_temporanee` (`id_psw`, `id_utente`, `timestamp`, `stringacasuale`) VALUES
(5, 2, '2019-08-27 15:27:06', 'X3W4w5Y6n5D4m1q0O4c4'),
(6, 5, '2019-08-29 15:13:32', 'r4b5Y3o9G9x3q8R7C9l0'),
(7, 5, '2019-08-29 15:17:50', 'L0n2m5X7B8A2I7R0f7K9'),
(8, 5, '2019-08-29 15:18:53', 'o2r2z3C9c8Z6u6o8b0k4'),
(9, 17, '2019-09-03 11:55:01', 'P4T1o0Q9G3Z5v2K8T0s0');

-- --------------------------------------------------------

--
-- Struttura della tabella `speckvisit`
--

CREATE TABLE `speckvisit` (
  `id_spvt` int(11) NOT NULL,
  `specialist` int(11) DEFAULT 1,
  `visitcode` int(11) NOT NULL,
  `result` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `speckvisit`
--

INSERT INTO `speckvisit` (`id_spvt`, `specialist`, `visitcode`, `result`) VALUES
(8, 151, 28, ''),
(152, 72, 46, 'RIP'),
(153, 73, 3, ' '),
(154, 76, 42, ' '),
(155, 84, 39, ' '),
(156, 73, 26, ' '),
(157, 83, 19, ' '),
(158, 78, 24, ' '),
(159, 77, 33, ' '),
(160, 81, 27, ' '),
(161, 78, 39, ' '),
(162, 79, 13, ' '),
(163, 82, 22, ' '),
(164, 84, 2, ' '),
(165, 78, 6, ' '),
(166, 77, 5, ' '),
(167, 83, 36, ' '),
(168, 76, 25, ' '),
(169, 85, 33, ' '),
(170, 74, 18, ' '),
(171, 77, 43, ' '),
(172, 74, 27, ' '),
(173, 71, 34, 'RIPPP\r\n                            \r\n                                \r\n                            \r\n                                \r\n                            '),
(174, 85, 18, ' '),
(175, 83, 26, ' '),
(176, 77, 13, ' '),
(177, 82, 36, ' '),
(178, 77, 22, ' '),
(179, 83, 3, ' '),
(180, 78, 4, ' '),
(181, 77, 35, ' '),
(182, 73, 27, ' '),
(183, 78, 14, ' '),
(184, 83, 41, ' '),
(185, 82, 28, ' '),
(186, 77, 15, ' '),
(187, 78, 32, ' '),
(188, 76, 24, ' '),
(189, 82, 35, ' '),
(190, 75, 16, ' '),
(191, 80, 18, ' '),
(192, 73, 19, ' '),
(193, 85, 38, ' '),
(194, 73, 20, ' '),
(195, 82, 6, ' '),
(196, 78, 36, ' '),
(197, 82, 9, ' '),
(198, 79, 26, ' '),
(199, 77, 43, ' '),
(200, 76, 32, ' '),
(201, 75, 16, 'risultato test negativo'),
(202, 77, 20, ' '),
(203, 80, 27, ' '),
(204, 76, 34, ' '),
(205, 80, 27, ' '),
(206, 76, 13, ' '),
(207, 74, 37, ' '),
(208, 81, 23, ' '),
(209, 74, 42, ' '),
(210, 83, 23, ' '),
(211, 78, 13, ' '),
(260, 1, 28, NULL),
(273, 71, 13, NULL),
(283, 1, 18, NULL),
(286, 1, 13, NULL),
(287, 1, 36, NULL),
(290, 73, 33, NULL),
(292, 1, 4, NULL),
(300, 1, 4, NULL),
(304, 1, 23, NULL);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `ticket`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `ticket` (
`id_disp` int(11)
,`patient` int(11)
,`familydoctor` int(11)
,`visit` int(11)
,`paid` tinyint(4)
,`ticket` int(11)
,`type` char(1)
,`madedate` datetime
,`name` varchar(100)
,`doneby` varchar(61)
);

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(64) NOT NULL,
  `urole` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id_user`, `email`, `password`, `urole`) VALUES
(1, 'xx', 'null', 'X'),
(2, 'iolanda.siciliani@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'P'),
(3, 'marino.fiorentino@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(4, 'stefania.lori@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'P'),
(5, 'violante.rossi@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(6, 'vincenzo.ferri@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(7, 'lorna.pisano@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(8, 'eliano.loggia@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'P'),
(9, 'marcella.sagese@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'P'),
(10, 'alfonso.trentini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(11, 'fortunata.manna@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(12, 'ettore.dellucci@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(13, 'pia.gallo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(14, 'pierina.colombo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(15, 'patrizia.bucco@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(16, 'fiorenza.dellucci@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(17, 'valente.ricci@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'P'),
(18, 'elvira.lucchesi@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(19, 'rocco.trevisan@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(20, 'erminio.bucci@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(21, 'mafalda.laurentis@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(22, 'luciana.mancini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(23, 'guglielmi.mancini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(24, 'marco.schiavone@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(25, 'maurizio.mazzanti@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(26, 'delfio.arcuri@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(27, 'luisa.derose@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(28, 'cornelio.barese@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(29, 'raniero.barese@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(30, 'venera.saba@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(31, 'anna.meggiorini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(32, 'federico.bajardo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(33, 'ester.boni@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(34, 'eugenio.tani@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(35, 'simone.tartari@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(36, 'isaia.vischio@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(37, 'lorenza.brunelli@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(38, 'giacobbe.mazzini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(39, 'miriam.porricino@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(40, 'aronne.savoia@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(41, 'giuditta.checchini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(42, 'sara.giacopuzzi@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(43, 'isaia.deagostini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(44, 'qoelet.zamboni@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(45, 'chiara.marzotto@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(46, 'michea.benedetti@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(47, 'pasqualina.esposito@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(48, 'aronne.zanoni@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(49, 'camilla.lovato@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(50, 'ezechiele.fallaci@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'P'),
(51, 'alidegrego@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(52, 'fidenzio.milanesi@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(53, 'eligio.marchesi.4@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(54, 'aurelia.cucciola@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(55, 'carminelapalerma@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(56, 'joleamericana@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(57, 'marco.monaldo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(58, 'filicalabr@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(59, 'milani.fernando@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(60, 'trentatretrentini@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(61, 'ricciilbomber@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(62, 'udinese.esenidu@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(63, 'fiorenza.lombardi@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(64, 'pagnotto.lagnocca@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(65, 'silvia.longo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(66, 'giordano.youtuber@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(67, 'edvige.bruno@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(68, 'liberata.la.citta@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(69, 'urbano6969@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(70, 'alvise.crema@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(71, 'ippolito.boh@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(72, 'salalapasta@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(73, 'anselmo112233@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(74, 'cristoforetti@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(75, 'labarese12@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(76, 'mancini.scarso@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(77, 'vittoria_schiavone_04gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(78, 'raul.pinto.raul@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(79, 'carmensexy@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(80, 'greta.giordano@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(81, 'nino.nino@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(82, 'simone_fallaci@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(83, 'rossiilboss@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(84, 'genovesetirchia@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(85, 'matteo.folliero@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'D'),
(86, 'farmacia.torino@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(87, 'farmacia.venezia@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(88, 'farmacia.mel@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(89, 'farmacia.collegno@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(90, 'farmacia.borgosesia@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(91, 'farmacia.valduggia@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(92, 'farmacia.hone@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(93, 'farmacia.nus@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(94, 'farmacia.bergamo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(95, 'farmacia.zanica@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(96, 'farmacia.pontida@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(97, 'farmacia.falzes@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(98, 'farmacia.livo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(99, 'farmacia.pontenellealpi@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(100, 'farmacia.tezzesulbrenta@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(101, 'farmacia.revinelago@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(102, 'farmacia.trieste@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(103, 'farmacia.azzanodecimo@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(104, 'farmacia.savona@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(105, 'farmacia.valmozzola@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(106, 'farmacia.montese@gmail.com', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'S'),
(107, 'ULSS.Alessandria@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(108, 'ULSS.Asti@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(109, 'ULSS.Belluno@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(110, 'ULSS.Bergamo@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(111, 'ULSS.Biella@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(112, 'ULSS.Bologna@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(113, 'ULSS.Bolzano@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(114, 'ULSS.Brescia@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(115, 'ULSS.Como@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(116, 'ULSS.Cremona@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(117, 'ULSS.Cuneo@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(118, 'ULSS.Ferrara@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(119, 'ULSS.Lecco@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(120, 'ULSS.Lodi@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(121, 'ULSS.Mantova@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(122, 'ULSS.Milano@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(123, 'ULSS.Modena@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(124, 'ULSS.Monza e Brianza@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(125, 'ULSS.Novara@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(126, 'ULSS.Padova@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(127, 'ULSS.Parma@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(128, 'ULSS.Pavia@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(129, 'ULSS.Piacenza@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(130, 'ULSS.Pordenone@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(131, 'ULSS.Ravenna@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(132, 'ULSS.Reggio Emilia@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(133, 'ULSS.Rimini@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(134, 'ULSS.Rovigo@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(135, 'ULSS.Sondrio@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(136, 'ULSS.Torino@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(137, 'ULSS.Trento@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(138, 'ULSS.Treviso@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(139, 'ULSS.Trieste@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(140, 'ULSS.Udine@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(141, 'ULSS.Aosta@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(142, 'ULSS.Varese@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(143, 'ULSS.Venezia@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(144, 'ULSS.Vercelli@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(145, 'ULSS.Verona@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(146, 'ULSS.Vicenza@ssr.it', 'U26ROyZnHY9MleS8TNouknkcehWAUedkgey+5iBjQlc=', 'C'),
(148, 'michele.tran@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'P'),
(149, 'francesco.petroli@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'P'),
(150, 'matteo.frare@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'D'),
(151, 'giacomo.vischio@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'D'),
(152, 'farmacia.thiene@gmail.com', 'sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=', 'S');

-- --------------------------------------------------------

--
-- Struttura della tabella `visit`
--

CREATE TABLE `visit` (
  `id_visit` int(11) NOT NULL,
  `patient` int(11) NOT NULL,
  `familydoctor` int(11) NOT NULL,
  `visdate` datetime NOT NULL,
  `doctorsays` text DEFAULT NULL,
  `motivation` text DEFAULT NULL,
  `type` char(1) NOT NULL DEFAULT 'V'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `visit`
--

INSERT INTO `visit` (`id_visit`, `patient`, `familydoctor`, `visdate`, `doctorsays`, `motivation`, `type`) VALUES
(2, 2, 51, '2019-08-14 10:30:00', 'iolanda non sta molto bene                                \n                            \n                                \n                            ', 'non sto benissimo', 'V'),
(3, 2, 51, '2019-04-17 00:00:00', 'Tachipirina 100 una compressa al dì, per 5 gg.\r\nPoi Brufen uso orale, 5 gg.', 'Tosse forte con influenza', 'V'),
(4, 2, 51, '2019-04-17 00:00:00', 'Voltaren 100mg per 7gg su ginocchio sx.\r\nImpacco la sera con Flectorartro.\r\nAlternare con ghiaccio durante il giorno ogni due gg.', 'Dolore all\'articolazione ginocchio sx dopo trauma contusivo.', 'V'),
(5, 4, 53, '2019-04-17 00:00:00', 'Tachipirina 100 una compressa al dì, per 5 gg.\r\nPoi Brufen uso orale, 5 gg.', 'Influenza virale\r\n', 'V'),
(6, 4, 53, '2019-04-17 00:00:00', 'Voltaren 100mg per 7gg su ginocchio sx.\r\nImpacco la sera con Flectorartro.\r\nAlternare con ghiaccio durante il giorno ogni due gg.', 'Artrite', 'V'),
(7, 4, 53, '2019-04-17 00:00:00', 'Fluifort uso orale 2 volte al dì', 'Raffreddore e tosse grassa', 'V'),
(8, 4, 53, '2019-04-17 00:00:00', '10gg di riposo, no carico. Raggi RX.', 'Dolore forte ginocchio dx', 'V'),
(9, 4, 53, '2019-04-17 00:00:00', '40gg a casa a riposo per virus infettivo. No contatto umano con saliva o sangue. Controllo tra 10gg con dottore specialista.', 'Il paziente riscontra tosse con gocce di sangue, diagnosticato virus infettivo.', 'V'),
(10, 4, 53, '2019-04-17 00:00:00', 'Levocetrizina 10mg per allergia, una compressa al dì in stagione.', 'Allergia cronica al polline.', 'V'),
(11, 5, 54, '2019-04-17 00:00:00', 'Voltaren 100mg per 7gg su ginocchio sx.\r\nImpacco la sera con Flectorartro.\r\nAlternare con ghiaccio durante il giorno ogni due gg. Riposo forzato per 10gg.', 'Sovraccarico della schiena in zona lombare all\'altezza delle vertebre L4-L5.', 'V'),
(12, 5, 54, '2019-04-18 00:00:00', 'Riposo senza carichi per 15gg.', 'Distorsione al polso dx.', 'V'),
(13, 6, 55, '2019-04-17 00:00:00', 'Riposo per trauma da contusione. Prognosi di 14gg.', 'Trauma da contusione al gomito dx.', 'V'),
(14, 7, 56, '2019-04-17 00:00:00', '', '', 'V'),
(15, 7, 56, '2019-04-17 00:00:00', '', '', 'V'),
(16, 8, 57, '2019-04-17 00:00:00', '', '', 'V'),
(17, 9, 58, '2019-04-17 00:00:00', '', '', 'V'),
(18, 10, 59, '2019-04-17 00:00:00', '', '', 'V'),
(19, 11, 60, '2019-04-17 00:00:00', '', '', 'V'),
(20, 11, 60, '2019-04-17 00:00:00', '', '', 'V'),
(21, 11, 60, '2019-04-17 00:00:00', '', '', 'V'),
(22, 13, 62, '2019-04-17 00:00:00', '', '', 'V'),
(23, 13, 62, '2019-04-17 00:00:00', '', '', 'V'),
(24, 13, 62, '2019-04-17 00:00:00', '', '', 'V'),
(25, 14, 63, '2019-04-17 00:00:00', '', '', 'V'),
(26, 14, 63, '2019-04-17 00:00:00', '', '', 'V'),
(27, 14, 63, '2019-04-17 00:00:00', '', '', 'V'),
(28, 14, 63, '2019-04-17 00:00:00', '', '', 'V'),
(29, 14, 63, '2019-04-17 00:00:00', '', '', 'V'),
(30, 14, 63, '2019-04-17 00:00:00', '', '', 'V'),
(31, 15, 64, '2019-04-17 00:00:00', '', '', 'V'),
(32, 15, 64, '2019-04-17 00:00:00', '', '', 'V'),
(33, 15, 64, '2019-04-17 00:00:00', '', '', 'V'),
(34, 16, 65, '2019-04-17 00:00:00', '', '', 'V'),
(35, 17, 66, '2019-04-17 00:00:00', '', '', 'V'),
(36, 17, 66, '2019-04-17 00:00:00', '', '', 'V'),
(37, 17, 66, '2019-04-17 00:00:00', '', '', 'V'),
(38, 17, 66, '2019-04-17 00:00:00', '', '', 'V'),
(39, 17, 66, '2019-04-17 00:00:00', '', '', 'V'),
(40, 18, 67, '2019-04-17 00:00:00', '', '', 'V'),
(41, 20, 69, '2019-04-17 00:00:00', '', '', 'V'),
(42, 21, 70, '2019-04-17 00:00:00', '', '', 'V'),
(43, 22, 51, '2019-04-17 00:00:00', '', '', 'V'),
(44, 22, 51, '2019-04-17 00:00:00', '', '', 'V'),
(45, 22, 51, '2019-04-17 00:00:00', '', '', 'V'),
(46, 23, 52, '2019-04-17 00:00:00', '', '', 'V'),
(47, 23, 52, '2019-04-17 00:00:00', '', '', 'V'),
(48, 24, 53, '2019-04-17 00:00:00', '', '', 'V'),
(49, 25, 54, '2019-04-17 00:00:00', '', '', 'V'),
(50, 26, 55, '2019-04-17 00:00:00', '', '', 'V'),
(51, 27, 56, '2019-04-17 00:00:00', '', '', 'V'),
(52, 28, 57, '2019-04-17 00:00:00', '', '', 'V'),
(53, 29, 58, '2019-04-17 00:00:00', '', '', 'V'),
(54, 30, 59, '2019-04-17 00:00:00', '', '', 'V'),
(55, 31, 60, '2019-04-17 00:00:00', '', '', 'V'),
(56, 31, 60, '2019-04-17 00:00:00', '', '', 'V'),
(57, 31, 60, '2019-04-17 00:00:00', '', '', 'V'),
(58, 32, 61, '2019-04-17 00:00:00', '', '', 'V'),
(59, 33, 62, '2019-04-17 00:00:00', '', '', 'V'),
(60, 33, 62, '2019-04-17 00:00:00', '', '', 'V'),
(61, 34, 63, '2019-04-17 00:00:00', '', '', 'V'),
(62, 35, 64, '2019-04-17 00:00:00', '', '', 'V'),
(63, 36, 65, '2019-04-17 00:00:00', '', '', 'V'),
(64, 36, 65, '2019-04-17 00:00:00', '', '', 'V'),
(65, 36, 65, '2019-04-17 00:00:00', '', '', 'V'),
(66, 36, 65, '2019-04-17 00:00:00', '', '', 'V'),
(67, 37, 66, '2019-04-17 00:00:00', '', '', 'V'),
(68, 38, 67, '2019-04-17 00:00:00', '', '', 'V'),
(69, 39, 68, '2019-04-17 00:00:00', '', '', 'V'),
(70, 40, 69, '2019-04-17 00:00:00', '', '', 'V'),
(71, 40, 69, '2019-04-17 00:00:00', '', '', 'V'),
(72, 40, 69, '2019-04-17 00:00:00', '', '', 'V'),
(73, 40, 69, '2019-04-17 00:00:00', '', '', 'V'),
(74, 40, 69, '2019-04-17 00:00:00', '', '', 'V'),
(75, 40, 69, '2019-04-17 00:00:00', '', '', 'V'),
(76, 40, 69, '2019-04-17 00:00:00', '', '', 'V'),
(77, 41, 70, '2019-04-17 00:00:00', '', '', 'V'),
(78, 41, 70, '2019-04-17 00:00:00', '', '', 'V'),
(79, 41, 70, '2019-04-17 00:00:00', '', '', 'V'),
(80, 42, 51, '2019-04-17 00:00:00', '', '', 'V'),
(81, 42, 51, '2019-04-17 00:00:00', '', '', 'V'),
(82, 42, 51, '2019-04-17 00:00:00', '', '', 'V'),
(83, 42, 51, '2019-04-17 00:00:00', '', '', 'V'),
(84, 43, 52, '2019-04-17 00:00:00', '', '', 'V'),
(85, 45, 54, '2019-04-17 00:00:00', '', '', 'V'),
(86, 45, 54, '2019-04-17 00:00:00', '', '', 'V'),
(87, 45, 54, '2019-04-17 00:00:00', '', '', 'V'),
(88, 45, 54, '2019-04-17 00:00:00', '', '', 'V'),
(89, 45, 54, '2019-04-17 00:00:00', '', '', 'V'),
(90, 46, 55, '2019-04-17 00:00:00', '', '', 'V'),
(91, 46, 55, '2019-04-17 00:00:00', '', '', 'V'),
(92, 46, 55, '2019-04-17 00:00:00', '', '', 'V'),
(93, 47, 56, '2019-04-17 00:00:00', '', '', 'V'),
(94, 48, 57, '2019-04-17 00:00:00', '', '', 'V'),
(95, 48, 57, '2019-04-17 00:00:00', '', '', 'V'),
(96, 50, 59, '2019-04-17 00:00:00', '', '', 'V'),
(97, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(98, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(99, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(100, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(101, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(102, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(103, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(104, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(105, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(106, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(107, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(108, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(109, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(110, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(111, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(112, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(113, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(114, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(115, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(116, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(117, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(118, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(119, 51, 52, '2019-04-17 00:00:00', '', '', 'V'),
(120, 52, 53, '2019-04-17 00:00:00', '', '', 'V'),
(121, 52, 53, '2019-04-17 00:00:00', '', '', 'V'),
(122, 53, 54, '2019-04-17 00:00:00', '', '', 'V'),
(123, 53, 54, '2019-04-17 00:00:00', '', '', 'V'),
(124, 53, 54, '2019-04-17 00:00:00', '', '', 'V'),
(125, 53, 54, '2019-04-17 00:00:00', '', '', 'V'),
(126, 53, 54, '2019-04-17 00:00:00', '', '', 'V'),
(127, 54, 55, '2019-04-17 00:00:00', '', '', 'V'),
(128, 54, 55, '2019-04-17 00:00:00', '', '', 'V'),
(129, 54, 55, '2019-04-17 00:00:00', '', '', 'V'),
(130, 54, 55, '2019-04-17 00:00:00', '', '', 'V'),
(131, 55, 56, '2019-04-17 00:00:00', '', '', 'V'),
(132, 55, 56, '2019-04-17 00:00:00', '', '', 'V'),
(133, 56, 57, '2019-04-17 00:00:00', '', '', 'V'),
(134, 57, 58, '2019-04-17 00:00:00', '', '', 'V'),
(135, 58, 59, '2019-04-17 00:00:00', '', '', 'V'),
(136, 58, 59, '2019-04-17 00:00:00', '', '', 'V'),
(137, 61, 62, '2019-04-17 00:00:00', '', '', 'V'),
(138, 62, 63, '2019-04-17 00:00:00', '', '', 'V'),
(139, 62, 63, '2019-04-17 00:00:00', '', '', 'V'),
(140, 57, 63, '2019-04-17 00:00:00', '', '', 'V'),
(141, 62, 63, '2019-04-17 00:00:00', '', '', 'V'),
(142, 63, 64, '2019-04-17 00:00:00', '', '', 'V'),
(143, 63, 64, '2019-04-17 00:00:00', '', '', 'V'),
(144, 64, 65, '2019-04-17 00:00:00', '', '', 'V'),
(145, 65, 66, '2019-04-17 00:00:00', '', '', 'V'),
(146, 66, 67, '2019-04-17 00:00:00', '', '', 'V'),
(147, 67, 68, '2019-04-17 00:00:00', '', '', 'V'),
(148, 67, 68, '2019-04-17 00:00:00', '', '', 'V'),
(149, 67, 68, '2019-04-17 00:00:00', '', '', 'V'),
(150, 67, 68, '2019-04-17 00:00:00', '', '', 'V'),
(151, 68, 69, '2019-04-17 00:00:00', '', '', 'V'),
(152, 68, 69, '2019-04-17 00:00:00', '', '', 'V'),
(153, 69, 69, '2019-04-17 00:00:00', '', '', 'V'),
(154, 69, 70, '2019-04-17 00:00:00', '', '', 'V'),
(155, 69, 70, '2019-04-17 00:00:00', '', '', 'V'),
(156, 69, 70, '2019-04-17 00:00:00', '', '', 'V'),
(157, 69, 70, '2019-04-17 00:00:00', '', '', 'V'),
(158, 70, 51, '2019-04-17 00:00:00', '', '', 'V'),
(159, 70, 51, '2019-04-17 00:00:00', '', '', 'V'),
(160, 71, 51, '2019-04-17 00:00:00', '', '', 'V'),
(161, 72, 52, '2019-04-17 00:00:00', '', '', 'V'),
(162, 73, 53, '2019-04-17 00:00:00', '', '', 'V'),
(163, 74, 54, '2019-04-17 00:00:00', '', '', 'V'),
(164, 75, 55, '2019-04-17 00:00:00', '', '', 'V'),
(165, 75, 55, '2019-04-17 00:00:00', '', '', 'V'),
(166, 75, 55, '2019-04-17 00:00:00', '', '', 'V'),
(167, 75, 55, '2019-04-17 00:00:00', '', '', 'V'),
(168, 75, 55, '2019-04-17 00:00:00', '', '', 'V'),
(169, 76, 56, '2019-04-17 00:00:00', '', '', 'V'),
(170, 76, 56, '2019-04-17 00:00:00', '', '', 'V'),
(171, 76, 56, '2019-04-17 00:00:00', '', '', 'V'),
(172, 76, 56, '2019-04-17 00:00:00', '', '', 'V'),
(173, 77, 57, '2019-04-17 00:00:00', '', '', 'V'),
(174, 77, 57, '2019-04-17 00:00:00', '', '', 'V'),
(175, 77, 57, '2019-04-17 00:00:00', '', '', 'V'),
(176, 78, 58, '2019-04-17 00:00:00', '', '', 'V'),
(177, 78, 58, '2019-04-17 00:00:00', '', '', 'V'),
(178, 78, 58, '2019-04-17 00:00:00', '', '', 'V'),
(179, 78, 58, '2019-04-17 00:00:00', '', '', 'V'),
(180, 79, 59, '2019-04-17 00:00:00', '', '', 'V'),
(181, 80, 60, '2019-04-17 00:00:00', '', '', 'V'),
(182, 80, 60, '2019-04-17 00:00:00', '', '', 'V'),
(183, 81, 61, '2019-04-17 00:00:00', '', '', 'V'),
(184, 82, 62, '2019-04-17 00:00:00', '', '', 'V'),
(185, 83, 63, '2019-04-17 00:00:00', '', '', 'V'),
(186, 83, 63, '2019-04-17 00:00:00', '', '', 'V'),
(187, 83, 63, '2019-04-17 00:00:00', '', '', 'V'),
(188, 83, 63, '2019-04-17 00:00:00', '', '', 'V'),
(189, 84, 64, '2019-04-17 00:00:00', '', '', 'V'),
(190, 84, 64, '2019-04-17 00:00:00', '', '', 'V'),
(191, 84, 64, '2019-04-17 00:00:00', '', '', 'V'),
(192, 84, 64, '2019-04-17 00:00:00', '', '', 'V'),
(193, 84, 64, '2019-04-17 00:00:00', '', '', 'V'),
(194, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(195, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(196, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(197, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(198, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(199, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(200, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(201, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(202, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(203, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(204, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(205, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(206, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(207, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(208, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(209, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(210, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(211, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(212, 85, 65, '2019-04-17 00:00:00', '', '', 'V'),
(213, 5, 54, '2019-08-27 00:00:00', 'Serve un\'ecografia', 'Sento un dolore al petto', 'V'),
(215, 17, 66, '2019-08-29 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(221, 37, 66, '2019-08-29 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(222, 65, 66, '2019-08-29 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(226, 17, 66, '2019-08-30 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(227, 17, 66, '2019-08-31 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(228, 65, 66, '2019-08-31 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(229, 17, 66, '2019-09-01 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(230, 37, 66, '2019-09-02 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(231, 17, 66, '2019-09-02 00:00:00', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(232, 13, 66, '2019-09-04 08:30:00', 'a caso', 'a caso', 'V'),
(233, 2, 51, '2019-09-01 00:00:00', 'rip', 'muoro', 'V'),
(234, 17, 66, '2019-09-04 00:00:00', '', 'Visita senza prenotazione da parte del paziente', 'V'),
(235, 37, 66, '2019-09-04 10:00:01', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(236, 2, 51, '2019-09-05 09:00:49', NULL, 'ggg', 'V'),
(238, 41, 137, '2019-09-05 03:03:01', NULL, NULL, 'R'),
(241, 5, 61, '2019-09-06 09:00:00', NULL, 'boh', 'V'),
(246, 41, 137, '2019-09-05 15:13:59', NULL, NULL, 'R'),
(249, 17, 66, '2019-09-05 17:45:08', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(250, 37, 66, '2019-09-05 17:52:31', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(251, 65, 66, '2019-09-05 17:52:46', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(256, 65, 66, '2019-09-06 11:56:01', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(257, 17, 66, '2019-09-06 12:35:12', 'Tutto a posto', 'Visita senza prenotazione da parte del paziente', 'V'),
(258, 149, 150, '2019-09-07 18:30:14', 'vuole andare in vacanza', 'ciao', 'V'),
(271, 5, 52, '2019-09-08 12:00:41', 'ecco a te amore', 'voglio una visita specialistica', 'V'),
(281, 32, 137, '2019-09-08 11:31:10', NULL, 'richiamo', 'R'),
(282, 5, 137, '2019-09-08 12:41:53', NULL, 'richiamo', 'R'),
(283, 5, 137, '2019-09-08 12:54:35', NULL, 'richiamo', 'R'),
(284, 5, 137, '2019-09-08 13:03:36', NULL, 'richiamo', 'R'),
(285, 5, 137, '2019-09-08 13:06:17', NULL, 'richiamo', 'R'),
(286, 37, 66, '2019-09-08 15:12:40', 'Devo provare delle cose', 'Visita senza prenotazione da parte del paziente', 'V'),
(287, 151, 150, '2019-09-08 15:31:11', 'Giacomo sta benone, basta che vada a prendersi l\'Acetamol e il Brufen, torna tra 10gg dopo una visita per gli anticopri e un esame per l\'acido folico e controlliamo il suo stato di salute che dovrebbe migliorare.', 'Visita senza prenotazione da parte del paziente', 'V'),
(288, 148, 150, '2019-09-08 16:56:00', 'Michele Ã¨ andato a male', 'Visita senza prenotazione da parte del paziente', 'V'),
(289, 2, 61, '2019-09-09 09:00:39', NULL, 'emicrania', 'V'),
(290, 17, 66, '2019-09-08 18:11:57', NULL, 'Visita senza prenotazione da parte del paziente', 'V'),
(291, 66, 67, '2019-09-09 09:00:35', NULL, 'Ciao', 'V'),
(292, 149, 150, '2019-09-08 21:02:36', 'tutto ok', 'Visita senza prenotazione da parte del paziente', 'V');

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `visitforlist`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `visitforlist` (
`id_disp` int(11)
,`patient` int(11)
,`cardname` varchar(100)
,`prescrby` varchar(61)
,`madeby` varchar(61)
,`prescrdate` datetime
,`madedate` datetime
);

-- --------------------------------------------------------

--
-- Struttura della tabella `visitlist`
--

CREATE TABLE `visitlist` (
  `id_vislis` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `category` int(11) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `visitlist`
--

INSERT INTO `visitlist` (`id_vislis`, `name`, `category`, `description`) VALUES
(2, 'Visita acidina', 2, ''),
(3, 'Visita adrenalinica', 2, ''),
(4, 'Visita anticorpi', 2, ''),
(5, 'Visita sanguigna', 2, ''),
(6, 'Visita Immunoglobuline', 2, ''),
(7, 'Visita calcoli renali', 2, ''),
(8, 'Visita trigliceridi', 2, ''),
(9, 'Visita batteriologica', 3, ''),
(10, 'Visita virale', 3, ''),
(11, 'Visita CMV', 3, ''),
(12, 'Visita EBV', 3, ''),
(13, 'Visita HBV DNA', 3, ''),
(14, 'Visita HCV', 3, ''),
(15, 'Visita HIV', 3, ''),
(16, 'Visita HPV', 3, ''),
(17, 'Visita Pneumocistis carini', 3, ''),
(18, 'Visita BRAF', 3, ''),
(19, 'Visita per il breath', 2, ''),
(20, 'Visita pre-prelievo sanguigno', 7, ''),
(21, 'Visita CISH', 4, ''),
(22, 'Visita EGFR', 4, ''),
(23, 'Visita FISH', 4, ''),
(24, 'Visita KRAS', 4, ''),
(25, 'Visita per mutazioni', 4, ''),
(26, 'Visita SRY', 5, ''),
(27, 'Diagnostica ormonale', 6, ''),
(28, 'Diagnostica tumorale', 6, ''),
(29, 'Visita istologica', 6, ''),
(30, 'Visita cariotipo', 5, 'Prelievo di sangue incluso'),
(31, 'Visita senologica', 6, ''),
(32, 'Visita radiologica apparato digerente', 9, ''),
(33, 'Visita radiologica apparato cardiocircolatorio', 8, ''),
(34, 'Visita radiologica apparato genitale femminile', 10, ''),
(35, 'Visita radiologica apparato osteoarticolare e tessuti molli', 11, ''),
(36, 'Visita ecografica', 12, ''),
(37, 'Visita nucleare apparato cardiocircolatorio', 16, ''),
(38, 'Visita nucleare sistema nervoso', 17, ''),
(39, 'Visita nucleare traccianti immunologici', 18, ''),
(40, 'Visita densitometrica ossea MOC', 13, ''),
(41, 'Visita TAC', 14, ''),
(42, 'Visita pre-risonanza magnetica', 15, ''),
(43, 'Visita cardiologica', 19, ''),
(44, 'Visita gastroenterologica', 20, ''),
(45, 'Visita ginecologica', 21, ''),
(46, 'Visita ecografica morfologica/fetale', 22, ''),
(47, 'Visita generale', 22, 'Prevede: estriolo plasmatico e urinario, estrone, gonadotropina corionica');

-- --------------------------------------------------------

--
-- Struttura per la vista `doctor_ext`
--
DROP TABLE IF EXISTS `doctor_ext`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `doctor_ext`  AS  select `D`.`id_doc` AS `id_doc`,`D`.`doc_reg_numb` AS `doc_reg_numb`,`D`.`city` AS `city`,`D`.`type` AS `type`,`D`.`specialization` AS `specialization`,`D`.`work_district` AS `work_district`,`P`.`id_pat` AS `id_pat`,`P`.`ssn` AS `ssn`,`P`.`firstname` AS `firstname`,`P`.`lastname` AS `lastname`,`P`.`birthday` AS `birthday`,`P`.`birthplace` AS `birthplace`,`P`.`sex` AS `sex`,`P`.`familydoctor` AS `familydoctor`,`P`.`pdistrict` AS `pdistrict` from (`doctor` `D` join `patient` `P` on(`D`.`id_doc` = `P`.`id_pat`)) ;

-- --------------------------------------------------------

--
-- Struttura per la vista `examforlist`
--
DROP TABLE IF EXISTS `examforlist`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `examforlist`  AS  (select `x`.`id_disp` AS `id_disp`,`x`.`patient` AS `patient`,`x`.`cardname` AS `cardname`,`x`.`prescrby` AS `prescrby`,`x`.`madeby` AS `madeby`,`x`.`prescrdate` AS `prescrdate`,`x`.`madedate` AS `madedate` from ((select `D1`.`id_disp` AS `id_disp`,`V1`.`patient` AS `patient`,`L1`.`name` AS `cardname`,`C1`.`name` AS `prescrby`,`C11`.`name` AS `madeby`,`V1`.`visdate` AS `prescrdate`,`D1`.`madedate` AS `madedate` from (((((`disposition` `D1` join `visit` `V1` on(`D1`.`visit` = `V1`.`id_visit`)) join `chs` `C1` on(`V1`.`familydoctor` = `C1`.`id_chs`)) join `exam` `E1` on(`E1`.`id_exam` = `D1`.`id_disp`)) join `examlist` `L1` on(`E1`.`examcode` = `L1`.`id_exlis`)) left join `chs` `C11` on(`C11`.`id_chs` = `E1`.`providedby`))) union (select `D2`.`id_disp` AS `id_disp`,`V2`.`patient` AS `patient`,`L2`.`name` AS `cardname`,concat(`P2`.`firstname`,' ',`P2`.`lastname`) AS `prescrby`,`C2`.`name` AS `madeby`,`V2`.`visdate` AS `prescrdate`,`D2`.`madedate` AS `madedate` from (((((`disposition` `D2` join `visit` `V2` on(`D2`.`visit` = `V2`.`id_visit`)) join `patient` `P2` on(`V2`.`familydoctor` = `P2`.`id_pat`)) join `exam` `E2` on(`E2`.`id_exam` = `D2`.`id_disp`)) join `examlist` `L2` on(`E2`.`examcode` = `L2`.`id_exlis`)) left join `chs` `C2` on(`C2`.`id_chs` = `E2`.`providedby`)))) `x`) ;

-- --------------------------------------------------------

--
-- Struttura per la vista `exam_all`
--
DROP TABLE IF EXISTS `exam_all`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `exam_all`  AS  (select `V`.`patient` AS `patient`,`E`.`examcode` AS `examcode`,`E`.`result` AS `result`,`V`.`visdate` AS `examdate`,`D`.`madedate` AS `madedate`,`E`.`providedby` AS `providedby`,`D`.`familydoctor` AS `disposedby` from ((`visit` `V` join `disposition` `D`) join `exam` `E`) where `V`.`id_visit` = `D`.`visit` and `D`.`id_disp` = `E`.`id_exam`) union (select `recall`.`patient` AS `patient`,`recall`.`examcode` AS `examcode`,`recall`.`result` AS `result`,`recall`.`recdate` AS `examdate`,`recall`.`madedate` AS `madedate`,`recall`.`providedby` AS `providedby`,`recall`.`chs` AS `disposedby` from `recall`) ;

-- --------------------------------------------------------

--
-- Struttura per la vista `patient4chs3doct`
--
DROP TABLE IF EXISTS `patient4chs3doct`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `patient4chs3doct`  AS  (select `P`.`id_pat` AS `id_pat`,`P`.`pdistrict` AS `district`,`P`.`ssn` AS `ssn`,concat(`P`.`firstname`,' ',`P`.`lastname`) AS `patname`,`D`.`id_pat` AS `id_doc`,concat(`D`.`firstname`,' ',`D`.`lastname`) AS `docname`,`P`.`birthday` AS `birthday`,`X`.`lastvisit` AS `lastvisit`,`Y`.`examstodo` AS `examstodo` from (((`patient` `P` join `patient` `D` on(`P`.`familydoctor` = `D`.`id_pat`)) left join (select `visit`.`patient` AS `patient`,max(`visit`.`visdate`) AS `lastvisit` from `visit` where cast(`visit`.`visdate` as date) < curdate() and `visit`.`type` = 'V' group by `visit`.`patient`) `X` on(`P`.`id_pat` = `X`.`patient`)) left join (select `disposition`.`patient` AS `patient`,count(0) AS `examstodo` from `disposition` where `disposition`.`type` = 'E' and (`disposition`.`madedate` is null or cast(`disposition`.`madedate` as date) > curdate()) group by `disposition`.`patient`) `Y` on(`P`.`id_pat` = `Y`.`patient`))) ;

-- --------------------------------------------------------

--
-- Struttura per la vista `prescrforlist`
--
DROP TABLE IF EXISTS `prescrforlist`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prescrforlist`  AS  (select `D1`.`id_disp` AS `id_disp`,`V1`.`patient` AS `patient`,`L1`.`name` AS `cardname`,concat(`P1`.`firstname`,' ',`P1`.`lastname`) AS `prescrby`,`DS1`.`city` AS `madeby`,`V1`.`visdate` AS `prescrdate`,`D1`.`madedate` AS `madedate` from (((((`disposition` `D1` join `visit` `V1` on(`D1`.`visit` = `V1`.`id_visit`)) join `patient` `P1` on(`V1`.`familydoctor` = `P1`.`id_pat`)) join `prescription` `S1` on(`S1`.`id_pres` = `D1`.`id_disp`)) join `drug` `L1` on(`S1`.`drug` = `L1`.`id_drug`)) left join `drugstore` `DS1` on(`DS1`.`id_drst` = `S1`.`drugstore`))) ;

-- --------------------------------------------------------

--
-- Struttura per la vista `ticket`
--
DROP TABLE IF EXISTS `ticket`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ticket`  AS  (select `X`.`id_disp` AS `id_disp`,`X`.`patient` AS `patient`,`X`.`familydoctor` AS `familydoctor`,`X`.`visit` AS `visit`,`X`.`paid` AS `paid`,`X`.`ticket` AS `ticket`,`X`.`type` AS `type`,`X`.`madedate` AS `madedate`,`X`.`name` AS `name`,`X`.`doneby` AS `doneby` from (select `D`.`id_disp` AS `id_disp`,`D`.`patient` AS `patient`,`D`.`familydoctor` AS `familydoctor`,`D`.`visit` AS `visit`,`D`.`paid` AS `paid`,`D`.`ticket` AS `ticket`,`D`.`type` AS `type`,`D`.`madedate` AS `madedate`,`L1`.`name` AS `name`,concat(`U1`.`firstname`,' ',`U1`.`lastname`) AS `doneby` from (((`disposition` `D` join `visitlist` `L1`) join `speckvisit` `S`) join `patient` `U1`) where `D`.`id_disp` = `S`.`id_spvt` and `S`.`visitcode` = `L1`.`id_vislis` and `U1`.`id_pat` = `S`.`specialist` union select `D`.`id_disp` AS `id_disp`,`D`.`patient` AS `patient`,`D`.`familydoctor` AS `familydoctor`,`D`.`visit` AS `visit`,`D`.`paid` AS `paid`,`D`.`ticket` AS `ticket`,`D`.`type` AS `type`,`D`.`madedate` AS `madedate`,`L2`.`name` AS `name`,`U2`.`name` AS `doneby` from (((`disposition` `D` join `examlist` `L2`) join `exam` `E`) join `chs` `U2`) where `D`.`id_disp` = `E`.`id_exam` and `E`.`examcode` = `L2`.`id_exlis` and `U2`.`id_chs` = `E`.`providedby` union select `D`.`id_disp` AS `id_disp`,`D`.`patient` AS `patient`,`D`.`familydoctor` AS `familydoctor`,`D`.`visit` AS `visit`,`D`.`paid` AS `paid`,`D`.`ticket` AS `ticket`,`D`.`type` AS `type`,`D`.`madedate` AS `madedate`,`L3`.`name` AS `name`,`U3`.`city` AS `doneby` from (((`disposition` `D` join `drug` `L3`) join `prescription` `P`) join `drugstore` `U3`) where `D`.`id_disp` = `P`.`id_pres` and `P`.`drug` = `L3`.`id_drug` and `U3`.`id_drst` = `P`.`drugstore`) `X`) ;

-- --------------------------------------------------------

--
-- Struttura per la vista `visitforlist`
--
DROP TABLE IF EXISTS `visitforlist`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `visitforlist`  AS  (select `x`.`id_disp` AS `id_disp`,`x`.`patient` AS `patient`,`x`.`cardname` AS `cardname`,`x`.`prescrby` AS `prescrby`,`x`.`madeby` AS `madeby`,`x`.`prescrdate` AS `prescrdate`,`x`.`madedate` AS `madedate` from ((select `D1`.`id_disp` AS `id_disp`,`V1`.`patient` AS `patient`,`L1`.`name` AS `cardname`,`C1`.`name` AS `prescrby`,concat(`P1`.`firstname`,' ',`P1`.`lastname`) AS `madeby`,`V1`.`visdate` AS `prescrdate`,`D1`.`madedate` AS `madedate` from (((((`disposition` `D1` join `visit` `V1` on(`D1`.`visit` = `V1`.`id_visit`)) join `chs` `C1` on(`V1`.`familydoctor` = `C1`.`id_chs`)) join `speckvisit` `S1` on(`S1`.`id_spvt` = `D1`.`id_disp`)) join `visitlist` `L1` on(`S1`.`visitcode` = `L1`.`id_vislis`)) left join `patient` `P1` on(`P1`.`id_pat` = `S1`.`specialist`))) union (select `D2`.`id_disp` AS `id_disp`,`V2`.`patient` AS `patient`,`L2`.`name` AS `cardname`,concat(`P2`.`firstname`,' ',`P2`.`lastname`) AS `prescrby`,concat(`P21`.`firstname`,' ',`P21`.`lastname`) AS `madeby`,`V2`.`visdate` AS `prescrdate`,`D2`.`madedate` AS `madedate` from (((((`disposition` `D2` join `visit` `V2` on(`D2`.`visit` = `V2`.`id_visit`)) join `patient` `P2` on(`V2`.`familydoctor` = `P2`.`id_pat`)) join `speckvisit` `S2` on(`S2`.`id_spvt` = `D2`.`id_disp`)) join `visitlist` `L2` on(`S2`.`visitcode` = `L2`.`id_vislis`)) left join `patient` `P21` on(`P21`.`id_pat` = `S2`.`specialist`)))) `x`) ;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_categ`);

--
-- Indici per le tabelle `chs`
--
ALTER TABLE `chs`
  ADD PRIMARY KEY (`id_chs`),
  ADD KEY `cdistrict` (`cdistrict`);

--
-- Indici per le tabelle `disposition`
--
ALTER TABLE `disposition`
  ADD PRIMARY KEY (`id_disp`),
  ADD KEY `disposition_ibfk_1` (`patient`),
  ADD KEY `disposition_ibfk_3` (`visit`),
  ADD KEY `disposition_ibfk_2` (`familydoctor`);

--
-- Indici per le tabelle `district`
--
ALTER TABLE `district`
  ADD PRIMARY KEY (`id_dist`);

--
-- Indici per le tabelle `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id_doc`),
  ADD KEY `work_district` (`work_district`);

--
-- Indici per le tabelle `drug`
--
ALTER TABLE `drug`
  ADD PRIMARY KEY (`id_drug`);

--
-- Indici per le tabelle `drugstore`
--
ALTER TABLE `drugstore`
  ADD PRIMARY KEY (`id_drst`),
  ADD KEY `ddistrict` (`ddistrict`);

--
-- Indici per le tabelle `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id_exam`),
  ADD KEY `exam_ibfk_2` (`examcode`),
  ADD KEY `exam_ibfk_3` (`providedby`);

--
-- Indici per le tabelle `examlist`
--
ALTER TABLE `examlist`
  ADD PRIMARY KEY (`id_exlis`),
  ADD KEY `category` (`category`);

--
-- Indici per le tabelle `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id_notify`),
  ADD KEY `user` (`user`);

--
-- Indici per le tabelle `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id_pat`),
  ADD KEY `familydoctor` (`familydoctor`),
  ADD KEY `pdistrict` (`pdistrict`);

--
-- Indici per le tabelle `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id_photo`),
  ADD KEY `photo_ibfk_1` (`user`);

--
-- Indici per le tabelle `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id_pres`),
  ADD KEY `prescription_ibfk_2` (`drug`),
  ADD KEY `prescription_ibfk_3` (`drugstore`);

--
-- Indici per le tabelle `psw_temporanee`
--
ALTER TABLE `psw_temporanee`
  ADD PRIMARY KEY (`id_psw`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indici per le tabelle `speckvisit`
--
ALTER TABLE `speckvisit`
  ADD PRIMARY KEY (`id_spvt`),
  ADD KEY `speckvisit_ibfk_2` (`specialist`),
  ADD KEY `speckvisit_ibfk_3` (`visitcode`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indici per le tabelle `visit`
--
ALTER TABLE `visit`
  ADD PRIMARY KEY (`id_visit`),
  ADD KEY `patient` (`patient`),
  ADD KEY `visit_ibfk_2` (`familydoctor`);

--
-- Indici per le tabelle `visitlist`
--
ALTER TABLE `visitlist`
  ADD PRIMARY KEY (`id_vislis`),
  ADD KEY `category` (`category`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `category`
--
ALTER TABLE `category`
  MODIFY `id_categ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT per la tabella `disposition`
--
ALTER TABLE `disposition`
  MODIFY `id_disp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=306;
--
-- AUTO_INCREMENT per la tabella `district`
--
ALTER TABLE `district`
  MODIFY `id_dist` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;
--
-- AUTO_INCREMENT per la tabella `drug`
--
ALTER TABLE `drug`
  MODIFY `id_drug` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;
--
-- AUTO_INCREMENT per la tabella `examlist`
--
ALTER TABLE `examlist`
  MODIFY `id_exlis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=261;
--
-- AUTO_INCREMENT per la tabella `notification`
--
ALTER TABLE `notification`
  MODIFY `id_notify` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT per la tabella `photo`
--
ALTER TABLE `photo`
  MODIFY `id_photo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;
--
-- AUTO_INCREMENT per la tabella `psw_temporanee`
--
ALTER TABLE `psw_temporanee`
  MODIFY `id_psw` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=153;
--
-- AUTO_INCREMENT per la tabella `visit`
--
ALTER TABLE `visit`
  MODIFY `id_visit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=293;
--
-- AUTO_INCREMENT per la tabella `visitlist`
--
ALTER TABLE `visitlist`
  MODIFY `id_vislis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `chs`
--
ALTER TABLE `chs`
  ADD CONSTRAINT `chs_ibfk_1` FOREIGN KEY (`id_chs`) REFERENCES `user` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `chs_ibfk_2` FOREIGN KEY (`cdistrict`) REFERENCES `district` (`id_dist`);

--
-- Limiti per la tabella `disposition`
--
ALTER TABLE `disposition`
  ADD CONSTRAINT `disposition_ibfk_1` FOREIGN KEY (`patient`) REFERENCES `patient` (`id_pat`) ON DELETE NO ACTION,
  ADD CONSTRAINT `disposition_ibfk_2` FOREIGN KEY (`familydoctor`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION,
  ADD CONSTRAINT `disposition_ibfk_3` FOREIGN KEY (`visit`) REFERENCES `visit` (`id_visit`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`id_doc`) REFERENCES `user` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `doctor_ibfk_2` FOREIGN KEY (`work_district`) REFERENCES `district` (`id_dist`);

--
-- Limiti per la tabella `drugstore`
--
ALTER TABLE `drugstore`
  ADD CONSTRAINT `drugstore_ibfk_1` FOREIGN KEY (`id_drst`) REFERENCES `user` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `drugstore_ibfk_2` FOREIGN KEY (`ddistrict`) REFERENCES `district` (`id_dist`);

--
-- Limiti per la tabella `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`id_exam`) REFERENCES `disposition` (`id_disp`) ON DELETE CASCADE,
  ADD CONSTRAINT `exam_ibfk_2` FOREIGN KEY (`examcode`) REFERENCES `examlist` (`id_exlis`) ON DELETE NO ACTION,
  ADD CONSTRAINT `exam_ibfk_3` FOREIGN KEY (`providedby`) REFERENCES `chs` (`id_chs`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `examlist`
--
ALTER TABLE `examlist`
  ADD CONSTRAINT `examlist_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`id_categ`) ON DELETE CASCADE;

--
-- Limiti per la tabella `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`id_pat`) REFERENCES `user` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `patient_ibfk_2` FOREIGN KEY (`familydoctor`) REFERENCES `doctor` (`id_doc`),
  ADD CONSTRAINT `patient_ibfk_3` FOREIGN KEY (`pdistrict`) REFERENCES `district` (`id_dist`);

--
-- Limiti per la tabella `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`id_pres`) REFERENCES `disposition` (`id_disp`) ON DELETE NO ACTION,
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`drug`) REFERENCES `drug` (`id_drug`) ON DELETE NO ACTION,
  ADD CONSTRAINT `prescription_ibfk_3` FOREIGN KEY (`drugstore`) REFERENCES `drugstore` (`id_drst`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `psw_temporanee`
--
ALTER TABLE `psw_temporanee`
  ADD CONSTRAINT `psw_temporanee_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `user` (`id_user`) ON DELETE CASCADE;

--
-- Limiti per la tabella `speckvisit`
--
ALTER TABLE `speckvisit`
  ADD CONSTRAINT `speckvisit_ibfk_1` FOREIGN KEY (`id_spvt`) REFERENCES `disposition` (`id_disp`) ON DELETE NO ACTION,
  ADD CONSTRAINT `speckvisit_ibfk_2` FOREIGN KEY (`specialist`) REFERENCES `doctor` (`id_doc`) ON DELETE NO ACTION,
  ADD CONSTRAINT `speckvisit_ibfk_3` FOREIGN KEY (`visitcode`) REFERENCES `visitlist` (`id_vislis`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `visit`
--
ALTER TABLE `visit`
  ADD CONSTRAINT `visit_ibfk_1` FOREIGN KEY (`patient`) REFERENCES `patient` (`id_pat`) ON DELETE CASCADE,
  ADD CONSTRAINT `visit_ibfk_2` FOREIGN KEY (`familydoctor`) REFERENCES `user` (`id_user`) ON DELETE CASCADE;

--
-- Limiti per la tabella `visitlist`
--
ALTER TABLE `visitlist`
  ADD CONSTRAINT `visitlist_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`id_categ`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
