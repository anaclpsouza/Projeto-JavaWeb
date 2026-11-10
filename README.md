# 🐾 Miauguinho - Sistema de Gestão Veterinária

Aplicação web desenvolvida em **Java Web** (Servlets e JSP) para gerenciamento de clínica veterinária, permitindo o controle de veterinários, tutores/donos e animais atendidos.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java (Java SE / JDK 8+)
- **Servidor Web**: Apache Tomcat 9.0
- **Tecnologias Java EE**:
  - Servlets (`javax.servlet`)
  - JSP (*JavaServer Pages*)
  - JSTL 1.2 (*JavaServer Pages Standard Tag Library*)
- **Banco de Dados**: MySQL 8.x
- **Conector de Banco**: `mysql-connector-j-8.0.33.jar`
- **Front-end**: HTML5, CSS3, Bootstrap 5 e JavaScript

---

## 🚀 Funcionalidades

- **Autenticação**:
  - Login e controle de sessão para veterinários.
  - Encerramento de sessão (*Logout*).
- **Gestão de Veterinários**:
  - Cadastro, alteração, listagem e remoção de veterinários.
- **Gestão de Tutores (Donos)**:
  - Cadastro de clientes com CPF, telefone e endereço.
  - Edição, consulta individual e listagem geral.
- **Gestão de Animais (Pets)**:
  - Cadastro de pets associados ao dono e ao veterinário responsável.
  - Edição de dados do pet (peso, idade, raça).
  - Remoção e consulta detalhada de atendimentos.

---

## 📁 Estrutura de Pastas

```text
Projeto-JavaWeb/
├── .classpath                   # Configuração de classpath do Eclipse
├── .gitignore                   # Arquivos e pastas ignoradas pelo Git
├── .project                     # Metadados do projeto Eclipse
├── miauguinhoBD.sql             # Script SQL de criação do banco e tabelas
└── src/
    └── main/
        ├── java/
        │   ├── classes/         # Modelos de dados e conexão JDBC (Animal, Dono, Veterinario, BD)
        │   └── servlets/        # Controladores HTTP (LoginServlet, PetServlet, DonoServlet, etc.)
        └── webapp/              # Camada de apresentação e recursos web
            ├── assets/
            │   ├── css/         # Folhas de estilo (style.css e Bootstrap)
            │   ├── js/          # Scripts e interações
            │   └── img/         # Logotipos e ícones
            ├── WEB-INF/
            │   └── lib/         # Dependências JAR (JSTL e MySQL Connector)
            └── *.jsp            # Telas do sistema (Login, Index, Forms de cadastro e consultas)
```

---

## ⚙️ Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:
1. **JDK (Java Development Kit)** versão 8, 11 ou superior.
2. **Servidor Apache Tomcat** versão 9.0.
3. **MySQL Server** (ou MySQL Workbench / XAMPP).
4. **IDE** recomendada: Eclipse IDE for Enterprise Java and Web Developers (ou IntelliJ IDEA Ultimate / VS Code com extensões Java).

---

## 🗄️ Configuração do Banco de Dados

1. Abra o seu cliente MySQL (ex: MySQL Workbench).
2. Execute o script contido no arquivo [`miauguinhoBD.sql`](miauguinhoBD.sql) para criar o schema `miauguinho` e as tabelas (`veterinario`, `dono`, `animal`).
3. Verifique as credenciais no arquivo `src/main/java/classes/BD.java`:
   ```java
   static String url = "jdbc:mysql://localhost:3306/miauguinho";
   static String user = "root";       // seu usuário do MySQL
   static String password = "sua_senha"; // sua senha do MySQL
   ```

---

## 💻 Como Executar o Projeto

### No Eclipse:
1. Abra o Eclipse e vá em **File > Import... > Existing Projects into Workspace**.
2. Selecione a pasta raiz `Projeto-JavaWeb`.
3. Configure o servidor **Apache Tomcat v9.0** na aba **Servers**.
4. Clique com o botão direito no projeto > **Run As > Run on Server**.
5. O sistema abrirá na tela inicial/login em:
   ```
   http://localhost:8080/Trab1Lp2/Login.jsp
   ```

---

## 📝 Licença

Projeto desenvolvido para fins acadêmicos na disciplina de Linguagem de Programação II (LP2).

