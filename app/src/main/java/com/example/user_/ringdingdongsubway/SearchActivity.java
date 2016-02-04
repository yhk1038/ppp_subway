package com.example.user_.ringdingdongsubway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;


public class SearchActivity extends Activity {
    private ArrayList<Station2> s_list = new ArrayList<Station2>();
    //10==경의선 11== 공항철도 12==경춘선 13==분당선 14==수인선 15==의정부 16==신분당선 17==에버라인 18==인천선

    private void getStation() {
        String[] arrStation = getResources().getStringArray(R.array.arrstation);

        for (int i = 0; i <= 539; i++) {
            do {
                s_list.add(new Station2(1, arrStation[i]));
                i++;
            } while (i < 98);
            do {
                s_list.add(new Station2(2, arrStation[i]));
                i++;
            } while (i < 145);
            do {
                s_list.add(new Station2(3, arrStation[i]));
                i++;
            } while (i < 185);
            do {
                s_list.add(new Station2(4, arrStation[i]));
                i++;
            } while (i < 228);
            do {
                s_list.add(new Station2(5, arrStation[i]));
                i++;
            } while (i < 272);
            do {
                s_list.add(new Station2(6, arrStation[i]));
                i++;
            } while (i < 300);
            do {
                s_list.add(new Station2(7, arrStation[i]));
                i++;
            } while (i < 340);
            do {
                s_list.add(new Station2(8, arrStation[i]));
                i++;
            } while (i < 353);
            do {
                s_list.add(new Station2(9, arrStation[i]));
                i++;
            } while (i < 376);
            do {
                s_list.add(new Station2(10, arrStation[i]));
                i++;
            } while (i < 418);
            do {
                s_list.add(new Station2(11, arrStation[i]));
                i++;
            } while (i < 424);
            do {
                s_list.add(new Station2(12, arrStation[i]));
                i++;
            } while (i < 443);
            do {
                s_list.add(new Station2(13, arrStation[i]));
                i++;
            } while (i < 473);
            do {
                s_list.add(new Station2(14, arrStation[i]));
                i++;
            } while (i < 481);
            do {
                s_list.add(new Station2(15, arrStation[i]));
                i++;
            } while (i < 495);
            do {
                s_list.add(new Station2(16, arrStation[i]));
                i++;
            } while (i < 498);
            do {
                s_list.add(new Station2(17, arrStation[i]));
                i++;
            } while (i < 512);
            do {
                s_list.add(new Station2(18, arrStation[i]));
                i++;
            } while (i < 540);
        }


    }

    private AutoCompleteTextView autoEdit;
    protected AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String[] arrStation = getResources().getStringArray(R.array.arrstation);
        assetManager = getResources().getAssets();

        ArrayAdapter<String> autoStation = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrStation);
        autoEdit = (AutoCompleteTextView) findViewById(R.id.search_station_autoable);
        autoEdit.setAdapter(autoStation);


        Button searchBtn = (Button) findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, SetAlarmActivity.class);

                Station t = new Station();
                try {
                    //AssetManager.AssetInputStream ais = (AssetManager.AssetInputStream)assetManager.open("station.json");
                    InputStreamReader isr = new InputStreamReader(assetManager.open("stations.json"));
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        JSONObject stationJson = null;
                        try {
                            stationJson = new JSONObject(line);

                            if (autoEdit.getText().toString().equals(stationJson.getString("sTATION_NM"))) {
                                t.setStation_name(stationJson.getString("sTATION_NM"));
                                t.setStaion_x(stationJson.getDouble("xPOINT_WGS"));
                                t.setStaion_y(stationJson.getDouble("yPOINT_WGS"));
                                t.setStation_line(stationJson.getInt("lINE_NUM"));
                                break;
                            }

                        } catch (JSONException e) {
                            continue;
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                intent.putExtra("station", t);
                startActivity(intent);
            }
        });
        ListView list = (ListView) findViewById(R.id.stationList);
        getStation();
        StationAdapter s_adapter = new StationAdapter(this, R.layout.row, s_list);
        list.setAdapter(s_adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, SetAlarmActivity.class);

                Station t = new Station();
                try {
                    //AssetManager.AssetInputStream ais = (AssetManager.AssetInputStream)assetManager.open("station.json");
                    InputStreamReader isr = new InputStreamReader(assetManager.open("stations.json"));
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        JSONObject stationJson = null;
                        try {
                            stationJson = new JSONObject(line);

                            if (s_list.get(position).getName().equals(stationJson.get("sTATION_NM"))) {
                                t.setStation_name(stationJson.getString("sTATION_NM"));
                                t.setStaion_x(stationJson.getDouble("xPOINT_WGS"));
                                t.setStaion_y(stationJson.getDouble("yPOINT_WGS"));
                                t.setStation_line(stationJson.getInt("lINE_NUM"));
                                break;
                            }

                        } catch (JSONException e) {
                            continue;
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                intent.putExtra("station", t);
                startActivity(intent);
            }
        });
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

}