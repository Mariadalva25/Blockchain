import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

class Bloco {
    private String hash;
    private String hashAnterior;
    private String merkleRoot;
    private List<String> transacoes;

    public Bloco(String hashAnterior, List<String> transacoes) {
        this.hashAnterior = hashAnterior;
        this.transacoes = transacoes;
        this.merkleRoot = calcularMerkleRoot();
        this.hash = calcularHash(); // Calcular o hash ao criar o bloco
    }

    public String calcularHash() {
        return sha256(hashAnterior + merkleRoot);
    }

    private String calcularMerkleRoot() {
        List<String> hashes = new ArrayList<>();
        for (String transacao : transacoes) {
            hashes.add(sha256(transacao));
        }
        return construirMerkleTree(hashes);
    }

    private String construirMerkleTree(List<String> hashes) {
        while (hashes.size() > 1) {
            List<String> novaCamada = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i += 2) {
                if (i + 1 < hashes.size()) {
                    novaCamada.add(sha256(hashes.get(i) + hashes.get(i + 1)));
                } else {
                    novaCamada.add(hashes.get(i)); // Se o número de hashes for ímpar
                }
            }
            hashes = novaCamada;
        }
        return hashes.get(0); // Raiz da árvore de Merkle
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
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHash() {
        return hash;
    }

    public String getHashAnterior() {
        return hashAnterior;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }
}

class Blockchain {
    private List<Bloco> blocos;

    public Blockchain() {
        blocos = new ArrayList<>();
        // Adiciona o bloco gênese (o primeiro bloco da blockchain)
        blocos.add(new Bloco("0", List.of("Bloco Gênese")));
    }

    public void adicionarBloco(List<String> transacoes) {
        Bloco blocoAnterior = blocos.get(blocos.size() - 1);
        Bloco novoBloco = new Bloco(blocoAnterior.getHash(), transacoes);
        blocos.add(novoBloco);
    }

    public boolean isValida() {
        for (int i = 1; i < blocos.size(); i++) {
            Bloco blocoAtual = blocos.get(i);
            Bloco blocoAnterior = blocos.get(i - 1);

            // Verifica se o hash do bloco anterior corresponde
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
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Blockchain");
        Blockchain blockchain = new Blockchain();

        System.out.println("Criando transações");
        blockchain.adicionarBloco(List.of("Transação 1: Alice paga Bob 10 BTC", "Transação 2: Bob paga Charlie 5 BTC"));
        
        System.out.println("Criando bloco com as transações");

        // Exibir os blocos da blockchain
        for (Bloco bloco : blockchain.getBlocos()) {
            System.out.println("Bloco:");
            System.out.println("  Hash: " + bloco.getHash());
            System.out.println("  Hash Anterior: " + bloco.getHashAnterior());
            System.out.println("  Merkle Root: " + bloco.getMerkleRoot());
        }

        System.out.println("Blockchain é válida: " + blockchain.isValida());
    }
}
