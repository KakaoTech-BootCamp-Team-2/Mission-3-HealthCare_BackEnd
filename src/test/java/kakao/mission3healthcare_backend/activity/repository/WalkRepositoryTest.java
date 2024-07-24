package kakao.mission3healthcare_backend.activity.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import kakao.mission3healthcare_backend.activity.domain.entity.Walk;
import kakao.mission3healthcare_backend.auth.domain.entity.Member;
import kakao.mission3healthcare_backend.auth.repository.MemberRepository;

/**
 * @author : parkjihyeok
 * @since : 2024/07/24
 */
@SpringBootTest
@Transactional
@DisplayName("활동(걷기) Repository")
@WithMockUser
class WalkRepositoryTest {

	@Autowired WalkRepository walkRepository;
	@Autowired MemberRepository memberRepository;

	@Test
	@DisplayName("활동(걷기) 저장 테스트")
	void saveTest() {
		// Given
		Member member = Member.builder().username("testId").build();
		memberRepository.save(member);

		Walk walk = new Walk(1.4, 141.2, member);

		// When
		walkRepository.save(walk);
		List<Walk> results = walkRepository.findByUsername("testId");
		Walk getWalk = results.get(0);
		Walk result = walkRepository.findById(walk.getId()).get();

		// Then
		assertEquals(1, results.size());
		assertEquals(walk, getWalk);
		assertEquals(walk, result);
	}

	@Test
	@DisplayName("사용자의 활동(걷기)정보를 불러오는 테스트")
	void findByUsernameTest() {
		// Given
		Member member = Member.builder().username("testId").build();
		memberRepository.save(member);

		Walk walk1 = new Walk(1.4, 141.2, member);
		Walk walk2 = new Walk(2.4, 142.2, member);
		Walk walk3 = new Walk(3.4, 143.2, member);
		Walk walk4 = new Walk(4.4, 144.2, member);
		Walk walk5 = new Walk(5.4, 145.2, member);

		walkRepository.save(walk1);
		walkRepository.save(walk2);
		walkRepository.save(walk3);
		walkRepository.save(walk4);
		walkRepository.save(walk5);

		// When
		List<Walk> result = walkRepository.findByUsername("testId");

		// Then
		assertEquals(5, result.size());
		assertEquals(3.4, result.get(2).getDistance());
	}

	@Test
	@DisplayName("활동(걷기) 삭제 테스트")
	void deleteTest() {
		// Given
		Member member = Member.builder().username("testId").build();
		memberRepository.save(member);

		Walk walk = new Walk(1.4, 141.2, member);
		walkRepository.save(walk);

		// When
		walkRepository.deleteById(walk.getId());

		// Then
		assertEquals(Optional.empty(), walkRepository.findById(walk.getId()));
	}
}
