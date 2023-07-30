package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.Budget;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.BudgetService;
import com.liuyu.projectmanagement.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class BudgetDao implements BudgetService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Budget> rowMapper = new BeanPropertyRowMapper<>(Budget.class);
    @Override
    public ResponsePack findAll(String projectId, String name) {
        String sql = "select * from tb_budget where project_id = ? and status != -1";
        Object[] param = null;
        if (!StringUtils.isEmpty(name)) {
            sql += " and budget_name like ?";
            param = new Object[]{projectId, "%" + name + "%"};
        } else {
            param = new Object[]{projectId};
        }
        try{
            List<Budget> list = jdbcTemplate.query(sql, this.rowMapper, param);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }

    @Override
    public ResponsePack findAll() {
        return null;
    }

    @Override
    public ResponsePack save(Budget budget, String projectId) {
        String sql = "insert into tb_budget (budget_name, budget_price, budget_count, budget_sum, project_id) values (?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(sql, budget.getBudgetName(), budget.getBudgetPrice(), budget.getBudgetCount(), budget.getBudgetPrice() * budget.getBudgetCount(), projectId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_SAVE);
        }
    }

    @Override
    public ResponsePack save(Budget budget) {
        return null;
    }

    @Override
    public ResponsePack update(Budget budget) {
        String sql = "update tb_budget set budget_name = ?, budget_price = ?, budget_count = ?, budget_sum = ? where budget_id = ?";
        try{
            jdbcTemplate.update(sql, budget.getBudgetName(), budget.getBudgetPrice(), budget.getBudgetCount(), budget.getBudgetPrice() * budget.getBudgetCount(), budget.getBudgetId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_UPDATE);
        }
    }

    @Override
    public ResponsePack remove(String id) {
        String sql = "update tb_budget set status = -1 where budget_id = ?";
        try {
            jdbcTemplate.update(sql, id);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail();
        }
    }

    @Override
    public ResponsePack findById(String id) {
        return null;
    }
}
