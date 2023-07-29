package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.Job;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDao implements JobService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Job> rowMapper = new BeanPropertyRowMapper<>(Job.class);

    @Override
    public ResponsePack findAll(String projectId) {
        String sql = "select * from tb_job where project_id = ? and status != -1 order by job_id";
        try{
            List<Job> list = jdbcTemplate.query(sql, this.rowMapper, projectId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when sql data!");
        }
    }

    @Override
    public ResponsePack save(Job job, String projectId) {
        String sql = "insert into tb_job (job_name, job_unit, job_price, status, project_id) values (?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(sql, job.getJobName(), job.getJobUnit(), job.getJobPrice(), 1, projectId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when insert data!");
        }
    }

    @Override
    public ResponsePack update(Job job) {
        String sql = "update tb_job set job_name = ?, job_unit = ?, job_price = ?, status = ? where job_id = ?";
        try{
            jdbcTemplate.update(sql, job.getJobName(), job.getJobUnit(), job.getJobPrice(), job.getStatus(), job.getJobId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when update data!");
        }
    }

    @Override
    public ResponsePack remove(String jobId) {
        String sql = "update tb_job set status = -1 where job_id = ?";
        try{
            jdbcTemplate.update(sql, jobId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when delete data!");
        }
    }
}
