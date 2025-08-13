


CREATE TABLE t_orders (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          order_number VARCHAR(50) NOT NULL,
                          sku_code VARCHAR(100) NOT NULL,
                          price DECIMAL(19,2) NOT NULL,
                          quantity INT NOT NULL,
                          PRIMARY KEY (id)
);
