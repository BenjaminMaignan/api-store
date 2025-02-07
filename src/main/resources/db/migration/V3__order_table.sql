CREATE TABLE "order"
(
    id          UUID PRIMARY KEY,
    customer_id UUID    NOT NULL,
    total       DECIMAL(10, 2) NOT NULL,
    status      VARCHAR NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE order_item
(
    id         UUID PRIMARY KEY,
    order_id   UUID NOT NULL,
    article_id UUID NOT NULL,
    quantity   INT  NOT NULL,
    FOREIGN KEY (order_id) REFERENCES "order" (id),
    FOREIGN KEY (article_id) REFERENCES article (id)
);