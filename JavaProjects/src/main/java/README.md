#RevisaAi - Backend (Java Spring Boot)

#Configuração Inicial

1. Certifique-se de ter o **PostgreSQL** rodando.
2. Crie um banco de dados chamado `revisaai`.
3. As credenciais padrão são: `postgres` / `123456` (ajuste no `application.properties` se necessário).

#Autenticação

- **Login**: `POST /auth/login`
- **Registro**: `POST /auth/register`
- **Headers**: Para rotas protegidas, envie o header `Authorization: Bearer <SEU_TOKEN>`.

#Documentação Interativa (Swagger)

Com o projeto rodando, acesse para testar os endpoints:
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

#Configuração de CORS

A API está liberada para:
- React (Porta 3000)
- Vite (Porta 5173)
- Live Server (Porta 5500)