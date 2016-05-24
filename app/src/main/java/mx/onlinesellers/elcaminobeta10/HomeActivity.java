package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public Button btn_ruta;
    public Button btn_search;
    public Button btn_messenger;
    public Button btn_ajustes;
    public Button btn_account;

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

    public LinearLayout home_layout_footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        btn_ruta = (Button) findViewById(R.id.bar_btn_ruta);
        btn_search = (Button) findViewById(R.id.bar_btn_search);
        btn_messenger = (Button) findViewById(R.id.bar_btn_messenger);
        btn_ajustes = (Button) findViewById(R.id.bar_btn_ajustes);
        btn_account = (Button) findViewById(R.id.bar_btn_account);
        btn_ruta.setTypeface(fontAwesomeFont);
        btn_search.setTypeface(fontAwesomeFont);
        btn_messenger.setTypeface(fontAwesomeFont);
        btn_ajustes.setTypeface(fontAwesomeFont);
        btn_account.setTypeface(fontAwesomeFont);

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
        // A. Creamos el arreglo de Strings para llenar la lista
        String[] cosasPorHacer = new String[] { "r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01","r01",};
        // B. Creamos un nuevo ArrayAdapter con nuestra lista de cosasPorHacer
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, cosasPorHacer);
        // D. Asignamos el adaptador a nuestra lista
        listViewActivity.setAdapter(arrayAdapter);
        //listViewActivity.


    }
}
