-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 18 juin 2020 à 06:03
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_pedagogiques`
--

-- --------------------------------------------------------

--
-- Structure de la table `addcours`
--

DROP TABLE IF EXISTS `addcours`;
CREATE TABLE IF NOT EXISTS `addcours` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_cours` int(10) NOT NULL,
  `id_module` int(10) NOT NULL,
  `titre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `addcours`
--

INSERT INTO `addcours` (`id`, `id_cours`, `id_module`, `titre`) VALUES
(3, 1, 1, 'js'),
(4, 5, 3, 'git'),
(5, 2, 1, 'javascript 2'),
(6, 4, 3, 'big data '),
(7, 3, 1, 'javascript chap3');

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `poste` varchar(30) NOT NULL,
  `naissance` date NOT NULL,
  `tele` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `nom`, `prenom`, `email`, `poste`, `naissance`, `tele`) VALUES
(1, 'mohamed', 'ahmed', 'ahmed.mohamed@uca.com', 'secretaire', '1980-04-21', 687963254);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cours` int(30) NOT NULL,
  `id_etudiant` int(30) NOT NULL,
  `commentaire` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id`, `id_cours`, `id_etudiant`, `commentaire`) VALUES
(1, 1, 1, 'merci pour le tutorielle '),
(11, 1, 2, 'c tres bon cours');

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `url` varchar(535) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `nom`, `url`) VALUES
(1, 'javascript1', 'http://196.200.177.195/moocs/web_developpment/javascript-dom-html-css/01%20Javascript%20dans%20notre%20Navigateur/001%20LObjet%20Window.mp4'),
(2, 'javascript2', 'http://196.200.177.195/moocs/web_developpment/javascript-dom-html-css/01%20Javascript%20dans%20notre%20Navigateur/002%20LObjet%20Location.mp4'),
(3, 'javascript3', 'http://196.200.177.195/moocs/web_developpment/javascript-dom-html-css/01%20Javascript%20dans%20notre%20Navigateur/003%20LObjet%20Document%20le%20DOM.mp4'),
(4, 'big data1', 'http://196.200.177.195/moocs/big_data/Big_Data_Analytics_Using_Spark/01-Introduction_and_Course_Information/01-UCSDSE232018-V004400_DTH.mp4'),
(5, 'git1', 'http://196.200.177.195/moocs/git/Learning%20Git%20and%20GitHub/01%20Introduction/001%20Welcome.mp4');

-- --------------------------------------------------------

--
-- Structure de la table `cycle`
--

DROP TABLE IF EXISTS `cycle`;
CREATE TABLE IF NOT EXISTS `cycle` (
  `id` int(2) NOT NULL,
  `nom_cycle` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cycle`
--

INSERT INTO `cycle` (`id`, `nom_cycle`) VALUES
(1, 'DUT'),
(2, 'Licence professionnelle');

-- --------------------------------------------------------

--
-- Structure de la table `element`
--

DROP TABLE IF EXISTS `element`;
CREATE TABLE IF NOT EXISTS `element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_module` int(30) NOT NULL,
  `hrtp` int(10) NOT NULL,
  `hrtd` int(10) NOT NULL,
  `hrcours` int(10) NOT NULL,
  `description` text NOT NULL,
  `intitule` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `naissance` date NOT NULL,
  `id_filiere` int(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `tele` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `nom`, `prenom`, `naissance`, `id_filiere`, `email`, `tele`) VALUES
(1, 'dryouch', 'marouane', '1998-10-20', 1, 'dryouch.marouane@gmail.com', 624085847),
(2, 'soulaimani', 'yassir', '1999-06-06', 1, 'yassir.soulaimani@gmail.com', 62587411),
(3, 'ahmed', 'ali', '1998-06-10', 1, 'ahmed.ali@gmail.com', 658741234);

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

DROP TABLE IF EXISTS `filiere`;
CREATE TABLE IF NOT EXISTS `filiere` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `coordinateur_id` int(10) NOT NULL,
  `intitule` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`id`, `coordinateur_id`, `intitule`) VALUES
(1, 2, 'Licence professionnelle ISIL'),
(2, 1, 'DUT Genie informatique');

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

