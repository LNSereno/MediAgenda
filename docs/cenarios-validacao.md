# Cenários de validação por entrega

As sete histórias e os cenários originais estão em Histórias_BDD.md. Os cenários abaixo completam a verificação da implementação atual.

## Entrega 02

### História 1 — cadastro
Dado que a recepcionista preenche nome, CPF, nascimento e contatos válidos
Quando salva o paciente
Então ele aparece na lista e continua disponível após recarregar a página.

Dado que já existe um CPF cadastrado
Quando a recepcionista tenta cadastrar esse CPF novamente
Então o formulário informa a duplicação e mantém apenas o cadastro original.

### História 2 — médico
Dado que o administrador preenche nome, CRM, telefone, e-mail e especialidade
Quando salva o médico
Então ele aparece na lista e na seleção de agendamento.

### História 3 — agendamento
Dado que o médico tem horário disponível
Quando a recepcionista agenda um paciente nesse horário
Então a consulta é persistida com status AGENDADA.

Dado que o médico já tem consulta ativa naquele horário
Quando há uma segunda tentativa para o mesmo médico e horário
Então o sistema informa o conflito e mantém apenas a consulta original.

## Entrega 03

### História 4 — cancelamento
Dado que existe consulta em aberto
Quando o paciente informa um motivo e cancela
Então o status fica CANCELADA e o motivo é persistido.

Dado que uma consulta foi cancelada
Quando a recepcionista agenda o mesmo médico no mesmo horário
Então a nova consulta é aceita.

Dado que existe consulta em aberto
Quando é enviado um cancelamento com motivo vazio
Então a alteração é recusada e o status anterior é mantido.

### História 5 — confirmação
Dado que existe uma consulta AGENDADA
Quando o paciente confirma presença
Então o status persistido passa a CONFIRMADA.

Dado que a consulta está CANCELADA
Quando há tentativa de confirmar presença
Então o sistema recusa a alteração.

## Entrega 04

### História 6 — prontuário
Dado que o atendimento foi concluído
Quando o médico salva diagnóstico, prescrição e observações
Então o prontuário fica vinculado à consulta e ao paciente.

Dado que a consulta ainda está AGENDADA ou CONFIRMADA
Quando há tentativa de salvar prontuário
Então o sistema recusa a gravação.

Dado que a consulta já possui um prontuário
Quando há nova tentativa de gravação
Então o registro original é mantido e nenhum segundo prontuário é criado.

### História 7 — histórico
Dado que o paciente tem três prontuários e outro paciente possui um
Quando o médico acessa o histórico do primeiro
Então vê apenas os três registros dele, do atendimento mais recente ao mais antigo.

Dado que o paciente ainda não tem prontuários
Quando seu histórico é aberto
Então o sistema informa que não há prontuários.
