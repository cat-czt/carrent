package com.qc.information.main.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qc.information.config.AuthPassport;
import com.qc.information.main.entity.Comment;
import com.qc.information.main.service.ICommentService;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.qc.information.config.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author czt123
 * @since 2019-05-09
 */
@RestController
@RequestMapping("/main/comment")
public class CommentController extends BaseController {

    @Autowired
    private ICommentService commentService;


    @GetMapping("/list")
    @AuthPassport(false)
    @ResponseBody
    public List<Comment> OrdersList(Comment comment){
        Wrapper<Comment> wrapper = new EntityWrapper<>();
        return commentService.selectList(wrapper);
    }

    @GetMapping("/latest")
    @AuthPassport(false)
    @ResponseBody
    public List<Comment> latest(){
        Wrapper<Comment> wrapper = new EntityWrapper();
        List<Comment> list =  commentService.selectList(wrapper);
        if(list != null && list.size() > 5){
            list = list.subList(0,5);
        }
        return list;
    }


    @PostMapping("/edit")
    @ResponseBody
    public ResultData edit(@RequestBody Comment comment){
        return commentService.edit(comment);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Comment get(@PathVariable("id") Integer id){
        Wrapper<Comment> wrapper = new EntityWrapper<>();
        wrapper.eq("order_id",id);
        return commentService.selectOne(wrapper);
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    @Transactional
    public ResultData del(@PathVariable("id") Integer id){
        Wrapper<Comment> wrapper = new EntityWrapper<>();
        wrapper.eq("order_id",id);
        commentService.delete(wrapper);
        return ResultUtils.success();
    }
}

