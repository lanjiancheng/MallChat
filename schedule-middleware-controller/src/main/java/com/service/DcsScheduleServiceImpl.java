package com.service;

import com.huajuaner.domain.DataCollect;
import com.huajuaner.domain.DcsScheduleInfo;
import com.huajuaner.domain.DcsServerNode;
import com.huajuaner.domain.Instruct;
import com.huajuaner.export.DcsScheduleResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥
 */
@Service("dcsScheduleService")
public class DcsScheduleServiceImpl implements DcsScheduleService {

    @Value("${bugstack.middleware.scheudle.zkAddress}")
    private String zkAddress;

    private DcsScheduleResource dcsScheduleResource = null;

    @Override
    public List<String> queryPathRootServerList() throws Exception {
        return getDcsScheduleResource().queryPathRootServerList();
    }

    @Override
    public List<DcsScheduleInfo> queryDcsScheduleInfoList(String schedulerServerId) throws Exception {
        return getDcsScheduleResource().queryDcsScheduleInfoList(schedulerServerId);
    }

    @Override
    public void pushInstruct(Instruct instruct) throws Exception {
        getDcsScheduleResource().pushInstruct(instruct);
    }

    @Override
    public DataCollect queryDataCollect() throws Exception {
        return getDcsScheduleResource().queryDataCollect();
    }

    @Override
    public List<DcsServerNode> queryDcsServerNodeList() throws Exception {
        return getDcsScheduleResource().queryDcsServerNodeList();
    }

    private DcsScheduleResource getDcsScheduleResource() {
        if (null == dcsScheduleResource) {
            dcsScheduleResource = new DcsScheduleResource(zkAddress);
        }
        return dcsScheduleResource;
    }

}
