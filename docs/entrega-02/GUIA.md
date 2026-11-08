# Entrega 02 — Cadastros e agendamento

Complementação organizada em 08/11/2026. O prazo original desta etapa já passou. Os artefatos abaixo descrevem a versão atual e os fluxos correspondentes à etapa.

## Histórias implementadas (POST-IT)

### História 1 — Cadastro de paciente

> Como recepcionista da clínica, quero cadastrar um novo paciente, para que ele possa agendar consultas futuramente.

### História 2 — Cadastro de médico

> Como administrador do consultório, quero cadastrar um médico com sua especialidade, para que ele possa ser associado a consultas.

### História 3 — Agendamento de consulta

> Como paciente ou recepcionista, quero agendar uma consulta com um médico em uma data e horário específicos, para garantir o atendimento.

## Referências

- [Histórias BDD originais](../../Histórias_BDD.md)
- [Protótipo da entrega 1](https://www.figma.com/design/UEResJqM7OeZHOcTmodCFX/MediAgenda---Prot%C3%B3tipo-Lo-Fi)
- [Cenários adicionais e critérios de aceite](../cenarios-validacao.md)
- [Como executar](../../README.md#como-executar)
- [Issue desta etapa](https://github.com/LNSereno/MediAgenda/issues/3)

## Vídeo de uso

Gravar a aplicação Spring Boot em execução, com áudio ou legendas. Siga estes passos e explique o resultado de cada ação.

1. Abra a aplicação em http://localhost:8080.
2. Em **Cadastrar paciente**, preencha João Silva, CPF 00000000000, nascimento 12/05/2000, telefone 8100000000 e joao@example.com. Salve e mostre a linha na tabela.
3. Em **Cadastrar médico**, preencha Dra. Ana, CRM TESTE-PE, telefone 8100000001, ana@example.com e Cardiologia. Salve.
4. Em **Agendar consulta**, escolha João e Ana e um horário. Salve e mostre AGENDADA.
5. Tente agendar de novo para a mesma médica no mesmo horário. Mostre a mensagem e confira que existe uma única consulta.
6. Recarregue a página para mostrar os dados persistidos. Pare o servidor com Ctrl+C, rode novamente e mostre que os cadastros continuam no banco local.

## Vídeo de código

Grave o editor e explique estes trechos com áudio ou legendas.

1. Abra pom.xml e MediAgendaApplication.java. Explique as dependências Spring Web, Thymeleaf, JPA, Validation e H2 e a inicialização.
2. Abra Patient.java e Doctor.java. Mostre atributos privados, getters e setters explícitos, @Entity e identificadores gerados.
3. Em WebController.java, mostre os GETs dos formulários e os POSTs de cadastro. Explique validação e mensagens de CPF/CRM repetidos.
4. Abra os repositórios e explique como JpaRepository grava e consulta as entidades.
5. Abra Appointment.java e AppointmentService.schedule. Mostre os vínculos com paciente e médico e a recusa de conflito.
6. Abra patient-form.html, doctor-form.html e appointment-form.html. Mostre como th:field e as opções de seleção chegam ao controller.
7. Mostre application.properties e explique que o H2 em arquivo mantém os dados entre execuções.

## Evidências antes da publicação

- Capture a tela da Issue desta etapa no GitHub, com título e situação visíveis.
- Salve a imagem em docs/entrega-02/issues.png e adicione a imagem na seção desta entrega no README.
- Publique os dois vídeos no YouTube e substitua os marcadores “A gravar” no README por links reais.
- Confira os links em uma janela sem login antes da entrega.
