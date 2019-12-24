package com.aoeai.lcsr.service;

import com.aoeai.lcsr.dao.entity.LcsrProblemRecord;
import com.aoeai.lcsr.dao.service.ILcsrProblemRecordService;
import com.aoeai.lcsr.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 刷题记录
 */
@Service
public class ProblemRecordService {

    @Autowired
    private ILcsrProblemRecordService lcsrProblemRecordService;

    /**
     * 保存刷题记录
     * @param userId
     * @param problemId
     * @return
     */
    public boolean insert(int userId, int problemId){
        LcsrProblemRecord record = new LcsrProblemRecord();
        record.setUserId(userId);
        record.setProblemId(problemId);
        record.setAddTime(DateUtils.now());

        return lcsrProblemRecordService.save(record);
    }
}
