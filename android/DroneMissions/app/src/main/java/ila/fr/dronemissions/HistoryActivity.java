package ila.fr.dronemissions;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ila.fr.dronemissions.DAO.Mission;
import ila.fr.dronemissions.DAO.Point;

public class HistoryActivity extends AppCompatActivity {

    ListView listView;
    List<Mission> ms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("Missions History");

        // TODO : replace this with a call to Missions API service
        ms = new ArrayList<Mission>();
        Point p1 = new Point(12345.12345,12345.12345, 123456.123456, false);
        List<Point> points = new ArrayList<Point>();
        points.add(p1);points.add(p1);points.add(p1);
        Mission m1 = new Mission("Mission Miss-Pomme Bretagne 2018","en cours", points);
        Mission m2 = new Mission("Mission Golden Pomme","terminée", points);
        Mission m3 = new Mission("Mission Pomme Canada","en cours", points);
        Mission m4 = new Mission("Mission My Lovely Pomme","terminée", points);
        Mission m5 = new Mission("Mission Last Pomme on Earth","en cours", points);
        ms.add(m1);ms.add(m2);ms.add(m3);ms.add(m4);ms.add(m5);


        listView = (ListView) findViewById(R.id.listView);
        //android.R.layout.simple_list_item_2 est une vue disponible de base dans le SDK android,
        //Contenant deux TextView avec comme identifiants "@android:id/text1 et text2"

        SimpleAdapter myadapter = createListViewAdapter(ms);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent( HistoryActivity.this,
                        MissionReportActivity.class);
                intent.putExtra("MISSION", (Mission) ms.get(position));
                startActivity(intent);
            }
        });
    }

    protected SimpleAdapter createListViewAdapter(List<Mission> missions){
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Mission mission : missions) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("title", mission.getTitle());
            datum.put("type", mission.getStatus());
            data.add(datum);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "type"},
                new int[] {android.R.id.text1,
                        android.R.id.text2}){
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setTextColor(Color.parseColor("#234A7A"));
                text1.setTypeface(null, Typeface.BOLD_ITALIC);
                text2.setTextColor(Color.parseColor("#5dc305"));
                return view;
            };
        };
        return adapter;
    }
}
