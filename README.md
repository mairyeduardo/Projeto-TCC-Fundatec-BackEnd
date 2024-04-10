# Projeto-TCC-Fundatec-BackEnd

Back End do projeto "Solo Craft" desenvolvido para o TCCII;

## Para que serve? 

Está aplicação é um gerenciador de serviços, para trabalhadores autonomos que realizam serviços fora de casa.
Facilitando a organização de seus serviços e clientes, lucros e despesas.

---

## Funcionalidades: 

- Cadastro de conta
- Login
- Cadastro de serviços
- Consulta dos serviços cadastrados
- Deletar serviços
- Finalizar serviços
- Adicinar custos no serviço
- Cadastro de clientes
- Consulta de clientes
- Gerar relatórios
- Consultar relatórios
- Excluir relatórios

---

## Como Utilizar? 

Obs: No projeto estou utilizando o `Java 17` e `SpringBoot 3.2.0`

Existem duas formas de utilizar este projeto, Rodando em LocalHost ou consumindo os endpoints que estão alocados em um Servidor, veja abaixo como proceguir em cada etapa:

### Consumindo Api alocada em Servidor AWS ( Prod ):

#### Link do swagger produção: http://ec2-3-215-135-209.compute-1.amazonaws.com:8080/solocraft/swagger-ui/index.html#/

#### Necessario:

Basta consumir os endpoints da Api, como fazer: 

URL Base da Api: http://ec2-3-215-135-209.compute-1.amazonaws.com:8080/solocraft/

--- 

### Rodando o projeto em LocalHost:

#### Link do Swagger para localhost: http://localhost:8080/solocraft/swagger-ui/index.html

#### Necessario:

- Possuir `MySQL Community Server` na máquina, aconselhado baixar tambem `MySQL Workbench` para visualizar conexão com Banco.


- Aconselhado utilizar um compilador de código, pode ser utilizado o `IntelliJ IDEA` ou `Eclipse IDE` para facilitar o uso da aplicação, se preferir é possivel gerar um arquivo `.jar` para subir a aplicação via terminal.

#### Observação:
É Necessario criar um banco de dados com nome `solocraft` para poder rodar o projeto pela primeira vez.

#### Configurações:

Altere as configurações do arquivo `application.yaml` para funcionar em local host, exemplo:

``` 
server:
  servlet:
    context-path: /solocraft
  port: 8081
  error:
    include-message: always

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/solocraft
    username: root
    password: '@fundatec'
    driver-class-name: com.mysql.cj.jdbc.Driver
    test-while-idle: true
    validation-query: SELECT 1
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
      naming-strategy: org.hibernate.cfg.ImprovedNamingStrategy
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
```

#### Instalar dependencias Maven: 

Faça a Build do projeto utilizando o Maven, todas as dependencias do projeto devem estar mantidas no arquivo `pom.xml`.

Dependenciais atuais do projeto: 

- Spring Boot
- MySql
- Lombok

#### Rodando Via IDE: 

Para rodar o projeto Execute o arquivo `Application`, desta forma o servidor Local do Spring Boot ira subir.

---
