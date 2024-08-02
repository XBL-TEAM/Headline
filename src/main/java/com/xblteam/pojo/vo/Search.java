package com.xblteam.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Search {
    
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize =10;

}