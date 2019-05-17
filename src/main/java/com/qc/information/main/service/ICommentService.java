package com.qc.information.main.service;

import com.baomidou.mybatisplus.service.IService;
import com.qc.information.main.entity.Comment;
import com.qc.information.utils.ResultData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author czt123
 * @since 2019-05-09
 */
public interface ICommentService extends IService<Comment> {

    ResultData edit(Comment comment);
}
