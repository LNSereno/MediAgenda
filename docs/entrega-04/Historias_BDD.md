# Entrega 04

### História 6: Registro de Prontuário após Consulta
Como médico, quero registrar diagnóstico e prescrição após atender um paciente, para manter o histórico clínico atualizado.
- Cenário: Registro de prontuário de consulta realizada<br>
    Dado que a consulta do paciente "João Silva" com "Dra. Ana" foi marcada como "REALIZADA"<br>
    Quando o médico preenche diagnóstico, prescrição e observações<br>
    E clica em "Salvar prontuário"<br>
    Então um novo prontuário deve ser criado, vinculado à consulta e ao paciente

### História 7: Consulta ao Histórico do Paciente
Como médico, quero visualizar o histórico de prontuários de um paciente, para ter contexto sobre atendimentos anteriores.
- Cenário: Visualização do histórico clínico<br>
    Dado que o paciente "João Silva" possui 3 prontuários registrados<br>
    Quando o médico acessa o perfil do paciente<br>
    Então deve visualizar a lista dos 3 prontuários, ordenados do mais recente para o mais antigo<br>
