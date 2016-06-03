package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

public class UserPerfilMotosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public ListView listViewActivity;
    public Map<Integer, Map<String, Object>> listAdapterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_perfil_motos);
        configureBar("Perfil", getResources().getString(R.string.ELCIcon_usercircle));
        listViewActivity = (ListView) findViewById(R.id.listView);
        listViewActivity.setOnItemClickListener(this);
        createActivityList();
    }

    public void configureBar(String title, String icon){
        TextView bar_titulo = (TextView) findViewById(R.id.bar_textview_titulo);
        bar_titulo.setText(title);
        TextView bar_icon = (TextView) findViewById(R.id.bar_textview_icon);
        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        bar_icon.setTypeface(ElCaminoFontIcon);
        bar_icon.setText(icon);

        Button btns1 = (Button) findViewById(R.id.TabManagerS1);
        Button btns2 = (Button) findViewById(R.id.TabManagerS2);
        Button btns3 = (Button) findViewById(R.id.TabManagerS3);
        btns1.setTypeface(ElCaminoFontIcon);
        btns2.setTypeface(ElCaminoFontIcon);
        btns3.setTypeface(ElCaminoFontIcon);
        btns3.setTextColor(getResources().getColor(R.color.ELCColorBlak));

    }
    public void returnHome(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
    }

    public void ClickTabManager(View v){
        String tagString = v.getTag().toString();
        Integer tagClick = Integer.parseInt(tagString);
        Class sendView;
        switch (tagClick){
            case 1:{
                sendView = UserPerfilActivity.class;
            }
            break;
            case 2:{
                sendView = UserPerfilContactEmeActivity.class;
            }
            break;
            case 3:{
                sendView = UserPerfilMotosActivity.class;
            }
            break;
            default:{
                sendView = this.getClass();
            }
            break;
        }
        if(this.getClass() != sendView){
            Intent intent = new Intent(this, sendView);
            this.startActivity(intent);
        }

    }

    public void createActivityList(){
        listAdapterActivity = new HashMap<>();
        Map<String, Object> Item01 = new HashMap<>();
        Item01.put("t", "Ducati");
        Item01.put("m", "Mosnter\n821 cc");
        Item01.put("l",  R.drawable.s2demologo);
        Item01.put("n",  R.drawable.s2demomoto);
        listAdapterActivity.put(0, Item01);

        Map<String, Object> Item02 = new HashMap<>();
        Item02.put("t", "Yahama");
        Item02.put("m", "Bolt Special R\n950 cc");
        Item02.put("l", R.drawable.s1demologo);
        Item02.put("n", R.drawable.s1demomoto);
        listAdapterActivity.put(1, Item02);


        listViewActivity.setAdapter(new AdpaterRowsActivityP1(this, listAdapterActivity));
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
