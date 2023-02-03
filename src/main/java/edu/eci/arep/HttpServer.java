package edu.eci.arep;

import java.net.*;
import java.io.*;
import java.util.HashMap;

public class HttpServer {
    static HashMap <String , String> cache = new HashMap<>();
    static HttpConnection conexionAPI = new HttpConnection();
    static String route = "/HOME";
    static String response ="";

    /**
     * Método main encargado de iniciar el servidor http
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        boolean running = true;
        while (running) {
            try {
                System.out.println("Ready to Receive ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine;
            String outputLine = " ";
            boolean firstLine = true;
            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    route = inputLine.split(" ")[1];
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            if (route.split("/").length > 1) {
                saveCache();
            }
            else {
                response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n" +
                        "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<title>Form Example</title>\n" +
                        "<meta charset=\"UTF-8\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "</head>\n" +
                        "<body bgcolor= \"#e5dde6\">\n" +
                        "<center><h1>BUSCA UNA PELICULA</h1></center><br>\n" +
                        "<form action=\"/get\">\n" +
                        "<center><label for=\"name\">Name:</label><br>\n</center>" +
                        "<center><input type=\"text\" id=\"name\" value=\"\"></center><br><br>\n" +
                        "<center><input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\"></center><br><br>\n" +
                        "</form>\n" +
                        "<div id=\"getrespmsg\"></div>\n" +
                        "\n " +
                        "<script>\n" +
                        "function loadGetMsg(){\n" +
                        "let name = document.getElementById(\"name\");\n" +
                        "let url = \"get/?t=\" + name.value;\n" +
                        "fetch (url, {method: 'GET'})\n" +
                        ".then(x => x.text())\n" +
                        ".then(y => document.getElementById(\"getrespmsg\").innerHTML = y);\n" +
                        "}\n " +
                        "</script>\n" +
                        "</body>\n" +
                        "</html>";
            }
            outputLine = response;
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     *Guarda en un HashMap el cache de la aplicación
     * @throws IOException
     */
    private static void saveCache() throws IOException {
        if (route.split("/")[1].equals("get")) {
            if(cache.containsKey(route.split("=")[1])){
                System.out.println("entro cache");
                response = cache.get(route.split("=")[1]);
            }
            else{
                response = conexionAPI.UseApi(route.split("/")[2]);
                cache.put(route.split("=")[1],response);
            }
        }
    }
}