package me.limichael.whodoiowe;


import android.os.Parcel;
import android.os.Parcelable;

public class Owes implements Parcelable {

    private String personToPay;
    private double amount;
    private String description;

    public String getPersonToPay() {
        return personToPay;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Owes createFromParcel(Parcel in) {
            return new Owes(in.readString(), in.readDouble(), in.readString());
        }

        public Owes[] newArray(int size) {
            return new Owes[size];
        }
    };

    public void setPersonToPay(String personToPay) {
        this.personToPay = personToPay;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owes(String person, double money, String what) {
        personToPay = person;
        amount = money;
        description = what;

    }

    @Override
    public String toString() {
        return String.format("You owe %.2f to %s for %s", amount, personToPay, description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(personToPay);
        dest.writeDouble(amount);
        dest.writeString(description);
    }
}
