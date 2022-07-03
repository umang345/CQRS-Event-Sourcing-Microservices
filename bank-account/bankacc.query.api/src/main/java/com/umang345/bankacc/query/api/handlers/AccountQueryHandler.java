package com.umang345.bankacc.query.api.handlers;

import com.umang345.bankacc.query.api.dto.AccountLookupResponse;
import com.umang345.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.umang345.bankacc.query.api.queries.FindAccountsByIdQuery;
import com.umang345.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.umang345.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler
{
    AccountLookupResponse findAccountById(FindAccountsByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupResponse findAccountsWithBalance(FindAccountsWithBalanceQuery query);
}
