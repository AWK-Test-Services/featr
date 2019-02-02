package com.awk.featr.controllers;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.model.TestLevel;
import com.awk.featr.model.TestType;
import com.awk.featr.services.FeatureFileService;
import com.awk.featr.services.IndexingService;
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

    private FeatrConfiguration configuration;

    private final IndexingService indexingService;

    @Autowired
	public IndexController(FeatrConfiguration config, IndexingService indexingService) {
        this.configuration = requireNonNull(config);
        this.indexingService = requireNonNull(indexingService);
	}

	@GetMapping("/version")
	public @ResponseBody String version() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"version()");

		JSONObject versionJson = new JSONObject();
		versionJson.put("version", configuration.getVersion());
		return versionJson.toString();
	}

	@GetMapping("/index")
    public @ResponseBody String index() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"index()");
        indexingService.index();

        JSONObject returnJson = new JSONObject();
        returnJson.put("result", true);
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
