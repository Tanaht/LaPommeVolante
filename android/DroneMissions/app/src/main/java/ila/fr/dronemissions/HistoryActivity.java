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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        // TODO : replace this with a call to Missions API service
        List<String> missions = Arrays.asList("Mission Pomme 1", "Mission Golden Pomme",
                "Mission Pomme Canada", "Mission My Lovely Pomme", "Mission Last Pomme on Earth");

        listView = (ListView) findViewById(R.id.listView);
        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoryActivity.this,
          //      android.R.layout.simple_list_item_1, missions);
        List<Mission> ms = new ArrayList<Mission>();
        Point p1 = new Point(12345.12345, 123456.123456, false);
        List<Point> points = new ArrayList<Point>();
        points.add(p1);points.add(p1);points.add(p1);
        Mission m1 = new Mission("m1","en cours", points);
        Mission m2 = new Mission("m2","terminée", points);
        Mission m3 = new Mission("m3","en cours", points);
        ms.add(m1);ms.add(m2);ms.add(m3);
        SimpleAdapter myadapter = createListViewAdapter(ms);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    protected SimpleAdapter createListViewAdapter(List<Mission> missions){
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Mission mission : missions) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("title", mission.getTitle());
            datum.put("type", mission.getType());
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
