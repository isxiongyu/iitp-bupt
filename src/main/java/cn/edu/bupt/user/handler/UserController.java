package cn.edu.bupt.user.handler;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.enums.EmailType;
import cn.edu.bupt.exception.user.*;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
        try {
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
                    commonHelper.sendMail(user, EmailType.REGISTER);
                } catch (SystemException e) {
                    logger.error("发送激活邮件失败, 失败信息：" + e.getMessage());
                }
            });
            mv.setViewName("user/success");
            logger.info("user = " + user + " 注册成功");
        } catch (RegisterException re) {
            throw re;
        } catch (Exception e) {
            logger.error("系统性异常：{}", e.getMessage());
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
        return mv;
    }

    @RequestMapping("/active.do")
    public ModelAndView active(String code) throws ActiveException, SystemException {
        ModelAndView mv = new ModelAndView();
        try {
            userService.active(code);
        } catch (ActiveException ae) {
            throw ae;
        } catch (Exception e) {
            logger.error("系统性异常：{}", e.getMessage());
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
        mv.setViewName("user/success");
        return mv;
    }

    @RequestMapping("/login.do")
    public ModelAndView login(String name, String password, HttpServletRequest request) throws LoginException, SystemException {
        ModelAndView mv = new ModelAndView();
        try {
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
            User user = userService.login(name, password);
            request.getSession().setAttribute("user", user);
        } catch (LoginException le) {
            throw le;
        } catch (Exception e) {
            logger.error("系统性异常：{}", e.getMessage());
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
        mv.setViewName("main");
        return mv;
    }

    @RequestMapping("/sendVerifyCode.do")
    public ModelAndView sendVerifyCode(String email) throws ModPasswordException, SystemException {
        ModelAndView mv = new ModelAndView();
        try {
            logger.info("发送验证码至邮箱：" + email);
            userService.sendEmailVerifyCode(email);
        } catch (ModPasswordException me) {
            throw me;
        } catch (Exception e) {
            logger.error("系统性异常：{}", e.getMessage());
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
        mv.setViewName("/user/verifyCode");
        mv.addObject("email", email);
        return mv;
    }

    @RequestMapping("/verify.do")
    public ModelAndView verify(String email, String code) throws SystemException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("email", email);
        mv.addObject("code", code);
        try {
            if (email == null || code == null) {
                throw new ModPasswordException("请输入邮箱或者验证码");
            }
            userService.verifyCode(email, code);
            mv.setViewName("/user/modPassword");
        } catch (ModPasswordException mpe) {
            mv.addObject("ModPasswordError", mpe.getMessage());
            mv.setViewName("/user/verifyCode");
        } catch (Exception e) {
            logger.error("系统性异常：{}", e.getMessage());
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
        return mv;
    }

    @RequestMapping("/modPassword.do")
    public ModelAndView modPassword(String email, String newPassword, String confirmPassword) throws ModPasswordException, SystemException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("email", email);
        mv.addObject("newPassword", newPassword);
        mv.addObject("confirmPassword", confirmPassword);
        try {
            if (confirmPassword == null || newPassword == null) {
                throw new ModPasswordException("请输入新密码或者确认密码");
            }
            userService.modPassword(email, newPassword, confirmPassword);
        } catch (ModPasswordException mpe) {
            throw mpe;
        } catch (Exception e) {
            logger.error("系统性异常：{}", e.getMessage());
            logger.error("系统性异常：{}", Arrays.toString(e.getStackTrace()));
            throw new SystemException();
        }
        mv.addObject("name", email);
        mv.addObject("password", newPassword);
        mv.setViewName("/user/login");
        return mv;
    }
}
