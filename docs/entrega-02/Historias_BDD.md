### História 1: Cadastro de Paciente
Como recepcionista da clínica, quero cadastrar um novo paciente no sistema, para que ele possa agendar consultas futuramente.
- Cenário: Cadastro de paciente com dados válidos<br>
  	Dado que estou na tela de cadastro de pacientes<br>
  	Quando eu preencho nome, CPF, data de nascimento, telefone e email<br>
  	E clico em "Salvar"<br>
  	Então o paciente deve ser criado com sucesso<br>
  	E deve aparecer na lista de pacientes cadastrados


### História 2: Cadastro de Médico
Como administrador do consultório, quero cadastrar um médico com sua especialidade, para que ele possa ser associado a consultas.
- Cenário: Cadastro de médico com especialidade<br>
    Dado que estou na tela de cadastro de médicos<br>
    Quando eu preencho nome, CRM, telefone, email e seleciono uma especialidade<br>
    E clico em "Salvar"<br>
    Então o médico deve ser criado com sucesso<br>
    E deve estar disponível para ser selecionado ao agendar consultas


### História 3: Agendamento de Consulta
Como paciente ou recepcionista, quero agendar uma consulta com um médico em uma data e horário específicos, para garantir o atendimento.
- Cenário 1: Agendamento de consulta em horário disponível<br>
    Dado que o médico "Dra. Ana" não possui consulta marcada às 14h do dia 10/09<br>
    Quando eu seleciono o paciente "João Silva"<br>
    E seleciono o médico "Dra. Ana" e o horário 14h do dia 10/09<br>
    E confirmo o agendamento<br>
    Então a consulta deve ser criada com status "AGENDADA"

- Cenário 2: Tentativa de agendamento em horário já ocupado<br>
    Dado que o médico "Dra. Ana" já possui uma consulta às 14h do dia 10/09<br>
    Quando eu tento agendar outra consulta para o mesmo médico no mesmo horário<br>
    Então o sistema deve exibir uma mensagem de conflito de horário<br>
    E a nova consulta não deve ser criada
