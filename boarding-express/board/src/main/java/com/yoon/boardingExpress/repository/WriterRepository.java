package com.yoon.boardingExpress.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.yoon.boardingExpress.domain.QWriter;
import com.yoon.boardingExpress.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.beans.Expression;

@RepositoryRestResource
public interface WriterRepository extends JpaRepository<Writer, Long>,
        QuerydslPredicateExecutor<Writer>, /**  <Entity> 에 명시한 모든 필드에 대해서 검색기능을 구현해준다. */
        QuerydslBinderCustomizer<QWriter> /**  위 구현 + 검색에 대한 세부적인 설정을 가능하게해준다. */
{

    @Override
    default void customize(QuerydslBindings bindings, QWriter root) {
        bindings.excludeUnlistedProperties(true); // 리스팅 하지 않은 필드에 대해서는 검색 기능 구현을 제외한다.
        bindings.including(root.id, root.name, root.email, root.phoneNumber);
        bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.email).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.phoneNumber).first(StringExpression::containsIgnoreCase);
    }
}