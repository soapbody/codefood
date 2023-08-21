create table forma_pagamento (
	id bigserial not null,
	descricao varchar(60) not null,
	primary key (id)
);
create table grupo (
	id bigserial not null,
	nome varchar(60) not null,

	primary key (id)
);

create table grupo_permissao (
	grupo_id bigint not null,
	permissao_id bigint not null,

	primary key (grupo_id, permissao_id)
);

create table permissao (
	id bigserial not null,
	descricao varchar(60) not null,
	nome varchar(100) not null,

	primary key (id)
);

create table produto (
	id bigserial not null,
	restaurante_id bigint not null,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	ativo boolean not null,

	primary key (id)
);

create table restaurante (
	id bigserial not null,
	cozinha_id bigint not null,
	nome varchar(80) not null,
	taxa_frete decimal(10,2) not null,
	last_update timestamp not null,
	creation_date timestamp not null,

	cidade_id bigint,
	cep varchar(9),
	logradouro varchar(100),
	numero varchar(20),
	complemento varchar(60),
	bairro varchar(60),

	primary key (id)
);

create table restaurante_forma_pagamento (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,

	primary key (restaurante_id, forma_pagamento_id)
);

create table usuario (
	id bigserial not null,
	nome varchar(80) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	creation_date timestamp not null,

	primary key (id)
);

create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,

	primary key (usuario_id, grupo_id)
);


alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo
foreign key (grupo_id) references grupo (id);

alter table produto add constraint fk_produto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint fk_restaurante_cozinha
foreign key (cozinha_id) references cozinha (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto
foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);