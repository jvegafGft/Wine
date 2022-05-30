-- gft03.wines_spa definition
CREATE TABLE `wines_spa`
(
    `id`          int(11) NOT NULL,
    `winery`      text COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
    `wine`        text COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
    `year`        int(11) DEFAULT NULL,
    `rating`      double                           DEFAULT NULL,
    `num_reviews` int(11) DEFAULT NULL,
    `country`     text COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
    `region`      text COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
    `price`       double                           DEFAULT NULL,
    `type`        text COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
    `body`        double                           DEFAULT NULL,
    `acidity`     double                           DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;