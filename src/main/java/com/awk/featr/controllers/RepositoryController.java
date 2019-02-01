package com.awk.featr.controllers;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.configuration.RepositoryConfiguration;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.awk.featr.services.RepositoryException;
import com.awk.featr.services.RepositoryService;

@CrossOrigin(origins = "*")
@RestController
public class RepositoryController {

    private RepositoryService repoService;
    private FeatrConfiguration configuration;

    @Autowired
	public RepositoryController(FeatrConfiguration config, RepositoryService repoSvc ) {
        this.configuration = config;
        this.repoService = repoSvc;
	}

    @GetMapping("/clone")
    public @ResponseBody String cloneRepository(@RequestParam("repository") String repoId) {
        JSONObject statusJson = new JSONObject();
        try {
            RepositoryConfiguration repoConfig = configuration.getRepository(repoId);
            repoService.cloneRepository(repoConfig);
            statusJson.put("result", true);
        } catch (RepositoryException e) {
            statusJson.put("result", false);
            statusJson.put("message", e.getMessage());
            e.printStackTrace();
        }

        return statusJson.toString();
    }

    @GetMapping("/delete")
	public @ResponseBody String deleteRepository(@RequestParam("repository") String repoId) {
        JSONObject statusJson = new JSONObject();

        try {
            RepositoryConfiguration repoConfig = configuration.getRepository(repoId);
            repoService.deleteRepository( repoConfig );
            statusJson.put("result", true);
        } catch (RepositoryException e) {
            e.printStackTrace();
            statusJson.put("result", false);
            statusJson.put("message", e.getMessage());
        }

        return statusJson.toString();
	}
}
