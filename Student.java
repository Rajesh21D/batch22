class Student{
      private int id;
      private String name;
      private int age;
      private Department department;
      private MyDate birthDate;

     public Student(int id,String name,int age,Department department,MyDate myDate){
               this.id=id;
               this.name=name;
               this.age=age;
               this.department=department;
               this.birthDate=myDate;
}
     public void setId(int id){
          this.id=id;
    }
     public int getId(){
           return id;
}
      public void setName(String name){
           this.name=name;
}
     public String getName(){
           return name;
}
public  void setAge(int age){
         this.age=age;
}
public  int getAge(){
         return age;
}
public void setDepartment(Department department){
           this.department=department;
}
public Department getDepartment(){
        return department;

}

      public void setBirthDate(MyDate date){
            this.birthDate=date;
}
    public MyDate getBirthDate(){
           return birthDate;
}

}
