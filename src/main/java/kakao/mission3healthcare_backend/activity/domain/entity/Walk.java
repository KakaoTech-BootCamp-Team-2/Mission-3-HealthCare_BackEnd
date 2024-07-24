package kakao.mission3healthcare_backend.activity.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kakao.mission3healthcare_backend.auth.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 걷기 Entity
 *
 * @author : parkjihyeok
 * @since : 2024/07/24
 */
@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("walk")
public class Walk extends Activity {

	private Double distance; // 거리
	private Double avgHeartRate; // 평균 심박수

	public Walk(Double distance, Double avgHeartRate, Member member) {
		super(member);
		this.distance = distance;
		this.avgHeartRate = avgHeartRate;
	}
}
