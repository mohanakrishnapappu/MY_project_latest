package com.structurax.controller;

import com.structurax.entity.Document;
import com.structurax.service.DocumentService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(
            DocumentService documentService) {

        this.documentService = documentService;
    }

    @PostMapping(
        value = "/upload/{projectId}",
        consumes = "multipart/form-data"
)
public Document uploadDocument(

        @PathVariable Long projectId,

        @RequestParam String category,

        @RequestPart("file")
        MultipartFile file) {

    return documentService.uploadDocument(
            projectId,
            category,
            file
    );
}

    @GetMapping("/{id}")
    public Document getDocument(
            @PathVariable Long id) {

        return documentService.getDocument(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Document> getProjectDocuments(
            @PathVariable Long projectId) {

        return documentService
                .getDocumentsByProject(projectId);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable Long id) {

        Document document =
                documentService.getDocument(id);

        Resource resource =
                documentService.downloadDocument(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + document.getOriginalFileName()
                                + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public String deleteDocument(
            @PathVariable Long id) {

        documentService.deleteDocument(id);

        return "Document Deleted Successfully";
    }
}