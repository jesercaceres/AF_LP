# Dentro do Pacote View selecione o FrmLoginVIEW.java e execute-o utilizando a tecla "SHIFT juntamente com a tecla F6".
## Script banco de dados.
## Copie e cole esse código em seu MySQL para estar criando as tabelas utilizadas pelas classes DAO.

create database bancoteste;

USE bancoteste;

DROP TABLE IF EXISTS login;

CREATE TABLE login (

email VARCHAR(50) PRIMARY KEY,

nome VARCHAR(50) NOT NULL,

senha VARCHAR(100) NOT NULL

);


DROP TABLE IF EXISTS funcionario;

CREATE TABLE funcionario (

cpf_funcionario CHAR(14) PRIMARY KEY,

nome_funcionario VARCHAR(50) NOT NULL,

sexo_funcionario VARCHAR(10),

endereco_funcionario VARCHAR(100),

cidade_funcionario VARCHAR(50),

estado_funcionario VARCHAR(50)

);
