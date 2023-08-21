create table endereco (
    cep varchar(30),
    logradouro varchar(60),
    numero varchar(60),
    complemento varchar(60),
    bairro varchar(60),
    cidade_id bigint not null
);

alter table endereco add constraint fk_endereco_cidade
foreign key (cidade_id) references cidade (id);