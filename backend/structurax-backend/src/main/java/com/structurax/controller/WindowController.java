package com.structurax.controller;

import com.structurax.dto.WindowRequest;
import com.structurax.entity.Window;
import com.structurax.service.WindowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/windows")
public class WindowController {

    private final WindowService windowService;

    public WindowController(WindowService windowService) {
        this.windowService = windowService;
    }

    @PostMapping
    public Window createWindow(
            @RequestBody WindowRequest request) {

        return windowService.createWindow(request);
    }

    @GetMapping("/design/{designProjectId}")
    public List<Window> getWindows(
            @PathVariable Long designProjectId) {

        return windowService.getWindows(designProjectId);
    }

    @DeleteMapping("/{id}")
    public String deleteWindow(
            @PathVariable Long id) {

        windowService.deleteWindow(id);

        return "Window Deleted Successfully";
    }
}