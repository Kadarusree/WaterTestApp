package ramakrishna.watertest_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class PH_Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_ph__options);
    }

    public void what_is_ph(View v) {
        startActivity(new Intent(getApplicationContext(), PH_Description.class));
    }

    public void start_ph_test(View v) {
        startActivity(new Intent(getApplicationContext(), PH_Test_Activity.class));

    }
}
