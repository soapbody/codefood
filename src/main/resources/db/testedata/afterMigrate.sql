

INSERT INTO estado (nome) VALUES ('São Paulo');
INSERT INTO estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO estado (nome) VALUES ('Minas Gerais');

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


insert into restaurante (id, nome, taxa_frete, cozinha_id, creation_date, last_update, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, current_timestamp at time zone 'UTC', current_timestamp at time zone 'UTC', true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, creation_date, last_update, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, current_timestamp at time zone 'UTC', current_timestamp at time zone 'UTC', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, creation_date, last_update, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, current_timestamp at time zone 'UTC', current_timestamp at time zone 'UTC', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, creation_date, last_update, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, current_timestamp at time zone 'UTC', current_timestamp at time zone 'UTC', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, creation_date, last_update, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 3, current_timestamp at time zone 'UTC', current_timestamp at time zone 'UTC', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, creation_date, last_update, ativo, aberto) values (6, 'Bar da Maria', 6, 3, current_timestamp at time zone 'UTC', current_timestamp at time zone 'UTC', true, true);


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into usuario (id, nome, email, senha, creation_date) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', current_timestamp at time zone 'UTC'),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', current_timestamp at time zone 'UTC'),
(3, 'José Souza', 'jose.aux@algafood.com', '123', current_timestamp at time zone 'UTC'),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', current_timestamp at time zone 'UTC'),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', current_timestamp at time zone 'UTC');

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

--insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    --endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                --status, data_criacao, subtotal, taxa_frete, valor_total)
--values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
        --'CRIADO', current_timestamp at time zone 'UTC', 298.90, 10, 308.90);

--insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
--values (1, 1, 1, 1, 78.9, 78.9, null);

--insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
--values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


--insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    --endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	                --status, data_criacao, subtotal, taxa_frete, valor_total)
--values (2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        --'CRIADO', current_timestamp at time zone 'UTC', 79, 0, 79);

--insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
--values (3, 2, 6, 1, 79, 79, 'Ao ponto');