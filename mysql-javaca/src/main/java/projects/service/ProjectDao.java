package projects.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projects.dao.DbConnection;
import projects.entity.Project;
import projects.exceptions.DbException;

public class ProjectDao {

	
	private static final String CATEGORTY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";

	
	
		public Project insertProject(Project project) {
		// TODO Auto-generated method stub
			//@formatter:off
			String sql = ""
					+ "INSERT INTO " + PROJECT_TABLE + " "
					+ "(project_name, estimated_hours, difficulty, notes) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)";
			//formatter:off
		 try(Connection conn = DbConnection.getConnection()){
			 startTransaction(conn);
			 
			 try(PreparedStatement stmt = conn.prepareStatement(sql)){
				 setParamater( stmt, 1, project.getProjectName(), String.class);
				 setParamater( stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				 setParamater( stmt, 3, project.getActualHours(), BigDecimal.class);
				 setParamater( stmt, 4, project.getDifficulty(), Integer.class);
				 setParamater( stmt, 5, project.getNotes(), String.class);
				 
				 stmt.executeUpdate();
				 
				 Integer projectId = getLastInsertId = getLastInsertId(conn, PROJECT_TABLE);
				 commitTransaction(conn);
				 project.setProjectId(projectId);
				 return project;
			 }
			  catch (Exception e) {
			// TODO Auto-generated catch block
				  rollbackTransaction(conn);
				  throw new DbException(e);
		 }
		}
		catch (SQLException e) {
			throw new DbException(e);
			 
		}
			 
		
	}
	
	
	
	
}
