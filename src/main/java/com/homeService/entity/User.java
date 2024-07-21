package com.homeService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User implements UserDetails, Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Вместо username используется email
     */
    private String username; //Email
    private String firstName;//Имя
    private String lastName; //Фамилия
    private String phone;    //Номер телефона
    private String password; //Пароль
    @Transient private String passwordConfirm;
    private boolean enabled;
    private boolean accountNonLocked;

    /**
     * example: ["1", "5", ...]
     */
    @Type(type = "text") private String idProductCartJSON;
    @Transient private ArrayList<Long> productsCart;

    /**
     * example: ["1", "5", ...]
     */
    @Type(type = "text") private String idProductFavoriteJSON;
    @Transient private ArrayList<Long> productsFavorite;

    /*===================================*/

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public ArrayList<Long> getProductsCart() {
        if (productsCart == null) {
            productsCart = new ArrayList<>();
            try {
                for (Object o : (JSONArray) new JSONParser().parse(idProductCartJSON)) productsCart.add(Long.parseLong(o.toString()));
            } catch (ParseException e) {e.printStackTrace();}
        }
        return productsCart;
    }
    public ArrayList<Long> getProductsFavorite() {
        if (productsFavorite == null) {
            productsFavorite = new ArrayList<>();
            try {
                for (Object o : (JSONArray) new JSONParser().parse(idProductFavoriteJSON)) productsFavorite.add(Long.parseLong(o.toString()));
            } catch (ParseException e) {e.printStackTrace();}
        }
        return productsFavorite;
    }

    /*===================================*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        if (username != null && !username.isEmpty()) return username;
        else return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return (int) (this.id - user.id);
    }
}
