    package com.structurax.service;

    import com.structurax.dto.TaskRequest;
    import com.structurax.entity.Project;
    import com.structurax.entity.Task;
    import com.structurax.entity.User;
    import com.structurax.exception.ResourceNotFoundException;
    import com.structurax.repository.ProjectRepository;
    import com.structurax.repository.TaskRepository;
    import com.structurax.repository.UserRepository;

    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class TaskService {

        private final TaskRepository taskRepository;
        private final ProjectRepository projectRepository;
        private final UserRepository userRepository;

        public TaskService(
                TaskRepository taskRepository,
                ProjectRepository projectRepository,
                UserRepository userRepository) {

            this.taskRepository = taskRepository;
            this.projectRepository = projectRepository;
            this.userRepository = userRepository;
        }

        public Task createTask(TaskRequest request) {

            Project project =
                    projectRepository.findById(
                            request.getProjectId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Project Not Found"));

            User user =
                    userRepository.findById(
                            request.getAssignedUserId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "User Not Found"));

            Task task = new Task();

            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setPriority(request.getPriority());
            task.setStatus(request.getStatus());
            task.setProgress(request.getProgress());
            task.setStartDate(request.getStartDate());
            task.setDueDate(request.getDueDate());

            task.setProject(project);
            task.setAssignedUser(user);

            return taskRepository.save(task);
        }

        public List<Task> getAllTasks() {
            return taskRepository.findAll();
        }

        public Task getTaskById(Long id) {

            return taskRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Task Not Found"));
        }

        public Task updateTask(
                Long id,
                TaskRequest request) {

            Task task =
                    taskRepository.findById(id)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Task Not Found"));

            Project project =
                    projectRepository.findById(
                            request.getProjectId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Project Not Found"));

            User user =
                    userRepository.findById(
                            request.getAssignedUserId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "User Not Found"));

            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setPriority(request.getPriority());
            task.setStatus(request.getStatus());
            task.setProgress(request.getProgress());
            task.setStartDate(request.getStartDate());
            task.setDueDate(request.getDueDate());

            task.setProject(project);
            task.setAssignedUser(user);

            return taskRepository.save(task);
        }

        public void deleteTask(Long id) {

            Task task =
                    taskRepository.findById(id)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Task Not Found"));

            taskRepository.delete(task);
        }

        public List<Task> getTasksByProject(
                Long projectId) {

            return taskRepository
                    .findByProjectId(projectId);
        }

        public List<Task> getTasksByUser(
                Long userId) {

            return taskRepository
                    .findByAssignedUserId(userId);
        }
    }