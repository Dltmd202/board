package com.nts.seonghwan.be.config.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleSessionManager {
    private final Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Object value) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);
        return sessionId;
    }

    public Object getSession(String sessionId) {
        return sessionStore.get(sessionId);
    }

    public Object getSession(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String accessToken = authorizationHeader.substring(7);
            return getSession(accessToken);
        }
        return null;
    }
}
