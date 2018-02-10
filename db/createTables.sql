drop table if exists users;
create table users (
	`id` int unsigned auto_increment,
	`name` varchar(20) not null,
	`email` varchar(50) not null,
	`password` varchar(20) not null,
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists categories;
create table categories (
	`id` int unsigned auto_increment,
	`name` varchar(100) not null,
	`parent_id` int,
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists articles;
create table articles (
	`id` int unsigned auto_increment,
	`subject` varchar(255) not null,
	`content` text,
	`user_id` int not null,
	`category_id` int not null,
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;