package com.quasar.sika.design.server.common.controller;

import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "commonController")
@RequestMapping("common")
public class CommonController extends BaseStandardController {
}
