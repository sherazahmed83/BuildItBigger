package com.udacity.gradle.builditbigger.task;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.IsolatedContext;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Sheraz on 8/2/2015.
 */
public class JokeEndpointsAsyncTaskTest extends AndroidTestCase {
    private CountDownLatch signal = null;
    private String mResult = null;
    private Exception mError = null;
    private Context mContext;
    private final String API_SERVICE_ROOT_URL = "https://sherazjokeproject.appspot.com/_ah/api/";

    public JokeEndpointsAsyncTaskTest() {

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext = new IsolatedContext(null, getContext()) {
            @Override
            public Object getSystemService(final String pName) {
                return getContext().getSystemService(pName);
            }
        };
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testJokeGetTask() throws InterruptedException {

        JokeEndpointsAsyncTask task = new JokeEndpointsAsyncTask(null, null, API_SERVICE_ROOT_URL);
        task.setListener(new JokeEndpointsAsyncTask.JokeEndpointsAsyncTaskListener() {
            @Override
            public void onComplete(String resultString, Exception e) {
                mResult = resultString;
                mError = e;
                signal.countDown();
            }
        }).execute();
        signal.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mResult));
        assertTrue(TextUtils.getTrimmedLength(mResult) > 0);
    }
}
