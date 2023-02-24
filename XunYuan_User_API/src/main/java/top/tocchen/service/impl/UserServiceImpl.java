//package top.tocchen.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import top.tocchen.entity.AdminUser;
//import top.tocchen.mapper.AdminUserMapper;
//import top.tocchen.service.UserService;
//import top.tocchen.utils.DBUtil;
//import top.tocchen.utils.MD5Util;
//import top.tocchen.utils.exceptions.DataExistsException;
//
///**
// * @author tocchen
// * @date 2023/2/9 21:39
// * @since jdk 1.8
// **/
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired private AdminUserMapper adminUserMapper;
//
//    public AdminUser queryIdByUser(String id) {
//        AdminUser result = adminUserMapper.queryIdByUser(id);
//        DBUtil.isEmpty2QueryException(result);
//        result.setPassword(null);
//        return result;
//    }
//
//    public AdminUser queryMoreByUser(String jobNumber, String email, String password) {
//        String userId = adminUserMapper.queryEmailJobNumberByUserId(email, jobNumber);
//        DBUtil.isEmpty2QueryException(userId);
//
//        String salt = this.queryUserSalt(userId);
//
//        AdminUser result = adminUserMapper.queryMoreByUser(jobNumber, email, MD5Util.generateMD5(password,salt).get("MD5Str"));
//        DBUtil.isEmpty2QueryException(result);
//        result.setPassword(null);
//
//        return result;
//    }
//
//    public void saveUser(String id, String jobNumber, String email, String password,String salt) {
//        if (adminUserMapper.queryJobNumberExists(jobNumber) == 1){
//            throw new DataExistsException();
//        }
//        adminUserMapper.saveUser(id, jobNumber, email, password,salt);
//    }
//
//    public int updatePassword(String id, String newPassword) {
//        int result = adminUserMapper.updatePassword(id, newPassword);
//        DBUtil.numberNo1UpdateException(result);
//        return result;
//    }
//
//    public int removeUser(String id) {
//        int result = adminUserMapper.removeUser(id);
//        DBUtil.numberNo1UpdateException(result);
//        return result;
//    }
//
//    public String queryUserSalt(String id) {
//        String salt = adminUserMapper.queryUserSalt(id);
//        DBUtil.isEmpty2QueryException(salt);
//        return salt;
//    }
//}
