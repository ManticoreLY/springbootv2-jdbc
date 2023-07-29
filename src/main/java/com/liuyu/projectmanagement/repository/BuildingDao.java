package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.Building;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuildingDao implements BuildingService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Building> rowMapper = new BeanPropertyRowMapper<>(Building.class);

    @Override
    public ResponsePack findAll(String projectId) {
        String sql = "select * from tb_building where project_id = ? and status != -1 order by building_id, create_dt";
        try {
            List<Building> list = jdbcTemplate.query(sql, this.rowMapper, projectId);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error where query data!");
        }
    }

    @Override
    public ResponsePack save(Building building, String projectId) {
        String sql = "insert into tb_building (building_name, status, project_Id) values (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, building.getBuildingName(), 1, projectId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error where insert data!");
        }
    }

    @Override
    public ResponsePack update(Building building) {
        String sql = "update tb_building set building_name = ?  where building_id = ?";
        try {
            jdbcTemplate.update(sql, building.getBuildingName(), building.getBuildingId());
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error where update data!");
        }
    }

    @Override
    public ResponsePack remove(String buildingId) {
        String sql = "update tb_building set status = -1 where building_id = ?";
        try {
            jdbcTemplate.update(sql, buildingId);
            return new ResponsePack().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("System error where delete data!");
        }
    }
}
