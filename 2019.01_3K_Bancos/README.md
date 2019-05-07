#Banco de Dados

##Criando a tabela usando MySQL
```
CREATE TABLE `ps2`.`bancos` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL,
    `cnpj` VARCHAR(45) NOT NULL,
    `numero_clientes` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);
```
