package Task03.model;

import Task03.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *@author Yakovlev Alexandr
 */
public class JdbcDaoImpl implements DAO {
    private Connection connection;

    public JdbcDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable(String tableName) throws SQLException {
        String sqlStatement = "CREATE TABLE IF NOT EXIST %s\n" +
                "(\n" +
                " ID integer\n" +
                " primary key\n" +
                " FIO text not null,\n" +
                " SCOPE TEXT\n" +
                ");\n";
        sqlStatement = String.format(sqlStatement,tableName);
        final PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteTable(String tableName) throws SQLException {
        String sqlStatement = "DROP TABLE IF EXIST %s";
        sqlStatement = String.format(sqlStatement, tableName);
        final PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void insertStudent(Student student) throws SQLException {
        String sqlStatement = "INSERT INTO STUDENT (FIO, SCOPE) VALUES{?,?}";
        final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setString(1, student.getFIO());
        statement.setInt(2, student.getScope());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Student getStudentByID(int ID) throws SQLException {
        String sqlStatement = "SELECT ID, FIO, SCOPE FROM STUDENT WHERE ID = ?";
        final PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setInt(1,ID);
        statement.execute();
        final ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        final Student student = new Student(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getInt(3));
        statement.close();
        return student;
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        String sqlStatememnt = "IPDATE STUDENT SET FIO = ?, SCOPE = ? WHERE ID = ?";
        final PreparedStatement statement = connection.prepareStatement(sqlStatememnt);
        statement.setString(1,student.getFIO());
        statement.setInt(2,student.getScope());
        statement.setInt(3,student.getId());
        statement.executeUpdate();
        statement.close();
    }
}
