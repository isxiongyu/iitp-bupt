package cn.edu.bupt.user.dao.repository;

import cn.edu.bupt.user.dao.UserDao;
import cn.edu.bupt.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserRepository
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 1:38 下午
 */
public class UserRepository {
    private UserDao userDao;
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }
    public User queryByPhone(String phone) {
        return userDao.queryByPhone(phone);
    }
    public User queryByEmail(String email) {
        return userDao.queryByEmail(email);
    }
    public List<User> queryByDepartment(int department) {
        return userDao.queryByDepartment(department);
    }
    public User queryBy3Unique(String username, String phone, String email) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("phone", phone);
        map.put("email", email);
        return userDao.queryBy3Unique(map);
    }
    public User queryByUnique(String unique) {
        return userDao.queryByUnique(unique);
    }
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
