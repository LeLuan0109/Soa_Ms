package com.project.app.auth.controller;


import com.project.app.auth.dto.LoginDto;
import com.project.app.auth.dto.MeDto;
import com.project.app.auth.request.auth.ChangePasswordInput;
import com.project.app.auth.request.auth.ForgotPasswordInput;
import com.project.app.auth.request.auth.LoginRequest;
import com.project.app.auth.request.auth.SetForgotPasswordInput;
import com.project.app.auth.request.auth.TokenForgotPasswordInput;
import com.project.app.auth.request.auth.UpdateMeInput;
import com.project.app.auth.service.AuthorService;
import com.project.app.core.common.response.DataResponse;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/author")
@RequiredArgsConstructor
public class AuthorController {

  private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
  private final AuthorService authorService;

  @PostMapping("/login")
  public ResponseEntity<DataResponse<LoginDto>> login(
      @Valid @RequestBody LoginRequest loginDTO, HttpServletRequest request) throws Exception {
    return ResponseEntity.ok(
        DataResponse.<LoginDto>builder()
            .data(authorService.login(
                loginDTO.getUsername(),
                loginDTO.getPassword(),
                request
            ))
            .build()
    );
  }

  @GetMapping("/get-author")
  public ResponseEntity<DataResponse<MeDto>> getMe(){
    return ResponseEntity.ok(
        DataResponse.<MeDto>builder()
            .data(authorService.getMe())
            .build()
    );
  }

  @PostMapping("/change-password")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> changePassword(@Valid @RequestBody ChangePasswordInput changePasswordInput)
      throws DataNotFoundException {
    return ResponseEntity.ok(
        DataResponse.<RestHttpPostResponse>builder()
            .data(authorService.changePassword(changePasswordInput))
            .build()
    );
  }

  @PostMapping("/update-me")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> updateMe(@Valid @RequestBody UpdateMeInput updateMeInput) throws DataNotFoundException {
    return ResponseEntity.ok(
        DataResponse.<RestHttpPostResponse>builder()
            .data(authorService.updateMe(updateMeInput))
            .build()
    );
  }

  @PostMapping("/send-mail-forgot-password")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> sendMailForgotPassword(@Valid @RequestBody ForgotPasswordInput forgotPasswordInput) throws Exception {
    return ResponseEntity.ok(
        DataResponse.<RestHttpPostResponse>builder()
            .data(authorService.sendMailForgotPassword(forgotPasswordInput.getEmail()))
            .build()
    );
  }

  @PostMapping("/check-token-forgot-password")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> checkTokenForgotPassword( @RequestBody TokenForgotPasswordInput tokenForgotPasswordInput)
      throws DataNotFoundException, UnsupportedEncodingException, UnsupportedEncodingException {
    return ResponseEntity.ok(
        DataResponse.<RestHttpPostResponse>builder()
            .data(authorService.checkTokenForgotPassword(tokenForgotPasswordInput.getToken()))
            .build()
    );
  }

  @PostMapping("/set-forgot-password")
  public ResponseEntity<DataResponse<RestHttpPostResponse>> setForgotPassword(@Valid @RequestBody SetForgotPasswordInput setForgotPasswordInput) throws DataNotFoundException {
    return ResponseEntity.ok(
        DataResponse.<RestHttpPostResponse>builder()
            .data(authorService.setForgotPassword(setForgotPasswordInput.getToken() , setForgotPasswordInput.getPasswordNew() , setForgotPasswordInput.getConfirmPassword()))
            .build()
    );
  }
}
