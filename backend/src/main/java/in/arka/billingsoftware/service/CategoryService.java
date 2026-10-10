package in.arka.billingsoftware.service;

import in.arka.billingsoftware.io.CategoryRequest;
import in.arka.billingsoftware.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
CategoryResponse add(CategoryRequest request, MultipartFile file);
List<CategoryResponse>read();

void delete(String categoryId);
}
