class Test{

     public static void main(String args[]){
             Department d= new Department(10,"Traning");
             MyDate date=new MyDate(03,07,2022);
              Student s= new Student(66458,"D RAJESH",22,d,date);
             System.out.println(s.getId());
             System.out.println(s.getName());
             System.out.println(s.getAge());
             System.out.println(d.getDepartmentName());
             System.out.println(date.getMonth());
}
}
