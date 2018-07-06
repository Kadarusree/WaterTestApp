package ramakrishna.watertest_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import ramakrishna.watertest_image.nitrate.NITRATE_MainActivity;
import ramakrishna.watertest_image.nitrite.NITRITE_MainActivity;

public class Nitrate_Nitrite extends AppCompatActivity {

    TextView nitrate, nitrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_nitrate__nitrite);
        nitrate = (TextView) findViewById(R.id.Nitrate);
        nitrite = (TextView) findViewById(R.id.Nitrite);


        nitrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NITRATE_MainActivity.class));
            }
        });

        nitrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NITRITE_MainActivity.class));

            }
        });
    }
}
