# Entrega 04 — Prontuário e histórico

Esta etapa conclui as histórias restantes do produto.

## Histórias implementadas (POST-IT)

### História 6 — Registro de prontuário após consulta

> Como médico, quero registrar diagnóstico e prescrição após atender um paciente, para manter o histórico clínico atualizado.

### História 7 — Consulta ao histórico do paciente

> Como médico, quero visualizar o histórico de prontuários de um paciente, para ter contexto sobre atendimentos anteriores.

## Referências

- [Histórias BDD originais](../../Histórias_BDD.md)
- [Protótipo da entrega 1](https://www.figma.com/design/UEResJqM7OeZHOcTmodCFX/MediAgenda---Prot%C3%B3tipo-Lo-Fi)
- [Cenários adicionais e critérios de aceite](../cenarios-validacao.md)
- [Como executar](../../README.md#como-executar)
- [Issue desta etapa](https://github.com/LNSereno/MediAgenda/issues/2)

## Vídeo de uso

Gravar a aplicação Spring Boot em execução, com áudio ou legendas. Siga estes passos e explique o resultado de cada ação.

1. Use apenas dados fictícios. Para demonstrar a conclusão, agende três atendimentos em datas e horários anteriores ao momento da gravação.
2. Abra uma consulta em **Ver consulta**, clique **Concluir atendimento** e mostre REALIZADA.
3. Abra **Abrir prontuário**. Mostre os campos e o histórico na mesma tela, como no protótipo.
4. Preencha diagnóstico “Consulta inicial”, prescrição “Orientações de acompanhamento” e observações “Dados fictícios para demonstração acadêmica”. Salve.
5. Repita com outros dois atendimentos, usando diagnósticos “Acompanhamento” e “Consulta de rotina”.
6. Na tabela de pacientes, abra o nome. Mostre os três prontuários do mais recente ao mais antigo e abra os detalhes para ler prescrição e observações.
7. Abra outro paciente sem prontuário e mostre a mensagem de histórico vazio.
8. Abra novamente uma consulta já registrada. Mostre que o sistema direciona ao histórico.

## Vídeo de código

Grave o editor e explique estes trechos com áudio ou legendas.

1. Abra MedicalRecord.java. Explique o vínculo @OneToOne com Appointment, o identificador e os campos clínicos.
2. Mostre o construtor validando REALIZADA, diagnóstico e prescrição. Observações são opcionais.
3. Em Appointment.complete, mostre a transição para REALIZADA e a verificação do horário.
4. Em MedicalRecordService.register, explique @Transactional, o bloqueio da consulta e a recusa de segundo registro.
5. Abra MedicalRecordRepository. Leia a consulta por paciente e a ordenação decrescente pela data do atendimento.
6. Em MedicalRecordController, percorra as rotas de formulário, gravação e histórico.
7. Abra record-form.html e clinical-fragments.html. Mostre os valores dinâmicos vindos do banco e a expansão dos detalhes.
8. Mostre ClinicalHistoryTest e ClinicalWebTest e execute mvn test.

## Evidências antes da publicação

- Capture a tela da Issue desta etapa no GitHub, com título e situação visíveis.
- Salve a imagem em docs/entrega-04/issues.png e adicione a imagem na seção desta entrega no README.
- Publique os dois vídeos no YouTube e substitua os marcadores “A gravar” no README por links reais.
- Confira os links em uma janela sem login antes da entrega.
