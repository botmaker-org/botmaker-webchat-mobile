import { StatusBar } from "expo-status-bar";
import { Button, Platform, SafeAreaView, StyleSheet, Text, TextInput, View } from "react-native";
import BotmakerWebchat from "./BotmakerWebchat";
import React from "react";

export default function App() {
  const [isLoading, setIsLoading] = React.useState<boolean>(true);
  const [orderNumber, setOrderNumber] = React.useState<string>('');
  const webChatRef = React.useRef<BotmakerWebchat>(null);

  const handleSetOrderNumber = React.useCallback(() => {
    if(webChatRef.current) {
      webChatRef.current.setVars({'orderNumber': orderNumber});
    }
  }, [orderNumber]);

  const handleSayHi = React.useCallback(() => {
    if(webChatRef.current) {
      webChatRef.current.sendMessage('Hola!!!');
    }
  }, []);

  return (
    <SafeAreaView  style={styles.container}>
      <StatusBar style="dark" />
      <TextInput 
        style={{ height: 40, borderColor: 'gray', borderWidth: 1 }}
        onChangeText={text => setOrderNumber(text)}
        value={orderNumber}
        placeholder="Order number"
        editable={!isLoading} 
      />
      <Button
        onPress={handleSetOrderNumber}
        title="Set order number"
        color="#841584"
        accessibilityLabel="Set order number"
        disabled={isLoading}
      />
      <Button
        onPress={handleSayHi}
        title="SayHi"
        color="#841584"
        accessibilityLabel="Set order number"
        disabled={isLoading}
      />
      <BotmakerWebchat 
        ref={webChatRef}
        style={styles.webchat}
        webchatId="GZ8QBYYH6K"
        botmakerVars={{
          firstName:'Jose',
          lastName:'Perez',
          email:'jose.perez@bormaker.com'
        }}
        onWebchatLoaded={() => setIsLoading(false)}
        />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    height: "100%",
    paddingTop: Platform.OS === "android" ? 40 : 0
  },
  webchat: {
  },
});
