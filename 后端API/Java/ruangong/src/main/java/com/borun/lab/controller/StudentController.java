package com.borun.lab.controller;


import com.alibaba.fastjson.JSONObject;
import com.borun.lab.bean.Student;
import com.borun.lab.service.StudentService;
import com.borun.lab.utils.RespUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sign")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public JSONObject login(@RequestParam String account, @RequestParam String password) {
        if(account == null || password == null)
            return RespUtils.createFailResp("获取参数异常！");
        Student model = studentService.doLogin(account, password);
        if (model != null)
            return RespUtils.createSucResp(model);
        else
            return RespUtils.createFailResp("用户名或密码错误");
    }

    @PostMapping("/change")
    public JSONObject change(@RequestParam String account, @RequestParam String password,@RequestParam String newpass) {
        if(account == null || password == null|| newpass == null)
            return RespUtils.createFailResp("获取参数异常！");
        Student model = studentService.doLogin(account, password);
        if (model == null)
            return RespUtils.createFailResp("用户名或密码错误！");
        try{
            model.setPassword(newpass);
            studentService.saveOrUpdate(model);
            return RespUtils.createSucResp("密码修改成功！");
        }catch(Exception e){
            return RespUtils.createFailResp("系统异常！");
        }
    }


    @PostMapping("/arrive")
    public JSONObject arrive(@RequestParam String account) {
        if (account == null)
            return RespUtils.createFailResp("获取参数异常！");
        try {
            Student model = studentService.findByStuNum(account);
            model.setSign(1);
            model.setSignall(model.getSignall() + 1);
            studentService.saveOrUpdate(model);
            return RespUtils.createSucResp("签到成功！");
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @PostMapping("/leave")
    public JSONObject leave(@RequestParam String account) {
        if (account == null)
            return RespUtils.createFailResp("获取参数异常！");
        try {
            Student model = studentService.findByStuNum(account);
            model.setLeave(1);
            model.setLeaveall(model.getLeaveall() + 1);
            studentService.saveOrUpdate(model);
            return RespUtils.createSucResp("请假成功！");
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @GetMapping("/arriveall")
    public JSONObject arriveall() {
        try {
            Student model = new Student();
            model.setSign(1);
            List<Student> list = studentService.queryByExample(model);
            long count = studentService.getMaxSignall();
            return RespUtils.createSucResp(count+"",list);
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @GetMapping("/leaveall")
    public JSONObject leaveall() {
        try {
            Student model = new Student();
            model.setLeave(1);
            List<Student> list = studentService.queryByExample(model);
            long count = studentService.getMaxSignall();
            return RespUtils.createSucResp(count+"",list);
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @GetMapping("/unsignall")
    public JSONObject unsignall() {
        try {
            Student model = new Student();
            model.setSign(0);
            model.setLeave(0);
            List<Student> list = studentService.queryByExample(model);
            long count = studentService.getMaxSignall();
            return RespUtils.createSucResp(count+"",list);
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @GetMapping("/arrivecount")
    public JSONObject arrivecount() {
        try {
            Student model = new Student();
            model.setSign(1);
            long count = studentService.getCount(model);
            return RespUtils.createSucResp(count);
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @GetMapping("/leavecount")
    public JSONObject leavecount() {
        try {
            Student model = new Student();
            model.setLeave(1);
            long count = studentService.getCount(model);
            return RespUtils.createSucResp(count);
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @GetMapping("/unsigncount")
    public JSONObject unsigncount() {
        try {
            Student model = new Student();
            model.setSign(0);
            model.setLeave(0);
            long count = studentService.getCount(model);
            return RespUtils.createSucResp(count);
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

    @PostMapping("/signorleave")
    public JSONObject signorleave(@RequestParam String account) {
        if (account == null)
            return RespUtils.createFailResp("获取参数异常！");
        try {
            Student model = studentService.findByStuNum(account);
            if (model.getLeave() == 1 || model.getSign() == 1)
                return RespUtils.createSucResp("签到过了！");
            else
                return RespUtils.createFailResp("没有签到！");
        } catch (Exception e) {
            return RespUtils.createFailResp("系统异常！");
        }
    }

    @PostMapping("/adminsign")
    public JSONObject adminsign() {
        List<Student> list = studentService.queryByExample(null);
        try {
            for (Student model:list) {
                model.setSign(0);
                model.setLeave(0);
                studentService.saveOrUpdate(model);
            }
            return RespUtils.createSucResp();
        } catch (Exception e) {
            return RespUtils.createFailResp();
        }
    }

}
