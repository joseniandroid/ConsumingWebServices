package sidespell.tech.webservices.entities;

import android.graphics.Bitmap;

import sidespell.tech.webservices.apis.WeatherApi;

/**
 * Represents a weather object.
 */
public class Weather {

    private String mCity;
    private String mCountry;
    private String mIconSrc;
    private String mDescription;

    private double mTemperature;
    private long   mHumidity;
    private long   mPressure;

    private Bitmap mWeatherIcon;

    public String getCity() {
        return mCity;
    }

    public Weather setCity(String city) {
        mCity = city;
        return this;
    }

    public String getCountry() {
        return mCountry;
    }

    public Weather setCountry(String country) {
        mCountry = country;
        return this;
    }

    public String getIconSrc() {
        return mIconSrc;
    }

    public Weather setIconSrc(String iconSrc) {
        mIconSrc = WeatherApi.IMG_BASE_URL + iconSrc + ".png";
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public Weather setDescription(String description) {
        mDescription = description;
        return this;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public Weather setTemperature(double temperature) {
        mTemperature = temperature;
        return this;
    }

    public long getHumidity() {
        return mHumidity;
    }

    public Weather setHumidity(long humidity) {
        mHumidity = humidity;
        return this;
    }

    public long getPressure() {
        return mPressure;
    }

    public Weather setPressure(long pressure) {
        mPressure = pressure;
        return this;
    }

    public Bitmap getWeatherIcon() {
        return mWeatherIcon;
    }

    public Weather setWeatherIcon(Bitmap weatherIcon) {
        mWeatherIcon = weatherIcon;
        return this;
    }
}
