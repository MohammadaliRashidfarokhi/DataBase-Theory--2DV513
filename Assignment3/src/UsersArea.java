import java.sql.*;
import java.util.Scanner;

public class UsersArea {
    private static Scanner scanner;

    public static void switcher(Scanner scan) throws SQLException {
        scanner = new Scanner(System.in);

        int answer = scan.nextInt();

        switch (answer){
            case 1:
                System.out.println("Please Enter Your Name: ");
                scan.nextLine();
                String userName = scan.nextLine();
                System.out.println("Please Enter Your Status: ");

                String userStatus = scan.nextLine();
                registerUser(userName,userStatus);
                break;

            case 2:
                System.out.println("All existing users and their hobbies.");
                showFriends();

                break;
            case 3:
                System.out.println("See all existing users who have more than 2 friends.");
                friendsWith2Friends();
                break;
            case 4:
                System.out.println("User's friends of friends ");
                System.out.println("Please write user's name: ");
                String friendName = scanner.nextLine();
                showFriendsOfFriendsSpecificUser(friendName);
                break;
            case 5:
                System.out.println("Most Popular user ( Has most friends) ");
                showMustPopularUser();
                break;
            case 6:
                System.out.println("See People you may know ");
                System.out.println("Please write your name: ");

                String name = scanner.nextLine();
                suggestFriends(name);
                break;
            case 7:
                System.out.println("See People you may know (According to their hobby) ");

               suggestFriendsHobby();
                break;
            case 0:
                    System.exit(0);
                    break;
            default:
                System.out.println("Please choose from the provided options");


        }

    }

