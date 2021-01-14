package minwoo.hellospring.service;

import minwoo.hellospring.domain.Member;
import minwoo.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("spring1");

        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원검증(){
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("spring1");
        member2.setName("spring1");
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail(); // 예외 안터졌을때 테스트 실패
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 사용자 입니다.");
        }

        //when
    }

    @Test
    void 전체회원리스트() {
    }

    @Test
    void 아이디로찾기() {
    }
}