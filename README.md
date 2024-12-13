# Rede Blockchain
 O sistema permite a criação de transações, a assinatura delas para garantir sua autenticidade, a inclusão das transações em blocos, a adição desses blocos à rede e a validação através do mecanismo de Proof of Work, utilizando nonce e dificuldade. Além disso, o projeto assegura a integridade e a validação de toda a rede. Esse é o desafio proposto na trilha de aprendizado do programa de bolsas de Blockchain - Compass.

# Funcionalidade
1. Criação de Transações e Assinaturas:
Criação de Transações: A classe Transacao representa uma transação que ocorre entre dois usuários (remetente e destinatário), com um valor associado. Ela inclui também uma taxa de transação, que pode ser usada para incentivar mineradores.
Assinaturas: A classe Carteira gera uma chave pública e uma chave privada. O remetente usa a chave privada para assinar as transações. A assinatura é gerada concatenando a transação com a chave privada e aplicando um hash SHA-256. A assinatura pode ser verificada com a chave pública.

2. Adição de Blocos à Rede e Validação do Mecanismo de Proof of Work:
Adição de Blocos: Os blocos são armazenados na lista blocos dentro da classe Blockchain. A cada transação, um novo bloco é gerado e adicionado à blockchain. O bloco contém um hash, o hash do bloco anterior, uma lista de transações e um número nonce.
Proof of Work: A classe Bloco implementa o mecanismo de proof of work no método minerar(). O método usa um prefixo (representado por zeros) que deve ser encontrado no hash do bloco. Isso é feito incrementando o nonce até que o hash do bloco comece com o número de zeros exigido pela dificuldade da rede.

3. Taxas de Transação:
Taxas de Transação: Cada transação inclui uma taxa de 0.01, que é especificada no construtor da classe Transacao. As taxas incentivam os mineradores a incluir transações no próximo bloco e são pagas ao minerador.

4. Recompensas para Mineradores:
Recompensas: Quando um bloco é minerado, a recompensa é atribuída ao minerador. No método adicionarBloco() da classe Blockchain, após a mineração do bloco, a recompensa (passada como parâmetro) é adicionada ao saldo do minerador. A recompensa é uma maneira de incentivar os mineradores a validar blocos e garantir a segurança da rede.

5. Controle de Saldos:
Controle de Saldos: A cada transação, os saldos dos remetentes e destinatários são atualizados. Isso é feito dentro do método adicionarBloco(). Se o remetente tem saldo suficiente, o valor é subtraído do saldo dele e adicionado ao saldo do destinatário. O saldo do minerador também é atualizado após a mineração do bloco.

6. Resolução de Forks:
Resolução de Forks: Em caso de um fork (quando duas versões da blockchain existem), a função resolverFork() compara a cadeia atual com uma nova cadeia (passada como parâmetro). A cadeia mais longa (com mais blocos) é adotada como a válida, garantindo que a rede tenha uma única versão da blockchain. Isso resolve disputas entre diferentes versões da blockchain, onde a mais longa sempre é considerada a verdadeira.


 O sistema foi desenvolvido em java
* Configurar o Ambiente
Antes de tudo, você precisa ter o Java Development Kit (JDK) instalado em seu sistema. Você pode baixar a versão mais recente do JDK do site oficial da Oracle ou usar uma distribuição open-source, como o OpenJDK.

*  Criar o Arquivo Java
Crie um arquivo chamado Main.java e copie o código que esta na pasta Main.java para esse arquivo:

# Executando a aplicação
---
**Compilar o Código**

Abra um terminal ou prompt de comando, navegue até o diretório onde você salvou Main.java, e compile o código usando o seguinte comando:

```
javac Main.java
```
Esse comando criará um arquivo Main.class no mesmo diretório.
Após a compilação, você pode executar o código com o seguinte comando:
```
java Main
```
Após executar o comando, você verá a saída no console, que incluirá os hashes dos blocos e a validação da blockchain:
```
Iniciando Blockchain
Chave pública da carteira: 85050dfa7f98240c6754d102a0f93824971f8dfb3f64ca93947493d6e6dd164d
Chave privada da carteira: 052fd528-2596-4ece-a438-82da85582728
Assinatura da transação 1: 29faaa4a9a15ac9619786b22fc44d6ad5e3c2f07671a255d6d787b2cd77616da
Assinatura da transação 2: ef83a276f4b69d28daa36df40aa7dacaa27451fcdbac9de488c43245c4a94fad
Assinatura da transação 1 verificada: true
Assinatura da transação 2 verificada: true
Criando bloco com as transações
Bloco:
  Hash: dbaeb03f5e03cc389fb59e1f7bb4d13d423ca5e6c16f48fb665cdf02f1a691e5
  Hash Anterior: 0
  Nonce: 0
  Recompensa do Minerador: 0.0
Bloco:
  Hash: 0000da9513ca6e54c86ec59fc984dc19673afe671d8ff60bfaa7a8f3944cec76
  Hash Anterior: dbaeb03f5e03cc389fb59e1f7bb4d13d423ca5e6c16f48fb665cdf02f1a691e5  
  Nonce: 37426
  Recompensa do Minerador: 10.0
Histórico de transações por endereço:
Endereço: Bob
  - Bob:Charlie:5.0
Endereço: Alice
  - Alice:Bob:10.0
Blockchain é válida: true
Saldos:
Endereço: Bob Saldo: 5.0 BTC
Endereço: Alice Saldo: -10.0 BTC
Endereço: Charlie Saldo: 5.0 BTC
Endereço: Minerador Saldo: 10.0 BTC
```