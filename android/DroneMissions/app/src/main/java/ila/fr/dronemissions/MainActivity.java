package ila.fr.dronemissions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

                //TODO

            }
        });

        btn_OldMissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO
                
            }
        });
    }
}
