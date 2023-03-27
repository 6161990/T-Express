package com.yoon.boardingExpress.repository.querydsl;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<String> findAllDistinctHashtags(); /** Return 이 도메인이 아니기 때문에 커스텀하게 querydsl 을 사용한다.*/
}
