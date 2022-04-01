package com.example.restbootc51.interceptor;

import com.example.restbootc51.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
    public static final String HEADER_X_API_TOKEN = "X-API-Token";

    private final UserRepository userRepository;

    public Interceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (userRepository.findByToken(request.getHeader(HEADER_X_API_TOKEN)).isPresent()) {
            return true;
        }

        response.setStatus(401);
        return false;
    }
}
