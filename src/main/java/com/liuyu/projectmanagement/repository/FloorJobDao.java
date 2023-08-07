package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.Floor;
import com.liuyu.projectmanagement.entity.FloorJob;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.FloorJobService;
import com.liuyu.projectmanagement.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FloorJobDao implements FloorJobService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FloorJob> rowMapper = new BeanPropertyRowMapper<>(FloorJob.class);

    @Override
    public ResponsePack save(FloorJob floorJob) {
        String sql = "insert into tb_floor_job (floor_id, job_id, worker_id, job_count) value (?, ?, ?, ?)";
        try{
            jdbcTemplate.update(sql, floorJob.getFloorId(), floorJob.getJobId(), floorJob.getWorkerId(), floorJob.getJobCount());
            return new ResponsePack().success();
        }catch (Exception e) {
            return new ResponsePack().fail();
        }
    }

    @Override
    public ResponsePack batchSaveFloorJob(List<Floor> floorList, List<String> jobIdList) {
        StringBuilder s = new StringBuilder();
        s.append("insert into tb_floor_job (floor_id, job_id) values");
        for(int i = 0; i < floorList.size(); i++) {
            for(int j = 0; j < jobIdList.size(); j++) {
                if (i == floorList.size() - 1 && j == jobIdList.size() - 1) s.append(" (\"" + floorList.get(i).getFloorId() + "\", \"" + jobIdList.get(j) +"\");");
                else s.append(" (\"" + floorList.get(i).getFloorId() + "\", \"" + jobIdList.get(j) +"\"),");
            }
        }
        String sql = s.toString();
        System.out.println(sql);
        try{
            for (int i = 0; i < floorList.size(); i++) {
                jdbcTemplate.update("delete from tb_floor_job where floor_id = ?", floorList.get(i).getFloorId());
            }
            jdbcTemplate.update(sql);
            return new ResponsePack().success();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("Error occur where batch insert data!");
        }
    }

    @Override
    public ResponsePack findFloorByJobId(String jobId) {
        String sql = "select fj.id, b.building_name, fj.floor_id, f.floor_name, j.job_id, j.job_name, fj.job_count, w.worker_id, w.worker_name from tb_floor_job fj " +
                "join tb_floor f on fj.floor_id = f.floor_id " +
                "join tb_job j on fj.job_id = j.job_id " +
                "join tb_building b on f.building_id = b.building_id " +
                "left join tb_worker w on fj.worker_id = w.worker_id " +
                "where fj.job_id = ? and f.status != -1 order by b.building_name, f.floor_name;";
        try{
            List<FloorJob> list = jdbcTemplate.query(sql, this.rowMapper, jobId);
            return new ResponsePack(list).success();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("Error occur where query data!");
        }
    }

    @Override
    public ResponsePack listFloorJobInfo(String buildingId) {
        String sql = "select fj.id, fj.floor_id, fj.finish_count, f.floor_name, f.progress_per, fj.job_count, j.job_id, j.job_name, j.job_unit, w.worker_name from tb_floor_job fj join tb_floor f on f.floor_id = fj.floor_id join tb_job j on j.job_id = fj.job_id left join tb_worker w on w.worker_id = fj.worker_id left join tb_building b on b.building_id = f.building_id where b.building_id = ? and f.status != -1 and j.status != -1 order by fj.floor_id; ";
        try {
            List<FloorJob> list = jdbcTemplate.query(sql, this.rowMapper, buildingId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }

    @Override
    public ResponsePack findAll(String projectId) {
        return null;
    }

    @Override
    public ResponsePack findAll() {
        return null;
    }

    @Override
    public ResponsePack save(FloorJob floorJob, String projectId) {
        return null;
    }


    @Override
    public ResponsePack update(FloorJob floorJob) {
        String sql = "update tb_floor_job set worker_id = ?, job_count = ? where id = ?";
        try{
            jdbcTemplate.update(sql, floorJob.getWorkerId(), floorJob.getJobCount(), floorJob.getId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_UPDATE);
        }
    }

    @Override
    public ResponsePack remove(String id) {
        String sql = "update tb_floor_job set status = -1 where id = ?";
        try {
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
    public ResponsePack setFinishCount(String id, Double finishCount) {
        String sql = "update tb_floor_job set finish_count = ? where id = ?";
        try {
            jdbcTemplate.update(sql, finishCount, id);
            return new ResponsePack().success();
        } catch (Exception e) {
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_UPDATE);
        }
    }
    @Override
    public ResponsePack setFloorProgress(String floorId) {
        String sql = "select round(avg(case job_count when 0 then 0 else finish_count / job_count end), 2) from (select floor_id, (case when finish_count is null then 0 else finish_count end) finish_count, (case when job_count is null then 0 else job_count end) job_count from tb_floor_job where floor_id = ?) as resultMap group by floor_id;";
        try{
            Double db = jdbcTemplate.queryForObject(sql, Double.class, floorId);
            System.out.println(db);
            jdbcTemplate.update("update tb_floor set progress_per = ? where floor_id = ?", db, floorId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail();
        }
    }
}
