CREATE TABLE orders
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_id VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL
);

CREATE TABLE order_item
(
    order_id   UUID         NOT NULL,
    item_key   INTEGER      NOT NULL,
    product_id VARCHAR(255) NOT NULL,

    PRIMARY KEY (order_id, item_key),

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
            REFERENCES orders (id)
            ON DELETE CASCADE
);