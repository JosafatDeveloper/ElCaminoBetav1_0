package mx.onlinesellers.elcaminobeta10.ELCEXTRAS;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.security.Key;
import java.util.Map;

/**
 * Created by dis2 on 14/09/16.
 */
public class ELCSENSORS extends Service implements SensorEventListener{
    // Context
    private Context ctx;
    // Sensor
    private final SensorManager mSensorManager;
    private final Sensor mAccelerometer;
    private final Sensor mGiroscope;
    private float[] giroscopeData;
    private float[] accelerometerData;

    private TextView labelG;
    private TextView labelA;

    // Init
    public ELCSENSORS(){
        super();
        this.ctx = this.getApplicationContext();
        mSensorManager = (SensorManager)this.ctx.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGiroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }
    public ELCSENSORS(Context c){
        super();
        this.ctx = c;
        mSensorManager = (SensorManager)this.ctx.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGiroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Inicializador
    public void startSensor() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGiroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void resumeSensor() {
        startSensor();
    }

    public void pauseSensor() {
        mSensorManager.unregisterListener(this);
    }

    // Event
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensorActivity = event.sensor;
        if (sensorActivity.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            /*
            giroscopeData[0] = x;
            giroscopeData[1] = y;
            giroscopeData[2] = z;
            */
            labelA.setText("T_ACC x:"+x+" y:"+y+" z:"+z);
            //Log.i("LOG", "TYPE_ACCELEROMETER x:"+x+" y:"+y+" z:"+z);
        }
        if (sensorActivity.getType() == Sensor.TYPE_GYROSCOPE){
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];
            /*
            accelerometerData[0] = axisX;
            accelerometerData[1] = axisY;
            accelerometerData[2] = axisZ;
            */
            labelG.setText("T_GY Ax:"+axisX+" Ay:"+axisY+" Az:"+axisZ);
            //Log.i("LOG", "TYPE_GYROSCOPE Ax:"+axisX+" Ay:"+axisY+" Az:"+axisZ);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void LogLabel(TextView gLabel, TextView aLabel){
        labelA = aLabel;
        labelG = gLabel;
    }
}
