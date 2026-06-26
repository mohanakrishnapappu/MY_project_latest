package com.structurax.service;

import com.structurax.dto.WindowRequest;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Window;
import com.structurax.repository.DesignProjectRepository;
import com.structurax.repository.WindowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WindowService {

    private final WindowRepository windowRepository;
    private final DesignProjectRepository designProjectRepository;

    public WindowService(
            WindowRepository windowRepository,
            DesignProjectRepository designProjectRepository) {

        this.windowRepository = windowRepository;
        this.designProjectRepository = designProjectRepository;
    }

    public Window createWindow(WindowRequest request) {

        DesignProject project =
                designProjectRepository
                        .findById(request.getDesignProjectId())
                        .orElseThrow();

        Window window = new Window();

        window.setWindowType(request.getWindowType());
        window.setPositionX(request.getPositionX());
        window.setPositionY(request.getPositionY());
        window.setWidth(request.getWidth());

        window.setDesignProject(project);

        return windowRepository.save(window);
    }

    public List<Window> getWindows(Long designProjectId) {
        return windowRepository.findByDesignProjectId(designProjectId);
    }

    public void deleteWindow(Long id) {
        windowRepository.deleteById(id);
    }
}