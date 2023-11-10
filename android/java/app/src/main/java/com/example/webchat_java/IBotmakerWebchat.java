package com.example.webchat_java;

import android.webkit.ValueCallback;

import java.util.Map;

public interface IBotmakerWebchat {
    /**
     * Maximiza el webchat de Botmaker.
     */
    public void bmMaximize();
    /**
     * Minimiza el webchat de Botmaker.
     */
    public void bmMinimize();
    /**
     * Oculta el webchat de Botmaker.
     */
    public void bmHide();
    /**
     * Visibiliza el webchat de Botmaker.
     */
    public void bmShow();
    /**
     * Envía un mensaje al webchat de botmaker.
     *
     * @param inputText Mensaje a enviar.
     */
    public void bmSendMessage(String inputText);
    /**
     * Devuelve informacion del webchat de Botmaker.
     *
     * @param callback para obtener el mensaje de informacion devuelto por el webchat de Botmaker.
     */
    public void bmInfo(final ValueCallback<String> callback);
    /**
     * Establece variables del webchat de Botmaker..
     *
     * @param variables Mapa con variables a establecer.
     */
    public void bmSetVariables(Map<String, String> variables);
}
