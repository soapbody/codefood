BEGIN;

-- Desativar verificações de chave estrangeira
SET CONSTRAINTS ALL DEFERRED;

DELETE FROM grupo_permissao;
DELETE FROM restaurante_forma_pagamento;
DELETE FROM usuario_grupo;
DELETE FROM produto;
DELETE FROM restaurante; -- Excluir registros da tabela 'restaurante' antes da tabela 'cozinha'
DELETE FROM usuario;
DELETE FROM forma_pagamento;
DELETE FROM grupo;
DELETE FROM permissao;
DELETE FROM cozinha; -- Agora você pode excluir registros da tabela 'cozinha'
DELETE FROM cidade;
DELETE FROM estado;

-- Redefinir sequências
ALTER SEQUENCE cidade_id_seq RESTART WITH 1;
ALTER SEQUENCE cozinha_id_seq RESTART WITH 1;
ALTER SEQUENCE estado_id_seq RESTART WITH 1;
ALTER SEQUENCE forma_pagamento_id_seq RESTART WITH 1;
ALTER SEQUENCE grupo_id_seq RESTART WITH 1;
ALTER SEQUENCE permissao_id_seq RESTART WITH 1;
ALTER SEQUENCE produto_id_seq RESTART WITH 1;
ALTER SEQUENCE restaurante_id_seq RESTART WITH 1;
ALTER SEQUENCE usuario_id_seq RESTART WITH 1;
-- Repita para outras tabelas conforme necessário

COMMIT;


INSERT INTO estado (nome, id) VALUES ('São Paulo', 1);
INSERT INTO estado (nome, id) VALUES ('Rio de Janeiro', 2);
INSERT INTO estado (nome, id) VALUES ('Minas Gerais', 3);

INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Rio de Janeiro', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Belo Horizonte', 3);

INSERT INTO cozinha (descricao, nome) VALUES ('Brasileira', 'Cozinha Brasileira');
INSERT INTO cozinha (descricao, nome) VALUES ('Italiana', 'Cozinha Italiana');
INSERT INTO cozinha (descricao, nome) VALUES ('Japonesa', 'Cozinha Japonesa');

INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Crédito');
INSERT INTO forma_pagamento (descricao) VALUES ('Dinheiro');
INSERT INTO forma_pagamento (descricao) VALUES ('Pix');

INSERT INTO grupo (nome) VALUES ('Administradores');
INSERT INTO grupo (nome) VALUES ('Clientes');
INSERT INTO grupo (nome) VALUES ('Fornecedores');

INSERT INTO permissao (descricao, nome) VALUES ('Acesso Total', 'Admin');
INSERT INTO permissao (descricao, nome) VALUES ('Acesso de Leitura', 'Leitura');
INSERT INTO permissao (descricao, nome) VALUES ('Acesso de Escrita', 'Escrita');

INSERT INTO restaurante (creation_date, last_update, nome, taxa_frete, cozinha_id) VALUES (current_timestamp, current_timestamp, 'Restaurante A', 10.0, 1);
INSERT INTO restaurante (creation_date, last_update, nome, taxa_frete, cozinha_id) VALUES (current_timestamp, current_timestamp, 'Restaurante B', 5.0, 2);
INSERT INTO restaurante (creation_date, last_update, nome, taxa_frete, cozinha_id) VALUES (current_timestamp, current_timestamp, 'Restaurante C', 15.0, 3);

INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id) VALUES (true, 'Feijoada', 'Feijoada Completa', 25.0, 1);
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id) VALUES (true, 'Pizza', 'Pizza de Calabresa', 30.0, 2);
INSERT INTO produto (ativo, descricao, nome, preco, restaurante_id) VALUES (true, 'Sushi', 'Combo Sushi', 40.0, 3);

INSERT INTO usuario (creation_date, email, nome, senha) VALUES (current_timestamp, 'admin@example.com', 'Admin', 'password');
INSERT INTO usuario (creation_date, email, nome, senha) VALUES (current_timestamp, 'user1@example.com', 'User 1', 'password');
INSERT INTO usuario (creation_date, email, nome, senha) VALUES (current_timestamp, 'user2@example.com', 'User 2', 'password');
