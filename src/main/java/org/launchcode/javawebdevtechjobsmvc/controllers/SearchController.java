package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.javawebdevtechjobsmvc.models.JobData.findAll;
import static org.launchcode.javawebdevtechjobsmvc.models.JobData.findByColumnAndValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {

        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);

        ArrayList<Job> jobs = new ArrayList<>();
        if (searchTerm.equals("all") || searchTerm.isEmpty()) {
            jobs = findAll();

        } else {
            jobs = findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }
}
