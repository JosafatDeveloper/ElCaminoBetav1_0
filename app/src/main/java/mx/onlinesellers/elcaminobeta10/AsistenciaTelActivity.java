package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AsistenciaTelActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public ListView listViewActivity;
    public Map<Integer, Map<String, Object>> listAdapterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia_tel);
        configureBar("Expertos", getResources().getString(R.string.ELCIcon_telephonecircle));
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
        Item01.put("t", "Luis Moncayo");
        Item01.put("m", "Mécanico");
        Item01.put("d", getResources().getString(R.string.ELCIcon_telephonecircle));
        Item01.put("s", AsistenciaTelActivity.class);
        Item01.put("n", "5536486463");
        listAdapterActivity.put(0, Item01);

        Map<String, Object> Item02 = new HashMap<>();
        Item02.put("t", "Daniel Luna");
        Item02.put("m", "Instructor");
        Item02.put("d", getResources().getString(R.string.ELCIcon_telephonecircle));
        Item02.put("s", AsistenciaTelActivity.class);
        Item02.put("n", "5536486463");
        listAdapterActivity.put(1, Item02);

        Map<String, Object> Item03 = new HashMap<>();
        Item03.put("t", "Javier Arujo");
        Item03.put("m", "Equipo de Seguridad");
        Item03.put("d", getResources().getString(R.string.ELCIcon_telephonecircle));
        Item03.put("s", AsistenciaTelActivity.class);
        Item02.put("n", "5536486463");
        listAdapterActivity.put(2, Item03);

        Map<String, Object> Item04 = new HashMap<>();
        Item04.put("t", "Alberto Olmos");
        Item04.put("m", "Ascesor de Seguros");
        Item04.put("d", getResources().getString(R.string.ELCIcon_telephonecircle));
        Item04.put("s", AsistenciaTelActivity.class);
        Item02.put("n", "5536486463");
        listAdapterActivity.put(3, Item04);

        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        //listViewActivity.setOnItemClickListener(this);
        listViewActivity.setAdapter(new AdpaterRowsActivityS1(this, listAdapterActivity, ElCaminoFontIcon));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Uri numero = Uri.parse( "tel:" + (String) listAdapterActivity.get(position).get("n"));
        Intent intent = new Intent(Intent.ACTION_CALL, numero);
        this.startActivity(intent);
    }
}
