CREATE TABLE venda(
    id SERIAL PRIMARY KEY NOT NULL,
    usuario_id INTEGER,
    CONSTRAINT fk_venda_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);