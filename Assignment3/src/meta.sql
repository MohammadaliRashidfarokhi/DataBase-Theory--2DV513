drop database if exists metaAPI;
create database metaAPI;

use metaAPI;



DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`     int(30) unsigned NOT NULL auto_increment,
    `name`   varchar(255) default NULL,
    `status` TEXT         default NULL,
    PRIMARY KEY (`id`)
) AUTO_INCREMENT = 1;

INSERT INTO `users` (`name`, `status`)
VALUES ('Ciaran Barber', 'id, mollis nec, cursus a, enim. Suspendisse aliquet, sem ut'),
       ('Joelle Clemons', 'Donec vitae erat vel pede blandit congue. In scelerisque scelerisque'),
       ('Lillith Whitley', 'mi fringilla mi lacinia mattis. Integer eu lacus. Quisque imperdiet,'),
       ('Jasper Patel', 'magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna.'),
       ('Octavia Holloway', 'Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum'),
       ('Oliver Dyer', 'ultricies sem magna nec quam. Curabitur vel lectus. Cum sociis'),
       ('Raja Sampson', 'dui, semper et, lacinia vitae, sodales at, velit. Pellentesque ultricies'),
       ('Nissim Kerr', 'libero mauris, aliquam eu, accumsan sed, facilisis vitae, orci. Phasellus'),
       ('Thane Pugh', 'Nunc ut erat. Sed nunc est, mollis non, cursus non,'),
       ('Roanna Smith', 'est ac facilisis facilisis, magna tellus faucibus leo, in lobortis');
INSERT INTO `users` (`name`, `status`)
VALUES   ('Sebastian Benjamin','massa. Quisque porttitor eros nec tellus. Nunc lectus pede, ultrices'),
         ('Addison Berger','magna a tortor. Nunc commodo auctor velit. Aliquam nisl. Nulla'),
         ('Tallulah Sullivan','rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam'),
         ('Cynthia Bray','leo elementum sem, vitae aliquam eros turpis non enim. Mauris'),
         ('Francis Hart','eget metus eu erat semper rutrum. Fusce dolor quam, elementum'),
         ('Erin Rivera','et magnis dis parturient montes, nascetur ridiculus mus. Proin vel'),
         ('Abra Herman','magnis dis parturient montes, nascetur ridiculus mus. Proin vel arcu'),
         ('Cruz Knapp','eleifend vitae, erat. Vivamus nisi. Mauris nulla. Integer urna. Vivamus'),
         ('Willa Collins','Sed eu eros. Nam consequat dolor vitae dolor. Donec fringilla.'),
         ('Daria Fuentes','Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius.');



CREATE TABLE hobbies
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    hobby_name TEXT
);

insert into hobbies (hobby_name)
values ('Astrology');
insert into hobbies (hobby_name)
values ('Anime');
insert into hobbies (hobby_name)
values ('Hacking');
insert into hobbies (hobby_name)
values ('Inventing');
insert into hobbies (hobby_name)
values ('Up-cycling');
insert into hobbies (hobby_name)
values ('Planning');
insert into hobbies (hobby_name)
values ('BASE jumping');
insert into hobbies (hobby_name)
values ('Tennis');
insert into hobbies (hobby_name)
values ('Air hockey');
insert into hobbies (hobby_name)
values ('Photography');
insert into hobbies (hobby_name)
values ('Aircraft spotting');




CREATE TABLE hobbies_users
(
    Id         INTEGER PRIMARY KEY auto_increment,
    user_name  int(30) unsigned not null,
    foreign key (user_name) references users (id) ON DELETE CASCADE on update cascade,
    hobby_name int(30),
    foreign key (hobby_name) references hobbies (id) ON DELETE CASCADE on update cascade
);
CREATE INDEX hobbies_users_hobby_name
    ON hobbies_users (hobby_name,user_name);
insert into hobbies_users (user_name, hobby_name) values (1, 7);
insert into hobbies_users (user_name, hobby_name) values (17, 6);
insert into hobbies_users (user_name, hobby_name) values (9, 6);
insert into hobbies_users (user_name, hobby_name) values (6, 6);
insert into hobbies_users (user_name, hobby_name) values (13, 3);
insert into hobbies_users (user_name, hobby_name) values (20, 3);
insert into hobbies_users (user_name, hobby_name) values (20, 1);
insert into hobbies_users (user_name, hobby_name) values (18, 6);
insert into hobbies_users (user_name, hobby_name) values (20, 8);
insert into hobbies_users (user_name, hobby_name) values (3, 3);
insert into hobbies_users (user_name, hobby_name) values (12, 9);
insert into hobbies_users (user_name, hobby_name) values (13, 10);
insert into hobbies_users (user_name, hobby_name) values (12, 6);
insert into hobbies_users (user_name, hobby_name) values (6, 11);
insert into hobbies_users (user_name, hobby_name) values (4, 1);
insert into hobbies_users (user_name, hobby_name) values (4, 3);
insert into hobbies_users (user_name, hobby_name) values (12, 3);
insert into hobbies_users (user_name, hobby_name) values (5, 11);
insert into hobbies_users (user_name, hobby_name) values (17, 7);
insert into hobbies_users (user_name, hobby_name) values (3, 6);
insert into hobbies_users (user_name, hobby_name) values (5, 2);
insert into hobbies_users (user_name, hobby_name) values (2, 9);
insert into hobbies_users (user_name, hobby_name) values (9, 5);
insert into hobbies_users (user_name, hobby_name) values (5, 11);
insert into hobbies_users (user_name, hobby_name) values (6, 3);
insert into hobbies_users (user_name, hobby_name) values (2, 2);
insert into hobbies_users (user_name, hobby_name) values (3, 8);
insert into hobbies_users (user_name, hobby_name) values (10, 3);
insert into hobbies_users (user_name, hobby_name) values (14, 3);
insert into hobbies_users (user_name, hobby_name) values (2, 10);
insert into hobbies_users (user_name, hobby_name) values (16, 8);
insert into hobbies_users (user_name, hobby_name) values (17, 4);
insert into hobbies_users (user_name, hobby_name) values (9, 7);
insert into hobbies_users (user_name, hobby_name) values (2, 8);
insert into hobbies_users (user_name, hobby_name) values (15, 7);
insert into hobbies_users (user_name, hobby_name) values (5, 7);
insert into hobbies_users (user_name, hobby_name) values (6, 8);
insert into hobbies_users (user_name, hobby_name) values (12, 1);
insert into hobbies_users (user_name, hobby_name) values (1, 11);
insert into hobbies_users (user_name, hobby_name) values (19, 8);
insert into hobbies_users (user_name, hobby_name) values (7, 11);
insert into hobbies_users (user_name, hobby_name) values (20, 8);
insert into hobbies_users (user_name, hobby_name) values (10, 3);
insert into hobbies_users (user_name, hobby_name) values (8, 1);
insert into hobbies_users (user_name, hobby_name) values (5, 10);
insert into hobbies_users (user_name, hobby_name) values (19, 2);
insert into hobbies_users (user_name, hobby_name) values (4, 11);
insert into hobbies_users (user_name, hobby_name) values (16, 4);
insert into hobbies_users (user_name, hobby_name) values (16, 1);
insert into hobbies_users (user_name, hobby_name) values (15, 8);



CREATE TABLE friendship
(
    Id       INTEGER PRIMARY KEY auto_increment,
    user1_id int(30) unsigned,
    foreign key (user1_id) references users (id) ON DELETE CASCADE,
    user2_id int(30) unsigned,
    foreign key (user2_id) references users (id) ON DELETE CASCADE
);

INSERT INTO friendship
VALUES (1, 1, 2);
INSERT INTO friendship
VALUES (2, 1, 3);
INSERT INTO friendship
VALUES (3, 2, 3);
INSERT INTO friendship
VALUES (4, 2, 5);
INSERT INTO friendship
VALUES (5, 2, 4);
INSERT INTO friendship
VALUES (6, 2, 1);
INSERT INTO friendship
VALUES (7, 2, 7);
INSERT INTO friendship
VALUES (8, 3, 1);
INSERT INTO friendship
VALUES (9, 3, 2);
INSERT INTO friendship
VALUES (10, 4, 2);
INSERT INTO friendship
VALUES (11, 4, 6);
INSERT INTO friendship
VALUES (12, 4, 5);
INSERT INTO friendship
VALUES (13, 5, 4);
INSERT INTO friendship
VALUES (14, 5, 2);
INSERT INTO friendship
VALUES (15, 5, 6);
INSERT INTO friendship
VALUES (16, 6, 5);
INSERT INTO friendship
VALUES (17, 6, 4);
INSERT INTO friendship
VALUES (18, 7, 2);

CREATE VIEW mustPopularUser AS
SELECT users.name, COUNT(friendship.user2_id) as Popular
FROM friendship
         JOIN users
              ON users.id = friendship.user2_id
GROUP BY users.name
ORDER BY COUNT(friendship.user2_id) DESC
    LIMIT 1;