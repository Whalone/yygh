package com.whalone.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whalone.yygh.hosp.mapper.HospitalSetMapper;
import com.whalone.yygh.hosp.service.HospitalSetService;
import com.whalone.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    // 这里不用进行注入 ServiceImpl已经完成注入
//    @Autowired
//    private HospitalSetMapper hospitalSetMapper;
}
