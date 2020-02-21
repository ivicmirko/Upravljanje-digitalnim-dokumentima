INSERT INTO `naucnacentrala`.`authority` (`id`, `name`) VALUES ('1', 'admin');
INSERT INTO `naucnacentrala`.`authority` (`id`, `name`) VALUES ('2', 'reviewer');
INSERT INTO `naucnacentrala`.`authority` (`id`, `name`) VALUES ('3', 'editor');
INSERT INTO `naucnacentrala`.`authority` (`id`, `name`) VALUES ('4', 'author');


INSERT INTO `naucnacentrala`.`sciencearea` (`id`, `code`, `name`) VALUES ('1', '1', 'elektrotehnika');
INSERT INTO `naucnacentrala`.`sciencearea` (`id`, `code`, `name`) VALUES ('2', '2', 'hemija');
INSERT INTO `naucnacentrala`.`sciencearea` (`id`, `code`, `name`) VALUES ('3', '3', 'masinstvo');

--adminNC
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('1', 'kac', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Mirko', 'no', 'Ivic', 'mr', 'asd', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');

--editors
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('2', 'kac', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Marko', 'no', 'Markic', 'mr', 'marko', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('3', 'kac', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Petar', 'no', 'Petric', 'mr', 'petar', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('4', 'kac', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Dusan', 'no', 'Dusanic', 'mr', 'dusan', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('5', 'kac', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Ivan', 'no', 'Ivanic', 'mr', 'ivan', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');


--reviewers
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('6', 'kac', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Darko', 'yes', 'Darkic', 'mr', 'darko', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('7', 'beograd', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Mladen', 'yes', 'Mladenic', 'mr', 'mladen', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('8', 'novi sad', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Goran', 'yes', 'Garanic', 'mr', 'goran', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('9', 'nis', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Aleksandar', 'yes', 'Aleksandric', 'mr', 'aca', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('12', 'nis', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Dule', 'yes', 'Dulic', 'mr', 'dule', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');

--author
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('10', 'subotica', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Zoltan', 'no', 'Zoltany', 'mr', 'zoli', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');
INSERT INTO `naucnacentrala`.`systemuser` (`id`, `city`, `country`, `email`, `is_enabled`, `is_removed`, `latitude`, `longitude`, `name`, `reviewer`, `surname`, `title`, `username`, `password`) VALUES ('11', 'Novi Pazar', 'srbija', 'ivic996@gmail.com', b'1', b'0', '25.6', '45.7', 'Mujo', 'no', 'Mujic', 'mr', 'mujo', '$2a$10$zxww4A8NKbMeHicT3ri9x.cxjstiwU2v7L8CI1Z3u1uVXT40LdZHm');

INSERT INTO `naucnacentrala`.`author` (`id`) VALUES ('10');
INSERT INTO `naucnacentrala`.`author` (`id`) VALUES ('11');

--authority
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (1, 1);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (2, 3);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (3, 3);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (4, 3);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (5, 3);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (6, 2);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (7, 2);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (8, 2);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (9, 2);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (10, 4);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (11, 4);
INSERT INTO `naucnacentrala`.`user_authority` (user_id, authority_id) VALUES (12, 2);

--naucne oblasti
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (1, 1);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (1, 2);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (1, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (2, 1);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (2, 2);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (3, 2);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (4, 2);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (4, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (5, 1);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (5, 2);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (5, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (6, 1);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (6, 2);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (7, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (8, 1);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (8, 2);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (8, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (9, 2);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (9, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (10, 1);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (10, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (11, 2);
INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (11, 3);

INSERT INTO `naucnacentrala`.`user_areas` (user_id, area_id) VALUES (12, 1);

--editor
INSERT INTO `naucnacentrala`.`editor` (`id`, `magazine_id`) VALUES ('2', null);
INSERT INTO `naucnacentrala`.`editor` (`id`, `magazine_id`) VALUES ('3', null);
INSERT INTO `naucnacentrala`.`editor` (`id`, `magazine_id`) VALUES ('4', null);
INSERT INTO `naucnacentrala`.`editor` (`id`, `magazine_id`) VALUES ('5', null);

--magazine

INSERT INTO `naucnacentrala`.`magazine` (`id`, `active`, `issn`, `name`, `open_access`, `status`, `maineditor_id`) VALUES ('1', b'1', '452314523', 'mali tesla', b'1', 'approved', '2');
INSERT INTO `naucnacentrala`.`magazine` (`id`, `active`, `issn`, `name`, `open_access`, `status`, `maineditor_id`) VALUES ('2', b'1', '453414523', 'masinac', b'1', 'approved', '3');

INSERT INTO `naucnacentrala`.`magazine_science_areas` (magazine_id, science_areas_id) VALUES (1, 1);
INSERT INTO `naucnacentrala`.`magazine_science_areas` (magazine_id, science_areas_id) VALUES (1, 3);
INSERT INTO `naucnacentrala`.`magazine_science_areas` (magazine_id, science_areas_id) VALUES (2, 3);

UPDATE `naucnacentrala`.`editor` SET `magazine_id` = '1' WHERE (`id` = '5');

--magazine reviewer
INSERT INTO `naucnacentrala`.`magazine_reviewer` (magazine_id, reviewer_id) VALUES (1, 6);
INSERT INTO `naucnacentrala`.`magazine_reviewer` (magazine_id, reviewer_id) VALUES (1, 8);
INSERT INTO `naucnacentrala`.`magazine_reviewer` (magazine_id, reviewer_id) VALUES (2, 6);
INSERT INTO `naucnacentrala`.`magazine_reviewer` (magazine_id, reviewer_id) VALUES (2, 8);







