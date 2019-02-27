package service.login;

import bee.UserInfo;
import dao.UserInfoDao;

import java.security.MessageDigest;

public class UserInfoService {
    private UserInfoDao userInfoDao = null;
    private static UserInfoService instance = null;
    private static final Object salt = "be5e0323a9195ade5f56695ed9f2eb6b036f3e6417115d0cbe2fb9d74d8740406838dc84f152014b39a2414fb3530a40bc028a9e87642bd03cf5c36a1f70801e";
    private static final String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    public UserInfoService(){ userInfoDao = UserInfoDao.getInstance();}
    public static UserInfoService getInstance(){
        if(instance == null){
            synchronized (UserInfoService.class){
                if(instance == null){
                    instance = new UserInfoService();
                }
            }
        }
        return instance;
    }
    public boolean checkRegister(UserInfo userInfo){
        if(userInfo.getId() == null || userInfo.getPassword() == null || userInfo.getUsername() == null){
            return false;
        }else if(userInfoDao.select(userInfo.getId()) != null){
            return false;
        }else{
            userInfo.setPassword(encode(userInfo.getPassword()));
            userInfoDao.insert(userInfo);
            return true;
        }
    }
    public boolean checkLogin(UserInfo userInfo){
        String enPassword = encode(userInfo.getPassword());
        UserInfo checkUser = userInfoDao.select(userInfo.getId());
        if(checkUser == null) return false;
        else return enPassword.equals(checkUser.getPassword());
    }
    private String encode(String rawPassword){
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
                result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPassword).getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private String mergePasswordAndSalt(String rawPassword){
        if(rawPassword == null) rawPassword = "";
        return rawPassword + "{" + salt.toString() + "}";
    }
    private String byteArrayToHexString(byte[] b){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            stringBuffer.append(byteToHex(b[i]));
        }
        return stringBuffer.toString();
    }
    private String byteToHex(byte b){
        int n = b;
        if(n < 0) n = n + 256;
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
