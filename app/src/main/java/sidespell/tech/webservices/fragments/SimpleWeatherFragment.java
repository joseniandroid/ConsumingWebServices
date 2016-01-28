package sidespell.tech.webservices.fragments;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import sidespell.tech.webservices.BuildConfig;
import sidespell.tech.webservices.R;
import sidespell.tech.webservices.apis.WeatherApi;
import sidespell.tech.webservices.entities.Weather;

/**
 * A placeholder fragment containing a simple view.
 */
public class SimpleWeatherFragment extends Fragment {

    private TextView    mTvLocation;
    private ImageView   mImgIcon;
    private TextView    mTvTemp;
    private TextView    mTvDetails;
    private ProgressBar mProgressBar;
    private TextView    mTvError;

    public static SimpleWeatherFragment newInstance() {
        return new SimpleWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Find all views
        mTvLocation = (TextView) view.findViewById(R.id.tvLocation);
        mImgIcon = (ImageView) view.findViewById(R.id.imgWeatherIcon);
        mTvTemp = (TextView) view.findViewById(R.id.tvCurrentTemp);
        mTvDetails = (TextView) view.findViewById(R.id.tvDetails);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mTvError = (TextView) view.findViewById(android.R.id.empty);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask();
        fetchWeatherTask.execute("Cebu");
    }

    /**
     * A background running task that retrieves weather data.
     */
    public class FetchWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mTvError.setVisibility(View.GONE);
            mTvLocation.setVisibility(View.GONE);
            mTvTemp.setVisibility(View.GONE);
            mTvDetails.setVisibility(View.GONE);
        }

        @Override
        protected Weather doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String city = params[0];

            Uri builtUri = Uri.parse(WeatherApi.BASE_URL).buildUpon()
                    .appendQueryParameter(WeatherApi.PARAM_QUERY, city)
                    .appendQueryParameter(WeatherApi.PARAM_MODE, "json")
                    .appendQueryParameter(WeatherApi.PARAM_UNITS, "metric")
                    .appendQueryParameter(WeatherApi.PARAM_API_KEY,
                            BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                    .build();

            return WeatherApi.getWeather(builtUri, "GET");
        }

        @Override
        protected void onPostExecute(Weather w) {
            if (w == null) {
                // TODO: Set empty view
                mTvError.setVisibility(View.VISIBLE);
                mTvLocation.setVisibility(View.GONE);
                mTvTemp.setVisibility(View.GONE);
                mTvDetails.setVisibility(View.GONE);
                return;
            }

            mProgressBar.setVisibility(View.GONE);
            mTvError.setVisibility(View.GONE);
            mTvLocation.setVisibility(View.VISIBLE);
            mTvTemp.setVisibility(View.VISIBLE);
            mTvDetails.setVisibility(View.VISIBLE);

            String locFormat = getString(R.string.weather_location_format);
            String detailsFormat = getString(R.string.details_format);
            String tempFormat = getString(R.string.temperature_format);

            mTvLocation.setText(String.format(locFormat, w.getCity(), w.getCountry()));
            mImgIcon.setImageBitmap(w.getWeatherIcon());
            mTvTemp.setText(String.format(tempFormat, w.getTemperature()));
            mTvDetails.setText(String.format(detailsFormat,
                    w.getDescription(),
                    w.getHumidity(),
                    w.getPressure()));
        }
    }
}
