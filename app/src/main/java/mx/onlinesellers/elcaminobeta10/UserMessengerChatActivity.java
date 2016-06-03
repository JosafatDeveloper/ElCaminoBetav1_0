package mx.onlinesellers.elcaminobeta10;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserMessengerChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_messenger_chat);


        configureBar("Mensajes", getResources().getString(R.string.ELCIcon_comments));
    }

    public void configureBar(String title, String icon){
        RelativeLayout bar_layout = (RelativeLayout) findViewById(R.id.bar_layout);
        TextView bar_titulo = (TextView) findViewById(R.id.bar_textview_titulo);
        bar_titulo.setText(title);
        TextView bar_icon = (TextView) findViewById(R.id.bar_textview_icon);
        Typeface ElCaminoFontIcon = Typeface.createFromAsset(getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
        bar_icon.setTypeface(ElCaminoFontIcon);
        bar_icon.setText(icon);
    }


}
