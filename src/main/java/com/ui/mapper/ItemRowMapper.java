package com.ui.mapper;

import com.ui.model.Item;
import com.ui.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {

        Item item = new Item();
        item.setItemId(rs.getLong("item_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getBigDecimal("price"));

        return item;
    }

}
