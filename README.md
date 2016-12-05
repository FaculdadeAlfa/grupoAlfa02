Instalaço da aplicação

1 - Clonar o repositório para o eclipse.
 Tomcat 8
 Java 7 ou superior
 Banco de Dados mysql


2 - Cria banco "alfa02" no mysql e executar scripts abaixo para criar o usuário com permissão de acesso:

 create table usuario (username varchar(50), password varchar(10), enable bit);
 insert into usuario values ('admin', '12345', 1);
 create table usuario_autorizacao (usuario_username varchar(50), autorizacoes_nome varchar(30));
 insert into usuario_autorizacao values ('admin', 'ROLE_USER');
 
 As outras tabelas são criadas automaticamente pelo hibernate.



Acesso:
http://localhost:8080/alfa02
Usuário: admin
Senha: 12345
