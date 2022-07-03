package com.umang345.bankacc.query.api.controllers;

import com.umang345.bankacc.query.api.dto.AccountLookupResponse;
import com.umang345.bankacc.query.api.dto.EqualityType;
import com.umang345.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.umang345.bankacc.query.api.queries.FindAccountsByIdQuery;
import com.umang345.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.umang345.bankacc.query.api.queries.FindAllAccountsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/bankAccountLookup")
public class AccountLookupController
{
    private final QueryGateway queryGateway;

    @Autowired
    public AccountLookupController(QueryGateway queryGateway)
    {
         this.queryGateway = queryGateway;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts()
    {
         try{
            var query = new FindAllAccountsQuery();

            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response==null || response.getAccounts() == null)
            {
                 return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response,HttpStatus.OK);
         }
         catch (Exception e)
         {
             var safeErrorMessage = "Failed to complete get all accounts request";
             System.out.println(e.toString());

             return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping("/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id)
    {
        try{
            var query = new FindAccountsByIdQuery(id);

            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response==null || response.getAccounts() == null)
            {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            var safeErrorMessage = "Failed to complete get account by Id request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byHolderId/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable(value = "accountHolderId") String accountHolderId)
    {
        try{
            var query = new FindAccountByHolderIdQuery(accountHolderId);

            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response==null || response.getAccounts() == null)
            {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            var safeErrorMessage = "Failed to complete get account by Holder Id request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/withBalance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable(value = "equalityType") EqualityType equalityType
                                                                    ,@PathVariable(value = "balance") double balance)
    {
        try{
            var query = new FindAccountsWithBalanceQuery(equalityType,balance);

            var response = queryGateway.query(query, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if(response==null || response.getAccounts() == null)
            {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            var safeErrorMessage = "Failed to complete get accounts with balance request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}























