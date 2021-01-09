package com.sabya.SmartCleanBackendRestApi.controller;

import com.sabya.SmartCleanBackendRestApi.model.StepTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenderingController {

    @Autowired
    StepTimerController stepTimerController;

    @GetMapping("/stepTimer/_render")
    public String render(Model model){
         model.addAttribute("timers",stepTimerController.getTimers().values());
        return "index";
    }
}
