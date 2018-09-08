package Task03.dao;

import Task03.model.Student;

import java.sql.SQLException;

/*
 *@author Yakovlev Alexandr
 */
public interface DAO {
    void createTable(String tableName)throws SQLException;
    void deleteTable(String tableName)throws SQLException;
    void insertStudent(Student student)throws SQLException;
    Student getStudentByID(int ID)throws SQLException;
    void updateStudent(Student student)throws SQLException;
}
