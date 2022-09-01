package com.thelma.ecommercewebsite.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
//    @JoinColumn(name = "user_id")
    private AppUser user;
    @ManyToMany
    private List<Product> product;
}