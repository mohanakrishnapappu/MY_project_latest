package com.structurax.service;

import com.structurax.entity.Document;
import com.structurax.entity.Project;
import com.structurax.repository.DocumentRepository;
import com.structurax.repository.ProjectRepository;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final ProjectRepository projectRepository;

    public DocumentService(
            DocumentRepository documentRepository,
            ProjectRepository projectRepository) {

        this.documentRepository = documentRepository;
        this.projectRepository = projectRepository;
    }

    public Document uploadDocument(
            Long projectId,
            String category,
            MultipartFile file) {

        try {

            Project project =
                    projectRepository.findById(projectId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Project Not Found"));

            String projectFolder =
                    "uploads/project_" + projectId;

            Path folderPath =
                    Paths.get(projectFolder);

            if (!Files.exists(folderPath)) {

                Files.createDirectories(folderPath);
            }

            String uniqueName =
                    System.currentTimeMillis()
                            + "_"
                            + file.getOriginalFilename();

            Path filePath =
                    folderPath.resolve(uniqueName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            Document document =
                    new Document();

            document.setFileName(uniqueName);

            document.setOriginalFileName(
                    file.getOriginalFilename());

            document.setFileType(
                    file.getContentType());

            document.setDocumentCategory(
                    category);

            document.setFileSize(
                    file.getSize());

            document.setFilePath(
                    filePath.toString());

            document.setUploadedAt(
                    LocalDateTime.now());

            document.setProject(project);

            return documentRepository.save(document);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Document Upload Failed");
        }
    }

    public Document getDocument(Long id) {

        return documentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Document Not Found"));
    }

    public List<Document> getDocumentsByProject(
            Long projectId) {

        return documentRepository
                .findByProjectId(projectId);
    }

    public Resource downloadDocument(
            Long id) {

        try {

            Document document =
                    getDocument(id);

            Path path =
                    Paths.get(
                            document.getFilePath());

            return new UrlResource(
                    path.toUri());

        } catch (MalformedURLException e) {

            throw new RuntimeException(
                    "Download Failed");
        }
    }

    public void deleteDocument(
            Long id) {

        Document document =
                getDocument(id);

        try {

            Files.deleteIfExists(
                    Paths.get(
                            document.getFilePath()));

        } catch (Exception e) {

            throw new RuntimeException(
                    "File Delete Failed");
        }

        documentRepository.delete(document);
    }
}