package com.algaworks.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private LocalDateTime time;
    private String title;
    private List<Field> fields;

    public Problem(Integer status, String title) {
        this.status = status;
        this.time = LocalDateTime.now();
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}