package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dis2 on 03/06/16.
 */
public class ManagerSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ELCBD.db";
    public static final int DATABASE_VERSION = 2;

    public ManagerSQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la tabla Quotes
        db.execSQL(ManagerSQLite.CREATE_USERS_SCRIPT);
        db.execSQL(ManagerSQLite.CREATE_ROUTES_SCRIPT);
        db.execSQL(ManagerSQLite.CREATE_ROUTE_TRACK_SCRIPT);
        db.execSQL(ManagerSQLite.CREATE_ROUTE_TRACK_POINT_SCRIPT);
        db.execSQL(ManagerSQLite.CREATE_ROUTE_TRACK_CONFIG_SCRIPT);
        db.execSQL(ManagerSQLite.CREATE_USER_DATA_SCRIPT);
        db.execSQL(ManagerSQLite.INSERT_QUOTES_USER_DATA_SCRIPT_1_0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ManagerSQLite.CREATE_USER_DATA_SCRIPT);
        db.execSQL(ManagerSQLite.INSERT_QUOTES_USER_DATA_SCRIPT_1_0);
        //db.execSQL("ALTER TABLE routes_track_points RENAME TO routes_track_point");
        //db.execSQL("DROP TABLE routes_tracks");
        //db.execSQL(ManagerSQLite.CREATE_ROUTE_TRACK_SCRIPT);
    }
}