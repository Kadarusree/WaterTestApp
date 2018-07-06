package ramakrishna.watertest_image.tds;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
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

import ramakrishna.watertest_image.PH_Test_Result;
import ramakrishna.watertest_image.R;
import ramakrishna.watertest_image.adapters.ViewPagerAdapter;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;

/**
 * Created by srikanthk on 4/28/2018.
 */

public class TDS_TestFragment extends Fragment {

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
    TextView testComplete;
    // ///End for view pager //////////////

   MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ph_viewpager, null);
        testComplete = (TextView) v.findViewById(R.id.btn_ph_complete);
        testComplete.setVisibility(View.INVISIBLE);


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

                getActivity().onBackPressed();

            }
        });


        llDots = (LinearLayout) v.findViewById(R.id.llDots);
        viewPager = (ViewPager) v.findViewById(R.id.pager);

        images = new int[]{R.drawable.tds_1, R.drawable.tds_2, R.drawable.tds_3, R.drawable.tds_3
        };

        names = new String[]{getResources().getString(R.string.tds_1),
                getResources().getString(R.string.tds_2),
                getResources().getString(R.string.tds_3),
                getResources().getString(R.string.tds_4)


        };

        adapter = new ViewPagerAdapter(getActivity(), images, names);
        viewPager.setAdapter(adapter);

     //   getSlide();

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
                if (posi==(names.length-1)){
                    help.setVisibility(View.VISIBLE);
                }
                else {
                    help.setVisibility(View.INVISIBLE);

                }
                if (posi==3){
                    showDialog();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                pos = arg0;
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (pos < images.length) {
                    pos++;
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


    public void showDialog() {
        final Dialog dialog = new Dialog(new ContextThemeWrapper(getActivity(), R.style.DialogSlideAnim));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_value);


        Button mButton = (Button)dialog. findViewById(R.id.mSubmit);
        final EditText mEditText = (EditText)dialog.findViewById(R.id.mValue) ;
        int maxLength = 4;
        mEditText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                playResult(Integer.parseInt(mEditText.getText().toString()));
            }
        });

        dialog.show();
    }

    public void playAudio(int position){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        switch (position){

            case 0:

                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_1);
                mediaPlayer.start();
                break;
            case 1:
                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_2);
                mediaPlayer.start();
                break;
            case 2:

                mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_3);
                mediaPlayer.start();
                break;
                case 3:

                    mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_4);
                    mediaPlayer.start();
                break;
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
    }


    public void playResult(int value){
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        if (value<=299){
            mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_5);
            Result mResult = new Result(1, Constants.TDS_TEST,"TDS",value+"","mg/l","#F3DA35","");
            DatabaseHelper mHelper = new DatabaseHelper(getActivity());
            mHelper.insertResult(mResult);
            show_Dialog(value+"",getActivity().getResources().getString(R.string.tds_below_300));
        }
        else if(value>=300&&value<600){
            mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_6);
            Result mResult = new Result(1, Constants.TDS_TEST,"TDS",value+"","mg/l","#F3DA35","");
            DatabaseHelper mHelper = new DatabaseHelper(getActivity());
            mHelper.insertResult(mResult);
            show_Dialog(value+"",getActivity().getResources().getString(R.string.tds_300_600));

        }
        else if(value>=600&&value<900){
            mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_7);
            Result mResult = new Result(1, Constants.TDS_TEST,"TDS",value+"","mg/l","#F3DA35","");
            DatabaseHelper mHelper = new DatabaseHelper(getActivity());
            mHelper.insertResult(mResult);
            show_Dialog(value+"",getActivity().getResources().getString(R.string.tds_600_900));

        }
        else if(value>=900&&value<1200){
            mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_8);
            Result mResult = new Result(1, Constants.TDS_TEST,"TDS",value+"","mg/l","#F13F37","");
            DatabaseHelper mHelper = new DatabaseHelper(getActivity());
            mHelper.insertResult(mResult);
            show_Dialog(value+"",getActivity().getResources().getString(R.string.tds_900_1200));

        }
        else if(value>=1200){
            mediaPlayer= MediaPlayer.create(getActivity(),R.raw.tds_9);
            Result mResult = new Result(1, Constants.TDS_TEST,"TDS",value+"","mg/l","#F13F37","");
            DatabaseHelper mHelper = new DatabaseHelper(getActivity());
            mHelper.insertResult(mResult);
            show_Dialog(value+"",getActivity().getResources().getString(R.string.tds_above_1200));

        }
        mediaPlayer.start();

    }
    public void show_Dialog(String value, String text) {
        final Dialog d = new Dialog(getActivity());
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);

        TextView tv = (TextView) d.findViewById(R.id.tv_output_text);

        tv.setText("The TDS content of this water sample is " + value + "\n\n" + text);

        tv.setVisibility(View.GONE);

        Button ok = (Button) d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();
    }



}

