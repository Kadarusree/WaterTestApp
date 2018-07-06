package ramakrishna.watertest_image.ph;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.utils.Constants;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class PH_MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActionBar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ph_activity_main);
        toolbar = getSupportActionBar();
        loadFragment(new PH_TestFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        toolbar.setTitle("PH TEST");
        toolbar.setElevation(0f);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_shop:
                toolbar.setTitle(getResources().getString(R.string.title_shop));
                loadFragment(new PH_TestFragment());
                return true;
            case R.id.navigation_gifts:
                loadFragment(new PH_HelpFragment());
                toolbar.setTitle(getResources().getString(R.string.title_gifts));
                return true;
            case R.id.navigation_cart:
                Constants.CURRENT_TEST = Constants.PH_TEST;

                loadFragment(new PH_ResultsLog());
                toolbar.setTitle(getResources().getString(R.string.title_cart));
                return true;

        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

