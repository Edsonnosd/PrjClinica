-- cria banco de dados
create database dbClinica;

-- escolhe o banco de dados 
use dbClinica;

-- criar tabela
create table tbUsuario(
	id_usuario int primary key,
	usuario varchar(50) not null,
	fone varchar(15),
	login varchar(15) not null unique,
	senha varchar(15) not null
);


create table tbFornecedores (
  id int auto_increment primary key,
  nome varchar(150),
  cnpj varchar (30),
  email varchar(100),
  telefone varchar(16),
  celular varchar(16),
  cep varchar(15),
  endereco varchar (150),
  numero int,
  complemento varchar (50),
  bairro varchar (60),
  cidade varchar (50),
  estado char (2)
);

create table tbConsulta(
	id_consulta int auto_increment primary key,
    id_paciente int,
    nome_paciente varchar(150),
    data_consulta timestamp default current_timestamp,
    nome_resp varchar(150) not null,
    obs varchar(800),
    
    foreign key (id_paciente) references tbpaciente(id_paciente);
  
);
