package com.inncretech.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

//TODO: Read an environment variable "instanceid" . Append this to user identifier.
//if it is not there default to 1.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-user.xml" })
public class DefaultUserServiceImplJDBCTest implements Runnable {

	@Autowired
	private UserService userService = null;
	@Autowired
	private TestUtil dbUtility = null;
	@Autowired
	private IdGenerator idGenerator = null;

	private int noOfThread = 10;
	private long noOfUsers = 100;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	Thread thread = null;

	@Test
	public void createUser() throws Exception {
		for (int threadCount = 1; threadCount <= 1; threadCount++) {
			thread = new Thread(this, "Create User Thread from [" + threadCount
					+ "]");
			System.out.println("Creating New Thread for Execution....."
					+ thread);
			thread.start();
			thread.join();
		}

	}

	@Before
	public void setUp() {
	  String[] tablesToBeDeleted = {"user"};
		dbUtility.cleanUpdb(tablesToBeDeleted);

	}

	User createTestUser(String fName, String lName, String eMail, String uName,
			String password, String facebookId, String twitterId,
			String googleId) {
		User usr = new User();
		usr.setFirstName(fName);
		usr.setLastName(lName);
		usr.setEmail(eMail);
		usr.setUserName(uName);
		usr.setPassword(password);
		usr.setFacebookId(facebookId);
		usr.setTwitterId(twitterId);
		usr.setGoogleId(googleId);
		return usr;

	}

	UserProfile CreateTestProfile(String shortBio, String longBio) {
		UserProfile usrprf = new UserProfile();
		usrprf.setLongBio(longBio);
		usrprf.setShortBio(shortBio);
		return usrprf;
	}

	@Override
	public void run() {
		try{
			 List list = new ArrayList();
             list.add(0, "jdbc:mysql://localhost:3306/userdb1?");
             list.add(1, "jdbc:mysql://localhost:3306/userdb2?");
             String url = (String) list.get(0);
             System.out.println("URL :::"+url);
             jdbcInsertUserDB(url);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jdbcInsertUserDB(String url) throws Exception {
		try {

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection(url
							+ "user=root&password=root");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into user values (?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			for (int i = 1; i <= 100; i++) {
				System.out.println("The value ="+i);
				preparedStatement.setInt(1, i);
				preparedStatement.setString(2, "Test" + i);
				preparedStatement.setString(3, "Test" + i);
				preparedStatement.setString(4, "Test" + i);
				preparedStatement.setString(5, "Test" + i);
				preparedStatement.setInt(6, 2);
				preparedStatement.setInt(7, 3);
				preparedStatement.setInt(8, 4);
				preparedStatement.setInt(9, 5);
				preparedStatement.setString(10, "Test" + i);
				preparedStatement.setString(11, "Test" + i);
				preparedStatement.setString(12, "Test" + i);
				preparedStatement.setString(13, "Test" + i);
				preparedStatement.setString(14, "Test" + i);

				preparedStatement.executeUpdate();
System.out.println(new Date());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connect.close();
		}
	}
}