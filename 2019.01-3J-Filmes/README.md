# Banco de Dados

## Criando a tabela usando MySQL
```
CREATE TABLE ps2.Filmes (
  id INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  genero VARCHAR(255) NOT NULL,
  minutos INT NOT NULL,
  PRIMARY KEY (id)
);
```

## Pastas e Arquivos
* **lib** - Contém as bibliotecas usadas no projeto
* **src** - Código fonte do projeto
* **src/br.mackenzie.ps2.filmes.Filme.java** - Entidade que representa um Banco e é utilizada pelo DAO para mapeamento no Banco de Dados
* **src/br.mackenzie.ps2.filmes.FilmeDAO.java** - Gerencia conexão com o banco de dados e faz o CRUD de Banco e, para fim de teste, existe um metodo main