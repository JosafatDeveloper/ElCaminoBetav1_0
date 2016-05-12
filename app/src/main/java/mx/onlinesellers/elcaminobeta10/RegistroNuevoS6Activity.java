package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistroNuevoS6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuevo_s6);
    }

    // Demo Run
    public void onClickNext(View view){
        Intent intent = new Intent(RegistroNuevoS6Activity.this, RegistroNuevoS7Activity.class);
        startActivity(intent);
    }
}
