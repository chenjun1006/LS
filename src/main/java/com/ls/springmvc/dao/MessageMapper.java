package com.ls.springmvc.dao;

import com.ls.springmvc.entity.Message;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}