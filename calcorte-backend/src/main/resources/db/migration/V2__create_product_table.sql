CREATE TABLE IF NOT EXISTS products
(
    id bigserial NOT NULL,
    code character varying(255),
    description character varying(255),
    material_type integer,
    CONSTRAINT products_pk PRIMARY KEY (id),
    CONSTRAINT products_uk UNIQUE (code)
)