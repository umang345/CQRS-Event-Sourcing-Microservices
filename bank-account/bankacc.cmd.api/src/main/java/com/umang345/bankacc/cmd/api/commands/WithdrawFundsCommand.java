package com.umang345.bankacc.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class WithdrawFundsCommand
{
    @TargetAggregateIdentifier
    private String id;
    private double amount;
}
