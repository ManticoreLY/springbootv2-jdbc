package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.Floor;
import com.liuyu.projectmanagement.entity.FloorJob;
import com.liuyu.projectmanagement.entity.Job;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.FloorService;
import com.liuyu.projectmanagement.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FloorDao implements FloorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Floor> rowMapper = new BeanPropertyRowMapper<>(Floor.class);

    @Override
    public ResponsePack findAll(String buildingId) {
        String sql = "select * from tb_floor where building_id = ? and status != -1 order by order_index is null asc, order_index asc";
        try {
            List<Floor> list = jdbcTemplate.query(sql, this.rowMapper, buildingId);
            if (list.size() > 0) {
                for(Floor floor: list) {
                    List<FloorJob> floorJobs = jdbcTemplate.query("select tfj.*, tj.job_name, tj.job_unit, tw.worker_name from tb_floor_job tfj left join tb_job tj on tfj.job_id = tj.job_id left join tb_worker tw on tfj.worker_id = tw.worker_id where tfj.floor_id = ? and tj.status != -1 order by tfj.create_dt", new BeanPropertyRowMapper<>(FloorJob.class), floor.getFloorId());
                    floor.setFloorJobs(floorJobs);
                }
            }
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }
    @Override
    public ResponsePack save(Floor floor) {
        String sql = "insert into tb_floor (floor_name, status, order_index, floor_info, building_id) values (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, floor.getFloorName(), 1, floor.getOrderIndex(), floor.getFloorInfo(), floor.getBuildingId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_SAVE);
        }
    }

    @Override
    public ResponsePack batchSave(List<String> floorNameList, String buildingId) {
        String sql = "insert into tb_floor (floor_name, status,  building_id) values ";
        for (int i = 0 ; i < floorNameList.size(); i++) {
            if (i == floorNameList.size() - 1) sql += "('" + floorNameList.get(i) + "', 1, '" + buildingId +"')";
            else sql += "('" + floorNameList.get(i) + "', 1, '" + buildingId +"'), ";
        }
        System.out.println(sql);
        try {
            jdbcTemplate.update(sql);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_SAVE);
        }
    }

    @Override
    public ResponsePack update(Floor floor) {
        String sql = "update tb_floor set floor_name = ?, floor_info = ?, order_index = ?,  building_id = ? where floor_id = ?";
        try {
            jdbcTemplate.update(sql, floor.getFloorName(), floor.getFloorInfo(), floor.getOrderIndex(), floor.getBuildingId(), floor.getFloorId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error where update data!");
        }
    }
    @Override
    public ResponsePack remove(String floorId) {
        try {
            Integer i = jdbcTemplate.queryForObject("select count(*) from tb_floor_job where floor_id = ?", Integer.class, floorId);
            if (i > 0) {
                return new ResponsePack().fail("请删除当前楼层已关联的工序!");
            }
            String sql = "update tb_floor set status = -1 where floor_id = ?";
            jdbcTemplate.update(sql, floorId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error where delete data!");
        }
    }

    @Override
    public ResponsePack listFloors(String buildingId) {
        String sql = "select f.*,group_concat(j.job_id) as job_id, group_concat(j.job_name) as job_name, group_concat(fj.status) as job_status from tb_floor f left join tb_floor_job fj on f.floor_id = fj.floor_id left join tb_job j on fj.job_id = j.job_id  where f.building_id = ? and f.status != -1 group by f.floor_id";
        try{
            List<Floor> list = jdbcTemplate.query(sql, this.rowMapper, buildingId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail(ProjectUtils.ERROR_MESSAGE_IN_QUERY);
        }
    }
}
