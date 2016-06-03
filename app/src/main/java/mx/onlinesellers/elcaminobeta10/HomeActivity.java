package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public Button home_btn_b1;
    public Button home_btn_b2;
    public Button home_btn_b3;
    public Button home_btn_b4;
    public Button home_btn_b5;
    public Button home_btn_b6;

    public TextView home_bstatus_s1;
    public TextView home_bstatus_s2;
    public TextView home_bstatus_s3;
    public TextView home_bstatus_s4;

    public ListView listViewActivity;
    public Map<Integer, Map<String, Object>> listAdapterActivity;

    public LinearLayout home_layout_footer;

    private ManagerSQLite dataSource;
    private SimpleCursorAdapter adapter;
    public final static int ADD_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");

        home_btn_b1 = (Button) findViewById(R.id.home_btn_b1);
        home_btn_b2 = (Button) findViewById(R.id.home_btn_b2);
        home_btn_b3 = (Button) findViewById(R.id.home_btn_b3);
        home_btn_b4 = (Button) findViewById(R.id.home_btn_b4);
        home_btn_b5 = (Button) findViewById(R.id.home_btn_b5);
        home_btn_b6 = (Button) findViewById(R.id.home_btn_b6);

        home_btn_b1.setTypeface(ElCaminoFontIcon);
        home_btn_b2.setTypeface(ElCaminoFontIcon);
        home_btn_b3.setTypeface(ElCaminoFontIcon);
        home_btn_b4.setTypeface(ElCaminoFontIcon);
        home_btn_b5.setTypeface(ElCaminoFontIcon);
        home_btn_b6.setTypeface(ElCaminoFontIcon);

        home_btn_b1.setOnClickListener(this);
        home_btn_b2.setOnClickListener(this);
        home_btn_b3.setOnClickListener(this);
        home_btn_b4.setOnClickListener(this);
        home_btn_b5.setOnClickListener(this);
        home_btn_b6.setOnClickListener(this);


        home_bstatus_s1 = (TextView) findViewById(R.id.home_bar_status_routr_s1);
        home_bstatus_s2 = (TextView) findViewById(R.id.home_bar_status_routr_s2);
        home_bstatus_s3 = (TextView) findViewById(R.id.home_bar_status_routr_s3);
        home_bstatus_s4 = (TextView) findViewById(R.id.home_bar_status_routr_s4);

        home_bstatus_s1.setTypeface(ElCaminoFontIcon);
        home_bstatus_s2.setTypeface(ElCaminoFontIcon);
        home_bstatus_s3.setTypeface(ElCaminoFontIcon);
        home_bstatus_s4.setTypeface(ElCaminoFontIcon);

        listViewActivity = (ListView) findViewById(R.id.listViewActiviry);

        home_layout_footer = (LinearLayout) findViewById(R.id.linearLayoutMenuHomeBarBot);

        ordeneMenuArea();
        createActivityList();
        configureBarMenu();

        dataSource = new ManagerSQLite(this);
        Cursor users = dataSource.getAllUsers();
        if(users.getCount() == 0){
            Intent intent = new Intent(this, DeveloperUserNewActivity.class);
            startActivityForResult(intent, ADD_REQUEST_CODE);
        }

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
        if(this.getClass() != HomeActivity.class){
            Intent intent = new Intent(this, HomeActivity.class);
            this.startActivity(intent);
        }
    }

    public void sendMenuClick(View v){
        String tagString = v.getTag().toString();
        Integer tagClick = Integer.parseInt(tagString);
        Class sendView;
        switch (tagClick){
            case 1:{
                sendView = RoutesActivity.class;
            }
            break;
            case 2:{
                sendView = HomeActivity.class;
            }
            break;
            case 3:{
                sendView = UserMessengerActivity.class;
            }
            break;
            case 4:{
                sendView = HomeActivity.class;
            }
            break;
            case 5:{
                sendView = UserPerfilActivity.class;
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

    public void ordeneMenuArea(){
        // Size Screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        //int screenHeight = displayMetrics.heightPixels;

        // Ordene Menu
        float btn_width = screenWidth / 6;
        int btn_heigth = home_layout_footer.getLayoutParams().height-20;
        int post_y_btn = (int) (home_layout_footer.getLayoutParams().height / 2) - (btn_heigth/2);
        home_layout_footer.setPadding(0, post_y_btn, 0, 0);
        home_btn_b1.setLayoutParams(new LinearLayout.LayoutParams((int)btn_width, btn_heigth));
        home_btn_b2.setLayoutParams(new LinearLayout.LayoutParams((int)btn_width, btn_heigth));
        home_btn_b3.setLayoutParams(new LinearLayout.LayoutParams((int)btn_width, btn_heigth));
        home_btn_b4.setLayoutParams(new LinearLayout.LayoutParams((int)btn_width, btn_heigth));
        home_btn_b5.setLayoutParams(new LinearLayout.LayoutParams((int)btn_width, btn_heigth));
        home_btn_b6.setLayoutParams(new LinearLayout.LayoutParams((int)btn_width, btn_heigth));
    }

    public void createActivityList(){
        listAdapterActivity = new HashMap<>();
        Map<String, Object> Item01 = new HashMap<>();
        Item01.put("t", "Moncayito");
        Item01.put("m", "¿Que onda donde estas?");
        Item01.put("d", "hace 5 minutos");
        Item01.put("s", UserMessengerActivity.class);
        listAdapterActivity.put(0, Item01);

        Map<String, Object> Item02 = new HashMap<>();
        Item02.put("t", "Flamas");
        Item02.put("m", "Flamas te a compratido una nueva ruta");
        Item02.put("d", "hace 3 horas");
        Item02.put("s", UserLoginActivity.class);
        listAdapterActivity.put(1, Item02);

        Map<String, Object> Item03 = new HashMap<>();
        Item03.put("t", "YAMAHA");
        Item03.put("m", "Nueva Moto RT5000 con 250hp");
        Item03.put("d", "7:12 pm");
        Item03.put("s", PublicidadViewActivity.class);
        listAdapterActivity.put(2, Item03);


        Map<String, Object> Item04 = new HashMap<>();
        Item04.put("t", "MC:Los Quemados - Flamas");
        Item04.put("m", "Registrate para la nueva rodada México-Pachuca");
        Item04.put("d", "hace 3 dias");
        Item04.put("s", UserMessengerActivity.class);
        listAdapterActivity.put(3, Item04);


        /*
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, cosasPorHacer);
                */

        listViewActivity.setOnItemClickListener(this);
        listViewActivity.setAdapter(new AdpaterRowsActivity(this, listAdapterActivity));
        // D. Asignamos el adaptador a nuestra lista
        //listViewActivity.setAdapter(arrayAdapter);
        //listViewActivity.


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("LOGMA", "row:"+ position+ " id:"+ id);
        Intent intent = new Intent(HomeActivity.this, (Class) listAdapterActivity.get(position).get("s"));
        HomeActivity.this.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Log.d("LOGMA", v.getTag().toString());
        String tagString = v.getTag().toString();
        Integer tagClick = Integer.parseInt(tagString);
        Class sendView;
        boolean btn_footer_click = false;
        switch (tagClick){
            case 1:{
                sendView = DestinosActivity.class;
                btn_footer_click = true;
            }
            break;
            case 2:{
                sendView = RescueProveActivity.class;
                btn_footer_click = true;
            }
            break;
            case 3:{
                sendView = AsistenciaTelActivity.class;
                btn_footer_click = true;
            }
            break;
            case 4:{
                sendView = CentroServicioActivity.class;
                btn_footer_click = true;
            }
            break;
            case 5:{
                sendView = TiendasActivity.class;
                btn_footer_click = true;
            }
            break;
            case 6:{
                sendView = RidersActivity.class;
                btn_footer_click = true;
            }
            break;
            default:{
                sendView = HomeActivity.class;
            }
            break;
        }
        if(btn_footer_click){
            Intent intent = new Intent(this, sendView);
            this.startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //Insertando el registro con los datos del formulario
                String username = data.getStringExtra("user_name");
                String useralias = data.getStringExtra("user_alias");
                dataSource.saveUserRow(username,useralias);
                //Refrescando la lista manualmente
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
