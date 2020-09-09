package cn.edu.bupt.user.dao;

import cn.edu.bupt.user.model.User;

import java.util.List;
import java.util.Map;

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
    User queryBy3Unique(Map<String, String> params);
    User queryByUnique(String unique);
    int insertUser(User user);
}
