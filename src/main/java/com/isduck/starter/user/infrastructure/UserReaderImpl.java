package com.isduck.starter.user.infrastructure;

import com.isduck.starter.user.domain.User;
import com.isduck.starter.user.domain.Users;
import com.isduck.starter.user.dto.UserSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public Users getUsers(UserSearchDto userSearchDto, Pageable pageable) {
        return new Users(userRepository.findAll(searchUser(userSearchDto), pageable));
    }

    public Specification<User> searchUser(UserSearchDto userSearchDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(
                    builder.like(root.get("name").as(String.class), "%"+userSearchDto.getName()+"%")
            );
            predicates.add(
                    builder.like(root.get("email").as(String.class), "%"+userSearchDto.getEmail()+"%")
            );

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
