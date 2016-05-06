package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RestablecerNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_new);
    }

    // Demo Run
    public void onClickNext(View view){
        Intent intent = new Intent(RestablecerNewActivity.this, UserLoginActivity.class);
        startActivity(intent);
    }
}
