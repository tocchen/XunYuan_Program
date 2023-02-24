package top.tocchen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import top.tocchen.utils.JwtTokenUtil;
import top.tocchen.utils.redis.RedisCacheKeyGenerator;
import top.tocchen.utils.redis.RedisDBName;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author tocchen
 * @date 2023/2/24 16:00
 * @since jdk 1.8
 * JWT 过滤器
 **/
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    RedisTemplate redisTemplate;

    //实现过滤
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取指定头信息
        String authHeader = request.getHeader(this.tokenHeader);
        // 判断获取的值是否未空，并且是以tokenHead为前缀
        if (authHeader != null && authHeader.startsWith(this.tokenHead)){
            // 获取Token值
            String authToken = authHeader.substring(this.tokenHead.length());
            // 获取token中存储的userId
            String userId = jwtTokenUtil.getUserUserIdFromToken(authToken);
            String tokenKey = RedisCacheKeyGenerator.generatorAllKey(null, RedisDBName.REDIS_TOKEN_USER_NAME, userId);
            // TODO 判断token是否存储到 SecurityContextHolder.getContext().getAuthentication()当中，或者是否在Redis换存中存在
             //1. 判读是否为空
            if (userId != null
                    && SecurityContextHolder.getContext().getAuthentication() == null
                    && redisTemplate.boundValueOps(tokenKey).get() == null){
                //TODO 获取用户信息
                CustomUserDetailsImpl userDetails = null;
                if (jwtTokenUtil.validateToken(authToken,userDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    BoundValueOperations boundValueOps = redisTemplate.boundValueOps(tokenKey);
                    boundValueOps.set(authentication);
                    boundValueOps.expire(1, TimeUnit.DAYS);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}
