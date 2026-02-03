import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class htmlAnalyzer {
    public static void main(String[] args) {
        // Caminho absoluto ou relativo para o arquivo HTML
        String caminho = "teste.html"; // ajuste o caminho se necessário

        int profundidade = 0;
        int abertura = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String linhaLimpa = linha.strip();

                if (linhaLimpa.length() >= 2) {
                    String primeiro = linhaLimpa.substring(0, 1);
                    String doisPrimeiros = linhaLimpa.substring(0, 2);

                    // Se for abertura de uma tag
                    if (!doisPrimeiros.equals("</") && primeiro.equals("<")) {
                        System.out.println(linha);
                        abertura++;
                        if (abertura >= profundidade) {
                            profundidade++;
                        }
                        System.out.println("Abertura atual: " + abertura);
                        System.out.println("Profundidade atual: " + profundidade);

                    } else if (doisPrimeiros.equals("</")) {
                        System.out.println(linha);
                        abertura--;
                        System.out.println("Fechamento encontrado, abertura atual: " + abertura + ", profundidade atual: " + profundidade);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
