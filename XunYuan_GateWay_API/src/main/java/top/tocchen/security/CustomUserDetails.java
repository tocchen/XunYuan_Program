//package top.tocchen.security;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import top.tocchen.enums.UserRoleEnum;
//import top.tocchen.vo.UserAuthVO;
//
//import java.util.Collection;
//
///**
// * @author tocchen
// * @date 2023/2/23 12:52
// * @since jdk 1.8
// **/
//@Data
//public class CustomUserDetails implements UserDetails {
//
//    private String userId;
//
//    private String userName;
//
//    private String userPassword;
//
//    private UserRoleEnum role;
//
//    private String salt;
//
//
//    public CustomUserDetails(UserAuthVO authVO){
//        this.userId = authVO.getUserId();
//        this.userName = authVO.getUserName();
//        this.userPassword = authVO.getPassword();
//        this.role = authVO.getRole();
//        this.salt = authVO.getSalt();
//    }
//
//    // 获取-权限-不能为Null
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
