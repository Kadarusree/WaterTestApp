package ramakrishna.watertest_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class TDS_Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_tds__options);
    }

    public void what_is_tds(View v) {
        startActivity(new Intent(getApplicationContext(), TDS_Description.class));
    }

    public void start_tds_test(View v) {
        startActivity(new Intent(getApplicationContext(), TDS_Test_Activity.class));

    }
}
