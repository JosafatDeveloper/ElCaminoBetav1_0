package mx.onlinesellers.elcaminobeta10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadAppActivity extends AppCompatActivity {

    ImageView loadIcon;
    private ManagerSQLite dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_app);
        // Animate Load
        loadIcon = (ImageView) findViewById(R.id.loadapp_imageview_loadicon);
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(-1);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());
        loadIcon.startAnimation(rotate);
        // Load Data and verify
        chargeAppANDVerify();
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("¿Seguro que quieres salir?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void chargeAppANDVerify() {
        // Carga parametros y valores
        dataSource = new ManagerSQLite(this);
        Cursor dataUserCursor = dataSource.getALLUserData();
        Map<String, Object> PARAMETROS = new HashMap<String, Object>();
        while (dataUserCursor.moveToNext()){
            PARAMETROS.put(dataUserCursor.getString(
                    dataUserCursor.getColumnIndexOrThrow(ManagerSQLite.ColumnUserData.USER_PARAMETRO)),
                    dataUserCursor.getString(dataUserCursor.getColumnIndexOrThrow(ManagerSQLite.ColumnUserData.USER_VALUE)));
        }
        dataUserCursor.close();
        // Verifica conecxión a internet
        // Verifica conexión con el Servidor
        // Verifica la API del Host este funcionando
        // Verifica que la app sea reciente
        // Verifica y actualiza parches o parametros
        // Verifica Compatibilidad con el dispositivo
        // Verifica Permisos de la app para su optimo funcionamiento
        // Carga los parametros Generales
        // Verifica la app si tiene un usuario
        // Load keys user
        String key_user = (String) PARAMETROS.get(ManagerSQLite.ParamterosDataUser.P_USER_ID);
        if(key_user.equals("null")){
            Intent intent = new Intent(this, NewOpenActivity.class);
            this.startActivity(intent);
        }else{
            // -> Verifica autenticiación del usuario

            // -> Verifica ultima sicronización de datos
        }

    }

    public void verificadorURLCode(String url){

    }
}
