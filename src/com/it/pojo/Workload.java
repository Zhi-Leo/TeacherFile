package com.it.pojo;

public class Workload {
    private String id;//  工作量id
    private String teacher;//  教师用户名
    private String workDate;// 工作量日期
    private float hours;// 工作量耗时
    private String description;//  工作描述
//todo 需要补充空参构造和全参构造，并且get和set方法

    public Workload() {
    }

    public Workload(String id, String teacher, String workDate, float hours, String description) {
        this.id = id;
        this.teacher = teacher;
        this.workDate = workDate;
        this.hours = hours;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
