package com.example.xydw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class MainActivity extends Activity {
    private Button btn_start;
    private TextView tv_loc_info;
    private LocationClient locationClient = null;
    private static final int UPDATE_TIME = 5000;
    private static int LOCATION_COUTNS = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        locationClient = new LocationClient(this);
        //设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setAddrType("detail");
        option.setCoorType("gcj02");
        option.setScanSpan(5000);
        option.disableCache(true);//禁止启用缓存定位
        locationClient.setLocOption(option);

        locationClient.setLocOption(option);
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location == null) {
                    return;
                }
                StringBuffer sb = new StringBuffer(256);
                sb.append("Time : ");
                sb.append(location.getTime());
                sb.append("\nError code : ");
                sb.append(location.getLocType());
                sb.append("\nLatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nLontitude : ");
                sb.append(location.getLongitude());
                sb.append("\nRadius : ");
                sb.append(location.getRadius());
                if (location.getLocType() == BDLocation.TypeGpsLocation){
                    sb.append("\nSpeed : ");
                    sb.append(location.getSpeed());
                    sb.append("\nSatellite : ");
                    sb.append(location.getSatelliteNumber());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                    sb.append("\nAddress : ");
                    sb.append(location.getAddrStr());
                }
                LOCATION_COUTNS ++;
                sb.append("\n检查位置更新次数：");
                sb.append(String.valueOf(LOCATION_COUTNS));
                tv_loc_info.setText(sb.toString());
            }
        });
    }
    private void initView(){
        btn_start = (Button) findViewById(R.id.btn_start);
        tv_loc_info = (TextView) findViewById(R.id.tv_loc_info);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (locationClient == null) {
                    return;
                }
                if (locationClient.isStarted()) {
                    btn_start.setText("Start");
                    locationClient.stop();
                }else {
                    btn_start.setText("Stop");
                    locationClient.start();
	                    /*
	                     *当所设的整数值大于等于1000（ms）时，定位SDK内部使用定时定位模式。
	                     *调用requestLocation( )后，每隔设定的时间，定位SDK就会进行一次定位。
	                     *如果定位SDK根据定位依据发现位置没有发生变化，就不会发起网络请求，
	                     *返回上一次定位的结果；如果发现位置改变，就进行网络请求进行定位，得到新的定位结果。
	                     *定时定位时，调用一次requestLocation，会定时监听到定位结果。
	                     */
                    locationClient.requestLocation();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationClient != null && locationClient.isStarted()) {
            locationClient.stop();
            locationClient = null;
        }
    }
}
