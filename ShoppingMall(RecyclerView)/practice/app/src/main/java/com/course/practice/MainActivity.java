package com.course.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView phone = (TextView) findViewById(R.id.textView2);
        TextView email = (TextView) findViewById(R.id.textView3);
        TextView address = (TextView) findViewById(R.id.textView4);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        final ProductAdapter adapter = new ProductAdapter();

        adapter.addItem(new Product("에어팟", "20만", "에어팟 2", R.drawable.airpod));
        adapter.addItem(new Product("갤럭시 21", "90만", "신형 삼성 갤럭시 시리즈", R.drawable.galaxy21));
        adapter.addItem(new Product("헤드셋", "10만", "게이밍 헤드셋", R.drawable.headset));
        adapter.addItem(new Product("아이패드", "50만", "아이패트 프로", R.drawable.ipad));
        adapter.addItem(new Product("아이폰13", "100만", "출시 특가상품", R.drawable.iphone));
        adapter.addItem(new Product("키보드", "8만", "유명 게이머 사용 브랜드", R.drawable.keyboard));
        adapter.addItem(new Product("맥북", "120만", "14인치", R.drawable.macbook));
        adapter.addItem(new Product("마우스", "5만", "다양한 DSI 지원", R.drawable.mouse));
        adapter.addItem(new Product("마우스패드", "2만", "부드러운 감촉", R.drawable.pad));
        adapter.addItem(new Product("USB", "1만", "1테라 저장용량", R.drawable.usb));

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnProductItemClickListener() {
            @Override
            public void onItemClick(ProductAdapter.ViewHolder holder, View view, int position) {
                Product item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "이름 : " + item.getName() + "\n 가격 : " + item.getCost() + "\n 설명 : " + item.getNotification(), Toast.LENGTH_LONG).show();
            }
        });


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01025556174"));
                startActivity(intent);
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveMap(v);
            }
        });


    }

    public void moveMap(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }


    protected void sendEmail () {
        String[] TO = {"lsh981127@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        try {
            startActivity(Intent.createChooser(emailIntent, "이메일 보내기..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "이메일 클라이언트 없음.", Toast.LENGTH_SHORT).show();
        }
    }
}
