package ManagementSystem;

/*
    CSC 241 Spring 2026
    Assignment #: 1
    Due Date: Thursday, February 12
    Name: Jace Dunlap
 */

/* TODO:
    [Assignment 1]  Load dataset for company and employees in src/Data/company.txt and src/Data/employees.txt
                    Implement a procedure to print/summarize employess/company
*/

import java.io.*;
import java.nio.file.FileSystems;
import java.util.*;
import java.util.Comparator;
import java.util.Collections;
public class CompangeSys {
    public static void main (String[] args) {
        System.out.println("CompangeSys - Company Management System On");

        // loading company info
        String dataCompany = "company";
        String PathCompany = FileSystems.getDefault().getPath(System.getProperty("user.dir"),"src","Data", dataCompany + ".txt").toAbsolutePath().toString();
        File fileCompany = new File(PathCompany);
        System.out.println("Company Info Has Been Loaded.");

        // loading employees info
        String dataEmployees = "employees";
        String pathEmployees = FileSystems.getDefault().getPath(System.getProperty("user.dir"),"src","Data", dataEmployees + ".txt").toAbsolutePath().toString();
        File fileEmployees = new File(pathEmployees);
        System.out.println("Employee Info Is Ready.");

        // data structure - table for employees
        int numEmployees = 8, numEntries = 7;
        String[][] employees = new String[numEmployees][numEntries+1];

        try{
            BufferedReader breader = new BufferedReader(new FileReader(pathEmployees));
            String line;
            List<String[]> entry = new ArrayList<>();
            while((line = breader.readLine()) != null) {
                String[] employeeData = line.split(", ");
                entry.add(employeeData);
            }
            breader.close();
            for(int index = 0; index < entry.size(); index++) {
                employees[index] = entry.get(index);
            }


        } catch (IOException e) {
            System.out.println("Breader Exception " + e.toString() + "\n Line 46");
            System.exit(0);
        }
        for (String[] row : employees) {
            //   System.out.println(Arrays.toString(row));
        }
        /*
            TODO: Implement a procedure to load data about company and employees
        */
        class Department{
            String deptName;
            int deptCount;

            public Department(String deptName, int deptCount){
                this.deptName = deptName;
                this.deptCount = deptCount;
            }

            public String getdeptName() { return deptName;}
            public int getdeptCount() { return deptCount;}

            @Override
            public String toString() {
                return deptName + ", " + deptCount;
            }
        }
    // both applications for employees and summary run after project is built, displaying both in separate tabs sequentially
        if (args[0].equals("employees")) {
        //    System.out.println("[TODO] You need to print the information of employees");


            printEmployees(employees);
        }
        else if (args[0].equals("summary")) {
       //     System.out.println("[TODO] You need to summarize the company and departments");

            summarizeCompany(employees);
        }
        else {
            System.out.println("You Need to Enter a Proper Argument");
        }
    }
    // TODO: these formatted printing method should be used
    // print the EMPLOYEES table
    public static String[][] printEmployees (String[][] employees) {
        System.out.printf("%-30.28s %-20.18s %-6s %-20.18s %-20.18s %-20.18s %-5s\n", "Name", "Title", " ", "Year", "Department", "Skills", "Points");
        /*
        TODO: print employee data in a table
        */
        //importing buffered reader to pull file from src
        try (BufferedReader employeeReader = new BufferedReader(new FileReader ("src/Data/employees.txt"))) {
            //array list reading from txt file
            List<String[]> dataList = new ArrayList<>();
            //new variable line to hold data from .txt and read from loop
            String line;

            //while loop starts when file is not equal to null
            while ((line = employeeReader.readLine()) != null) {
                String[] employDetails = line.split(",\\s*");
                //splits data from commas
                dataList.add(employDetails);
                //reads out data in println nested in loop

            }
            for (String[] employeeRow : dataList) {
                System.out.printf("%-30.28s %-20.18s %-6s %-20.18s %-20.18s %-20.18s %-5s\n", (Object[]) employeeRow);
            }
            return dataList.toArray(new String[0][]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // print the SUMMARY of the company
        public static void summarizeCompany (String[][] employees) {
            System.out.println("[SUMMARY]");
            ArrayList<String> depts = new ArrayList<String>();
            try (BufferedReader companyReader = new BufferedReader(new FileReader ("src/Data/company.txt"))) {

                //variable line to hold data from .txt and read from loop
                String line;

                List<String[]> dataList = new ArrayList<>();

                while ((line = companyReader.readLine()) != null) {
                    String[] companyDetails = line.split(":");
                    dataList.add(companyDetails);
                    if (companyDetails[0].trim().equals("List of Departments") && companyDetails.length > 1) {

                        String deptLine = companyDetails[1].trim();
                        depts = new ArrayList<>(Arrays.asList(deptLine.split(",\\s*|\\s+and\\s+")));

                    }

                }
                for (String[] companyRow : dataList) {
                    System.out.printf("%-30.28s %-4s\n", (Object[]) companyRow);
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(" ");
            System.out.printf("%-20.18s %-4s\n", "Departments", "Number of Employees");





            for (int i = 0; i < depts.size(); i++) {
                System.out.printf("%-20s %d%n", depts.get(i),
                        employeeCounter(employees, depts.get(i)));
            }
    }

    public static int employeeCounter(String[][] employees, String deptName) {
        int deptCount = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i][4].equals(deptName)) {
                deptCount++;
            }
        }
        return deptCount;
    }
        /*
        TODO: summarize company (company/department info, table of the number of employees in each department)
            Read the description carefully!
        */
    }