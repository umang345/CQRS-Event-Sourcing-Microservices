package com.umang345.bankacc.query.api.handlers;

import com.umang345.bankacc.core.events.AccountClosedEvent;
import com.umang345.bankacc.core.events.AccountOpenedEvent;
import com.umang345.bankacc.core.events.FundsDepositedEvent;
import com.umang345.bankacc.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler
{
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);

}
