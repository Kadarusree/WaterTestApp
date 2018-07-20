package ramakrishna.watertest_image.hardness;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.ph.PH_ResultsLog;
import ramakrishna.watertest_image.utils.Constants;

public class HardnessPart2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActionBar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ph_activity_main);
        toolbar = getSupportActionBar();
        loadFragment(new Hardness_TestFragment2());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        toolbar.setTitle(getResources().getString(R.string.db_opt6));
        toolbar.setElevation(0f);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.navigation_shop:
                toolbar.setTitle(getResources().getString(R.string.title_shop));
                loadFragment(new Hardness_TestFragment2());
                return true;
            case R.id.navigation_gifts:
                loadFragment(new Hardness_HelpFragment());
                toolbar.setTitle(getResources().getString(R.string.title_gifts));
                return true;
            case R.id.navigation_cart:
                Constants.CURRENT_TEST = Constants.HARDNESSEST;
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
