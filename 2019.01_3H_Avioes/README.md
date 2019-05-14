# Banco de Dados

## Criando a tabela usando MySQL
```
CREATE TABLE `ps2`.`avioes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(255) NOT NULL,
  `preco` DECIMAL NOT NULL,
  PRIMARY KEY (`id`)
);
```

## Pastas e Arquivos
* **lib** - Contém as bibliotecas usadas no projeto
* **src** - Código fonte do projeto
* **src/br.mackenzie.ps2.Aviao.java** - Entidade que representa um Avião e é utilizada pelo DAO para mapeamento no Banco de Dados
* **src/br.mackenzie.ps2.AvioesDAO.java** - Gerencia conexão com o banco de dados e faz o CRUD de Aviao