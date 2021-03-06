package cn.edu.bupt.admin.service;

import cn.edu.bupt.admin.dao.AdminDao;
import cn.edu.bupt.admin.model.Admin;
import cn.edu.bupt.exception.admin.AdminAccessPermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminDao adminDao;

    public void login(Admin admin) throws AdminAccessPermissionException {
        Admin getAdmin = adminDao.selectByAdminName(admin.getAdminName());
        if (getAdmin == null || !getAdmin.getPassword().equals(admin.getPassword())) {
            throw new AdminAccessPermissionException("用户不存在或密码错误");
        }
        System.out.println("登录");
    }
}
