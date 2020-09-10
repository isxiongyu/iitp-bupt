package cn.edu.bupt.exception.handler;

import cn.edu.bupt.exception.RegisterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: ExceptionController
 * Description:
 * Create by xiongyu
 * Date: 2020/9/10 10:49 下午
 */

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RegisterException.class)
    public ModelAndView registerException(RegisterException ex) {
        String exceptionMsg = ex.getMessage();
        ModelAndView mv = new ModelAndView();
        mv.addObject("registerError", exceptionMsg);
        mv.setViewName("user/register");
        return mv;
    }
}
