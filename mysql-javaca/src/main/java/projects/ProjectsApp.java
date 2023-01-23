package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.dao.DbConnection;
import projects.entity.Project;
import projects.exceptions.DbException;
import projects.service.ProjectServices;

public class ProjectsApp {
	ProjectServices projectServices = new ProjectServices();

	private Scanner scanner = new Scanner(System.in);
	
	//@formatter:on
	private List<String> operations = List.of(
			"1) Add a project"
			);
	
	//@formatter:off	

	public static void main(String[] args) {
		new ProjectsApp().processUserSelections();
		
	}

	private void processUserSelections() {
		// TODO Auto-generated method stub
		boolean done = false;
		while(!done) {
			try {
				int selection = getUserSelections();
				switch (selection) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createProject();
					
					
				default:
					System.out.println(selection + " is not a valid option. Try again.");
				}
			} catch (Exception e) {
				System.out.println("Error; " + e.toString() + " Try Again");
			}
		}
		}

	
		private void createProject() {
		// TODO Auto-generated method stub
			String name = getStringInput("Enter the project name");
			BigDecimal estimatedHours = getDecimalInput("Enter estimated hours");
			BigDecimal actualHours = getDecimalInput("Enter the actual hours");
			Integer difficuly = getIntInput("Enter the project difficulty 1-5");
			String notes = getStringInput("Enter project notes");
			Project project = new Project();
			
			project.setProjectName(name);
			project.setEstimatedHours(estimatedHours);
			project.setActualHours(actualHours);
			project.setDifficulty(difficuly);
			project.setNotes(notes);
			Project dbProject = projectServices.addProject(project);
			
	}

		private boolean exitMenu() {
			
			System.out.println("Exiting the menu");
			return true;
		
	}

	private int getUserSelections() {
		// TODO Auto-generated method stub
		PrintOperations();
		Integer input = getIntInput("Enter a menu selesction");
		return Objects.isNull(input) ? -1 : input;
	}

	

	private Integer getIntInput(String prompt) {
		// TODO Auto-generated method stub
String input= getStringInput(prompt);
		
		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + "This is not a valid option");
		}
		
	
		
	}

	private String getStringInput(String prompt) {
		// TODO Auto-generated method stub
		System.out.println(prompt + ": ");
		String line = scanner.nextLine();
		
		return line.isBlank() ? null : line.trim();
		
	}

	private BigDecimal getDecimalInput(String prompt) {
		String input= getStringInput(prompt);
		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + "This is not a valid option");
		}
		
	}
	private void PrintOperations() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("/nThese are the available selections. Press the Enteer key to quit");
		operations.forEach(line -> System.out.println(" " + line));
		
	
		
	}

}
