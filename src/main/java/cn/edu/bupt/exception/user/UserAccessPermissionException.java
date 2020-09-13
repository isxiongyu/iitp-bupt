package cn.edu.bupt.exception.user;

/**
 * ClassName: UserAccessPromiseException
 * Description:
 * Create by xiongyu
 * Date: 2020/9/13 11:04 下午
 */
public class UserAccessPermissionException extends Exception {
    public UserAccessPermissionException() {
        super();
    }

    public UserAccessPermissionException(String message) {
        super(message);
    }
}
