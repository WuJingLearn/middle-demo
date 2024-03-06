package org.javaboy.mediator.home;

/**
 * @author majin.wj
 * @date 2023/6/28 9:47 PM
 * @desc
 */
public class Home {
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


    public boolean isAdapter(GetHomeRquest request){
        return this.restroom == request.getRestroom() && this.hiveRoom == request.hiveRoom
                && this.room == request.getRoom();
    }

    public GetHomeRquest coverRequest(){
        GetHomeRquest getHomeRquest = new GetHomeRquest();
        getHomeRquest.setRestroom(this.restroom);
        getHomeRquest.setRoom(this.room);
        getHomeRquest.setHiveRoom(this.hiveRoom);
        return getHomeRquest;
    }
}

