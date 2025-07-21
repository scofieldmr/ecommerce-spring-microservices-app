CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_number VARCHAR(255),
    sku_code VARCHAR(255),
    price DECIMAL(19, 2),
    quantity INT
);