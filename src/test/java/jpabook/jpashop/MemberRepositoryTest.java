package jpabook.jpashop;

import org.junit.jupiter.api.Assertions;
import jpabook.jpashop.Member;
import jpabook.jpashop.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        org.assertj.core.api.Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        org.assertj.core.api.Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        org.assertj.core.api.Assertions.assertThat(findMember).isEqualTo(member);
    }
}