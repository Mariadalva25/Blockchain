import java.security.*;
import java.util.*;

class Bloco {
    private String hash;
    private String hashAnterior;
    private List<String> transacoes;
    protected int nonce;

    public Bloco(String hashAnterior, List<String> transacoes) {
        this.hashAnterior = hashAnterior;
        this.transacoes = transacoes;
        this.nonce = 0;
        this.hash = calcularHash();
    }

    public void minerar(int dificuldade) {
        String prefixo = new String(new char[dificuldade]).replace('\0', '0');
        while (!calcularHash().substring(0, dificuldade).equals(prefixo)) {
            nonce++;
            hash = calcularHash();
        }
    }

    public String calcularHash() {
        return sha256(hashAnterior + transacoes.toString() + nonce);
    }

    public String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getHash() {
        return hash;
    }

    public String getHashAnterior() {
        return hashAnterior;
    }
}

class Blockchain {
    private List<Bloco> blocos;
    private Map<String, List<String>> historicoTransacoes;

    public Blockchain() {
        blocos = new ArrayList<>();
        blocos.add(new Bloco("0", List.of("Bloco Gênese"))); 
        historicoTransacoes = new HashMap<>();
    }

    public void adicionarBloco(List<String> transacoes, int dificuldade) {
        Bloco blocoAnterior = blocos.get(blocos.size() - 1);
        Bloco novoBloco = new Bloco(blocoAnterior.getHash(), transacoes);
        novoBloco.minerar(dificuldade);
        blocos.add(novoBloco);

        for (String transacao : transacoes) {
            String[] partes = transacao.split(":");
            String remetente = partes[0].trim();
            historicoTransacoes.putIfAbsent(remetente, new ArrayList<>());
            historicoTransacoes.get(remetente).add(transacao);
        }
    }

    public boolean isValida() {
        for (int i = 1; i < blocos.size(); i++) {
            Bloco blocoAtual = blocos.get(i);
            Bloco blocoAnterior = blocos.get(i - 1);

            if (!blocoAtual.getHashAnterior().equals(blocoAnterior.getHash())) {
                return false;
            }

            if (!blocoAtual.getHash().equals(blocoAtual.calcularHash())) {
                return false;
            }
        }
        return true;
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public Map<String, List<String>> getHistoricoTransacoes() {
        return historicoTransacoes;
    }
}

class Carteira {
    private String chavePrivada;
    private String chavePublica;


    public Carteira() {

        this.chavePrivada = UUID.randomUUID().toString();

        
        this.chavePublica = sha256(chavePrivada);
    }

    
    private String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public String getChavePublica() {
        return chavePublica;
    }


    public String assinarTransacao(String transacao) {
        
        return sha256(transacao + chavePrivada); 


    public boolean verificarAssinatura(String transacao, String assinatura) {
     
        return assinatura.equals(sha256(transacao + chavePrivada));
    }
}

class Transacao {
    private String remetente;
    private String destinatario;
    private double valor;

    public Transacao(String remetente, String destinatario, double valor) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return remetente + " paga " + destinatario + " " + valor + " BTC";
    }
}

public class Main {

    public static boolean isEnderecoValido(String endereco) {
        return endereco.length() == 64 && endereco.matches("[a-fA-F0-9]{64}");
    }

    public static void main(String[] args) {
        System.out.println("Iniciando Blockchain");


        Carteira carteira = new Carteira();
        System.out.println("Chave pública da carteira: " + carteira.getChavePublica());
        System.out.println("Chave privada da carteira: " + carteira.getChavePrivada());

        
        Transacao t1 = new Transacao("Alice", "Bob", 10);
        Transacao t2 = new Transacao("Bob", "Charlie", 5);

    
        String assinaturaT1 = carteira.assinarTransacao(t1.toString());
        String assinaturaT2 = carteira.assinarTransacao(t2.toString());

        System.out.println("Assinatura da transação 1: " + assinaturaT1);
        System.out.println("Assinatura da transação 2: " + assinaturaT2);

    
        boolean verificadoT1 = carteira.verificarAssinatura(t1.toString(), assinaturaT1);
        boolean verificadoT2 = carteira.verificarAssinatura(t2.toString(), assinaturaT2);
        System.out.println("Assinatura da transação 1 verificada: " + verificadoT1);
        System.out.println("Assinatura da transação 2 verificada: " + verificadoT2);

        Blockchain blockchain = new Blockchain();
        int dificuldade = 4;

    
        List<String> transacoes = new ArrayList<>();
        transacoes.add(t1.toString());
        transacoes.add(t2.toString());

        blockchain.adicionarBloco(transacoes, dificuldade);

        System.out.println("Criando bloco com as transações");
        for (Bloco bloco : blockchain.getBlocos()) {
            System.out.println("Bloco:");
            System.out.println("  Hash: " + bloco.getHash());
            System.out.println("  Hash Anterior: " + bloco.getHashAnterior());
            System.out.println("  Nonce: " + bloco.nonce);
        }

        System.out.println("Histórico de transações por endereço:");
        for (Map.Entry<String, List<String>> entry : blockchain.getHistoricoTransacoes().entrySet()) {
            System.out.println("Endereço: " + entry.getKey());
            for (String transacaoItem : entry.getValue()) {
                System.out.println("  - " + transacaoItem);
            }
        }

        System.out.println("Blockchain é válida: " + blockchain.isValida());
    }
}
