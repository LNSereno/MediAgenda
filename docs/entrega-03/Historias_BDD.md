# Entrega 03

### História 4: Cancelamento de Consulta
Como paciente, quero cancelar uma consulta agendada, para liberar o horário caso eu não possa comparecer.
- Cenário: Cancelamento de consulta futura<br>
    Dado que existe uma consulta "AGENDADA" para o paciente "João Silva"<br>
    Quando eu seleciono a consulta e clico em "Cancelar"<br>
    E informo o motivo do cancelamento<br>
    Então o status da consulta deve mudar para "CANCELADA"<br>
    E o horário deve voltar a ficar disponível para o médico

### História 5: Confirmação de Consulta
Como paciente, quero confirmar minha presença em uma consulta agendada, para que a clínica saiba que devo comparecer.
- Cenário: Confirmação de presença<br>
    Dado que existe uma consulta "AGENDADA" para o paciente "João Silva"<br>
    Quando eu acesso a consulta e clico em "Confirmar presença"<br>
    Então o status da consulta deve mudar para "CONFIRMADA"
