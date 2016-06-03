package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class RidersActivity extends AppCompatActivity {

    public ListView listViewActivity;
    public Map<Integer, Map<String, Object>> listAdapterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riders);
        configureBar("Riders", getResources().getString(R.string.ELCIcon_helmet));
        configureBarMenu();
        listViewActivity = (ListView) findViewById(R.id.listView);
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
        Item01.put("t", "Moncayito");
        Item01.put("m", "Disponible");
        Item01.put("d", getResources().getString(R.string.ELCIcon_margencirculo));
        Item01.put("z", getResources().getString(R.string.ELCIcon_margencirculo));
        Item01.put("s", AsistenciaTelActivity.class);
        listAdapterActivity.put(0, Item01);

        Map<String, Object> Item02 = new HashMap<>();
        Item02.put("t", "Josafat Vargas");
        Item02.put("m", "No Conectado");
        Item02.put("d", getResources().getString(R.string.ELCIcon_margencirculo));
        Item02.put("z", getResources().getString(R.string.ELCIcon_margencirculo));
        Item02.put("s", AsistenciaTelActivity.class);
        listAdapterActivity.put(1, Item02);

        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        //listViewActivity.setOnItemClickListener(this);
        listViewActivity.setAdapter(new AdpaterRowsActivityS2(this, listAdapterActivity, ElCaminoFontIcon));



    }
}
