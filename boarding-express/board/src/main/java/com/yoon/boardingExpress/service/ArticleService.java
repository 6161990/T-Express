package com.yoon.boardingExpress.service;

import com.yoon.boardingExpress.domain.Article;
import com.yoon.boardingExpress.domain.UserAccount;
import com.yoon.boardingExpress.domain.constant.SearchType;
import com.yoon.boardingExpress.dto.ArticleDto;
import com.yoon.boardingExpress.dto.ArticleWithCommentsDto;
import com.yoon.boardingExpress.repository.ArticleRepository;
import com.yoon.boardingExpress.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if(searchKeyword == null || searchKeyword.isBlank()){
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from) ;
            case ID -> articleRepository.findByUserAccount_IdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#"+searchKeyword, pageable).map(ArticleDto::from);
            case NAME -> articleRepository.findByUserAccount_NameContaining(searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComments(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("The Article is Not Exist. ArticleId is" + articleId));
    }

    @Transactional(readOnly = true)
    public ArticleDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("The Article is Not Exist. ArticleId is" + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().id());
        articleRepository.save(dto.toEntity(userAccount));
    }

    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId); /** referenceById 를 하면 id 로 해당 데이터를 가져오는 select 쿼리 없이 reference 를 바로 가져오게된다. */
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().id());

            if (article.getUserAccount().getId().equals(userAccount.getId())) {
                if (dto.title() != null) { article.setTitle(dto.title()); }
                if (dto.content() != null) { article.setContent(dto.content()); }
            }
            article.setHashtag(dto.hashtag());
            /** @Transaction 이 걸려있기 때문에 영속성 컨텍스트가 살아 있어서 내부적으로 변경을 감지하기때문에 save 쿼리를 내보내지 않아도 됨*/
        } catch (EntityNotFoundException e){
            log.warn("The Article is Not Exist. ArticleId is {} - {}", dto.id(), e.getLocalizedMessage());
        }

    }

    public void deleteArticle(Long articleId, String userId) {
        articleRepository.deleteByIdAndUserAccount_Id(articleId, userId);
    }

    public long articleCount() {
        return articleRepository.count();
    }

    public Page<ArticleDto> searchArticlesViaHashtag(String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()){
            return Page.empty(pageable);
        }

        return articleRepository.findByHashtag(searchKeyword, pageable).map(ArticleDto::from);
    }

    public List<String> getHashtags() {
        return articleRepository.findAllDistinctHashtags();
    }
}
