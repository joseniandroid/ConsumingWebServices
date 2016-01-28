package sidespell.tech.webservices.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import sidespell.tech.webservices.R;

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
}
