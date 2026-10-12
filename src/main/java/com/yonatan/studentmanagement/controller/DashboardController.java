package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.dto.DashboardStats;
import com.yonatan.studentmanagement.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard/stats")
    public DashboardStats getStats() {
        return dashboardService.getStats();
    }
}