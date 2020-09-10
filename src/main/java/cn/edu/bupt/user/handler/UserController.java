package cn.edu.bupt.user.handler;

import cn.edu.bupt.exception.RegisterException;
import cn.edu.bupt.user.model.User;
import cn.edu.bupt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: UserController
 * Description:
 * Create by xiongyu
 * Date: 2020/9/9 2:02 下午
 */

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/register.do")
    public ModelAndView register(@Validated User user, BindingResult br) throws RegisterException {
        ModelAndView mv = new ModelAndView();
        mv.addObject(user);
        int errorCount = br.getErrorCount();
        if (errorCount > 0) {
            FieldError nameError = br.getFieldError("username");
            FieldError emailError = br.getFieldError("email");
            FieldError phoneError = br.getFieldError("phone");
            FieldError sexError = br.getFieldError("sex");
            FieldError yearError = br.getFieldError("year");
            FieldError departmentError = br.getFieldError("department");
            if (nameError != null) {
                String nameErrorMsg = nameError.getDefaultMessage();
                mv.addObject("nameErrorMsg", nameErrorMsg);
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
                mv.addObject("departmentErrorMsg", "请填入正确的公寓号");
            }
            mv.setViewName("user/register");
            return mv;
        }
        userService.register(user);
        mv.setViewName("user/success");
        return mv;
    }
}
