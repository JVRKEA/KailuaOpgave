package App;

import Car.Car;
import Car.Rental;
import Car.Customer;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kailua_car_rental";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "JVRKEA";

    private Connection conn;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Main app = new Main();
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Main() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Kailua Car Rental ---");
            System.out.println("1. List Cars");
            System.out.println("2. List Customers");
            System.out.println("3. List Rentals");
            System.out.println("4. Insert Car");
            System.out.println("5. Insert Customer");
            System.out.println("6. Insert Rental");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            try {
                switch (choice) {
                    case 1:
                        listCars();
                        break;
                    case 2:
                        listCustomers();
                        break;
                    case 3:
                        listRentals();
                        break;
                    case 4:
                        insertCar();
                        break;
                    case 5:
                        insertCustomer();
                        break;
                    case 6:
                        insertRental();
                        break;
                    case 0:
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listCars() throws SQLException {
        String sql = "SELECT * FROM car";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("car_id"),
                        rs.getString("registration_number"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("fuel_type"),
                        rs.getDate("first_registration"),
                        rs.getInt("odometer_km"),
                        rs.getInt("car_type_id")
                );
                System.out.println(car);
            }
        }
    }

    private void listCustomers() throws SQLException {
        String sql = "SELECT * FROM customer";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("zip"),
                        rs.getString("city"),
                        rs.getString("mobile_phone"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("driver_license_number"),
                        rs.getDate("driver_since")
                );
                System.out.println(customer);
            }
        }
    }

    private void listRentals() throws SQLException {
        String sql = "SELECT * FROM rental";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Rental rental = new Rental(
                        rs.getInt("rental_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("car_id"),
                        rs.getTimestamp("from_datetime"),
                        rs.getTimestamp("to_datetime"),
                        rs.getInt("max_km"),
                        rs.getInt("start_odometer")
                );
                System.out.println(rental);
            }
        }
    }

    private void insertCar() throws SQLException {
        System.out.print("Registration number: ");
        String reg = scanner.nextLine();
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Fuel type: ");
        String fuel = scanner.nextLine();
        System.out.print("First registration (yyyy-mm-dd): ");
        String firstReg = scanner.nextLine();
        System.out.print("Odometer km: ");
        int odo = Integer.parseInt(scanner.nextLine());
        System.out.print("Car type id (1=Luxury, 2=Family, 3=Sport): ");
        int typeId = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO car (registration_number, brand, model, fuel_type, first_registration, odometer_km, car_type_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reg);
            ps.setString(2, brand);
            ps.setString(3, model);
            ps.setString(4, fuel);
            ps.setDate(5, Date.valueOf(firstReg));
            ps.setInt(6, odo);
            ps.setInt(7, typeId);
            ps.executeUpdate();
            System.out.println("Car inserted.");
        }
    }

    private void insertCustomer() throws SQLException {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Zip: ");
        String zip = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Mobile phone: ");
        String mobile = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Driver licence number: ");
        String dlNumber = scanner.nextLine();
        System.out.print("Driver since (yyyy-mm-dd): ");
        String driverSince = scanner.nextLine();

        String sql = "INSERT INTO customer (name, address, zip, city, mobile_phone, phone, email, driver_license_number, driver_since) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, zip);
            ps.setString(4, city);
            ps.setString(5, mobile);
            ps.setString(6, phone);
            ps.setString(7, email);
            ps.setString(8, dlNumber);
            ps.setDate(9, Date.valueOf(driverSince));
            ps.executeUpdate();
            System.out.println("Customer inserted.");
        }
    }

    private void insertRental() throws SQLException {
        String createRentalSql = "CREATE TABLE IF NOT EXISTS rental (" +
                "rental_id INT AUTO_INCREMENT PRIMARY KEY," +
                "customer_id INT," +
                "car_id INT," +
                "from_datetime DATETIME," +
                "to_datetime DATETIME," +
                "max_km INT," +
                "start_odometer INT," +
                "FOREIGN KEY (customer_id) REFERENCES customer(customer_id)," +
                "FOREIGN KEY (car_id) REFERENCES car(car_id))";
        try (Statement st = conn.createStatement()) {
            st.execute(createRentalSql);
        }

        System.out.print("Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Car ID: ");
        int carId = Integer.parseInt(scanner.nextLine());
        System.out.print("From date & time (yyyy-mm-dd hh:mm:ss): ");
        String fromDT = scanner.nextLine().trim();
        System.out.print("To date & time (yyyy-mm-dd hh:mm:ss): ");
        String toDT = scanner.nextLine().trim();
        System.out.print("Max km: ");
        int maxKm = Integer.parseInt(scanner.nextLine());
        System.out.print("Start odometer: ");
        int startOdo = Integer.parseInt(scanner.nextLine());

        if (fromDT.length() == 16) {
            fromDT += ":00";
        }
        if (toDT.length() == 16) {
            toDT += ":00";
        }

        String sql = "INSERT INTO rental (customer_id, car_id, from_datetime, to_datetime, max_km, start_odometer) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.setInt(2, carId);
            ps.setTimestamp(3, Timestamp.valueOf(fromDT));
            ps.setTimestamp(4, Timestamp.valueOf(toDT));
            ps.setInt(5, maxKm);
            ps.setInt(6, startOdo);
            ps.executeUpdate();
            System.out.println("Rental created.");
        }
    }
}