# FlashFlow API - Sistema de Gerenciamento de Fila Médica

API REST desenvolvida em Spring Boot para gerenciamento de filas de espera em sistemas médicos.

## Funcionalidades

### Sistema de Usuários
- **Usuario (classe base)**: Login, senha, nome
- **Medico**: CRM, especialidade, disponibilidade
- **Atendente**: Setor, horários de trabalho
- **TecnicoSaude**: Área de atuação, registro profissional

### Sistema de Pacientes
- Cadastro de pacientes com dados pessoais
- Sistema de prioridades (EMERGENCIA, URGENTE, NORMAL)
- Gerenciamento de status (AGUARDANDO, EM_ATENDIMENTO, ATENDIDO, CANCELADO)
- Fila ordenada por prioridade e ordem de chegada

## Como Executar

```bash
# Clonar o repositório
git clone https://github.com/tassiosantos/flashflow_api.git
cd flashflow_api

# Executar com Maven
mvn spring-boot:run

# Ou construir JAR e executar
mvn clean package
java -jar target/flashflow-api-1.0.0.jar
```

A aplicação será iniciada na porta 8080.

## API Endpoints

### Usuários
- `GET /api/usuarios` - Listar todos os usuários
- `GET /api/usuarios/{id}` - Buscar usuário por ID
- `GET /api/usuarios/login/{login}` - Buscar usuário por login
- `POST /api/usuarios` - Criar novo usuário
- `PUT /api/usuarios/{id}` - Atualizar usuário
- `DELETE /api/usuarios/{id}` - Deletar usuário
- `POST /api/usuarios/autenticar` - Autenticar usuário

### Médicos
- `GET /api/medicos` - Listar todos os médicos
- `GET /api/medicos/{id}` - Buscar médico por ID
- `GET /api/medicos/crm/{crm}` - Buscar médico por CRM
- `GET /api/medicos/disponiveis` - Listar médicos disponíveis
- `GET /api/medicos/especialidade/{especialidade}` - Buscar por especialidade
- `POST /api/medicos` - Criar novo médico
- `PUT /api/medicos/{id}` - Atualizar médico
- `PUT /api/medicos/{id}/disponibilidade?disponivel=true` - Alterar disponibilidade
- `DELETE /api/medicos/{id}` - Deletar médico

### Pacientes e Fila
- `GET /api/pacientes` - Listar todos os pacientes
- `GET /api/pacientes/{id}` - Buscar paciente por ID
- `GET /api/pacientes/cpf/{cpf}` - Buscar paciente por CPF
- `POST /api/pacientes` - Adicionar paciente na fila
- `GET /api/pacientes/fila` - Obter fila ordenada por prioridade
- `POST /api/pacientes/chamar-proximo` - Chamar próximo da fila
- `PUT /api/pacientes/{id}/finalizar-atendimento` - Finalizar atendimento
- `GET /api/pacientes/fila/contagem` - Contar pacientes na fila
- `GET /api/pacientes/status/{status}` - Listar por status
- `PUT /api/pacientes/{id}` - Atualizar paciente
- `DELETE /api/pacientes/{id}` - Deletar paciente

## Exemplos de Uso

### Autenticar usuário
```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"login":"dr.silva","senha":"123456"}' \
  http://localhost:8080/api/usuarios/autenticar
```

### Adicionar paciente na fila
```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","cpf":"12345678901","prioridade":"URGENTE","observacoes":"Dor no peito"}' \
  http://localhost:8080/api/pacientes
```

### Chamar próximo da fila
```bash
curl -X POST http://localhost:8080/api/pacientes/chamar-proximo
```

### Ver fila ordenada
```bash
curl http://localhost:8080/api/pacientes/fila
```

## Tecnologias Utilizadas

- **Spring Boot 3.1.5** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Segurança (configuração básica)
- **H2 Database** - Banco de dados em memória para desenvolvimento
- **Maven** - Gerenciamento de dependências
- **Java 17** - Linguagem de programação

## Banco de Dados

O projeto utiliza H2 Database em memória para desenvolvimento. O console H2 está disponível em:
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:flashflow`
- Username: `sa`
- Password: (vazio)

## Dados de Exemplo

A aplicação cria automaticamente dados de exemplo:

**Usuários:**
- Dr. João Silva (login: dr.silva, senha: 123456) - Cardiologista
- Dra. Maria Santos (login: dra.santos, senha: 123456) - Pediatra
- Ana Costa (login: atendente1, senha: 123456) - Atendente
- Carlos Oliveira (login: tecnico1, senha: 123456) - Técnico de Saúde

**Pacientes:**
- José da Silva (CPF: 12345678901) - Prioridade NORMAL
- Maria Fernanda (CPF: 98765432100) - Prioridade URGENTE

## Estrutura do Projeto

```
src/main/java/com/flashflow/api/
├── FlashFlowApiApplication.java          # Classe principal
├── config/
│   ├── SecurityConfig.java              # Configuração de segurança
│   └── DataInitializer.java             # Dados iniciais
├── entity/
│   ├── Usuario.java                     # Entidade base de usuário
│   ├── Medico.java                      # Entidade médico
│   ├── Atendente.java                   # Entidade atendente
│   ├── TecnicoSaude.java               # Entidade técnico de saúde
│   └── Paciente.java                    # Entidade paciente
├── repository/
│   ├── UsuarioRepository.java           # Repositório de usuários
│   ├── MedicoRepository.java            # Repositório de médicos
│   └── PacienteRepository.java          # Repositório de pacientes
├── service/
│   ├── UsuarioService.java              # Serviços de usuário
│   └── PacienteService.java             # Serviços de paciente
└── controller/
    ├── UsuarioController.java           # Controlador de usuários
    ├── MedicoController.java            # Controlador de médicos
    └── PacienteController.java          # Controlador de pacientes
```

## Testes

Execute os testes com:
```bash
mvn test
```

## Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request