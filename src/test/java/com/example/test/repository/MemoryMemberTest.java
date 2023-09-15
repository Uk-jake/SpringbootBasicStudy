package com.example.test.repository;

import com.example.test.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트가 끝난 이후에 각각의 레포지토리에 있는 데이터들을 삭제함
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //member객체와 restult객체가 동일한지 알아보기 위해 아래와 같이 사용
        assertEquals(member, result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Jake1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Jake2");
        repository.save(member2);

        Member result1 = repository.findByName("Jake1").get();
        Member result2 = repository.findByName("Jake2").get();

        assertEquals(result1, member1);
        assertEquals(result2, member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result =  repository.findAll();

        assertEquals(result.size(),2);

    }



}
