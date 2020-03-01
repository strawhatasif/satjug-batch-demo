package com.fun;

import com.fun.model.SettledAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CorrectSpellingRowMapper implements RowMapper<SettledAccount> {

    @Override
    public SettledAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SettledAccount().toBuilder()
                .id(rs.getString("ID"))
                .name(rs.getString("NAME"))
                .product(rs.getString("PRODUCT"))
                .notes(rs.getString("NOTES"))
                .status(rs.getString("STATUS"))
                .build();
    }
}
