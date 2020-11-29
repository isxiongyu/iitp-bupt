package cn.edu.bupt.exception.handler;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.exception.user.ActiveException;
import cn.edu.bupt.exception.user.LoginException;
import cn.edu.bupt.exception.user.RegisterException;
import cn.edu.bupt.exception.user.SystemException;
import cn.edu.bupt.goods.dao.repository.CategoryRepository;
import cn.edu.bupt.goods.model.Category;
import cn.edu.bupt.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: ExceptionController
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 10:49 下午
 */

@ControllerAdvice
public class ExceptionController {

    @Autowired
    private CommonHelper commonHelper;

    @Autowired
    private CategoryRepository categoryRepository;

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

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

    @ExceptionHandler(SystemException.class)
    public ModelAndView systemException(SystemException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:/WEB-INF/exception/systemException.jsp");
        return mv;
    }

    @ExceptionHandler(ActiveException.class)
    public ModelAndView activeException(ActiveException ex) {
        ModelAndView mv = new ModelAndView();
        String exceptionMsg = ex.getMessage();
        mv.addObject("activeError", exceptionMsg);
        mv.setViewName("forward:/WEB-INF/exception/activeException.jsp");
        return mv;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView maxUploadSizeExceededException() {
        ModelAndView mv = new ModelAndView();
        List<Category> categories = categoryRepository.selectAll();
        mv.addObject("imgErrorMsg", "上传图片超过1M大小");
        mv.addObject("categories", categories);
        mv.setViewName("user/addGoods");
        return mv;
    }
}
