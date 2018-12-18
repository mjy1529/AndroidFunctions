package com.example.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "권한 허용!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "권한 거부!", Toast.LENGTH_SHORT).show();
            }
        };

        if (Build.VERSION.SDK_INT > 23) {
            //위치 권한 받기
            TedPermission.with(MainActivity.this)
                    .setPermissionListener(permissionListener)
                    .setDeniedMessage("If you reject permission, you can not user this service\n\nPleas turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                    .check();

        }

        getLocation();
    }

    public void getLocation() {
        tv = (TextView) findViewById(R.id.textView2);
        tv.setText("위치정보 미수신중");
        Button button = (Button) findViewById(R.id.button);

        //Location 제공자에서 정보 얻어오기
        //1. Location을 사용하기 위한 권한을 얻어와야 한다.
        //2. LocationManager를 통해서 원하는 제공자의 리스너 등록

        //LocationManager 객체를 얻어옴
        final LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                //GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        100, //최소 시간간격
                        1, //최소 변경거리
                        mLocationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        100,
                        1,
                        mLocationListener);
            }
        });
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();
            float accuracy = location.getAccuracy();
            String provider = location.getProvider();

            tv.setText("위치정보 : " + provider +"\n위도 : " + latitude + "\n경도 : " + longitude +
                    "\n고도 : " + altitude + "\n정확도 : " + accuracy);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
