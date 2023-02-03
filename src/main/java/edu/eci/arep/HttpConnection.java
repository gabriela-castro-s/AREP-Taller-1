package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class HttpConnection {

    public  static final String key = "&apikey=8643317e";
    private static final String URL = "https://www.omdbapi.com/";
    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     *
     * @param consulta
     * @return
     * @throws IOException
     */
    public String UseApi(String consulta) throws IOException {

        URL obj = new URL(getURL(consulta));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            System.out.println(response);
            return response.toString();
        }
        else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return null;
    }


    /**
     *
     * @param path
     * @return
     */
    public  String getURL(String path){
        return URL+path+key;
    }

}