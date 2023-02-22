package top.tocchen.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.tocchen.entity.AdminUserDetails;
import top.tocchen.utils.exceptions.UserIdNotFoundException;

/**
 * @author tocchen
 * @date 2023/2/22 21:01
 * @since jdk 1.8
 * 实现 UserDetailsService 添加 loadUserById(String id)方法
 **/
public interface XunYuanUserDetailsService extends UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public AdminUserDetails loadUserById(String userId) throws UserIdNotFoundException;
}
