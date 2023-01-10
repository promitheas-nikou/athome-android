package com.example.athome.data;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.athome.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class NetworkWorker {

    public static void Do() {
        (new JsonTask()).execute("http://contabo1.telehorizon.com:47279/manifest.yaws");
    }

    private static class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            File outputDir = MainActivity.GetSingletonInstance().getCacheDir();
            File manifestFile = new File(outputDir, "manifest.json");
            boolean fetchRemoteManifest = false;
            if(manifestFile.exists()) {
                try {
                    FileInputStream r = new FileInputStream(manifestFile);
                    byte[] bf = new byte[(int)manifestFile.length()];
                    r.read(bf);
                    JSONObject json = ((JSONObject) ((new JSONTokener(new String(bf,"UTF-8")))).nextValue());
                    System.out.println(System.currentTimeMillis()/1000);
                    if(json.getLong("validUntil")<(System.currentTimeMillis()/1000))
                        fetchRemoteManifest = true;
                } catch (FileNotFoundException e) {
                    fetchRemoteManifest = true;
                    e.printStackTrace();
                } catch (JSONException e) {
                    fetchRemoteManifest = true;
                    e.printStackTrace();
                } catch (IOException e) {
                    fetchRemoteManifest = true;
                    e.printStackTrace();
                }
            }
            else
                fetchRemoteManifest = true;
            System.out.println(fetchRemoteManifest);
            if(fetchRemoteManifest) {
                try {
                    URL url = new URL(params[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();


                    InputStream stream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }
                    FileWriter writer = new FileWriter(manifestFile);
                    String bufString = buffer.toString();
                    writer.write(bufString);
                    writer.flush();
                    writer.close();

                    try {
                        JSONObject json = ((JSONObject) ((new JSONTokener(bufString))).nextValue());
                        HairdresserProfessionalsList.ReloadFromJSON(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return bufString;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println(result);
        }
    }

}
