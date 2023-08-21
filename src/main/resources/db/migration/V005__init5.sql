create table pedido (
	id bigserial not null primary key,
	sub_total numeric not null,
	taxa_frete numeric not null,
	valor_total numeric not null,
	data_criacao timestamp not null,
	data_cancelamento timestamp not null,
	data_entrega timestamp not null,
	status_pedido varchar(30) not null,
	restaurante_id bigint not null references restaurante(id),
	cliente_id bigint not null references usuario(id),
	cep varchar(30) not null,
	logradouro varchar(80) not null,
	numero varchar(30) not null,
	complemento varchar(80),
	bairro varchar(60) not null,
	cidade varchar(60) not null,
	forma_pagamento bigint not null references forma_pagamento(id)
);

create table item_pedido (
	id bigserial not null primary key,
	produto_id bigint not null references produto(id),
	pedido_id bigint not null references pedido(id),
	quantidade bigint not null,
	preco_unitario numeric not null,
	preco_total numeric not null,
	observacao varchar(120)
);