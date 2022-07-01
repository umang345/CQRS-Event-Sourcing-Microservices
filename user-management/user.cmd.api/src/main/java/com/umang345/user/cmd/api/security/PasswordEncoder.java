package com.umang345.user.cmd.api.security;

public interface PasswordEncoder
{
    String hashPassword(String password);
}
