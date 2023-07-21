package shop.mtcoding.mall.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

@Rollback(false) // 메서드 종료시마다 롤백안하기
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 진짜 디비로 테스트
@Import(ProductRepository.class) // R - mtt 매니저가 뜸.
@DataJpaTest // T -> DS -> C -> ( R -> DB )
public class ProductRepositoryTest {

    @Autowired
    public ProductRepository productRepository;

    @Test
    public void save_test(){
        productRepository.save("바나나",5000,50);
    }

    @Test
    public void findById_test(){
        Product product = productRepository.findById(1);
        System.out.println(product.getId());
        System.out.println(product.getName());
    }



}
