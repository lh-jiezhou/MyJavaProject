package com.lh.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 除了有参构造
@AllArgsConstructor // 有参构造(无参构造又没了)
@NoArgsConstructor // 再加上无参构造
public class Books {

    /**
     * 书id
     */
    private Integer bookID;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 数量
     */
    private Integer bookCounts;
    /**
     * 描述
     */
    private String detail;

}