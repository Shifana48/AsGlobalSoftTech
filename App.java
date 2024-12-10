package com.refine.Hibernate_crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



//import javax.security.auth.login.Configuration;

public class App 
{
    public static void main( String[] args )
    {
    Configuration con=new Configuration().configure().addAnnotatedClass(Student.class);
    ServiceRegistry reg=new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
    
 SessionFactory sf=con.buildSessionFactory(reg);
 Session session=sf.openSession();
 Transaction tx=session.beginTransaction();
 
 
 //single value storage
 Student stu=new Student();
 stu.setUid(101);
 stu.setUname("Shifa");
 session.save(stu);
 
 //multiple value storage
 List<Student> list=new ArrayList<Student>();
 list.add(new Student(102,"one"));
 list.add(new Student(103,"two"));
 list.add(new Student(104,"three"));
 list.add(new Student(105,"four"));

 for(Student stud:list) {
	 session.save(stud);
 }
 
 //to update one data in query method
 Query query1=session.createQuery("UPDATE Student set uname=:value1 WHERE uid=:value2");
 query1.setParameter("value1","ash");
 query1.setParameter("value2",101);
 int resultList1=query1.executeUpdate();

//to delete one data
 Query query2=session.createQuery("DELETE Student WHERE uid=:value3");
 query2.setParameter("value3", 102);
 int resultList2=query2.executeUpdate();
// 
session.flush();
 session.clear();
 
 //to print user data in normal console
 

Query query=session.createQuery("FROM Student WHERE Uid=:value");
query.setParameter("value", 101);
Student st1=(Student)query.uniqueResult();
System.out.println(st1.getUid()+ "\t" + st1.getUname());

//to print all datas together
Query query11 =session.createQuery("FROM Student");
List<Student>resultList=query11.list();
for(Student st:resultList) {
	 System.out.println(st.getUid()+ "\t" + st.getUname());
}

 //session.update(resultlist1);
 tx.commit();
 session.close();
 sf.close();
 		 }
}
