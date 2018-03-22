package com.borun.lab.service;

import com.borun.lab.bean.Version;

import java.util.List;

public interface VersionService {

    Version saveOrUpdate(Version version);

    List<Version> queryByExample(Version version);

    void deleteById(Integer id);

    Version findById(Integer id);

    int getLastId();
}
