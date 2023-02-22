package top.tocchen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tocchen.entity.UserEntity;
import top.tocchen.mapper.UserMapper;
import top.tocchen.service.UserService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.MD5Util;
import top.tocchen.utils.exceptions.DataExistsException;
import top.tocchen.utils.exceptions.QueryException;

/**
 * @author tocchen
 * @date 2023/2/9 21:39
 * @since jdk 1.8
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserMapper userMapper;

    public UserEntity queryIdByUser(String id) {
        UserEntity result = userMapper.queryIdByUser(id);
        DBUtil.isEmpty2QueryException(result);
        result.setPassword(null);
        return result;
    }

    public UserEntity queryMoreByUser(String jobNumber, String email, String password) {
        String userId = userMapper.queryEmailJobNumberByUserId(email, jobNumber);
        DBUtil.isEmpty2QueryException(userId);

        String salt = this.queryUserSalt(userId);

        UserEntity result = userMapper.queryMoreByUser(jobNumber, email, MD5Util.generateMD5(password,salt).get("MD5Str"));
        DBUtil.isEmpty2QueryException(result);
        result.setPassword(null);

        return result;
    }

    public void saveUser(String id, String jobNumber, String email, String password,String salt) {
        if (userMapper.queryJobNumberExists(jobNumber) == 1){
            throw new DataExistsException();
        }
        userMapper.saveUser(id, jobNumber, email, password,salt);
    }

    public int updatePassword(String id, String newPassword) {
        int result = userMapper.updatePassword(id, newPassword);
        DBUtil.numberNo1UpdateException(result);
        return result;
    }

    public int removeUser(String id) {
        int result = userMapper.removeUser(id);
        DBUtil.numberNo1UpdateException(result);
        return result;
    }

    public String queryUserSalt(String id) {
        String salt = userMapper.queryUserSalt(id);
        DBUtil.isEmpty2QueryException(salt);
        return salt;
    }
}
