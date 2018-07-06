package ramakrishna.watertest_image.nitrite;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ramakrishna.watertest_image.R;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class NITRITE_HelpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_nitrite__description,null);

        return v;
    }
}
