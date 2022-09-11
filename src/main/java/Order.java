public class Order {

    static String firstName;
    static String lastName;
    static String address;
    static int metroStation;
    static String phone;
    static int rentTime;
    static String deliveryDate;
    static String comment;
    static String color;

    public Order() {
    }

    public Order(String firstName, String lastName, String address, int metroStation,
                 String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        Order.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        Order.lastName = lastName;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Order.address = address;
    }

    public static int getMetroStation() {
        return metroStation;
    }

    public static void setMetroStation(int metroStation) {
        Order.metroStation = metroStation;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Order.phone = phone;
    }

    public static int getRentTime() {
        return rentTime;
    }

    public static void setRentTime(int rentTime) {
        Order.rentTime = rentTime;
    }

    public static String getDeliveryDate() {
        return deliveryDate;
    }

    public static void setDeliveryDate(String deliveryDate) {
        Order.deliveryDate = deliveryDate;
    }

    public static String getComment() {
        return comment;
    }

    public static void setComment(String comment) {
        Order.comment = comment;
    }

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        Order.color = color;
    }
}


