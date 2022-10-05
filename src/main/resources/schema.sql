DROP TABLE IF EXISTS `CURRENCY`;

CREATE TABLE CURRENCY (
                                  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                  code varchar(20) NOT NULL,
                                  name varchar(20) NOT NULL,
                                  update_date TIMESTAMP
);

INSERT INTO CURRENCY (code, name, update_date) VALUES
                                                   ('USD', '美元', now()),
                                                   ('GBP', '英鎊', now()),
                                                   ('EUR', '歐元', now());