package qlsv;

import java.util.Scanner;

public class QLSV {
  public static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {   
	  ShowMenu();
	  sendCommand();
  }
  static void  ShowMenu() {
	  String mnu;;
	  mnu = "\n/********** STUDENT MANAGEMENTS ***********/\n"
	  		+ " + 1. Add student.\n"
	  		+ " + 2. Edit student by id.\n"
	  		+ " + 3. Delete student by id.\n"
	  		+ " + 4. Sort student by gpa.\n"
	  		+ " + 5. Sort student by name.\n"
	  		+ " + 6. Show student.\n"
	  		+ " + 0. Exit.\n"
	  		+ "/******* ENTER A NUMBER TO USE *********/";
	  System.out.println(mnu);
  }
  static void sendCommand() {
	  String strNum;
	  ReadPropertiesFromConfig config = ReadPropertiesFromConfig.getInstance();	
	  MariaConnection maria = new MariaConnection();	    
	  Students students = new Students(maria.getConnection(config.getProperty("host"), config.getProperty("username"), config.getProperty("password")));
      StudentManager studentManager = new StudentManager(students);
      int studentId;
      boolean closing = false;
      do {
    	  strNum = keyboard.nextLine();
		  switch(strNum) {
		  case "0":
			  // Close app
			  closing = true;
			  break;
		  case "1":
			  // Add student
			  System.out.println("Add a student.");
			  studentManager.add();
			  break;
		  case "2":
			  // Edit _
			  studentId = studentManager.inputId();
			  studentManager.edit(studentId);
			  break;
		  case "3":
			  // Delete _
			  studentId = studentManager.inputId();
			  studentManager.delete(studentId);
			  break;
		  case "4":
			  // Sort by gpa_
			  studentManager.SortByGpa();
			  break;
		  case "5":
			  // Sort by name _
			  studentManager.SortByName();
			  break;
		  case "6":
			  // Show list _
			  studentManager.show();
			  break;		
		  default:
			  // default statement
			  System.out.println("Please enter a valid number!");
			  break;
		  }
		  if (closing == true) {
			  break;
		  }
		  ShowMenu();

      }
      while (closing == false);
      
  }

}
