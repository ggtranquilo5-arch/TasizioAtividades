import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorOperacoes {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
  
        server.createContext("/dados", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {

                int a = 15;
                int b = 8;
                double divisao = (double) a / b;

                String json = "{"
                    + "\"a\":" + a + ","
                    + "\"b\":" + b + ","
                    + "\"soma\":" + (a + b) + ","
                    + "\"subtracao\":" + (a - b) + ","
                    + "\"multiplicacao\":" + (a * b) + ","
                    + "\"divisao\":" + divisao
                    + "}";

                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                
                exchange.sendResponseHeaders(200, json.length());
                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();
            }
        });

        server.setExecutor(null);
        System.out.println("Servidor Java rodando em http://localhost:8080/dados");
        server.start();
    }
}