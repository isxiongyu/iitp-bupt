package cn.edu.bupt.user.dao.repository;

import cn.edu.bupt.user.dao.UserDao;
import cn.edu.bupt.user.helper.UserHelper;
import cn.edu.bupt.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: UserRepository
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 1:38 下午
 */

@Repository
public class UserRepository {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserHelper userHelper;
    public User queryByUsername(String username) {
        System.out.println(userDao);
        return userHelper.convert2Model(userDao.queryByUsername(username));
    }
    public User queryByPhone(String phone) {
        return userHelper.convert2Model(userDao.queryByPhone(phone));
    }
    public User queryByEmail(String email) {
        return userHelper.convert2Model(userDao.queryByEmail(email));
    }
    public List<User> queryByDepartment(int department) {
        return userDao.queryByDepartment(department).stream().
                map(userHelper::convert2Model).collect(Collectors.toList());
    }
    public User queryBy3Unique(String username, String phone, String email) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("phone", phone);
        map.put("email", email);
        return userHelper.convert2Model(userDao.queryBy3Unique(map));
    }
    public User queryByUnique(String unique) {
        return userHelper.convert2Model(userDao.queryByUnique(unique));
    }
    public int insertUser(User user) {
        return userDao.insertUser(userHelper.convert2Do(user));
    }
    public User queryByCode(String code) {
        return userHelper.convert2Model(userDao.queryByCode(code));
    }
    public void updateStatusByCode(String code, String status) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("status", status);
        userDao.updateStatusByCode(params);
    }
    public void updatePasswordByEmail(String email, String newPassword) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("newPassword", newPassword);
        userDao.updatePasswordByEmail(params);
    }
}
