package servlet.danmu;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/danmuTest")
public class DanmuTest {
    private static String userId;
    @OnOpen
    public void onOpen(/*@PathParam("userId") String userId,*/ Session session) throws IOException {
      /*  this.userId = userId;
       // logger.debug("新连接：{}",userId);*/
    }
    @OnMessage
    public void onMessage(String message, Session session) throws IOException{
        session.getBasicRemote().sendText(/*"收到" + this.userId + "的信息 : "*/ message);
    }
    @OnClose
    public void onClose(){
        System.out.println("关闭");
    }
    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }
}
