---

## ✅ `loja.sql` — Script SQL

```sql
CREATE DATABASE loja;
GO
USE loja;
GO

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