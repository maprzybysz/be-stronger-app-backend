insert into user_roles(id, role, description)
VALUES (1, "ROLE_USER", "default role for user");
insert into user_roles(id, role, description)
VALUES (2, "ROLE_ADMIN", "default role for admin");
insert into app_user(id, email, is_enabled, password, rules_accepted, username)
VALUES (1, "notentego678@gmail.com", true, "$2a$10$JAu1KH2QCPGZqhyA0JUF0uurkrXOneGATPCvH9oikxKHFF6u7zrgC", true,
        "mefiu678");
insert into app_user_roles(app_user_id, roles_id)
VALUES (1, 1);
insert into meal(id, carbohydrates, description, fat, goodness, grammage, name, protein)
VALUES (1, 100, 'pyszna pizza', 50, 1000, 100, 'pizza Lidl', 10);
insert into meal(id, carbohydrates, description, fat, goodness, grammage, name, protein)
VALUES (2, 100, 'pyszna pizza ', 50,
        1000, 100, 'pizza Biedronka', 10);
insert into meal(id, carbohydrates, description, fat, goodness, grammage, name, protein)
VALUES (3, 100, 'pyszny kebab', 50, 1000, 100, 'kebab', 10);
insert into meal(id, carbohydrates, description, fat, goodness, grammage, name, protein)
VALUES (4, 100, 'pyszna pizza', 50, 1000, 100, 'Kerf pizza', 10);

INSERT INTO eaten_meal (id, meal_date, meal_name, meal_time, total_carbohydrates, total_fat, total_goodness, total_grammage, total_protein, username)
VALUES (1, '2021-01-21', 'pizza z Biedronki', 'KOLACJA', 100, 100, 100, 100, 100, 'mefiu678');
INSERT INTO eaten_meal (id, meal_date, meal_name, meal_time, total_carbohydrates, total_fat,
                                         total_goodness, total_grammage, total_protein, username)
VALUES (2, '2021-01-21', 'pizza z Biedronki', 'ŚNIADANIE', 100, 100, 100, 100, 100, 'mefiu678');
INSERT INTO eaten_meal (id, meal_date, meal_name, meal_time, total_carbohydrates, total_fat,
                                         total_goodness, total_grammage, total_protein, username)
VALUES (3,
    '2021-01-21',
    'pizza z Lidl',
    'PRZEKĄSKA', 100, 100, 100, 100, 100, 'mefiu678');
INSERT INTO eaten_meal (id, meal_date, meal_name, meal_time, total_carbohydrates, total_fat,
                                         total_goodness, total_grammage, total_protein, username)
VALUES (4,
    '2021-01-21',
    'pizza z '
    'Biedronki',
    'OBIAD', 100, 100,
    100, 100, 100, 'mefiu678');