import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class htmlAnalyzer {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Uso: java htmlAnalyzer <URL>");
            System.out.println("Exemplo: java htmlAnalyzer https://www.exemplo.com");
            return;
        }

        String caminho = args[0];

        int profundidade = 0;
        int abertura = 0;
        String valorLinha = "";

        valorLinha = depth(caminho, profundidade, abertura, valorLinha);
        System.out.println(valorLinha);

    }

    public static String depth(String caminho, int profundidade, int abertura, String valorLinha){
        try {
            URL url = new URL(caminho);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("User-Agent", "Mozilla/5.0");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;

            while ((linha = reader.readLine()) != null) {
                String linhaLimpa = linha.strip();

                if (linhaLimpa.length() >= 2) {

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
            reader.close();
        } catch (IOException e) {
            System.err.println("Erro ao acessar a URL: " + caminho);
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
