package com.udacity.gradle.builditbigger.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.android.library.JokesActivity;
import com.example.sheraz.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Sheraz on 8/1/2015.
 */
public class JokeEndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private JokeEndpointsAsyncTaskListener mListener;
    private Exception mError = null;
    private String mRootUrl;

    public JokeEndpointsAsyncTask(Context context, ProgressDialog progressDialog, String rootUrl) {
        this.mContext = context;
        this.mProgressDialog = progressDialog;
        this.mRootUrl = rootUrl;
    }

    @Override
    protected void onPreExecute()
    {
        if (null != mProgressDialog) {
            mProgressDialog.show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    //
                    //    - 10.0.2.2 is localhost's IP address in Android emulator
                    //    - https://sherazjokeproject.appspot.com is used after application deployment at
                    //      Google Cloud Endpoints server by using Android Studio
                    //
                    // - turn off compression when running against local devappserver
                    .setRootUrl(mRootUrl)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            mError = e;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (this.mListener != null) {
            this.mListener.onComplete(result, mError);
        }

        if (mContext != null) {
            Intent intent = new Intent(mContext, JokesActivity.class);
            intent.putExtra(JokesActivity.JOKE_TEXT_STRING, result);
            mContext.startActivity(intent);
        }
    }

    @Override
    protected void onCancelled() {
        if (this.mListener != null) {
            mError = new InterruptedException("AsyncTask cancelled");
            this.mListener.onComplete(null, mError);
        }
    }

    public static interface JokeEndpointsAsyncTaskListener {
        public void onComplete(String jsonString, Exception e);
    }

    public JokeEndpointsAsyncTask setListener(JokeEndpointsAsyncTaskListener listener) {
        this.mListener = listener;
        return this;
    }
}