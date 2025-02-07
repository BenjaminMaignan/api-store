CREATE TABLE cart
(
    id          UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE cart_item
(
    id         UUID PRIMARY KEY,
    cart_id    UUID NOT NULL,
    article_id UUID NOT NULL,
    quantity   INT  NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart (id),
    FOREIGN KEY (article_id) REFERENCES article (id)
);