package mx.onlinesellers.elcaminobeta10.ELCEXTRAS;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import mx.onlinesellers.elcaminobeta10.PlayRoutrLookActivity;

/**
 * Created by dis2 on 21/09/16.
 */

public class ELCAPIServer extends Service {
    public Context c;
    public PlayRoutrLookActivity viewActivity;
    private ProgressDialog progress;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Init
    public ELCAPIServer(Context ctx, PlayRoutrLookActivity viewActivitythis){
        super();
        this.c = ctx;
        this.viewActivity = viewActivitythis;
    }

    public void _save_acelerometro(float data_x, float data_y, float data_z, String id){
        String newquery = "INSERT INTO d_max (d_maxcol, d_maxcol1, d_maxcol2, d_maxcol3) VALUES ('"+id+"', '"+data_x+"', '"+data_y+"', '"+data_z+"')";

        byte[] data = new byte[0];
        try {
            data = newquery.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String query = Base64.encodeToString(data, Base64.DEFAULT);

        String wsAPI = "ws:developer.query('"+query+"')";
        String url = "http://appelcamino.mx/api/?api_key=DD-3dac-bce5-32cc-d48f-27fa-62e9-9306-7b3c-35f0-94f7&v=1&l=default";
        new PostClass(this.c, url, wsAPI).execute();
    }
    
    private class PostClass extends AsyncTask<String, Void, Void> {
        private final Context context;
        private final String urlsend;
        private final String parametroSend;

        public PostClass(Context c, String u, String p) {
            this.context = c;
            this.urlsend = u;
            this.parametroSend = p;
        }

        protected void onPreExecute() {
            //progress = new ProgressDialog(this.context);
            //progress.setMessage("Loading");
            //progress.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(urlsend);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                String urlParameters = "q="+parametroSend;
                connection.setRequestMethod("POST");
                connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();
                int responseCode = connection.getResponseCode();
                final StringBuilder output = new StringBuilder("Request URL " + url);
                output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
                output.append(System.getProperty("line.separator") + "Response Code " + responseCode);
                output.append(System.getProperty("line.separator") + "Type " + "POST");
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
                System.out.println("output===============" + br);
                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();
                output.append(
                        System.getProperty("line.separator")
                                + "Response " + System.getProperty("line.separator")
                                + System.getProperty("line.separator")
                                + responseOutput.toString()
                );
                viewActivity.runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                Log.i("LOG", output.toString());
                                //Log.i(output);
                                //progress.dismiss();
                            }
                        });
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }
}
