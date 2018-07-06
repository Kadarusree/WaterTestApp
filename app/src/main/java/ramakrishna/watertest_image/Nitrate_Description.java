package ramakrishna.watertest_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Nitrate_Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_nitrate__description);
    }

    public void nitrate_test(View v) {
        startActivity(new Intent(getApplicationContext(), Nitrate_Test.class));
    }
}
