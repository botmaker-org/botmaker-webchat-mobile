package com.example.webchat_java;

import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);

        IBMWebchatLoader BMWebchat = new BMWebchatLoader(
                webView,
                "https://storage.googleapis.com/m-infra.appspot.com/public/GuilleDemo/peperBotMobile.html"
        );

        LinearLayout frameLayout = findViewById(R.id.layoutInferior);
        Button b_maximize = frameLayout.findViewById(R.id.b_bmMaximize);
        Button b_minimize = frameLayout.findViewById(R.id.b_bmMinimize);
        Button b_bmHide = frameLayout.findViewById(R.id.b_bmHide);
        Button b_bmShow = frameLayout.findViewById(R.id.b_bmShow);
        Button b_bmSendMessage = frameLayout.findViewById(R.id.b_bmSendMessage);
        Button b_bmInfo = frameLayout.findViewById(R.id.b_bmInfo);
        Button b_bmConnect = frameLayout.findViewById(R.id.b_bmConnect);
        Button b_bmSetVariables = frameLayout.findViewById(R.id.b_bmSetVariables);

        b_maximize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMWebchat.bmMaximize();
            }
        });

        b_minimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMWebchat.bmMinimize();
            }
        });

        b_bmHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMWebchat.bmHide();
            }
        });

        b_bmShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMWebchat.bmShow();
            }
        });

        b_bmSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageToSend = "Prueba Send Message";
                BMWebchat.bmSendMessage(messageToSend);
            }
        });

        b_bmInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMWebchat.bmInfo(new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        System.out.println(value);
                    }
                });
            }
        });

        b_bmConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMWebchat.bmConnect();
            }
        });

        b_bmSetVariables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMWebchat.bmSetVariables(Map.of("nombre", "Dario"));
            }
        });

        /*
        --------------------
        peperBotMobile.html:
        --------------------
        <!DOCTYPE html><html><body><!-- START Botmaker Webchat-->
          <script>
              (function () {
                  let js = document.createElement('script');
                  js.type = 'text/javascript';
                  js.async = 1;
                  js.src = 'https://go.botmaker.com/rest/webchat/p/KCRZV8L0G2/init.js';
                  document.body.appendChild(js);
              })();
          </script>
          <!-- END Botmaker Webchat--></body></html>
        */

    }
}
