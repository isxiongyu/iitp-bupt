package cn.edu.bupt.admin.service;

import cn.edu.bupt.admin.adminexception.AdminAccessPermissionException;
import cn.edu.bupt.admin.model.Admin;

public interface AdminService {
    void login(Admin admin) throws AdminAccessPermissionException;
}
