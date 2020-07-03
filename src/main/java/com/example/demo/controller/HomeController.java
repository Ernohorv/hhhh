package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.LocationStats;
import com.example.demo.services.DataService;

@Controller
public class HomeController {
	
	@Autowired
	DataService service;

	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = service.getAllStats();
		int totalReports = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReported", totalReports);
		logger.trace("Home method accessed");
		return "home";
	}
}
