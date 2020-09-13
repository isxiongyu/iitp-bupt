package cn.edu.bupt.exception.handler;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.exception.LoginException;
import cn.edu.bupt.exception.RegisterException;
import cn.edu.bupt.exception.SystemException;
import cn.edu.bupt.user.model.User;
import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

/**
 * ClassName: ExceptionController
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 10:49 下午
 */

@ControllerAdvice
public class ExceptionController {

    @Autowired
    CommonHelper commonHelper;

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(RegisterException.class)
    public ModelAndView registerException(RegisterException ex, HttpServletRequest request) throws SystemException {
        String exceptionMsg = ex.getMessage();
        ModelAndView mv = new ModelAndView();
        User user = commonHelper.toBean(request.getParameterMap(), User.class);
        mv.addObject("user", user);
        mv.addObject("registerError", exceptionMsg);
        mv.setViewName("user/register");
        return mv;
    }

    @ExceptionHandler(LoginException.class)
    public ModelAndView loginException(LoginException ex, HttpServletRequest request) {
        String exceptionMsg = ex.getMessage();
        ModelAndView mv = new ModelAndView();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        mv.addObject("name", name);
        mv.addObject("password", password);
        mv.addObject("loginError", exceptionMsg);
        mv.setViewName("user/login");
        return mv;
    }
}
