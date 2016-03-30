package com.dbalota.show.dao;

import com.dbalota.show.dao.mapper.UserAccountRowMapper;
import com.dbalota.show.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dmytro_Balota on 3/30/2016.
 */
@Repository
public class UserAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createAccount(long userId) {
        jdbcTemplate.update("insert into user_accounts(userId) values(?)", userId);
    }

    public double getBalance(long account) {
        return jdbcTemplate.queryForObject("select balance from user_accounts where accountNumber = ?", new Object[]{account},
                Double.class);
    }

    public void setBalance(long account, double amount) {
        jdbcTemplate.update("update user_accounts set balance = ? where accountNumber = ?", amount, account);
    }

    public List<UserAccount> getAccounts(long userId) {
        return jdbcTemplate.query("select * from user_accounts where userId = ?", new Object[]{userId}, new UserAccountRowMapper());
    }

    public void deleteAccount(long accountNumber) {
        jdbcTemplate.update("delete from user_accounts where accountNumber = ?",accountNumber);
    }
}
