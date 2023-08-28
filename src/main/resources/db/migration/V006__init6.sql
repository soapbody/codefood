create table pedido (
	id bigserial not null primary key,
	subtotal numeric not null,
	taxa_frete numeric not null,
	valor_total numeric not null,
	data_criacao TIMESTAMPTZ DEFAULT (current_timestamp AT TIME ZONE 'UTC') not null,
	data_cancelamento TIMESTAMPTZ,
	data_entrega TIMESTAMPTZ,
	data_confirmacao TIMESTAMPTZ,
	status varchar(30) not null,
	restaurante_id bigint not null references restaurante(id),
	usuario_cliente_id bigint not null references usuario(id),
	endereco_cep varchar(30) not null,
	endereco_logradouro varchar(80) not null,
	endereco_numero varchar(30) not null,
	endereco_complemento varchar(80),
	endereco_bairro varchar(60) not null,
	endereco_cidade_id bigint not null,
	forma_pagamento_id bigint not null references forma_pagamento(id)
);
alter table pedido add constraint fk_pedido_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table pedido add constraint fk_pedido_usuario_cliente
foreign key (usuario_cliente_id) references usuario (id);

alter table pedido add constraint fk_pedido_forma_pagamento
foreign key (forma_pagamento_id) references forma_pagamento (id);

create table item_pedido (
	id bigserial not null primary key,
	produto_id bigint not null references produto(id),
	pedido_id bigint not null references pedido(id),
	quantidade integer not null,
	preco_unitario numeric not null,
	preco_total numeric not null,
	observacao varchar(120),
	CONSTRAINT uk_item_pedido_produto UNIQUE (pedido_id, produto_id)
);

alter table item_pedido add constraint fk_item_pedido_pedido
foreign key (pedido_id) references pedido (id);

alter table item_pedido add constraint fk_item_pedido_produto
foreign key (produto_id) references produto (id);