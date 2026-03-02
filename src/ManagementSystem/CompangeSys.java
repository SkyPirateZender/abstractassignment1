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
       // System.out.printf("%-20.18s %-4s\n", fileCompany);


        try (BufferedReader companyReader = new BufferedReader(new FileReader ("src/Data/company.txt"))) {

            //variable line to hold data from .txt and read from loop
            String line;

            List<String[]> dataList = new ArrayList<>();

            while ((line = companyReader.readLine()) != null) {
                String[] companyDetails = line.split(":");
                dataList.add(companyDetails);


            }
            for (String[] companyRow : dataList) {
                System.out.printf("%-30.28s %-4s\n", (Object[]) companyRow);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" ");
        System.out.printf("%-20.18s %-4s\n", "Department", "Number of Employees");

        Map<String, Integer> departmentNumber = new HashMap<>();

        try (BufferedReader dpCounter = new BufferedReader(new FileReader("src/Data/employees.txt"))) {
            String line;
            while((line = dpCounter.readLine()) != null) {
                String department = line.trim();
                departmentNumber.put(department, departmentNumber.getOrDefault(department, 0) + 1);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        String[][] employeeCount = new String[departmentNumber.size()][7];
        int i = 0;
        for (Map.Entry<String, Integer> entry : departmentNumber.entrySet()) {
            employeeCount[i][4] = entry.getKey();
            employeeCount[i][5] = String.valueOf(entry.getValue());
            i++;
        }
        System.out.printf("%-20.18s %-4s\n", employeeCount);
        for (String[] row:employeeCount) {
            System.out.println(row[0]);
        }


//        List<Department> departmentCount = new ArrayList<>();
//        try {
//            Scanner departmentScanner = new Scanner(new File("src/Data/employees.txt"));
//            while (departmentScanner.hasNextLine()) {
//                String line = departmentScanner.nextLine();
//                String[] departmentTotal = line.split(",");
//                if (departmentTotal.length == 4) {
//                    String deptName = departmentTotal[4];
//                    int deptCount = Integer.parseInt(departmentTotal[4]);
//                    departmentCount.add(new Department(deptName, deptCount));
//                }
//            }departmentScanner.close();
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//Collections.sort(departmentCount, Comparator.comparingInt(Department::getdeptCount));
//
//        for(Department department:departmentCount) {
//            System.out.println(department);
//        }System.out.close();
//
//        try {
//            PrintWriter write = new PrintWriter(new FileWriter("src/Data/employees.txt"));
//            for (Department department:departmentCount) {
//    System.out.println(department);
//            }
//            }catch (IOException e) {
//            e.printStackTrace();
//        }




//        List<String> departmentsCount = new ArrayList<>();
//
//            Map<String, Integer> totalEmployee = new LinkedHashMap<>();
//            for (String value : departmentsCount) {
//                totalEmployee.put(value, totalEmployee.getOrDefault(value, 0) + 1);
//            }
//
//
//        try {
//            Scanner companyScan = new Scanner(new File("src/Data/employees.txt"));
//
//            while (companyScan.hasNextLine()) {
//                String line = companyScan.nextLine();
//                String dept = companyScan.nextLine().trim();
//                if (!dept.isEmpty()) {
//                    totalEmployee.put(dept, 0);
//                }
//                String[] value = line.split(",");
//
//                if (value.length > 4) {
//                    departmentsCount.add(value[4]);
//                }
//            } companyScan.close();
//
//            Scanner employeeScanner = new Scanner(new File("src/Data/employees.txt"));
//            while (employeeScanner.hasNextLine()) {
//                String line = employeeScanner.nextLine();
//                String[] parts = line.split(",");
//                if (parts.length > 1) {
//                    String deptName = parts[1].trim();
//                    if (totalEmployee.containsKey(deptName)) {
//                        totalEmployee.put(deptName, totalEmployee.get(deptName) + 1);
//                    }
//                }
//            }
//            employeeScanner.close();
//
//            totalEmployee.forEach((dept, count) -> {
//                System.out.printf("%-20s %d%n", dept, count);
//            });
//        }  catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//

     //  String[] departmentCount = getData("src/Data/employees.txt", 4);
//        for (String department : departmentCount)
//        System.out.printf("%-20.18s %-4s\n", department, totalEmployee);
    }

        /*
        TODO: summarize company (company/department info, table of the number of employees in each department)
            Read the description carefully!
        */
    }



/*
public class EmployeeCounter {
    public static void main(String[] args) {
        // Map to store: Department Name -> Employee Count
        Map<String, Integer> deptCounts = new LinkedHashMap<>();

        try {
            // 1. Read Departments file to initialize the list
            Scanner deptScanner = new Scanner(new File("departments.txt"));
            while (deptScanner.hasNextLine()) {
                String dept = deptScanner.nextLine().trim();
                if (!dept.isEmpty()) {
                    deptCounts.put(dept, 0);
                }
            }
            deptScanner.close();

            // 2. Read Employees file and increment counts
            Scanner empScanner = new Scanner(new File("employees.txt"));
            while (empScanner.hasNextLine()) {
                String line = empScanner.nextLine();
                // Assuming format: EmployeeName,DepartmentName
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    String deptName = parts[1].trim();
                    if (deptCounts.containsKey(deptName)) {
                        deptCounts.put(deptName, deptCounts.get(deptName) + 1);
                    }
                }
            }
            empScanner.close();

            // 3. Display formatted output
            System.out.printf("%-20s %s%n", "Departments", "# of Employees");
            deptCounts.forEach((dept, count) -> {
                System.out.printf("%-20s %d%n", dept, count);
            });

        } catch (FileNotFoundException e) {
            System.err.println("Error: One of the files was not found.");
        }
    }
 */
