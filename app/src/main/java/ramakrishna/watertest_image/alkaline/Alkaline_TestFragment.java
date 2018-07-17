package ramakrishna.watertest_image.alkaline;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import ramakrishna.watertest_image.Fluoride_Test_Result;
import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.adapters.ViewPagerAdapter;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class Alkaline_TestFragment extends Fragment {

    // /// For View pager /////////////

    Dialog dialog_ViewPage;
    ViewPager viewPager;
    PagerAdapter adapter;
    int[] images;
    String[] names;
    LinearLayout llDots;
    Button btn;

    int pos = 0;
    Handler myHanldler = new Handler();
    MediaPlayer mediaPlayer;
    // ///End for view pager //////////////

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ph_viewpager, null);


        getDialogForViewPager(v);
        return v;
    }


    protected void getDialogForViewPager(View v) {
        pos = 0;
        playAudio(0);
        dialog_ViewPage = new Dialog(getActivity());
        dialog_ViewPage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_ViewPage.setContentView(R.layout.ph_viewpager);
        dialog_ViewPage.setTitle("Steps to connect...");
        dialog_ViewPage.setCancelable(false);

        TextView connect = (TextView) v.findViewById(R.id.btn_restart);
        final TextView help = (TextView) v.findViewById(R.id.btn_ph_complete);
        final LinearLayout parent_layout = (LinearLayout) v.findViewById(R.id.parent_layout);
        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getActivity().finish();
            }
        });


        llDots = (LinearLayout) v.findViewById(R.id.llDots);
        viewPager = (ViewPager) v.findViewById(R.id.pager);

        images = new int[]{R.drawable.ph_1, R.drawable.alk_2, R.drawable.alk_3, R.drawable.alk_3
        };

        names = new String[]{getResources().getString(R.string.alk_1),
                getResources().getString(R.string.alk_2),
                getResources().getString(R.string.alk_3),
                getResources().getString(R.string.alk_4)
        };

        adapter = new ViewPagerAdapter(getActivity(), images, names);
        viewPager.setAdapter(adapter);

        //getSlide();

        for (int i = 0; i < adapter.getCount(); i++) {
            // Toast.makeText(act, "toast:"+i, 2000).show();
            ImageButton imgDot = new ImageButton(getActivity());
            imgDot.setTag(i);
            imgDot.setImageResource(R.drawable.dot_selector);
            imgDot.setBackgroundResource(0);
            imgDot.setPadding(10, 10, 10, 10);
            imgDot.setPaddingRelative(5, 5, 5, 5);
            LayoutParams params = new LayoutParams(20, 20);
            imgDot.setLayoutParams(params);

            if (i == 0)
                imgDot.setSelected(true);

            llDots.addView(imgDot);
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int posi) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (i != posi) {
                        ((ImageView) llDots.findViewWithTag(i)).setSelected(false);
                    }
                }
                ((ImageView) llDots.findViewWithTag(posi)).setSelected(true);

                playAudio(posi);

                if (posi == (names.length - 1)) {
                    help.setVisibility(View.VISIBLE);
                } else {
                    help.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                pos = arg0;
                if (pos == 3) {
                    showDialog();
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
//startActivity(new Intent(getActivity(),SpeedViewAct.class));

                if (pos < images.length) {
                    pos++;

                    if (pos == 3) {
                        // showDialog();
                        playAudio(3);
                    }
                    getSlide();

                }
            }
        });
        // dialog_ViewPage.show();
    }

    private void getSlide() {
        viewPager.setCurrentItem(pos);
        //  myHanldler.postDelayed(run, 3000);
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            if (pos < images.length) {
                getSlide();
                pos++;
            }
        }
    };


    public void playAudio(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        switch (position) {

            case 0:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.alk_1);
                mediaPlayer.start();
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.alk_2);
                mediaPlayer.start();
                break;
            case 2:

                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.alk_3);
                mediaPlayer.start();
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.alk_4);
                mediaPlayer.start();
                break;
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(new ContextThemeWrapper(getActivity(), R.style.DialogSlideAnim));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_value);


        Button mButton = (Button) dialog.findViewById(R.id.mSubmit);
        final EditText mEditText = (EditText) dialog.findViewById(R.id.mValue);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "Enter Drop Count", Toast.LENGTH_SHORT).show();
                } else {
                    int drops = Integer.parseInt(mEditText.getText().toString());
                    int value = drops * 5;
                    if (value < 200) {
                        playResult(value, R.raw.alk_5, "#F3DA35");
                        showRDialog(value + "", getActivity().getResources().getString(R.string.alk_under_200));


                    } else if (value >= 200) {
                        playResult(value, R.raw.alk_6, "#F13F37");
                        showRDialog(value + "", getActivity().getResources().getString(R.string.alk_above_200));


                    }

                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

    public void playResult(int value, int id, String color) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(getActivity(), id);
        Result mResult = new Result(1, Constants.ALKALINE_TEST, "ALKALINITY", value + "", "PPM", color, "");
        DatabaseHelper mHelper = new DatabaseHelper(getActivity());
        mHelper.insertResult(mResult);

        mediaPlayer.start();

    }


    public void showRDialog(String value, String text) {
        final Dialog d = new Dialog(getActivity());
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);

        TextView tv = (TextView) d.findViewById(R.id.tv_output_text);

        tv.setText(getResources().getString(R.string.alk_res_text) + " " + value + "PPM\n\n" + text);

        Button ok = (Button) d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
                mediaPlayer.release();
            }
        });
        d.show();
    }

}
