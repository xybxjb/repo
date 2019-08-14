package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.FeeDueTimeDao;
import cn.deepcoding.model.FeeDueTime;
import cn.deepcoding.service.FeeDueTimeService;
@Service
@Transactional
public class FeeDueTimeServiceImpl implements  FeeDueTimeService {
@Autowired
private	FeeDueTimeDao feeDueTimeDao;

 

}
