package com.borun.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.borun.lab.bean.Version;
import com.borun.lab.service.VersionService;
import com.borun.lab.utils.RespUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    private VersionService versionService;

    @Autowired
    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping("/getupdate")
    public JSONObject getupdate() {
        try {
            int maxId = versionService.getLastId();
            Version model = null;
            if(maxId != 0) {
                model = versionService.findById(maxId);
                return RespUtils.createSucResp("最新新版本", model);
            }
            else
                return RespUtils.createFailResp("没有找到任何版本");
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }
}
