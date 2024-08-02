package com.xblteam.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component

@NoArgsConstructor
@AllArgsConstructor
@Data

@TableName("news_user")
public class User implements Serializable {

    @TableId("uid")
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
    @Version
    private Integer version;
    @TableLogic
    private Integer isDeleted;
    @Serial
    private static final long serialVersionUID = 1L;

}
