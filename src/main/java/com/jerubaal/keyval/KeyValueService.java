package com.jerubaal.keyval;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class KeyValueService {

    private JdbcTemplate jdbcTemplate;

    public KeyValueService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<String> get(String key) {
        List<String> results = new ArrayList<>();
        try {
            results = jdbcTemplate.query(
                    "select v from demo where k = ?",
                    List.of(key).toArray(),
                    (resultSet, rowNum) -> resultSet.getString("v")
            );
        } catch (DataAccessException ex) {
            Logger.getLogger(this.getClass().getName()).warning("Failed to look up value for key '" + key + "'");
        }
        return results.stream().findFirst();
    }
}
