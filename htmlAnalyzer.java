import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class htmlAnalyzer {
    public static void main(String[] args) {
        // Caminho absoluto ou relativo para o arquivo HTML
        String caminho = "src/teste2.html"; // ajuste o caminho se necessário

        int profundidade = 0;
        int abertura = 0;
        String valorLinha = "";

        valorLinha = depth(caminho, profundidade, abertura, valorLinha);
        System.out.println(valorLinha);

    }

    public static String depth(String caminho, int profundidade, int abertura, String valorLinha){
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            // boolean dentroTag = false;
            while ((linha = reader.readLine()) != null) {
                String linhaLimpa = linha.strip();

                if (linhaLimpa.length() >= 2) {
                    // String primeiro = linhaLimpa.substring(0, 1);
                    String doisPrimeiros = linhaLimpa.substring(0, 2);

                    // abertura de uma tag
                    if (!doisPrimeiros.equals("</")) {
                        // System.out.println(linha);
                        abertura++;
                        if (abertura > profundidade) {
                            profundidade = abertura;
                            valorLinha = linhaLimpa;
                        }
                        
                        // Debug
                        // System.out.println("Abertura atual: " + abertura);
                        // System.out.println("Profundidade atual: " + profundidade);
                        
                        // fechamento de uma tag                    
                    } else if (doisPrimeiros.equals("</")) {
                        // System.out.println(linha);
                        abertura--;
                        // System.out.println("Fechamento encontrado, abertura atual: " + abertura + ", profundidade atual: " + profundidade);
                    }
                    

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String doisPrimeiros = valorLinha.substring(0, 1);
        if (doisPrimeiros.equals("<")) {
            valorLinha = removeTag(valorLinha);
        }

        return valorLinha;
    }

    public static String removeTag(String valorObtido){

        StringBuilder novaString = new StringBuilder(valorObtido);

        for (int i = 0; i < novaString.length(); i++){
            char element = novaString.charAt(i);
            
            if (element == '<') {
                // for (int j = i; novaString.charAt(j) != '>'; j++){
                //     novaString.deleteCharAt(j);
                // }

                while (element != '>') {
                    novaString.deleteCharAt(i);
                    element = novaString.charAt(i);
                }
            }

            if (element == '>') {
                novaString.deleteCharAt(i);
                i--;
            }

            // System.out.println(novaString);
            // System.out.println(element);                    
        }
                
        return novaString.toString();
    }
}
