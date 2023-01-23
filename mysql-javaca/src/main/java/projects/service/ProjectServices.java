package projects.service;

import projects.entity.Project;
import provided.util.DaoBase;




public class ProjectServices extends DaoBase{
	
	private ProjectDao projectDao = new ProjectDao();
	
	
	
	
	
	
	
	
	
	public Project addProject(Project project) {
		// TODO Auto-generated method stub
		return projectDao.insertProject(project);;
	}

}
