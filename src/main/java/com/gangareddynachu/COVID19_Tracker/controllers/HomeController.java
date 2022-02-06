package com.gangareddynachu.COVID19_Tracker.controllers;

import com.gangareddynachu.COVID19_Tracker.models.LocationStats;
import com.gangareddynachu.COVID19_Tracker.services.Covid19DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    Covid19DataService covid19DataService;

    @GetMapping("/")
    public String home(Model model)
    {

        List<LocationStats> allStats = covid19DataService.getAllStats();
       int totalReportedCases= allStats.stream().mapToInt(stat ->stat.getLatestTotalCases()).sum();
       int totalnewcases =allStats.stream().mapToInt(stat ->stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationstats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalnewcases);
        return "home";
    }


}
