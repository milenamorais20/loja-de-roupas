CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    tamanho CHAR(1),
    preco DOUBLE PRECISION,
    vendido BOOLEAN,
    venda_id INTEGER,
    CONSTRAINT fk_produto_venda FOREIGN KEY (venda_id) REFERENCES venda(id)
);