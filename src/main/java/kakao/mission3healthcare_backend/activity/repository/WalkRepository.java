package kakao.mission3healthcare_backend.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kakao.mission3healthcare_backend.activity.domain.entity.Walk;

/**
 * 걷기 Repository
 *
 * @author : parkjihyeok
 * @since : 2024/07/24
 */
public interface WalkRepository extends JpaRepository<Walk, Long> {

	@Query("select w from Walk w where w.member.username = :username")
	List<Walk> findByUsername(String username);
}
