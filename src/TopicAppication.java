import model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TopicAppication {

    private static JdbcImpl jdbc = new JdbcImpl();
    static Topic topic = new Topic();
    static Scanner scanner = new Scanner(System.in);

    public static void insertTopic(){
        System.out.print("Enter Name : ");
        topic.setName(scanner.nextLine());
        System.out.print("Enter Description : ");
        topic.setDescription(scanner.nextLine());
        topic.setStatus(true);
        insert(topic);
    }

    public static void insert(Topic topic){
        try (Connection connection = jdbc.dataSource().getConnection()){
            String insertSql = "INSERT INTO topics(name, description, status) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.setString(1,topic.getName());
            statement.setString(2,topic.getDescription());
            statement.setBoolean(3,topic.getStatus());
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectTopic(){
        try (Connection connection = jdbc.dataSource().getConnection()) {
            String selectSql = "SELECT * FROM topics ORDER BY id ASC ";
            PreparedStatement statement = connection.prepareStatement(selectSql);
            ResultSet resultSet = statement.executeQuery();
            List<Topic> topics = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Boolean status = resultSet.getBoolean("status");
                topics.add(new Topic(id, name, description, status));
            }
            topics.forEach(System.out::println);
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateTopic(){
        System.out.print("Input ID : ");
        topic.setId(scanner.nextInt());
        System.out.print("Input Name : ");
        scanner.nextLine();
        topic.setName(scanner.nextLine());
        System.out.print("Input Description : ");
        topic.setDescription(scanner.nextLine());
        System.out.print("Input Status : ");
        topic.setStatus(scanner.nextBoolean());
        update(topic);
    }
    public static void update(Topic topic){
        try (Connection connection= jdbc.dataSource().getConnection()){
            String update = "UPDATE topics SET name=?, description=?, status=? WHERE id="+ topic.getId();
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1,topic.getName());
            statement.setString(2,topic.getDescription());
            statement.setBoolean(3,topic.getStatus());
            int count = statement.executeUpdate();
            System.out.println(count);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void deleteTopic(Integer id){
        try (Connection connection= jdbc.dataSource().getConnection()){
            String dalete = "DELETE FROM topics WHERE id="+id;
            PreparedStatement statement = connection.prepareStatement(dalete);
            int count = statement.executeUpdate();
            if (count==1){
                System.out.println("Delete Success!");
            }else {
                System.out.println("You Delete Wrong ID :(");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void selectID(Integer id){
        try (Connection connection= jdbc.dataSource().getConnection()){
            String selectById = "SELECT * FROM topics WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(selectById);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            Topic topic = new Topic();
            while (resultSet.next()){
                topic.setId(resultSet.getInt("id"));
                topic.setName(resultSet.getString("name"));
                topic.setDescription(resultSet.getString("description"));
                topic.setStatus(resultSet.getBoolean("status"));
            }
            System.out.println(topic);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectName(String name){
        try (Connection connection= jdbc.dataSource().getConnection()){
            String selectByName = "SELECT * FROM topics WHERE name=?";
            PreparedStatement statement = connection.prepareStatement(selectByName);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Topic topic = new Topic();
            while (resultSet.next()){
                topic.setId(resultSet.getInt("id"));
                topic.setName(resultSet.getString("name"));
                topic.setDescription(resultSet.getString("description"));
                topic.setStatus(resultSet.getBoolean("status"));
            }
            System.out.println(topic);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
