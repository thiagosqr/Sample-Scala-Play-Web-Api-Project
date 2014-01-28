# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table `ecombase_products` (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`store_id` BIGINT NOT NULL,`name` VARCHAR(254) NOT NULL,`hash` VARCHAR(254) NOT NULL);

# --- !Downs

drop table `ecombase_products`;

