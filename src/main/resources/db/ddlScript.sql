USE `kb_catalog_inventory` ;

#------------------------- ALTER STATEMENTS FOR EXISTING SCHEMA ------------------------
ALTER TABLE `kb_catalog_inventory`.`brands` add trending BIT(1) NOT NULL;
ALTER TABLE `kb_catalog_inventory`.`brand_model_category` ADD INDEX category_name_index (category_name);
ALTER TABLE `kb_catalog_inventory`.`category` ADD INDEX category_name_index (category_name);
ALTER TABLE `kb_catalog_inventory`.`category` ADD INDEX category_id_index (id);
ALTER TABLE `kb_catalog_inventory`.`image_gallery` ADD INDEX image_gallery_id_index (id);

#------------------------- CREATE TABLE STATEMENTS FOR EXISTING SCHEMA ------------------------
drop table if exists `kb_catalog_inventory`.`offer`;
create table if not exists `kb_catalog_inventory`.`offer`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    banner_length INT NOT NULL,
    occasion VARCHAR(100) NOT NULL,
    bank_offer VARCHAR(150) NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    PRIMARY KEY (id)
);

drop table if exists `kb_catalog_inventory`.`product_banner`;
create table if not exists `kb_catalog_inventory`.`product_banner`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_category VARCHAR(100) NOT NULL,
    discount_detail VARCHAR(256) NOT NULL,
    image_gallery_id BIGINT NOT NULL,
    message VARCHAR(256) NOT NULL,
    PRIMARY KEY (id),
    INDEX product_banner_id_index (id ASC ),
    FOREIGN KEY (product_category) REFERENCES category(category_name),
    FOREIGN KEY (image_gallery_id) REFERENCES image_gallery(id)
);

drop table if exists `kb_catalog_inventory`.`product_deal`;
create table if not exists `kb_catalog_inventory`.`product_deal`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(256) NOT NULL,
    banner_id BIGINT NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    product_category VARCHAR(100) NOT NULL,
    image_gallery_id BIGINT NOT NULL,
    discount_range int NOT NULL,
    brand_model_id BIGINT(20),
    is_active BIT(1) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (banner_id) REFERENCES product_banner(id),
    FOREIGN KEY (product_category) REFERENCES brand_model_category(category_name),
    FOREIGN KEY (image_gallery_id) REFERENCES image_gallery(id),
    FOREIGN KEY (brand_model_id) REFERENCES brand_model_category(brand_model_id)
);

drop table if exists `kb_catalog_inventory`.`trending_deal`;
create table if not exists `kb_catalog_inventory`.`trending_deal`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    category_id BIGINT NOT NULL,
    parent_id BIGINT,
    discount_range int NOT NULL,
    image_gallery_id BIGINT NOT NULL,
    description VARCHAR(256) NOT NULL,
    brand_model_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (parent_id) REFERENCES category(parent_id),
    FOREIGN KEY (image_gallery_id) REFERENCES image_gallery(id),
    FOREIGN KEY (brand_model_id) REFERENCES brand_model_category(brand_model_id)
);