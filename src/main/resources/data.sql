insert into user_roles(id, role, description) VALUES (1, "ROLE_USER", "default role for user");
insert into user_roles(id, role, description) VALUES (2, "ROLE_ADMIN", "default role for admin");
insert into app_user(id, email, is_enabled, password, username) VALUES (999, "notentego678@gmail.com", true, "$2a$10$JAu1KH2QCPGZqhyA0JUF0uurkrXOneGATPCvH9oikxKHFF6u7zrgC", "mefiu678");
insert into app_user_roles(app_user_id, roles_id) VALUES (999, 1);