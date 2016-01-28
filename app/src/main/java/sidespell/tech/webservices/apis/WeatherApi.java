package sidespell.tech.webservices.apis;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sidespell.tech.webservices.entities.Weather;
import sidespell.tech.webservices.utils.HttpUtils;

/**
 * A service class that manages weather data sent and retrieved from the server.
 */
public class WeatherApi {

    public static final String BASE_URL     = "http://api.openweathermap.org/data/2.5/weather";
    public static final String IMG_BASE_URL = "http://openweathermap.org/img/w/";

    public static final int SUCCESS_CODE = 200;

    public static final String PARAM_QUERY   = "q";
    public static final String PARAM_MODE    = "mode";
    public static final String PARAM_UNITS   = "units";
    public static final String PARAM_API_KEY = "appId";

    private static final String OWM_CODE        = "cod";
    private static final String OWM_CITY_NAME   = "name";
    private static final String OWM_WEATHER     = "weather";
    private static final String OWM_DESCRIPTION = "description";
    private static final String OWM_ICON        = "icon";
    private static final String OWM_MAIN        = "main";
    private static final String OWM_TEMPERATURE = "temp";
    private static final String OWM_PRESSURE    = "pressure";
    private static final String OWM_HUMIDITY    = "humidity";
    private static final String OWM_SYS         = "sys";
    private static final String OWM_COUNTRY     = "country";

    public static Weather getWeather(Uri uri, @NonNull String requestMethod) {
        String json = HttpUtils.getResponse(uri, requestMethod);

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        // Here we will now parse the json response and convert it into a Weather object.
        final String city;
        final String country;
        final String icon;
        final String description;
        final double temperature;
        final long humidity;
        final long pressure;

        try {
            // 1) Convert the json string response into an actual JSON Object
            JSONObject jsonObject = new JSONObject(json);

            // 2) Get the status code
            int statusCode = jsonObject.getInt(OWM_CODE);

            // 3) Check if the HTTP Status Code if it's valid or not
            if (statusCode == SUCCESS_CODE) {
                // 4) Retrieve the individual bits of data that we need for our Weather model.
                // 5) Get the city name from the base jsonObject
                city = jsonObject.getString(OWM_CITY_NAME);

                // 6) Get the country name from "sys" object
                JSONObject sys = jsonObject.getJSONObject(OWM_SYS);
                country = sys.getString(OWM_COUNTRY);

                // 7) Get temperature, humidity, and pressure under "main" object
                JSONObject main = jsonObject.getJSONObject(OWM_MAIN);
                temperature = main.getDouble(OWM_TEMPERATURE);
                humidity = main.getLong(OWM_HUMIDITY);
                pressure = main.getLong(OWM_PRESSURE);

                // 8) Get description and icon from "weather" object
                JSONArray weather = jsonObject.getJSONArray(OWM_WEATHER);
                JSONObject w0 = weather.getJSONObject(0);
                description = w0.getString(OWM_DESCRIPTION).toUpperCase();
                icon = w0.getString(OWM_ICON);

                // 9) If there's no issue up to this point, then we are now ready to create our
                // Weather model.
                Weather w = new Weather()
                        .setCity(city)
                        .setCountry(country)
                        .setIconSrc(icon)
                        .setDescription(description)
                        .setTemperature(temperature)
                        .setHumidity(humidity)
                        .setPressure(pressure);

                // 10) Get the actual weather icon
                Bitmap imageBitmap = HttpUtils.getImageBitmap(w.getIconSrc());
                w.setWeatherIcon(imageBitmap);

                // 11) Return our weather data
                return w;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
