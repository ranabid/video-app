package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.storage.StorageFileNotFoundException;
import com.app.storage.StorageService;

@Controller
public class FileUploadController {

	private static Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	private final StorageService storageService;
	
	@Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

	
	@RequestMapping(path="/upload", method=RequestMethod.POST)
	public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
		LOGGER.info("Inside upload controller");
		storageService.store(file);
		return ResponseEntity.ok("Success");
		
	}
	
	@ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
