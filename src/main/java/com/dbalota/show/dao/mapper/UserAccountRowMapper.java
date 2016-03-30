package com.dbalota.show.dao.mapper;

import com.dbalota.show.models.User;
import com.dbalota.show.models.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Balota on 3/30/2016.
 */
public class UserAccountRowMapper implements RowMapper<UserAccount> {
    public UserAccount mapRow(ResultSet rs,
                              int rowNum) throws SQLException {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountNumber(rs.getLong("accountNumber"));
        userAccount.setBalance(rs.getDouble("balance"));
        userAccount.setUserId(rs.getInt("userId"));
        return userAccount;
    }
}