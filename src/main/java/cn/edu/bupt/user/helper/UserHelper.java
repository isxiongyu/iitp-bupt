package cn.edu.bupt.user.helper;

import cn.edu.bupt.enums.UserStatus;
import cn.edu.bupt.user.dao.UserDo;
import cn.edu.bupt.user.model.User;

/**
 * ClassName: UserHelper
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 1:22 下午
 */
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
}
