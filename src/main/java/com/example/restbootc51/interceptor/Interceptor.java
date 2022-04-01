package com.example.restbootc51.interceptor;

import com.example.restbootc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
    public static final String HEADER_X_API_TOKEN = "X-API-Token";
    public static final String PATH_SWAGGER = "/swagger-ui";
    public static final String PATH_USER_REG = "/user";

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals(PATH_SWAGGER) | userRepository.findByToken(request.getHeader(HEADER_X_API_TOKEN)).isPresent()) {
            return true;
        } else if (request.getRequestURI().equals(PATH_USER_REG)) {
            return true;
        }

        response.setStatus(401);
        return false;
    }
}
