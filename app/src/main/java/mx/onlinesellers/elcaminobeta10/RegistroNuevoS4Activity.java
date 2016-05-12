package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistroNuevoS4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuevo_s4);
    }
    // Demo Run
    public void onClickNext(View view){
        Intent intent = new Intent(RegistroNuevoS4Activity.this, RegistroNuevoS5Activity.class);
        startActivity(intent);
    }
}
