package ai.lmalix.earthquackeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquackeActivity extends AppCompatActivity {

    public String timeConv(long time){
        Date dateObject = new Date(time);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }

    public  String heureConv(long time) {
        Date dateObject = new Date(time);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    public String placeAdapt(String place){
        String txtArray[] =place.split("of");
        return txtArray[1];
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquacke);

        String JSON_DATA_SAMPLE = "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1641664875000,\"url\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2021-12-01&endtime=2022-01-01&minmagnitude=4.5&limit=5\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.12.3\",\"limit\":5,\"offset\":1,\"count\":5},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":4.6,\"place\":\"24 km ESE of Manay, Philippines\",\"time\":1640974658734,\"updated\":1641066695040,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000g87b\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000g87b&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":326,\"net\":\"us\",\"code\":\"7000g87b\",\"ids\":\",us7000g87b,\",\"sources\":\",us,\",\"types\":\",origin,phase-data,\",\"nst\":null,\"dmin\":1.157,\"rms\":0.59,\"gap\":101,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 4.6 - 24 km ESE of Manay, Philippines\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[126.7437,7.1263,67.13]},\"id\":\"us7000g87b\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":9.5,\"place\":\"37 km N of Yurimaguas, Peru\",\"time\":1840971078830,\"updated\":1641312446040,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000g871\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000g871&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":312,\"net\":\"us\",\"code\":\"7000g871\",\"ids\":\",us7000g871,\",\"sources\":\",us,\",\"types\":\",origin,phase-data,\",\"nst\":null,\"dmin\":4.069,\"rms\":0.6,\"gap\":103,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 4.5 - 37 km N of Yurimaguas, Peru\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-76.1839,-5.566,110.42]},\"id\":\"us7000g871\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":1.9,\"place\":\"109 km W of Hihifo, Tonga\",\"time\":1640979774124,\"updated\":1640972819040,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000g870\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000g870&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":369,\"net\":\"us\",\"code\":\"7000g870\",\"ids\":\",us7000g870,\",\"sources\":\",us,\",\"types\":\",origin,phase-data,\",\"nst\":null,\"dmin\":3.611,\"rms\":1.07,\"gap\":38,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 4.9 - 109 km W of Hihifo, Tonga\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-174.8129,-16.034,280.24]},\"id\":\"us7000g870\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":5.5,\"place\":\"27 km NNE of Pucallpa, Peru\",\"time\":1640964930504,\"updated\":1640970424040,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000g86k\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000g86k&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":312,\"net\":\"us\",\"code\":\"7000g86k\",\"ids\":\",us7000g86k,\",\"sources\":\",us,\",\"types\":\",origin,phase-data,\",\"nst\":null,\"dmin\":1.804,\"rms\":1.04,\"gap\":79,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 4.5 - 27 km NNE of Pucallpa, Peru\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-74.4781,-8.1453,157.39]},\"id\":\"us7000g86k\"},\n" +
                "{\"type\":\"Feature\",\"properties\":{\"mag\":4.7,\"place\":\"73 km NNW of Phôngsali, Laos\",\"time\":1640962382458,\"updated\":1641015040040,\"tz\":null,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us7000g86b\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us7000g86b&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":340,\"net\":\"us\",\"code\":\"7000g86b\",\"ids\":\",us7000g86b,\",\"sources\":\",us,\",\"types\":\",origin,phase-data,\",\"nst\":null,\"dmin\":2.187,\"rms\":0.5,\"gap\":72,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 4.7 - 73 km NNW of Phôngsali, Laos\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[101.7114,22.2358,10]},\"id\":\"us7000g86b\"}],\"bbox\":[-174.8129,-16.034,10,126.7437,22.2358,280.24]}";

        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        try {
            JSONObject json_data = new JSONObject(JSON_DATA_SAMPLE);
            JSONArray features = json_data.getJSONArray("features");

            for (int i=0; i<features.length(); i++) {

                JSONObject feature = features.getJSONObject(i);
                JSONObject properties = feature.getJSONObject("properties");
                double mag = properties.optDouble("mag");
                String place = properties.optString("place");
                long time = properties.optLong("time");
                earthquakes.add(new Earthquake(""+mag, placeAdapt(place), ""+timeConv(time), heureConv(time)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(new EarthquakeAdapter(this, earthquakes));

    }
}