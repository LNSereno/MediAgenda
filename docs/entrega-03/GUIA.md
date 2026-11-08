# Entrega 03 — Confirmação e cancelamento

## Histórias implementadas (POST-IT)

### História 4 — Cancelamento de consulta

> Como paciente, quero cancelar uma consulta agendada, para liberar o horário caso eu não possa comparecer.

### História 5 — Confirmação de consulta

> Como paciente, quero confirmar minha presença em uma consulta agendada, para que a clínica saiba que devo comparecer.

## Referências

- [Histórias BDD](../../Histórias_BDD.md)
- [Protótipo Figma](https://www.figma.com/design/UEResJqM7OeZHOcTmodCFX/MediAgenda---Prot%C3%B3tipo-Lo-Fi)
- [Cenários adicionais e critérios de aceite](../cenarios-validacao.md)
- [Como executar](../../README.md#como-executar)
- [Issue desta etapa](https://github.com/LNSereno/MediAgenda/issues/4)

## Vídeo de uso

Gravar a aplicação Spring Boot em execução, com áudio ou legendas. Siga estes passos e explique o resultado de cada ação.

1. Use os cadastros e uma consulta AGENDADA criados na demonstração da entrega 2.
2. Na agenda, abra **Ver consulta**. Clique **Confirmar presença** e mostre CONFIRMADA após o redirecionamento.
3. Abra a consulta novamente. Tente cancelar sem motivo e mostre a validação do campo.
4. Digite “Imprevisto pessoal” e cancele. Abra os detalhes e mostre CANCELADA com o motivo.
5. Agende outra consulta para a mesma médica no mesmo horário. Mostre que ela é aceita.
6. Recarregue a página e mostre os dois registros, um cancelado e outro agendado.

## Vídeo de código

Grave o editor e explique estes trechos com áudio ou legendas.

1. Em AppointmentStatus.java, identifique AGENDADA, CONFIRMADA e CANCELADA.
2. Em WebController.java, abra os POSTs confirm e cancel e o tratamento de mensagens.
3. Em AppointmentService.java, explique confirm, cancel, @Transactional e a busca que bloqueia a consulta durante uma alteração.
4. Mostre que confirm aceita apenas AGENDADA, e cancel exige uma consulta em aberto e motivo.
5. Em schedule e AppointmentRepository, mostre como consultas CANCELADAS são excluídas do teste de conflito.
6. Abra appointment-details.html e mostre os botões condicionados ao status.
7. Mostre os testes que verificam essas regras.

## Evidências antes da publicação

- Capture a tela da Issue desta etapa no GitHub, com título e situação visíveis.
- Salve a imagem em docs/entrega-03/issues.png e adicione a imagem na seção desta entrega no README.
- Publique os dois vídeos no YouTube e substitua os marcadores “A gravar” no README por links reais.
- Confira os links em uma janela sem login antes da entrega.
