package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);   // 供 用户详情服务使用
}
