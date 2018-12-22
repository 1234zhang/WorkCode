package MessageBoard.service;

import MessageBoard.been.MessageInfo;
import MessageBoard.dao.MessageBoardDao;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageBoardService {
    private static MessageBoardService instance;
    private static MessageBoardDao messageBoardDao = null;
    public MessageBoardService(){
        messageBoardDao = MessageBoardDao.getInstance();
    }
    public static MessageBoardService getInstance(){
        if(instance == null){
            synchronized (MessageBoardService.class){
                if(instance == null){
                    instance = new MessageBoardService();
                }
            }
        }
        return instance;
    }
    public List<MessageInfo> findAllContentChild(MessageInfo content){
        List<MessageInfo> list = messageBoardDao.findMessageInfoByPid(content.getId());
        for (MessageInfo message: list) {
            List<MessageInfo> childContent = findAllContentChild(message);
            message.setChildContent(childContent);
        }
        return list;
    }
    public List<MessageInfo> getAllMessage(){
        List<MessageInfo> list = messageBoardDao.findMessageInfoByPid(0);
        for (MessageInfo message: list) {
            List<MessageInfo> childContent = findAllContentChild(message);
            message.setChildContent(childContent);
        }
        return list;
    }
    public String messageToJson(List<MessageInfo> messageInfos){
        JSONObject Msg = new JSONObject();
        List<MessageInfo> list = messageInfos;
        JSONArray data = new JSONArray();
        for (MessageInfo message: list) {
            JSONArray child = new JSONArray();
            JSONObject content = new JSONObject();
            content.put("id", message.getId());
            content.put("username", message.getUserName());
            content.put("text", message.getText());
            child = getChildJSON(message.getChildContent());
            content.put("child", child);
            data.put(content);
        }
        Msg.put("data", data);
        Msg.put("status",10001);

        return Msg.toString();
    }
    private JSONArray getChildJSON(List<MessageInfo> messageInfos){
        List<MessageInfo> list = messageInfos;
        JSONArray data = new JSONArray();
        if(list.size() == 0) return null;
        for(MessageInfo message: list){
           JSONObject content = new JSONObject();
           JSONArray child = new JSONArray();
           content.put("id", message.getId());
           content.put("username", message.getUserName());
           content.put("text", message.getText());
           child = getChildJSON(message.getChildContent());
           content.put("child", child);
           data.put(content);
        }
        return data;
    }
    public boolean insertMessage(MessageInfo message){
        if(message.getUserName() != null && message.getText() != null){
            messageBoardDao.insert(message);
            return true;
        }
        return false;
    }
    public MessageInfo selectMessage(int id){
        return messageBoardDao.select(id);
    }
    public void deleteMessage(int id){
        MessageInfo messageInfo = selectMessage(id);
        if(messageInfo == null){
            return;
        }
        List<MessageInfo> list = new ArrayList<MessageInfo>();
        list = findAllContentChild(messageInfo);
        for (MessageInfo message: list) {
            instance.deleteMessage(message.getId());
            messageBoardDao.delete(message.getId());
        }
        messageBoardDao.delete(messageInfo.getId());
    }
}
