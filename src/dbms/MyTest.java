package dbms;
import static org.junit.Assert.*;

import org.junit.Test;



public class MyTest {

	Dbms d = new impDBMS();

	@Test
	public void test1() {
		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("create database test"));
		assertEquals(Dbms.PARSING_ERROR, d.input("createdatabase test;"));

		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("create databasetest;"));
		assertEquals(Dbms.Con_DB, d.input("create database Test;"));

		assertEquals(Dbms.PARSING_ERROR, d.input("CREATETABLE test (test1 int,test2 varchar(255),test3 int);"));

		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("CREATE TABLEtest (test1 int,test2 varchar(255),test3 int);"));
		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("CREATE TABLE test test1 int,test2 varchar(255),test3 int);"));
		
		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("CREATE TABLE test ( test1 int,test2varchar(255),test3 int)"));
		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("CREATE TABLE test ( test1 int,test2 varchar(255),test3 int)"));
		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("CREATE TABLE test ( test1 int,test2 varchar(255)test3 int)"));
		assertEquals(
				Dbms.Con_Table,
				d.input("CREATE TABLE test ( test1 int,test2 varchar(255),test3 int);"));
		assertEquals(
				Dbms.TABLE_ALREADY_EXISTS,
				d.input("CREATE TABLE test ( test1 int,test2 varchar(255),test3 int);"));
		assertEquals(
				Dbms.COLUMN_NOT_FOUND,
				d.input("INSERT INTO test (test1,test2,test4)VALUES (1,'Mohamed','4');"));
		assertEquals(
				Dbms.TABLE_NOT_FOUND,
				d.input("INSERT INTO tests (test1,test2,test3)VALUES (1,'Mohamed','4');"));
		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("INSERT INTO tests (test1,test2,test3)VALUES (1,'Mohamed','4')"));

	}@Test
	public void test2() {
	
		assertEquals(
				Dbms.TABLE_NOT_FOUND,
				d.input("INSERT INTO y (test0,test1,test2)VALUES (1, 45,'mahmoud');"));
		assertEquals(
				Dbms.Con_Table,
				d.input("CREATE TABLE Testing(test0 int,test1 varchar(255),test2 varchar(255),test3 varchar(255),test4 varchar(255));"));
		assertEquals(Dbms.Con_Delete, d.input("Delete * from testing;"));
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO testing (test0,test1,test2,TEST3,test4) VALUES (1,'Mohamed','Ali','11 street','Tanta');"));
		assertEquals(
				Dbms.COLUMN_TYPE_MISMATCH,
				d.input("INSERT INTO testing (test0,test1,test2,TEST3,test4) VALUES ('a','Mohamed','Ali','11 street','Tanta');"));
		assertEquals(
				Dbms.Con_Update,
				d.input("UPDATE testing SET test1='ahmed', test3='done' WHERE test0= 1;"));

		assertEquals(Dbms.NOT_MATCH_CRITERIA,
				d.input("DELETE FROM testing WHERE test1='Ahmed';"));

		assertEquals(Dbms.Con_Delete,
				d.input("DELETE FROM testing WHERE test1='ahmed';"));
		assertEquals(
				Dbms.Con_Table,
				d.input("CREATE TABLE trial(ID int,Name varchar(255),Job varchar(255),Scho0l varchar(255),Age int);"));
		
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO testing (test0,test1,test2,TEST3,test4) VALUES (1,'Mohamed','Ali','11 street','Tanta');"));
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO testing (test0,test1,test2,TEST3,test4) VALUES (2,'yaser','masry','15 street','alex');"));

		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO testing (test0,test1,test2,TEST3,test4) VALUES (3,'amr','yussef','17 street','cairo');"));


	}
	@Test
	public void test3(){
		assertEquals(
				"1  Mohamed  Ali  11 street  Tanta",
				d.input("select * from testing where test0=1  ;"));
		
		assertEquals(
				"2  yaser  masry  15 street  alex  \n3  amr  yussef  17 street  cairo",
				d.input("select * from testing where test0> 1;"));
	
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO testing (test0,test1,test2,TEST3,test4) VALUES (4,'omar','kareem','19 street','suiz');"));

		assertEquals(
				"4  omar  kareem  19 street  suiz  \n3  amr  yussef  17 street  cairo  \n2  yaser  masry  15 street  alex  \n1  Mohamed  Ali  11 street  Tanta",
				d.input("select * from testing order by test0 DESC ;"));
		assertEquals(
				"Mohamed  \nyaser  \namr  \nomar",
				d.input("select test1 from testing ;"));
		assertEquals(
				"Mohamed  \nyaser  \namr  \nomar",
				d.input("select test1 from testing ;"));
		assertEquals(
				"yussef  \nkareem",
				d.input("select test2 from testing where test0>2 ;"));
		assertEquals(
				"1  Mohamed  Ali  11 street  Tanta  \n2  yaser  masry  15 street  alex  \n3  amr  yussef  17 street  cairo  \n4  omar  kareem  19 street  suiz",
				d.input("select * from testing order by test0 asc ;"));
	
		
	}
	
	
	

}
