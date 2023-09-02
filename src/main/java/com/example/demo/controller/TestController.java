package com.example.demo.controller;

//@Controller 控制层需要的注解
//@RestController 使用这个也是可以的，但是使用后他里面所有请求返回的都是字符串！
//一般只需要作为接口放回JSON格式数据的话推荐使用@RestController
//@Controller这个是可以与Thymeleaf模板引擎使用时可以返回一个页面的

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Student;
import com.example.demo.service.IStudentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping指定路径名
//@RequestMapping("/test")用这个来指定路径也是可以的
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    public IStudentInterface studentInterface;

    //Get请求
    @GetMapping
    public String queryAll() {
        List<Student> students = studentInterface.queryAllStudents();
        return JSON.toJSONString(students);
    }

    //使用了RestFull风格
    @GetMapping("/{id}")
    public String query(@PathVariable(value = "id") Integer id) {
        return JSON.toJSONString(id);
    }

    //post请求
    //@RequestBody 表示接收请求是JSON格式的数据
    @PostMapping("/{add}")
    public String add(@PathVariable(value = "add")Integer id) {
        return JSON.toJSONString(id);
    }

    //Delete请求
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") Integer id) {
        return JSON.toJSONString(id);
    }

    //Put请求
    @PutMapping
    public String update() {
        return JSON.toJSONString("44");
    }

}
