package MessageBoard.service;

import MessageBoard.been.UserInfo;
import MessageBoard.dao.UserInfoDao;

public class UserInfoService {
    private UserInfoDao userInfoDao = null;
    private static UserInfoService instance = null;

    public UserInfoService(){
        userInfoDao = UserInfoDao.getInstance();
    }
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
    public boolean registerCheck(UserInfo user){
        if(user.getPassword() == null && user.getPassword() == null) return false;
        else if(userInfoDao.select(user.getUsername()) != null) return false;
        else {
            userInfoDao.insert(user);
            return true;
        }
    }
    public boolean loginCheck(UserInfo user){
        if(user.getUsername() == null && user.getPassword() == null) return false;
        else return userInfoDao.select(user.getUsername()) != null;
    }
}
