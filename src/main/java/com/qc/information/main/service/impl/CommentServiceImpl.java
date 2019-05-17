package com.qc.information.main.service.impl;

import com.qc.information.main.entity.Comment;
import com.qc.information.main.mapper.CommentMapper;
import com.qc.information.main.service.ICommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czt123
 * @since 2019-05-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    @Transactional
    public ResultData edit(Comment comment) {
        if(comment.getOrderId() != null){
            this.updateById(comment);
        }else{
            this.insert(comment);
        }
        return ResultUtils.success();
    }
}
