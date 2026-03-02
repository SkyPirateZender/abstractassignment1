package ManagementSystem;

public class Department{
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