package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.CashAccount;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.CashAccountService;
import com.liuyu.projectmanagement.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CaseAccountDao implements CashAccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<CashAccount> rowMapper = new BeanPropertyRowMapper<>(CashAccount.class);
    @Override
    public ResponsePack findAll() {
        return null;
    }

    @Override
    public ResponsePack save(CashAccount cashAccount) {
        return null;
    }

    @Override
    public ResponsePack update(CashAccount cashAccount) {
        String sql = "update tb_cash_account set business_id = ?, cash = ?, person_id = ? where id = ?";
        try{
            jdbcTemplate.update(sql, cashAccount.getBusinessId(), cashAccount.getCash(), cashAccount.getPersonId(), cashAccount.getId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_UPDATE);
        }
    }

    @Override
    public ResponsePack remove(String id) {
        String sql = "update tb_cash_account set status = -1 where id = ?";
        try{
            jdbcTemplate.update(sql, id);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_DELETE);
        }
    }

    @Override
    public ResponsePack findById(String id) {
        return null;
    }

    @Override
    public ResponsePack listAll(String projectId, String businessId, String personId, String start, String end) {
        StringBuilder sql = new StringBuilder("select ac.*, b.business_name, w.worker_name as person_name from tb_cash_account ac left join tb_business b on ac.business_id = b.business_id left join tb_worker w on w.worker_id = ac.person_id where ac.status != -1 and ac.project_id = ?");
        if (!StringUtils.isEmpty(businessId)) sql.append(" and ac.business_id = \"" + businessId + "\"");
        if (!StringUtils.isEmpty(personId)) sql.append(" and ac.person_id = \"" + personId + "\"");
        if (start != null && end != null) sql.append(" and timestamp(ac.create_dt) > \"" + start + "\" and timestamp(ac.create_dt) < \"" + end + "\"");
        sql.append(" order by ac.create_dt desc");
        try{
            List<CashAccount> list = jdbcTemplate.query(sql.toString(), this.rowMapper, projectId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }

    @Override
    public ResponsePack save(CashAccount cashAccount, String projectId) {
        String sql = "insert into tb_cash_account (business_id, cash, person_id, project_id, status) value (?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(sql, cashAccount.getBusinessId(), cashAccount.getCash(), cashAccount.getPersonId(), projectId, 1);
            return new ResponsePack().success();
        } catch (Exception e) {
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_SAVE);
        }
    }
}
