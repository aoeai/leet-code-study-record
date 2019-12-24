package com.aoeai.lcsr.service;

import com.aoeai.lcsr.bo.userproblem.UserProblemBo;
import com.aoeai.lcsr.constant.TextConstant;
import com.aoeai.lcsr.dao.entity.LcsrProblem;
import com.aoeai.lcsr.dao.service.ILcsrProblemService;
import com.aoeai.lcsr.enums.ProblemDifficultyEnum;
import com.aoeai.lcsr.enums.ProblemStatusEnum;
import com.aoeai.lcsr.form.problem.ProblemListForm;
import com.aoeai.lcsr.utils.page.Page;
import com.aoeai.lcsr.utils.page.PageHelper;
import com.aoeai.lcsr.utils.page.Pagination;
import com.aoeai.lcsr.vo.user.ProblemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 问题
 */
@Service
public class ProblemService {

    @Autowired
    private ILcsrProblemService lcsrProblemService;

    @Autowired
    private UserProblemService userProblemService;

    public Pagination list(ProblemListForm form, int userId) {
        Map<Integer, UserProblemBo> map = userProblemService.getUserProblemMap(userId,
                form.getStatus(), form.getFewDaysAgo());
        List<Integer> problemIdsByUser = new ArrayList<>();
        if (form.getStatus() > 0 || form.getFewDaysAgo() != null) {
            if (map.isEmpty()) {
                // 不应该查询出数据
                problemIdsByUser.add(Integer.MAX_VALUE);
            }else {
                for (Map.Entry<Integer, UserProblemBo> e :map.entrySet()){
                    problemIdsByUser.add(e.getValue().getProblemId());
                }
            }
        }

        List<LcsrProblem> list = lcsrProblemService.list(getProblemListQuery(form, problemIdsByUser));
        List<ProblemVo> records = new ArrayList<>(list.size());
        for (LcsrProblem p : list) {
            ProblemVo pVo = new ProblemVo();
            BeanUtils.copyProperties(p, pVo);
            pVo.setDifficulty(ProblemDifficultyEnum.getDifficulty(p.getDifficulty()));

            pVo.setUrlCN(String.format("https://leetcode-cn.com/problems/%s/", p.getProblemEn()));
            pVo.setUrlEN(String.format("https://leetcode.com/problems/%s/discuss/?currentPage=1&orderBy=most_votes&query=", p.getProblemEn()));

            UserProblemBo userProblem = map.get(p.getId());
            if (userProblem != null) {
                pVo.setDoCount(userProblem.getDoCount() == null ? TextConstant.NONE : userProblem.getDoCount().toString());
                pVo.setStatus(userProblem.getStatus());
                pVo.setLastDoTime(userProblem.getLastDoTime() == null ? TextConstant.NONE : userProblem.getLastDoTime());
            }else {
                pVo.setDoCount(TextConstant.NONE);
                pVo.setStatus(ProblemStatusEnum.NOT_ANSWERED.getTitle());
                pVo.setLastDoTime(TextConstant.NONE);
            }

            records.add(pVo);
        }

        return pagingQuery(records, form.getPage());
    }

    /**
     * 组装问题列表查询条件
     * @param form
     * @return
     */
    private QueryWrapper getProblemListQuery(ProblemListForm form, List<Integer> problemIdsByUser){
        List<Integer> problemIds = new ArrayList<>();
        if (StringUtils.isNotBlank(form.getIds())) {
            for (String id : form.getIds().split(",")) {
                if (!NumberUtils.isDigits(id)) {
                    continue;
                }
                problemIds.add(Integer.parseInt(id));
            }
        }
        // 交集
        if(!problemIds.isEmpty() && !problemIdsByUser.isEmpty()){
            problemIds.retainAll(problemIdsByUser);

            // 没有数据
            if (problemIds.isEmpty()) {
                problemIds.add(Integer.MAX_VALUE);
            }
        }
        if (problemIds.isEmpty()) {
            problemIds = problemIdsByUser;
        }

        QueryWrapper query = new QueryWrapper();
        if (problemIds.size() > 0) {
            query.in("id", problemIds);
        }

        return query;
    }

    /**
     * 分页
     * @param records
     * @param currentNum
     * @return
     */
    private Pagination pagingQuery(List<ProblemVo> records, int currentNum){
        int pageSize = 20;
        Page page = new Page(pageSize, records.size());
        page.setCurrentNum(currentNum);

        int start = (page.getCurrentNum() - 1) * pageSize;
        start = start < 0 ? 0 : start;
        int end = start + pageSize;
        end = end > records.size() - 1? records.size() - 1: end;
        end = end < 0 ? 0 : end;

        Pagination pagination = PageHelper.buildPagination(page, records.subList(start, end));
        return pagination;
    }

}
