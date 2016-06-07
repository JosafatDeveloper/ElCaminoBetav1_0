package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

import static mx.onlinesellers.elcaminobeta10.ManagerSQLite.ColumnOther.NAME_TRACK_AND_NAME_ROUTE;

/**
 * Created by dis2 on 01/06/16.
 */
public class AdpaterRowsActivityS4 extends CursorAdapter {

    Typeface ElCaminoFontIcon;
    public AdpaterRowsActivityS4(Context context, Cursor cursor, int flags){
        super(context, cursor, 0);
        ElCaminoFontIcon = Typeface.createFromAsset(context.getAssets(), "fonts/ElCamnioFontIcon-Regular.ttf");
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return  LayoutInflater.from(context).inflate(R.layout.row_activity_s4, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.row_text_title);
        title.setText(cursor.getString(cursor.getColumnIndexOrThrow(ManagerSQLite.ColumnRoutes.ROUTE_NAME)));
        TextView describe = (TextView) view.findViewById(R.id.row_text_describe);
        describe.setText(cursor.getString(cursor.getColumnIndexOrThrow(ManagerSQLite.ColumnRoutes.ROUTE_MODIFY)));

        TextView icon = (TextView) view.findViewById(R.id.row_text_icon);
        icon.setTypeface(ElCaminoFontIcon);
        icon.setText(R.string.ELCIcon_sendview);

        TextView icon2 = (TextView) view.findViewById(R.id.row_text_icon2);
        icon2.setTypeface(ElCaminoFontIcon);
        icon2.setText(R.string.ELCIcon_locationarrow);
    }
}
