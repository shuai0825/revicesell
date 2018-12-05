package com.pmcc.revicesell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sky
 * @create 2018-06-22 9:41
 * @desc
 **/
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;

    private static CopyOnWriteArrayList<WebSocket> webSocketSet = new CopyOnWriteArrayList<>();

    @OnOpen
    public void opOpen(Session session, EndpointConfig config) {
        this.session = session;
        webSocketSet.add(this);
        log.info("websocekt消息,有新的链接，总数:{}", webSocketSet.size());
    }

    @OnClose
    public void onClose(Session session) {
        webSocketSet.remove(this);
        log.info("websocekt消息,断开链接，总数:{}", webSocketSet.size()+"/id:"+session.getId());
    }

    @OnMessage
    public void onMessage(String msessage,Session session) {

        log.info("websocekt消息,收到新的消息:{}", msessage+"/session:"+session.getId());
        if("关闭".equals(msessage)){
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String msessage) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("websocekt消息,发送新的消息:{}", msessage);
            try {
                webSocket.session.getBasicRemote().sendText(msessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
