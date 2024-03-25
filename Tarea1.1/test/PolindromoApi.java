

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 import org.json.JSONArray ;
    import org.json.JSONObject ;

    import java.io.BufferedReader ;
    import java.io.InputStreamReader ;
    import java.net.HttpURLConnection ;
    import java.net.URL ;
    import java.nio.charset.StandardCharsets ;
/**
 *
 * @author Personal
 */
public class PolindromoApi {

   
        public static boolean isPalindromo(String phrase) {
            try {
                String apiUrl = "https://api.stackexchange.com/2.3/search?order=desc&sort=activity&intitle="
                        + phrase.replace(" ", "%20");
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + 
                            conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader
        ((conn.getInputStream()), StandardCharsets.UTF_8));
                StringBuilder responseBuilder = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    responseBuilder.append(output);
                }
                conn.disconnect();

                JSONObject jsonObject = new JSONObject(responseBuilder.toString());
                JSONArray itemsArray = jsonObject.getJSONArray("items");

                return itemsArray.length() > 0; 
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    

}
