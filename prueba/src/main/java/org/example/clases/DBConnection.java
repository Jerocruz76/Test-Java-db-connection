package org.example.clases;

import java.sql.*;

public class DBConnection {
    private static final String URL= "jdbc:mysql://127.0.0.1:3306/school";
    private static final String USER = "root";
    private static final String password = "Rlwl2023.";
    Connection connection = null;

    public void connect(){
        try {
            connection = DriverManager.getConnection(URL, USER, password);
            System.out.println("conexion arriba");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            if(connection !=null) connection.close();
            System.out.println("conexion abajo");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addStudent(Student student){
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO students (name, lastname, age) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setInt(3, student.getAge());
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("User successfully created");
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement != null)  preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void deleteStudent(int id){
        PreparedStatement preparedStatement = null;
        try{
            String sql = "DELETE FROM students WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1 , id);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("student successfully deleted");
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement != null)  preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void ShowStudents(){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.createStatement();
            String sql = "SELECT * FROM students";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                System.out.println("id: " + id + " name: " + name + " lastname: " + lastname + " age: " + age);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void ShowByID(int id){
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String sql = "SELECT * FROM students WHERE id = ?";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int ID = resultSet.getInt("id");
                if (id == ID) {
                    int id_Student = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String lastname = resultSet.getString("lastname");
                    int age = resultSet.getInt("age");
                    System.out.println("id: " + id_Student + " name: " + name + " lastname: " + lastname + " age: " + age);
                }
            }
            System.out.println("student successfully deleted");
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(statement != null)  statement.close();
                if (resultSet != null) resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
