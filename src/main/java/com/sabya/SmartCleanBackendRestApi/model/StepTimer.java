package com.sabya.SmartCleanBackendRestApi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
public class StepTimer {

    private static int ObjCounter=13;

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id=++ObjCounter;
    private int counter;
    private int stepTime;
    private Date creationTime;
    private Date updationTime;


//    @PrePersist
//    private void setCreationTime()
//    {
//        creationTime=new Date();
//    }

//    @PreUpdate
//    private void setUpdationTime()
//    {
//        updationTime=new Date();
//    }
}
