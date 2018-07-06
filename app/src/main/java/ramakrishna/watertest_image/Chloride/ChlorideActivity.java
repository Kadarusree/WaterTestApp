package ramakrishna.watertest_image.Chloride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ramakrishna.watertest_image.R;

public class ChlorideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chloride);
    }

    public void what_is_ch(View v) {
        startActivity(new Intent(getApplicationContext(), Chloride_Description.class));
    }

    public void start_ch_test(View v) {
        startActivity(new Intent(getApplicationContext(), ChlorideTest.class));

    }
}
