# Backend - Hackathon de Gestão de Consultório

Este repositório contém o backend do protótipo de gestão de consultório, desenvolvido em **Java Spring Boot**. Ele é responsável por fornecer APIs REST para gerenciar pacientes, exames, médicos e atendentes, permitindo integração com o front-end em React.

---

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security (JWT)**
- **H2 Database** (para desenvolvimento e testes)
- **Maven** (gerenciamento de dependências)

---

## 🏗 Estrutura do Projeto

```bash
src/
└── main/
├── java/
│ └── com.consultorio/
│ ├── controller/ # Classes que expõem as APIs REST
│ ├── service/ # Regras de negócio
│ ├── repository/ # Acesso a dados (JPA Repositories)
│ ├── model/ # Entidades do banco
│ └── security/ # Configuração de autenticação JWT
└── resources/
├── application.properties # Configurações do Spring Boot
└── data.sql # Dados iniciais (opcional)
```

## ⚙ Funcionalidades Implementadas

### Paciente
- Criar, atualizar, deletar e consultar pacientes
- Consultar fila de exames por paciente
- Consultar histórico de exames

### Médico
- Consultar lista de pacientes em atendimento
- Atualizar status de exames (Em Atendimento / Finalizado)

### Atendente
- Registrar pacientes e exames
- Consultar e organizar fila de espera
- Atualizar status do paciente na fila

### Autenticação
- Login via **JWT**
- Proteção de endpoints por roles (`MEDICO`, `ATENDENTE`, `PACIENTE`)

---

## 📦 Endpoints Principais

| Método | Endpoint                     | Descrição                                |
|--------|-------------------------------|------------------------------------------|
| POST   | `/auth/login`                 | Autenticação de usuário e retorno do JWT |
| GET    | `/pacientes`                  | Listar todos os pacientes                |
| GET    | `/pacientes/{id}`             | Buscar paciente por ID                   |
| POST   | `/pacientes`                  | Criar novo paciente                      |
| PUT    | `/pacientes/{id}`             | Atualizar paciente                       |
| DELETE | `/pacientes/{id}`             | Deletar paciente                         |
| GET    | `/exames`                     | Listar todos os exames                    |
| POST   | `/exames`                     | Criar novo exame                          |
| PUT    | `/exames/{id}/status`         | Atualizar status de um exame             |
| GET    | `/fila`                        | Consultar fila de pacientes/exames       |

> 💡 Obs: O JWT deve ser enviado no header `Authorization: Bearer <token>`.

---

## 🛠 Configuração do Projeto

```bash
1. **Clonar o repositório**
git clone <URL_DO_REPOSITORIO>
cd backend

Compilar e rodar
./mvnw clean install
./mvnw spring-boot:run
Banco de dados H2

Acesse http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: (em branco)

Variáveis de ambiente

JWT_SECRET → Segredo para assinatura do token JWT

SERVER_PORT → Porta da aplicação (padrão 8080)
```

## 🧪 Testes
O projeto inclui testes unitários e de integração:
```bash
./mvnw test 
```

## 📌 Observações
Este backend é apenas um protótipo para hackathon, não recomendado para produção.  <br>
A persistência dos dados é temporária (H2 em memória). Para produção, recomenda-se configurar PostgreSQL ou MySQL.  <br>
Todos os endpoints estão documentados e podem ser testados via Postman ou Swagger.  <br>

## 👥 Equipe

Thiago Coutinho Sousa Silva - Dev/QA (Frontend)<br>
Kevenn Viana Santos - Dev (Backend)<br>
Lincon de Jesus Brito <br>
Tássio Nascimento Santos <br>
Raphael dos Santos Cerqueira <br>

## 🚀 Futuras Melhorias

melhoria 1  <br>
melhoria 2  <br>

## 📄 Licença
Este projeto está licenciado sob a MIT License.
