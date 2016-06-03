package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by dis2 on 01/06/16.
 */
public class AdpaterRowsActivityS5 extends BaseAdapter {
    Context context;
    Map<Integer, Map<String, Object>> data;
    public ImageView userImageView;
    Typeface ElCaminoFontIcon;

    private static LayoutInflater inflater = null;

    public AdpaterRowsActivityS5(Context context, Map<Integer, Map<String, Object>> data, Typeface typeface){
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ElCaminoFontIcon = typeface;
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
            vi = inflater.inflate(R.layout.row_activity_s5, parent, false);
        vi.setMinimumHeight(65);
        TextView title = (TextView) vi.findViewById(R.id.row_text_title);
        title.setText((String) data.get(position).get("t"));

        TextView describe = (TextView) vi.findViewById(R.id.row_text_describe);
        describe.setText((String) data.get(position).get("m"));

        TextView icon = (TextView) vi.findViewById(R.id.row_text_icon);
        icon.setTypeface(ElCaminoFontIcon);
        icon.setText((String)data.get(position).get("d"));

        TextView plus = (TextView) vi.findViewById(R.id.row_text_plus);
        plus.setText((String) data.get(position).get("p"));

        TextView valor = (TextView) vi.findViewById(R.id.row_text_valor);
        valor.setTypeface(ElCaminoFontIcon);
        valor.setText(Html.fromHtml((String) data.get(position).get("v")));


        return vi;
    }
}