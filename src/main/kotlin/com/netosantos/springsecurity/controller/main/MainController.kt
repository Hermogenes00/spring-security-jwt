package com.netosantos.springsecurity.controller.main

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("main")
class MainController {

    @GetMapping("names")
    fun getNames():MutableList<String>{
        return mutableListOf("Neto","João","José")
    }
}