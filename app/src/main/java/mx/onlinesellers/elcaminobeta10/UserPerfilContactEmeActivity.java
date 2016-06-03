package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserPerfilContactEmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_perfil_contact_eme);
        configureBar("Perfil", getResources().getString(R.string.ELCIcon_usercircle));
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
        btns2.setTextColor(getResources().getColor(R.color.ELCColorBlak));

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

}
