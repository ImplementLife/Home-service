package com.homeService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Date date;
    private Long statusId;
    private Long userId;

    /**
     * example : {"products" : [{"productId", "count"}], commentsId:[...]}
     */
    @Type(type = "text")
    private String infoJSON;

    @Transient private OrderStatus orderStatus;
    @Transient private ArrayList<Comment> idComments;
    @Transient private ArrayList<OrderDetails> orderDetails;
    @Transient private UserData userData;
    @Transient private Delivery delivery;
    @Transient private String payMethod;
    @Transient private String userComment;
    @Transient private User user;

    /*==================================*/

    public String getFormattedDate() {
        return new SimpleDateFormat("[yyyy.MM.dd HH:mm]").format(date);
    }

    /*=======================================================*/

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetails {
        private String count;
        private String productId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserData {
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delivery {
        private String method;
        private String address;
    }

    /*===========             JSON             ==============
    private JSONObject getJSONObject(String JSON) {
        try { return (JSONObject) new JSONParser().parse(JSON);
        } catch (Exception e) { return new JSONObject(); }
    }
    public boolean initInfoJSON(ArrayList<OrderDetails> orderDetails) {
        if (orderDetails == null) return false;
        if (orderDetails.size() < 1) return false;
        this.orderDetails = orderDetails;
        return initInfoJSON();
    }
    public boolean initInfoJSON() {
        if (orderDetails == null) return false;
        JSONArray array = new JSONArray();
        for (OrderDetails o : orderDetails) {
            JSONObject temp = new JSONObject();
            temp.put("count", o.getCount());
            temp.put("productId", o.getProductId());
            array.add(temp);
        }
        JSONObject object = getJSONObject(infoJSON);
        object.put("orderDetails", array);
        setInfoJSON(object.toJSONString());
        return true;
    }*/
}
