package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CentroServicioActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public ListView listViewActivity;
    public Map<Integer, Map<String, Object>> listAdapterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centro_servicio);
        configureBar("Centros de Servicios", getResources().getString(R.string.ELCIcon_toolfix));
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
        listAdapterActivity = new HashMap<>();
        Map<String, Object> Item01 = new HashMap<>();
        Item01.put("t", "Reparaciones Moto House");
        Item01.put("m", "Disponible");
        Item01.put("d", getResources().getString(R.string.ELCIcon_pointgps));
        Item01.put("p", "Zona Norte");
        Item01.put("v", estrellaString(5));
        Item01.put("c", new LatLng(19.4913243,-99.12135));
        Item01.put("s", AsistenciaTelActivity.class);
        listAdapterActivity.put(0, Item01);

        Map<String, Object> Item02 = new HashMap<>();
        Item02.put("t", "Centro de Servicio Moto");
        Item02.put("m", "Disponible");
        Item02.put("d", getResources().getString(R.string.ELCIcon_pointgps));
        Item02.put("p", "Centro");
        Item02.put("v", estrellaString(5));
        Item02.put("c", new LatLng(19.4335817,-99.1417838));
        Item02.put("s", AsistenciaTelActivity.class);
        listAdapterActivity.put(1, Item02);

        Map<String, Object> Item03 = new HashMap<>();
        Item03.put("t", "Servicios Honda");
        Item03.put("m", "Sin conecxión");
        Item03.put("d", getResources().getString(R.string.ELCIcon_pointgps));
        Item03.put("p", "Coacalco");
        Item03.put("v", estrellaString(2));
        Item03.put("c", new LatLng(19.6326242,-99.1177653));
        Item03.put("s", AsistenciaTelActivity.class);
        listAdapterActivity.put(2, Item03);

        Map<String, Object> Item04 = new HashMap<>();
        Item04.put("t", "Refacciones y Servicios Moto");
        Item04.put("m", "Disponible");
        Item04.put("d", getResources().getString(R.string.ELCIcon_pointgps));
        Item04.put("p", "Puebla");
        Item04.put("v", estrellaString(1));
        Item04.put("c", new LatLng(19.0400176,-98.3330513));
        Item04.put("s", AsistenciaTelActivity.class);
        listAdapterActivity.put(3, Item04);

        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        //listViewActivity.setOnItemClickListener(this);
        listViewActivity.setAdapter(new AdpaterRowsActivityS3(this, listAdapterActivity, ElCaminoFontIcon));

    }

    public String estrellaString(int numStart){
        String returnStart = "";
        switch (numStart){
            case 0:{
                returnStart = "<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>";
            }
            break;
            case 1:{
                returnStart = "<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>";
            }
            break;
            case 2:{
                returnStart = "<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>";
            }
            break;
            case 3:{
                returnStart = "<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>";
            }
            break;
            case 4:{
                returnStart = "<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>";
            }
            break;
            case 5:{
                returnStart = "<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#F0E400>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>";
            }
            break;
            default:{
                returnStart = "<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>"+"<font color=#EBEBEB>"+getResources().getString(R.string.ELCIcon_estrellafill)+"</font>";
            }
            break;
        }
        return returnStart;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MapsVIewElementActivity.class);
        intent.putExtra("t","Centros de Servicios");
        intent.putExtra("i",getResources().getString(R.string.ELCIcon_toolfix));
        Map<String, Object> ItemSend = listAdapterActivity.get(position);
        intent.putExtra("d", (Serializable) ItemSend);
        this.startActivity(intent);
    }
}
