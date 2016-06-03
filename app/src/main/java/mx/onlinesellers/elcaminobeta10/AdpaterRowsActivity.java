package mx.onlinesellers.elcaminobeta10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.suitebuilder.TestMethod;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by dis2 on 24/05/16.
 */
public class AdpaterRowsActivity extends BaseAdapter {

    Context context;
    Map<Integer, Map<String, Object>> data;
    public ImageView userImageView;


    private static LayoutInflater inflater = null;

    public AdpaterRowsActivity(Context context, Map<Integer, Map<String, Object>> data){
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
            vi = inflater.inflate(R.layout.row_activity, parent, false);
        vi.setMinimumHeight(65);
        TextView title = (TextView) vi.findViewById(R.id.row_text_title);
        title.setText((String) data.get(position).get("t").toString());

        TextView describe = (TextView) vi.findViewById(R.id.row_text_describe);
        describe.setText((String) data.get(position).get("m"));

        TextView tiempo = (TextView) vi.findViewById(R.id.row_text_tiempo);
        tiempo.setText((String) data.get(position).get("d"));

        userImageView = (ImageView) vi.findViewById(R.id.userImageView);
        Bitmap imageUser = BitmapFactory.decodeResource(context.getResources(),R.drawable.user);
        userImageView.setImageBitmap(imageUser);

        return vi;
    }

    public String elipsisTextView(String stringTextView, int limitChart, TextView textView){
        String newStringTextView = "";
        if(stringTextView.length()<limitChart){
            int endOfLastLine = textView.getLayout().getLineEnd(4);
            newStringTextView = stringTextView.subSequence(0, endOfLastLine - 3) + "...";
        }else{
            newStringTextView = stringTextView;
        }
        return newStringTextView;
    }
}
