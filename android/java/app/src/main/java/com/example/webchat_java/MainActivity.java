package com.example.webchat_java;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);

        Map<String, String> BOTMAKER_VAR = new HashMap<String, String>() {{
            put("firstName", "Lionel");
            put("lastName", "Scaloni");
            put("customVar", "newValueForCustomVar");
            put("userIdOnBusiness", "1");
        }};

        IBotmakerWebchat BMWebchat = new BotmakerWebchat(
                webView,
                "KCRZV8L0G2",
                BOTMAKER_VAR
        );

        LinearLayout frameLayout = findViewById(R.id.layoutInferior);
        Button b_maximize = frameLayout.findViewById(R.id.b_bmMaximize);
        Button b_minimize = frameLayout.findViewById(R.id.b_bmMinimize);
        Button b_bmHide = frameLayout.findViewById(R.id.b_bmHide);
        Button b_bmShow = frameLayout.findViewById(R.id.b_bmShow);
        Button b_bmSendMessage = frameLayout.findViewById(R.id.b_bmSendMessage);
        Button b_bmInfo = frameLayout.findViewById(R.id.b_bmInfo);
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
                        showAlert(value);
                    }
                });
            }
        });
        b_bmSetVariables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> variables = new HashMap<String, String>() {{
                    put("variable_prueba", "prueba 1");
                    put("variable_prueba_2", "prueba 2");
                }};
                BMWebchat.bmSetVariables(variables);
            }
        });
    }

    private void showAlert(String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Info")
                .setMessage(value);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
