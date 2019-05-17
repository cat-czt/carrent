package com.qc.information.main.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

/**
 * Created by 稻草人 on 2019/4/14.
 */

@Data
public class Comment {

    @TableField("order_id")
    private String orderId;
    @TableField("comment_score")
    private Integer commentScore;
    @TableField("comment_desc")
    private String commentDesc;

}
