package com.example.user_.ringdingdongsubway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SetAlarmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        final Station t = (Station) getIntent().getSerializableExtra("station");
        TextView textView =(TextView)findViewById(R.id.s_name);
        final String name = t.getStation_name();
        final Double x =t.getStaion_x();
        final Double y =t.getStaion_y();
        final int line = t.getStation_line();
        textView.setText(name+"역 설정");
//        Log.e("TAG", t.getStation_name());
//        Log.e("TAGx", t.getStaion_x() + "");
//        Log.e("TAGy", t.getStaion_y() + "");
//        Log.e("TAGline", t.getStation_line() + "");

        Button setBtn = (Button) findViewById(R.id.setBtn);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAlarmActivity.this, AlarmActivity.class);
                t.setStation_name(name);
                t.setStation_line(line);
                t.setStaion_x(x);
                t.setStaion_y(y);
                intent.putExtra("station", t);
                startActivity(intent);
                finish();
            }
        });
    }
}
