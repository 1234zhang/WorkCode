package service.login;

import bee.UserInfo;
import dao.UserInfoDao;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class QQLoginService {
    private UserInfoDao userInfoDao = null;
    private static QQLoginService instance = null;
    public static final String appId = "101551268";
    public static final String appKey = "5d4fe492a308fad228e4c5793eb0fb88";

    public QQLoginService(){
        userInfoDao = UserInfoDao.getInstance();
    }

    public static QQLoginService getInstance(){
        if(instance == null){
            synchronized (QQLoginService.class){
                if(instance == null){
                    instance = new QQLoginService();
                }
            }
        }
        return instance;
    }
    public String getAccessToken(String preUrl,String key){
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        String access_token = null;
        try {
            URL url = new URL(preUrl + "?grant_type=authorization_code&client_id="
            + appId +"&client_secret=" + appKey + "&code=" + key +"&redirect_uri=http%3a%2f%2fbrandonxcc.top%3a8080%2fMyNetEase%2flogin%2fgetToken");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            inputStream = conn.getInputStream();
            access_token = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }
    public String getOpenId(String preUrl, String key){
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        String OpenIdJson = null;
        URL url = null;
        try {
            url = new URL(preUrl + "?" + key);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            inputStream = conn.getInputStream();
            OpenIdJson = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OpenIdJson;
    }
    public void getUserInfo(String preUrl, String key,String access_token){
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        String userInfo = null;
        URL url = null;
        try {
            url = new URL(preUrl + "?" + access_token + "&oauth_consumer_key=" + appId + "&openid=" + key);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset","utf-8");
            inputStream = conn.getInputStream();
            userInfo = new BufferedReader(new InputStreamReader(inputStream,"utf-8")).lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject jsonObject = new JSONObject(userInfo);
            UserInfo userInfo1 = new UserInfo(" ",jsonObject.getString("nickname"),key);
            userInfoDao.insert(userInfo1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
