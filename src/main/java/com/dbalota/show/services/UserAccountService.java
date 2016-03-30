package com.dbalota.show.services;

import com.dbalota.show.models.UserAccount;

import java.util.List;

/**
 * Created by Dmytro_Balota on 3/30/2016.
 */
public interface UserAccountService {

    void createAccount(long userId);

    double checkBalance(long account);

    void withdrawAccount(long account, double amount);

    void refill(long account, double amount);

    List<UserAccount> getUserAccounts(long userId);

    void deleteAccount(long accountNumber);
}
