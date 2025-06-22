 package com.project.app.user.controller;

 import com.project.app.core.common.request.FilterPaging;
 import com.project.app.core.common.request.IdInput;
 import com.project.app.core.common.request.InfoInputUpdate;
 import com.project.app.core.common.response.DataResponse;
 import com.project.app.core.common.response.PageInfoResponse;
 import com.project.app.core.common.response.PagingResponse;
 import com.project.app.core.common.response.RestHttpPostResponse;
 import com.project.app.core.exception.exceptionSub.DataNotFoundException;
 import com.project.app.user.dto.account.AccountDto;
 import com.project.app.user.request.account.AccountFilterRequest;
 import com.project.app.user.request.account.AccountInfoRequest;
 import com.project.app.user.request.account.AccountStatusRequest;
 import com.project.app.user.service.AccountService;
 import jakarta.validation.Valid;
 import java.util.List;
 import lombok.RequiredArgsConstructor;
 import org.springframework.data.domain.Page;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.access.prepost.PreAuthorize;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 @RestController
 @RequestMapping("${api.prefix}/account")
 @RequiredArgsConstructor
 public class AccountController {

   private final AccountService accountService;

   @PreAuthorize("hasAnyAuthority('view_account')")
   @PostMapping("/filter-accounts")
   public ResponseEntity<DataResponse<PagingResponse<PageInfoResponse, List<AccountDto>>>> filterAccounts(
       @RequestBody FilterPaging<AccountFilterRequest> filterAccount){
     Page<AccountDto> data = this.accountService.filterAccounts(filterAccount);
     PagingResponse result = new PagingResponse(
         data.getTotalElements(),
         new PageInfoResponse(
             data.getPageable().getPageNumber(),
             data.getNumberOfElements(),
             data.getPageable().getPageNumber(),
             data.hasNext(),
             data.getTotalPages()
         ),
         data.getContent()
     );
     return ResponseEntity.ok(
         new DataResponse<>(result));
   }

   @PreAuthorize("hasAnyAuthority('add_account')")
   @PostMapping("/create-account")
   public ResponseEntity<RestHttpPostResponse> createUser(
       @Valid @RequestBody AccountInfoRequest createAccount
   ) throws DataNotFoundException {

     return ResponseEntity.ok(this.accountService.createUser(createAccount));
   }

   @PreAuthorize("hasAnyAuthority('update_account')")
   @PostMapping("/update-account")
   public ResponseEntity<RestHttpPostResponse> updateAccount(
       @Valid @RequestBody InfoInputUpdate<Integer,AccountInfoRequest> updateAccount
   ) throws DataNotFoundException {
       return ResponseEntity.ok(this.accountService.updateUser(updateAccount.getId() , updateAccount.getInput()));
   }

   @PreAuthorize("hasAnyAuthority('delete_account')")
   @PostMapping("/delete-account")
   public ResponseEntity<DataResponse<RestHttpPostResponse>> deleteAccount(@RequestBody IdInput<Integer> accountId) throws DataNotFoundException {
     return ResponseEntity.ok(
         new DataResponse<>(
             this.accountService.deletedUser(accountId.getId())
         )
     );
   }

   @PreAuthorize("hasAnyAuthority('update_account')")
   @PostMapping("/update-status")
   public ResponseEntity<DataResponse<RestHttpPostResponse>> updateStatus(@RequestBody InfoInputUpdate<Integer , AccountStatusRequest> updateAccount) throws DataNotFoundException {
     return ResponseEntity.ok(
         new DataResponse<>(
             this.accountService.updateStatus(updateAccount.getId(), updateAccount.getInput())
         )
     );
   }

   @PreAuthorize("hasAnyAuthority('view_account')")
   @PostMapping("/get-account-detail")
   public ResponseEntity<DataResponse<AccountDto>> getAccountDetail(@RequestBody IdInput<Integer> accountId)
       throws DataNotFoundException {
     return ResponseEntity.ok(
         new DataResponse<>(
             this.accountService.getUserDetail(accountId.getId())
         )
     );
   }

   @PreAuthorize("hasAnyAuthority('update_account')")
   @PostMapping("/set-password-account")
   public ResponseEntity<DataResponse<RestHttpPostResponse>> setPasswordAccount(
       @Valid @RequestBody InfoInputUpdate<Integer ,AccountInfoRequest> updateAccount
   ) throws DataNotFoundException {
     return ResponseEntity.ok(
         new DataResponse<>(
             this.accountService.setPasswordUser(updateAccount.getId() , updateAccount.getInput())
         )
     );
   }

 }
