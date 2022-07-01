package com.umang345.user.cmd.api.dto;

import com.umang345.user.core.dto.BaseResponse;

public class RegisterUserResponse extends BaseResponse
{
    private String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
