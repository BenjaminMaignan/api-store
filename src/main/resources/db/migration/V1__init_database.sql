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
    price DECIMAL(10, 2) NOT NULL,
    available_stock INTEGER NOT NULL DEFAULT 0
);
