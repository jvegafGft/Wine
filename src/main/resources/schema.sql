drop table if exists `wine`;
drop table if exists `region`;
drop table if exists `type`;
drop table if exists `winery`;

create table `region` (
    `id` INT auto_increment
        primary key,
    `country` VARCHAR(255) null,
    `name` VARCHAR(255) null
);

create table `type` (
    `id` INT auto_increment
        primary key,
    `name` VARCHAR(255) null
);

create table `winery` (
    `id` INT auto_increment
        primary key,
    `name` VARCHAR(255) null
);

create table `wine` (
    `id`INT auto_increment
        primary key,
    `acidity` VARCHAR(255) null,
    `body` VARCHAR(255) null,
    `name` VARCHAR(255) null,
    `num_reviews` INT not null,
    `price` DOUBLE not null,
    `rating` DOUBLE not null,
    `year` VARCHAR(255) null,
    `region_id` INT null,
    `type_id` INT null,
    `winery_id` INT null,
    constraint `winery_wine`
        foreign key (`winery_id`) references winery(`id`),
    constraint `type_wine`
        foreign key (`type_id`) references type(`id`),
    constraint `region_wine`
        foreign key (`region_id`) references region(`id`)
);


