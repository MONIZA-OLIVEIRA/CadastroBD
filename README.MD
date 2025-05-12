# CadastroBD

Projeto Java com JDBC para cadastro de Pessoas Físicas e Jurídicas, utilizando SQL Server, padrão DAO e herança entre classes.

## 📦 Tecnologias Utilizadas

- Java (NetBeans)
- JDBC
- SQL Server
- Padrão DAO
- POO (Herança, Polimorfismo)
- Git

## 📁 Estrutura de Pacotes
cadastrobd/
├── model/
│ ├── Pessoa.java
│ ├── PessoaFisica.java
│ ├── PessoaJuridica.java
│ ├── PessoaFisicaDAO.java
│ └── PessoaJuridicaDAO.java
├── model/util/
│ ├── ConectorBD.java
│ └── SequenceManager.java
CadastroBDTeste.java


## 🧪 Funcionalidades

- Incluir pessoa física ou jurídica no banco
- Alterar dados existentes
- Excluir registros.
- Listar todos ou buscar por ID
- Menu de interação via console

## ⚙️ Requisitos

- JDK 11+
- NetBeans
- SQL Server
- Driver JDBC (`mssql-jdbc-12.2.0.jre11.jar`)

## 🔧 Configuração do Banco

1. Crie o banco de dados `loja`
2. Execute o script abaixo

```sql
CREATE TABLE Pessoa (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    logradouro VARCHAR(100),
    cidade VARCHAR(50),
    estado VARCHAR(2),
    telefone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE PessoaFisica (
    id INT PRIMARY KEY,
    cpf VARCHAR(14),
    FOREIGN KEY (id) REFERENCES Pessoa(id)
);

CREATE TABLE PessoaJuridica (
    id INT PRIMARY KEY,
    cnpj VARCHAR(18),
    FOREIGN KEY (id) REFERENCES Pessoa(id)
);

CREATE SEQUENCE seq_pessoa START WITH 1 INCREMENT BY 1;

🧠 Como Executar
Clone o repositório

Importe o projeto no NetBeans

Adicione o driver JDBC ao projeto (lib/mssql-jdbc-12.2.0.jre11.jar)

Configure seu banco de dados local com nome loja e usuário loja

Execute a classe CadastroBDTeste.java

📄 Documentação
Veja o arquivo relatorio.pdf com a descrição completa da prática.