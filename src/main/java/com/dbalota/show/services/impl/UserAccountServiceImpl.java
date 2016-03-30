package com.dbalota.show.services.impl;

import com.dbalota.show.dao.UserAccountDao;
import com.dbalota.show.models.UserAccount;
import com.dbalota.show.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dmytro_Balota on 3/30/2016.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public void createAccount(long userId) {
        userAccountDao.createAccount(userId);
    }

    @Override
    public double checkBalance(long account) {
        return userAccountDao.getBalance(account);
    }

    @Override
    public void withdrawAccount(long account, double amount) {
        double balance = userAccountDao.getBalance(account);
        userAccountDao.setBalance(account, balance - amount);
    }

    @Override
    public void refill(long account, double amount) {
        double balance = userAccountDao.getBalance(account);
        userAccountDao.setBalance(account, balance + amount);
    }

    @Override
    public List<UserAccount> getUserAccounts(long userId) {
        return userAccountDao.getAccounts(userId);
    }

    @Override
    public void deleteAccount(long accountNumber) {
        userAccountDao.deleteAccount(accountNumber);
    }
}
