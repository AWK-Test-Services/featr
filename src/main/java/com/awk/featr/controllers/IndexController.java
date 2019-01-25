package com.awk.featr.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@CrossOrigin(origins = "*")
@RestController
public class IndexController {

    @Value("${featr.version}")
    private String version;

    @Autowired
	public IndexController( ) {
	}

	@GetMapping("/version")
	public @ResponseBody String version() {

//    	return version;
		JSONObject versionJson = new JSONObject();
		versionJson.put("version", version);
		return versionJson.toString();
	}
}
