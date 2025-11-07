# Botmaker Webchat – HTML demo

Ejemplo página HTML simple.

- Podés probar usando el script de tu bot

- El script de integración se consigue en:
  **go.botmaker.com/#/integrations/channels → Webchat -> "Probar Webchat" o "Instalación"**.

![alt text](image.png)

El WebchatID configurable se saca de js.src:

`js.src = 'https://go.botmaker.com/rest/webchat/p/A6TT4D3H4L/init.js'` --> A6TT4D3H4L

- Métodos expuestos:
  `bmShow()`, `bmHide()`, `bmMinimize()`, `bmMaximize()`, `bmInfo()`,
  `bmSetVariables({...})`, `bmSendMessage('texto')`.

**Para ejecutar el HTML**
- Opción 1: Doble click sobre el archivo
- Opción 2: Parado en la carpeta /html ejecutar `python -m http.server 8080`.