package github.lionfish.repository;


import github.lionfish.entity.SLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SLinkRepository extends JpaRepository<SLink,Integer> {

}
