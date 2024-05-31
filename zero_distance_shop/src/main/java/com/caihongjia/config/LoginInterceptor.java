package com.caihongjia.config;

import com.caihongjia.common.constant.JwtSigningKey;
import com.caihongjia.common.exception.user.LoginException;
import com.caihongjia.common.util.JwtUtil;
import com.caihongjia.common.util.ThreadLocalUtil;
import com.caihongjia.pojo.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // OPTIONS请求放行
        if("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 从请求头获取前端传来的token
        String token = request.getHeader("Authorization");
        if(token == null || "".equals(token)){
            return false;
        }
        System.out.println("前端的token值:" + token);
        // 解析token,正确放行,错误报错
        Object data = null;
        try{
            Claims claims = JwtUtil.parseJwt(JwtSigningKey.userKey, token);
            data = claims.get("data");
        }catch(Exception e){
            System.out.println("jwt令牌异常");
            throw new LoginException("未登录，请先登录！");
        }
        System.out.println("data:" + data);
        ThreadLocalUtil.set(data);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
