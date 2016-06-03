package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by dis2 on 02/06/16.
 */
public class AdpaterRowsActivityP1 extends BaseAdapter {
    Context context;
    Map<Integer, Map<String, Object>> data;

    private static LayoutInflater inflater = null;

    public AdpaterRowsActivityP1(Context context, Map<Integer, Map<String, Object>> data){
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row_activity_d1, parent, false);
        vi.setMinimumHeight(100);
        TextView title = (TextView) vi.findViewById(R.id.row_text_title);
        title.setText((String) data.get(position).get("t"));

        TextView describe = (TextView) vi.findViewById(R.id.row_text_describe);
        describe.setText((String) data.get(position).get("m"));


        ImageView logo = (ImageView) vi.findViewById(R.id.row_image_logo);
        int logosend = (int) data.get(position).get("l");
        logo.setImageResource(logosend);

        ImageView moto = (ImageView) vi.findViewById(R.id.row_image_moto);
        int motosend = (int) data.get(position).get("n");
        moto.setImageResource(motosend);


        return vi;
    }
}
