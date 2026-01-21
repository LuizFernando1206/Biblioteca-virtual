# Biblioteca Virtual

API REST de biblioteca virtual em **Java 21** com **Spring Boot** e **PostgreSQL**, incluindo autenticação **JWT**, documentação com **Swagger** criptografia de senha e relacionamento entre usuários e livros.

---

## Funcionalidades

- **Cadastro e autenticação de usuários**  
- **CRUD de livros**  
- **Relacionamento usuário ↔ livros**  

---

## Tecnologias

- **Java 21**  
- **Spring Boot**  
- **PostgreSQL**  
- **Spring Security (JWT)**  
- **Maven**  
- **JPA / Hibernate**
- **Swagger**
- **Git / GitHub**  

---

## Como rodar

git clone https://github.com/LuizFernando1206/Biblioteca-virtual.git
Entre na pasta do projeto:

bash
Copiar código
cd Biblioteca-virtual
Configure o banco PostgreSQL (application.properties):

URL

Usuário

Senha

Rode a aplicação:

Se usar Maven:

./mvnw spring-boot:run

Se usar Gradle:

./gradlew bootRun

Teste os endpoints com Postman ou outro cliente HTTP

## Estrutura do projeto
Controller → endpoints da API

Service → lógica de negócio

Model -> Modelo das entidades

Repository -> Camada que conecta ao banco de dados
