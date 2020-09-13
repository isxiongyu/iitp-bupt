package cn.edu.bupt.admin.handler;

import cn.edu.bupt.admin.model.Admin;
import cn.edu.bupt.admin.service.AdminService;
import cn.edu.bupt.exception.admin.AdminAccessPermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login.do")
    public ModelAndView login(Admin admin, HttpServletRequest request) throws AdminAccessPermissionException {
        ModelAndView modelAndView = new ModelAndView();
        adminService.login(admin);
        // 下面两步干啥的？
        modelAndView.addObject("admin", admin);
        request.getSession().setAttribute("admin", admin);
        modelAndView.setViewName("/redirect: jsps/admin/main.jsp");
        return modelAndView;
    }
}
