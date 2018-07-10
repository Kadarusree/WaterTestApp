package ramakrishna.watertest_image.iron;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.ph.PH_ResultsLog;
import ramakrishna.watertest_image.utils.Constants;

/**
 * Created by srikanthk on 5/17/2018.
 */

public class IronMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActionBar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ph_activity_main);
        toolbar = getSupportActionBar();
        loadFragment(new Iron_TestFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        toolbar.setTitle("IRON TEST");
        toolbar.setElevation(0f);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_shop:
                toolbar.setTitle(getResources().getString(R.string.title_shop));
                loadFragment(new Iron_TestFragment());
                return true;
            case R.id.navigation_gifts:
                loadFragment(new Iron_HelpFragment());
                toolbar.setTitle(getResources().getString(R.string.title_gifts));
                return true;
           case R.id.navigation_cart:
               Constants.CURRENT_TEST = Constants.IRON_TEST;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}