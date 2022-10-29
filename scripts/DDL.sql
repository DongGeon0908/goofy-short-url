CREATE DATABASE goofy CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `short_url`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `createdAt`   datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `modifiedAt`  datetime                                                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `type`        int                                                           DEFAULT NULL,
    `origin_url`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `origin_url_UNIQUE` (`origin_url`)
) ENGINE=InnoDB AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE INDEX idx_type ON short_url (id);
CREATE INDEX idx_origin_url ON short_url (origin_url);
