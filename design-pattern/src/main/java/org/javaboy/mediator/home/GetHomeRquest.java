package org.javaboy.mediator.home;

/**
 * @author majin.wj
 * @date 2023/6/28 10:06 PM
 * @desc
 */
public class GetHomeRquest {

    int room; //房间数量

    int restroom; //厕所

    int hiveRoom; //客厅


    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getRestroom() {
        return restroom;
    }

    public void setRestroom(int restroom) {
        this.restroom = restroom;
    }

    public int getHiveRoom() {
        return hiveRoom;
    }

    public void setHiveRoom(int hiveRoom) {
        this.hiveRoom = hiveRoom;
    }
}
