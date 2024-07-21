package com.homeService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Product implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private String article;
    private Long categoryId;
    private boolean isPublic;

    @Type(type = "text") private String description;
    @Type(type = "text") private String characteristicsJSON;
    @Type(type = "text") private String priceListJSON;
    @Type(type = "text") private String imagesJSON;

    /*===================================*/
    @Transient private TreeMap<Integer, Discount> optPrices;
    @Transient private ArrayList<String> images;
    @Transient private boolean isFavorite;
    @Transient private boolean isInCart;
    @Transient private String defaultPrice;
    /*===================================*/

    public final ArrayList<String> getImages() {
        if (images != null) return images;
        return new ArrayList<String>();
    }

    /*===   |   ===*/
    @Override
    public int compareTo(Object o) {
        Product product = (Product) o;
        return (int) (this.id - product.id);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Product) {
            Product product = (Product) o;
            return this.id.equals(product.id);
        } else return false;
    }

    /*===================================*/
    public static class Discount {
        private final int minCount;
        private final int percent;

        public Discount(int minCount, int money) throws Exception {
            if (minCount < 1) throw new Exception("minCount can have value is >= 1;");
            if (money < 0) throw new Exception("Money diapason: [0 - large], received value in money: " + money + ';');

            this.minCount = minCount;
            this.percent = money;
        }

        public int getMinCount() {
            return minCount;
        }
        public int getPercent() {
            return percent;
        }
    }

    /*================      other       ===================*/
    public final String getMainImg() {
        if (images != null) return images.get(0);
        return "none";
    }

}