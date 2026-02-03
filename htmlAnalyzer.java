import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class htmlAnalyzer {
    public static void main(String[] args) {
        try {
            // Substitua pela sua URL real
            String urlString = "http://127.0.0.1:5500/teste.html";
            URL url = new URL(urlString);

            // Abra uma conexão e leia o conteúdo HTML
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder htmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line);
            }
            reader.close();

            // Use expressões regulares para encontrar o trecho desejado
            String regex = "<p>(.*?)</p>"; // Exemplo: procurando por parágrafos
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(htmlContent.toString());
            while (matcher.find()) {
                String trechoDesejado = matcher.group(1);
                System.out.println("Trecho encontrado: " + trechoDesejado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}