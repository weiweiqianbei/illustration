package com.illustration.filter;

import com.illustration.entity.User;
import com.illustration.entity.vo.LoginUserVO;
import com.illustration.result.GlobalExceptionHandler;
import com.illustration.utils.JsonUtil;
import com.illustration.utils.JwtUtil;
import com.illustration.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 该方法会被doFilter调用
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        log.info("URI===============》{}", uri);
        log.info("请求方式===============》{}", request.getMethod());
        // 获取token
        String token = request.getHeader("Authorization");
        if (token == null || "/user/login".equals(uri) || "/user/register".equals(uri)) {
            // 放行
            doFilter(request, response, filterChain);
            return;
        }
        Claims claims;
        try {
            claims = jwtUtil.parseToken(token);
        } catch (SignatureException e) {
            log.info("JwtAuthenticationFilter:无法转换为Claims\n{}", e.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonUtil.serialize(new GlobalExceptionHandler.ErrorResponse(401, "请重新登录")));
            return;
        }
        String uuid = claims.get("token", String.class);
        if (uuid == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonUtil.serialize(new GlobalExceptionHandler.ErrorResponse(401, "请重新登录")));
            return;
        }
        // 从Redis中获取用户信息
        String string = redisUtil.get(uuid);
        if (string == null) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonUtil.serialize(new GlobalExceptionHandler.ErrorResponse(401, "请重新登录")));
            return;
        }
        LoginUserVO user = new LoginUserVO();
        try {
            user.setUser(JsonUtil.deserialize(string, User.class));
            log.info("访问用户==================>{}", user);
        } catch (Exception e) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonUtil.serialize(new GlobalExceptionHandler.ErrorResponse(401, "请重新登录")));
            return;
        }
        // 将用户信息放到SecurityContext中
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 放行
        filterChain.doFilter(request, response);
    }
}
