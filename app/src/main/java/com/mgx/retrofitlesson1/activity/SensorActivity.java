package com.mgx.retrofitlesson1.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.mgx.retrofitlesson1.R;

import java.util.List;

/**
 * Created by glmgr on 2017/1/3.
 */

public class SensorActivity extends BaseActivity implements SensorEventListener {
    private TextView tvAccelerometer, tvGyroscope, tvMagnetic, tvLight, tvOrientation, tvSensor;
    public static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp;
    private float[] angle = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initUI();

        // 获取SensorManager对象
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // 注册加速度传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);

        //注册陀螺传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);

        //注册磁场传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);

        //注册光线传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI);

        //注册方向传感器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_NORMAL);

        //获取当前手机支持的所有传感器
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            //输出当前传感器的名称
            tvSensor.append(sensor.getName() + "\n");
        }
    }

    private void initUI() {
        tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometer);
        tvMagnetic = (TextView) findViewById(R.id.tvMagnetic);
        tvLight = (TextView) findViewById(R.id.tvLight);
        tvOrientation = (TextView) findViewById(R.id.tvOrientation);
        tvSensor = (TextView) findViewById(R.id.tvSensor);
        tvGyroscope = (TextView) findViewById(R.id.tvGyroscope);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // 通过getType方法获得当前传回数据的传感器类型
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                String accelerometer = "加速度\n" + "X: " + sensorEvent.values[0] + "\n"
                        + "Y: " + sensorEvent.values[1] + "\n"
                        + "Z: " + sensorEvent.values[2] + "\n";
                tvAccelerometer.setText(accelerometer);
                break;
            case Sensor.TYPE_GYROSCOPE:
                if (timestamp != 0) {
                    // sensorEvent.timestamp表示当前的时间，单位是纳秒（一百万分之一毫秒）
                    final float dT = (sensorEvent.timestamp - timestamp) * NS2S;
                    angle[0] += sensorEvent.values[0] * dT;
                    angle[1] += sensorEvent.values[1] * dT;
                    angle[2] += sensorEvent.values[2] * dT;
                    tvGyroscope.setText("陀螺仪\n" + angle[0] + "\n" + angle[1] + "\n" + angle[2] + "\n");
                }
                timestamp = sensorEvent.timestamp;
                break;
            case Sensor.TYPE_LIGHT:
                tvLight.setText("亮度：" + sensorEvent.values[0] + "\n");
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                String magentic = "磁场\n" + "X: " + sensorEvent.values[0] + "\n"
                        + "Y: " + sensorEvent.values[1] + "\n"
                        + "Z: " + sensorEvent.values[2] + "\n";
                tvMagnetic.setText(magentic);
                break;
            case Sensor.TYPE_ORIENTATION:
                String orientation = "方向\n" + "X: " + sensorEvent.values[0] + "\n"
                        + "Y: " + sensorEvent.values[1] + "\n"
                        + "Z: " + sensorEvent.values[2] + "\n";
                tvOrientation.setText(orientation);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
