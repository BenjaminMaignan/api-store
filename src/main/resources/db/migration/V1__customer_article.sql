CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer (
    id UUID PRIMARY KEY,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);

CREATE TABLE article (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price FLOAT NOT NULL
);

CREATE TABLE article_item
(
    id         UUID PRIMARY KEY,
    article_id UUID NOT NULL,
    size       VARCHAR NOT NULL,
    color      VARCHAR NOT NULL,
    available_stock INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (article_id) REFERENCES article (id)
);