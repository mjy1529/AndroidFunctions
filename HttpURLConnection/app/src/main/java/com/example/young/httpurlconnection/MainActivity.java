package com.example.young.httpurlconnection;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String urlStr;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlStr = editText.getText().toString();
                RequestThread thread = new RequestThread();
                thread.start();
            }
        });
    }

    class RequestThread extends Thread {
        public void run() {
            try {
                URL url = new URL(urlStr);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn != null) {
                    conn.setConnectTimeout(10000); //제한시간 10초 : 10초안에 연결이 안되면 연결을 끊겠다!
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoInput(true);

                    int resCode = conn.getResponseCode(); //연결

                    //if(resCode == HttpURLConnection.HTTP_OK) //resCode가 ok일 때

                    //conn.getInputStream() : 데이터가 들어올 수 있는 통로를 만들어줌.
                    //BufferedReader : 문자열로 처리, 한 줄씩 읽어들일 떄 유용하게 사용할 수 있음.
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;

                    while (true) {
                        line = reader.readLine();
                        if(line == null) { //다 읽었다는 의미
                            break;
                        }
                        println(line);
                    }
                    reader.close();
                    conn.disconnect();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data +"\n");
            }
        });
    }
}
