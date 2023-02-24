package top.tocchen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tocchen.entity.NormalUser;
import top.tocchen.mapper.NormalUserMapper;
import top.tocchen.service.NormalUserService;
import top.tocchen.utils.MD5Util;
import top.tocchen.utils.UUIDUtil;
import top.tocchen.utils.exceptions.UserExistNotRegisterException;
import top.tocchen.vo.NormalUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 22:21
 * @since jdk 1.8
 **/
@Service
public class NormalUserServiceImpl implements NormalUserService {

    @Autowired
    private NormalUserMapper userMapper;

    public void insertNormalUser(NormalUserVo normalUserVo) {
        if (userMapper.existNormalUserByPhone(normalUserVo.getUserPhone()) != 0){
            throw new UserExistNotRegisterException();
        }
        NormalUser user = new NormalUser(
                UUIDUtil.generateUUID(),
                normalUserVo.getUserName(),
                normalUserVo.getUserAccount(),
                normalUserVo.getUserPhone(),
                normalUserVo.getUserEmail(),
                MD5Util.generateMd5(normalUserVo.getUserPassword())
        );
        userMapper.insertNormalUser(user);
    }

    public NormalUser queryNormalUserByPhoneOrAccount(NormalUserVo normalUserVo) {
        NormalUser result = userMapper.queryNormalUserByPhoneOrAccount(normalUserVo.getUserPhone());
        return result;
    }
}
