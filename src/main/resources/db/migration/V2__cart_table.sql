CREATE TABLE cart
(
    id          UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE cart_item
(
    id         UUID PRIMARY KEY,
    cart_id    UUID,
    article_item_id UUID,
    quantity   INT  DEFAULT 0,
    FOREIGN KEY (cart_id) REFERENCES cart (id),
    FOREIGN KEY (article_item_id) REFERENCES article_item (id)
);