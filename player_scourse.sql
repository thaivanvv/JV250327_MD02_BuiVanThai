create database player_management_db;
use player_management_db;

drop table player;

create table player(
    playerId int auto_increment primary key,
    fullName varchar(100) not null,
    email varchar(100) not null,
    phoneNumber varchar(15) not null,
    registerDate date not null,
    status bit default 1
);

drop procedure updatePlayer;

delimiter //
    create procedure getListPlayer()
    begin
        select * from player;
    end;

    create procedure addPlayer(in_fullName varchar(100), in_email varchar(100), in_phoneNumber varchar(15))
    begin
        insert into player(fullName,email,phoneNumber,registerDate)
            values(in_fullName,in_email,in_phoneNumber,now());
    end;

    create procedure updatePlayer(in_id int, in_fullName varchar(100),in_email varchar(100), in_phoneNumber varchar(15), in_register date, in_status bit )
    begin
        update player
            set fullName = in_fullName, email = in_email, phoneNumber = in_phoneNumber, registerDate = in_register, status = in_status
            where playerId = in_id;
    end;

    create procedure searchPlayerById(in_id int)
    begin
        select * from player where playerId = in_id;
    end;

    create procedure deletePlayer(in_id int)
    begin
        delete from player
            where playerId=in_id;
    end;

    create procedure searchPlayerByName(in_name varchar(100))
    begin
        select * from player
            where fullName like concat('%',in_name,'%');
    end
// delimiter ;