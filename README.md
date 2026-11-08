<p align="center">
  <img src="./logo.svg" alt="Logo MediAgenda" width="120" />
</p>

<h1 align="center">MediAgenda</h1>

## Sobre o Projeto

MediAgenda é uma aplicação web para gerenciamento de consultório médico, que permite o cadastro de pacientes e médicos, o agendamento e acompanhamento de consultas, e o registro de prontuários.

O sistema busca facilitar a rotina administrativa de clínicas de pequeno e médio porte, reduzindo conflitos de agenda, centralizando o histórico clínico dos pacientes e dando visibilidade rápida da agenda diária de cada médico.

## Tecnologias e ferramentas

- Java, Spring Boot, Spring Data JPA
- Thymeleaf, HTML5 e CSS3
- H2 (banco local para desenvolvimento)

## Equipe
| Nome | email |
| --- | --- |
| Henrique Neves Pontual | hnp@cesar.school |
| Lucas Nery Sereno |  lns3@cesar.school |
| Pedro Freitas Lustosa de Caravalho | pflc@cesar.school |
| Rubens Sousa Carvalho da Rocha | rscr@cesar.school |

## Alterações na equipe

Não houve entrada ou saída de integrantes registrada até esta entrega.

| Membro | E-mail CESAR School | Entrada | Saída |
| --- | --- | --- | --- |
| Nenhuma alteração registrada | - | - | - |

## Como executar

Requisitos: Java 17 e Maven 3.9 ou superior. Na pasta do projeto:

```bash
mvn test
mvn spring-boot:run
```

Abra http://localhost:8080. O banco H2 fica em `data/mediagenda` e preserva os dados entre execuções. Use dados fictícios nas gravações.

## Sequência das entregas

| Etapa | Escopo | Material |
| --- | --- | --- |
| 01 | Sete histórias em BDD e protótipo Lo-Fi | Links originais abaixo |
| 02 | Histórias 1, 2 e 3 — pacientes, médicos e agenda | [Guia e dois roteiros](docs/entrega-02/GUIA.md) |
| 03 | Histórias 4 e 5 — cancelar e confirmar | [Guia e dois roteiros](docs/entrega-03/GUIA.md) |
| 04 | Histórias 6 e 7 — prontuário e histórico | [Guia e dois roteiros](docs/entrega-04/GUIA.md) |

## Entrega 1

- [Histórias em BDD](./Histórias_BDD.md)
- [Protótipo Lo-Fi (Figma)](https://www.figma.com/design/UEResJqM7OeZHOcTmodCFX/MediAgenda---Prot%C3%B3tipo-Lo-Fi?node-id=3-46&p=f&t=iy5O8tKdYP0rVKmC-0)
- [Screencast (YouTube)](https://youtu.be/cMknoEeaaEY)


## Entrega 02

### Histórias implementadas (POST-IT)

> **História 1** — Como recepcionista da clínica, quero cadastrar um novo paciente no sistema, para que ele possa agendar consultas futuramente.

> **História 2** — Como administrador do consultório, quero cadastrar um médico com sua especialidade, para que ele possa ser associado a consultas.

> **História 3** — Como paciente ou recepcionista, quero agendar uma consulta com um médico em uma data e horário específicos, para garantir o atendimento.

[Roteiro do vídeo de uso e do vídeo de código](docs/entrega-02/GUIA.md) · [Cenários BDD e aceite](docs/cenarios-validacao.md) · [Issue da etapa](https://github.com/LNSereno/MediAgenda/issues/3)

Captura da Issue desta etapa: a equipe deve registrar a tela atual e inseri-la nesta seção, conforme o guia.

- Vídeo de uso no YouTube — **A gravar**.
- Vídeo de código no YouTube — **A gravar**.

## Entrega 03

### Histórias implementadas (POST-IT)

> **História 4** — Como paciente, quero cancelar uma consulta agendada, para liberar o horário caso eu não possa comparecer.

> **História 5** — Como paciente, quero confirmar minha presença em uma consulta agendada, para que a clínica saiba que devo comparecer.

[Roteiro do vídeo de uso e do vídeo de código](docs/entrega-03/GUIA.md) · [Cenários BDD e aceite](docs/cenarios-validacao.md) · [Issue da etapa](https://github.com/LNSereno/MediAgenda/issues/4)

Captura da Issue desta etapa: a equipe deve registrar a tela atual e inseri-la nesta seção, conforme o guia.

- Vídeo de uso no YouTube — **A gravar**.
- Vídeo de código no YouTube — **A gravar**.

## Entrega 04

### Histórias implementadas (POST-IT)

> **História 6** — Como médico, quero registrar diagnóstico e prescrição após atender um paciente, para manter o histórico clínico atualizado.

> **História 7** — Como médico, quero visualizar o histórico de prontuários de um paciente, para ter contexto sobre atendimentos anteriores.

[Roteiro do vídeo de uso e do vídeo de código](docs/entrega-04/GUIA.md) · [Cenários BDD e aceite](docs/cenarios-validacao.md) · [Issue da etapa](https://github.com/LNSereno/MediAgenda/issues/2)

![Captura da Issue da entrega 04](docs/entrega-04/issue-02.jpg)

- Vídeo de uso no YouTube — **A gravar**.
- Vídeo de código no YouTube — **A gravar**.

## Apresentação final

[Roteiro de 7 minutos e 30 segundos, com demonstração e divisão sugerida entre os integrantes](docs/apresentacao-final.md).

## Verificação

O projeto usa quatro entidades persistidas, Spring Boot, Thymeleaf e H2. Cada história consulta ou grava dados no banco. Getters, setters e construtores são escritos explicitamente.

[Registro dos testes e verificações](docs/entrega-04/verificacao.md). Os oito testes passaram na revisão de 08/11/2026.

A entrega documental fica completa depois que a equipe publicar os seis vídeos das etapas 02 a 04 e incluir as capturas atuais das Issues 02 e 03.
