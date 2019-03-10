package fitmama.repo;

import fitmama.model.Measurement;
import fitmama.model.UserGroup;
import fitmama.model.UserGroupJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupJoinRepository extends JpaRepository<UserGroupJoin, Long> {
    List<UserGroupJoin> findByUser_Id(Long userId);
    List<UserGroupJoin> findByGroup_Id(Long groupId);
}
