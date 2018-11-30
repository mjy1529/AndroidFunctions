package com.test.autobluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;


public class BluetoothService {

    //Debugging
    private static final String TAG = "BluetoothService";
    private BluetoothAdapter btAdapter;

    private Activity mAcitivity;
    private Handler mHandler;

    private static final int REQUEST_ENABLE_BT = 2;

    public BluetoothService(Activity ac, Handler h) {
        this.mAcitivity = ac;
        this.mHandler = h;

        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean getDeviceState() {
        Log.d(TAG, "check the bluetooth support");

        if(btAdapter == null) {
            return false;
        } else {
            return true;
        }
    }

    public void enableBluetooth() {
        Log.d(TAG, "check the enabled bluetooth");

        if(btAdapter.isEnabled()) { // 기기의 블루투스 상태가 On인 경우
            Log.d(TAG, "Bluetooth Enable Now");
            btAdapter.disable();
        } else {
            // 기기의 블루투스 상태가 Off인 경우 블루투스 활성화를 요청하는 알림창 띄우기
            // 알림창에서 확인/취소를 선택한 결과가 onActivityResult()로 들어옴
            Log.d(TAG, "Bluetooth Unable!");

//            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            mAcitivity.startActivityForResult(intent, REQUEST_ENABLE_BT);

            btAdapter.enable();
        }
    }
}
