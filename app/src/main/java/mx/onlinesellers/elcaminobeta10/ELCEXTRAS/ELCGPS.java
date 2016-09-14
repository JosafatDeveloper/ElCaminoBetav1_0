package mx.onlinesellers.elcaminobeta10.ELCEXTRAS;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dis2 on 07/06/16.
 */
public class ELCGPS extends Service implements LocationListener {
    // Context
    private Context ctx;
    // Coordenadas
    public double latitud;
    public double longitud;
    public double altitud;
    public double latitud_last;
    public double longitud_last;
    public double altitud_last;
    // Servicios
    private double distancia = 0;
    private String unidad = "KMH";
    private double velocidad = 0;
    private long timergps = 0;
    // GPS
    private Criteria criteria;
    private String providerNow;
    private boolean GPSActive = false;
    private Location locationResquest;
    private LocationManager locationManager;
    private float minDistancia = 10;
    private long minTiempo = 5 * 1000;

    // Init
    public ELCGPS() {
        super();
        this.ctx = this.getApplicationContext();
    }

    public ELCGPS(Context c) {
        super();
        this.ctx = c;
    }

    public ELCGPS(Context c, Criteria crta) {
        super();
        this.ctx = c;
        newLocationListener(crta);
    }

    // IBinder
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Create Location Listener
    public void newLocationListener(Criteria crta) {
        criteria = (Criteria) crta;
        locationManager = (LocationManager) ctx.getSystemService(LOCATION_SERVICE);
        boolean isGPS_PROVIDER = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNETWORK_PROVIDER = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isPASSIVE_PROVIDER = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
        Log.d("LOGMA", "isGPS:" + isGPS_PROVIDER);
        Log.d("LOGMA", "isNET:" + isNETWORK_PROVIDER);
        Log.d("LOGMA", "isPAS:" + isPASSIVE_PROVIDER);
        try {
            startLoaction();
        } catch (Exception e) {
            Log.e("LOG", "Error en el servicio GPS:" + e.toString());
        }
    }

