CREATE DATABASE goofy CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


CREATE TABLE `short_url`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `createdAt`   datetime                                DEFAULT CURRENT_TIMESTAMP,
    `modifiedAt`  datetime                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `type`        int                                     DEFAULT NULL,
    `url`         varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE INDEX idx_id ON short_url (id);
