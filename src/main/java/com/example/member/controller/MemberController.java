package com.example.member.controller;

import com.example.member.dto.MemberDTO;
import com.example.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "/memberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("저장할 memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList  = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "/memberList";
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity axiosFindById(@PathVariable Long id) {
        MemberDTO memberDTO = memberService.findById(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "/memberDetail";
    }

}
