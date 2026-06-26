package com.structurax.controller;

import com.structurax.dto.ProjectMemberRequest;
import com.structurax.entity.ProjectMember;
import com.structurax.service.ProjectMemberService;
import com.structurax.dto.MemberRoleUpdateRequest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project-members")
public class ProjectMemberController {

    private final ProjectMemberService service;

    public ProjectMemberController(
            ProjectMemberService service) {

        this.service = service;
    }

    @PostMapping
    public ProjectMember addMember(
            @RequestBody
            ProjectMemberRequest request) {

        return service.addMember(request);
    }

    @PutMapping("/{memberId}/role")
public ProjectMember updateMemberRole(
        @PathVariable Long memberId,
        @RequestBody MemberRoleUpdateRequest request) {

    return service.updateMemberRole(
            memberId,
            request.getRole());
}

@DeleteMapping("/{memberId}")
public String removeMember(
        @PathVariable Long memberId) {

    service.removeMember(memberId);

    return "Member Removed Successfully";
}

}