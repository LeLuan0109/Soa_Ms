package com.project.app.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
@Slf4j
public class SecurityUtils {
  private SecurityUtils() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * Lấy đối tượng Authentication hiện tại.
   */
  public static Authentication getCurrentAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  /**
   * Lấy thông tin Principal (thông tin người dùng).
   */
  public static Object getPrincipal() {
    Authentication authentication = getCurrentAuthentication();
    if (authentication != null) {
      return authentication.getPrincipal();
    }
    return null;
  }

  /**
   * Lấy username từ SecurityContext. Hỗ trợ principal là String hoặc UserDetails.
   */
  public static String getUsername() {
    Object principal = getPrincipal();

    log.info("Security Context Authentication: {}", getCurrentAuthentication());
    log.info("Principal: {}", principal);

    if (principal == null) {
      log.warn("Principal is null!");
      return null;
    }

    if (principal instanceof String) {
      log.info("Principal is a String: {}", principal);
      return (String) principal;
    }

    if (principal instanceof org.springframework.security.core.userdetails.UserDetails) {
      String username = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
      log.info("Principal is a UserDetails: {}", username);
      return username;
    }

    log.warn("Principal is of unknown type: {}", principal.getClass().getName());
    return null;
  }


}
