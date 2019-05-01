package com.ipnx.ipnxmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ipnx.ipnxmobile.models.responses.login.InternetService;
import com.ipnx.ipnxmobile.models.responses.login.ServiceAddress;
import com.ipnx.ipnxmobile.models.responses.login.TelephonyService;

import java.util.List;

public class Profile implements Parcelable
{
    private String mobile;
    private String lastName;
    private String address;
    private long id;
    private String company;
    private String phoneNumbers;
    private long success;
    private Object emailAddress;
    private String customerStatus;
    private String due;
    private String night;
    private String phoneNumber;
    private String customerStatusColor;
    private String fullName;
    private String message;
    private ServiceAddress serviceAddress;
    private String emailAddresses;
    private String balance;
    private String type;
    private String addressFull;
    private String customerNumber;
    private String firstName;

    public final static Creator<Profile> CREATOR = new Creator<Profile>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(int size) {
            return (new Profile[size]);
        }

    }
            ;

    protected Profile(Parcel in) {
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.company = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumbers = ((String) in.readValue((String.class.getClassLoader())));
        this.success = ((long) in.readValue((long.class.getClassLoader())));
        this.emailAddress = ((Object) in.readValue((Object.class.getClassLoader())));
        this.customerStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.due = ((String) in.readValue((String.class.getClassLoader())));
        this.night = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.customerStatusColor = ((String) in.readValue((String.class.getClassLoader())));
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceAddress = ((ServiceAddress) in.readValue((ServiceAddress.class.getClassLoader())));
        this.emailAddresses = ((String) in.readValue((String.class.getClassLoader())));
        this.balance = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.addressFull = ((String) in.readValue((String.class.getClassLoader())));
        this.customerNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Profile() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public long getSuccess() {
        return success;
    }

    public void setSuccess(long success) {
        this.success = success;
    }

    public Object getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(Object emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerStatusColor() {
        return customerStatusColor;
    }

    public void setCustomerStatusColor(String customerStatusColor) {
        this.customerStatusColor = customerStatusColor;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServiceAddress getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(ServiceAddress serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mobile);
        dest.writeValue(lastName);
        dest.writeValue(address);
        dest.writeValue(id);
        dest.writeValue(company);
        dest.writeValue(phoneNumbers);
        dest.writeValue(success);
        dest.writeValue(emailAddress);
        dest.writeValue(customerStatus);
        dest.writeValue(due);
        dest.writeValue(night);
        dest.writeValue(phoneNumber);
        dest.writeValue(customerStatusColor);
        dest.writeValue(fullName);
        dest.writeValue(message);
        dest.writeValue(serviceAddress);
        dest.writeValue(emailAddresses);
        dest.writeValue(balance);
        dest.writeValue(type);
        dest.writeValue(addressFull);
        dest.writeValue(customerNumber);
        dest.writeValue(firstName);
    }

    public int describeContents() {
        return 0;
    }

}