package com.umang345.user.core.events;

import com.umang345.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent
{
    private String id;
    private User user;
}
