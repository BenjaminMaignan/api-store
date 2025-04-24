package com.bmaignan.apistore.articleitem.service.impl;

import com.bmaignan.apistore.articleitem.dto.ArticleItemRequestDTO;
import com.bmaignan.apistore.articleitem.dto.ArticleItemResponseDTO;
import com.bmaignan.apistore.articleitem.mapper.ArticleItemMapper;
import com.bmaignan.apistore.articleitem.model.ArticleItem;
import com.bmaignan.apistore.articleitem.repository.ArticleItemDao;
import com.bmaignan.apistore.articleitem.service.ArticleItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ArticleItemServiceImpl implements ArticleItemService {
    private final ArticleItemDao articleItemDao;
    private final ArticleItemMapper articleItemMapper;

    public ArticleItemServiceImpl(ArticleItemDao articleItemDao, ArticleItemMapper articleItemMapper) {
        this.articleItemDao = articleItemDao;
        this.articleItemMapper = articleItemMapper;
    }

    @Override
    public ArticleItemResponseDTO getArticleItemById(UUID id) {
        return articleItemDao
                .findById(id)
                .map(articleItemMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Article item not found"));
    }

    @Override
    public ArticleItem getEntity(UUID id) {
        return articleItemDao
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Article item not found"));
    }

    @Override
    @Transactional
    public ArticleItemResponseDTO createArticleItem(ArticleItemRequestDTO articleItemRequestDTO) {
        return
                articleItemMapper.toResponseDTO(
                        articleItemDao
                                .save(articleItemMapper.toEntity(articleItemRequestDTO))
                );
    }

    @Override
    @Transactional
    public ArticleItemResponseDTO updateArticleItem(ArticleItemRequestDTO articleItemRequestDTO) {
        return articleItemMapper.toResponseDTO(
                articleItemDao.save(articleItemMapper.toEntity(articleItemRequestDTO))
        );
    }

    @Override
    public void deleteArticleItem(UUID id) {

    }
}
