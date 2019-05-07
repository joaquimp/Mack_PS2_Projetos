# Banco de Dados

## Criando a tabela usando MySQL
```
CREATE TABLE `ps2`.`bancos` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL,
    `cnpj` VARCHAR(45) NOT NULL,
    `numero_clientes` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);
```

## Pastas e Arquivos
* **lib** - Contém as bibliotecas usadas no projeto
* **src** - Código fonte do projeto
* **src/br.mackenzie.ps2.Banco.java** - Entidade que representa um Banco e é utilizada pelo DAO para mapeamento no Banco de Dados
* **src/br.mackenzie.ps2.BancoDAO.java** - Gerencia conexão com o banco de dados e faz o CRUD de Banco
* **src/br.mackenzie.ps2.Main.java** - Arquivo para testar a aplicação