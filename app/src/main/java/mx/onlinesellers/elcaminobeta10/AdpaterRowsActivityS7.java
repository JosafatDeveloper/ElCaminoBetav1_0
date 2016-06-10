package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by dis2 on 03/06/16.
 */
public class AdpaterRowsActivityS7 extends CursorAdapter {

    Typeface ElCaminoFontIcon;
    public AdpaterRowsActivityS7(Context context, Cursor cursor, int flags){
        super(context, cursor, 0);
        ElCaminoFontIcon = Typeface.createFromAsset(context.getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return  LayoutInflater.from(context).inflate(R.layout.row_activity_s4, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.row_text_title);
        title.setText(cursor.getString(cursor.getColumnIndexOrThrow(ManagerSQLite.ColumnRoutesTrack.ROUTE_TRACK_NAME)));
        TextView describe = (TextView) view.findViewById(R.id.row_text_describe);
        describe.setText(cursor.getString(cursor.getColumnIndexOrThrow(ManagerSQLite.ColumnRoutesTrack.MODIFY_TRACK)));

        TextView icon = (TextView) view.findViewById(R.id.row_text_icon);
        icon.setTypeface(ElCaminoFontIcon);
        icon.setText(R.string.ELCIcon_sendview);

        TextView icon2 = (TextView) view.findViewById(R.id.row_text_icon2);
        icon2.setTypeface(ElCaminoFontIcon);
        icon2.setText(R.string.ELCIcon_locationarrow);

        Button icon_btn = (Button) view.findViewById(R.id.destinos_btn_newtrack);
        icon_btn.setTypeface(ElCaminoFontIcon);
        int status_track = cursor.getInt(cursor.getColumnIndexOrThrow(ManagerSQLite.ColumnRoutesTrack.STATUS_ROUTE_TRACK));
        switch (status_track){
            case 0:{
                icon_btn.setText(R.string.ELCIcon_playcircle);
                icon_btn.setTextColor(context.getResources().getColor(R.color.ELCColorBTNActPlay));
            }
            break;
            case 1:{
                icon_btn.setText(R.string.ELCIcon_pausecircle);
                icon_btn.setTextColor(context.getResources().getColor(R.color.ELCColorBTNActPause));
            }
            break;
            case 2:{
                icon_btn.setText(R.string.ELCIcon_stopcircle);
                icon_btn.setTextColor(Color.DKGRAY);
            }
            break;
            default:{
                icon_btn.setText(R.string.ELCIcon_stopcircle);
                icon_btn.setTextColor(Color.DKGRAY);
            }
        }
        icon_btn.setOnClickListener(new View.OnClickListener(){
            DestinosSelectActivity destinosSelectActivity = (DestinosSelectActivity) context;
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(ManagerSQLite.ColumnRoutes.ID_ROUTE));
            int status_track = cursor.getInt(cursor.getColumnIndexOrThrow(ManagerSQLite.ColumnRoutesTrack.STATUS_ROUTE_TRACK));
            @Override
            public void onClick(View v) {
                //do something
                destinosSelectActivity.onItemClickPlay(v, id, status_track);
            }
        });
    }
}