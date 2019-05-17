package com.qc.information.main.mapper;

import com.qc.information.main.entity.Player;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qc.information.main.entity.PlayerQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author czt123
 * @since 2019-05-09
 */
public interface PlayerMapper extends BaseMapper<Player> {

    List<PlayerQuery> list();

    List<Player> assist();

    List<Player> shoot();
}
