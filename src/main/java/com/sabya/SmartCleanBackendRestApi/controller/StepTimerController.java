package com.sabya.SmartCleanBackendRestApi.controller;

import com.sabya.SmartCleanBackendRestApi.model.StepTimer;
import com.sabya.SmartCleanBackendRestApi.model.StepTimerTask;
import com.sabya.SmartCleanBackendRestApi.repository.StepTimerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Getter
@RestController
public class StepTimerController {
    @Autowired
    private StepTimerRepository dao;

    private Map<Integer, StepTimer> timers = new HashMap<>();

    private HashMap<Integer,Timer> map =new HashMap<>();

    @RequestMapping("/stepTimer/_create/{stepTimer}")
    public int createTimer(@MatrixVariable("startval") int startval, @MatrixVariable("step") int step)
    {
        StepTimer stepTimer=new StepTimer();
     //   System.out.println(startval +"  "+step);
        stepTimer.setCounter(startval);
        stepTimer.setStepTime(step);
        stepTimer.setCreationTime(new Date());
        stepTimer.setUpdationTime(new Date());
        timers.put(stepTimer.getId(),stepTimer);
        StepTimerTask task = new StepTimerTask(stepTimer);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(task,0,step);
        map.put(stepTimer.getId(),timer);
//        dao.save(stepTimer);
        return stepTimer.getId();
    }



    @GetMapping("/")
    public List<StepTimer> getAll()
    {
       dao.saveAll(timers.values());
        return dao.findAll();
    }

    @GetMapping("/stepTimer/_check/{id}")
    public Optional<StepTimer> checkTimer(@PathVariable("id") int id)
    {
        //System.out.println(id);
        dao.saveAll(timers.values());
        return dao.findById(id);
    }

    @GetMapping("/stepTimer/_check")
    public List<StepTimer> checkTimer()
    {
        dao.saveAll(timers.values());
        return dao.findAll();
    }

    @RequestMapping("/stepTimer/_clear/{id}")
    public int clear(@PathVariable("id") int id)
    {
         map.get(id).cancel();
         dao.saveAll(timers.values());
         dao.deleteById(id);
         timers.remove(id);
         dao.saveAll(timers.values());

         return id;

    }

    @RequestMapping("/stepTimer/_pause/{id}")
    public int pause(@PathVariable("id") int id)
    {
        map.get(id).cancel();
        dao.saveAll(timers.values());
        return id;

    }





}
