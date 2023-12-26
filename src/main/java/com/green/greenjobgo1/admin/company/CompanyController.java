package com.green.greenjobgo1.admin.company;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "company")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/company")
public class CompanyController {

    private final CompanyServicempl service;

}
