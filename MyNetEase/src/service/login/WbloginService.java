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

public class WbloginService {
    private static UserInfoDao userInfoDao = null;
    private static WbloginService instance = null;
    private String appId = "2097788999";
    private String appSecret = "9ac141357f306b78cdf881560d1e6c9a";

    public WbloginService(){
        userInfoDao = UserInfoDao.getInstance();
    }

    public static WbloginService getInstance(){
        if(instance == null){
            synchronized (WbloginService.class){
                if(instance == null){
                    instance = new WbloginService();
                }
            }
        }
        return instance;
    }
    public String getAccessToken(String code) {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        String result = null;
        try {
            URL url = new URL("https://api.weibo.com/oauth2/access_token?client_id=" + appId + "&client_secret=" + appSecret +
                    "&grant_type=authorization_code&redirect_uri=http%3a%2f%2fbrandonxcc.top%3a8080%2fMyNetEase%2flogin%2fWbToken&code=" + code);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            inputStream = conn.getInputStream();
            result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public void getOpenid(String accessToken, String uid){
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        String result = null;
        try {
            URL url = new URL("https://api.weibo.com/2/users/show.json?access_token=" + accessToken + "&uid=" + uid);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset","utf-8");
            inputStream = conn.getInputStream();
            result = new BufferedReader(new InputStreamReader(inputStream,"utf-8")).lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject jsonObject = new JSONObject(result);
            UserInfo userInfo = new UserInfo(" ",jsonObject.getString("screen_name"),jsonObject.getString("idstr"));
            userInfoDao.insert(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
