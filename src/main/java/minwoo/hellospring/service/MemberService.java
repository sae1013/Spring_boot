package minwoo.hellospring.service;

import minwoo.hellospring.domain.Member;
import minwoo.hellospring.repository.MemberRepository;
import minwoo.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        ValidationDuplicateMember(member); // 중복값 검증.
        memberRepository.save(member);
        return member.getId();
    }

    private void ValidationDuplicateMember(Member member){
        Optional<Member> result = memberRepository.findByName(member.getName());
        // 이미 있는 값일때.
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 사용자 입니다.");
        });
    }

    /**
     전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}

