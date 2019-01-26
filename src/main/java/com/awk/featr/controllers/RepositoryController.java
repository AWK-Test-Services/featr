package com.awk.featr.controllers;

import com.awk.featr.configuration.RepositoryConfiguration;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.awk.featr.services.RepositoryException;
import com.awk.featr.services.RepositoryService;

@CrossOrigin(origins = "*")
@RestController
public class RepositoryController {

    private RepositoryService repoService;
    private RepositoryConfiguration config;

    @Autowired
	public RepositoryController(RepositoryConfiguration config, RepositoryService repoSvc ) {
        this.config = config;
        this.repoService = repoSvc;
	}

    @GetMapping("/clone")
    public @ResponseBody String cloneRepository() {
        JSONObject statusJson = new JSONObject();

        try {
            repoService.cloneRepository( config );
            statusJson.put("result", "OK");
        } catch (RepositoryException e) {
            e.printStackTrace();
            statusJson.put("message", e.getMessage());
            statusJson.put("result", "NOK");
        }

        return statusJson.toString();
    }

    @GetMapping("/delete")
	public @ResponseBody String deleteRepository() {
        JSONObject statusJson = new JSONObject();

        try {
            repoService.deleteRepository( config );
            statusJson.put("result", "OK");
        } catch (RepositoryException e) {
            e.printStackTrace();
            statusJson.put("message", e.getMessage());
            statusJson.put("result", "NOK");
        }
        return statusJson.toString();
	}
}
