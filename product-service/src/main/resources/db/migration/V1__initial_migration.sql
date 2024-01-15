CREATE TABLE categories
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE product_properties
(
    id          UUID NOT NULL,
    product_id  UUID NOT NULL,
    property_id UUID NOT NULL,
    value       VARCHAR(255),
    CONSTRAINT pk_product_properties PRIMARY KEY (id)
);

CREATE TABLE products
(
    id             UUID    NOT NULL,
    sku_code       VARCHAR(255),
    name           VARCHAR(255),
    price          DECIMAL,
    description    VARCHAR(255),
    stock          INTEGER,
    image_url      VARCHAR(255),
    is_deleted     BOOLEAN,
    rating         DECIMAL NOT NULL,
    num_of_reviews INTEGER NOT NULL,
    status         VARCHAR(255),
    subcategory_id UUID    NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE properties
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_properties PRIMARY KEY (id)
);

CREATE TABLE reviews
(
    id          UUID NOT NULL,
    description VARCHAR(255),
    value       VARCHAR(255),
    user_id     UUID,
    product_id  UUID NOT NULL,
    CONSTRAINT pk_reviews PRIMARY KEY (id)
);

CREATE TABLE subcategories
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    category_id UUID NOT NULL,
    CONSTRAINT pk_subcategories PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_name UNIQUE (name);

ALTER TABLE products
    ADD CONSTRAINT uc_products_sku_code UNIQUE (sku_code);

ALTER TABLE subcategories
    ADD CONSTRAINT uc_subcategories_name UNIQUE (name);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_SUBCATEGORY FOREIGN KEY (subcategory_id) REFERENCES subcategories (id);

ALTER TABLE product_properties
    ADD CONSTRAINT FK_PRODUCT_PROPERTIES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE product_properties
    ADD CONSTRAINT FK_PRODUCT_PROPERTIES_ON_PROPERTY FOREIGN KEY (property_id) REFERENCES properties (id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE subcategories
    ADD CONSTRAINT FK_SUBCATEGORIES_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);