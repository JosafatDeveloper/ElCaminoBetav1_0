package mx.onlinesellers.elcaminobeta10;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

public class PlayRoutrLookActivity extends AppCompatActivity {

    public Button acti_sA_btn;
    public Button acti_sB_btn;
    public Button acti_sC_btn;

    public TextView acti_icon;
    public TextView acti_titulo;
    public TextView acti_describe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_routr_look);


        acti_sA_btn = (Button) findViewById(R.id.look_btn_a);
        acti_sB_btn = (Button) findViewById(R.id.look_btn_b);
        acti_sC_btn = (Button) findViewById(R.id.look_btn_c);

        acti_icon = (TextView) findViewById(R.id.look_icon_row);
        acti_titulo = (TextView) findViewById(R.id.look_titulo);
        acti_describe = (TextView) findViewById(R.id.look_describe);

        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        acti_icon.setTypeface(ElCaminoFontIcon);
        acti_sA_btn.setTypeface(ElCaminoFontIcon);
        acti_sB_btn.setTypeface(ElCaminoFontIcon);
        acti_sC_btn.setTypeface(ElCaminoFontIcon);


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            acti_titulo.setText(extras.getString("t"));
            acti_describe.setText(extras.getString("m"));
        }
    }
}