DROP TABLE IF EXISTS `module`;
CREATE TABLE IF NOT EXISTS `module` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_filiere` int(10) NOT NULL,
  `id_respo` int(10) NOT NULL,
  `intitule` varchar(50) NOT NULL,
  `id_semestre` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`id`, `id_filiere`, `id_respo`, `intitule`, `id_semestre`) VALUES
(1, 1, 1, 'web', 5),
(2, 1, 2, 'java', 6),
(3, 2, 1, 'unix', 1);

-- --------------------------------------------------------

--
-- Structure de la table `moduleaffecte`
--

DROP TABLE IF EXISTS `moduleaffecte`;
CREATE TABLE IF NOT EXISTS `moduleaffecte` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_prof` int(10) NOT NULL,
  `id_semestre` int(10) NOT NULL,
  `nom_module` varchar(30) NOT NULL,
  `id_projet` int(10) NOT NULL,
  `rempli` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `moduleaffecte`
--

INSERT INTO `moduleaffecte` (`id`, `id_prof`, `id_semestre`, `nom_module`, `id_projet`, `rempli`) VALUES
(69, 5, 1, 'Base de donnÃ©es ', 74, 'oui'),
(68, 1, 1, 'Web avancÃ©e', 74, 'oui'),
(66, 3, 1, 'Uml', 74, 'oui'),
(67, 6, 1, 'MathÃ©matique', 74, 'oui'),
(65, 4, 1, 'Java avancÃ©e', 74, 'oui'),
(70, 8, 1, 'Langue et communication ', 74, 'oui'),
(71, 4, 2, 'Administration rÃ©seau ', 74, 'oui'),
(72, 2, 2, 'Java EE', 74, 'oui'),
(73, 9, 2, 'Gestion de projet', 74, 'oui');

-- --------------------------------------------------------

--
-- Structure de la table `moduledetail`
--

DROP TABLE IF EXISTS `moduledetail`;
CREATE TABLE IF NOT EXISTS `moduledetail` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `id_module` int(30) NOT NULL,
  `objectif` text NOT NULL,
  `prerequis` text NOT NULL,
  `evaluation` text NOT NULL,
  `notemodule` text NOT NULL,
  `validation` text NOT NULL,
  `id_projet` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `moduledetail`
--

