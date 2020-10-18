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
    UserDo queryByUsername(String username);
    UserDo queryByPhone(String phone);
    UserDo queryByEmail(String email);
    List<UserDo> queryByDepartment(int department);
    UserDo queryBy3Unique(Map<String, String> params);
    UserDo queryByUnique(String unique);
    int insertUser(UserDo user);
    UserDo queryByCode(String code);
    void updateStatusByCode(Map<String, String> params);
    void updatePasswordByEmail(Map<String, String> params);
    void updateUser(UserDo userDo);
}