    // Search Proviner Eneabled
    private Location getLastKnownLocationSearch() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Log.i("LOG", "Provider Disponibles:" + provider);
            if (Build.VERSION.SDK_INT >= 23) { // Marshmallow+
                if (ContextCompat.checkSelfPermission(ctx.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(ctx.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Location l = locationManager.getLastKnownLocation(provider);
                    if (l == null) {
                        continue;
                    }
                    if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                        bestLocation = l;
                    }
                }
            } else {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }
        }
        return bestLocation;
    }

    // Start Location
    public void startLoaction() {
        if (Build.VERSION.SDK_INT >= 23) { // Marshmallow+
            Log.d("LOG", "SDK_INT:>=23");
            if (ContextCompat.checkSelfPermission(ctx.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(ctx.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                Log.i("LOG", "Permisos:true SDK_INT:>=23");
                providerNow = locationManager.getBestProvider(criteria, true);
                locationManager.requestLocationUpdates(providerNow, minTiempo, minTiempo, this);
                locationResquest = locationManager.getLastKnownLocation(providerNow);
                if (locationResquest == null) {
                    Log.e("LOG", "Error: Al cargar ultima localización");
                    locationResquest = getLastKnownLocationSearch();
                    logLocation(locationResquest);
                } else {
                    logLocation(locationResquest);
                }
            } else {
                Log.e("LOG", "Error: Permiso denegado GPS");
            }
        } else {
            providerNow = locationManager.getBestProvider(criteria, true);
            locationManager.requestLocationUpdates(providerNow, minTiempo, minTiempo, this);
            locationResquest = locationManager.getLastKnownLocation(providerNow);
            if (locationResquest == null) {
                Log.e("LOG", "Error: Al cargar ultima localización");
                locationResquest = getLastKnownLocationSearch();
                logLocation(locationResquest);
            } else {
                logLocation(locationResquest);
            }
        }
    }

    // Stop Location
    public void stopLocation() {
        if (Build.VERSION.SDK_INT >= 23) { // Marshmallow+
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locationManager.removeUpdates(this);
            }
        }else{
            locationManager.removeUpdates(this);
        }
    }

    // Log Location
    private void logLocation(Location location){
        Log.i("LOG", "Start Location LAT:" + location.getLatitude() + " LON:" + location.getLongitude() + " Pro:" + location.getProvider() + " Acc:" + location.getAccuracy() + " Time:" + getDate(location.getTime(), "dd/MM/yyyy hh:mm:ss.SSS"));
        Log.i("LOG", "Extra Location Altitude:"+location.getAltitude());
        Log.i("LOG", "Extra Location Speed:"+location.getSpeed());
        Log.i("LOG", "Extra Location Bearing:"+location.getBearing());
    }

    // Create Criteria
    private Criteria newCriteria(int accuracy, boolean altitude, boolean bearing, boolean cost, int power, boolean speed){
        Criteria newC = new Criteria();
        if(Criteria.ACCURACY_HIGH == accuracy || Criteria.ACCURACY_MEDIUM == accuracy){
            newC.setAccuracy(Criteria.ACCURACY_FINE);
        }else if(Criteria.ACCURACY_LOW == accuracy){
            newC.setAccuracy(Criteria.ACCURACY_COARSE);
        }else{
            newC.setAccuracy(accuracy);
        }
        newC.setAltitudeRequired(altitude);
        newC.setBearingRequired(bearing);
        newC.setCostAllowed(cost);
        newC.setPowerRequirement(power);
        newC.setSpeedRequired(speed);
        if(Criteria.ACCURACY_HIGH == accuracy || Criteria.ACCURACY_MEDIUM == accuracy || Criteria.ACCURACY_LOW == accuracy){
            newC.setHorizontalAccuracy(accuracy);
            newC.setVerticalAccuracy(accuracy);
            if(Criteria.ACCURACY_HIGH == accuracy || Criteria.ACCURACY_MEDIUM == accuracy){
                newC.setBearingAccuracy(accuracy);
                newC.setSpeedAccuracy(accuracy);
            }
        }
        return newC;
    }

    public Criteria newHighCriteria(){
        minDistancia = 1;
        minTiempo = 1 * 1000;
        return newCriteria(Criteria.ACCURACY_HIGH, true, false, true, Criteria.POWER_HIGH, true);
    }
    public Criteria newFineCriteria(){
        minDistancia = 1;
        minTiempo = 2 * 1000;
        return newCriteria(Criteria.ACCURACY_FINE, false, false, true, Criteria.POWER_HIGH, true);
    }
    public Criteria newCoarseCriteria(){
        minDistancia = 5;
        minTiempo = 4 * 1000;
        return newCriteria(Criteria.ACCURACY_COARSE, false, false, true, Criteria.POWER_MEDIUM, true);
    }
    public Criteria newLowCriteria(){
        minDistancia = 10;
        minTiempo = 10 * 1000;
        return newCriteria(Criteria.ACCURACY_LOW, false, false, false, Criteria.POWER_LOW, false);
    }

    // GPS Listener
    @Override
    public void onLocationChanged(Location location) {
        locationResquest = location;
        Log.i("LOG", "Location Change Lat:"+location.getLatitude()+" Lon:"+location.getLongitude());
        latitud = location.getLatitude();
        longitud = location.getLongitude();
        altitud = location.getAltitude();
        latitud_last = latitud;
        longitud_last = longitud;
        altitud_last = altitud;
        velocidad = location.getSpeed();
        timergps = location.getTime();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("LOGMA", "onStatusChanged:");
        switch (status) {
            case LocationProvider.AVAILABLE: {
                Log.d("LOGMA", "AVAILABLE GPS:" + provider);
            }
            break;
            case LocationProvider.OUT_OF_SERVICE: {
                Log.d("LOGMA", "OUT_OF_SERVICE GPS:" + provider);
            }
            break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE: {
                Log.d("LOGMA", "TEMPORARILY_UNAVAILABLE GPS:" + provider);
            }
            break;
        }

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("LOG", "ProviderEnabled:"+provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.e("LOG", "ProviderDisabled:"+provider);
    }

    // Set
    public void setChangeMPH(){unidad="MPH";}
    public void setChangeKMH(){unidad="KMH";}

    // Get
    public double getVelocidad(){return velocidad;}
    public Location getLocation(){return locationResquest;}

    // Functions
    public float calcularDistancia(double startLatitud, double startLongitud, double nowLatitud, double nowLongitud) {
        float[] results = new float[1];
        Location.distanceBetween(
                startLatitud, startLongitud,
                nowLatitud, nowLongitud, results);
        return results[0];
    }

    public double calcularVelocidad() {
        double calcularV = 0;
        switch (unidad){
            case "KMH":{
                calcularV = (double) ((velocidad * 3600) / 1000);
            }
            break;
            case "MPH":{
                calcularV = (double) (velocidad * 2.2369);
            }
            break;
            default:{
                calcularV = (double) ((velocidad * 3600) / 1000);
            }
        }
        return calcularV;
    }
    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
