package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.FloorJob;
import com.liuyu.projectmanagement.entity.Worker;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.WorkerService;
import com.liuyu.projectmanagement.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDao implements WorkerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Worker> rowMapper = new BeanPropertyRowMapper<>(Worker.class);

    @Override
    public ResponsePack findAll(String projectId) {
        String sql = "select * from tb_worker where project_id = ? and status != -1";
        try{
            List<Worker> list = jdbcTemplate.query(sql, this.rowMapper, projectId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when query data!");
        }
    }

    @Override
    public ResponsePack save(Worker worker, String projectId) {
        String sql = "insert into tb_worker (worker_name, worker_phone, status, id_code, project_id) values (?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(sql, worker.getWorkerName(), worker.getWorkerPhone(), worker.getStatus(), worker.getIdCode(), projectId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when insert data!");
        }
    }

    @Override
    public ResponsePack update(Worker worker) {
        String sql = "update tb_worker set worker_name = ?, worker_phone = ?, status = ?, id_code = ? where worker_id = ?";
        try{
            jdbcTemplate.update(sql, worker.getWorkerName(), worker.getWorkerPhone(), worker.getStatus(), worker.getIdCode(), worker.getWorkerId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when update data!");
        }
    }

    @Override
    public ResponsePack remove(String workerId) {
        String sql = "update tb_worker set status = -1 where worker_id = ?";
        try{
            jdbcTemplate.update(sql, workerId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error when delete data!");
        }
    }

    @Override
    public ResponsePack listWorkerJob(String workerId) {
        String sql = "select b.building_name, f.floor_name, j.job_name, j.job_unit, j.job_price, fj.finish_count from tb_floor_job fj join tb_floor f on f.floor_id = fj.floor_id left join tb_building b on b.building_id = f.building_id left join tb_job j on j.job_id = fj.job_id left join tb_worker w on fj.worker_id = w.worker_id where fj.worker_id = ?";
        try{
            List<FloorJob> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FloorJob.class), workerId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }
}
