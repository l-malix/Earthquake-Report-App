package ai.lmalix.earthquackeapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> arrayItem) {
        super(context, 0, arrayItem);
    }

    public String decFormatter(String dec){
        DecimalFormat formatter = new DecimalFormat("0.00");
        return formatter.format(Double.parseDouble(dec));
    }

    public int getBackgroundColor(String mag) {

        int color = 0;
        float magnitude = Float.parseFloat(mag);

        switch ((int) magnitude) {
            case 1:
                color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                color = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                color = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                color = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                color = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                color = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }

        return color;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);

        Earthquake currentItem = getItem(position);

        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        TextView localization = (TextView) convertView.findViewById(R.id.localization);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        int magnitudeColor = getBackgroundColor(currentItem.getMagnitude());

        magnitude.setText(decFormatter(currentItem.getMagnitude()));
        localization.setText(currentItem.getPlace());
        date.setText(currentItem.getDate());
        time.setText(currentItem.getTime());
        magnitudeCircle.setColor(magnitudeColor);

        return convertView;
    }
}
