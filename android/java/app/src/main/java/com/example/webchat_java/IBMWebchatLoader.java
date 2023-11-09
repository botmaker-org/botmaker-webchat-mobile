package com.example.webchat_java;

import android.webkit.ValueCallback;

public interface IBMWebchatLoader {
    public void bmMaximize();
    public void bmMinimize();
    public void bmHide();
    public void bmShow();
    public void bmSendMessage(String inputText);
    public void bmInfo(final ValueCallback<String> callback);
    public void bmConnect();
    public void bmSetVariables(Object variables);
}
