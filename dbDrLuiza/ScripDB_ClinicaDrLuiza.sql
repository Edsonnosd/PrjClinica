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

-- descrevendo tabela
# describe tbUsuario;  

-- insere dados na tabela (CRUD) create -> insert
insert into tbUsuario(id_usuario,usuario,fone,
						login,senha)
		values(1,'Edson','88888-9999','edson','123');

insert into tbUsuario(id_usuario,usuario,fone,
						login,senha)
		values(2,'Redjane','77777-5555','redjane','123');

-- exibe dados da tabela (CRUD) red -> select

describe tbusuario;
select * from tbUsuario where login = 'edson' and senha = '123';

-- modifica dados na tabela (CRUD) update -> update
# update tbUsuario set fone='88888-8888' where id_usuario=1;

-- apaga registro da tabela (CRUD) delete -> delete
# delete from tbUsuario where id_usuario=1;  




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
    obs varchar(800)
    
  
);

delete from tbpaciente;
delete from tbpac;