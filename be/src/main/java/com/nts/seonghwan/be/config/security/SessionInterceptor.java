package com.nts.seonghwan.be.config.security;

import com.nts.seonghwan.be.common.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.nts.seonghwan.be.common.error.exception.ErrorCode.NO_AUTHENTICATION;

@RequiredArgsConstructor
public class SessionInterceptor implements HandlerInterceptor {
    private final SimpleSessionManager sessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals(HttpMethod.OPTIONS.toString())) return true;

        Long authenticatedUserId = (Long) sessionManager.getSession(request);

        if(Objects.isNull(authenticatedUserId)) {
            response.setStatus(NO_AUTHENTICATION.getStatus().value());
            response.setHeader("content-type", "application/json; charset=utf-8");
            response.getWriter()
                    .write(ApiUtils.error(NO_AUTHENTICATION.getMessage(), NO_AUTHENTICATION.getStatus()).toString());
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
