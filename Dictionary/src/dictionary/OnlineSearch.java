/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Yorovy
 */
public class OnlineSearch {
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
    
    public static void translate(String text) {
        try {
            String commant = new StringBuilder()
                .append("{\"fromLang\":\"en\",\"toLang\":\"vi\",\"text\":\"")
                .append(text).append("\"}").toString();
            System.out.println(commant);
            URL url = new URL (ENDPOINT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
            connection.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(commant.getBytes());
                os.flush();
            }

            int statusCode = connection.getResponseCode();
            System.out.println(statusCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (statusCode == 200) ? connection.getInputStream() : connection.getErrorStream()
            ));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("Lỗi rồi ông ơi");
        }
        
    }
}
