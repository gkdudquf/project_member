package com.example.member;

import com.example.member.dto.MemberDTO;
import com.example.member.entity.MemberEntity;
import com.example.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    private MemberDTO newMember(int i) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("test" + i);
        memberDTO.setMemberName("name" + i);
        memberDTO.setMemberPassword("test" + i + i + i + i);
        memberDTO.setMemberMobile("010-1234-456" + 1);
        memberDTO.setMemberBirth("1996-08-1" + i);
        return memberDTO;
    }

    @Test
    @Rollback(value = false)
    @DisplayName("DB에 데이터 붓기")
    public void saveList() {
        IntStream.rangeClosed(1,9).forEach(i -> {
            memberRepository.save(MemberEntity.toSaveEntity(newMember(i)));
        });
    }
}
