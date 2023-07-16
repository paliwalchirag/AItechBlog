/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entities.user;
import java.sql.*;
/**
 *
 * @author 91859
 */
//data base mai data insert karne ke liye yhe class banaya 
public class UserDao {
    private Connection con;
    
    //constructor
    //whenever you want to use this class in another class they have to pass the object of connection then only you can use this class obj
    public UserDao(Connection con){
        this.con=con;
    }
    
    //method to insert user to database
    //                  entiites wala user ka obj
    public boolean saveUser(user user){
        //this is the code to insert data in database
        boolean f=false;
        try{
            
            //code here in such a way the user data is dave to database
            //                          tablename(column name) 
            String query = "insert into user(name,email,password,gender,about) values(?,?,?,?,?)";
            //eith the help of pstmt we can now put value dynamical from user
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getPassword());
            pstmt.setString(4,user.getGender());
            pstmt.setString(5,user.getAbout());
            
            pstmt.executeUpdate();
            f=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
        
    }
    
    //get user by email and password
    public user getUserByEmailAndPassword(String email,String password){
        user useer = null;
        
        try{
            
            String query ="select * from user where email=? and password=? ";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,email);
            pstmt.setString(2,password);
            
            //store the data in set
            ResultSet set = pstmt.executeQuery();
            
            //if their is any data with the provided emai and password set.next() will return true it 
            //mean the user is alredy register in data base 
            if(set.next()){
                
                //store the data in user
                useer =new user();
                String name=set.getString("name");
                useer.setName(name);
                useer.setId(set.getInt("id"));
                useer.setEmail(set.getString("email"));
                useer.setPassword(set.getString("password"));
                useer.setGender(set.getString("gender"));
                useer.setAbout(set.getString("about"));
                useer.setDateTime(set.getTimestamp("rdate"));
                useer.setProfile(set.getString("profile"));
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return useer;
    }
    
     public boolean updateUser(user user) {

        boolean f = false;
        try {

            String query = "update user set name=? , email=? , password=? , gender=? ,about=? , profile=? where  id =?";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, user.getGender());
            p.setString(5, user.getAbout());
            p.setString(6, user.getProfile());
            p.setInt(7, user.getId());

            p.executeUpdate();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
     
     public user getUserByUserId(int userId) {
        user user = null;
        try {
            String q = "select * from user where id=?";
            PreparedStatement ps = this.con.prepareStatement(q);
            ps.setInt(1, userId);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                user = new user();

//             data from db
                String name = set.getString("name");
//             set to user object
                user.setName(name);

                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setDateTime(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
        
}
