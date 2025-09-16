package Car;

import java.sql.Date;

public class Customer {
    public int customerId;
    public String name;
    public String address;
    public String zip;
    public String city;
    public String mobilePhone;
    public String phone;
    public String email;
    public String driverLicenseNumber;
    public Date driverSince;

    public Customer(int customerId, String name, String address, String zip, String city,
                    String mobilePhone, String phone, String email, String driverLicenseNumber, Date driverSince) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.email = email;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverSince = driverSince;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public Date getDriverSince() {
        return driverSince;
    }

    @Override
    public String toString() {
        return String.format("ID:%d Name:%s Addr:%s Zip:%s City:%s Mobile:%s DL:%s Since:%s",
                customerId, name, address, zip, city, mobilePhone, driverLicenseNumber, driverSince);
    }
}