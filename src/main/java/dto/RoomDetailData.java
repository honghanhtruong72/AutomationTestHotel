package dto;

public class RoomDetailData {
    private int roomIndex;
    private int count;
    private double priceOneNight;
    private String roomType;

    public RoomDetailData(int roomIndex, int count, double priceOneNight, String roomType) {
        this.roomIndex = roomIndex;
        this.count = count;
        this.priceOneNight = priceOneNight;
        this.roomType = roomType;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getCount() {
        return count;
    }

    public double getPriceOneNight() {
        return priceOneNight;
    }
}
