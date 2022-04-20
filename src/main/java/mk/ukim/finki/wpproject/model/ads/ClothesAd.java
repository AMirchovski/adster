package mk.ukim.finki.wpproject.model.ads;

import lombok.Data;
import mk.ukim.finki.wpproject.model.Ad;
import mk.ukim.finki.wpproject.model.Category;
import mk.ukim.finki.wpproject.model.City;
import mk.ukim.finki.wpproject.model.User;
import mk.ukim.finki.wpproject.model.enums.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "clothes_ads")
public class ClothesAd extends Ad {
    @Enumerated(EnumType.STRING)
    private TypeClothing typeClothing;

    private int numSize;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Enumerated(EnumType.STRING)
    private Color color;

    public ClothesAd() {
    }

    public ClothesAd(String title, String description, boolean isExchangePossible, boolean isDeliveryPossible,
                     Double price, City city, AdType type, Condition condition, Category category, User advertisedByUser,
                     TypeClothing typeClothing, int numSize, Size size, Color color) {
        super(title, description, isExchangePossible, isDeliveryPossible, price, city, type, condition, category, advertisedByUser);
        this.typeClothing = typeClothing;
        this.numSize = numSize;
        this.size = size;
        this.color = color;
    }
}