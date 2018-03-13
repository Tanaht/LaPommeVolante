package ila.fr.dronemissions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ila.fr.dronemissions.DAO.Mission;
import ila.fr.dronemissions.DAO.Point;
import service.CommunicationServerHelper;

public class MainActivity extends AppCompatActivity {

    Button btn_NewMission;
    Button btn_OldMissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_NewMission = (Button) findViewById(R.id.btn_NewMission);
        btn_OldMissions = (Button) findViewById(R.id.btn_OldMissions);

        btn_NewMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this,
                        NewMissionActivity.class);
                startActivity(intent);
//                List<Point> points = new ArrayList<>();
//                Point p1 = new Point(-1.6388297,48.1148383,  10, false);
//                Point p2 = new Point(-1.6391757,48.1153379,  10, false);
//                Point p3 = new Point(-1.6390014,48.1161849, 10, false);
//                points.add(p1);
//                points.add(p2);
//                points.add(p3);
//                Mission mission = new Mission("highway to hell", "true", points);
//                CommunicationServerHelper csh = new CommunicationServerHelper(getApplicationContext());
//                csh.throwMissionOrder(mission);
            }
        });

        btn_OldMissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this,
                        HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
