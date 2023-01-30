package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import projects.dao.ProjectDao;
import projects.entity.Project;
import provided.util.DaoBase;




public class ProjectServices extends DaoBase{
	
	private ProjectDao projectDao = new ProjectDao();
	
	
	
	
	
	
	
	
	
	public Project addProject(Project project) {
		// TODO Auto-generated method stub
		return projectDao.insertProject(project);
	}









	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
		
	}









	public Project fetchProjectById(Integer projectId) {
		
		return projectDao.fetchProjectById(projectId).orElseThrow(() -> new NoSuchElementException(
				"Project with project ID=" + projectId + "does not exist"));
	}

}
