package com.awk.featr.controllers;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.model.TestLevel;
import com.awk.featr.model.TestType;
import com.awk.featr.services.FeatureFileService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;


@CrossOrigin(origins = "*")
@RestController
public class IndexController {

    @Value("${featr.version}")
    private String version;

    private FeatrConfiguration configuration;
    private FeatureFileService featureFileService;

    @Autowired
	public IndexController(FeatrConfiguration config, FeatureFileService featureFileService) {
        this.configuration = requireNonNull(config);
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
        JSONObject returnJson = new JSONObject();
        try {
            configuration.getRepositories()
                    .forEach((id, featureConfig) -> featureFileService.readFeatureFiles(featureConfig));
            returnJson.put("result", true);
        } catch (Exception e) {
            returnJson.put("result", false);
            returnJson.put("message", e.getMessage());
        }
        return returnJson.toString();
    }

    @GetMapping("/types")
    public @ResponseBody List<TestType> types() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"types()");

        return configuration.getTestTypes();
    }

    @GetMapping("/levels")
    public @ResponseBody List<TestLevel> levels() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"levels()");

        return configuration.getTestLevels();
    }
}
