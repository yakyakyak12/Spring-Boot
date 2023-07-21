package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 업데이트
    @PostMapping("/product/update")
    public String update(int id, String name, int price, int qty) {
        productRepository.updateById(id, name, price, qty);
        return "redirect:/";
    }


    // 삭제
    @PostMapping("/product/delete")
    public String delete(int id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }

    // 상세보기
    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){
        System.out.println("id : "+id);
        Product product = productRepository.findById(id);
        request.setAttribute("p", product);
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        return "detail";
    }

    @GetMapping("/") // 메인 페이지를 줘
    public String home(HttpServletRequest request){
       List<Product> productList = productRepository.findAll();
       request.setAttribute("productList", productList);
        return "home";
    }

    @GetMapping("/write") // 라이트 페이지를 줘
    public String writePage(){
        return "write";
    }

    @PostMapping("/product") // 상품을 줄께
    public void write(String name, int price, int qty, HttpServletResponse response) throws IOException {
        System.out.println("name : "+name);
        System.out.println("price : "+price);
        System.out.println("qty : "+qty);

        productRepository.save(name,price,qty);
        // 컨트롤러의 메서드를 재호출하는 방법
        response.sendRedirect("/");
//      return "redirect:/";
    }

}
