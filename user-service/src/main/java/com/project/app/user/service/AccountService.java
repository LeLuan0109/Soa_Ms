package com.project.app.user.service;

import com.project.app.core.common.request.FilterPaging;
import com.project.app.core.common.response.RestHttpPostResponse;
import com.project.app.core.exception.exceptionSub.DataNotFoundException;
import com.project.app.user.dto.account.AccountDto;
import com.project.app.user.request.account.AccountFilterRequest;
import com.project.app.user.request.account.AccountInfoRequest;
import com.project.app.user.request.account.AccountStatusRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
		Page<AccountDto> filterAccounts(@Valid FilterPaging<AccountFilterRequest> filterAccount);
		RestHttpPostResponse createUser(@Valid AccountInfoRequest createAccount) throws DataNotFoundException;
		RestHttpPostResponse updateUser(@Valid Integer id,AccountInfoRequest updateAccount) throws DataNotFoundException;
		RestHttpPostResponse deletedUser(@Valid Integer id) throws DataNotFoundException;
		RestHttpPostResponse updateStatus(@Valid Integer id, AccountStatusRequest updateStatus) throws DataNotFoundException;
		AccountDto getUserDetail(@Valid Integer id) throws DataNotFoundException;
		RestHttpPostResponse setPasswordUser(@Valid Integer id,AccountInfoRequest updateAccount) throws DataNotFoundException;

}
