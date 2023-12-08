# Correção P4 (commit de 25/11)

  - **GRAVE** nomeção dos diagramas fora do padrão até hoje.
  - **Cliente** quando muda de plano, precisa mudar todos os veículos dele.
  - **App** relatório de estacionamentos lendo do arquivo (deve ler da memória para ser atualizado)
  - **GRAVE** classe uso de vaga não está fazendo o polimorfismo. Está usando um if de regra inaceitável
  ```
  if (cliente.isMensalista() || (cliente.isTurnista() && cliente.getTurno().eHorarioDoTurno(LocalTime.now()))) {
  ```
  - **GRAVE** com isso, o relatório está usando errado também um método "isMensalista" e "isHorista"

  - **Mensalista** o valor pago é 0. Da forma como está, a cada uso do estacionamento a pessoa paga R$500
  - **Interface Diario** não faz sentido, ainda mais porque está eliminando um requisito do uso de vaga: só pode estacionar em vaga disponível.
  - **Estacionamento** clientes deve ser mapa para busca
  - **Veículo** ainda está sem relatório (data e valor)

  Pessoal, o sistema de vocês está com boa parte dos requisitos implementadas, mas com muitas decisões equivocadas de modularidade (o que é tão importante para nós como os requisitos em si). Vejam as observações para ajeitarem nas últimas atualizações, pois isso pode zerar a nota na última etapa.


## Nota base do grupo

  - Requisitos corretamente implementados (classes e testes): 7/12 pontos;
  - Documentação de código: 3/3 pontos;
  - Tarefas nas aulas ao longo do projeto: 5/5 pontos;
  

## Colaborações
  - Gabriel Faria @gabrielfaria13
    - carga dados ✔️
    - Relat: arrecadação total ➕➖
    - Média arrecadação horistas ⚠️
  - Gabriel Ferreira @Druitti
    - Uso Mensal ⚠️
    - Uso Hora ⚠️
    - Uso turno ⚠️
  - Joao Victor @JvSalim
    - Relat: arrecadação total ➕➖
  - Lucas Garcia @lucasgarcia04
    - Uso Mensal ⚠️
    - Uso Hora ⚠️
    - Uso turno ⚠️
  - Maisa Pires @my4wyy
    - Média de usos mensalistas ⚠️
  - Miguel Vieira @migueltico2
    
  
  - Geral
    - Cliente com plano de uso ✔️ ➕➖
    - Estruturas de armazenamento eficientes; ➕➖
   	- Relatório veículos ❌
	- Aplicativo com acesso aos relatórios ✔️
	- App robusto ✔️
	
	

## Requisitos
  - Diagrama atualizado
  - Estruturas de armazenamento eficientes; 
  - Aplicativo com acesso aos requisitos principais e aos relatórios gerenciais; 
  - Aplicativo com base de teste cadastrada;
  - Aplicativo com funcionamento robusto;
  - Cliente categorizado em três modalidades: horista, de turno ou mensalista.
  - Regra de cliente horista;
  - Regra de cliente mensalista;
  - Regra de cliente de turno;
  - Relatórios:
    - Veículos (data e valor de uso)
    - A arrecadação total de cada um dos estacionamentos, em ordem decrescente;
    - Quantas vezes, em média, os clientes mensalistas utilizaram o estacionamento no mês corrente;
    - Qual a arrecadação média gerada pelos clientes horistas no mês corrente;

❌
➕➖
✔️
⚠️