package com.umang345.user.query.api.handlers;

import com.umang345.user.core.events.UserRegisteredEvent;
import com.umang345.user.core.events.UserRemovedEvent;
import com.umang345.user.core.events.UserUpdatedEvent;

public interface UserEventHandler
{
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
