package com.umang345.bankacc.core.events;

import com.umang345.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccountOpenedEvent
{
    private String id;
    private String accountHolderId;
    private AccountType accountType;
    private Date creationDate;
    private double openingBalance;
}
