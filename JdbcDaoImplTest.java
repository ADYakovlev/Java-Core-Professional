import Task03.dao.DAO;
import Task03.model.JdbcDaoImpl;
import Task03.model.Student;
import org.junit.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 *@author Yakovlev Alexandr
 */
public class JdbcDaoImplTest {
    private static Connection connection;
    private static DAO dao;
    private static final URL ="jdbc:sqlite:test.db";

    @BeforeClass
    public static void initDB() throws SQLException {
        connection = DriverManager.getConnection(URL);
        dao = new JdbcDaoImpl(connection);
    }

    @Before
    public void initTable() throws SQLException {
        dao.createTable(App.TABLE_NAME);
    }

    @Test
    public void insertStudentTest() throws SQLException {
        final Student student = new Student(
                "Test1",
                1
        );
        dao.insertStudent(student);
        Assert.assertEquals(student, getStudentById(1));
    }
    @Test
    public void updateStudentTest() throws SQLException{
        final int id = 1;
        final Student student = new Student(
                id,
                "Test",
                1
        );
        dao.insertStudent(student);
        student.setFIO("Test2");
        student.setScope(5);
        dao.updateStudent(student);
        Assert.assertEquals(student,getStudentById(id));
    }
    @Test
    public void getStudentByIdTest()throws SQLException{
        final Student student = new Student(
                "Test",
                1
        );
        dao.insertStudent(student);
        Assert.assertNotNull(dao.getStudentByID(1));
    }

    private Student getStudentById(int id) throws SQLException{
        return dao.getStudentByID(id);
    }

    @Before
    public void clearDB()throws SQLException{
        dao.deleteTable(App.TABLE_NAME);
    }
    @AfterClass
    public static void end() throws SQLException{
        connection.close();
    }
}
