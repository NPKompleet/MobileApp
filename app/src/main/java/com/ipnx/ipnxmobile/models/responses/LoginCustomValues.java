package com.ipnx.ipnxmobile.models.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginCustomValues implements Parcelable
{

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("phone_numbers")
    @Expose
    private String phoneNumbers;
    @SerializedName("success")
    @Expose
    private long success;
    @SerializedName("email_address")
    @Expose
    private Object emailAddress;
    @SerializedName("customer_status")
    @Expose
    private String customerStatus;
    @SerializedName("due")
    @Expose
    private String due;
    @SerializedName("night")
    @Expose
    private String night;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("customer_status_color")
    @Expose
    private String customerStatusColor;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("service_address")
    @Expose
    private ServiceAddress serviceAddress;
    @SerializedName("email_addresses")
    @Expose
    private String emailAddresses;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("address_full")
    @Expose
    private String addressFull;
    @SerializedName("customer_number")
    @Expose
    private String customerNumber;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("Internet_services")
    @Expose
    private List<InternetService> internetServices = null;
    @SerializedName("Telephony_services")
    @Expose
    private List<TelephonyService> telephonyServices = null;
    public final static Parcelable.Creator<LoginCustomValues> CREATOR = new Creator<LoginCustomValues>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginCustomValues createFromParcel(Parcel in) {
            return new LoginCustomValues(in);
        }

        public LoginCustomValues[] newArray(int size) {
            return (new LoginCustomValues[size]);
        }

    }
            ;

    protected LoginCustomValues(Parcel in) {
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
        in.readList(this.internetServices, (InternetService.class.getClassLoader()));
        in.readList(this.telephonyServices, (TelephonyService.class.getClassLoader()));
    }

    public LoginCustomValues() {
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

    public List<InternetService> getInternetServices() {
        return internetServices;
    }

    public void setInternetServices(List<InternetService> internetServices) {
        this.internetServices = internetServices;
    }

    public List<TelephonyService> getTelephonyServices() {
        return telephonyServices;
    }

    public void setTelephonyServices(List<TelephonyService> telephonyServices) {
        this.telephonyServices = telephonyServices;
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
        dest.writeList(internetServices);
        dest.writeList(telephonyServices);
    }

    public int describeContents() {
        return 0;
    }

}