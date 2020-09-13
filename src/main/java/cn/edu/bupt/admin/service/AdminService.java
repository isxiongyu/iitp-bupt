package cn.edu.bupt.admin.service;

import cn.edu.bupt.admin.model.Admin;
import cn.edu.bupt.exception.admin.AdminAccessPermissionException;

public interface AdminService {
    void login(Admin admin) throws AdminAccessPermissionException;
}
