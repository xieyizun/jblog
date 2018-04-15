drop table if exists users;
create table users (
	`id` int not null auto_increment,
	`name` varchar(20) not null,
	`email` varchar(50) not null,
	`password` varchar(20) not null,
	`is_admin` boolean default false,
	`locked` boolean default false,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`),
	unique(`name`),
	unique(`email`),
	index `email_password`(`email`, `password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists categories;
create table categories (
	`id` int not null auto_increment,
	`name` varchar(100) not null,
	`parent_id` int default null,
	`user_id` int not null,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists articles;
create table articles (
	`id` int not null auto_increment,
	`subject` varchar(255) not null,
	`content` text,
	`user_id` int not null,
	`category_id` int not null,
	`status` tinyint default 0,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`),
	constraint `article_user` foreign key(`user_id`) references `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists article_attachments;
create table article_attachments (
	`id` int not null auto_increment,
	`article_id` int not null,
	`attachment_url` varchar(255) not null,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`),
	constraint `article_attachments` foreign key(`article_id`) references `articles`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists article_tags;
create table article_tags (
	`id` int unsigned not null auto_increment,
	`name` varchar(100) not null,
	`article_id` int not null,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`),
	unique(`name`),
	constraint `article_tag` foreign key(`article_id`) references `articles`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists comments;
create table comments (
	`id` int unsigned auto_increment not null,
	`content` text,
	`article_id` int not null,
	`user_id` int not null,
	`comment_id` int default null,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`),
	constraint `comments_user` foreign key(`user_id`) references `users`(`id`),
	constraint `comments_article` foreign key(`article_id`) references `articles`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists favor_counters;
create table favor_counters (
	`id` int unsigned auto_increment not null,
	`article_id` int default null,
	`user_id` int default null,
	`comment_id` int default null,
	`favor_count` int default 0,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists view_counters;
create table view_counters (
	`id` int unsigned auto_increment not null,
	`article_id` int default null,
	`user_id` int default null,
	`view_count` int default 0,
	
	`created_at` datetime,
	`updated_at` datetime,
	`enabled` boolean default true,
	primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

