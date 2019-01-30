package com.awk.featr.controllers;

import com.awk.featr.configuration.RepositoryConfiguration;
import com.awk.featr.services.FeatureFileService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;


@CrossOrigin(origins = "*")
@RestController
public class IndexController {

    @Value("${featr.version}")
    private String version;

    private RepositoryConfiguration repositoryConfiguration;
    private FeatureFileService featureFileService;

    @Autowired
	public IndexController(RepositoryConfiguration config, FeatureFileService featureFileService) {
        this.repositoryConfiguration = requireNonNull(config);
        this.featureFileService = requireNonNull(featureFileService);
	}

	@GetMapping("/version")
	public @ResponseBody String version() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"version()");

		JSONObject versionJson = new JSONObject();
		versionJson.put("version", version);
		return versionJson.toString();
	}

	@GetMapping("/index")
    public @ResponseBody String index() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"index()");
        featureFileService.readFeatureFiles(repositoryConfiguration);

        JSONObject returnJson = new JSONObject();
        returnJson.put("result", true);
        return returnJson.toString();
    }
}
