package in.arka.billingsoftware.service.impl;

import in.arka.billingsoftware.entity.CategoryEntity;
import in.arka.billingsoftware.entity.ItemEntity;
import in.arka.billingsoftware.io.ItemRequest;
import in.arka.billingsoftware.io.ItemResponse;
import in.arka.billingsoftware.repository.CategoryRepository;
import in.arka.billingsoftware.repository.ItemRepository;
import in.arka.billingsoftware.service.FileUploadService;
import in.arka.billingsoftware.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public ItemResponse add(ItemRequest request, MultipartFile file) {


        String imgUrl = fileUploadService.uploadFile(file);
        ItemEntity newItem = convertToEntity(request);

        CategoryEntity existingCategory = categoryRepository
                .findByCategoryId(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found: " + request.getCategoryId()
                        )
                );

        newItem.setCategory(existingCategory);
        newItem.setImgUrl(imgUrl);

        newItem = itemRepository.save(newItem);

        return convertToResponse(newItem);
    }

    private ItemResponse convertToResponse(ItemEntity itemEntity) {

        return ItemResponse.builder()
                .itemId(itemEntity.getItemId())
                .name(itemEntity.getName())
                .description(itemEntity.getDescription())
                .price(itemEntity.getPrice())
                .imgUrl(itemEntity.getImgUrl())
                .categoryName(itemEntity.getCategory().getName())
                .categoryId(itemEntity.getCategory().getCategoryId())
                .createdAt(itemEntity.getCreatedAt())
                .updatedAt(itemEntity.getUpdatedAt())
                .build();
    }

    private ItemEntity convertToEntity(ItemRequest request) {

        return ItemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    @Override
    public List<ItemResponse> fetchItems() {

        return itemRepository.findAll()
                .stream()
                .map(itemEntity -> convertToResponse(itemEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(String itemId) {

        ItemEntity existingItem = itemRepository
                .findByItemId(itemId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Item not found: " + itemId
                        )
                );

        boolean isFileDelete =
                fileUploadService.deleteFile(existingItem.getImgUrl());

        if (isFileDelete) {
            itemRepository.delete(existingItem);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to delete the image"
            );
        }

    }
}