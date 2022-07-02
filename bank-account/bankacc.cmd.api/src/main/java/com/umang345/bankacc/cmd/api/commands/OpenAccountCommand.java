package com.umang345.bankacc.cmd.api.commands;

import com.umang345.bankacc.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class OpenAccountCommand
{
    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "No Account holder Id was supplied!!")
    private String accountHolderId;

    @NotNull(message = "No Account Type was supplied!!")
    private AccountType accountType;

    @Min(value = 50, message = "Opening Balance must be atleast 50")
    private double openingBalance;
}