INSERT INTO `moduledetail` (`id`, `id_module`, `objectif`, `prerequis`, `evaluation`, `notemodule`, `validation`, `id_projet`) VALUES
(41, 49, '', '', '', '', '', 50),
(16, 52, 'a', 'a', 'c', 'd', 'a', 50),
(6, 51, 'module pour renforcer les ', 'avoir un diplome', 'par examen', '10', 'sur 10', 50),
(7, 50, 'creation des bd', 'avoir etudier sql', 'par les comtrÃ´les', 'sur 10', 'avoir 10 par element', 50),
(40, 49, '', '', '', '', '', 50),
(39, 49, 'a', 'a', 'd', 'd', 'd', 50),
(38, 49, 'a', 'a', 'a', 'a', 'a', 50),
(37, 49, 'a', 'a', 'a', 'a', 'z', 50),
(36, 49, 'a', 'a', 'a', 'az', 'e', 50),
(42, 49, '', '', '', '', '', 50),
(43, 49, '', '', '', '', '', 50),
(44, 49, '', '', '', '', '', 50),
(45, 49, '', '', '', '', '', 50),
(46, 49, '', '', '', '', '', 50),
(47, 49, '', '', '', '', '', 50),
(48, 49, '', '', '', '', '', 50),
(49, 49, '', '', '', '', '', 50),
(50, 49, '', '', '', '', '', 50),
(51, 49, '', '', '', '', '', 50),
(52, 49, '', '', '', '', '', 50),
(53, 49, '', '', '', '', '', 50),
(54, 49, '', '', '', '', '', 50),
(55, 49, '', '', '', '', '', 50),
(56, 49, '', '', '', '', '', 50),
(57, 49, '', '', '', '', '', 50),
(58, 49, '', '', '', '', '', 50),
(59, 49, '', '', '', '', '', 50),
(60, 49, '', '', '', '', '', 50),
(61, 49, '', '', '', '', '', 50),
(62, 49, '', '', '', '', '', 50),
(63, 49, '', '', '', '', '', 50),
(64, 49, '', '', '', '', '', 50),
(65, 49, 'module essentielle dans le dÃ©veloppement informatique', 'il faut avoir un bon niveau en algorithmique', 'par controle', 'sur 10', 'il faut avoir une note supÃ©rieure a 10 dans chaque Ã©lement', 50),
(66, 54, 'c est un module essentielle dans le web', 'avoir un bon niveau en java', 'par contrÃ´le', 'sur 10 ', 'avoir note supÃ©rieure Ã  10 ', 56),
(67, 50, 'un module important pour ce filiere ', 'il faut Ã©tudier mÃ©rise ', 'par ds et examen', 'la note sur 10', 'il faut avoir note supÃ©rieure Ã  10', 50),
(68, 53, 'ce module vise Ã  developper le niveau des Ã©tudiants dans le web', 'il faut Ã©tudier d avant php , css html', 'par controle et ds et examen finale', 'la note est sÃ»r 10', 'il faut avoir un moyen supÃ©rieure Ã  10', 56),
(69, 72, 'Ce module vise Ã  renforcer les capacitÃ©s en dÃ©veloppement web.', 'Avoir des compÃ©tences en Java et POO.', 'l\'Ã©valuation se fait par contrÃ´le et examen.', 'la note doit Ã©tre sur 10.', 'Avoir une note supÃ©rieure de 10 dans le module , et supÃ©rieure de 8 dans chaque element.', 74),
(70, 68, 'ce module vise Ã  amÃ©liorer les compÃ©tences des Ã©tudiants en dÃ©veloppement web.', 'Avoir des bases en html, css ...', 'par contrÃ´le et examen ', 'sur 10', 'avoir note supÃ©rieure Ã  10', 74),
(71, 69, 'ce module Ã  pour le but de ...', 'avoir un bon niveau en ...', 'par contrÃ´le ', 'avoir une note supÃ©rieure Ã  10', 'avoir une note plus que 10 ', 74),
(72, 69, 'ce module Ã  pour le but de ...', 'avoir un bon niveau en ...', 'par contrÃ´le ', 'avoir une note supÃ©rieure Ã  10', 'avoir une note plus que 10 ', 74),
(73, 66, 'module important pour avoir des compÃ©tences en conception ..', 'avoir des connaissances en POO.', 'par l examen finale ', 'sur 10', 'avoir une note supÃ©rieure Ã  10 ', 74),
(74, 67, 'pour renforcer le sens d analyse ...', 'mathÃ©matique appliquÃ©', 'par examen et ds', 'la note est sur 10', 'avoir une note supÃ©rieure Ã  10 .', 74),
(75, 65, 'module important ...', 'avoir connaissance en dev procÃ©durale ..', 'par 40% contrÃ´le et 60% examen ', 'sur 10', 'avoir note supÃ©rieur Ã  10 ', 74),
(76, 71, 'module pour le but ...', 'avoir des compÃ©tences en ...', 'par contrÃ´le et examen ', 'sur 10', 'doit avoir une note supÃ©rieure Ã  10', 74),
(77, 70, 'module important pour developper ', 'tous les Ã©tudiants peut ...', '60% contrÃ´le et 40% examen', 'avoir note sup a 10 ', 'sur 10', 74),
(78, 73, 'un module important pour les Ã©tudiants dans leurs vie professionnelle ', 'tous les Ã©tudiants pouvons ...', '%30 ds et 70% examen ', 'sur 10', 'avoir une note supÃ©rieure Ã  10 ', 74),
(79, 70, 'ce module pour l objectif d amÃ©liorer les compÃ©tences linguistiques des Ã©tudiants.', 'tous les Ã©tudiants pouvons Ã©tudier ce cours', 'par contrÃ´le ', 'la note sur 10', 'avoir note supÃ©rieure Ã  10 ', 74);

-- --------------------------------------------------------

--
-- Structure de la table `modulefile`
--

