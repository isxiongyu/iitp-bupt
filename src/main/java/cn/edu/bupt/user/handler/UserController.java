package cn.edu.bupt.user.handler;

import cn.edu.bupt.user.dao.repository.UserRepository;
import cn.edu.bupt.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: UserController
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 2:02 下午
 */

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/selUser.do")
    public void selUser(String username) {
        User user = userRepository.queryByUsername(username);
        System.out.println(user);
    }
}
