create table estado (
	id bigserial not null primary key,
	nome varchar(80) not null
);

--INSERT into estado(nome) select distinct nome_estado from cidade;

--alter table cidade add column estado_id bigserial;

--UPDATE cidade c SET estado_id = (SELECT e.id FROM estado e WHERE e.nome = c.nome_estado);


--ALTER TABLE cidade add constraint fk_cidade_estado
--foreign key (estado_id) references estado(id);

--alter table cidade drop column nome_estado;

--alter table cidade rename column nome_cidade to nome;
--alter table cidade alter column nome type varchar(80);
--alter table cidade alter column nome set not null;