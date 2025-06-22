package com.project.app.core.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {
    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static Integer getOrgId() {
        HttpServletRequest request = getCurrentRequest();
        String orgIdHeader = request.getHeader("OrgId");

        if (orgIdHeader == null || orgIdHeader.isEmpty()) {
            throw new IllegalArgumentException("OrgId header is missing or empty");
        }

        try {
            return Integer.parseInt(orgIdHeader);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid OrgId format: " + orgIdHeader, e);
        }
    }
}
