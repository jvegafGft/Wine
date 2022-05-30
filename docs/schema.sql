DROP TABLE IF EXISTS `wine`;
DROP TABLE IF EXISTS `region`;
DROP TABLE IF EXISTS `winery`;
DROP TABLE IF EXISTS `type`;

CREATE TABLE `winery`
(
    `id`   INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` text COLLATE utf8mb4_spanish2_ci DEFAULT NULL
);
INSERT INTO `winery` (`name`, `id`) (SELECT DISTINCT winery, 0 FROM wines_spa);

CREATE TABLE `type`
(
    `id`   int(11) AUTO_INCREMENT primary key,
    `name` text COLLATE utf8mb4_spanish2_ci DEFAULT NULL
);
INSERT INTO `type` (name, id) (SELECT DISTINCT type, 0 FROM wines_spa);

CREATE TABLE region
(
    `id`      int(11) AUTO_INCREMENT PRIMARY KEY,
    `name`    text COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
    `country` text COLLATE utf8mb4_spanish2_ci DEFAULT NULL
);
INSERT INTO `region` (name, id, country) (SELECT DISTINCT region, 0, 'Espana' FROM wines_spa);


create table wine
(
    `id`          int(11) AUTO_INCREMENT PRIMARY KEY,
    `name`        text COLLATE utf8mb4_spanish2_ci DEFAULT NULL,
    `year`        int(11) DEFAULT NULL,
    `num_reviews` int(11) DEFAULT NULL,
    `rating`      double                           DEFAULT NULL,
    `price`       double                           DEFAULT NULL,
    `body`        double                           DEFAULT NULL,
    `acidity`     double                           DEFAULT NULL,
    `winery_id`   int REFERENCES winery (id),
    `type_id`     int REFERENCES `type` (id),
    `region_id`   int REFERENCES region (id)
);


INSERT INTO wine
SELECT w.id          as id,
       w.wine        as name,
       w.year        as `year`,
       w.num_reviews as num_reviews,
       w.rating      as rating,
       w.price       as price,
       w.body        as body,
       w.acidity     as acidity,
       winery.id     as winery_id,
       type.id       as type_id,
       region.id     as region_id
FROM wines_spa w
         LEFT JOIN winery on winery.name = w.winery
         LEFT JOIN `type` on type.name = w.type
         LEFT JOIN region on region.name = w.region
ORDER BY id;
