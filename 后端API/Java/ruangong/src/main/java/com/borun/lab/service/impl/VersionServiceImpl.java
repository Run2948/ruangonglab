package com.borun.lab.service.impl;

import com.borun.lab.bean.Version;
import com.borun.lab.dao.VersionDao;
import com.borun.lab.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VersionServiceImpl implements VersionService{

    private VersionDao versionDao;

    @Autowired
    public VersionServiceImpl(VersionDao versionDao) {
        this.versionDao = versionDao;
    }

    @Override
    public Version saveOrUpdate(Version version) {
        return versionDao.saveAndFlush(version);
    }

    @Override
    public List<Version> queryByExample(Version version) {
        if(version == null)
            return versionDao.findAll();
        return versionDao.findAll(Example.of(version));
    }

    @Override
    public void deleteById(Integer id) {
        Version model = versionDao.findVersionById(id);
        if(model != null)
            versionDao.delete(model);
    }

    @Override
    public Version findById(Integer id) {
        return versionDao.findVersionById(id);
    }

    @Override
    public int getLastId() {
        return versionDao.findMaxId();
    }
}
