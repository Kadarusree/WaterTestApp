package ramakrishna.watertest_image;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ramakrishna.watertest_image.utils.Constants;
import ramakrishna.watertest_image.utils.LongOperation;

public class MainActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Constants.audio_played = false;
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio_1);
        mPlayer.start();
        mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

            }
        });

        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                mPlayer.release();
                getLanguageDialog();

            }
        }, SPLASH_TIME_OUT);
    }

    public void getLanguageDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Please Select your preferred Language");
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);

        final RadioButton rd1 = (RadioButton) dialog.findViewById(R.id.rd_1);
        final RadioButton rd2 = (RadioButton) dialog.findViewById(R.id.rd_2);
        final RadioButton rd3 = (RadioButton) dialog.findViewById(R.id.rd_3);
        Button btn = (Button) dialog.findViewById(R.id.btn_1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = checkUser();
                Constants.user = user;


                if (rd1.isChecked()) {
                    String languageToLoad = "te"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());

                    if (user.equalsIgnoreCase("")) {
                        registerDialog();
                        dialog.dismiss();
                    } else {
                        finish();
                        startActivity(new Intent(getApplicationContext(), Home.class));

                    }
                } else if (rd2.isChecked()) {
                    String languageToLoad = "te"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(), "Hindi Version is Coming Soon...", Toast.LENGTH_LONG).show();
                } else if (rd3.isChecked()) {
                    String languageToLoad = "te"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(), "Telugu Version is Coming Soon...", Toast.LENGTH_LONG).show();
                }

            }
        });

        rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    rd2.setChecked(false);
                rd3.setChecked(false);

            }
        });
        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    rd1.setChecked(false);
                rd3.setChecked(false);
                String languageToLoad = "te"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            }
        });
        rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    rd1.setChecked(false);
                rd2.setChecked(false);
                String languageToLoad = "te"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            }
        });

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a1);
        mPlayer.start();
        // now that the dialog is set up, it's time to show it
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlayer.release();
    }


    public void registerDialog() {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.registerbox);
        final EditText mEditText = (EditText) d.findViewById(R.id.username);
        final EditText adress = (EditText) d.findViewById(R.id.adress);
        final EditText mSchoolName = (EditText) d.findViewById(R.id.schoolname);
        final EditText mPhoneNum = (EditText) d.findViewById(R.id.mobile_num);
        final EditText mEmail = (EditText) d.findViewById(R.id.email_reg);


        TextView sendEmail = (TextView) d.findViewById(R.id.btn_register);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEditText.getText().toString().trim().length() < 3
                        || adress.getText().toString().trim().length() < 3
                        || mSchoolName.getText().toString().trim().length() < 6
                        || !isValidEmail(mEmail.getText().toString().trim())
                        || mPhoneNum.getText().toString().trim().length() < 10) {
                    if (mEditText.getText().toString().trim().length() < 3) {
                        mEditText.setError("Minimum 3 Characters");
                    }
                    if (adress.getText().toString().trim().length() < 3) {
                        adress.setError("Minimum 3 Characters");
                    }
                    if (!isValidEmail(mEmail.getText().toString().trim()) ){
                        mEmail.setError("Enter vaild email");
                    }
                    if (mSchoolName.getText().toString().trim().length() < 6) {
                        mSchoolName.setError("Enter School Name");
                    }
                    if (mPhoneNum.getText().toString().trim().length() < 10) {
                        mPhoneNum.setError("Enter 10 digit mobile number");
                    }

                } else {
                    d.dismiss();
                    register(mEditText.getText().toString().trim(),
                            adress.getText().toString().trim(),mSchoolName.getText().toString().trim(),
                            mEmail.getText().toString().trim(),mPhoneNum.getText().toString().trim()
                           );
                }

            }
        });
        d.show();
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void register(String name, String adress, String school, String email, String phone) {
        SharedPreferences mSharedpref = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedpref.edit();
        mEditor.putString("name", name);
        mEditor.putString("adress", adress);
        mEditor.putString("school", school);
        mEditor.putString("email", email);
        mEditor.putString("phone", phone);

        mEditor.commit();


        LongOperation mOperation1 =  new LongOperation(email, "Chemistry lab water quality test kit", "Hi "+name+"\n\n"+"Weicome to Chemistry lab water quality test kit Application\n\nRegards\nSCI Team");
        mOperation1.execute();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LongOperation mOperation2 =  new LongOperation("firebase.srikanth@gmail.com", "New User Registered","Name : "+name+"\n"+"School Name : "+school+"\n"+"Address : "+adress+"\n"+"Email : "+email+"\n"+"Mobile No. : "+phone+"\n");
        mOperation2.execute();

        finish();
        startActivity(new Intent(getApplicationContext(), Home.class));

    }

    public String checkUser() {
        SharedPreferences mSharedpref = getSharedPreferences("User", MODE_PRIVATE);
        String name = mSharedpref.getString("name", "");
        return name;
    }

    public String adress() {
        SharedPreferences mSharedpref = getSharedPreferences("User", MODE_PRIVATE);
        String name = mSharedpref.getString("adress", "");
        return name;
    }

}
