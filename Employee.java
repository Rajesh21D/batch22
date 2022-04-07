class Employee{
          private int id;
          private String name;
          private int salary;
  public void setId( int employeeId){
            id=employeeId;
}
  public int getId(){
          return id;
}
public void setName( String employeeName){
            name=employeeName;
}
  public String getName(){
          return name;
}
public void setSalary( int employeeSalary){
            salary=employeeSalary;
}
  public int getSalary(){
          return salary;
}

   public static void main(String args[]){
      Employee employee= new Employee();
        employee.setId(66458);
         employee.setName("Rajesh");
         employee.setSalary(25000);

         System.out.println(employee.getId());
          System.out.println(employee.getName());
          System.out.println(employee.getSalary());
}
}
  
  