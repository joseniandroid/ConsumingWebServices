package sidespell.tech.webservices.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sidespell.tech.webservices.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SimpleWeatherFragment extends Fragment {

    public static SimpleWeatherFragment newInstance() {
        return new SimpleWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }
}
