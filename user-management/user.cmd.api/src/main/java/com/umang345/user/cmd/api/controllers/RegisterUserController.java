package com.umang345.user.cmd.api.controllers;

import com.umang345.user.cmd.api.commands.RegisterUserCommand;
import com.umang345.user.cmd.api.dto.RegisterUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
public class RegisterUserController
{
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway)
    {
         this.commandGateway = commandGateway;
    }

    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand command)
    {
        command.setId(UUID.randomUUID().toString());

        try {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RegisterUserResponse("User Successfully Registered"), HttpStatus.CREATED);
        }
        catch (Exception e){
            var safeErrorMessage = "Error while processing register user request for id - "+command.getId();
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}






















