package ramakrishna.watertest_image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main2Activity extends AppCompatActivity {

    ImageView img;
    Button btn, cam;
   static TextView tv1,tv2;


    String path;
    File f;

    ArrayList<String> colorList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        img=(ImageView)findViewById(R.id.img);
        btn=(Button)findViewById(R.id.btn98);

        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);

        cam=(Button)findViewById(R.id.cam);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 2);
            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 1);
            }
        });


        colorList.add("176 23 31");
        colorList.add("220 20 60");
        colorList.add("255 182 193");
        colorList.add("255 174 185");
        colorList.add("238 162 173");
        colorList.add("205 140 149");
        colorList.add("139 95 101");
        colorList.add("255 192 203");
        colorList.add("255 181 197");
        colorList.add("238 169 184");
        colorList.add("205 145 158");
        colorList.add("139 99 108");
        colorList.add("219 112 147");
        colorList.add("255 130 171");
        colorList.add("238 121 159");
        colorList.add("205 104 137");
        colorList.add("139 71 93");
        colorList.add("255 240 245");
        colorList.add("238 224 229");
        colorList.add("205 193 197");
        colorList.add("139 131 134");
        colorList.add("255 62 150");
        colorList.add("238 58 140");
        colorList.add("205 50 120");
        colorList.add("139 34 82");
        colorList.add("255 105 180");
        colorList.add("255 110 180");
        colorList.add("238 106 167");
        colorList.add("205 96 144");
        colorList.add("139 58 98");
        colorList.add("135 38 87");
        colorList.add("255 20 147");
        colorList.add("238 18 137");
        colorList.add("205 16 118");
        colorList.add("139 10 80");
        colorList.add("255 52 179");
        colorList.add("238 48 167");
        colorList.add("205 41 144");
        colorList.add("139 28 98");
        colorList.add("199 21 133");
        colorList.add("208 32 144");
        colorList.add("218 112 214");
        colorList.add("255 131 250");
        colorList.add("238 122 233");
        colorList.add("205 105 201");
        colorList.add("139 71 137");
        colorList.add("216 191 216");
        colorList.add("255 225 255");
        colorList.add("238 210 238");
        colorList.add("205 181 205");
        colorList.add("139 123 139");
        colorList.add("255 187 255");
        colorList.add("238 174 238");
        colorList.add("205 150 205");
        colorList.add("139 102 139");
        colorList.add("221 160 221");
        colorList.add("238 130 238");
        colorList.add("255 0 255");
        colorList.add("238 0 238");
        colorList.add("205 0 205");
        colorList.add("139 0 139");
        colorList.add("128 0 128");
        colorList.add("186 85 211");
        colorList.add("224 102 255");
        colorList.add("209 95 238");
        colorList.add("180 82 205");
        colorList.add("122 55 139");
        colorList.add("148 0 211");
        colorList.add("153 50 204");
        colorList.add("191 62 255");
        colorList.add("178 58 238");
        colorList.add("154 50 205");
        colorList.add("104 34 139");
        colorList.add("75 0 130");
        colorList.add("138 43 226");
        colorList.add("155 48 255");
        colorList.add("145 44 238");
        colorList.add("125 38 205");
        colorList.add("85 26 139");
        colorList.add("147 112 219");
        colorList.add("171 130 255");
        colorList.add("159 121 238");
        colorList.add("137 104 205");
        colorList.add("93 71 139");
        colorList.add("72 61 139");
        colorList.add("132 112 255");
        colorList.add("123 104 238");
        colorList.add("106 90 205");
        colorList.add("131 111 255");
        colorList.add("122 103 238");
        colorList.add("105 89 205");
        colorList.add("71 60 139");
        colorList.add("248 248 255");
        colorList.add("230 230 250");
        colorList.add("0 0 255");
        colorList.add("0 0 238");
        colorList.add("0 0 205");
        colorList.add("0 0 139");
        colorList.add("0 0 128");
        colorList.add("25 25 112");
        colorList.add("61 89 171");
        colorList.add("65 105 225");
        colorList.add("72 118 255");
        colorList.add("67 110 238");
        colorList.add("58 95 205");
        colorList.add("39 64 139");
        colorList.add("100 149 237");
        colorList.add("176 196 222");
        colorList.add("202 225 255");
        colorList.add("188 210 238");
        colorList.add("162 181 205");
        colorList.add("110 123 139");
        colorList.add("119 136 153");
        colorList.add("112 128 144");
        colorList.add("198 226 255");
        colorList.add("185 211 238");
        colorList.add("159 182 205");
        colorList.add("108 123 139");
        colorList.add("30 144 255");
        colorList.add("28 134 238");
        colorList.add("24 116 205");
        colorList.add("16 78 139");
        colorList.add("240 248 255");
        colorList.add("70 130 180");
        colorList.add("99 184 255");
        colorList.add("92 172 238");
        colorList.add("79 148 205");
        colorList.add("54 100 139");
        colorList.add("135 206 250");
        colorList.add("176 226 255");
        colorList.add("164 211 238");
        colorList.add("141 182 205");
        colorList.add("96 123 139");
        colorList.add("135 206 255");
        colorList.add("126 192 238");
        colorList.add("108 166 205");
        colorList.add("74 112 139");
        colorList.add("135 206 235");
        colorList.add("0 191 255");
        colorList.add("0 178 238");
        colorList.add("0 154 205");
        colorList.add("0 104 139");
        colorList.add("51 161 201");
        colorList.add("173 216 230");
        colorList.add("191 239 255");
        colorList.add("178 223 238");
        colorList.add("154 192 205");
        colorList.add("104 131 139");
        colorList.add("176 224 230");
        colorList.add("152 245 255");
        colorList.add("142 229 238");
        colorList.add("122 197 205");
        colorList.add("83 134 139");
        colorList.add("0 245 255");
        colorList.add("0 229 238");
        colorList.add("0 197 205");
        colorList.add("0 134 139");
        colorList.add("95 158 160");
        colorList.add("0 206 209");
        colorList.add("240 255 255");
        colorList.add("224 238 238");
        colorList.add("193 205 205");
        colorList.add("131 139 139");
        colorList.add("224 255 255");
        colorList.add("209 238 238");
        colorList.add("180 205 205");
        colorList.add("122 139 139");
        colorList.add("187 255 255");
        colorList.add("174 238 238");
        colorList.add("150 205 205");
        colorList.add("102 139 139");
        colorList.add("47 79 79");
        colorList.add("151 255 255");
        colorList.add("141 238 238");
        colorList.add("121 205 205");
        colorList.add("82 139 139");
        colorList.add("0 255 255");
        colorList.add("0 238 238");
        colorList.add("0 205 205");
        colorList.add("0 139 139");
        colorList.add("0 128 128");
        colorList.add("72 209 204");
        colorList.add("32 178 170");
        colorList.add("3 168 158");
        colorList.add("64 224 208");
        colorList.add("128 138 135");
        colorList.add("0 199 140");
        colorList.add("127 255 212");
        colorList.add("118 238 198");
        colorList.add("102 205 170");
        colorList.add("69 139 116");
        colorList.add("0 250 154");
        colorList.add("245 255 250");
        colorList.add("0 255 127");
        colorList.add("0 238 118");
        colorList.add("0 205 102");
        colorList.add("0 139 69");
        colorList.add("60 179 113");
        colorList.add("84 255 159");
        colorList.add("78 238 148");
        colorList.add("67 205 128");
        colorList.add("46 139 87");
        colorList.add("0 201 87");
        colorList.add("189 252 201");
        colorList.add("61 145 64");
        colorList.add("240 255 240");
        colorList.add("224 238 224");
        colorList.add("193 205 193");
        colorList.add("131 139 131");
        colorList.add("143 188 143");
        colorList.add("193 255 193");
        colorList.add("180 238 180");
        colorList.add("155 205 155");
        colorList.add("105 139 105");
        colorList.add("152 251 152");
        colorList.add("154 255 154");
        colorList.add("144 238 144");
        colorList.add("124 205 124");
        colorList.add("84 139 84");
        colorList.add("50 205 50");
        colorList.add("34 139 34");
        colorList.add("0 255 0");
        colorList.add("0 238 0");
        colorList.add("0 205 0");
        colorList.add("0 139 0");
        colorList.add("0 128 0");
        colorList.add("0 100 0");
        colorList.add("48 128 20");
        colorList.add("124 252 0");
        colorList.add("127 255 0");
        colorList.add("118 238 0");
        colorList.add("102 205 0");
        colorList.add("69 139 0");
        colorList.add("173 255 47");
        colorList.add("202 255 112");
        colorList.add("188 238 104");
        colorList.add("162 205 90");
        colorList.add("110 139 61");
        colorList.add("85 107 47");
        colorList.add("107 142 35");
        colorList.add("192 255 62");
        colorList.add("179 238 58");
        colorList.add("154 205 50");
        colorList.add("105 139 34");
        colorList.add("255 255 240");
        colorList.add("238 238 224");
        colorList.add("205 205 193");
        colorList.add("139 139 131");
        colorList.add("245 245 220");
        colorList.add("255 255 224");
        colorList.add("238 238 209");
        colorList.add("205 205 180");
        colorList.add("139 139 122");
        colorList.add("250 250 210");
        colorList.add("255 255 0");
        colorList.add("238 238 0");
        colorList.add("205 205 0");
        colorList.add("139 139 0");
        colorList.add("128 128 105");
        colorList.add("128 128 0");
        colorList.add("189 183 107");
        colorList.add("255 246 143");
        colorList.add("238 230 133");
        colorList.add("205 198 115");
        colorList.add("139 134 78");
        colorList.add("240 230 140");
        colorList.add("238 232 170");
        colorList.add("255 250 205");
        colorList.add("238 233 191");
        colorList.add("205 201 165");
        colorList.add("139 137 112");
        colorList.add("255 236 139");
        colorList.add("238 220 130");
        colorList.add("205 190 112");
        colorList.add("139 129 76");
        colorList.add("227 207 87");
        colorList.add("255 215 0");
        colorList.add("238 201 0");
        colorList.add("205 173 0");
        colorList.add("139 117 0");
        colorList.add("255 248 220");
        colorList.add("238 232 205");
        colorList.add("205 200 177");
        colorList.add("139 136 120");
        colorList.add("218 165 32");
        colorList.add("255 193 37");
        colorList.add("238 180 34");
        colorList.add("205 155 29");
        colorList.add("139 105 20");
        colorList.add("184 134 11");
        colorList.add("255 185 15");
        colorList.add("238 173 14");
        colorList.add("205 149 12");
        colorList.add("139 101 8");
        colorList.add("255 165 0");
        colorList.add("238 154 0");
        colorList.add("205 133 0");
        colorList.add("139 90 0");
        colorList.add("255 250 240");
        colorList.add("253 245 230");
        colorList.add("245 222 179");
        colorList.add("255 231 186");
        colorList.add("238 216 174");
        colorList.add("205 186 150");
        colorList.add("139 126 102");
        colorList.add("255 228 181");
        colorList.add("255 239 213");
        colorList.add("255 235 205");
        colorList.add("255 222 173");
        colorList.add("238 207 161");
        colorList.add("205 179 139");
        colorList.add("139 121 94");
        colorList.add("252 230 201");
        colorList.add("210 180 140");
        colorList.add("156 102 31");
        colorList.add("255 153 18");
        colorList.add("250 235 215");
        colorList.add("255 239 219");
        colorList.add("238 223 204");
        colorList.add("205 192 176");
        colorList.add("139 131 120");
        colorList.add("222 184 135");
        colorList.add("255 211 155");
        colorList.add("238 197 145");
        colorList.add("205 170 125");
        colorList.add("139 115 85");
        colorList.add("255 228 196");
        colorList.add("238 213 183");
        colorList.add("205 183 158");
        colorList.add("139 125 107");
        colorList.add("227 168 105");
        colorList.add("237 145 33");
        colorList.add("255 140 0");
        colorList.add("255 127 0");
        colorList.add("238 118 0");
        colorList.add("205 102 0");
        colorList.add("139 69 0");
        colorList.add("255 128 0");
        colorList.add("255 165 79");
        colorList.add("238 154 73");
        colorList.add("205 133 63");
        colorList.add("139 90 43");
        colorList.add("250 240 230");
        colorList.add("255 218 185");
        colorList.add("238 203 173");
        colorList.add("205 175 149");
        colorList.add("139 119 101");
        colorList.add("255 245 238");
        colorList.add("238 229 222");
        colorList.add("205 197 191");
        colorList.add("139 134 130");
        colorList.add("244 164 96");
        colorList.add("199 97 20");
        colorList.add("210 105 30");
        colorList.add("255 127 36");
        colorList.add("238 118 33");
        colorList.add("205 102 29");
        colorList.add("139 69 19");
        colorList.add("41 36 33");
        colorList.add("255 125 64");
        colorList.add("255 97 3");
        colorList.add("138 54 15");
        colorList.add("160 82 45");
        colorList.add("255 130 71");
        colorList.add("238 121 66");
        colorList.add("205 104 57");
        colorList.add("139 71 38");
        colorList.add("255 160 122");
        colorList.add("238 149 114");
        colorList.add("205 129 98");
        colorList.add("139 87 66");
        colorList.add("255 127 80");
        colorList.add("255 69 0");
        colorList.add("238 64 0");
        colorList.add("205 55 0");
        colorList.add("139 37 0");
        colorList.add("94 38 18");
        colorList.add("233 150 122");
        colorList.add("255 140 105");
        colorList.add("238 130 98");
        colorList.add("205 112 84");
        colorList.add("139 76 57");
        colorList.add("255 114 86");
        colorList.add("238 106 80");
        colorList.add("205 91 69");
        colorList.add("139 62 47");
        colorList.add("138 51 36");
        colorList.add("255 99 71");
        colorList.add("238 92 66");
        colorList.add("205 79 57");
        colorList.add("139 54 38");
        colorList.add("250 128 114");
        colorList.add("255 228 225");
        colorList.add("238 213 210");
        colorList.add("205 183 181");
        colorList.add("139 125 123");
        colorList.add("255 250 250");
        colorList.add("238 233 233");
        colorList.add("205 201 201");
        colorList.add("139 137 137");
        colorList.add("188 143 143");
        colorList.add("255 193 193");
        colorList.add("238 180 180");
        colorList.add("205 155 155");
        colorList.add("139 105 105");
        colorList.add("240 128 128");
        colorList.add("205 92 92");
        colorList.add("255 106 106");
        colorList.add("238 99 99");
        colorList.add("139 58 58");
        colorList.add("205 85 85");
        colorList.add("165 42 42");
        colorList.add("255 64 64");
        colorList.add("238 59 59");
        colorList.add("205 51 51");
        colorList.add("139 35 35");
        colorList.add("178 34 34");
        colorList.add("255 48 48");
        colorList.add("238 44 44");
        colorList.add("205 38 38");
        colorList.add("139 26 26");
        colorList.add("255 0 0");
        colorList.add("238 0 0");
        colorList.add("205 0 0");
        colorList.add("139 0 0");
        colorList.add("128 0 0");
        colorList.add("142 56 142");
        colorList.add("113 113 198");
        colorList.add("125 158 192");
        colorList.add("56 142 142");
        colorList.add("113 198 113");
        colorList.add("142 142 56");
        colorList.add("197 193 170");
        colorList.add("198 113 113");
        colorList.add("85 85 85");
        colorList.add("30 30 30");
        colorList.add("40 40 40");
        colorList.add("81 81 81");
        colorList.add("91 91 91");
        colorList.add("132 132 132");
        colorList.add("142 142 142");
        colorList.add("170 170 170");
        colorList.add("183 183 183");
        colorList.add("193 193 193");
        colorList.add("234 234 234");
        colorList.add("244 244 244");
        colorList.add("255 255 255");
        colorList.add("245 245 245");
        colorList.add("220 220 220");
        colorList.add("211 211 211");
        colorList.add("192 192 192");
        colorList.add("169 169 169");
        colorList.add("128 128 128");
        colorList.add("105 105 105");
        colorList.add("0 0 0");
        colorList.add("252 252 252");
        colorList.add("250 250 250");
        colorList.add("247 247 247");
        colorList.add("245 245 245");
        colorList.add("242 242 242");
        colorList.add("240 240 240");
        colorList.add("237 237 237");
        colorList.add("235 235 235");
        colorList.add("232 232 232");
        colorList.add("229 229 229");
        colorList.add("227 227 227");
        colorList.add("224 224 224");
        colorList.add("222 222 222");
        colorList.add("219 219 219");
        colorList.add("217 217 217");
        colorList.add("214 214 214");
        colorList.add("212 212 212");
        colorList.add("209 209 209");
        colorList.add("207 207 207");
        colorList.add("204 204 204");
        colorList.add("201 201 201");
        colorList.add("199 199 199");
        colorList.add("196 196 196");
        colorList.add("194 194 194");
        colorList.add("191 191 191");
        colorList.add("189 189 189");
        colorList.add("186 186 186");
        colorList.add("184 184 184");
        colorList.add("181 181 181");
        colorList.add("179 179 179");
        colorList.add("176 176 176");
        colorList.add("173 173 173");
        colorList.add("171 171 171");
        colorList.add("168 168 168");
        colorList.add("166 166 166");
        colorList.add("163 163 163");
        colorList.add("161 161 161");
        colorList.add("158 158 158");
        colorList.add("156 156 156");
        colorList.add("153 153 153");
        colorList.add("150 150 150");
        colorList.add("148 148 148");
        colorList.add("145 145 145");
        colorList.add("143 143 143");
        colorList.add("140 140 140");
        colorList.add("138 138 138");
        colorList.add("135 135 135");
        colorList.add("133 133 133");
        colorList.add("130 130 130");
        colorList.add("127 127 127");
        colorList.add("125 125 125");
        colorList.add("122 122 122");
        colorList.add("120 120 120");
        colorList.add("117 117 117");
        colorList.add("115 115 115");
        colorList.add("112 112 112");
        colorList.add("110 110 110");
        colorList.add("107 107 107");
        colorList.add("105 105 105");
        colorList.add("102 102 102");
        colorList.add("99 99 99");
        colorList.add("97 97 97");
        colorList.add("94 94 94");
        colorList.add("92 92 92");
        colorList.add("89 89 89");
        colorList.add("87 87 87");
        colorList.add("84 84 84");
        colorList.add("82 82 82");
        colorList.add("79 79 79");
        colorList.add("77 77 77");
        colorList.add("74 74 74");
        colorList.add("71 71 71");
        colorList.add("69 69 69");
        colorList.add("66 66 66");
        colorList.add("64 64 64");
        colorList.add("61 61 61");
        colorList.add("59 59 59");
        colorList.add("56 56 56");
        colorList.add("54 54 54");
        colorList.add("51 51 51");
        colorList.add("48 48 48");
        colorList.add("46 46 46");
        colorList.add("43 43 43");
        colorList.add("41 41 41");
        colorList.add("38 38 38");
        colorList.add("36 36 36");
        colorList.add("33 33 33");
        colorList.add("31 31 31");
        colorList.add("28 28 28");
        colorList.add("26 26 26");
        colorList.add("23 23 23");
        colorList.add("20 20 20");
        colorList.add("18 18 18");
        colorList.add("15 15 15");
        colorList.add("13 13 13");
        colorList.add("10 10 10");
        colorList.add("8 8 8");
        colorList.add("5 5 5");
        colorList.add("3 3 3");



        ///////////////train colors////////////////

        colorList.add("64 3 1");
        colorList.add("254 66 73 0");
        colorList.add("255 75 72 0");
        colorList.add("250 64 62 0");
        colorList.add("255 69 74 0");
        colorList.add("255 75 66 0");
        colorList.add("253 63 75 0");
        colorList.add("248 64 57 0");
        colorList.add("250 71 73 0");
        colorList.add("255 59 65 0");
        colorList.add("233 89 85 0");
        colorList.add("251 86 66 0");
        colorList.add("251 70 66 0");
        colorList.add("254 66 55 0");
        colorList.add("249 73 63 0");
        colorList.add("168 46 36 0");
        colorList.add("160 28 19 0");
        colorList.add("199 67 59 0");
        colorList.add("254 71 69 0");
        colorList.add("145 26 20 0");
        colorList.add("155 31 21 0");
        colorList.add("139 20 11 0");
        colorList.add("176 44 36 0");
        colorList.add("143 21 10 0");
        colorList.add("116 23 2 0");
        colorList.add("218 57 50 0");
        colorList.add("244 63 48 0");
        colorList.add("196 51 36 0");
        colorList.add("139 25 19 0");
        colorList.add("153 41 33 0");
        colorList.add("218 74 82 0");
        colorList.add("62 42 95 10");
        colorList.add("64 49 101 10");
        colorList.add("67 50 98 10");
        colorList.add("72 53 95 10");
        colorList.add("46 31 65 10");
        colorList.add("64 43 97 10");
        colorList.add("59 38 87 10");
        colorList.add("61 45 91 10");
        colorList.add("51 34 65 10");
        colorList.add("68 47 96 10");
        colorList.add("72 49 97 10");
        colorList.add("77 53 100 10");
        colorList.add("48 30 64 10");
        colorList.add("42 31 65 10");
        colorList.add("52 35 69 10");
        colorList.add("79 52 95 10");
        colorList.add("63 44 90 10");
        colorList.add("67 49 100 10");
        colorList.add("70 49 101 10");
        colorList.add("62 40 96 10");
        colorList.add("65 53 97 10");
        colorList.add("99 87 124 9");
        colorList.add("92 88 140 9");
        colorList.add("107 97 153 9");
        colorList.add("99 90 145 9");
        colorList.add("128 95 161 9");
        colorList.add("91 74 132 9");
        colorList.add("50 45 61 9");
        colorList.add("35 39 91 9");
        colorList.add("65 32 100 9");
        colorList.add("65 61 100 9");
        colorList.add("84 82 125 9");
        colorList.add("115 85 158 9");
        colorList.add("80 77 125 9");




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
           // tv1.setText("Dominant Color in the Picture is : "+getDominantColor1(bitmap)+"");

            getRGB(-getDominantColor1(bitmap));
            System.out.println(getDominantColor1(bitmap));


        }else if(requestCode==2){

            img.setImageURI(data.getData());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                tv1.setBackgroundColor(getDominantColor1(bitmap));
              //  tv1.setText("Dominant Color in the Picture is : "+getDominantColor1(bitmap)+"");

                getRGB(-getDominantColor1(bitmap));
                System.out.println(getDominantColor1(bitmap));

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
    ///////////////////////////////////////////////////////Code for Colors////////////////////////

    public static void colorFunc(ArrayList<String> colorList, double userColor) throws NumberFormatException

    {

        Map<Double, String> map = new TreeMap<>();
        for (String col : colorList) {
            try {
                String colArray[] = col.split(" ");
                int red = Integer.parseInt(colArray[0].trim());
                int green = Integer.parseInt(colArray[1].trim());
                int blue = Integer.parseInt(colArray[2].trim());

                double d = Math.sqrt(((red * red) + (green * green) + (blue * blue)));
                map.put(d, col);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        double equa = getEquals(map, userColor);
        double min = getMinimum(map, userColor);
        double max = getMaxNumber(map, userColor);
       // printLog(map + "");
      //  printLog("minimum : " + getValue(map, min) + "\n" + "maximum : " + getValue(map, max) + "\nEquals : " + equa);

        String nearString = getExactColorNear(equa, min, max, userColor, map);
        printLog("Value : " + nearString);
        StringTokenizer mTokenizer=new StringTokenizer(nearString," ");
        ArrayList<Integer> mInt=new ArrayList<>();
        mInt.clear();
        while (mTokenizer.hasMoreTokens()){
            mInt.add(Integer.parseInt(mTokenizer.nextToken()));
        }
     tv2.setBackgroundColor(Color.rgb(mInt.get(0), mInt.get(1), mInt.get(2)));

    }

    private static String getExactColorNear(double equa, double min, double max, double userColor,
                                            Map<Double, String> map) {

        String val = "";

        if (equa != 0) {
            printLog("------------GIVEN RGB EXACT MATCH--------------");
            val = getValue(map, equa);
        } else if (min != 0 && max != 0) {

            double d1 = userColor - min;
            double d2 = max - userColor;

            printLog("d1:" + d1);
            printLog("d2:" + d2);
            if (d1 < d2) {
                printLog("------------minimum is near by color--------------");
                val = getValue(map, min);

            } else {
                val = getValue(map, max);
                printLog("------------maximum is near by color--------------");
            }

        } else if (min == 0) {
            printLog("------------" + max + " max near number--------------");
            val = getValue(map, max);
        } else if (max == 0) {
            val = getValue(map, min);
            printLog("------------" + min + " min near number--------------");
        }

        return val;
    }

    private static String getValue(Map<Double, String> map, double min) {

        return map.get(min).toString();
    }

    {

    }

    private static double getEquals(Map<Double, String> map, double userColor) {
        double equ = 0;
        for (Map.Entry<Double, String> entry : map.entrySet()) {

            if (entry.getKey() == userColor) {
                equ = entry.getKey();
                return equ;
            }

        }
        return equ;

    }

    private static double getMaxNumber(Map<Double, String> map, double userColor) {

        double max = 0;
        for (Map.Entry<Double, String> entry : map.entrySet()) {

            if (entry.getKey() > userColor) {
                max = entry.getKey();
                return max;
            }

        }
        return max;

    }

    public static void printLog(String log) {
        // Log.d("color", log);
        System.out.println(log);
    }

    public static double getMinimum(Map<Double, String> map, double userColor) {
        double minimum = 0;
        for (Map.Entry<Double, String> entry : map.entrySet()) {

            if (entry.getKey() < userColor) {
                minimum = entry.getKey();
            }

        }
        return minimum;
    }


    public void getRGB(int originalNum){

        int ones=~originalNum;
        int twos=ones+1;
        System.out.println(Integer.toHexString(twos));

        int no = originalNum, r, g ,b;
        originalNum = ~originalNum +1;
        System.out.println(originalNum);
        b = originalNum & 0xff;
        g = (originalNum >> 8) & 0xff;
        r = (originalNum >> 16) & 0xff;

        System.out.println(r+"=="+g+"=="+b);

        double userColr = Math.sqrt(((r * r) + (g * g) + (b * b)));
        colorFunc(colorList, userColr);
        printLog("user color:" + r + " " + g + " " + b);
    }

}
