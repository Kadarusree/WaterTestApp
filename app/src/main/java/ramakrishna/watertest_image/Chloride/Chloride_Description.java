package ramakrishna.watertest_image.Chloride;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ramakrishna.watertest_image.R;

public class Chloride_Description extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chloride__description);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ch_d);
        mediaPlayer.start();
    }

    public void chloride_test(View v){
        startActivity(new Intent(getApplicationContext(), ChlorideTest.class));
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
