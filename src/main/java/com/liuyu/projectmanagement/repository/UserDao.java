package com.liuyu.projectmanagement.repository;

import com.liuyu.projectmanagement.entity.User;
import com.liuyu.projectmanagement.pack.ResponsePack;
import com.liuyu.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

    @Override
    public ResponsePack findUserById(String id) {
        String sql = "select * from tb_user where user_id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, this.rowMapper, id);
            return new ResponsePack(user).success();
        } catch (Exception e) {
            return new ResponsePack().fail();
        }
    }

    @Override
    public List<User> findUserListByProjId(String projectId) {
        String sql = "select * from tb_user where project_id = ?";
        List<User> list = jdbcTemplate.query(sql, this.rowMapper, projectId);
        return list;
    }

    @Override
    public ResponsePack removeUserById(String id) {
        return setStatus(id, "-1");
    }

    @Override
    public ResponsePack userLogin(String phoneNum, String password) {
        String sql = "select * from tb_user where phone_num = ? and password = ? and status != -1";
        try{
            List<User> list = jdbcTemplate.query(sql, this.rowMapper, phoneNum, password);
            if (list.size() > 0) return new ResponsePack(list.get(0)).success();
            else return new ResponsePack().fail("The user doesn't exist!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponsePack().fail("login fail");
        }
    }

    @Override
    public ResponsePack findAll(String userName, String phoneNum, String status) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from tb_user where status != -1");
        Map<String, String> map = new HashMap<>();
        map.put("user_name", userName);
        map.put("phone_num", phoneNum);
        map.put("status", status);

        for(String key: map.keySet()) {
            String value = map.get(key);
            if (!StringUtils.isEmpty(value)) {
                sb.append(" and " + key + "= " + value);
            }
        }
        String sql = sb.toString();
        System.out.println(sql);
        try{
            List<User> list = jdbcTemplate.query(sql, this.rowMapper);
            return new ResponsePack(list).success();
        } catch (Exception e) {
            return new ResponsePack().fail();
        }
    }

    @Override
    public ResponsePack setStatus(String id, String status) {
        String sql = "update tb_user set status = ? where user_id = ?";
        try{
            jdbcTemplate.update(sql, status, id);
            return new ResponsePack().success();
        } catch (Exception e) {
            return new ResponsePack().fail("操作出错");
        }

    }

    @Override
    public ResponsePack saveUser(User user) {
        String sql = "insert into tb_user(user_name, phone_num, password, status, user_auth) values (?, ?, ?, ?, ?)";
        try{
            int i = jdbcTemplate.update(sql, user.getUserName(), user.getPhoneNum(), user.getPassword(), user.getStatus(), user.getUserAuth());
            System.out.println(i);
            return new ResponsePack().success();
        } catch (Exception e) {
            return new ResponsePack().fail(e.toString());
        }
    }

    @Override
    public ResponsePack updateUser(User user) {
        String sql = "update tb_user set user_name = ?, phone_num = ?, password = ?, status=?, user_auth = ? where user_id = ?";
        try{
            jdbcTemplate.update(sql, user.getUserName(), user.getPhoneNum(), user.getPassword(), user.getStatus(), user.getUserAuth(), user.getUserId());
            return new ResponsePack().success();
        } catch (Exception e) {
            return new ResponsePack().fail();
        }
    }
}
