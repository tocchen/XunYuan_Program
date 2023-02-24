//package top.tocchen.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import top.tocchen.enums.UserRoleEnum;
//import top.tocchen.utils.exceptions.TokenAuthException;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author tocchen
// * @date 2023/2/22 18:54
// * @since jdk 1.8
// * JWT 工具类
// * 根据用户ID生成Token信息 对应的信息实体类{@link top.tocchen.vo.UserAuthVO}
// **/
//@Component
//public class JwtTokenUtil {
//
//    private static final String USER_INFO_ID = "Id";
//    private static final String USER_INFO_PASSWORD = "Password";
//    private static final String USER_INFO_ROLE = "Role";
//    private static final String USER_INFO_USERNAME = "Name";
//    private static final String CLAIM_KEY_CREATED = "created";
//
//    private static final String MD5_STR_KEY = "MD5Str";
//
//    /** 设置过期时间 */
//    @Value("${jwt.expiration}")
//    Long expiration;
//    /** 签名盐值 */
//    @Value("${jwt.secretSalt}")
//    String secretSalt;
//
//    /**
//     * 通过用户信息生成token信息
//     * @param claims 参数信息
//     * @return Token String
//     */
//    public String generateToken(Map<String, Object> claims){
//        return Jwts.builder()
//                .setClaims(claims) //参数
//                .setExpiration(generateExpirationDate()) //设置有效时间
//                .signWith(SignatureAlgorithm.HS512,secretSalt) // 设置签名
//                .compact();
//    }
//
//
//    /**
//     * 获取Token种携带的参数
//     * @param token token String
//     * @return 参数Claims对象
//     */
//    public Claims getClaimsFromToken(String token){
//        Claims claims = null;
//        try{
//            claims = Jwts.parser().setSigningKey(secretSalt)
//                    .parseClaimsJws(token)
//                    .getBody();
//        }catch (Exception e){
//            throw new TokenAuthException();
//        }
//        return claims;
//    }
//
//    public String getUserIdFormToken(String token){
//        return getClaimsFromToken(token).get(USER_INFO_ID).toString();
//    }
//    /**
//     * 生成Token有效时间
//     * @return 有效时间
//     */
//    public Date generateExpirationDate() {
//        return new Date(System.currentTimeMillis() + expiration * 1000);
//    }
//
//    /**
//     * 通过DB种的用户数据信息，判断token是否有效
//     * @return 布尔值
//     */
//    public boolean validateToken(String token, CustomUserDetails userDetails){
//        // 通过三个方面进行验证 1. userId 2. Role 3. password
//        Claims claimsFromToken = getClaimsFromToken(token);
//        String id = claimsFromToken.get(USER_INFO_ID, String.class);
//        String password = claimsFromToken.get(USER_INFO_PASSWORD, String.class);
//        String username = claimsFromToken.get(USER_INFO_USERNAME, String.class);
//        UserRoleEnum role = claimsFromToken.get(USER_INFO_ROLE, UserRoleEnum.class);
//        return userDetails.getUserId().equals(id)
//                && userDetails.getRole() == role
//                && userDetails.getPassword().equals(password)
//                && isTokenExpired(token)
//                && userDetails.getUsername().equals(username);
//    }
//
//
//    /**
//     * 判断token是否已经失效
//     */
//    public boolean isTokenExpired(String token) {
//        Date expiredDate = getExpiredDateFromToken(token);
//        return expiredDate.before(new Date());
//    }
//
//    /**
//     * 从token中获取过期时间
//     */
//    public Date getExpiredDateFromToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        return claims.getExpiration();
//    }
//
//    public String generateToken(CustomUserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(USER_INFO_ID, userDetails.getUserId());
//        claims.put(USER_INFO_PASSWORD, userDetails.getPassword());
//        claims.put(USER_INFO_ROLE, userDetails.getRole());
//        claims.put(USER_INFO_USERNAME, userDetails.getUsername());
//        claims.put(CLAIM_KEY_CREATED,new Date());
//        return generateToken(claims);
//    }
//
//    /**
//     * 判断token是否可以被刷新
//     */
//    public boolean canRefresh(String token) {
//        return !isTokenExpired(token);
//    }
//
//    /**
//     * 刷新token
//     */
//    public String refreshToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }
//
//}
