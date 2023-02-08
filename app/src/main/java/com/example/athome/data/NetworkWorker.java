package com.example.athome.data;

import android.os.AsyncTask;

import com.example.athome.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NetworkWorker {

    public static void CheckFetchManifest() {
        try {
            (new JsonTask()).execute("http://contabo1.telehorizon.com:47279/manifest.yaws").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static List<HairdresserArrangementSearchResultEntry> LookupPossibleHairdresserArrangements(HairdresserLookupParameters params) {
        try {
            return new JsonTaskFetchHairdresserArrangementLookupResults().execute(params).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class JsonTaskFetchHairdresserArrangementLookupResults extends AsyncTask<HairdresserLookupParameters, String, List<HairdresserArrangementSearchResultEntry>> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected List<HairdresserArrangementSearchResultEntry> doInBackground(HairdresserLookupParameters... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            JSONObject json = null;

            try {
                String servs = "";
                for(Map.Entry<HairdresserServices, Integer> p: params[0].getServices().entrySet())
                    servs+=String.format("%s*%d;",p.getKey().GetStandardCode(),p.getValue().intValue());
                String urlString = String.format("http://contabo1.telehorizon.com:47279/lookup_hairdresser_arrangement.yaws?time_interval_start=%d&max_price=%d&services=%s&",params[0].getTime(), params[0].getMaxCost(), servs);
                System.out.println(urlString);
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                String bufString = buffer.toString();
                try {
                    json = ((JSONObject) ((new JSONTokener(bufString))).nextValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(json==null)
                    return Collections.emptyList();
                String status = json.getString("status");
                if(status.equals("error") || (!status.equals("ok"))) {
                    return Collections.emptyList();
                }
                List<HairdresserArrangementSearchResultEntry> results = new ArrayList<>();
                JSONArray arr = json.getJSONArray("results");
                for(int i=0;i<arr.length();i++) {
                    JSONObject entry = arr.getJSONObject(i);
                    results.add(new HairdresserArrangementSearchResultEntry(entry.getString("name"), entry.getString("id"), entry.getInt("cost")));
                }
            }
            catch(JSONException e) {
                return Collections.emptyList();
            }
            return Collections.emptyList();
        }

        @Override
        protected void onPostExecute(List<HairdresserArrangementSearchResultEntry> result) {
            super.onPostExecute(result);
        }
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
            JSONObject json = null;
            boolean fetchRemoteManifest = false;
            if(manifestFile.exists()) {
                try {
                    FileInputStream r = new FileInputStream(manifestFile);
                    byte[] bf = new byte[(int)manifestFile.length()];
                    r.read(bf);
                    json = ((JSONObject) ((new JSONTokener(new String(bf,"UTF-8")))).nextValue());
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
                        json = ((JSONObject) ((new JSONTokener(bufString))).nextValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


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
            HairdresserProfessionalsList.ReloadFromJSON(json);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println(result);
        }
    }

}
