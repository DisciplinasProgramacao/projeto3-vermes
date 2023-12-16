# Correção

  - Observer: interface "subject". Há uma confusão: estacionamento chama notificação dele mesmo??
  - **GRAVE** Tudo contra a modularidade nesta linha:

```
  Iterator<Map.Entry<Cliente, Double>> iterator = top5Clientes.entrySet().iterator();
```

isso aparentemente seria um forEach (ou um sublist/submap)

  - O observer não está realmente observando. Ele insere o cliente, ordena todo mundo e depois corta em 5. O correto, para o padrão, seria ele comparar este cliente com os 5 maiores que já estão ordenados antes.

  - **RUIM** Não consigo mudar de plano de cliente no app
  - **GRAVE** Serviço é contratado na chegada, não na saída. O que isso causa de ruim: vocês estão fazendo a lógica do preço do serviço no app, o que está **MUITO** errado.

  - cliente, ao mudar de plano, precisa mudar o plano dos veículos
  - **RUIM** veículo usando vetor em lugar de coleção
  - **RUIM** por isso, veículo precisando receber cliente a cada estacionada (podia guardar o atributo)
  - **MUITO GRAVE** isTurnista, isHorista.... já comentado no P4. Viola OCP e DIP em mais de umas classe
  - **MUITO GRAVE** três métodos iguais no cliente por causa de tipo... 
  - **MUITO GRAVE** cálculos na classe cliente que são só de um tipo de cliente... 
    

  - top5 não está pegando dados carregados, só os digitados
  - não está cobrando preço mínimo para clientes horistas (só o serviço e 0 pelo tempo)
  - informação é ouro: histórico do cliente mostrando informações confusas
  - dados de arrecadação não podem ler só do arquivo (ou então tem que atualizar o arquivo antes de fazer a conta)

  - em resumo: a maioria das regras está implementada, mas a versão final tem **MUITOS** problemas graves de modularidade

  - dadas nossas conversas na apresentação, e como os erros são de projeto no geral, dei a nota igual para todos. Se quiserem que eu reavalie individualmente, basta avisar. 

## Nota base 8 

## Colaborações
  - Gabriel Faria
  - Gabriel Ferreira
  - João Victor
  - Lucas
  - Maisa 
  - Miguel
    


# Requisitos:
  - Gerais: /6 pontos
    - cliente mudar de plano
    - estacionar e cálculo de preço
    - relatórios estacionamento
    - relatórios cliente e carro
  - Padrões de projeto: /6 pontos
    - Factory 
    - Strategy
  - App: /5 pontos
    - funcional 
    - carga de teste
    - robustez
  - Documentação: /3 pontos
    - diagrama 
    - javadoc 


❌
😐
✔️
⚠️