# 📚 Biblioteca Ágil

Projeto acadêmico para a disciplina de **Padrões de Projeto e Arquitetura de Software** do curso de Bacharelado em Sistema da Informação do Instituto Federal de Sergipe - Campus Lagarto.

---

## 📖 Sobre o Projeto

O **Biblioteca Ágil** é um sistema de API REST para gerenciar as operações de uma biblioteca.  
Ele permite que membros realizem empréstimos e devoluções de livros, enquanto a administração pode gerenciar o catálogo de livros, os membros e o histórico de transações.  

Este projeto foi construído como uma demonstração prática da aplicação da **Arquitetura Limpa** e dos **princípios SOLID** no desenvolvimento de software robusto, manutenível e testável.

---

## 🏛️ Arquitetura Limpa

A estrutura do projeto segue os princípios da Arquitetura Limpa, que organiza o software em camadas concêntricas com uma regra de dependência estrita: as camadas externas sempre dependem das camadas internas. Isso protege as regras de negócio de detalhes de implementação, como frameworks e bancos de dados.  

**Infraestrutura → Aplicação → Domínio**

### 🔹 Camada de Domínio (domain)
O coração do sistema. Não depende de nenhuma outra camada e contém a lógica de negócio essencial.

- **model**: Contém as entidades de negócio, como `Membro`, `Livro` e `Empréstimo`.  
- **repository**: Define _interfaces_ de repositórios (ex: `LivroRepository`), que funcionam como contratos para persistência de dados.

### 🔹 Camada de Aplicação (application)
Orquestra as entidades de domínio para executar os casos de uso.

- **usecase**: Classes que implementam os fluxos de negócio específicos, como `EmprestimoUseCase`.  
- **dto**: Objetos de transferência de dados entre API e aplicação.

### 🔹 Camada de Infraestrutura (infra)
Detalhes de implementação e tecnologias.

- **controller**: Implementa a API REST (Spring Controller).  
- **config**: Configurações do Spring, como o `DataSeeder` que popula o banco com dados de exemplo.

---

## ✨ Princípios SOLID

O design do código foi guiado pelos cinco princípios SOLID:

- **S - Single Responsibility Principle (SRP)**  
  Cada classe tem uma única responsabilidade.  

- **O - Open/Closed Principle (OCP)**  
  O sistema é aberto para extensão, mas fechado para modificação.  

- **L - Liskov Substitution Principle (LSP)**  
  Implementações de uma interface devem ser substituíveis sem quebrar a aplicação.  

- **I - Interface Segregation Principle (ISP)**  
  Interfaces específicas como `MembroRepository` e `LivroRepository` são utilizadas.  

- **D - Dependency Inversion Principle (DIP)**  
  Casos de uso dependem de abstrações, nunca de implementações concretas.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot**  
- **Spring Data JPA**  
- **Maven**  
- **H2 Database** (banco em memória)  
- **Lombok**

---

## 🚀 Como Rodar o Projeto

### 🔧 Pré-requisitos
- **JDK 17** ou superior  
- **Apache Maven**


### ▶️ Execução

````markdown

``bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd biblioteca-agil
mvn spring-boot:run
````

📍 A aplicação estará disponível em:
👉 [http://localhost:8080](http://localhost:8080)

---

### 🔎 Exemplo de execução no console

```console
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.biblioteca:agil >---------------------
[INFO] Building biblioteca-agil 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]-------------------------
...
2025-08-29 14:42:10  INFO 12345 --- [  main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
2025-08-29 14:42:10  INFO 12345 --- [  main] com.biblioteca.AgilApplication           : Started AgilApplication in 3.456 seconds
```

---

## 🧪 Usando a API

A aplicação inicia com alguns **dados de exemplo** para facilitar a demonstração.
Você pode testar os endpoints usando `curl`, [Postman](https://www.postman.com/) ou outro cliente HTTP.

---

### 📌 Exemplos de Requisições

#### 1. ✅ Realizar um empréstimo (sucesso)

```bash
curl -X POST http://localhost:8080/api/emprestimos \
     -H "Content-Type: application/json" \
     -d '{"membroId": 1, "livroId": 1}'
```

**Resposta esperada:**

```json
HTTP/1.1 200 OK
Content-Type: application/json

{
  "id": 1,
  "membroId": 1,
  "livroId": 1,
  "status": "EM_ANDAMENTO"
}
```

---

#### 2. ❌ Tentar empréstimo com membro pendente (falha)

```bash
curl -X POST http://localhost:8080/api/emprestimos \
     -H "Content-Type: application/json" \
     -d '{"membroId": 2, "livroId": 2}'
```

**Resposta esperada:**

```json
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "erro": "Membro com pendências não pode realizar empréstimos."
}
```

---

#### 3. ❌ Tentar empréstimo de livro indisponível (falha)

```bash
curl -X POST http://localhost:8080/api/emprestimos \
     -H "Content-Type: application/json" \
     -d '{"membroId": 3, "livroId": 4}'
```

**Resposta esperada:**

```json
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "erro": "Livro já emprestado e indisponível no momento."
}
```

---

## 🗄️ Acessando o Banco de Dados H2

Você pode visualizar o banco em memória através do console H2:

* **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* **JDBC URL:** `jdbc:h2:mem:bibliotecaDB`
* **User Name:** `sa`
* **Password:** `password`

---

👨‍💻 *Desenvolvido por **João Pedro Santana Silva Santos*** | Manjaro Linux 🐧


