package ramakrishna.watertest_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Fluoride_Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_fluoride__options);
    }

    public void what_is_fluoride(View v) {
        startActivity(new Intent(getApplicationContext(), Fluoride_Description.class));
    }

    public void start_fluoride_test(View v) {
        startActivity(new Intent(getApplicationContext(), Fluoride_Test_Activity.class));

    }
}
