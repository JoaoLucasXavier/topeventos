package com.topeventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventoController {

  // form() - Método que vai retornar o formulário de cadastro de eventos
  @RequestMapping("/cadastrarEvento")
  public String form() {
    return "evento/formEvento";
  }

}