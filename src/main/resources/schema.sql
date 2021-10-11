CREATE TABLE `user` (
    `id` IDENTITY PRIMARY KEY,
    `login` VARCHAR (100) NOT NULL UNIQUE,
    `password` VARCHAR (255) NOT NULL,
    `status` VARCHAR (10) NOT NULL
);

CREATE TABLE `faculty` (
    `id` IDENTITY PRIMARY KEY,
    `name` VARCHAR (100) NOT NULL UNIQUE
);

