# Rede Blockchain
 O sistema permite a criação de transações, a assinatura delas para garantir sua autenticidade, a inclusão das transações em blocos, a adição desses blocos à rede e a validação através do mecanismo de Proof of Work, utilizando nonce e dificuldade. Além disso, o projeto assegura a integridade e a validação de toda a rede. Esse é o desafio proposto na trilha de aprendizado do programa de bolsas de Blockchain - Compass.
 ---
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
Chave pública da carteira: 5babee641faf2260cee2c024c3f667b52852cacfccdd556999902f74f268d20c
Chave privada da carteira: ae79721b-49e7-4156-b838-5049a3bb4d45
Assinatura da transação 1: 8e954cb92a239250a97cfbaa45da48acf69845137010e697201d382c310bd908
Assinatura da transação 2: a783083ae2221638c09ff1bdcda0437c6c538a8258d83cb736618c96a5a05362
Assinatura da transação 1 verificada: true
Assinatura da transação 2 verificada: true
Criando bloco com as transações
Bloco:
  Hash: dbaeb03f5e03cc389fb59e1f7bb4d13d423ca5e6c16f48fb665cdf02f1a691e5
  Hash Anterior: 0
  Nonce: 0
Bloco:
  Hash: 000052f2380719dde830b4ea121186eda8c363f9e930b2297f72573b28818e53
  Hash Anterior: dbaeb03f5e03cc389fb59e1f7bb4d13d423ca5e6c16f48fb665cdf02f1a691e5
  Nonce: 110794
Histórico de transações por endereço:
Endereço: Bob paga Charlie 5.0 BTC
  - Bob paga Charlie 5.0 BTC
Endereço: Alice paga Bob 10.0 BTC
  - Alice paga Bob 10.0 BTC
Blockchain é válida: true
```