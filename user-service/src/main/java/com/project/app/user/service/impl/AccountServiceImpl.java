package com.project.app.user.service.impl;

import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import com.project.app.core.helper.DateTimeHelper;
import com.project.app.core.util.LocalizationUtils;
import com.project.app.core.util.MessageKeys;
import com.project.app.user.client.OrgServiceClient;
import com.project.app.user.domain.Role;
import com.project.app.user.domain.RoleUser;
import com.project.app.user.domain.User;
import com.project.app.user.domain.UserOrg;
import com.project.app.user.dto.OrgDto;
import com.project.app.user.dto.account.AccountDto;
import com.project.app.user.repository.RoleRepository;
import com.project.app.user.repository.RoleUserRepository;
import com.project.app.user.repository.UserOrgRepository;
import com.project.app.user.repository.UserRepository;
import com.project.app.user.request.account.AccountFilterRequest;
import com.project.app.user.request.account.AccountInfoRequest;
import com.project.app.user.request.account.AccountStatusRequest;
import com.project.app.user.service.AccountService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final RoleUserRepository roleUserRepository;
  private final UserOrgRepository userOrgRepository;
  private final PasswordEncoder passwordEncoder;
  private final LocalizationUtils localizationUtils;
  private final OrgServiceClient orgServiceClient;

  @Override
  public Page<AccountDto> filterAccounts(FilterPaging<AccountFilterRequest> filterAccount) {
    AccountFilterRequest filter = filterAccount.getFilter();
    String username = Optional.ofNullable(filter.getUsername()).filter(s -> !s.isEmpty()).orElse(null);
    String fullName = Optional.ofNullable(filter.getFullName()).filter(s -> !s.isEmpty()).orElse(null);
    Boolean statusFilter = (filter.getStatus() != null) ? (filter.getStatus() == 1) : null;

    return userRepository.filterAccount(
        username,
        fullName,
        DateTimeHelper.toLocalDate(filter.getCreated()),
        statusFilter,
        filter.getUserId(),
        PageRequest.of(filterAccount.getPageIndex(), filterAccount.getPageSize(),
            Sort.sort(User.class).by(User::getCreateTime).descending())
    );
  }

  @Override
  public RestHttpPostResponse createUser(AccountInfoRequest createAccount)
      throws DataNotFoundException {
    if (userRepository.existsByUsername(createAccount.getUsername())) {
      throw new DataIntegrityViolationException(localizationUtils.getLocalizedMessage(MessageKeys.EMAIL_IS_EXIST));
    }

    List<OrgDto> organizations = orgServiceClient.getOrgByUserOrgIds(createAccount.getOrganization()).getBody();

    User newUser = User.builder()
        .username(createAccount.getUsername())
        .fullname(createAccount.getFullName())
        .phone(createAccount.getPhone())
        .address(createAccount.getAddress())
        .email(createAccount.getEmail())
        .userVersion(1)
        .isActive(true)
        .password(passwordEncoder.encode(createAccount.getPassword()))
        .build();

    User userSaved = userRepository.save(newUser);
    assignOrganizations(newUser.getId(), organizations);
    roleUserRepository.save(new RoleUser(newUser.getId(), createAccount.getRole()));

    return new RestHttpPostResponse(userSaved.getId().longValue());
  }

  @Override
  public RestHttpPostResponse updateUser(Integer id, AccountInfoRequest updateAccount)
      throws DataNotFoundException {
    User existingUser = getUserById(id);

    List<OrgDto> organizations = orgServiceClient.getOrgByUserOrgIds(updateAccount.getOrganization()).getBody();
    userOrgRepository.deleteByAuthorId(existingUser.getId());

    existingUser.setFullname(updateAccount.getFullName());
    existingUser.setUsername(updateAccount.getUsername());
    existingUser.setPhone(updateAccount.getPhone());
    existingUser.setEmail(updateAccount.getEmail());
    existingUser.setAddress(updateAccount.getAddress());
    User userSaved = userRepository.save(existingUser);

    assignOrganizations(existingUser.getId(), organizations);
    updateUserRole(existingUser.getId(), updateAccount.getRole());

    return new RestHttpPostResponse(userSaved.getId().longValue());
  }

  @Override
  public RestHttpPostResponse deletedUser(Integer id) throws DataNotFoundException {
    User existingUser = getUserById(id);
    userOrgRepository.deleteByAuthorId(id);
    userRepository.delete(existingUser);
    return new RestHttpPostResponse(id.longValue());
  }

  @Override
  public RestHttpPostResponse updateStatus(Integer id, AccountStatusRequest updateStatus)
      throws DataNotFoundException {
    User existingUser = getUserById(id);
    existingUser.setIsActive(updateStatus.getStatus() == 1);
    userRepository.save(existingUser);
    return new RestHttpPostResponse(id.longValue());
  }

  @Override
  public AccountDto getUserDetail(Integer id) throws DataNotFoundException {
    User existingUser = getUserById(id);
    List<Integer> organizationIds = userOrgRepository.getOrgIdByUserId(existingUser.getId());
    List<OrgDto> organizations = orgServiceClient.getOrgByUserOrgIds(organizationIds).getBody();

    RoleUser roleUser = roleUserRepository.findRoleUserByUserId(existingUser.getId()).orElse(null);
    Role role = (roleUser != null) ? roleRepository.findById(roleUser.getRoleId()).orElse(null) : null;

    AccountDto response = AccountDto.fromUser(existingUser);
    response.setOrganizations(organizations);
    response.setRoles(role != null ? role.getRoles() : null);
    response.setRoleName(role != null ? role.getName() : null);

    return response;
  }

  @Override
  public RestHttpPostResponse setPasswordUser(Integer id, AccountInfoRequest updateAccount)
      throws DataNotFoundException {
    User existingUser = getUserById(id);

    if (!updateAccount.getPassword().isEmpty()) {
      if (!updateAccount.getPassword().equals(updateAccount.getPasswordConfirmation())) {
        throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PASSWORDS_DO_NOT_MATCH));
      }
      existingUser.setPassword(passwordEncoder.encode(updateAccount.getPassword()));
      userRepository.save(existingUser);
    }

    return new RestHttpPostResponse(id.longValue());
  }

  protected User getUserById(Integer id) throws DataNotFoundException {
    return userRepository.findById(id)
        .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.USER_INVALID)));
  }

  protected void assignOrganizations(Integer userId, List<OrgDto> organizations) {
    for (OrgDto org : organizations) {
      userOrgRepository.save(new UserOrg(userId, org.getId()));
    }
  }

  protected void updateUserRole(Integer userId, Integer roleId) {
    RoleUser roleUser = roleUserRepository.findRoleUserByUserId(userId)
        .orElse(null);

    if (roleUser == null) {
      roleUser = new RoleUser();
      roleUser.setUserId(userId);
      roleUser.setRoleId(roleId);
      roleUserRepository.save(roleUser);
    } else if (!roleUser.getRoleId().equals(roleId)) {
      roleUser.setRoleId(roleId);
      roleUserRepository.save(roleUser);
    }
  }
}
