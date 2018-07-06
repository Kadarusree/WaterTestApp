package ramakrishna.watertest_image;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ramakrishna.watertest_image.KNN.CosineSimilarity;
import ramakrishna.watertest_image.KNN.EuclideanDistance;
import ramakrishna.watertest_image.KNN.FileManager;
import ramakrishna.watertest_image.KNN.L1Distance;
import ramakrishna.watertest_image.KNN.Metric;
import ramakrishna.watertest_image.KNN.TestRecord;
import ramakrishna.watertest_image.KNN.TrainRecord;
import ramakrishna.watertest_image.database.DatabaseHelper;
import ramakrishna.watertest_image.database.Result;
import ramakrishna.watertest_image.utils.Constants;

public class PH_Test_Result_new extends AppCompatActivity {

    ImageView img;
    Button btn, gallery;
    TextView tv1, tv2, tv3;

    int r, g, b, color_number;
    File testFile;


    MediaPlayer mediaPlayer;
    Uri mImageCaptureUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph__test__result_new);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio_4);
        mediaPlayer.start();

        img=(ImageView)findViewById(R.id.img_output);
        btn=(Button)findViewById(R.id.btn_capture);
        gallery=(Button)findViewById(R.id.btn_gallery);

        tv1=(TextView)findViewById(R.id.tv_result_color1);
        tv2=(TextView)findViewById(R.id.tv_result_color2);
        tv3=(TextView)findViewById(R.id.tv_result_color3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 1);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent();
                i.setAction(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 2);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            File f = new File(Environment.getExternalStorageDirectory().toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f = temp;
                    break;
                }
            }

            Bitmap bitmap;
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

            bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                    bitmapOptions);

            img.setImageBitmap(bitmap);
            tv1.setBackgroundColor(getDominantColor1(bitmap));

            tv1.setText("Dominant Color in the Picture is : " + getDominantColor1(bitmap) + "");
            String num=getDominantColor1(bitmap)+"";
            num=num.replace("-","");
            color_number=Integer.parseInt(num);
            int_to_rgb(color_number);

            tv2.setBackgroundColor(getDominantColor2(bitmap));
            tv2.setText("Dominant Color2 in the Picture is : " + getDominantColor2(bitmap) + "");

            tv3.setBackgroundColor(getDominantColor3(bitmap));
            tv3.setText("Dominant Color3 in the Picture is : " + getDominantColor3(bitmap) + "");
            // tv1.setText("Dominant Color in the Picture is : "+getDominantColor1(bitmap)+"");

           /* getRGB(-getDominantColor1(bitmap));
            System.out.println(getDominantColor1(bitmap));*/


        }else if(requestCode==2){

            img.setImageURI(data.getData());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                tv1.setBackgroundColor(getDominantColor1(bitmap));
                tv1.setText(getDominantColor1(bitmap) +"---"+ ((~getDominantColor1(bitmap)) +1) + "");
                String num=getDominantColor1(bitmap)+"";
                num=num.replace("-","");
                color_number=Integer.parseInt(num);
                int_to_rgb(color_number);


                tv2.setBackgroundColor(getDominantColor2(bitmap));
                tv2.setText(getDominantColor2(bitmap) +"---"+ ((~getDominantColor2(bitmap)) +1) + "");

                tv3.setBackgroundColor(getDominantColor3(bitmap));
                tv3.setText("Dominant Color3 in the Picture is : " + ((~getDominantColor3(bitmap)) +1)+ "");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static int getDominantColor1(Bitmap bitmap) {
        List<Palette.Swatch> swatchesTemp = Palette.from(bitmap).generate().getSwatches();
        List<Palette.Swatch> swatches = new ArrayList<Palette.Swatch>(swatchesTemp);
        Collections.sort(swatches, new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch swatch1, Palette.Swatch swatch2) {
                return swatch2.getPopulation() - swatch1.getPopulation();
            }
        });
        return swatches.size() > 0 ? swatches.get(0).getRgb() : getRandomColor();
    }

    private static int getRandomColor() {
        return 0;
    }
    public static int getDominantColor2(Bitmap bitmap) {
        List<Palette.Swatch> swatchesTemp = Palette.from(bitmap).generate().getSwatches();
        List<Palette.Swatch> swatches = new ArrayList<Palette.Swatch>(swatchesTemp);
        Collections.sort(swatches, new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch swatch1, Palette.Swatch swatch2) {
                return swatch2.getPopulation() - swatch1.getPopulation();
            }
        });
        return swatches.size() > 1 ? swatches.get(1).getRgb() : getRandomColor();
    }

    public static int getDominantColor3(Bitmap bitmap) {
        List<Palette.Swatch> swatchesTemp = Palette.from(bitmap).generate().getSwatches();
        List<Palette.Swatch> swatches = new ArrayList<Palette.Swatch>(swatchesTemp);
        Collections.sort(swatches, new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch swatch1, Palette.Swatch swatch2) {
                return swatch2.getPopulation() - swatch1.getPopulation();
            }
        });
        return swatches.size() > 2 ? swatches.get(2).getRgb() : getRandomColor();
    }




    public void int_to_rgb(int originalNum){
        int compliment = ~originalNum+1;
        System.out.println(Integer.toHexString(compliment));
        b = compliment & 0xff;
        g = (compliment >> 8) & 0xff;
        r = (compliment >> 16) & 0xff;
        System.out.println(r+"--"+g+"--"+b);
        create_test_file(PH_Test_Result_new.this,"rgb_test.txt","1 3 1\n"+r+" "+g+" "+g+" 0");
    }

    public void create_test_file(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(),"Test");
            if (!root.exists()) {
                root.mkdirs();
            }
            testFile = new File(root, sFileName);
            FileWriter writer = new FileWriter(testFile);
            writer.append(sBody);
            writer.flush();
            writer.close();
           // Toast.makeText(context, "Test Saved", Toast.LENGTH_SHORT).show();

            create_train_file(PH_Test_Result_new.this,"rgb_train.txt",readTxt());


         //  knn(gpxfile.getAbsolutePath(),gpxfile.getAbsolutePath(),1,2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create_train_file(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(),"Test");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
         //   Toast.makeText(context, "Train Saved", Toast.LENGTH_SHORT).show();



            knn(gpxfile.getAbsolutePath(),testFile.getAbsolutePath(),1,2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////KNN CODE//////////////////////////////////////////

    public  void knn(String trainingFile, String testFile, int K, int metricType){
        //get the current time
        final long startTime = System.currentTimeMillis();

        // make sure the input arguments are legal
        if(K <= 0){
            System.out.println("K should be larger than 0!");
            return;
        }

        // metricType should be within [0,2];
        if(metricType > 2 || metricType <0){
            System.out.println("metricType is not within the range [0,2]. Please try again later");
            return;
        }

        //TrainingFile and testFile should be the same group
        String trainGroup = extractGroupName(trainingFile);
        String testGroup = extractGroupName(testFile);

        if(!trainGroup.equals(testGroup)){
            System.out.println("trainingFile and testFile are illegal!");
            return;
        }


        try {
            //read trainingSet and testingSet
            TrainRecord[] trainingSet =  FileManager.readTrainFile(trainingFile);
            TestRecord[] testingSet =  FileManager.readTestFile(testFile);
System.out.println("Testing Set Length"+testingSet.length+"-----");
            for (int i=0;i<testingSet.length;i++){
                System.out.println(testingSet[i].toString()+"-----"+i);
            }

            //determine the type of metric according to metricType
            Metric metric;
            if(metricType == 0)
                metric = new CosineSimilarity();
            else if(metricType == 1)
                metric = new L1Distance();
            else if (metricType == 2)
                metric = new EuclideanDistance();
            else{
                System.out.println("The entered metric_type is wrong!");
                return;
            }

            //test those TestRecords one by one
            int numOfTestingRecord = testingSet.length;
            for(int i = 0; i < numOfTestingRecord; i ++){
                TrainRecord[] neighbors = findKNearestNeighbors(trainingSet, testingSet[i], K, metric);
                int classLabel = classify(neighbors);
                testingSet[i].predictedLabel = classLabel; //assign the predicted label to TestRecord
            }

            //calculate the accuracy
            int correctPrediction = 0;
            for(int j = 0; j < numOfTestingRecord; j ++){
                if(testingSet[j].predictedLabel == testingSet[j].classLabel)
                    correctPrediction ++;
            }

            //Output a file containing predicted labels for TestRecords
            int result = FileManager.outputFile(testingSet, trainingFile);
            showOutput(result);
          //  System.out.println("The prediction file is stored in "+predictPath);
            System.out.println("The accuracy is "+((double)correctPrediction / numOfTestingRecord)*100+"%");

            //print the total execution time
            final long endTime = System.currentTimeMillis();
            System.out.println("Total excution time: "+(endTime - startTime) / (double)1000 +" seconds.");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Find K nearest neighbors of testRecord within trainingSet
    static TrainRecord[] findKNearestNeighbors(TrainRecord[] trainingSet, TestRecord testRecord,int K, Metric metric){
        int NumOfTrainingSet = trainingSet.length;
        assert K <= NumOfTrainingSet : "K is lager than the length of trainingSet!";

        //Update KNN: take the case when testRecord has multiple neighbors with the same distance into consideration
        //Solution: Update the size of container holding the neighbors
        TrainRecord[] neighbors = new TrainRecord[K];

        //initialization, put the first K trainRecords into the above arrayList
        int index;
        for(index = 0; index < K; index++){
            System.out.println(testRecord+"-----");
            trainingSet[index].distance = metric.getDistance(trainingSet[index], testRecord);
            neighbors[index] = trainingSet[index];
        }

        //go through the remaining records in the trainingSet to find K nearest neighbors
        for(index = K; index < NumOfTrainingSet; index ++){
            trainingSet[index].distance = metric.getDistance(trainingSet[index], testRecord);

            //get the index of the neighbor with the largest distance to testRecord
            int maxIndex = 0;
            for(int i = 1; i < K; i ++){
                if(neighbors[i].distance > neighbors[maxIndex].distance)
                    maxIndex = i;
            }

            //add the current trainingSet[index] into neighbors if applicable
            if(neighbors[maxIndex].distance > trainingSet[index].distance)
                neighbors[maxIndex] = trainingSet[index];
        }

        return neighbors;
    }

    // Get the class label by using neighbors
    static int classify(TrainRecord[] neighbors){
        //construct a HashMap to store <classLabel, weight>
        HashMap<Integer, Double> map = new HashMap<Integer, Double>();
        int num = neighbors.length;

        for(int index = 0;index < num; index ++){
            TrainRecord temp = neighbors[index];
            int key = temp.classLabel;

            //if this classLabel does not exist in the HashMap, put <key, 1/(temp.distance)> into the HashMap
            if(!map.containsKey(key))
                map.put(key, 1 / temp.distance);

                //else, update the HashMap by adding the weight associating with that key
            else{
                double value = map.get(key);
                value += 1 / temp.distance;
                map.put(key, value);
            }
        }

        //Find the most likely label
        double maxSimilarity = 0;
        int returnLabel = -1;
        Set<Integer> labelSet = map.keySet();
        Iterator<Integer> it = labelSet.iterator();

        //go through the HashMap by using keys
        //and find the key with the highest weights
        while(it.hasNext()){
            int label = it.next();
            double value = map.get(label);
            if(value > maxSimilarity){
                maxSimilarity = value;
                returnLabel = label;
            }
        }

        return returnLabel;
    }

    static String extractGroupName(String filePath){
        StringBuilder groupName = new StringBuilder();
        for(int i = 15; i < filePath.length(); i ++){
            if(filePath.charAt(i) != '_')
                groupName.append(filePath.charAt(i));
            else
                break;
        }

        return groupName.toString();
    }


    private String readTxt(){

        InputStream inputStream = getResources().openRawResource(R.raw.rgb_train);
//     InputStream inputStream = getResources().openRawResource(R.raw.internals);
        System.out.println(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }



    public void showOutput(int result){
        switch (result){
            case 0:
                playMedia(R.raw.audio_5);
                Store_in_Pref(result+"","ACIDIC","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_low));
                break;
            case 1:
                playMedia(R.raw.audio_5);
                Store_in_Pref(result+"","ACIDIC","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_low));
                break;
            case 2:
                playMedia(R.raw.audio_5);
                Store_in_Pref(result+"","ACIDIC","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_low));
                break;
            case 3:
                playMedia(R.raw.audio_5);
                Store_in_Pref(result+"","ACIDIC","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_low));
                break;
            case 4:
                playMedia(R.raw.audio_5);
                Store_in_Pref(result+"","ACIDIC","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_low));
                break;
            case 5:
                playMedia(R.raw.audio_5);
                Store_in_Pref(result+"","ACIDIC","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_low));
                break;
            case 6:
                playMedia(R.raw.audio_5);
                Store_in_Pref(result+"","NEUTRAL","#F3DA35");
                show_Dialog(result+"",getResources().getString(R.string.ph_normal));
                break;
            case 7:
                playMedia(R.raw.audio_6);
                Store_in_Pref(result+"","NEUTRAL","#F3DA35");
                show_Dialog(result+"",getResources().getString(R.string.ph_normal));
                break;
            case 8:
                playMedia(R.raw.audio_6);
                Store_in_Pref(result+"","NEUTRAL","#F3DA35");
                show_Dialog(result+"",getResources().getString(R.string.ph_normal));
                break;
            case 9:
                playMedia(R.raw.audio_6);
                Store_in_Pref(result+"","NEUTRAL","#F3DA35");
                show_Dialog(result+"",getResources().getString(R.string.ph_normal));
                break;
            case 10:
                playMedia(R.raw.audio_7);
                Store_in_Pref(result+"","ALKALINE","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_high));
                break;
            case 11:
                playMedia(R.raw.audio_7);
                Store_in_Pref(result+"","ALKALINE","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_high));
                break;
            case 12:
                playMedia(R.raw.audio_7);
                Store_in_Pref(result+"","ALKALINE","#F13F37");
                show_Dialog(result+"",getResources().getString(R.string.ph_high));
                break;
        }
    }

    public void playMedia(int tone) {
        if (mediaPlayer != null || mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), tone);
            mediaPlayer.start();

        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    public void Store_in_Pref(String value, String range, String color){
        SharedPreferences mSharedPreferences=getSharedPreferences("WATER_TEST", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferences.edit();
        mEditor.putString("ph_value",value);
        mEditor.putString("ph_range",range);
        mEditor.putString("ph_color_code",color);


        Result mResult = new Result(1, Constants.PH_TEST,"PH",value,"mg/dl",color,"");
        DatabaseHelper mHelper = new DatabaseHelper(getApplicationContext());
        mHelper.insertResult(mResult);

        mEditor.commit();
    }

    public void show_Dialog(String value, String text){
        final Dialog d=new Dialog(PH_Test_Result_new.this);
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.dialog_output);

        TextView tv=(TextView)d.findViewById(R.id.tv_output_text);

        tv.setText("The pH value of this water sample is "+value+"\n\n"+text);

        Button ok=(Button)d.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();
    }
}


