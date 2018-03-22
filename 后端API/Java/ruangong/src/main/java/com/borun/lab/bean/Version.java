package com.borun.lab.bean;

import javax.persistence.*;

@Entity
@Table(name="version")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vernum;

    private String filesize;

    private String content;

    private String fileurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVernum() {
        return vernum;
    }

    public void setVernum(String vernum) {
        this.vernum = vernum;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
