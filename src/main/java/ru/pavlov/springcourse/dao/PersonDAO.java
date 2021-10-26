package ru.pavlov.springcourse.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pavlov.springcourse.models.Person;

import javax.print.attribute.standard.PresentationDirection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int PEOPLE_COUNT;

//    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "postgre";
//
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public List<Person> index(){
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while(resultSet.next()){
//                Person person = new Person();
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//
//                people.add(person);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return people;

        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)/*new PersonMapper()*/);
    }

    public Person show(int id){

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)/*new PersonMapper()*/)
                .stream().findAny().orElse(null);

//        Person person = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeQuery();
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            person = new Person();
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setAge(resultSet.getInt("age"));
//            person.setEmail(resultSet.getString("email"));
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
///*        Person resultPerson = null;
//        for (int i = 0; i < people.size(); i++){
//            if (id == people.get(i).getId()){
//                resultPerson =  people.get(i);
//                break;
//            }
//        }
//        return resultPerson;*/
////        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//        return person;
    }

    public void save(Person person){


//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person updatedPerson){

        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
        //        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
//
//            preparedStatement.setString(1, updatedPerson.getName());
//            preparedStatement.setInt(2, updatedPerson.getAge());
//            preparedStatement.setString(3, updatedPerson.getEmail());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    public void delete(int id){

        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

/*        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/


    }

}
