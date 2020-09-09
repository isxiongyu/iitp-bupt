package cn.edu.bupt.user.helper;

import cn.edu.bupt.enums.UserStatus;
import cn.edu.bupt.user.dao.UserDo;
import cn.edu.bupt.user.model.User;
import org.springframework.stereotype.Component;

/**
 * ClassName: UserHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 1:22 下午
 */

@Component
public class UserHelper {
    public User convert2Model(UserDo userDo) {
        User user = new User();
        user.setUserId(userDo.getUserId());
        user.setDepartment(userDo.getDepartment());
        user.setEmail(userDo.getEmail());
        user.setPassword(userDo.getPassword());
        user.setPhone(userDo.getPhone());
        user.setReport(userDo.getReport());
        user.setSex(userDo.getSex());
        user.setStar(userDo.getStar());
        user.setUserDescription(userDo.getUserDescription());
        user.setUsername(userDo.getUsername());
        user.setYear(userDo.getYear());
        user.setUserStatus(UserStatus.getByCode(userDo.getUserStatus()));
        return user;
    }

    public UserDo convert2Do(User user) {
        UserDo userDo = new UserDo();
        userDo.setUserId(user.getUserId());
        userDo.setDepartment(user.getDepartment());
        userDo.setEmail(user.getEmail());
        userDo.setPassword(user.getPassword());
        userDo.setPhone(user.getPhone());
        userDo.setReport(user.getReport());
        userDo.setSex(user.getSex());
        userDo.setStar(user.getStar());
        userDo.setUserDescription(user.getUserDescription());
        userDo.setUsername(user.getUsername());
        userDo.setYear(user.getYear());
        userDo.setUserStatus(user.getUserStatus().getCode());
        return userDo;
    }
}