    public static boolean registerUser(String userName, String stat) throws SQLException {

        int id = 0;
        String name= null;
        int total = 0;

        if(checkUsername(userName)){
            System.out.println("Registering user failed, the user Already exist");
        }
        else {
            String query = "INSERT INTO `users`(`name`, `status`) VALUES (?,?)";

            try (Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/metaAPI", "root", "root");
                 PreparedStatement pstmt = conn.prepareStatement(query,
                         Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, userName);
                pstmt.setString(2, stat);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {

                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            System.out.println(userName + " Has been Registered");

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                     query = "SELECT *    FROM hobbies";
                    Statement st =   conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);

                    // execute the query, and get a java resultset
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next())
                    {
                         id = rs.getInt("id");
                         name = rs.getString("hobby_name");

                        System.out.println(id + " - " + name);
                        if (rs.isLast()){
                        total = id;
                        }



                    }


                    query = "select  id from users where  `name` =? ";
                    PreparedStatement ps;
                    ps  =  MyConnection.getConnection().prepareStatement(query);
                    ps.setString(1, userName);
                    rs = ps.executeQuery();
                    if(rs.next())
                    {
                        id = rs.getInt("id");




                    }
                    registerUserHobby(total,id,userName);

                }

            } catch (SQLException ex) {

            }
        }

        return true;
    }

    private static boolean registerUserHobby(int AllHobbies, int id, String userName) throws SQLException {
        System.out.println("Please select what hobby suits you the best");
        Scanner scanner1 = new Scanner(System.in);


        int answer = scanner1.nextInt();
        System.out.println(answer);
        if (answer > AllHobbies) {
            System.out.println("Pleas select from the provided hobbies");
        }


        String query = "INSERT INTO `hobbies_users`(`user_name`, `hobby_name`) VALUES (?,?)";

        PreparedStatement ps = MyConnection.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.setInt(2, answer);

        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            System.out.println(userName + " Has been Registered");
        }
        return true;
    }
    public static boolean checkUsername(String username) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM `users` WHERE `name` =?";

        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if(rs.next())
            {

                checkUser = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return checkUser;
    }

    public static boolean showFriends() throws SQLException {

        Statement  statement;
        ResultSet rs;
        Connection conn = null;
        boolean checkUser = false;
        String query = "SELECT name, hob.hobby_name\n" +
                "FROM hobbies_users\n" +
                "         JOIN hobbies as hob\n" +
                "              ON hobbies_users.hobby_name = hob.id\n" +
                "         JOIN users as us\n" +
                "              ON user_name = us.id\n" +
                "ORDER BY us.name, hob.hobby_name";


            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaAPI", "root", "root");
            statement= conn.createStatement();
            rs = statement.executeQuery(query);


        while(rs.next())
            {
                String Name = rs.getString("name");
                String Hobby = rs.getString("hobby_name");
                System.out.println("Name: " + Name+" , Hobby Name: " +Hobby);

                checkUser = true;
            }


        return checkUser;
    }
    public static boolean friendsWith2Friends() throws SQLException {

        Statement  statement;
        ResultSet rs;
        Connection conn = null;
        boolean checkUser = false;
        String query = "SELECT users.name, COUNT(friendship.user2_id) as BFF\n" +
                "FROM friendship\n" +
                "         JOIN users\n" +
                "              ON users.id = friendship.user2_id\n" +
                "GROUP BY users.name\n" +
                "HAVING BFF > 1\n" +
                "ORDER BY users.name";


        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaAPI", "root", "root");
        statement= conn.createStatement();
        rs = statement.executeQuery(query);


        while(rs.next())
        {
            String Name = rs.getString("name");
            String Hobby = rs.getString("BFF");
            System.out.println("Name: " + Name+" , Antal Friends: " +Hobby);

            checkUser = true;
        }

        return checkUser;
    }

    public static void showFriendsOfFriendsSpecificUser(String friendName) throws SQLException {


        PreparedStatement ps;
        int id = returnID(friendName);

        ResultSet rs;


        if (id > 0){
            String query = "SELECT us.name AS KFL\n" +
                    "FROM friendship\n" +
                    "         INNER JOIN users as us\n" +
                    "                    ON us.id = friendship.user2_id\n" +
                    "WHERE friendship.user2_id\n" +
                    "          IN\n" +
                    "      (SELECT user2_id\n" +
                    "       FROM friendship\n" +
                    "       WHERE `user1_id`  = ? )\n" +
                    "GROUP BY user2_id";

            ps  =  MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1,id);


            rs = ps.executeQuery();
            while (rs.next())
            {


                System.out.println(rs.getString("KFL"));



            }
        }


    }
    public static boolean showMustPopularUser() throws SQLException {


        Statement  statement;
        ResultSet rs;
        Connection conn = null;
        boolean checkUser = false;
        String query = "SELECT * from mustPopularUser";


        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaAPI", "root", "root");
        statement= conn.createStatement();
        rs = statement.executeQuery(query);


        while(rs.next())
        {
            String Name = rs.getString("name");
            String Hobby = rs.getString("Popular");
            System.out.println("Name: " + Name+" , Popularity (Number of friends): " +Hobby);

            checkUser = true;
        }


        return checkUser;
    }

    public static void suggestFriendsHobby() throws SQLException {
        int cou= 0;
        System.out.println("Please Write Your Name");
        String name = scanner.nextLine();
        PreparedStatement ps;
        int id = 0;

        ResultSet rs;


        String queryGetID = "select  id from users where  name =? ";
        ps  =  MyConnection.getConnection().prepareStatement(queryGetID);
        ps.setString(1, name);
        rs = ps.executeQuery();
        if(rs.next())
        {
            id = rs.getInt("id");




        }
        String query = "SELECT name\n" +
                "FROM users\n" +
                "WHERE id IN\n" +
                "      (SELECT user2_id\n" +
                "       FROM friendship\n" +
                "       WHERE user1_id = ?)\n" +
                "  AND id IN\n" +
                "      (SELECT user_name\n" +
                "       FROM hobbies_users\n" +
                "       WHERE hobby_name IN\n" +
                "             (SELECT hobby_name\n" +
                "              FROM hobbies_users\n" +
                "              WHERE  user_name  = ?))";

        ps  =  MyConnection.getConnection().prepareStatement(query);
        ps.setInt(1,id);
        ps.setInt(2,id);

        rs = ps.executeQuery();
        while (rs.next())
        {

            cou++;


            System.out.println(rs.getString("name"));



        }
        if(cou <=0){
            System.out.println("Sorry we could not find any match! ");
        }



    }
    private static void suggestFriends(  String name) throws SQLException {

        int counter = 0;
        PreparedStatement ps;
        int id = returnID(name);
        ResultSet rs;

        if (id > 0){
            String query = "SELECT name\n" +
                    "FROM users\n" +
                    "WHERE id NOT IN\n" +
                    "      (SELECT user2_id\n" +
                    "       FROM friendship\n" +
                    "       WHERE user1_id = ?)\n" +
                    "  AND id IN\n" +
                    "      (SELECT user2_id\n" +
                    "       FROM friendship\n" +
                    "       WHERE user1_id IN\n" +
                    "             (SELECT user2_id\n" +
                    "              FROM friendship\n" +
                    "              WHERE user1_id = ?))\n" +
                    "  AND id != ?";

            ps  =  MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1,id);
            ps.setInt(2,id);
            ps.setInt(3,id);

            rs = ps.executeQuery();
            while (rs.next())
            {


                System.out.println(rs.getString("name"));



            }

            if(rs.next()){
                counter++;
                if(counter <=0){
                    System.out.println("Sorry we could not find any match! ");
                }
            }
        }
        else {
            System.out.println("Please check the User name");
        }

    }


    private static int returnID(String name) throws SQLException {
        PreparedStatement ps;
        int id = 0;

        ResultSet rs;


        String queryGetID = "select  id from users where  `name` =? ";
        ps  =  MyConnection.getConnection().prepareStatement(queryGetID);
        ps.setString(1, name);
        rs = ps.executeQuery();
        if(rs.next())
        {
        id =    rs.getInt("id");
        return id;


        }
        return -1;
    }
}
