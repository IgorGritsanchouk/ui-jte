package com.ui.repository;

import com.ui.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ui.mapper.ItemRowMapper;

import java.util.List;

@Repository
public class ItemRepository{

    public ItemRepository(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate= jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    public List<Item> getPaginatedItems(int offset, int limit) {
        String query = "SELECT item_id, name, description, price FROM items LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new Object[]{limit, offset}, new ItemRowMapper());
    }

    public int getTotalItemsCount() {
        String countQuery = "SELECT COUNT(*) FROM items";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }

}
