insert into user_roles(id, role, description) VALUES (1, "ROLE_USER", "default role for user");
insert into user_roles(id, role, description) VALUES (2, "ROLE_ADMIN", "default role for admin");
insert into app_user(id, email, is_enabled, password, rules_accepted, username)
VALUES (1, "notentego678@gmail.com", true,"$2a$10$JAu1KH2QCPGZqhyA0JUF0uurkrXOneGATPCvH9oikxKHFF6u7zrgC", true, "mefiu678");
insert into app_user(id, email, is_enabled, password, rules_accepted, username)
VALUES (2, "testuser@gmail.com", true,"$2a$10$JAu1KH2QCPGZqhyA0JUF0uurkrXOneGATPCvH9oikxKHFF6u7zrgC", true,
"testuser");
insert into app_user_roles(app_user_id, roles_id) VALUES (1, 1);
insert into app_user_roles(app_user_id, roles_id) VALUES (1, 1);
insert into meal_details(id, description, img_url) VALUES (1, 'opis 1', 'url');
insert into meal_details(id, description, img_url) VALUES (2, 'opis 2', 'url');
insert into meal_details(id, description, img_url) VALUES (3, 'opis 3', 'url');
insert into meal_details(id, description, img_url) VALUES (4, 'opis 4', 'url');
insert into meal_details(id, description, img_url) VALUES (5, 'opis 5', 'url');
insert into meal_details(id, description, img_url) VALUES (6, 'opis 6', 'url');
insert into meal_details(id, description, img_url) VALUES (7, 'opis 7', 'url');
insert into meal_details(id, description, img_url) VALUES (8, 'opis 8', 'url');
insert into meal_details(id, description, img_url) VALUES (9, 'opis 9', 'url');
insert into meal_details(id, description, img_url) VALUES (10, 'opis 10', 'url');
insert into meal_details(id, description, img_url) VALUES (11, 'opis 11', 'url');
insert into meal_details(id, description, img_url) VALUES (12, 'opis 12', 'url');
insert into meal_details(id, description, img_url) VALUES (13, 'opis 13', 'url');
insert into meal_details(id, description, img_url) VALUES (14, 'opis 14', 'url');
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (1, 100,  50,1000, 100, 'Produkt 1',10, 1);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (2, 100,  50,1000, 100, 'Produkt 2',10, 2);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (3, 100,  50,1000, 100, 'Produkt 3',10, 3);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (4, 100,  50,1000, 100, 'Produkt 4',10, 4);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (5, 100,  50,1000, 100, 'Produkt 5',10, 5);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (6, 100,  50,1000, 100, 'Produkt 6',10, 6);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (7, 100,  50,1000, 100, 'Produkt 7',10, 7);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (8, 100,  50,1000, 100, 'Produkt 8',10, 8);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (9, 100,  50,1000, 100, 'Produkt 9',10, 9);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (10, 100,  50,1000, 100, 'Produkt 10',10, 10);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (11, 100,  50,1000, 100, 'Produkt 11',10, 11);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (12, 100,  50,1000, 100, 'Produkt 12',10, 12);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (13, 100,  50,1000, 100, 'Produkt 13',10, 13);
insert into meal(id, carbohydrates, fat, goodness, grammage, name, protein, meal_details_id)
VALUES (14, 100,  50,1000, 100, 'Produkt 14',10, 14);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(1, 'produkt1', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(2, 'produkt2', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(3, 'produkt3', 2);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(4, 'produkt4', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(5, 'produkt5', 1);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(6, 'produkt6', 2);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(7, 'produkt7', 2);
insert into shopping_list_element(id, list_element, app_user_id) VALUES(8, 'produkt8', 1);