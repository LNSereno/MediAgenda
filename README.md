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

## Entrega 1

- [Histórias em BDD](./Histórias_BDD.md)
- [Protótipo Lo-Fi (Figma)](https://www.figma.com/design/UEResJqM7OeZHOcTmodCFX/MediAgenda---Prot%C3%B3tipo-Lo-Fi?node-id=3-46&p=f&t=iy5O8tKdYP0rVKmC-0)
- [Screencast (YouTube)](https://youtu.be/cMknoEeaaEY)

## Entrega 02

Esta etapa não foi implementada pela equipe. A base necessária para as histórias da Entrega 03 foi incluída nesta branch, sem criar evidências retroativas.

## Entrega 03

### Histórias implementadas (formato POST-IT)

> **História 4 - Cancelamento de consulta**
>
> **Como** paciente, **quero** cancelar uma consulta agendada e informar o motivo, **para** liberar o horário caso eu não possa comparecer.
>
> **Pronto quando:** a consulta muda para `CANCELADA`, o motivo fica persistido e o horário pode ser usado novamente pelo mesmo médico.

> **História 5 - Confirmação de consulta**
>
> **Como** paciente, **quero** confirmar minha presença em uma consulta agendada, **para** que a clínica saiba que devo comparecer.
>
> **Pronto quando:** uma consulta `AGENDADA` muda para `CONFIRMADA` e o novo status permanece salvo ao recarregar a página.

### O que foi desenvolvido

- Aplicação Spring Boot com interface web em Thymeleaf.
- Persistência JPA de pacientes, médicos e consultas (três entidades de domínio), usando H2 local.
- Cadastro dos dados mínimos que sustentam a agenda e bloqueio de conflito de horário do médico.
- Confirmação de presença e cancelamento com motivo persistido.
- Testes unitários das regras principais de agenda.
- Sem Lombok ou outro gerador de código boilerplate.

### Como rodar

Pré-requisito: Java 17 e Maven 3.9 ou superior.

```bash
mvn spring-boot:run
```

Abra [http://localhost:8080](http://localhost:8080). Os dados ficam em `data/mediagenda` e são preservados entre execuções locais. Para executar os testes:

```bash
mvn test
```

### Evidências a serem incluídas pela equipe

- Vídeo do sistema: **pendente** - grave seguindo as [instruções](docs/entrega-03/COMPLEMENTOS_MANUAIS.md) e substitua este marcador pelo link real do YouTube.
- Vídeo explicando o código: **pendente** - grave seguindo as [instruções](docs/entrega-03/COMPLEMENTOS_MANUAIS.md) e substitua este marcador pelo link real do YouTube.
- Issues do GitHub: nenhuma issue existia no repositório na análise. Crie/atualize issues reais, capture a tela e salve-a como `docs/entrega-03/evidencias/issues-entrega-03.png`; então troque este texto pela imagem real.
