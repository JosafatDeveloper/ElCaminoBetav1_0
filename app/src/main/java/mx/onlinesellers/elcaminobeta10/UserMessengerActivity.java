package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.graphics.Color;
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

public class UserMessengerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    public ListView listViewActivity;
    public Map<Integer, Map<String, Object>> listAdapterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_messenger);
        configureBar("Mensajes", getResources().getString(R.string.ELCIcon_comments));
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
        btns1.setTextColor(getResources().getColor(R.color.ELCColorBlak));

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
                sendView = UserMessengerActivity.class;
            }
            break;
            case 2:{
                sendView = UserMessengerGroupActivity.class;
            }
            break;
            case 3:{
                sendView = UserMessengerMCActivity.class;
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
        Item01.put("t", "Luis Moncayo");
        Item01.put("m", "Hola como estas?");
        Item01.put("d", "hace 3 min");
        listAdapterActivity.put(0, Item01);

        Map<String, Object> Item02 = new HashMap<>();
        Item02.put("t", "Daniel Luna");
        Item02.put("m", "Ya te mande la ruta que vamos hacer");
        Item02.put("d", "hace 30 min");
        listAdapterActivity.put(1, Item02);

        Map<String, Object> Item03 = new HashMap<>();
        Item03.put("t", "Javier Arujo");
        Item03.put("m", "Ya tengo el casco");
        Item03.put("d", "ayer");
        listAdapterActivity.put(2, Item03);

        Map<String, Object> Item04 = new HashMap<>();
        Item04.put("t", "Alberto Olmos");
        Item04.put("m", "ya recibi tu mensjae");
        Item04.put("d", "hace 2 dias");
        listAdapterActivity.put(3, Item04);

        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        //listViewActivity.setOnItemClickListener(this);
        listViewActivity.setAdapter(new AdpaterRowsActivityS6(this, listAdapterActivity, ElCaminoFontIcon));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Intent intent = new Intent(this, UserMessengerChatActivity.class);
        //this.startActivity(intent);
    }
}
