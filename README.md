# kotlin-spring-boot-vue-jwt-postgres-start

## Technologies

1. Backend: Kotlin, Spring boot, JWT, PostgreSQL
2. Frontend: Vue, JWT, Axios

## Tasks

    [X] Acesso via token JWT
    [X] Cadastro de produtos
        [X] Identificar o tipo de corte (fixo, variável)
    [ ] Cadastro de Grupos e Subgrupos de Produtos
    [ ] Cadastro de Clientes
    [ ] Cadastro de Projetos
        [ ] Dados do Cliente
        [ ] Dados de Ambientes
            [ ] Lista de Produtos - Compensados
        [ ] Lista de Produtos - Expediente
        [ ] Cálculo e visão do resultado 
            (Visualização de cortes na folha de compensado/fórmica)
            [ ] Uso de sobras cadastradas (opcional no cálculo)
            [ ] API de cálculo
        [ ] Identificação/nomeação e registro de sobras
    [ ] Cadastro de Usuários
    [ ] Documentação Swagger

## How to start

1. If on linux, run the script start services

        chmod +x runAll.sh &&  ./runAll.sh

2. Open the browser and the managers:    

    * Frontend service
        * http://localhost:8081
        * user: eder.nilson@gmail.com
        * password: 12345678
    * PgAdmin 4
        * http://localhost:5001
        * user: guest@guest.com
        * password: 12345678
    * Swagger API Docs
        * http://localhost:8081/api/swagger-ui.html

3. How to get the token to connect to Swagger

        curl -X POST 'localhost:8081/api/login' --header 'Content-Type: application/json' --data '{  "username": "eder.nilson@gmail.com", "password": "12345678"}'
    

## Credits

- Tips of docker-compose.yml and start script: [Sherzod Mamadaliev](https://github.com/mamadaliev)

## License

MIT    
