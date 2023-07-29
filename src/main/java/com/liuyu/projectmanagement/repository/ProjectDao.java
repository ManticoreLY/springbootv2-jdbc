package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.Project;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDao implements ProjectService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Project> rowMapper;

    private void initRowMapper() {
        this.rowMapper = new BeanPropertyRowMapper<>(Project.class);
    }
    @Override
    public ResponsePack findAll() {
        String sql = "Select * from tb_project where status != -1";
        this.initRowMapper();
        try {
            List<Project> list = jdbcTemplate.query(sql, this.rowMapper);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            return new ResponsePack().fail();
        }
    }

    @Override
    public ResponsePack findById(String projectId) {
        String sql = "Select * from tb_project where project_id = ?";
        this.initRowMapper();
        try{
            Project project = jdbcTemplate.queryForObject(sql, this.rowMapper, projectId);
            return new ResponsePack(project).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("Data can not be found");
        }
    }
    @Override
    public ResponsePack queryUserProjectList(String userId) {
        String sql = "select * from tb_project where user_id = ? and status != -1";
        this.initRowMapper();
        try {
            List<Project> list = jdbcTemplate.query(sql, this.rowMapper, userId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail();
        }
    }

    @Override
    public ResponsePack saveProject(Project project, String userId) {
        String sql = "insert into tb_project (project_name, status, user_id) values (?, ?, ?)";
        try{
            jdbcTemplate.update(sql, project.getProjectName(), project.getStatus(), userId);
            return new ResponsePack().success();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail();
        }
    }

    @Override
    public ResponsePack updateProject(Project project) {
        String sql = "update tb_project set project_name = ?, status = ?, user_id = ? where project_id = ?";
        try{
            jdbcTemplate.update(sql, project.getProjectName(), project.getStatus(), project.getUserId(), project.getProjectId());
            return new ResponsePack().success();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail();
        }
    }

    @Override
    public ResponsePack removeProject(String projectId) {
        return updateStatus(projectId, "-1");
    }

    @Override
    public ResponsePack updateStatus(String projectId, String status) {
        String sql = "update tb_project set status = ? where project_id = ?";
        try{
            jdbcTemplate.update(sql,status, projectId);
            return new ResponsePack().success();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail();
        }
    }
}
