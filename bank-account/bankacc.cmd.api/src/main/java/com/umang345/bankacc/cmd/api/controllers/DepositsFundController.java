package com.umang345.bankacc.cmd.api.controllers;

import com.umang345.bankacc.cmd.api.commands.DepositFundsCommand;
import com.umang345.bankacc.core.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/depositFunds")
public class DepositsFundController
{
    private final CommandGateway commandGateway;

    @Autowired
    public DepositsFundController(CommandGateway commandGateway)
    {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id,
                                                     @Valid @RequestBody DepositFundsCommand command)
    {
         try {
             command.setId(command.getId());
             commandGateway.send(command);

             return new ResponseEntity<>(new BaseResponse("Funds successfully deposited"),HttpStatus.OK);
         }
         catch (Exception e)
         {
             var safeErrorMessage = "Error while processing request to deposit funds into bank account for id - "+id;
             System.out.println(e.toString());

             return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

}



















