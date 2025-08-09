CREATE  TABLE t_inventory (
    id  bigint(20) NOT NULL AUTO_INCREMENT,
    sku_code varchar(64) NOT NULL,
    quantity int(11) NOT NULL,
    PRIMARY KEY (id)
)