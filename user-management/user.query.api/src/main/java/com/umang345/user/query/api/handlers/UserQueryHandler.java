package com.umang345.user.query.api.handlers;

import com.umang345.user.query.api.dto.UserLookupResponse;
import com.umang345.user.query.api.queries.FindAllUsersQuery;
import com.umang345.user.query.api.queries.FindUserByIdQuery;
import com.umang345.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler
{
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
