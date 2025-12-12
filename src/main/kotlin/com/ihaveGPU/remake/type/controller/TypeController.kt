package com.ihaveGPU.remake.type.controller

import com.ihaveGPU.remake.type.service.TypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/types")
class TypeControler @Autowired constructor(
  private val typeService: TypeService
){
  private val logger = org.slf4j.LoggerFactory.getLogger(TypeControler::class.java)
}