package shop.mtcoding.mall.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository // 컴퍼넌트 스캔
public class ProductRepository {

    @Autowired // di라고 부른다.
    private EntityManager em;

    public ProductDTO findByIdDTO(int id){
        Query query = em.createNativeQuery("select id, name, price, qty, '설명' des from product_tb where id = :id", ProductDTO.class);
        query.setParameter("id",id);
        ProductDTO productDTO = (ProductDTO) query.getSingleResult();
        return productDTO;
    } // des라는 별칭을 줌. 조회할 것은 매핑할 클래스를 적어준다.
    // mall 이하만 컴포넌트 스캔을 하기 때문에 파일은 mall 이하에 등록한다.


    //저장
    @Transactional // 트렌젝션 관리 주석 (insert, update, delete) 할때 사용해준다.
    public void save(String name, int price, int qty){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }
    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from product_tb where id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }


    // 목록보기
    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }

    // 상세보기
    public Product findById(int id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
        query.setParameter("id",id);
        Product product = (Product) query.getSingleResult();
        return product;
    }

    @Transactional
    public void updateById(int id,String name, int price, int qty) {
        Query query = em.createNativeQuery("update product_tb set name = :name ,price = :price, qty = :qty where id = :id");
        query.setParameter("name",name);
        query.setParameter("price",price);
        query.setParameter("qty",qty);
        query.setParameter("id",id);
        query.executeUpdate();
    }

    // insert, update, delete

}