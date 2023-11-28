# Correção
  - O que é a classe Registro?
  - O que é a classe Histórico?
  - **App**: EstacionamentoLotadoException não existe
  - **App**: getPlaca desnecessário (ou busca veículo interno)
  - **App**: IOException | ClassNotFoundException não são o mesmo erro, mas estão com o mesmo tratamento
  - **App**: Excecao no app ao estacionar
  - **Cliente:** dando vários gets que não existem em uso de vaga. 
  - **Uso** e **TesteUso**: não pode dar valor 0 para uso
  - **TesteUso**: método Helper? Não seria um before?
  - **TesteVaga**: um teste/assert para cada requisito
  - **TesteVeiculo**: saida >=0 não testa nada
  - **Uso**: não é por fracao de minutos. É fração de horas (a cada 15 min. Mínimo não pode ser 0)
  - **Uso**: get desnecessário
  - **Uso**: while para calcular tempo?
  - **Vaga**: id errado
  - **Vaga**: toString só retorna o id
  - **Vaga**: sem documentação
  - **Veículo**: não está tratando excecoes

## Nota base do grupo: 12

  - Contribuições
    - Gabriel Faria @gabrielfaria13
      - Estacionamento ✔️
      - Serializacao ✔️
      - Carga de dados ⚠️ (local dos arquivos)
    - Gabriel Ferreira @Druitti
      - Vaga ⚠️❌ (sem doc)
      - Teste uso ⚠️⚠️
      - Uso de vaga 2 ⚠️⚠️✔️ (sobrescreveu código do Miguel?)
      - Exceções uso de vaga ✔️
    - Joao Victor Salim @JvSalim
      - Estacionamento ✔️
      - Teste vaga: ⚠️
      - Excecões estacionamento ✔️
    - Lucas Garcia @lucasgarcia04
      - Cliente ✔️ 
      - Teste estacionamento ✔️
      - Historico cliente ⚠️
    - Maisa Pires @my4wyy
      - Veiculo ⚠️
      - Teste cliente ✔️
      - App: servico ❌
      - App: relatorios ✔️
    - Miguel Vieira @migueltico2
      - Uso de vaga1: ❌⚠️ (não tem código seu lá)
      - Teste Veículo: ⚠️⚠️
      - App ⚠️
      - Vaga toString e id ❌❌
    
  - Tarefas nas aulas 04 e 11/10: 5 pontos;
    - Gabriel Faria @gabrielfaria13 ✔️ ❌
    - Gabriel Ferreira @Druitti ➕➖ ✔️
    - Joao Victor Salim @JvSalim ✔️ ✔️
    - Lucas Garcia @lucasgarcia04 ✔️✔️
    - Maisa Pires @my4wyy ✔️✔️ 
    - Miguel Vieira @migueltico2 ✔️❌
    

- Requisitos : 9/12 pontos;
- Documentação: 3/3 pontos;


## Requisitos
  - Estacionar, sair e cobrança: R$4 a cada 15 minutos, com valor limite de R$50.  
  - Serviços, tempo mínimo e cobrança 
  - Um cliente identificado tem acesso a seu histórico de uso do estacionamento.  
  - Os dados das classes existentes devem ser salvos utilizando-se serialização; 
  - App:
    - Cadastrar estacionamentos com número de vagas
    - Veículos registrados por placa e ligados a clientes. 
    - Cliente identificado com nome e com mais de um veículo. 
    - Dados de clientes e veículos salvos e carregados.
    - 3 estacionamentos
	  - Gerar aleatoriamente 50 usos de vagas
  - Relatórios:
    - Valor total arrecadado do estacionamento;
    - Valor arrecadado em determinado mês;
    - Valor médio de cada utilização do estacionamento;
    - Ranking dos clientes que mais geraram arrecadação em um determinado mês.
  - Exceções que serão tratadas no aplicativo:
    - Sair de uma vaga cujo uso já foi finalizado;
    - Estacionar em uma vaga sem haver finalizado o uso anterior;
    - Cadastrar um cliente já existente;
    - Cadastrar um veículo já existente;
  
❌
➕➖
✔️
⚠️





