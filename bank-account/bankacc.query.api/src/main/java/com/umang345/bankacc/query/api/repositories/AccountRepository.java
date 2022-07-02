package com.umang345.bankacc.query.api.repositories;

import com.umang345.bankacc.core.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount,String> {
}
