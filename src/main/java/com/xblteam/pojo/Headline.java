package com.xblteam.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Component

@NoArgsConstructor
@AllArgsConstructor
@Data

@TableName("news_headline")
public class Headline implements Serializable {

    @TableId("hid")
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private Integer publisher;
    private Integer pageViews = 0;
    private Date createTime;
    private Date updateTime;
    private Long pastHours;
    @Version
    private Integer version;
    @TableLogic
    private Integer isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;

}
