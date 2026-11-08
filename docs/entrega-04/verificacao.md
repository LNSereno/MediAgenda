# Verificação da entrega 04

Verificação realizada em 08/11/2026.

- Oito testes automatizados passaram com Maven e Spring Boot.
- O fluxo HTTP foi executado no servidor Spring Boot com banco H2 de demonstração.
- Foram cadastrados paciente e médico fictícios e concluídos três atendimentos.
- Os prontuários foram salvos e consultados na ordem 15/08, 02/06 e 10/03/2026.
- Nova tentativa de gravar o mesmo prontuário manteve apenas o registro original.
- Cadastro com CPF repetido exibiu uma mensagem no formulário.
- Os testes automatizados também conferem isolamento entre pacientes e recusa de prontuário antes da conclusão.

Ambiente de verificação: JDK 26, código compilado com compatibilidade Java 17, Spring Boot 3.4.2 e Maven 3.9.9. A execução dos testes nesse JDK utilizou o agente Byte Buddy já presente no ambiente. Para a equipe, o requisito documentado continua sendo Java 17.

A gravação dos screencasts e sua publicação no YouTube ainda precisam ser realizadas.

Na revisão por etapa também foram verificadas a confirmação persistida, a recusa de cancelamento vazio e a reutilização do horário cancelado.
