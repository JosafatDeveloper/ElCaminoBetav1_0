package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RestablecerPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_pass);
    }

    // Demo Run
    public void onClickNext(View view){
        Intent intent = new Intent(RestablecerPassActivity.this, RestablecerNewActivity.class);
        startActivity(intent);
    }
}
