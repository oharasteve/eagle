# Created by backupDB: 2015-09-29 08:51:41

CREATE TABLE `Details` (
  `Clan` set('Hatfield','McCoy') NOT NULL default '',
  `KeyName` varchar(20) NOT NULL default '',
  `CellPhone` varchar(20) NOT NULL default '',
  `HomePhone` varchar(20) NOT NULL default '',
  `WorkPhone` varchar(20) NOT NULL default '',
  `OtherPhone` varchar(20) NOT NULL default '',
  `HomeEmail` varchar(40) NOT NULL default '',
  `WorkEmail` varchar(40) NOT NULL default '',
  `OtherEmail` varchar(40) NOT NULL default '',
  `HomeAddress1` varchar(40) NOT NULL default '',
  `HomeAddress2` varchar(40) NOT NULL default '',
  `HomeAddress3` varchar(40) NOT NULL default '',
  `WorkOrSchool` varchar(40) NOT NULL default '',
  `WorkAddress1` varchar(40) NOT NULL default '',
  `WorkAddress2` varchar(40) NOT NULL default '',
  `WorkAddress3` varchar(40) NOT NULL default '',
  PRIMARY KEY  (`KeyName`),
  KEY `Key` (`KeyName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Details for each person';


CREATE TABLE `Faces` (
  `Photo` varchar(100) NOT NULL COMMENT 'FK to Photos',
  `Left` int(11) NOT NULL COMMENT '0 to 65535',
  `Right` int(11) NOT NULL COMMENT '0 to 65535',
  `Top` int(11) NOT NULL COMMENT '0 to 65535',
  `Bottom` int(11) NOT NULL COMMENT '0 to 65535',
  `Name` varchar(40) NOT NULL,
  `Person` varchar(40) default NULL COMMENT 'FK to People',
  `LowYear` int(11) NOT NULL COMMENT 'Not reliable',
  `HighYear` int(11) NOT NULL COMMENT 'Not reliable',
  KEY `Person` (`Person`),
  KEY `Photo` (`Photo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Created from Picasa';


CREATE TABLE `Families` (
  `FamilyKey` varchar(20) NOT NULL default '',
  `FamilyName` varchar(20) NOT NULL default '',
  UNIQUE KEY `FamilyKey` (`FamilyKey`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Family Names';

INSERT INTO `Families` VALUES ('Hatfield','Hatfield');
INSERT INTO `Families` VALUES ('McCoy','McCoy');


CREATE TABLE `Marriages` (
  `HusbandKey` varchar(20) NOT NULL default '',
  `WifeKey` varchar(20) NOT NULL default '',
  `MonWed` int(11) NOT NULL default '0',
  `DayWed` int(11) NOT NULL default '0',
  `YrWed` int(11) NOT NULL default '0',
  `Where` varchar(40) NOT NULL default '',
  `Divorced` enum('','Divorced','Widowed') NOT NULL default '',
  KEY `HusbandKey` (`HusbandKey`,`WifeKey`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='List of known marriages';


CREATE TABLE `People` (
  `KeyName` varchar(20) NOT NULL default '',
  `First` varchar(20) NOT NULL default '',
  `Middle` varchar(20) NOT NULL default '',
  `Maiden` varchar(20) NOT NULL default '',
  `Last` varchar(20) NOT NULL default '',
  `Jr` varchar(10) NOT NULL default '',
  `Title` varchar(10) NOT NULL default '',
  `Nickname` varchar(20) NOT NULL default '',
  `Sex` enum('M','F') NOT NULL default 'M',
  `MonBorn` int(11) NOT NULL default '0',
  `DayBorn` int(11) NOT NULL default '0',
  `YrBorn` int(11) NOT NULL default '0',
  `WhereBorn` varchar(40) NOT NULL default '',
  `Alive` enum('Y','N') NOT NULL default 'Y',
  `MonDied` int(11) NOT NULL default '0',
  `DayDied` int(11) NOT NULL default '0',
  `YrDied` int(11) NOT NULL default '0',
  `WhereBuried` varchar(40) NOT NULL default '',
  `MotherKey` varchar(20) NOT NULL default '',
  `FatherKey` varchar(20) NOT NULL default '',
  `WikiTree` varchar(200) NOT NULL,
  `FindAGrave` varchar(200) NOT NULL,
  `Note` text NOT NULL,
  PRIMARY KEY  (`KeyName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='One entry per person';


CREATE TABLE `PeoplePhotos` (
  `Photo` varchar(75) NOT NULL,
  `KeyName` varchar(75) NOT NULL,
  `Position` varchar(40) NOT NULL default '',
  KEY `KeyName` (`Photo`,`KeyName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Which people are in which photos';


CREATE TABLE `PeoplePhotosDeletions` (
  `Photo` varchar(40) NOT NULL default '',
  `KeyName` varchar(40) NOT NULL default '',
  `Position` varchar(40) NOT NULL default '',
  `When` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Photo`,`KeyName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Backup for all deleted photos';


CREATE TABLE `Photos` (
  `Directory` varchar(100) NOT NULL default '',
  `Photo` varchar(100) NOT NULL default '',
  `Suffix` varchar(10) NOT NULL default '.jpg',
  `Original` varchar(120) NOT NULL default '',
  `Month` int(11) NOT NULL default '0',
  `Day` int(11) NOT NULL default '0',
  `Year` int(11) NOT NULL default '0',
  `Width` int(11) NOT NULL default '0',
  `Height` int(11) NOT NULL default '0',
  `ThumbWidth` int(11) NOT NULL default '0',
  `ThumbHeight` int(11) NOT NULL default '0',
  `Caption` text NOT NULL,
  `Viewings` int(11) NOT NULL default '0',
  `WhenAdded` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT 'When photo was added',
  PRIMARY KEY  (`Photo`),
  KEY `Directory` (`Directory`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='List of all photographs';


CREATE TABLE `PhotosEditLog` (
  `Dir` varchar(100) NOT NULL default '',
  `Photo` varchar(100) NOT NULL default '',
  `Suffix` varchar(10) NOT NULL default '',
  `Month` int(11) NOT NULL default '0',
  `Day` int(11) NOT NULL default '0',
  `Year` int(11) NOT NULL default '0',
  `Caption` varchar(200) NOT NULL default '',
  `When` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE `WebLinks` (
  `KeyName` varchar(20) NOT NULL default '',
  `Link` varchar(100) NOT NULL default ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Web Connections';
