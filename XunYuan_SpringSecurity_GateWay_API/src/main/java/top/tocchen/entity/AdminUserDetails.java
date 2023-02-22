package top.tocchen.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.tocchen.vo.UserAuthVO;

import java.util.Collection;

/**
 * @author tocchen
 * @date 2023/2/22 20:19
 * @since jdk 1.8
 **/
@Data
public class AdminUserDetails implements UserDetails {

    private UserAuthVO userAuthVO;

    public AdminUserDetails(UserAuthVO userAuthVO) {
        this.userAuthVO = userAuthVO;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
