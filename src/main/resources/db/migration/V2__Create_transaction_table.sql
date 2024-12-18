CREATE TABLE Customers
(
    id             BIGSERIAL PRIMARY KEY,
    purchase_date  DATE           NOT NULL,
    description    VARCHAR(255)   NOT NULL,
    sector         VARCHAR(100)   NOT NULL,
    payment_method VARCHAR(50)    NOT NULL,
    amount         DECIMAL(19, 2) NOT NULL,
    status         BOOLEAN        NOT NULL
);