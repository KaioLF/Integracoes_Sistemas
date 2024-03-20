CREATE table produto(
	id bigint not null auto_increment,
	nome varchar(100) not null,
	empresa varchar(150) not null,
	descricao varchar (250) not null,
	quantidade int not null,
	marca varchar(250) not null,
	valor decimal(10,2) not null,
	
	primary key(id)
);