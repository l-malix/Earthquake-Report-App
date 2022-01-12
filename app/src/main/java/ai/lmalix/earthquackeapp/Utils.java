package ai.lmalix.earthquackeapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {

    /** Tag for the log messages */
    public static final String TAG = Utils.class.getSimpleName();

    /**
     * Return Url Object from String url
     */
    private static URL createUrl(String StringUrl) {
        URL url = null;
        try {
            url = new URL(StringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "createUrl: "+e.getMessage());
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // verify the validity of the Url
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "makeHttpRequest: " + urlConnection.getResponseCode());
            }
        }catch (IOException e){
                Log.e(TAG, "makeHttpRequest: "+ e.getMessage() );
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    /**
     * Reading a stream of data from connection
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line!=null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Extracting features from a json String
     */
    private static ArrayList<Earthquake> extractFeatureFromJson(String earthquakeJson){

        if(TextUtils.isEmpty(earthquakeJson)) {
            return null;
        }


        try {
            JSONObject json_data = new JSONObject(earthquakeJson);
            JSONArray features = json_data.getJSONArray("features");

            ArrayList<Earthquake> earthquakes = new ArrayList<>();
            for (int i=0; i<features.length(); i++) {

                JSONObject feature = features.getJSONObject(i);
                JSONObject properties = feature.getJSONObject("properties");
                double mag = properties.optDouble("mag");
                String place = properties.optString("place");
                long time = properties.optLong("time");
                String url = properties.optString("url");

                earthquakes.add(new Earthquake(""+mag, place, ""+timeConv(time), heureConv(time), url));
            }

            return earthquakes;

        } catch (JSONException e) {
            Log.e(TAG, "extractFeatureFromJson: "+e.getMessage());
        }

        return null;
    }

    /**
     * Convert millisecond time to universal MMM dd, yyyy format
     * @param time millisecond time
     * @return universal time format
     */
    private static String timeConv(long time){
        Date dateObject = new Date(time);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }

    /**
     * convert time into h:nn a format
     * @param time millisecond time
     * @return time format
     */
    private static  String heureConv(long time) {
        Date dateObject = new Date(time);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Place extraction
     * @param place place string
     * @return extracted place
     */
    private static String placeAdapt(String place){
        String[] txtArray =place.split("of");
        return txtArray[1];
    }

    /**
     * Fetching utl data from string URL
     * 1 / convert string url into URL object
     * 2 / make an http request from the server, stream the data and convert the response into string Json
     * 3 / extract features from the Json response and stock it into Array
     */
    public static ArrayList<Earthquake> fetchEarthquake(String urlString) {

        URL url = createUrl(urlString);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "fetchEarthquake: "+e.getMessage() );
        }

        ArrayList<Earthquake> earthquakes = extractFeatureFromJson(jsonResponse);

        return earthquakes;
    }
}
