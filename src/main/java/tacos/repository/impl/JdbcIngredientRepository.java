package tacos.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import tacos.domain.Ingredient;
import tacos.repository.IngredientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@Repository
public class JdbcIngredientRepository implements IngredientRepository { // 使用JdbcTemplate查询

    private JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                this::romMapper);
    }

    @Override
    public Optional<Ingredient> findById(String id) {   // Optional表示从数据库查询返回的单个对象，可以解决空指针异常
        List<Ingredient> list = jdbcTemplate.query(
                "select id, name, type from Ingredient where id = ?",
                this::romMapper,
                id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name,type) values (?,?,?);",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    private Ingredient romMapper(ResultSet row, int rowNum) throws SQLException {    // mapper：将查询结果映射为Java对象
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }
}
