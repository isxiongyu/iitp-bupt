package cn.edu.bupt.user.handler;

import cn.edu.bupt.common.CommonHelper;
import cn.edu.bupt.enums.EmailType;
import cn.edu.bupt.exception.user.*;
import cn.edu.bupt.goods.model.Category;
import cn.edu.bupt.goods.model.Goods;
import cn.edu.bupt.goods.sevice.CategoryService;
import cn.edu.bupt.goods.sevice.GoodsService;
import cn.edu.bupt.user.model.User;
import cn.edu.bupt.user.service.UserService;
import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Properties;
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
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CommonHelper commonHelper;

    private ExecutorService executor = Executors.newFixedThreadPool(6);

    /**
     * 注册
     * @param user
     * @param br
     * @return
     * @throws RegisterException
     * @throws SystemException
     */
    @RequestMapping("/register.do")
    public ModelAndView register(@Validated User user, BindingResult br) throws RegisterException, SystemException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
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
                    String departmentErrorMsg = "请填写争正确的公寓号";
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
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        return mv;
    }

    /**
     * 激活
     * @param code 激活码
     * @return
     * @throws ActiveException
     * @throws SystemException
     */
    @RequestMapping("/active.do")
    public ModelAndView active(String code) throws ActiveException, SystemException {
        ModelAndView mv = new ModelAndView();
        try {
            userService.active(code);
        } catch (ActiveException ae) {
            throw ae;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        mv.setViewName("user/success");
        return mv;
    }

    /**
     * 登录
     * @param name
     * @param password
     * @param request
     * @return
     * @throws LoginException
     * @throws SystemException
     */
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
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        mv.setViewName("main");
        return mv;
    }

    /**
     * 修改密码发送验证码
     * @param email
     * @return
     * @throws ModPasswordException
     * @throws SystemException
     */
    @RequestMapping("/sendVerifyCode.do")
    public ModelAndView sendVerifyCode(String email) throws ModPasswordException, SystemException {
        ModelAndView mv = new ModelAndView();
        try {
            logger.info("发送验证码至邮箱：" + email);
            userService.sendEmailVerifyCode(email);
        } catch (ModPasswordException me) {
            throw me;
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        mv.setViewName("/user/verifyCode");
        mv.addObject("email", email);
        return mv;
    }

    /**
     * 修改密码  验证  验证码
     * @param email
     * @param code
     * @return
     * @throws SystemException
     */
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
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        return mv;
    }

    /**
     * 修改密码
     * @param email
     * @param newPassword
     * @param confirmPassword
     * @return
     * @throws ModPasswordException
     * @throws SystemException
     */
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
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        mv.addObject("name", email);
        mv.addObject("password", newPassword);
        mv.setViewName("/user/login");
        return mv;
    }

    @RequestMapping("/addGoodsLink.do")
    public ModelAndView addGoodsLink(HttpServletRequest request) throws SystemException, LoginException {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new LoginException("请先登录后再添加物品");
        }
        List<Category> categories = categoryService.selectAll();
        mv.addObject("categories", categories);
        mv.setViewName("/user/addGoods");
        return mv;
    }

    @RequestMapping("/addGoods.do")
    public ModelAndView addGoods(@Validated Goods goods, BindingResult br,
                                 MultipartFile image, HttpServletRequest request) throws SystemException, LoginException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("goods", goods);
        List<Category> categories = categoryService.selectAll();
        mv.addObject("categories", categories);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new LoginException("请先登录后再添加物品");
        }
        try {
            int errorCount = br.getErrorCount();
            if (errorCount > 0) {
                FieldError nameError = br.getFieldError("name");
                FieldError descriptionError = br.getFieldError("description");
                FieldError priceError = br.getFieldError("price");
                FieldError restError = br.getFieldError("rest");
                FieldError goodCategoryIdError = br.getFieldError("goodCategoryId");
                FieldError categorySecondIdError = br.getFieldError("categorySecondId");
                FieldError imgError = br.getFieldError("img");
                if (nameError != null) {
                    String nameErrorMsg = nameError.getDefaultMessage();
                    mv.addObject("nameErrorMsg", nameErrorMsg);
                }
                if (descriptionError != null) {
                    String descriptionMsg = descriptionError.getDefaultMessage();
                    mv.addObject("descriptionMsg", descriptionMsg);
                }
                if (priceError != null) {
                    String priceErrorMsg = "请输入正确的价格";
                    mv.addObject("priceErrorMsg", priceErrorMsg);
                }
                if (restError != null) {
                    String restErrorMsg = "请输入正确的物品数量";
                    mv.addObject("restErrorMsg", restErrorMsg);
                }
                if (goodCategoryIdError != null) {
                    String goodCategoryIdErrorMsg = goodCategoryIdError.getDefaultMessage();
                    mv.addObject("goodCategoryIdErrorMsg", goodCategoryIdErrorMsg);
                }
                if (categorySecondIdError != null) {
                    String categorySecondIdErrorMsg = categorySecondIdError.getDefaultMessage();
                    mv.addObject("categorySecondIdErrorMsg", categorySecondIdErrorMsg);
                }
                if (imgError != null) {
                    String imgErrorMsg = imgError.getDefaultMessage();
                    mv.addObject("imgErrorMsg", imgErrorMsg);
                }
                mv.setViewName("user/addGoods");
                return mv;
            }
            goods.setId(commonHelper.getUUID());
            goods.setOwnerId(user.getUserId());
            if (image == null || image.getSize() == 0) {
                mv.addObject("imgErrorMsg", "上传的图片有错");
                mv.setViewName("user/addGoods");
                return mv;
            } else {
                Properties pros = new Properties();
                pros.load(this.getClass().getResourceAsStream("/upload.properties"));
                String uploadPath = pros.getProperty("upload_path");
                String imgName = goods.getId() + ".jpg";
                String rootPath = request.getSession().getServletContext().getRealPath("/");
                File file = new File(rootPath + uploadPath, imgName);
                image.transferTo(file);
                goods.setImg(imgName);
            }
            goodsService.addGoods(goods);
        } catch (Exception e) {
            logger.error("系统性异常: ", e);
            throw new SystemException();
        }
        mv.setViewName("main");
        return mv;
    }
}
