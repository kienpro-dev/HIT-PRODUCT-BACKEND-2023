package com.example.projectbase.repository;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.response.FindProductResponseDto;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.id = ?1")
  Optional<User> findById(String id);

  @Query("SELECT u FROM User u WHERE u.username = ?1")
  Optional<User> findByUsername(String username);

  default User getUser(UserPrincipal currentUser) {
    return findByUsername(currentUser.getUsername())
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_USERNAME,
            new String[]{currentUser.getUsername()}));
  }

   //   @Query(value = "select * from products as p inner join shop_product as sp on p.product_id = sp.sp_product_id inner join shops as s on sp.sp_shop_id = s.shop_id inner join category_product as cp on cp.cp_product_id = p.product_id inner join categories as c on c.category_id = cp.cp_category_id ", nativeQuery = true)
    @Query("SELECT new com.example.projectbase.domain.dto.response.FindProductResponseDto(p.id, p.name, p.image, p.price, p.stock, c.id, c.name, s.id, s.name, s.address, s.timeOpen, s.timeClose, s.hotline) FROM Product p INNER JOIN p.shops s INNER JOIN p.categories c WHERE (p.name LIKE %:keyword%) OR (s.name LIKE %:keyword%) OR (c.name LIKE %:keyword%)")
    Page<FindProductResponseDto> find(String keyword, Pageable pageable);

  @Query("SELECT new com.example.projectbase.domain.dto.response.FindProductResponseDto(p.id, p.name, p.image, p.price, p.stock, c.id, c.name, s.id, s.name, s.address, s.timeOpen, s.timeClose, s.hotline) FROM Product p INNER JOIN p.shops s INNER JOIN p.categories c WHERE p.id = ?1")
  Optional<FindProductResponseDto> findProductDetail(int id);

}
