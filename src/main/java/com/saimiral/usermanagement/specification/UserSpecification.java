package com.saimiral.usermanagement.specification;

import com.saimiral.usermanagement.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public  static Specification<User> hasName(String name){
        return (root, query, criteriaBuilder) ->
                name == null ? null :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public  static Specification<User> ageGreaterThan(Integer minAge){
        return (root, query, criteriaBuilder) ->
                minAge == null ? null :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("age"), minAge);
    }

    public  static Specification<User> ageLessThan(Integer maxAge){
        return (root, query, criteriaBuilder) ->
                maxAge == null ? null :
                        criteriaBuilder.lessThanOrEqualTo(root.get("age"), maxAge);
    }
}
