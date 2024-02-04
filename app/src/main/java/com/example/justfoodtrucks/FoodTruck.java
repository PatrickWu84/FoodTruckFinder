package com.example.justfoodtrucks;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class FoodTruck implements Parcelable {
    private String name;
    private String cuisine;
    private double rating;
    private String address;
    private double latitude;
    private double longitude;
    private String additionalInfo;
    private double distanceFromCurr;

    public FoodTruck(String name, String cuisine,
                     double rating, String address, double latitude, double longitude,
                     String additionalInfo) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.additionalInfo = additionalInfo;
    }

    protected FoodTruck(Parcel in) {
        name = in.readString();
        cuisine = in.readString();
        rating = in.readDouble();
        address = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        additionalInfo = in.readString();
    }

    public static final Creator<FoodTruck> CREATOR = new Creator<FoodTruck>() {
        @Override
        public FoodTruck createFromParcel(Parcel in) {
            return new FoodTruck(in);
        }

        @Override
        public FoodTruck[] newArray(int size) {
            return new FoodTruck[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public double getDistanceFromCurr() { return distanceFromCurr; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(cuisine);
        dest.writeDouble(rating);
        dest.writeString(address);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(additionalInfo);
    }
    // comparators for sorting the food trucks in different ways
    public static Comparator<FoodTruck> ratingLoHiComparator = new Comparator<FoodTruck>() {
        @Override
        public int compare(FoodTruck o1, FoodTruck o2) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
    };

    public static Comparator<FoodTruck> ratingHiLoComparator = new Comparator<FoodTruck>() {
        @Override
        public int compare(FoodTruck o1, FoodTruck o2) {
            return Double.compare(o2.getRating(), o1.getRating());
        }
    };

    public static Comparator<FoodTruck> distanceComparator = new Comparator<FoodTruck>() {
        @Override
        public int compare(FoodTruck o1, FoodTruck o2) {
            return Double.compare(o1.getDistanceFromCurr(), o2.getDistanceFromCurr());
        }
    };

    public void computeDistance(double userLat, double userLon) {
        double theta = longitude- userLon;
        double dist = Math.sin(deg2rad(latitude))
                * Math.sin(deg2rad(userLat))
                + Math.cos(deg2rad(latitude))
                * Math.cos(deg2rad(userLat))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        this.distanceFromCurr = dist; }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0); }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI); }
}
