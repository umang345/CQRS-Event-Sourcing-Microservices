package com.umang345.bankacc.cmd.api.aggregates;

import com.umang345.bankacc.cmd.api.commands.CloseAccountCommand;
import com.umang345.bankacc.cmd.api.commands.DepositFundsCommand;
import com.umang345.bankacc.cmd.api.commands.OpenAccountCommand;
import com.umang345.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.umang345.bankacc.core.events.AccountClosedEvent;
import com.umang345.bankacc.core.events.AccountOpenedEvent;
import com.umang345.bankacc.core.events.FundsDepositedEvent;
import com.umang345.bankacc.core.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
@NoArgsConstructor
public class AccountAggregate
{
    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private double balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command)
    {
        var event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolderId(command.getAccountHolderId())
                .creationDate(new Date())
                .openingBalance(command.getOpeningBalance())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event)
    {
         this.id = event.getId();
         this.accountHolderId = event.getAccountHolderId();
         this.balance = event.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command)
    {
        var amount = command.getAmount();
        var event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance+amount)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event)
    {
         this.balance += event.getAmount();
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand command)
    {
         var amount = command.getAmount();

         if(this.balance - amount < 0)
         {
              throw new IllegalStateException("Withdrawn Declined , insufficient funds!!");
         }

          var event = FundsWithdrawnEvent.builder()
                  .id(command.getId())
                  .amount(amount)
                  .balance(this.balance - amount)
                  .build();

         AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event)
    {
         this.balance -= event.getAmount();
    }

    @CommandHandler
    public void handle(CloseAccountCommand command)
    {
         var event = AccountClosedEvent.builder()
                 .id(command.getId())
                 .build();

         AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event)
    {
         AggregateLifecycle.markDeleted();
    }

}




















