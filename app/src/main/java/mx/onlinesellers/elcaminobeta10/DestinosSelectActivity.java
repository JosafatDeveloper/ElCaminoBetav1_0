package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

public class DestinosSelectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    Button btn_add_new;
    public ListView listViewActivity;
    private ManagerSQLite dataSource;
    private AdpaterRowsActivityS7 adapter;
    public int IDROUTE;
    int CloudSelect = 5;
    int PauseSelect = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinos_select);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            configureBar(extras.getString("t"), getResources().getString(R.string.ELCIcon_locationarrow));
            btn_add_new = (Button) findViewById(R.id.destinos_add_viaje);
            btn_add_new.setText("+ Agregar recorrido a "+extras.getString("t"));
            IDROUTE = (int) extras.getLong("idR");
        }
        configureBarMenu();
        listViewActivity = (ListView) findViewById(R.id.listView);
        listViewActivity.setOnItemClickListener(this);
        createActivityList();
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

    public void createActivityList(){
        dataSource = new ManagerSQLite(this);
        Cursor cursor = dataSource.getAllTrackInfo();
        adapter = new AdpaterRowsActivityS7(this, cursor, 0);
        listViewActivity.setAdapter(adapter);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void newViajeCreate(View v){
        Cursor cursordata = dataSource.countTrack(IDROUTE);
        String[] data;
        if (cursordata != null) {
            while(cursordata.moveToNext()) {
                data = new String[1];
                data[0] = Integer.toString(cursordata.getInt(0));
                String newNametrack = " Vuelta "+ data[0];
                int id_track = dataSource.addNewRouteTrack(IDROUTE, newNametrack);
                int id_ajustes_track = dataSource.addTrackConfig(id_track, CloudSelect, PauseSelect, 0,0,0,0,0);
                cursordata.close();
                Intent intent = new Intent(this, PlayRoutrLookActivity.class);
                intent.putExtra("TRACK_ID", id_track);
                intent.putExtra("TRACK_CONFIG_ID", id_ajustes_track);
                intent.putExtra("ROUTE_ID", IDROUTE);
                intent.putExtra("CLOUD_SELECT", CloudSelect);
                intent.putExtra("PAUSEA_SELECT", PauseSelect);
                intent.putExtra("NAME_TRACK", newNametrack);
                intent.putExtra("PUBLIC_TYPE", 0);
                this.startActivity(intent);
            }
        }
    }
}
