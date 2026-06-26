package com.structurax.service;

import com.structurax.dto.ProjectMemberRequest;
import com.structurax.entity.Project;
import com.structurax.entity.ProjectMember;
import com.structurax.entity.User;
import com.structurax.repository.ProjectMemberRepository;
import com.structurax.repository.ProjectRepository;
import com.structurax.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class ProjectMemberService {

    private final ProjectMemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectMemberService(
            ProjectMemberRepository memberRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository) {

        this.memberRepository = memberRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ProjectMember addMember(
            ProjectMemberRequest request) {

        Project project =
                projectRepository.findById(
                        request.getProjectId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project Not Found"));

        User user =
                userRepository.findById(
                        request.getUserId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User Not Found"));

        ProjectMember member =
                new ProjectMember();

        member.setProject(project);
        member.setUser(user);
        member.setRole(request.getRole());

        return memberRepository.save(member);
    }

    public ProjectMember updateMemberRole(
            Long memberId,
            String role) {

        ProjectMember member =
                memberRepository.findById(memberId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Member Not Found"));

        member.setRole(role);

        return memberRepository.save(member);
    }

    public void removeMember(Long memberId) {

        ProjectMember member =
                memberRepository.findById(memberId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Member Not Found"));

        memberRepository.delete(member);
    }
}