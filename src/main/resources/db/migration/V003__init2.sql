create table cidade (
	id bigserial not null primary key,
	nome varchar(80) not null,
	estado_id bigint not null
);

alter table cidade add constraint fk_cidade_estado
foreign key (estado_id) references estado (id);