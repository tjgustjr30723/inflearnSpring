package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void login() throws Exception{
        Member member = new Member();
        member.setName("Jack");

        Long saveId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        Member member1 = new Member();
        member1.setName("Jack");

        Member member2 = new Member();
        member2.setName("Jack");

        memberService.join(member1);

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}
