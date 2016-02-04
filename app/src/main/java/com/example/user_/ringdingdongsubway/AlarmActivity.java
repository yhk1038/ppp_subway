package com.example.user_.ringdingdongsubway;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by oneno on 2016-02-01.
 */
public class AlarmActivity extends Activity implements OnClickListener{

    LocationManager locMgr;
    LocationListener locLnr;
    AlarmManager mAlarmMgr;

    TextView text;
    ImageButton startButton;
    ImageButton stopButton;

    private ArrayList<Station2> s_list = new ArrayList<Station2>();
    public Station t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list2);
        t = (Station) getIntent().getSerializableExtra("station");
        final String name = t.getStation_name();
        final int line = t.getStation_line();
        final double x = t.getStaion_x();
        final double y = t.getStaion_y();

        // 알람 관리자 핸들을 구한다
        mAlarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);




        Log.e("NOWx", LocationManager.GPS_PROVIDER);

        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locLnr = new MyLocationListener();

        text = (TextView) findViewById(R.id.text);
        startButton = (ImageButton) findViewById(R.id.start);
        stopButton = (ImageButton) findViewById(R.id.stop);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        // 플러스 버튼 클릭시
        LinearLayout fabBtn = (LinearLayout) findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v){
        int millis = 5000; //밀리세컨드
        int distance = 5; //거리

        if (v == startButton){ // 위치 감지를 시작한다.

            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, millis, distance, locLnr);
            text.setText("위치 감지를 시작합니다.");
        } else if (v == stopButton){ // 위치 감지를 중지한다.
            locMgr.removeUpdates(locLnr);
            text.setText("위치 감지를 중지합니다.");
        }
    }

    // 반복 알람 시작 - 특정 시간 지정
    public void onBtnAlarm2() {
        // 수행할 동작을 생성
        Intent intent = new Intent(this, AlarmActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(AlarmActivity.this, 0, intent, 0);

        // 알람이 발생할 정확한 시간을 지정
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);
        // 반복 알람 시작
        mAlarmMgr.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 5000, pIntent);
    }

    // 알람 중지
    public void onBtnStop() {
        // 수행할 동작을 생성
        Intent intent = new Intent(this, AlarmActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(AlarmActivity.this, 0, intent, 0);
        // 알람 중지
        mAlarmMgr.cancel(pIntent);
    }


    @Override
    public void onResume(){
        super.onResume();
        getStations();
    }
    private class StationAdapter extends ArrayAdapter<Station2> {

        private ArrayList<Station2> items;

        public StationAdapter(Context context, int textViewResourceId, ArrayList<Station2> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row, null);
            }
            Station2 p = items.get(position);

            if (p != null) {
                ImageView tt = (ImageView) v.findViewById(R.id.icon);
                TextView bt = (TextView) v.findViewById(R.id.label);
                if (tt != null) {
                    /*여기가 제일 중요한데 drawable폴더에 호선 아이콘을 60 60 사이즈로 다 저장해놓고
                        스위치 케이스나 이프문으로 각각의 것을 셋이미지리소스설정하면 바로 나옵니다.*/
                    //10==분당선 11== 인천선 12==신분당선 13==경의중앙선 14==경춘선 15==공항철도 16==의정부 17==수인선 18==에버라인 나머지는 그대로
                    if (p.Line == 1) {
                        tt.setImageResource(R.drawable.icon_1);
                    }
                    if (p.Line == 2) {
                        tt.setImageResource(R.drawable.icon_2);
                    }
                    if (p.Line == 3) {
                        tt.setImageResource(R.drawable.icon_3);
                    }
                    if (p.Line == 4) {
                        tt.setImageResource(R.drawable.icon_4);
                    }
                    if (p.Line == 5) {
                        tt.setImageResource(R.drawable.icon_5);
                    }
                    if (p.Line == 6) {
                        tt.setImageResource(R.drawable.icon_6);
                    }
                    if (p.Line == 7) {
                        tt.setImageResource(R.drawable.icon_7);
                    }
                    if (p.Line == 8) {
                        tt.setImageResource(R.drawable.icon_8);
                    }
                    if (p.Line == 9) {
                        tt.setImageResource(R.drawable.icon_9);
                    }
                    if (p.Line == 10) {
                        tt.setImageResource(R.drawable.icon_10);
                    }
                    if (p.Line == 11) {
                        tt.setImageResource(R.drawable.icon_11);
                    }
                    if (p.Line == 12) {
                        tt.setImageResource(R.drawable.icon_12);
                    }
                    if (p.Line == 13) {
                        tt.setImageResource(R.drawable.icon_13);
                    }
                    if (p.Line == 14) {
                        tt.setImageResource(R.drawable.icon_14);
                    }
                    if (p.Line == 15) {
                        tt.setImageResource(R.drawable.icon_15);
                    }
                    if (p.Line == 16) {
                        tt.setImageResource(R.drawable.icon_16);
                    }
                    if (p.Line == 17) {
                        tt.setImageResource(R.drawable.icon_17);
                    }
                    if (p.Line == 18) {
                        tt.setImageResource(R.drawable.icon_18);
                    }
                }
                if (bt != null) {
                    bt.setText(p.getName());
                }
            }
            return v;
        }
    }
    private void getStations(){
        ListView list = (ListView) findViewById(R.id.list2);
        final Station t = (Station) getIntent().getSerializableExtra("station");
        final String name = t.getStation_name();
        final int line = t.getStation_line();
        s_list.add(new Station2(line, name));
        StationAdapter s_adapter = new StationAdapter(this, R.layout.row2, s_list);

        list.setAdapter(s_adapter);
        s_adapter.notifyDataSetChanged();
    }




    public class MyLocationListener implements LocationListener{

        private boolean equals;
        private boolean equals1;

        // 위치 정보가 변경되었을 때 호출된다.
        @Override
        public void onLocationChanged(Location loc){
            String text = "현재위치 : \n" + "위도 : " + loc.getLatitude() + "\n"
                    + "경도 : " + loc.getLongitude();

            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

            Log.e("TAGgg", t.getStation_name());
            Log.e("TAGxgg", t.getStaion_x() + "");
            Log.e("TAGygg", t.getStaion_y() + "");
            Log.e("TAGlinegg", t.getStation_line() + "");


            if (loc.getLatitude() == loc.getLatitude() | loc.getLongitude() == loc.getLongitude()){
                Toast.makeText(getApplicationContext(), "도착", Toast.LENGTH_LONG).show();        // 위치 정보값이 목적지 위치에 일치하면 "도착"
            }else {
                Toast.makeText(getApplicationContext(), "도착하지 않음", Toast.LENGTH_LONG).show();   // 위치 정보값이 목적지 위치에 일치하면 "도착하지 않음"
            }

            //Intent intent = new Intent(SetAlarmActivity.this, AlarmActivity.class);
        }



        // 위치 제공자가 변경되었을 때 호출된다.
        @Override
        public void onProviderDisabled(String provider){
            Toast.makeText(getApplicationContext(), "GPS 이용 불가", Toast.LENGTH_SHORT).show();
        }

        // 위치 제공자가 활성화 되었을 때 호출된다.
        @Override
        public void onProviderEnabled(String provider){
            Toast.makeText(getApplicationContext(), "GPS 이용 가능", Toast.LENGTH_SHORT).show();
        }

        // 위치 제공자의 상태가 변경되었을 때 호출된다.
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){
            Toast.makeText(getApplicationContext(), "위치 제공자의 상태가 변경되었음", Toast.LENGTH_LONG).show();
        }
    }
}
