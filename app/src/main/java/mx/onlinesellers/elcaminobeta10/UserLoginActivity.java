package mx.onlinesellers.elcaminobeta10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }
    // Demo Run
    public void onClickNext(View view){
        Intent intent = new Intent(UserLoginActivity.this, UserLoginActivity.class);
        startActivity(intent);
    }
    public void onClickNext2(View view){
        Intent intent = new Intent(UserLoginActivity.this, RestablecerPassActivity.class);
        startActivity(intent);
    }
}
