package com.qc.information.main.entity;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author czt123
 * @since 2019-05-09
 */
@Data
public class PlayerQuery {
    private Integer id;
    private String teamName;
    private String playerName;
    private String nickName;
    private Integer age;
    private String sex;
    private String description;
    private String score;
    private String shootRecord;
    private String assists;

}
