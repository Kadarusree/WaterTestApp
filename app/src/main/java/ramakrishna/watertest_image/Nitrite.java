package ramakrishna.watertest_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Nitrite extends AppCompatActivity {


    TextView mWhat_is_nitrite, mStartNitrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_nitrite);

        mWhat_is_nitrite=(TextView)findViewById(R.id.btn_what_is_nitrite);
        mStartNitrite=(TextView)findViewById(R.id.btn_start_nitrite_test);

        mWhat_is_nitrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nitrite_Description.class));
            }
        });

        mStartNitrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Nitrite_Test.class));

            }
        });
    }
}
