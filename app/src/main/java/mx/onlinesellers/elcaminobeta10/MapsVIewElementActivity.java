package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapsVIewElementActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Map<String, Object> itemSend;
    public RelativeLayout viewplay;
    public Button play_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view_element);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        viewplay = (RelativeLayout) findViewById(R.id.mapitem_view_play);
        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        play_btn = (Button) findViewById(R.id.mapitem_btn_play);
        play_btn.setTypeface(ElCaminoFontIcon);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            configureBar(extras.getString("t"), extras.getString("i"));
            itemSend = (Map<String,Object>) extras.getSerializable("d");
            LatLng coordenadas = (LatLng)itemSend.get("c");
            Log.d("LOGMA", ""+coordenadas.latitude);
            if(extras.getBoolean("a")){
                viewplay.setVisibility(View.VISIBLE);
            }else{
                viewplay.setVisibility(View.GONE);
            }

        } else {
            configureBar("Error", "");
            viewplay.setVisibility(View.GONE);
        }
        configureBarMenu();
    }

    public void configureBar(String title, String icon){
        TextView bar_titulo = (TextView) findViewById(R.id.bar_footer_titulo);
        bar_titulo.setText(title);
        TextView bar_icon = (TextView) findViewById(R.id.bar_footer_icon);
        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        bar_icon.setTypeface(ElCaminoFontIcon);
        bar_icon.setText(icon);
    }
    public void configureBarMenu(){
        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        TextView bar_btn_ruta = (Button) findViewById(R.id.bar_btn_ruta);
        TextView bar_btn_search = (Button) findViewById(R.id.bar_btn_search);
        TextView bar_btn_messenger = (Button) findViewById(R.id.bar_btn_messenger);
        TextView bar_btn_ajustes = (Button) findViewById(R.id.bar_btn_ajustes);
        TextView bar_btn_account = (Button) findViewById(R.id.bar_btn_account);
        bar_btn_ruta.setTypeface(ElCaminoFontIcon);
        bar_btn_search.setTypeface(ElCaminoFontIcon);
        bar_btn_messenger.setTypeface(ElCaminoFontIcon);
        bar_btn_ajustes.setTypeface(ElCaminoFontIcon);
        bar_btn_account.setTypeface(ElCaminoFontIcon);
    }
    public void returnHome(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = (LatLng) itemSend.get("c");
        mMap.addMarker(new MarkerOptions().position(sydney).title((String)itemSend.get("t")));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

    }

    public void clickItemMapItem(View v){
        Intent intent = new Intent(this, PlayRoutrLookActivity.class);
        intent.putExtra("t", (String)itemSend.get("t"));
        intent.putExtra("m", (String)itemSend.get("m"));
        this.startActivity(intent);

    }
}
