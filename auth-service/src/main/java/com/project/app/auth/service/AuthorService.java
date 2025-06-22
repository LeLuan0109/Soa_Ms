package com.project.app.auth.service;

import com.project.app.auth.dto.LoginDto;
import com.project.app.auth.dto.MeDto;
import com.project.app.auth.request.auth.ChangePasswordInput;
import com.project.app.auth.request.auth.UpdateMeInput;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.UnsupportedEncodingException;

public interface AuthorService {

    LoginDto login(String phoneNumber, String password, HttpServletRequest request) throws Exception;

    MeDto getMe();
//
    RestHttpPostResponse changePassword(@Valid ChangePasswordInput changePasswordInput) throws DataNotFoundException;
    RestHttpPostResponse updateMe(@Valid UpdateMeInput updateMeInput) throws DataNotFoundException;
    RestHttpPostResponse sendMailForgotPassword(String email) throws Exception;
    RestHttpPostResponse checkTokenForgotPassword(String token) throws DataNotFoundException, UnsupportedEncodingException;
    RestHttpPostResponse setForgotPassword(String token , String passwordNew , String confirmPassword) throws DataNotFoundException;

}
