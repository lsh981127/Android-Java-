package com.test.cpr_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView textView, textView2;
    private ImageView imageView;
    Handler handler = new Handler();

    Button btn1, btn2, btn3, btn4;
    ImageView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (Button) findViewById(R.id.button1); // 119 다이얼
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3); // 병원 지도 키는거
        btn4 = (Button) findViewById(R.id.button4); // 카메라

        textView = (TextView) findViewById(R.id.textView); // 압박 속도 표현
        textView2 = (TextView) findViewById(R.id.textView2); // 타이머
        imageView = (ImageView) findViewById(R.id.imageView); // 깊이 표현 이미지뷰


        btn1.setOnClickListener(new View.OnClickListener() {   //119 다이얼 화면
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"));
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {  // 지도 출력
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NaverMapActivity.class);
                startActivity(i);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {  // 카메라
            @Override
            public void onClick(View view) {
                callCamera();
            }
        });


        // Intent intent = getIntent();      //DeviceControlActiviy에서 displaydata 메서드에서 보낸 intent 받기
        // String cpr = intent.getStringExtra("cpr");




        // textView2.setText("cpr 들어오는 값 : " + cpr);
        //TextThread tt = new TextThread(); // 압박속도 textview 스레드
        // ImageThread it = new ImageThread(); // 압박 깊이용 Imageview 스레드
        //tt.start();
        // it.start();

    }

    private void callCamera() {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            PackageManager pm = getPackageManager();
            final ResolveInfo mInfo = pm.resolveActivity(i, 0);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(mInfo.activityInfo.packageName, mInfo.activityInfo.name));
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
        } catch (Exception e) {
            Log.i("TAG", "Unable to launch camera:" + e);
        }
    }

}

    /** class TextThread extends Thread {    // 압박 속도 스레드

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                //여기 value
                Log.d("TextThread", "Value : " + value);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(value + "km/s");
                    }
                });
            }
        }
    }
}

    class ImageThread extends Thread {  // 압박 깊이 스레드(그림 표현)
        public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
        }


        Log.d("ImageThread", "depth" + depth);
        handler.post(new Runnable() {
        @Override
        public void run() {           // 들어오는 깊이에 대한 값을 if, else if 문으로 나누어 drawble 파일에 있는 이미지를 출력하도록 설정함
            if (value <= 0.0) imageView.setImageResource(R.drawable.image0);
            else if (value <= 0.5) imageView.setImageResource(R.drawable.image1);
            else if (value <= 1.0) imageView.setImageResource(R.drawable.image2);
            else if (value <= 1.5) imageView.setImageResource(R.drawable.image3);
            else if (value <= 2.0) imageView.setImageResource(R.drawable.image4);
            else if (value <= 2.5) imageView.setImageResource(R.drawable.image5);
            else if (value <= 3.0) imageView.setImageResource(R.drawable.image6);
            else if (value <= 3.5) imageView.setImageResource(R.drawable.image7);
            else if (value <= 4.0) imageView.setImageResource(R.drawable.image8);
            else if (value <= 4.5) imageView.setImageResource(R.drawable.image9);
            else if (value <= 5.0) imageView.setImageResource(R.drawable.image10);
            else if (value <= 5.5) imageView.setImageResource(R.drawable.image11;
            else if (value <= 6.0) imageView.setImageResource(R.drawable.image12);
            else if (value <= 6.5) imageView.setImageResource(R.drawable.image13;
            else if (value <= 7.0) imageView.setImageResource(R.drawable.image14);
            else imageView.setImageResource(R.drawable.image15);
        }
    });
    }
 }
 }
 }

 */
