# Apresentação final do MediAgenda

Duração planejada de 7 minutos e 30 segundos. Todos os integrantes devem estar presentes. A distribuição abaixo é uma sugestão para ensaio.

| Tempo | Responsável sugerido | Conteúdo |
| --- | --- | --- |
| 0:00–1:00 | Henrique | Problema e público |
| 1:00–2:00 | Lucas | Solução e evolução por entrega |
| 2:00–3:15 | Pedro | Fluxo de trabalho e ferramentas |
| 3:15–6:15 | Rubens | Demonstração do sistema |
| 6:15–7:30 | Equipe | Lições aprendidas e encerramento |

## Problema e público

Clínicas precisam manter os dados de pacientes e médicos, organizar os horários e consultar o histórico dos atendimentos. O MediAgenda reúne essas tarefas em uma aplicação web para o consultório.

## Solução e evolução

A entrega 1 definiu as sete histórias em BDD e as telas no Figma. A implementação foi organizada em cadastros e agendamento na etapa 2, confirmação e cancelamento na etapa 3 e prontuário e histórico na etapa 4.

## Fluxo de trabalho e ferramentas

Mostre as histórias, o protótipo, a Issue e os commits. Explique o percurso de uma ação do formulário ao controller, à regra de negócio e ao banco.

- [GitHub e código do MediAgenda](https://github.com/LNSereno/MediAgenda)
- [Protótipo Figma](https://www.figma.com/design/UEResJqM7OeZHOcTmodCFX/MediAgenda---Prot%C3%B3tipo-Lo-Fi)
- [Java](https://dev.java/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Thymeleaf](https://www.thymeleaf.org/)
- [H2](https://www.h2database.com/)
- [Maven](https://maven.apache.org/)

## Demonstração em três minutos

Prepare os cadastros e três prontuários antes de começar.

1. Mostre pacientes, médicos e agenda.
2. Agende uma consulta e demonstre a recusa do mesmo horário para o mesmo médico.
3. Confirme uma consulta. Cancele outra com motivo e mostre o horário liberado.
4. Conclua um atendimento em horário passado e registre o prontuário.
5. Abra o histórico do paciente e mostre a ordem dos atendimentos.

## Lições aprendidas

A equipe deve selecionar exemplos que realmente vivenciou. Pontos observáveis no repositório para discutir no ensaio são a validação dos cadastros, o tratamento de conflitos de agenda e a utilidade dos testes de regras e do fluxo web. Cada integrante deve dizer o que aprendeu e qual trecho consegue explicar.

## Antes de apresentar

Execute mvn test, rode mvn spring-boot:run e confira os dados de demonstração. Use dados fictícios. Ensaiem com cronômetro para manter o limite de oito minutos. Deixem um vídeo curto de demonstração preparado para o caso de falha do ambiente.
