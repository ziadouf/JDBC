package dbms;
import static org.junit.Assert.*;

import org.junit.Test;


public class Dbmstest {

	Dbms d = new impDBMS(); // need to be initialized by the implemented class
	//
	//
	//
	// Please Make sure a data base and a schema doesn't already exist in the
	// workspace folder 
	//(Running twice or another junit test will create a data base and a schema file which will affect 
	//the error msg no database found)
	//
	//
	//
	//

	@Test
	public void test_1() {
		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("CREATE TABLE Persons PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255);"));
		assertEquals(Dbms.PARSING_ERROR, d.input("CRETE DATABASE LAB3;"));

		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("INSERT Persons (PersonID,LastName,FirstName,MiddleName)VALUES (1,'Mohamed','Tamer','Ali');"));

		assertEquals(Dbms.TABLE_NOT_FOUND, d.input("DELETE FROM Persons;"));

		assertEquals(Dbms.PARSING_ERROR, d.input("SELECT *  Customers;"));

		assertEquals(
				Dbms.PARSING_ERROR,
				d.input("UPDATE Customers  ContactName='Alfred Schmidt', City='Hamburg' WHERE CustomerName='Alfreds Futterkiste'; "));

	}

	@Test
	public void test_2() {
		//
		//
		//
		// Please Make sure a data base and a schema doesn't already exist in the
		// workspace folder 
		//(Running twice or another junit test will create a data base and a schema file which will affect 
		//the error msg no database found)
		assertEquals(
				Dbms.DB_NOT_FOUND,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
		assertEquals(Dbms.Con_DB, d.input("CREATE DATABASE LAB3;"));
		assertEquals(
				Dbms.TABLE_NOT_FOUND,
				d.input("INSERT INTO O (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));

		assertEquals(
				Dbms.Con_Table,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));
		assertEquals(
				Dbms.TABLE_ALREADY_EXISTS,
				d.input("CREATE TABLE Persons(PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));"));

		assertEquals(
				Dbms.COLUMN_NOT_FOUND,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,MiddleName)VALUES (1,'Mohamed','Tamer','Ali');"));

		assertEquals(
				Dbms.COLUMN_TYPE_MISMATCH,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));

		assertEquals(
				Dbms.TABLE_NOT_FOUND,
				d.input("INSERT INTO O (PersonID,LastName,FirstName)VALUES (1, 23,'Tamer');"));

	}

	@Test
	public void test_3() {
		assertEquals(Dbms.Con_Delete, d.input("Delete * from persons;"));
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (1,'Mohamed','Ali','11 street','Tanta');"));
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO Persons (PersonID,LastName,FirstName,address,city) VALUES (2,'Mohamed','Tamer','11 street','Alexandria');"));
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO Persons VALUES (3,'Ahmad','Mohsen','12 street','Cairo');"));
		assertEquals(
				Dbms.Con_insert,
				d.input("INSERT INTO Persons VALUES (4,'Bassem','Yasser','43 street','Banha');"));

		assertEquals(
  				"1  Mohamed  Ali  11 street  Tanta  \n2  Mohamed  Tamer  11 street  Alexandria  \n3  Ahmad  Mohsen  12 street  Cairo  \n4  Bassem  Yasser  43 street  Banha",
  				d.input("select * from Persons ;"));
  
  		assertEquals(
  				"2  Mohamed  Tamer  11 street  Alexandria  \n3  Ahmad  Mohsen  12 street  Cairo  \n4  Bassem  Yasser  43 street  Banha",
  				d.input("select * from Persons where PersonID > 1 ;"));

  		assertEquals(
  				"1  Mohamed  Ali  11 street  Tanta  \n2  Mohamed  Tamer  11 street  Alexandria",
  				d.input("select * from Persons where LastName='Mohamed' ;"));
  
  		assertEquals(
  				"Mohsen",
  				d.input("select FirstName from Persons where Address='12 street' ;"));

		assertEquals(
				Dbms.Con_Update,
				d.input("UPDATE persons SET Lastname='Salem', City='Hamburg' WHERE personid= 1;"));

		assertEquals("Ali",
				d.input("select FirstName from Persons where City='Hamburg' ;"));

		assertEquals("1  Salem  Ali  11 street  Hamburg",
				d.input("select * from Persons where City='Hamburg' ;"));

		assertEquals(Dbms.NOT_MATCH_CRITERIA,
				d.input("DELETE FROM persons WHERE lastname='salem' ;"));

		assertEquals(Dbms.Con_Delete,
				d.input("DELETE FROM persons WHERE lastname='Salem' ;"));

		assertEquals(Dbms.NOT_MATCH_CRITERIA,
				d.input("select * from persons where City='Hamburg' ;"));

	}
}