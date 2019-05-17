package com.qc.information.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author czt
 * @since 2019-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleName;

    private String remark;

    private Integer createUser;

    private Integer updateUser;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
