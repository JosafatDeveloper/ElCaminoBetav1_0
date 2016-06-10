package mx.onlinesellers.elcaminobeta10;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DestinosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public ListView listViewActivity;
    private ManagerSQLite dataSource;
    private AdpaterRowsActivityS4 adapter;

    int CloudSelect = 5;
    int PauseSelect = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinos);
        configureBar("Destinos", getResources().getString(R.string.ELCIcon_locationarrow));
        configureBarMenu();
        listViewActivity = (ListView) findViewById(R.id.listView);
        listViewActivity.setOnItemClickListener(this);
        createActivityList();
        Button add_btn = (Button) findViewById(R.id.destinos_btn_add);
        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        add_btn.setTypeface(ElCaminoFontIcon);
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
        Cursor cursor = dataSource.getAllRoutes();
        adapter = new AdpaterRowsActivityS4(this, cursor, 0);
        listViewActivity.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView title = (TextView) view.findViewById(R.id.row_text_title);
        Intent intent = new Intent(this, DestinosSelectActivity.class);
        intent.putExtra("t", title.getText().toString());
        intent.putExtra("idR", id);
        this.startActivity(intent);
        Log.d("LOGMA", "DECLickItem");
    }

    public void onItemClickPlay(View v, long id){
        Cursor cursordata = dataSource.countTrack((int)id);
        String[] data;
        if (cursordata != null) {
            while(cursordata.moveToNext()) {
                data = new String[1];
                data[0] = Integer.toString(cursordata.getInt(0));
                String newNametrack = " Vuelta "+ data[0];
                int id_track = dataSource.addNewRouteTrack((int)id, newNametrack);
                int id_ajustes_track = dataSource.addTrackConfig(id_track, CloudSelect, PauseSelect, 0,0,0,0,0);
                cursordata.close();
                Intent intent = new Intent(this, PlayRoutrLookActivity.class);
                intent.putExtra("TRACK_ID", id_track);
                intent.putExtra("TRACK_CONFIG_ID", id_ajustes_track);
                intent.putExtra("ROUTE_ID", (int)id);
                intent.putExtra("CLOUD_SELECT", CloudSelect);
                intent.putExtra("PAUSEA_SELECT", PauseSelect);
                intent.putExtra("NAME_TRACK", newNametrack);
                intent.putExtra("PUBLIC_TYPE", 0);
                this.startActivity(intent);
            }
        }
    }


    public void clickAddDestino(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nombre del destino *obligatorio");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText().toString() != null && !input.getText().toString().isEmpty()){
                    dataSource.saveRoute(input.getText().toString());
                    adapter.changeCursor(dataSource.getAllRoutes());
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(DestinosActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Sin nombre de destino");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }


}
