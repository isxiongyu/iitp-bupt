package cn.edu.bupt.user.dao;

import cn.edu.bupt.user.model.User;

import java.util.List;

/**
 * ClassName: UserDap
 * Description:
 * Create by xiongyu
 * Date: 2020/9/8 11:44 下午
 */
public interface UserDao {
    User queryByUsername(String username);
    User queryByPhone(String phone);
    User queryByEmail(String email);
    List<User> queryByDepartment(int department);
    User queryByUnique(String username, String phone, String email);
    int insertUser(User user);
}
