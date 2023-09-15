package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.SubscriptionDto;
import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    private static final int ITEMS_PER_PAGE = 3;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/starroad/product")
    public ModelAndView product(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            HttpServletRequest request) {

        List<SubscriptionDto> subscriptionDtoList = findMemberAndSubscriptionInfo(request);
        System.out.println(subscriptionDtoList);
        for (SubscriptionDto dto: subscriptionDtoList) {
            System.out.println(dto);
        }

        List<ProductResponseDto> productList = productService.getProductList();

        int startIndex = (page - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, productList.size());

        model.addAttribute("productItems", productList.subList(startIndex, endIndex));
        model.addAttribute("pageEndIndex", Math.ceil(productList.size()/Double.valueOf(ITEMS_PER_PAGE)));
        model.addAttribute("currentPage", page);
        model.addAttribute("user", "장서우");
        model.addAttribute("price", 10000);

        ModelAndView mav = new ModelAndView("product/product");
        return mav;
    }

    private List<SubscriptionDto> findMemberAndSubscriptionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("currentUser");
        System.out.println();

        int memberNo = loginMember.getNo();
        int memberSalary = loginMember.getSalary();
        int memberGoal = loginMember.getGoal();
        Double monthGoal = (1.0 * memberSalary) *  (1.0 * memberGoal) / 100;
        System.out.println("monthGoal: "+ monthGoal);
        System.out.println("memberNo: "+memberNo);

        List<SubscriptionDto> subscriptionList = productService.getSubscriptions(loginMember.toMemberDto());

        return subscriptionList;
    }

    @GetMapping("/starroad/product/result")
    public ModelAndView product_search_result(
            Model model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) String rate,
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "1") int page) {

        List<ProductResponseDto> productList = null;
        if(type!=null || period!=null || query != null)
            productList = productService.findByForm(type.charAt(0), Integer.parseInt(period), query);
        if(productList == null) {
            productList  = productService.getProductList();
        }

        int startIndex = (page - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, productList.size());

        model.addAttribute("productItems", productList.subList(startIndex, endIndex));
        model.addAttribute("pageEndIndex", Math.ceil(productList.size()/Double.valueOf(ITEMS_PER_PAGE)));
        model.addAttribute("currentPage", page);

        if(type!=null)
            model.addAttribute("type", type);
        if(period!=null)
            model.addAttribute("period", period);
        if(rate!=null)
            model.addAttribute("rate", rate);
        if(query!=null)
            model.addAttribute("query", query);

        model.addAttribute("user", "장서우");
        model.addAttribute("price", 10000);

        ModelAndView mav = new ModelAndView("product/product_result");
        return mav;
    }
}
