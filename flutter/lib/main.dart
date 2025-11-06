import 'package:flutter/material.dart';
import 'botmaker_webchat_page.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: BotmakerWebchatPage(webchatId: 'A6TT4D3H4L'), // Webchat ID: configurable. README.md
    );
  }
}