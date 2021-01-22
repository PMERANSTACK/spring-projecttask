package com.projecttaskmanager.projecttaskboard.web;

import com.projecttaskmanager.projecttaskboard.domain.ProjectTask;
import com.projecttaskmanager.projecttaskboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/board")
@CrossOrigin
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("/list")
    public ResponseEntity<?> addpttoBoard(@RequestBody ProjectTask projectTask){
       ProjectTask newPT = projectTaskService.saveorupdateProjectTask(projectTask);
       return new ResponseEntity<ProjectTask>(newPT, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<ProjectTask> getAllPTS(){
        return projectTaskService.findAll();
    }

    @GetMapping("/{pt_id}")
    public ResponseEntity<?> getPTById(@PathVariable Long pt_id){
        ProjectTask projectTask = projectTaskService.findById(pt_id);
        return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
    }

    @DeleteMapping("/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable Long pt_id){
        projectTaskService.delete(pt_id);
        return new ResponseEntity<String>("project Task deleted", HttpStatus.OK);
    }
}
