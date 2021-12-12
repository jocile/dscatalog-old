# DS Catalog DevSuperior

Desenvolvimento de aplicativo de catálogo usando [Spring Boot](https://glysns.gitbook.io/springframework/) com a linguagem [Java 11](https://docs.oracle.com/en/java/javase/11/), banco de dados em memória H2 e [ReactJS](https://pt-br.reactjs.org/) estudando no Bootcamp Spring React do curso [DevSuperior](https://devsuperior.com.br/)

## Funcionalidades desenvolvidas nesse app:

- Listagem de produtos
- Busca de produto por id
- Recuperação de senha
- Área administrativa com CRUD de:
  - Busca paginada de recursos
  - Busca de recurso por id
  - Inserir novo recurso
  - Atualizar recurso
  - Deletar recurso

## Atividades desenvolvidas nesse app:

- Criar projeto Spring Boot
- Criar monorepo Git
- Organizar o projeto em camadas
  - Controlador REST
  - Serviço
  - Acesso a dados (Repository)
- Criar entidades
- Configurar perfil de teste do projeto
- Seeding da base de dados
- Criar web services REST
  - Parâmetros de rota @PathVariable
  - Parâmetros de requisição @RequestParam
  - Corpo de requisição @RequestBody
  - Resposta da requisição ResponseEntity<T>
- Padrão DTO
- CRUD completo
- Tratamento de exceções
- Postman (coleções, ambientes)
- Dados de auditoria
- Paginação de dados
- Associações entre entidades (N-N)

## Material de apoio

- <a href="http://www.youtube.com/watch?feature=player_embedded&v=b8uLFfzcVQ8
  " target="_blank"><img src="http://img.youtube.com/vi/b8uLFfzcVQ8/0.jpg" 
  alt="Conceitos sobre desenvolvimento web e REST" width="240" height="180" border="10" /></a>
- <a href="http://www.youtube.com/watch?feature=player_embedded&v=CAP1IPgeJkw" target="_blank"><img src="http://img.youtube.com/vi/CAP1IPgeJkw/0.jpg" 
  alt="Introdução a JPA e Hibernate" width="240" height="180" border="10" /></a>
- <a href="http://www.youtube.com/watch?feature=player_embedded&v=PfYifUFmXk8" target="_blank"><img src="http://img.youtube.com/vi/PfYifUFmXk8/0.jpg" 
  alt="Análise do app da Semana DevSuperior 1.0" width="240" height="180" border="10" /></a>
- [Github do DSCatalog](https://github.com/devsuperior/dscatalog-resources)
- [Figma do DSCatalog](https://www.figma.com/file/1n0aifcfatWv9ozp16XCrq/DSCatalog-Bootcamp)

## Modelo conceitual

![Modelo conceitual](backend/src/main/resources/static/DSCatalog%20Modelo%20conceitual.png)

## Testes manuais no postman

Busca paginada de clientes

```
GET /clients?page=0&linesPerPage=6&direction=ASC&orderBy=name
```

Busca de cliente por id

```
GET /clients/1
```

Inserção de novo cliente

```
POST /clients
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20T10:30:00Z",
  "children": 2
}
```

Atualização de cliente

```
PUT /clients/1
{
  "name": "Maria Silvaaa",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20T10:30:00Z",
  "children": 2
}
```

Deleção de cliente

```
DELETE /clients/1
```
