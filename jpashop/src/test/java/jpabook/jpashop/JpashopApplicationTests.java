package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpashopApplicationTests {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	MemberService memberService;

	@Test
	public void 회원가입() throws Exception {
		Member member = new Member();
		member.setName("kim");

		Long saveId = memberService.join(member);

		assertEquals(member, memberRepository.findOne(saveId));
	}

}
