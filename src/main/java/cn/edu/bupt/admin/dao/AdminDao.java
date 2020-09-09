package cn.edu.bupt.admin.dao;

import cn.edu.bupt.admin.model.Admin;
import cn.edu.bupt.user.model.User;

public interface AdminDao {
    Admin selectByAdminName(String name);
    User selectByUserId(String userId);
    User selectByReportNum();
}
