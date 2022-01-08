package ai.lmalix.earthquackeapp;

public class Earthquake {

    private String magnitude;
    private String place;
    private String date;
    private String time;

    public Earthquake(String magnitude, String place, String date, String time) {
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
        this.time = time;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
