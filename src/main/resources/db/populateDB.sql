DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories)
VALUES  (100000, '2021-10-28 21:30:00', 'ужин первого', 500),
        (100000, '2021-10-28 07:30:00', 'завтрак первого', 800),
        (100000, '2021-10-29 14:30:00', 'обед первого', 400),
        (100000, '2021-10-29 21:30:00', 'ужин первого', 500),
        (100000, '2021-10-29 07:30:00', 'завтрак первого', 800),
        (100000, '2021-10-28 14:30:00', 'обед первого', 800),
        (100001, '2021-10-28 21:30:00', 'ужин второго', 500),
        (100001, '2021-10-28 07:30:00', 'завтрак второго', 400),
        (100001, '2021-10-28 14:30:00', 'обед второго', 600);

