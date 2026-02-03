import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

public class htmlAnalyzer {
    public static void main(String[] args) {
        String urlString = "teste.html"; // Exemplo de URL

        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL();

            // Abre o stream de leitura
            try (InputStream is = url.openStream();
                 Scanner scanner = new Scanner(is, "UTF-8")) {
                
                // Lê linha por linha
                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    // Exibe a linha (apenas texto bruto)
                    System.out.println(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro geral: " + e.getMessage());
        }
    }
}
