package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
* model Student
*
* @author sijie
* @date 2023/09/02 13:21
* @version 0.0.1
*/
public class Student extends AbsPojoSet<Student> implements Serializable {
    private Long id;
    private String name;
    private Integer age;

    public Student setId(Long id){
        this.id = id;
        return this;
    }
    public Long getId(){
        return this.id;
    }

    public Student setName(String name){
        this.name = name;
        return this;
    }
    public String getName(){
        return this.name;
    }

    public Student setAge(Integer age){
        this.age = age;
        return this;
    }
    public Integer getAge(){
        return this.age;
    }



    @Override
    public void setPk(Long pk){
        this.id = pk;
    }

    @Override
    public Long getPk() {
        return id;
    }
}