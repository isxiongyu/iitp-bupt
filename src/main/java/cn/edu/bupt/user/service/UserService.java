package cn.edu.bupt.user.service;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.enums.UserStatus;
import cn.edu.bupt.exception.RegisterException;
import cn.edu.bupt.user.dao.repository.UserRepository;
import cn.edu.bupt.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ClassName: UserService
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 12:27 上午
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonHelper commonHelper;

    public void register(User user) throws RegisterException {
        user.setUserId(commonHelper.getUUID());
        user.setUserStatus(UserStatus.UN_ACTIVE);
        int res;
        try {
            res = userRepository.insertUser(user);
        } catch (Exception e) {
            throw new RegisterException("用户名已存在，或者邮箱、手机号已被注册");
        }
        if (res != 1) {
            throw new RegisterException("注册失败");
        }
    }

}
