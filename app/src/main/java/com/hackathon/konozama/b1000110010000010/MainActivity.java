package com.hackathon.konozama.b1000110010000010;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        WebView webView = (WebView) findViewById(R.id.Webview);
        webView.setWebViewClient(new WebViewClient() {
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.facebook.com");

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ボタンをおしたら個々のコードがじっこうされる
            }
        });
        Button button2 = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ボタンをおしたら個々のコードがじっこうされる
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* 日本語から2進数文字列に変換 */
    public static String JpToBin(String jp_text, String enc) {
        String res = "";

        try {
            byte data[] = jp_text.getBytes(enc);
            for (byte b : data) {
                res += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String JpToBin(String jp_text) {
        String res = "";

        try {
            byte data[] = jp_text.getBytes("UTF-8");
            Log.d("test01", "data len :" + data.length);
            for (byte b : data) {
                res += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return res;
    }

    /* 2進数文字列から日本語に変換 */
    public static String BinToJp(String bin_text) {
        String res = "";
        String tmp = bin_text;
        int count = 0;

        List<String> data = new ArrayList<String>();
        for (int i = 0; i < bin_text.length(); i++) {
            // 1バイトコードの場合
            if ("0".equals(tmp.substring(i, 1))) {
                data.add(count, tmp.substring(i, 8 * 1));
                count++;
                i = i + 8 * 1;
            }
            // 2バイトコードの場合
            else if ("10".equals(tmp.substring(i, 2))) {
                data.add(count, tmp.substring(i, 8 * 2));
                count++;
                i = i + 8 * 2;
            }
            // 3バイトコードの場合
            else if ("110".equals(tmp.substring(i, 3))) {
                data.add(count, tmp.substring(i, 8 * 3));
                count++;
                i = i + 8 * 3;
            }
            // 4バイトコードの場合
            else if ("1110".equals(tmp.substring(i, 4))) {
                data.add(count, tmp.substring(i, 8 * 4));
                count++;
                i = i + 8 * 4;
            }
            Log.d("01debug", data.get(count));
        }
        return res;
    }
}
