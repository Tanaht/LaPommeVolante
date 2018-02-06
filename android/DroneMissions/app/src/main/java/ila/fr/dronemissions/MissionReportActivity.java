package ila.fr.dronemissions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ila.fr.dronemissions.DAO.Mission;

public class MissionReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_report);
        setTitle("Mission Report");

        Mission mission = (Mission) getIntent().getSerializableExtra("MISSION");

        ((TextView)findViewById(R.id.mission_report_title)).setText(mission.getTitle());
        ((TextView)findViewById(R.id.mission_report_status)).setText(mission.getStatus());
    }
}
