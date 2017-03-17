package com.tieto.ecommerce.network;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpGetTask extends AsyncTask<String, Void, String> {

    private AsyncResponse delegate = null;

    public HttpGetTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String url = params[0];
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            int statusCode = urlConnection.getResponseCode();
            Log.v("HttpGetTask", "Status code: " + statusCode);
            if (statusCode != HttpURLConnection.HTTP_OK)
                return null;
            return readStream(urlConnection.getInputStream());
        } catch (SocketTimeoutException e) {
            Log.e("HttpGetTask",Log.getStackTraceString(e));
        } catch (IOException e) {
            Log.e("HttpGetTask",Log.getStackTraceString(e));
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return null;
    }

    private String readStream(InputStream iStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        StringBuilder text = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null)
            text.append(line + '\n');
        return text.toString();
    }

    @Override
    protected void onPostExecute(String res) {
        delegate.httpTaskFinished(res);
    }

}