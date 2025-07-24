import Model.Player;
import Service.PlayerService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayerService playerService = new PlayerService();
        int choice = -1;

        while(true){
            System.out.println("""
                    *****************STUDENT MANAGEMENT*****************
                    1. Danh sách người chơi
                    2. Thêm mới người chơi
                    3. Cập nhật người chơi
                    4. Xóa người chơi
                    5. Tìm kiếm n người chơi  theo tên
                    6. Sắp xếp n người chơi theo ngày đăng ký
                    7. Thoát
                    """);
            try{
                System.out.println("Nhập lựa chọn của bạn: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice){
                    case 1: {
                        List<Player> players = playerService.getListPlayer();
                        players.forEach(System.out::println);
                    }
                    break;
                    case 2:
                        playerService.addPlayer(sc);
                        break;
                    case 3:
                        playerService.updatePlayer(sc);
                        break;
                    case 4:
                        playerService.deletePlayer(sc);
                        break;
                    case 5: {
                        Player player = playerService.searchPlayerByName(sc);
                        System.out.println(player);
                    }
                        break;
                    case 6:
                    {
                        List<Player> playerSorted = playerService.sortPlayerByRegisterDate();
                        playerSorted.forEach(System.out::println);
                    }
                    break;
                    case 7:
                        System.out.println("Đã thoát khỏi chương trình.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập dữ liệu sai !");
                        break;
                }
            } catch (NumberFormatException e){
                System.out.println("Nhập sai dữ liệu !");
            }
        }
    }
}