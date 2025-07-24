package Service;

import Model.Player;
import PlayerManagement.PlayerManager;

import javax.swing.text.DateFormatter;
import java.text.Format;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlayerService {
    private final PlayerManager playerManager = new PlayerManager();
    public List<Player> getListPlayer(){
        return playerManager.getPlayer();
    }

    public void addPlayer(Scanner sc){
        Player player = new Player();
        List<Player> players = playerManager.getPlayer();
        player.setPlayerId(0);

        System.out.println("Nhập tên của bạn: ");
        while(true) {
            String name = sc.nextLine();
            if(name.isEmpty()) {
                System.out.println("Tên không được để trống !");
            } else {
                player.setFullName(name);
                break;
            }
        }

        System.out.println("Nhập Email của bạn: ");
        while(true){
            String email = sc.nextLine();
            boolean rs = Pattern.matches("^[a-zA-Z0-9!#$%]+@gmail.com$",email);


            if(email.isEmpty()) {
                System.out.println("Email không được để trống !");
            }else if(!rs){
                    System.out.println("Nhập dữ liệu sai!");
            } else {
                boolean found = false;
                for(Player p : players){
                    if(p.getEmail().equals(email)){
                        found = true;
                        break;
                    }
                }

                if(found){
                    System.out.println("Email đã tồn tại !");
                } else{
                    player.setEmail(email);
                    break;
                }
            }
        }

        System.out.println("Nhập sdt của bạn: ");
        while(true){
            String phoneNumber = sc.nextLine();
            boolean rs = Pattern.matches("^[0-9]{10}$",phoneNumber);

            if(phoneNumber.isEmpty()){
                System.out.println("không được để trống !");
            } else if(!rs){
                System.out.println("Nhập sai dữ liệu !");
            } else {
                player.setPhoneNumber(phoneNumber);
                break;
            }
        }

        player.setRegister(null);
        player.setStatus(null);

        playerManager.addPlayer(player);
    }

    public void updatePlayer(Scanner sc){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Player> players = playerManager.getPlayer();
        System.out.println("Nhập Id player cần sửa: ");
        Player player = playerManager.searchPlayerById(Integer.parseInt(sc.nextLine()));

        System.out.println("Nhập tên mới: ");
        while(true) {
            String name = sc.nextLine();
            if(name.isEmpty()) {
                System.out.println("Tên không được để trống !");
            } else {
                player.setFullName(name);
                break;
            }
        }

        System.out.println("Nhập Email mới: ");
        while(true){
            String email = sc.nextLine();
            boolean rs = Pattern.matches("^[a-zA-Z0-9!#$%]+@gmail.com$",email);


            if(email.isEmpty()) {
                System.out.println("Email không được để trống !");
            }else if(!rs){
                System.out.println("Nhập dữ liệu sai!");
            } else {
                boolean found = false;
                for(Player p : players){
                    if(p.getEmail().equals(email)){
                        found = true;
                        break;
                    }
                }

                if(!found){
                    System.out.println("Email đã tồn tại !");
                } else{
                    player.setEmail(email);
                    break;
                }
            }
        }

        System.out.println("Nhập sdt mới: ");
        while(true){
            String phoneNumber = sc.nextLine();
            boolean rs = Pattern.matches("^[0-9]{10}$",phoneNumber);

            if(phoneNumber.isEmpty()){
                System.out.println("không được để trống !");
            } else if(!rs){
                System.out.println("Nhập sai dữ liệu !");
            } else {
                player.setPhoneNumber(phoneNumber);
                break;
            }
        }

        System.out.println("Nhập ngày sửa(dd-MM-yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(),fmt);
        player.setRegister(date);

        System.out.println("Nhập trạng thái mới(true/false): ");
        player.setStatus(Boolean.parseBoolean(sc.nextLine()));

        playerManager.updatePlayer(player);
    }

    public void deletePlayer(Scanner sc){
        System.out.println("Nhập Id player cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());

        playerManager.deletePlayer(id);
    }

    public List<Player> sortPlayerByRegisterDate(){
        return playerManager.getPlayer()
                .stream()
                .sorted(Comparator.comparing(Player::getRegister).reversed())
                .toList();
    }

    public Player searchPlayerByName(Scanner sc){
        System.out.println("Nhập tên player cần tìm: ");
        String name = sc.nextLine();
        return playerManager.searchPlayerByName(name);
    }
}
