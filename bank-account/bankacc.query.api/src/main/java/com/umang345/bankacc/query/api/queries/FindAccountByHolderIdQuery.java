package com.umang345.bankacc.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByHolderIdQuery
{
    private String accountHolderId;
}
