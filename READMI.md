# Rede Blockchain
 O sistema permitir a criação de transações, a inclusão delas em blocos, a inserção desses blocos na rede e a validação da autenticidade. Além disso, o projeto garante a integridade e validação da rede como um todo. Desafio proposto durante a trilha de aprendizado do programa de bolsas de Blockchain - Compass.

 # Configuração inicial
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
Bloco:
  Hash: [hash do bloco gênese]
  Hash Anterior: 0
Bloco:
  Hash: [hash do bloco de transação]
  Hash Anterior: [hash do bloco gênese]
Blockchain é válida: true
```
