import React from 'react';
import { StyleProp, ViewStyle } from 'react-native';
import WebView from 'react-native-webview';

type BotmakerWebchatProps = {
  webchatId: string;
  style: StyleProp<ViewStyle> & {},
  onWebchatLoaded?: () => void;
  userUniqueId?: string;
  botmakerVars?: Record<string,string>;
};

class BotmakerWebchat extends React.Component<BotmakerWebchatProps> {

  webview: React.RefObject<WebView>;

  constructor(props: BotmakerWebchatProps) {
    super(props);
    this.webview = React.createRef();
  }

  setVars(vars: Record<string,string>) {
    if(this.webview.current) {
      const js = `bmSetVariables(JSON.parse('${JSON.stringify(vars)}'));`;
      this.webview.current.injectJavaScript(js);
    }
  }

  sendMessage(text: string) {
    if(this.webview.current) {
      const js = `bmSendMessage(JSON.parse('${JSON.stringify(text)}'));`;
      this.webview.current.injectJavaScript(js);
    }
  }

  handleEvents = (event: any) => {
    const { onWebchatLoaded } = this.props;
    const { data } = event.nativeEvent;
    if(data === 'bmChatLoaded') {
      if(onWebchatLoaded) {
        onWebchatLoaded();
      }
    }
  }

  render() {
    const { style, webchatId, botmakerVars = {} , userUniqueId} = this.props;

    return (
      <WebView
        ref={this.webview}
        style={style}
        onMessage={this.handleEvents}
        source={{ 
          uri: 'https://storage.googleapis.com/m-infra.appspot.com/public/GuilleDemo/blank.html'
        }}
        injectedJavaScript={`
          window.BOTMAKER_VAR = ${JSON.stringify({...botmakerVars, userIdOnBusiness: userUniqueId})};

          window.BOTMAKER_ACTION = {
              onload: function() {
                  window.ReactNativeWebView.postMessage('bmChatLoaded');
              }
          }

          let js = document.createElement('script');
          js.type = 'text/javascript';
          js.async = 1;
          js.src = 'https://go.botmaker.com/rest/webchat/p/${webchatId}/init.js';
          document.body.appendChild(js);
        `}
      />
    );
  }
}

export default BotmakerWebchat;
