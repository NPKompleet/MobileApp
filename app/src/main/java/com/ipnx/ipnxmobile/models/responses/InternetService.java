package com.ipnx.ipnxmobile.models.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InternetService implements Parcelable
{

    @SerializedName("activation_date")
    @Expose
    private String activationDate;
    @SerializedName("subscription_start_date")
    @Expose
    private String subscriptionStartDate;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("pkgnum")
    @Expose
    private long pkgnum;
    @SerializedName("svcnum")
    @Expose
    private long svcnum;
    @SerializedName("full_package_name")
    @Expose
    private String fullPackageName;
    @SerializedName("recurring_fee")
    @Expose
    private String recurringFee;
    @SerializedName("change_locationnum")
    @Expose
    private long changeLocationnum;
    @SerializedName("activation_date_epoch")
    @Expose
    private long activationDateEpoch;
    @SerializedName("pkgpart")
    @Expose
    private long pkgpart;
    @SerializedName("subscription_id")
    @Expose
    private long subscriptionId;
    @SerializedName("last_bill")
    @Expose
    private long lastBill;
    @SerializedName("svcpart")
    @Expose
    private long svcpart;
    @SerializedName("locationnum")
    @Expose
    private long locationnum;
    @SerializedName("custnum")
    @Expose
    private long custnum;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("service_location")
    @Expose
    private String serviceLocation;
    @SerializedName("delayed_suspension_date")
    @Expose
    private String delayedSuspensionDate;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("package_balance")
    @Expose
    private String packageBalance;
    @SerializedName("start_date_epoch")
    @Expose
    private String startDateEpoch;
    @SerializedName("fos_package")
    @Expose
    private long fosPackage;
    @SerializedName("last_bill_date")
    @Expose
    private String lastBillDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("slipip")
    @Expose
    private String slipip;
    @SerializedName("is_sme")
    @Expose
    private long isSme;
    @SerializedName("package_class_comment")
    @Expose
    private String packageClassComment;
    @SerializedName("package_level_unapplied_credits")
    @Expose
    private String packageLevelUnappliedCredits;
    @SerializedName("primary_service")
    @Expose
    private String primaryService;
    @SerializedName("package_level_owed")
    @Expose
    private String packageLevelOwed;
    @SerializedName("package_level_unapplied_payments")
    @Expose
    private String packageLevelUnappliedPayments;
    @SerializedName("usernum")
    @Expose
    private long usernum;
    @SerializedName("quantity")
    @Expose
    private long quantity;
    @SerializedName("change_pkgnum")
    @Expose
    private long changePkgnum;
    @SerializedName("change_date")
    @Expose
    private long changeDate;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("setup")
    @Expose
    private long setup;
    @SerializedName("totalbytes")
    @Expose
    private String totalbytes;
    @SerializedName("package_class")
    @Expose
    private String packageClass;
    @SerializedName("change_pkgpart")
    @Expose
    private long changePkgpart;
    @SerializedName("bill")
    @Expose
    private long bill;
    public final static Parcelable.Creator<InternetService> CREATOR = new Creator<InternetService>() {


        @SuppressWarnings({
                "unchecked"
        })
        public InternetService createFromParcel(Parcel in) {
            return new InternetService(in);
        }

        public InternetService[] newArray(int size) {
            return (new InternetService[size]);
        }

    }
            ;

    protected InternetService(Parcel in) {
        this.activationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.subscriptionStartDate = ((String) in.readValue((String.class.getClassLoader())));
        this.className = ((String) in.readValue((String.class.getClassLoader())));
        this.pkgnum = ((long) in.readValue((long.class.getClassLoader())));
        this.svcnum = ((long) in.readValue((long.class.getClassLoader())));
        this.fullPackageName = ((String) in.readValue((String.class.getClassLoader())));
        this.recurringFee = ((String) in.readValue((String.class.getClassLoader())));
        this.changeLocationnum = ((long) in.readValue((long.class.getClassLoader())));
        this.activationDateEpoch = ((long) in.readValue((long.class.getClassLoader())));
        this.pkgpart = ((long) in.readValue((long.class.getClassLoader())));
        this.subscriptionId = ((long) in.readValue((long.class.getClassLoader())));
        this.lastBill = ((long) in.readValue((long.class.getClassLoader())));
        this.svcpart = ((long) in.readValue((long.class.getClassLoader())));
        this.locationnum = ((long) in.readValue((long.class.getClassLoader())));
        this.custnum = ((long) in.readValue((long.class.getClassLoader())));
        this.reason = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceLocation = ((String) in.readValue((String.class.getClassLoader())));
        this.delayedSuspensionDate = ((String) in.readValue((String.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.packageBalance = ((String) in.readValue((String.class.getClassLoader())));
        this.startDateEpoch = ((String) in.readValue((String.class.getClassLoader())));
        this.fosPackage = ((long) in.readValue((long.class.getClassLoader())));
        this.lastBillDate = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.slipip = ((String) in.readValue((String.class.getClassLoader())));
        this.isSme = ((long) in.readValue((long.class.getClassLoader())));
        this.packageClassComment = ((String) in.readValue((String.class.getClassLoader())));
        this.packageLevelUnappliedCredits = ((String) in.readValue((String.class.getClassLoader())));
        this.primaryService = ((String) in.readValue((String.class.getClassLoader())));
        this.packageLevelOwed = ((String) in.readValue((String.class.getClassLoader())));
        this.packageLevelUnappliedPayments = ((String) in.readValue((String.class.getClassLoader())));
        this.usernum = ((long) in.readValue((long.class.getClassLoader())));
        this.quantity = ((long) in.readValue((long.class.getClassLoader())));
        this.changePkgnum = ((long) in.readValue((long.class.getClassLoader())));
        this.changeDate = ((long) in.readValue((long.class.getClassLoader())));
        this.packageName = ((String) in.readValue((String.class.getClassLoader())));
        this.expiryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.setup = ((long) in.readValue((long.class.getClassLoader())));
        this.totalbytes = ((String) in.readValue((String.class.getClassLoader())));
        this.packageClass = ((String) in.readValue((String.class.getClassLoader())));
        this.changePkgpart = ((long) in.readValue((long.class.getClassLoader())));
        this.bill = ((long) in.readValue((long.class.getClassLoader())));
    }

    public InternetService() {
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(String subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getPkgnum() {
        return pkgnum;
    }

    public void setPkgnum(long pkgnum) {
        this.pkgnum = pkgnum;
    }

    public long getSvcnum() {
        return svcnum;
    }

    public void setSvcnum(long svcnum) {
        this.svcnum = svcnum;
    }

    public String getFullPackageName() {
        return fullPackageName;
    }

    public void setFullPackageName(String fullPackageName) {
        this.fullPackageName = fullPackageName;
    }

    public String getRecurringFee() {
        return recurringFee;
    }

    public void setRecurringFee(String recurringFee) {
        this.recurringFee = recurringFee;
    }

    public long getChangeLocationnum() {
        return changeLocationnum;
    }

    public void setChangeLocationnum(long changeLocationnum) {
        this.changeLocationnum = changeLocationnum;
    }

    public long getActivationDateEpoch() {
        return activationDateEpoch;
    }

    public void setActivationDateEpoch(long activationDateEpoch) {
        this.activationDateEpoch = activationDateEpoch;
    }

    public long getPkgpart() {
        return pkgpart;
    }

    public void setPkgpart(long pkgpart) {
        this.pkgpart = pkgpart;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public long getLastBill() {
        return lastBill;
    }

    public void setLastBill(long lastBill) {
        this.lastBill = lastBill;
    }

    public long getSvcpart() {
        return svcpart;
    }

    public void setSvcpart(long svcpart) {
        this.svcpart = svcpart;
    }

    public long getLocationnum() {
        return locationnum;
    }

    public void setLocationnum(long locationnum) {
        this.locationnum = locationnum;
    }

    public long getCustnum() {
        return custnum;
    }

    public void setCustnum(long custnum) {
        this.custnum = custnum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getDelayedSuspensionDate() {
        return delayedSuspensionDate;
    }

    public void setDelayedSuspensionDate(String delayedSuspensionDate) {
        this.delayedSuspensionDate = delayedSuspensionDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPackageBalance() {
        return packageBalance;
    }

    public void setPackageBalance(String packageBalance) {
        this.packageBalance = packageBalance;
    }

    public String getStartDateEpoch() {
        return startDateEpoch;
    }

    public void setStartDateEpoch(String startDateEpoch) {
        this.startDateEpoch = startDateEpoch;
    }

    public long getFosPackage() {
        return fosPackage;
    }

    public void setFosPackage(long fosPackage) {
        this.fosPackage = fosPackage;
    }

    public String getLastBillDate() {
        return lastBillDate;
    }

    public void setLastBillDate(String lastBillDate) {
        this.lastBillDate = lastBillDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSlipip() {
        return slipip;
    }

    public void setSlipip(String slipip) {
        this.slipip = slipip;
    }

    public long getIsSme() {
        return isSme;
    }

    public void setIsSme(long isSme) {
        this.isSme = isSme;
    }

    public String getPackageClassComment() {
        return packageClassComment;
    }

    public void setPackageClassComment(String packageClassComment) {
        this.packageClassComment = packageClassComment;
    }

    public String getPackageLevelUnappliedCredits() {
        return packageLevelUnappliedCredits;
    }

    public void setPackageLevelUnappliedCredits(String packageLevelUnappliedCredits) {
        this.packageLevelUnappliedCredits = packageLevelUnappliedCredits;
    }

    public String getPrimaryService() {
        return primaryService;
    }

    public void setPrimaryService(String primaryService) {
        this.primaryService = primaryService;
    }

    public String getPackageLevelOwed() {
        return packageLevelOwed;
    }

    public void setPackageLevelOwed(String packageLevelOwed) {
        this.packageLevelOwed = packageLevelOwed;
    }

    public String getPackageLevelUnappliedPayments() {
        return packageLevelUnappliedPayments;
    }

    public void setPackageLevelUnappliedPayments(String packageLevelUnappliedPayments) {
        this.packageLevelUnappliedPayments = packageLevelUnappliedPayments;
    }

    public long getUsernum() {
        return usernum;
    }

    public void setUsernum(long usernum) {
        this.usernum = usernum;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getChangePkgnum() {
        return changePkgnum;
    }

    public void setChangePkgnum(long changePkgnum) {
        this.changePkgnum = changePkgnum;
    }

    public long getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(long changeDate) {
        this.changeDate = changeDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getSetup() {
        return setup;
    }

    public void setSetup(long setup) {
        this.setup = setup;
    }

    public String getTotalbytes() {
        return totalbytes;
    }

    public void setTotalbytes(String totalbytes) {
        this.totalbytes = totalbytes;
    }

    public String getPackageClass() {
        return packageClass;
    }

    public void setPackageClass(String packageClass) {
        this.packageClass = packageClass;
    }

    public long getChangePkgpart() {
        return changePkgpart;
    }

    public void setChangePkgpart(long changePkgpart) {
        this.changePkgpart = changePkgpart;
    }

    public long getBill() {
        return bill;
    }

    public void setBill(long bill) {
        this.bill = bill;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(activationDate);
        dest.writeValue(subscriptionStartDate);
        dest.writeValue(className);
        dest.writeValue(pkgnum);
        dest.writeValue(svcnum);
        dest.writeValue(fullPackageName);
        dest.writeValue(recurringFee);
        dest.writeValue(changeLocationnum);
        dest.writeValue(activationDateEpoch);
        dest.writeValue(pkgpart);
        dest.writeValue(subscriptionId);
        dest.writeValue(lastBill);
        dest.writeValue(svcpart);
        dest.writeValue(locationnum);
        dest.writeValue(custnum);
        dest.writeValue(reason);
        dest.writeValue(username);
        dest.writeValue(serviceLocation);
        dest.writeValue(delayedSuspensionDate);
        dest.writeValue(orderDate);
        dest.writeValue(packageBalance);
        dest.writeValue(startDateEpoch);
        dest.writeValue(fosPackage);
        dest.writeValue(lastBillDate);
        dest.writeValue(status);
        dest.writeValue(slipip);
        dest.writeValue(isSme);
        dest.writeValue(packageClassComment);
        dest.writeValue(packageLevelUnappliedCredits);
        dest.writeValue(primaryService);
        dest.writeValue(packageLevelOwed);
        dest.writeValue(packageLevelUnappliedPayments);
        dest.writeValue(usernum);
        dest.writeValue(quantity);
        dest.writeValue(changePkgnum);
        dest.writeValue(changeDate);
        dest.writeValue(packageName);
        dest.writeValue(expiryDate);
        dest.writeValue(amount);
        dest.writeValue(setup);
        dest.writeValue(totalbytes);
        dest.writeValue(packageClass);
        dest.writeValue(changePkgpart);
        dest.writeValue(bill);
    }

    public int describeContents() {
        return 0;
    }

}