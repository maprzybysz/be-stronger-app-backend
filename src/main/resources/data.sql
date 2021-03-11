insert into user_roles(id, role, description) VALUES (1, "ROLE_USER", "default role for user");
insert into user_roles(id, role, description) VALUES (2, "ROLE_ADMIN", "default role for admin");
insert into app_user(id, email, is_enabled, password, rules_accepted, username)
VALUES (1, "notentego678@gmail.com", true,"$2a$10$JAu1KH2QCPGZqhyA0JUF0uurkrXOneGATPCvH9oikxKHFF6u7zrgC", true, "mefiu678");
insert into app_user(id, email, is_enabled, password, rules_accepted, username)
VALUES (2, "testuser@gmail.com", true,"$2a$10$JAu1KH2QCPGZqhyA0JUF0uurkrXOneGATPCvH9oikxKHFF6u7zrgC", true,"testuser");
insert into app_user_details(id, sex, birthday, user_activity, height, user_goal, app_user_id)
VALUES (1,'Mężczyzna' , '1998-08-20', 'ZNIKOMA', 173, 'UTRZYMANIE', 1 );
insert into app_user_roles(app_user_id, roles_id) VALUES (1, 1);
insert into app_user_roles(app_user_id, roles_id) VALUES (1, 1);
insert into meal_details(id, description, img_url, create_date) VALUES (1, 'opis 1', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (2, 'opis 2', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (3, 'opis 3', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (4, 'opis 4', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (5, 'opis 5', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (6, 'opis 6', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (7, 'opis 7', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (8, 'opis 8', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (9, 'opis 9', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (10, 'opis 10', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (11, 'opis 11', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (12, 'opis 12', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (13, 'opis 13', 'url', '2021-02-21');
insert into meal_details(id, description, img_url, create_date) VALUES (14, 'opis 14', 'url', '2021-02-21');
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (1, 100,  50,1000, 100, 'Produkt 1',10, 1, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (2, 100,  50,1000, 100, 'Produkt 2',10, 2, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (3, 100,  50,1000, 100, 'Produkt 3',10, 3, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (4, 100,  50,1000, 100, 'Produkt 4',10, 4, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (5, 100,  50,1000, 100, 'Produkt 5',10, 5, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (6, 100,  50,1000, 100, 'Produkt 6',10, 6, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (7, 100,  50,1000, 100, 'Produkt 7',10, 7, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (8, 100,  50,1000, 100, 'Produkt 8',10, 8, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (9, 100,  50,1000, 100, 'Produkt 9',10, 9, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (10, 100,  50,1000, 100, 'Produkt 10',10, 10, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (11, 100,  50,1000, 100, 'Produkt 11',10, 11, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (12, 100,  50,1000, 100, 'Produkt 12',10, 12, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (13, 100,  50,1000, 100, 'Produkt 13',10, 13, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id, app_user_id)
VALUES (14, 100,  50,1000, 100, 'Produkt 14',10, 14, 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(1, 'produkt1', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(2, 'produkt2', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(3, 'produkt3', 2);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(4, 'produkt4', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(5, 'produkt5', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(6, 'produkt6', 2);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(7, 'produkt7', 2);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(8, 'produkt8', 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (1, '2021-02-01', 80, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (2, '2021-02-02', 90, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (3, '2021-02-03', 85, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (4, '2021-01-20', 95, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (5, '2021-02-06', 98, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (6, '2021-02-15', 82, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (7, '2020-02-20', 84, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (8, '2021-02-21', 96, 1);
insert into user_weight(id, date_added, weight, app_user_details_id) VALUES (9, '2021-02-22', 72, 1);
INSERT INTO user_tmr (id, carbohydrates, date_added, fat, protein, tmr, app_user_details_id)
VALUES (1, 153, '2021-01-01', 116.68, 136.8, 2210.85, 1);
INSERT INTO user_tmr (id, carbohydrates, date_added, fat, protein, tmr, app_user_details_id)
VALUES (2, 332, '2021-01-24', 188.53, 136.8, 3572.06, 1);
INSERT INTO user_tmr (id, carbohydrates, date_added, fat, protein, tmr, app_user_details_id)
VALUES (3, 207, '2021-02-15', 138.19, 136.8, 2618.32, 1);
INSERT INTO article (id, category, content, create_date, img_url, title, app_user_id)
VALUES (1, 'Bieganie', 'Praesent vel nunc nisl. Cras sed dictum lorem. Curabitur a nisi vitae mauris auctor semper.
Praesent metus erat, tempor tincidunt volutpat sed, pulvinar sed turpis. Ut cursus tortor non neque gravida, et feugiat
diam vehicula. Duis commodo consequat eros quis mattis. Vivamus nec eros convallis, viverra enim eu, tincidunt ex.
Aenean nisi nulla, interdum sit amet libero ac, elementum lacinia est. Quisque ac congue justo. Nulla pellentesque
interdum nisl nec ullamcorper. ', '2020-05-20', 'https://tricentre.pl/data/include/img/news/1585129978.jpg','Rozwój ducha', 1);
INSERT INTO article (id, category, content, create_date, img_url, title, app_user_id)
VALUES (2, 'Bieganie', 'Praesent vel nunc nisl. Cras sed dictum lorem. Curabitur a nisi vitae mauris auctor semper.
Praesent metus erat, tempor tincidunt volutpat sed, pulvinar sed turpis. Ut cursus tortor non neque gravida, et feugiat
diam vehicula. Duis commodo consequat eros quis mattis. Vivamus nec eros convallis, viverra enim eu, tincidunt ex.
Aenean nisi nulla, interdum sit amet libero ac, elementum lacinia est. Quisque ac congue justo. Nulla pellentesque
interdum nisl nec ullamcorper. ', '2020-05-20', 'https://tricentre.pl/data/include/img/news/1585129978.jpg','Rozwoj
sily', 1);
INSERT INTO article (id, category, content, create_date, img_url, title, app_user_id)
VALUES (3, 'Bieganie', 'Praesent vel nunc nisl. Cras sed dictum lorem. Curabitur a nisi vitae mauris auctor semper.
Praesent metus erat, tempor tincidunt volutpat sed, pulvinar sed turpis. Ut cursus tortor non neque gravida, et feugiat
diam vehicula. Duis commodo consequat eros quis mattis. Vivamus nec eros convallis, viverra enim eu, tincidunt ex.
Aenean nisi nulla, interdum sit amet libero ac, elementum lacinia est. Quisque ac congue justo. Nulla pellentesque
interdum nisl nec ullamcorper. ', '2020-05-20', 'https://tricentre.pl/data/include/img/news/1585129978.jpg','Rozwoj
sily 2', 1);
INSERT INTO article (id, category, content, create_date, img_url, title, app_user_id)
VALUES (4, 'Bieganie', 'Praesent vel nunc nisl. Cras sed dictum lorem. Curabitur a nisi vitae mauris auctor semper.
Praesent metus erat, tempor tincidunt volutpat sed, pulvinar sed turpis. Ut cursus tortor non neque gravida, et feugiat
diam vehicula. Duis commodo consequat eros quis mattis. Vivamus nec eros convallis, viverra enim eu, tincidunt ex.
Aenean nisi nulla, interdum sit amet libero ac, elementum lacinia est. Quisque ac congue justo. Nulla pellentesque
interdum nisl nec ullamcorper. ', '2020-05-20', 'https://tricentre.pl/data/include/img/news/1585129978.jpg','Rozwoj
sily 2', 1);
INSERT INTO article (id, category, content, create_date, img_url, title, app_user_id)
VALUES (5, 'Bieganie', 'Praesent vel nunc nisl. Cras sed dictum lorem. Curabitur a nisi vitae mauris auctor semper.
Praesent metus erat, tempor tincidunt volutpat sed, pulvinar sed turpis. Ut cursus tortor non neque gravida, et feugiat
diam vehicula. Duis commodo consequat eros quis mattis. Vivamus nec eros convallis, viverra enim eu, tincidunt ex.
Aenean nisi nulla, interdum sit amet libero ac, elementum lacinia est. Quisque ac congue justo. Nulla pellentesque
interdum nisl nec ullamcorper. ', '2020-05-20', 'https://tricentre.pl/data/include/img/news/1585129978.jpg','Rozwoj
sily 2', 1);
INSERT INTO exercise (id, description, exercise_category, img_url, media_url, name)
VALUES (1, 'opis', 'BARKI', null, null, 'OHP');
INSERT INTO exercise (id, description, exercise_category, img_url, media_url, name)
VALUES (2, 'opis', 'PLECY', null, null, 'Wiosłowanie');
INSERT INTO exercise (id, description, exercise_category, img_url, media_url, name)
VALUES (3, 'opis', 'PLECY', null, null, 'Podciąganie');
INSERT INTO exercise (id, description, exercise_category, img_url, media_url, name)
VALUES (4, 'opis', 'KLATKA_PIERSIOWA', null, null, 'Wyciskanie na ławce');
INSERT INTO exercise (id, description, exercise_category, img_url, media_url, name)
VALUES (5, 'opis', 'KLATKA_PIERSIOWA', null, null, 'Wyciskanie hantli');
INSERT INTO exercise (id, description, exercise_category, img_url, media_url, name)
VALUES (6, 'opis', 'BRZUCH', null, null, 'Allahy');
