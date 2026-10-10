package in.arka.billingsoftware.repository;

import in.arka.billingsoftware.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
Optional<CategoryEntity> findByCategoryId(String categoryId);
}
