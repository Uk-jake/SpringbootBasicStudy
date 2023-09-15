package com.example.test.service;

import com.example.test.domain.Member;
import com.example.test.repository.MemberRepository;
import com.example.test.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest  //스프링 컨테이너와 테스트를 함께 실행
@Transactional //Test이후 Rollback을 통해 Database의 실제 데이터가 반영이 되지 않음. -> DB에 데이터에 영향을 주지 않음.
class MemberServiceIntergrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
//    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //memberService에 member2를 추가했을때 IllegalStateException에서 예외가 나오는지 확인
        //나온다면 성공한 것이다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}