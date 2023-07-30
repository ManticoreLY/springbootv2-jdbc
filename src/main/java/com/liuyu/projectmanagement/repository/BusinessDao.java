package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.Business;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.BusinessService;
import com.liuyu.projectmanagement.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessDao implements BusinessService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Business>  rowMapper = new BeanPropertyRowMapper<>(Business.class);

    @Override
    public ResponsePack findAll() {
        return null;
    }

    @Override
    public ResponsePack save(Business business) {
        return null;
    }

    @Override
    public ResponsePack update(Business business) {
        return null;
    }

    @Override
    public ResponsePack remove(String id) {
        String sql = "update tb_business set status = -1 where business_id = ?";
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
    public ResponsePack listAll(String projectId, String name) {
        String sql = "select business_id, business_name from tb_business where project_id = ? and status != -1 and business_name like ?";
        try{
            List<Business> list = jdbcTemplate.query(sql, this.rowMapper, projectId, name + "%");
            return new ResponsePack(list).success();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }

    @Override
    public ResponsePack listAll(String projectId) {
        String sql = "select business_id, business_name from tb_business where project_id = ? and status != -1";
        try{
            List<Business> list = jdbcTemplate.query(sql, this.rowMapper, projectId);
            return new ResponsePack(list).success();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }

    @Override
    public ResponsePack saveBusiness(Business business, String projectId) {
        String sql = "insert into tb_business (business_name, project_id, status) value (?, ?, ?)";
        try{
            jdbcTemplate.update(sql, business.getBusinessName(), projectId, 1);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_UPDATE);
        }
    }
}
