package cn.edu.bupt.user.handler;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.exception.ActiveException;
import cn.edu.bupt.exception.LoginException;
import cn.edu.bupt.exception.RegisterException;
import cn.edu.bupt.exception.SystemException;
import cn.edu.bupt.user.model.User;
import cn.edu.bupt.user.service.impl.UserServiceImpl;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: UserController
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 2:02 下午
 */

@RequestMapping("user")
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CommonHelper commonHelper;

    ExecutorService executor = Executors.newFixedThreadPool(6);

    @RequestMapping("/register.do")
    public ModelAndView register(@Validated User user, BindingResult br) throws RegisterException, SystemException {
        ModelAndView mv = new ModelAndView();
        mv.addObject(user);
        int errorCount = br.getErrorCount();
        if (errorCount > 0) {
            FieldError nameError = br.getFieldError("username");
            FieldError passwordError = br.getFieldError("password");
            FieldError emailError = br.getFieldError("email");
            FieldError phoneError = br.getFieldError("phone");
            FieldError sexError = br.getFieldError("sex");
            FieldError yearError = br.getFieldError("year");
            FieldError departmentError = br.getFieldError("department");
            if (nameError != null) {
                String nameErrorMsg = nameError.getDefaultMessage();
                mv.addObject("nameErrorMsg", nameErrorMsg);
            }
            if (passwordError != null) {
                String passwordErrorMsg = passwordError.getDefaultMessage();
                mv.addObject("passwordErrorMsg", passwordErrorMsg);
            }
            if (emailError != null) {
                String emailErrorMsg = emailError.getDefaultMessage();
                mv.addObject("emailErrorMsg", emailErrorMsg);
            }
            if (phoneError != null) {
                String phoneErrorMsg = phoneError.getDefaultMessage();
                mv.addObject("phoneErrorMsg", phoneErrorMsg);
            }
            if (yearError != null) {
                String yearErrorMsg = yearError.getDefaultMessage();
                mv.addObject("yearErrorMsg", yearErrorMsg);
            }
            if (sexError != null) {
                String sexErrorMsg = sexError.getDefaultMessage();
                mv.addObject("sexErrorMsg", sexErrorMsg);
            }
            if (departmentError != null) {
                String departmentErrorMsg = departmentError.getDefaultMessage();
                mv.addObject("departmentErrorMsg", departmentErrorMsg);
            }
            mv.setViewName("user/register");
            return mv;
        }
        user.setCode(commonHelper.getUUID());
        userService.register(user);
        executor.execute(() -> {
            try {
                userService.sendMail(user);
            } catch (SystemException e) {
                logger.error("发送激活邮件失败");
            }
        });
        mv.setViewName("user/success");
        logger.info("user = " + user + " 注册成功");
        return mv;
    }

    @RequestMapping("/active.do")
    public ModelAndView active(String code) throws ActiveException {
        ModelAndView mv = new ModelAndView();
        userService.active(code);
        mv.setViewName("user/success");
        return mv;
    }

    @RequestMapping("/login.do")
    public ModelAndView login(String name, String password) throws LoginException {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isEmpty(name)) {
            mv.addObject("nameError", "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            mv.addObject("passwordError", "密码不能为空");
        }
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            mv.addObject("loginError", "用户名密码不能为空");
            mv.setViewName("user/login");
            return mv;
        }
        userService.login(name, password);
        mv.setViewName("main");
        return mv;
    }
}
