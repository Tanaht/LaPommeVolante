package ila.fr.dronemissions;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

import ila.fr.dronemissions.DAO.Mission;
import ila.fr.dronemissions.DAO.Point;
import service.CommunicationServerHelper;

public class NewMissionActivity extends AppCompatActivity implements LocationListener {

    private Button btn_NewMission;
    private static final int PERMISSIONS_ID = 5290;
    private LocationManager lm;

    private MapFragment mapFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mission);

        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        btn_NewMission = (Button) findViewById(R.id.btn_Envoyer);

        btn_NewMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Point> points = new ArrayList<>();
                Point p1 = new Point(-1.6388297,48.1148383,  30, false);
                Point p2 = new Point(-1.6391757,48.1153379,  30, false);
                Point p3 = new Point(-1.6390014,48.1161849, 30, false);
                Point p4 = new Point(-1.6373706,48.1164571, 30, false);
                Point p5 = new Point(-1.6360724,48.1155689, 30, false);
                Point p6 = new Point(-1.6378534,48.1152322, 30, false);
                points.add(p1);
                points.add(p2);
                points.add(p3);
                points.add(p4);
                points.add(p5);
                points.add(p6);
                Mission mission = new Mission("highway to hell", "true", points);
                CommunicationServerHelper csh = new CommunicationServerHelper(getApplicationContext());
                csh.throwMissionOrder(mission);
                Toast.makeText(getApplicationContext(), "Mission successfully sent to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSIONS_ID);
            return;
        }

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_ID) {
            checkPermissions();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (lm != null) {
            lm.removeUpdates(this);
        }

    }

    private void loadMap(){
        //TODO
        //48'

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Toast.makeText(this, "Location: " + latitude + "/" + longitude, Toast.LENGTH_SHORT).show();

    }

    //@Override
    public void onFragmentInteraction(Uri uri) {

    }
}
