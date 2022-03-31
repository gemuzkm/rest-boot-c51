package com.example.restbootc51.interceptor;

import com.example.restbootc51.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = request.getHeader("X-API-Token");
        if (userRepository.findByToken(userToken).isPresent()) {
            return true;
        } else if (request.getRequestURI().equals("/user")) {
            return true;
        }

        response.setStatus(401);

        return false;
    }
}
