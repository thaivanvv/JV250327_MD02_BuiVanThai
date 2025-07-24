package PlayerManagement;

import Model.Player;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    public List<Player> getPlayer(){
        List<Player> players = new ArrayList<>();
        Connection conn = ConnectionDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("call getListPlayer()");
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                Player player = new Player();
                player.setPlayerId(rs.getInt("playerId"));
                player.setFullName(rs.getString("fullName"));
                player.setEmail(rs.getString("email"));
                player.setPhoneNumber(rs.getString("phoneNumber"));
                player.setRegister(rs.getDate("registerDate").toLocalDate());
                player.setStatus(rs.getBoolean("status"));

                players.add(player);
            }

            return players;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        } finally{
            ConnectionDB.closeConnection(conn);
        }
    }

    public void addPlayer(Player player){
        Connection conn = ConnectionDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("call addPlayer(?,?,?)");
            call.setString(1,player.getFullName());
            call.setString(2,player.getEmail());
            call.setString(3,player.getPhoneNumber());
            call.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn);
        }
    }

    public void updatePlayer(Player player){
        Connection conn = ConnectionDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("call updatePlayer(?,?,?,?,?,?)");
            call.setInt(1,player.getPlayerId());
            call.setString(2,player.getFullName());
            call.setString(3,player.getEmail());
            call.setString(4,player.getPhoneNumber());
            call.setDate(5, Date.valueOf(player.getRegister()));
            call.setBoolean(6,Boolean.valueOf(player.getStatus()));
            call.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public void deletePlayer(int id){
        Connection conn = ConnectionDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("call deletePlayer(?)");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public Player searchPlayerByName(String name){
        Connection conn = ConnectionDB.getConnection();
        Player player;
        try{
            CallableStatement call = conn.prepareCall("call searchPlayerByName(?)");
            call.setString(1,name);
            ResultSet rs = call.executeQuery();
            if(rs.next()){
                player = new Player();
                player.setPlayerId(rs.getInt("playerId"));
                player.setFullName(rs.getString("fullName"));
                player.setEmail(rs.getString("email"));
                player.setPhoneNumber(rs.getString("phoneNumber"));
                player.setRegister(rs.getDate("registerDate").toLocalDate());
                player.setStatus(rs.getBoolean("status"));
                return player;
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return null;
    }

    public Player searchPlayerById(int id){
        Connection conn = ConnectionDB.getConnection();
        Player player;
        try{
            CallableStatement call = conn.prepareCall("call searchPlayerById(?)");
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            if(rs.next()){
                player = new Player();
                player.setPlayerId(rs.getInt("playerId"));
                player.setFullName(rs.getString("fullName"));
                player.setEmail(rs.getString("email"));
                player.setPhoneNumber(rs.getString("phoneNumber"));
                player.setRegister(rs.getDate("registerDate").toLocalDate());
                player.setStatus(rs.getBoolean("status"));
                return player;
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return null;
    }
}
