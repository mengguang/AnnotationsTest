package org.xmgu2008.mengguang.annotationstest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById(R.id.message)
    TextView txView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Click
    void startButton() {
        getIP();
    }

    @Background
    void getIP() {
        String url = "http://learndevops.cn/ip.php";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            showIP(body.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @UiThread
    void showIP(String ip) {
        txView.setText(ip);
    }
}
