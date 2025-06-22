package com.project.app.auth.service.Impl;

import com.project.app.auth.client.OrgServiceClient;
import com.project.app.auth.client.UserServiceClient;
import com.project.app.auth.component.JwtTokenForgotPasswordUtils;
import com.project.app.auth.component.JwtTokenUtils;
import com.project.app.auth.configuration.CustomUserDetails;
import com.project.app.auth.dto.AuthDto;
import com.project.app.auth.dto.LoginDto;
import com.project.app.auth.dto.MeDto;
import com.project.app.auth.dto.OrgDto;
import com.project.app.auth.request.auth.ChangePasswordInput;
import com.project.app.auth.request.auth.UpdateMeInput;
import com.project.app.auth.service.AuthorService;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import com.project.app.core.exception.exceptionSub.PermissionDenyException;
import com.project.app.core.exception.exceptionSub.RuntimeExceptionSls;
import com.project.app.core.util.LocalizationUtils;
import com.project.app.core.util.MessageKeys;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final JwtTokenUtils jwtTokenUtil;
    private final JwtTokenForgotPasswordUtils jwtTokenForgotPasswordUtils;
    private final AuthenticationManager authenticationManager;
    private final LocalizationUtils localizationUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceClient userServiceClient;
    private final OrgServiceClient orgServiceClient;
    private final UserDetailsService userDetailsService;
//    private final NotificationServiceClient notificationServiceClient;
//    @Qualifier("redisTemplateForgotPassword")
//    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public LoginDto login(String username, String password, HttpServletRequest request) throws Exception {
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        if (userDetails == null || !userDetails.isEnabled()) {
            throw new PermissionDenyException(localizationUtils.getLocalizedMessage(MessageKeys.USER_DEACTIVE));
        }

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), password, userDetails.getAuthorities())
            );
        } catch (BadCredentialsException ex) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.USER_INVALID));
        }

        return new LoginDto(jwtTokenUtil.generateToken(userDetails));
    }

    @Override
    public MeDto getMe() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MeDto auth = MeDto.fromUser(userServiceClient.getUserByUsername(username).getBody());

        List<Integer> orgIds = userServiceClient.getOrgIdByUserId(auth.getId()).getBody();
        List<OrgDto> organizations = orgServiceClient.getOrgByUserOrgIds(orgIds).getBody();
        auth.setOrganizations(organizations);

        return auth;
    }

    @Override
    public RestHttpPostResponse changePassword(ChangePasswordInput input)
        throws DataNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AuthDto user = Optional.ofNullable(userServiceClient.getUserByUsername(username).getBody())
            .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.USER_NOT_EXIST)));

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new RuntimeExceptionSls(localizationUtils.getLocalizedMessage(MessageKeys.PASSWORD_INCORRECT));
        }
        if (!input.getPasswordNew().equals(input.getConfirmPassword())) {
            throw new RuntimeExceptionSls(localizationUtils.getLocalizedMessage(MessageKeys.PASSWORDS_DO_NOT_MATCH));
        }

        user.setPassword(passwordEncoder.encode(input.getPasswordNew()));

        return new RestHttpPostResponse(userServiceClient.updateUser(user).getBody().longValue());
    }

    @Override
    public RestHttpPostResponse updateMe(UpdateMeInput input) throws DataNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AuthDto user = Optional.ofNullable(userServiceClient.getUserByUsername(username).getBody())
            .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.USER_NOT_EXIST)));

        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        user.setFullname(input.getFullName());
        user.setUsername(input.getEmail());

        return new RestHttpPostResponse(userServiceClient.updateUser(user).getBody().longValue());
    }

    @Override
    public RestHttpPostResponse sendMailForgotPassword(String email) throws Exception {
        String baseUrl =  "http://localhost:4200"  + "/set-forgot-password";

        AuthDto existingUser = Optional.ofNullable(userServiceClient.getUserByEmail(email).getBody())
            .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMAIL_NOT_EXIST)));
        String tokenForgotPassword = jwtTokenForgotPasswordUtils.generateToken(existingUser);
        String resetPasswordUrl = baseUrl + "?" + URLEncoder.encode(tokenForgotPassword, StandardCharsets.UTF_8.toString());

//        notificationServiceClient.sendEmail(
//            EmailNotificationEvent.builder()
//                .source("ntf-forgot-password")
//                .template("reset-password")
//                .recipient(email)
//                .emailContent(
//                    EmailContentDto.builder()
//                        .url(resetPasswordUrl)
//                        .build()
//                ).build()
//        );
//        String token = jwtTokenForgotPasswordUtils.generateToken(existingUser);
//        redisTemplate.opsForValue().set(existingUser.getEmail(), token, 30, TimeUnit.MINUTES);

        return new RestHttpPostResponse(existingUser.getId().longValue());
    }

    @Override
    public RestHttpPostResponse checkTokenForgotPassword(String token)
        throws DataNotFoundException {
        String email = jwtTokenForgotPasswordUtils.extractEmail(URLDecoder.decode(token, StandardCharsets.UTF_8));

        AuthDto user = Optional.ofNullable(userServiceClient.getUserByEmail(email).getBody())
            .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMAIL_NOT_EXIST)));

//        if (!jwtTokenForgotPasswordUtils.validateToken(token, user) || !checkTokenInRedis(email, token)) {
//            throw new RuntimeExceptionSls(localizationUtils.getLocalizedMessage(MessageKeys.UNAUTHORIZED));
//        }
        return new RestHttpPostResponse();
    }

    @Override
    public RestHttpPostResponse setForgotPassword(String token, String passwordNew, String confirmPassword)
        throws DataNotFoundException {
        String email = jwtTokenForgotPasswordUtils.extractEmail(token);

        AuthDto user = Optional.ofNullable(userServiceClient.getUserByEmail(email).getBody())
            .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMAIL_NOT_EXIST)));

        if (!jwtTokenForgotPasswordUtils.validateToken(token, user)) {
            throw new RuntimeExceptionSls(localizationUtils.getLocalizedMessage(MessageKeys.UNAUTHORIZED));
        }

        user.setPassword(passwordEncoder.encode(passwordNew));
        user.setUserVersion(user.getUserVersion() + 1);

//        if (redisTemplate.opsForValue().get(email) == null) {
//            throw new RuntimeException(localizationUtils.getLocalizedMessage(MessageKeys.USER_NOT_EXIST));
//        }
//
//        redisTemplate.delete(email);
        return new RestHttpPostResponse(userServiceClient.updateUser(user).getBody().longValue());
    }

//    private boolean checkTokenInRedis(String email, String token) {
//        String storedToken = (String) redisTemplate.opsForValue().get(email);
//        return storedToken != null && getPayload(token).equals(getPayload(storedToken));
//    }
//
//    private String getPayload(String token) {
//        String[] parts = token.split("\\.");
//        if (parts.length < 2) throw new IllegalArgumentException(localizationUtils.getLocalizedMessage(MessageKeys.USER_INVALID));
//        return parts[1];
//    }
}
