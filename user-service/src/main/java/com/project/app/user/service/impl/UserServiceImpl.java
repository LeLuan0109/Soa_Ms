package com.project.app.user.service.impl;

import com.project.app.core.exception.exceptionSub.RuntimeExceptionSls;
import com.project.app.core.util.LocalizationUtils;
import com.project.app.core.util.MessageKeys;
import com.project.app.user.domain.User;
import com.project.app.user.domain.UserOrg;
import com.project.app.user.dto.authUser.AuthUserDto;
import com.project.app.user.repository.UserOrgRepository;
import com.project.app.user.repository.UserRepository;
import com.project.app.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserOrgRepository userOrgRepository;
  private final ModelMapper modelMapper;
  private final LocalizationUtils localizationUtils;

  @Override
  public AuthUserDto getUserByUsername(String username) {
    return userRepository.getUserByUsername(username);
  }

  @Override
  public List<Integer> getOrgIdByUserId(Integer userId) {
    return userOrgRepository.getOrgIdByUserId(userId);
  }

  @Override
  public Integer updateUser(AuthUserDto userDto) {
    User user = userRepository.findById(userDto.getId())
        .orElseThrow(()-> new RuntimeExceptionSls(localizationUtils.getLocalizedMessage(MessageKeys.USER_NOT_EXIST)));
    modelMapper.map(userDto, user);
    User userSaved = userRepository.save(user);
    return userSaved.getId();
  }

  @Override
  public AuthUserDto getUserByEmail(String email) {
    return userRepository.getUserByEmail(email);

  }

  @Override
  public List<AuthUserDto> getAllUserAdmin() {
    return userRepository.getAllUserAdmin();
  }

  @Override
  public void UpdateUserOrg(List<Integer> userIds, List<Integer> orgIds) {
    userIds.forEach((userId)->
      orgIds.forEach((orgId)->{
        if (!userOrgRepository.existsByUserIdAndOrgId(userId, orgId)) {
          userOrgRepository.save(new UserOrg(userId, orgId));
        }
      })
    );
  }
}
