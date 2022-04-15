package mk.ukim.finki.wpproject.model.ads.realEstates;

import lombok.Data;
import mk.ukim.finki.wpproject.model.Category;
import mk.ukim.finki.wpproject.model.User;
import mk.ukim.finki.wpproject.model.enums.AdType;
import mk.ukim.finki.wpproject.model.enums.Condition;
import mk.ukim.finki.wpproject.model.enums.Heating;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "house_ads")
public class HouseAd extends RealEstateAd {

    private int yearMade;

    private int yardArea;

    private int numRooms;

    private int numFloors;

    private boolean hasBasement;

    @Enumerated(EnumType.STRING)
    private Heating heating;

    public HouseAd() {
    }

    public HouseAd(String title, String description, boolean isExchangePossible, boolean isDeliveryPossible,
                   Double price, String city, AdType type, Condition condition, Category category, User advertisedByUser,
                   int quadrature, int yearMade, int yardArea, int numRooms, int numFloors, boolean hasBasement, Heating heating) {
        super(title, description, isExchangePossible, isDeliveryPossible,
                price, city, type, condition, category, advertisedByUser, quadrature);
        this.yearMade = yearMade;
        this.yardArea = yardArea;
        this.numRooms = numRooms;
        this.numFloors = numFloors;
        this.hasBasement = hasBasement;
        this.heating = heating;
    }
}
