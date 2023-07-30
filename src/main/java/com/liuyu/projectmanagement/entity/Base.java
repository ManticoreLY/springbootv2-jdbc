package com.liuyu.projectmanagement.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public class Base {
    @Column(name = "status")
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_dt")
    private Date createDt;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_dt")
    private Date updateDt;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDt() {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df.format(createDt);
        return createDt;
    }

    public void setCreateDt(Date createDt) {
//       if (StringUtils.isEmpty(createDt)) this.createDt = new Date();
//       else {
//           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//           try {
//               this.createDt = df.parse(createDt);
//           } catch (ParseException e) {
//               throw new RuntimeException(e);
//           }
//       }
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df.format(updateDt);
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
//        if (StringUtils.isEmpty(updateDt)) this.updateDt = new Date();
//        else {
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
//                this.updateDt = df.parse(updateDt);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }
        this.updateDt = updateDt;
    }
}
