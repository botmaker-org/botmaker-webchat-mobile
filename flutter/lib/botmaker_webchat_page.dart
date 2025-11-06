import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';

class BotmakerWebchatPage extends StatefulWidget {
  const BotmakerWebchatPage({
    super.key,
  });

  @override
  State<BotmakerWebchatPage> createState() => _BotmakerWebchatPageState();
}

class _BotmakerWebchatPageState extends State<BotmakerWebchatPage> {
  late final WebViewController _controller;

  String _html() => '''
  <!DOCTYPE html>
  <html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Botmaker Webchat (Flutter)</title>
    <style> body { font-family: -apple-system, Arial, sans-serif; } </style>
  </head>
  <body>
    <h1>Webchat de ejemplo (Flutter)</h1>

    <script>
      window.BOTMAKER_VAR = {
        userIdOnBusiness: 'userId123',
        firstName: 'Flutter',
        lastName: 'Demo'
      };

      window.BOTMAKER_ACTION = {
        onload: function () {
          console.log('[Webchat] cargado (Flutter).');
        }
      };

      (function () {
        var js = document.createElement("script");
        js.type = "text/javascript";
        js.async = true;
        js.src = "https://go.botmaker.com/rest/webchat/p/A6TT4D3H4L/init.js";
        document.body.appendChild(js);
      })();
    </script>
  </body>
  </html>
  ''';

  @override
  void initState() {
    super.initState();

    _controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..loadHtmlString(_html());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Chat Botmaker')),
      body: WebViewWidget(controller: _controller),
    );
  }
}