DROP TABLE IF EXISTS `modulefile`;
CREATE TABLE IF NOT EXISTS `modulefile` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_projet` int(10) NOT NULL,
  `module_page1` varchar(535) NOT NULL,
  `module_page2` varchar(535) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `modulefile`
--

INSERT INTO `modulefile` (`id`, `id_projet`, `module_page1`, `module_page2`) VALUES
(51, 74, '66/page0.bmp', '66/page1.bmp'),
(50, 74, '65/page0.bmp', '65/page1.bmp'),
(49, 74, '70/page0.bmp', '70/page1.bmp'),
(52, 74, '67/page0.bmp', '67/page1.bmp'),
(53, 74, '68/page0.bmp', '68/page1.bmp'),
(54, 74, '69/page0.bmp', '69/page1.bmp'),
(55, 74, '71/page0.bmp', '71/page1.bmp'),
(56, 74, '72/page0.bmp', '72/page1.bmp'),
(58, 74, '73/page0.bmp', '73/page1.bmp');

-- --------------------------------------------------------

--
-- Structure de la table `moduleprojet`
--

DROP TABLE IF EXISTS `moduleprojet`;
CREATE TABLE IF NOT EXISTS `moduleprojet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_module` varchar(30) NOT NULL,
  `nbr_heure` int(4) NOT NULL,
  `id_semestre` int(11) NOT NULL,
  `id_projet` int(7) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=186 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `moduleprojet`
--

INSERT INTO `moduleprojet` (`id`, `nom_module`, `nbr_heure`, `id_semestre`, `id_projet`) VALUES
(62, 'web', 20, 2, 26),
(61, 'unix', 30, 1, 26),
(60, 'langue1', 30, 1, 26),
(59, 'math1', 20, 1, 26),
(58, 'algo1', 20, 1, 26),
(71, 'WEB', 40, 5, 28),
(72, 'jk', 10, 5, 29),
(70, 'UML', 40, 5, 28),
(69, 'LANGUE ', 40, 5, 28),
(68, 'GESTION DE PROJET', 40, 5, 28),
(67, 'BASE DONNES', 40, 5, 28),
(66, 'JAVA AVANCEES', 40, 5, 28),
(63, 'web', 30, 2, 26),
(64, 'c/c++', 30, 2, 26),
(65, 'c/c++', 20, 2, 26),
(73, 'jnj', 10, 5, 29),
(74, 'jnjkn', 10, 5, 29),
(75, 'jkh', 10, 5, 29),
(76, 'jjkh', 10, 5, 29),
(77, 'jjkh', 10, 5, 29),
(78, 'web', 40, 5, 30),
(79, 'Java avancÃ©es', 40, 5, 30),
(80, 'Gestion des projets', 40, 5, 30),
(81, 'Uml', 40, 5, 30),
(82, 'Base DonnÃ©es', 40, 5, 30),
(83, 'Langue', 40, 5, 30),
(84, 'JAVA EE', 40, 6, 30),
(85, 'BD AvancÃ©s', 40, 6, 30),
(86, 'RÃ©seau', 40, 6, 30),
(87, 'SFE', 40, 6, 30),
(88, 'SFE', 40, 6, 30),
(89, 'SFE', 40, 6, 30),
(90, 'web', 40, 5, 31),
(91, 'bd', 40, 5, 31),
(92, 'java', 40, 5, 31),
(93, 'uml', 40, 5, 31),
(94, 'gestion des projets', 40, 5, 31),
(95, 'langue', 40, 5, 31),
(96, 'bd avancÃ©s', 40, 6, 31),
(97, 'Java EE ', 40, 6, 31),
(98, 'SFE', 40, 6, 31),
(99, 'SFE', 40, 6, 31),
(100, 'SFE', 40, 6, 31),
(101, 'rÃ©seau', 40, 6, 31),
(102, 'web', 40, 5, 32),
(103, 'uml', 40, 5, 32),
(104, 'kotlin', 40, 5, 32),
(105, 'java', 40, 5, 32),
(106, 'flutter', 40, 5, 32),
(107, 'bd', 40, 5, 32),
(108, 'bd avancÃ©s ', 40, 6, 32),
(109, 'java EE', 40, 6, 32),
(110, 'rÃ©seau', 40, 6, 32),
(111, 'SFE', 40, 6, 32),
(112, 'SFE', 40, 6, 32),
(113, 'SFE', 40, 6, 32),
(114, 'f', 21, 5, 33),
(115, 'f', 21, 5, 33),
(116, 'g', 21, 5, 33),
(117, 'hghgf', 21, 5, 33),
(118, 'hgfh', 21, 5, 33),
(119, 'hgfh', 21, 5, 33),
(120, 'jhg', 80, 6, 33),
(121, 'jgh', 20, 6, 33),
(122, 'jghj', 21, 6, 33),
(123, 'jhg', 20, 6, 33),
(124, 'jgh', 13, 6, 33),
(125, 'jhg', 45, 6, 33),
(126, 'web', 40, 5, 34),
(127, 'uml', 40, 5, 34),
(128, 'java', 40, 5, 34),
(129, 'gestion des projets', 40, 5, 34),
(130, 'bd', 40, 5, 34),
(131, 'langue', 40, 5, 34),
(132, 'bd avancÃ©s', 40, 6, 34),
(133, 'rÃ©seau', 40, 6, 34),
(134, 'java ee', 40, 6, 34),
(135, 'sfe', 40, 6, 34),
(136, 'sfe', 40, 6, 34),
(137, 'sfe', 40, 6, 34),
(138, 'java', 40, 5, 35),
(139, 'web', 40, 5, 35),
(140, 'langue', 80, 5, 35),
(141, 'bd', 50, 5, 35),
(142, 'uml', 40, 5, 35),
(143, 'gp', 40, 5, 35),
(144, 'sfe', 40, 6, 35),
(145, 'sfe', 40, 6, 35),
(146, 'sfe', 40, 6, 35),
(147, 'bd avancÃ©', 40, 6, 35),
(148, 'java ee', 40, 6, 35),
(149, 'rÃ©seau', 40, 6, 35),
(150, 'java', 50, 5, 36),
(151, 'algo', 40, 5, 36),
(152, 'gestion projet', 40, 5, 36),
(153, 'bd', 40, 5, 36),
(154, 'uml', 40, 5, 36),
(155, 'langue', 40, 5, 36),
(156, 'sfe', 40, 6, 36),
(157, 'web', 40, 6, 36),
(158, 'sfe', 40, 6, 36),
(159, 'sfe', 40, 6, 36),
(160, 'bd avancees', 50, 6, 36),
(161, 'java ee', 40, 6, 36),
(162, 'ghh', 40, 5, 37),
(163, 'gh', 40, 5, 37),
(164, 'vv', 40, 5, 37),
(165, 'ggh', 40, 5, 37),
(166, 'ggh', 40, 5, 37),
(167, 'tggh', 40, 5, 37),
(168, 'ghhb', 50, 6, 37),
(169, 'dfgg', 50, 6, 37),
(170, 'sdf', 50, 6, 37),
(171, 'ddf', 40, 6, 37),
(172, 'gghh', 40, 6, 37),
(173, 'dxc', 20, 6, 37),
(174, 'h', 8, 5, 40),
(175, 'j', 8, 5, 40),
(176, 'g', 8, 5, 40),
(177, 'h', 8, 5, 40),
(178, 'v', 8, 5, 40),
(179, 'y', 8, 5, 40),
(180, 'g', 8, 6, 40),
(181, 'y', 8, 6, 40),
(182, 'v', 8, 6, 40),
(183, 'v', 8, 6, 40),
(184, 'g', 8, 6, 40),
(185, 't', 8, 6, 40);

-- --------------------------------------------------------

--
-- Structure de la table `offreprojet`
--

DROP TABLE IF EXISTS `offreprojet`;
CREATE TABLE IF NOT EXISTS `offreprojet` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_projet` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `admission` text NOT NULL,
  `debouche` text NOT NULL,
  `cout` int(8) DEFAULT NULL,
  `date_demande` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_responsable` int(10) NOT NULL,
  `id_cycle` int(2) NOT NULL,
  `situation` varchar(30) DEFAULT NULL,
  `nbr_semestre` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `offreprojet`
--

INSERT INTO `offreprojet` (`id`, `nom_projet`, `description`, `admission`, `debouche`, `cout`, `date_demande`, `id_responsable`, `id_cycle`, `situation`, `nbr_semestre`) VALUES
(74, 'IngÃ©nierie des applications mobiles', 'C\'est une formation qui vise Ã  former des cadres moyens en dÃ©veloppement informatique et Ã  enraÃ®chir leurs compÃ©tences dans le dÃ©veloppement mobile.', 'Il faut avoir un bac+2 en informatique de type : Dut - Deug - bts ', '-DÃ©veloppeur web \n- concepteur informatique \n- Administrateur de base de donnÃ©es ', 10, '2020-06-18 01:02:57', 2, 2, 'oui', 2);

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

DROP TABLE IF EXISTS `professeur`;
CREATE TABLE IF NOT EXISTS `professeur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `tele` int(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `naissance` date NOT NULL,
  `departement` varchar(30) NOT NULL,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `professeur`
--

INSERT INTO `professeur` (`id`, `nom`, `prenom`, `tele`, `email`, `naissance`, `departement`, `role`) VALUES
(1, 'gounane', 'said', 6349028, 'said.gounane@uca.com', '1985-10-10', 'informatique', NULL),
(2, 'karami', 'fahd', 6356788, 'karami.fahd@uca.com', '1980-07-04', 'informatique', NULL),
(3, 'aziz', 'ouaarab', 65874210, 'aziz.ouaarab@uca.com', '1990-01-01', 'informatique', NULL),
(4, 'benkirane', 'said', 68521470, 'said.benkirane@uca.com', '1990-01-01', 'informatique', NULL),
(5, 'guezzaz', 'azdin', 658741202, 'azdin.guezzaz@uca.com', '1990-01-01', 'informatique', NULL),
(6, 'meskine', 'driss', 685741250, 'dr.meskine@uca.com', '1990-01-01', 'informatique', NULL),
(7, 'ait elmaati', 'mustapha', 68574120, 'm.maati@uca.com', '1990-01-01', 'informatique', NULL),
(8, 'dali', 'rachid', 625085847, 'rachid.dali@uca.com', '1990-01-01', 'informatique', NULL),
(9, 'berkouch', 'yassir', 58746321, 'berkouch.yassir@uca.com', '1990-01-01', 'informatique', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `profparfiliere`
--

DROP TABLE IF EXISTS `profparfiliere`;
CREATE TABLE IF NOT EXISTS `profparfiliere` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_prof` int(10) NOT NULL,
  `id_filiere` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `profparfiliere`
--

INSERT INTO `profparfiliere` (`id`, `id_prof`, `id_filiere`) VALUES
(1, 1, 1),
(2, 2, 1),
(5, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `semestre`
--

DROP TABLE IF EXISTS `semestre`;
CREATE TABLE IF NOT EXISTS `semestre` (
  `id` int(11) NOT NULL,
  `nom_semestre` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `semestre`
--

INSERT INTO `semestre` (`id`, `nom_semestre`) VALUES
(1, 'Semestre 1'),
(2, 'Semestre 2'),
(3, 'Semestre 3'),
(4, 'Semestre 4'),
(5, 'Semestre 5'),
(6, 'Semestre 6');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(500) NOT NULL,
  `profession` varchar(30) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `profession`, `created`) VALUES
(11, 'karami.fahd@uca.com', '$2y$10$qUOY1N5OCX779cVhszgo0uiIxNVxkttu24dC/RE93knzjRTLVaChS', 'Professeur', '2020-05-12 18:51:08'),
(9, 'said.gounane@uca.com', '$2y$10$21OPSDlRt2Btg/m0Xqz/Su7pebJLJNRXxBgn353YYioKN2bc4exv2', 'Professeur', '2020-04-29 05:12:47'),
(10, 'ahmed.mohamed@uca.com', '$2y$10$90pwyaihjxtBsjk0CbheVumhP.Xxpe7nfjj8UDMM72/xDqC/43rpK', 'Administrateur', '2020-05-10 04:45:16'),
(12, 'aziz.ouaarab@uca.com', '$2y$10$tRjHJ8uaSaEibwgQaBkkHOXmwH875Ar.h9lCdNfvMugaNoADxZK3i', 'Professeur', '2020-05-19 15:22:54'),
(13, 'said.benkirane@uca.com', '$2y$10$jQ497IJFeNaf1i0oz87CF.MT814xknaEMRPTNLgQg1QDXcY8DpF2W', 'Professeur', '2020-05-19 15:24:03'),
(14, 'azdin.guezzaz@uca.com', '$2y$10$yX3CygiT8BJVaaf1fUi6nOnntxYEn/MO3VU0WliNbVXnIepkLlrFS', 'Professeur', '2020-05-19 15:25:07'),
(15, 'dr.meskine@uca.com', '$2y$10$b0bi43V4TQgkyMBj9zMoteJDpOYF15xxWxgHbMC4FH0n.Dy4CsWOm', 'Professeur', '2020-05-19 15:25:59'),
(16, 'm.maati@uca.com', '$2y$10$UMJc430bPvk1rjcm/S56ou5BPxsrpIIqhIqyJWllq1Z6529vCLLTS', 'Professeur', '2020-05-19 15:27:07'),
(17, 'rachid.dali@uca.com', '$2y$10$Ma3BvBr0Cxe5fx1NkB1oE.YtZrVt1XbmBDIq31eDwac/9saqZczJe', 'Professeur', '2020-05-19 15:28:17'),
(18, 'dryouch.marouane@gmail.com', '$2y$10$TzJ5Nx.6QmnM6on0YURtN.Fd1czqFHe/cSzAJOgX4By4WQv1wt7dm', 'Etudiant', '2020-05-29 19:49:28'),
(19, 'yassir.soulaimani@gmail.com', '$2y$10$KVD3h/iNa72QV8KuV/3Yl.8hsAYg6MZFlcCJ36bdz79in6ydQwjtK', 'Etudiant', '2020-06-05 14:31:18'),
(20, 'berkouch.yassir@uca.com', '$2y$10$UICP6Yrq773H/e16jQRufuAWUBhxfr2j1nEuDm9VncXDIVPWd9dcG', 'Professeur', '2020-06-18 02:30:19');

-- --------------------------------------------------------

--
-- Structure de la table `vue`
--

DROP TABLE IF EXISTS `vue`;
CREATE TABLE IF NOT EXISTS `vue` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `id_etudiant` int(30) NOT NULL,
  `id_cours` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vue`
--

INSERT INTO `vue` (`id`, `id_etudiant`, `id_cours`) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 1, 1),
(4, 1, 1),
(5, 1, 1),
(6, 1, 1),
(7, 2, 1),
(8, 1, 1),
(9, 1, 1),
(10, 1, 2),
(11, 1, 2),
(12, 1, 2),
(13, 1, 1),
(14, 1, 1),
(15, 1, 1),
(16, 1, 1),
(17, 1, 1),
(18, 1, 1),
(19, 1, 1),
(20, 1, 1),
(21, 1, 1),
(22, 1, 1),
(23, 1, 1),
(24, 1, 1),
(25, 1, 1),
(26, 1, 1),
(27, 1, 1),
(28, 1, 1),
(29, 1, 1),
(30, 1, 1),
(31, 1, 1),
(32, 1, 1),
(33, 1, 1),
(34, 1, 1),
(35, 1, 1),
(36, 1, 1),
(37, 1, 1),
(38, 1, 1),
(39, 1, 1),
(40, 1, 1),
(41, 1, 1),
(42, 1, 1),
(43, 1, 1),
(44, 1, 1),
(45, 1, 1),
(46, 1, 1),
(47, 1, 1),
(48, 1, 1),
(49, 1, 1),
(50, 1, 1),
(51, 1, 1),
(52, 1, 1),
(53, 1, 1),
(54, 1, 1),
(55, 1, 1),
(56, 1, 1),
(57, 1, 1),
(58, 1, 1),
(59, 1, 1),
(60, 1, 1),
(61, 1, 1),
(62, 1, 1),
(63, 1, 1),
(64, 1, 1),
(65, 1, 1),
(66, 1, 1),
(67, 1, 1),
(68, 1, 1),
(69, 1, 1),
(70, 1, 1),
(71, 1, 1),
(72, 1, 1),
(73, 1, 1),
(74, 1, 1),
(75, 1, 1),
(76, 1, 1),
(77, 1, 1),
(78, 1, 1),
(79, 1, 1),
(80, 1, 1),
(81, 1, 1),
(82, 1, 1),
(83, 1, 1),
(84, 1, 1),
(85, 1, 1),
(86, 1, 1),
(87, 1, 1),
(88, 1, 1),
(89, 1, 1),
(90, 1, 1),
(91, 1, 1),
(92, 1, 1),
(93, 1, 1),
(94, 1, 1),
(95, 1, 1),
(96, 1, 1),
(97, 1, 1),
(98, 1, 1),
(99, 1, 1),
(100, 1, 1),
(101, 1, 1),
(102, 1, 1),
(103, 1, 1),
(104, 1, 1),
(105, 1, 1),
(106, 1, 1),
(107, 1, 1),
(108, 1, 1),
(109, 1, 1),
(110, 1, 1),
(111, 1, 1),
(112, 1, 1),
(113, 1, 1),
(114, 2, 2),
(115, 1, 2),
(116, 1, 2),
(117, 1, 1),
(118, 1, 1),
(119, 1, 1),
(120, 1, 1),
(121, 1, 1),
(122, 1, 1),
(123, 1, 1),
(124, 3, 1),
(125, 3, 1),
(126, 1, 1),
(127, 1, 1),
(128, 1, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
