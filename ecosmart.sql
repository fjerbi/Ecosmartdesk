-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 10 avr. 2019 à 14:36
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
-- Base de données :  `ecosmart`
--

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

DROP TABLE IF EXISTS `annonce`;
CREATE TABLE IF NOT EXISTS `annonce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `nomAnnonce` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `nbSignal` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `heure` time NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `nbrrating` int(11) DEFAULT NULL,
  `nbruser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B26681E6B3CA4B` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonce` (`id`, `id_user`, `nomAnnonce`, `dateDebut`, `dateFin`, `adresse`, `description`, `nbSignal`, `type`, `heure`, `photo`, `rating`, `nbrrating`, `nbruser`) VALUES
(1, 11, 'lol', '2019-04-03', '2019-04-25', 'Tunis', 'aaa', NULL, 'musical', '16:00:00', 'C:\\Users\\firas\\Desktop\\53584710_663314730750629_4762261670540083200_n.jpg', 10, NULL, NULL),
(2, 11, 'Hello world', '2019-04-05', '2019-04-11', 'Ariana', 'azeaze', NULL, 'autres', '15:21:00', 'C:\\Users\\firas\\Desktop\\53584710_663314730750629_4762261670540083200_n.jpg', 10, NULL, NULL),
(3, 11, 'Ariana is dying', '2019-04-10', '2019-04-11', 'Ariana', 'azeaz', NULL, 'musical', '13:26:00', 'C:\\wamp64\\www\\Ecosmartweb\\web\\uploads\\annonce\\a.jpg', 10, NULL, NULL),
(4, 11, 'Dying', '2019-04-10', '2019-04-25', '', 'aaz', NULL, 'Pollution', '13:07:00', NULL, NULL, NULL, NULL),
(5, 11, 'lol123', '2019-04-03', '2019-04-24', 'Tunisia', 'hello', NULL, 'Pollution', '14:11:00', 'C:\\Users\\firas\\Desktop\\54520647_663319550750147_8248660317475700736_n.jpg', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `genre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rue` longtext COLLATE utf8_unicode_ci,
  `zip_code` int(11) DEFAULT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pays` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nbsignal` int(11) DEFAULT NULL,
  `datenaiss` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `genre`, `nom`, `prenom`, `rue`, `zip_code`, `ville`, `pays`, `phone`, `nbsignal`, `datenaiss`) VALUES
(9, 'admin', 'admin', 'firas.jerbi@esprit.tn', 'firas.jerbi@esprit.tn', 1, NULL, '$2y$10$CYz/NlxOj.C7JB3RO3K8qe15vkDixFWunMwaV6kwgd3ErAr5Hi/N2', '2018-03-01 18:29:07', 'efb38752-a3d6-4af4-a41b-3e34f5c8c0f6', NULL, 'a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}', NULL, 'Administrateur', 'ElSouk', 'No Way', 6666, 'Tunis', 'AF', '66666666', NULL, '1990-07-28 00:00:00'),
(11, 'fjerbi', 'fjerbi', 'firasjerbiv2@gmail.com', 'firasjerbiv2@gmail.com', 1, NULL, '$2y$10$bwQaFTXGMgkA.kLZKvuTt.SUTrWrSpOUbQzjkEOaNbCuTBtYAL6M6', NULL, NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Homme', 'firas', 'Jerbi', 'Ariana', 2073, 'Ariana', 'Tunisie', NULL, 0, '2019-04-03 00:00:00'),
(13, 'fjerbio', 'fjerbio', 'firasjerbiv3@gmail.com', 'firasjerbiv3@gmail.com', 0, NULL, '$2y$10$nbthpvQEzmJmbfZHPmCnTe4e0C5MkD6HGK0uGMJ36rxiWYdrJ8/YS', NULL, '0a771829-c1d2-4512-8d2c-bcfce6ed86c6', NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'Homme', 'firas', 'Jerbi', 'Ariana', 2073, 'Ariana', 'Tunisie', NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `profile`
--

DROP TABLE IF EXISTS `profile`;
CREATE TABLE IF NOT EXISTS `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `tagline` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `about_me` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8157AA0F6B3CA4B` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `profile`
--

INSERT INTO `profile` (`id`, `id_user`, `tagline`, `image`, `updated_at`, `about_me`) VALUES
(5, 3, '\"But man is not made for defeat. A man can be destroyed but not defeated.\"', '5acd033b1ca8f.jpg', '2018-04-10 19:32:27', '* Studied software-engineering at INSAT, past: Lycée Pilote Bourguiba Tunis, lives in Fondouk El Djedid, Nabul, Tunisia and is from ElKef.'),
(6, 4, 'Nothing to Lose, Nothing to win, No Purpose in this Life', '5a8a1cb612ff3.jpg', '2018-02-19 01:39:18', 'I question everything and worship nothing ! Studied at Iset Rades, now at Esprit, and hopefully nejah mch bch nokeed hakeka nkhalles fi floussi uu, ah w ey, m3a9ed <3'),
(7, 5, 'Strong Like a Tree.', '5a8b53b588049.jpg', '2018-02-19 23:46:13', 'Studies at ESPRIT now, studied at ESPRIT before. Oh yes, and I like to eat :D a lot! No, really, a FUCKING LOT.'),
(8, 6, '\"Qui néglige la musique ignore l\'approche du sublime\"', '5a8cab5a715bd.jpg', '2018-02-21 00:12:26', 'Ami de chacun , ami d\'aucun .. Quand tout est fichu, il y a encore le courage. La meilleure odeur est celle du pain , et le meilleur amour celui des enfants !!'),
(9, 8, 'Nothing To Lose, and He\'s loose', '5a9611e6c8fff.jpg', '2018-02-28 03:20:22', 're9ed akhtawni taw..'),
(10, 10, 'Hey, it\'s a tagline', NULL, '2018-04-10 19:33:37', 'Hi, it\'s an aboutMe section, je suis une princesse, je vous aime putain les fifiiiis d\'amouuree <3 <3'),
(11, 11, 'hello', NULL, '2019-04-06 12:49:27', 'rien');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `FK_B26681E6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `FK_8157AA0F6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
