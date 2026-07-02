package com.structurax.design.generator;

import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.design.model.GeneratedLayout;

public interface LayoutGenerationService {

    GeneratedLayout generate(ProjectRequestDTO request);

}