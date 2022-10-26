# challenge-customer

Projeto exemplo de utilização de Clean Architecture com Kotlin

Nesse projeto teremos uma API de clientes, utilizando a Clean Architecture para implementação da mesma.

### Pré requisitos

* docker-compose
  ```bash
  https://docs.docker.com/compose/install/#install-compose
  ```
* Docker
  ```bash
  https://docs.docker.com/engine/install/ 
  ```
* Java 17
  ```bash
  https://github.com/shyiko/jabba
  ```

### Instação do projeto

Clonar o repositório:

```bash 
git clone https://github.com/julianoalmeida/challenge-customer.git
```

Na raiz do projeto executar os comandos abaixo:

```bash
docker-compose up --build -d

./gradlew flywayMigrate

./gradlew :challenge-api:bootRun

./gradlew :challenge-consumer:bootRun
```

Testando a aplicação:


```bash
"endpoints": {
    "GET": localhost:8080/customer/{id}, // Buscar cliente
    "POST": {
      "URL": localhost:8080/customer, // Criação de um novo cliente
      "BODY":{
        "name": "zeca pagodinho",
        "email": "zequinha@gmail.com"
      }
    },
    "PUT": {
      "URL": localhost:8080/customer/{id}, // Alterar dados do cliente
      "BODY":{
        "name": "zeca pagodinho",
        "email": "zequinha@gmail.com"
      }
    }
}

Exemplo de request utilizando o curl

curl -X POST \
  http://localhost:8080/customer \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
    "name": "Fabiano zica do baile",
    "email": "fabianoaaa1s2ssilvaa@gmail.com"
  }'
```
