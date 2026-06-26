package com.structurax.controller;

import com.structurax.dto.WallRequest;
import com.structurax.entity.Wall;
import com.structurax.service.WallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/walls")
public class WallController {

    private final WallService wallService;

    public WallController(WallService wallService) {
        this.wallService = wallService;
    }

    @PostMapping
    public Wall createWall(
            @RequestBody WallRequest request) {

        return wallService.createWall(request);
    }

    @GetMapping("/design/{designProjectId}")
    public List<Wall> getWalls(
            @PathVariable Long designProjectId) {

        return wallService.getWalls(designProjectId);
    }

    @DeleteMapping("/{id}")
    public String deleteWall(
            @PathVariable Long id) {

        wallService.deleteWall(id);

        return "Wall Deleted Successfully";
    }
}