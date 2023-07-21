package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "product_tb")
@Entity // 테이블 만드는 어노테이션
public class Product {
    @Id // 프라이머리키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 오토인플리먼트 달아줌 (INDENTITY 정체성을 따라간다 ++라고 생각하면 됨)
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
}
