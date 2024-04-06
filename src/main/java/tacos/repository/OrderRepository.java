package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.PancakeOrder;

public interface OrderRepository extends CrudRepository<PancakeOrder, Long> {

}
