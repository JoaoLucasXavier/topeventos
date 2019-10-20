package com.topeventos.controllers;

import javax.validation.Valid;

import com.topeventos.models.Convidado;
import com.topeventos.models.Evento;
import com.topeventos.repository.ConvidadoRepository;
import com.topeventos.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventoController {

  @Autowired
  private EventoRepository er;

  @Autowired
  private ConvidadoRepository cr;

  @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
  public String form() {
    return "evento/formEvento";
  }

  @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
  public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
    if (result.hasErrors()) {
      attributes.addFlashAttribute("mensagem", "Verifique os campos!");
      return "redirect:/cadastrarEvento";
    }
    er.save(evento);
    attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso!");
    return "redirect:/cadastrarEvento";
  }

  @RequestMapping("/eventos")
  public ModelAndView listaEventos() {
    ModelAndView mv = new ModelAndView("index");
    Iterable<Evento> eventos = er.findAll();

    // Param1: referencia objeto na view ${eventos}
    // Param2: Lista de eventos obtidas do db
    mv.addObject("eventos", eventos);

    return mv;
  }

  @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
  public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
    Evento evento = er.findByCodigo(codigo);
    ModelAndView mv = new ModelAndView("evento/detalhesEvento");
    mv.addObject("evento", evento);
    // Enviar para view 'detalhesEvento' lista de convidados de determinado Evento
    Iterable<Convidado> convidados = cr.findByEvento(evento);
    mv.addObject("convidados", convidados);
    return mv;
  }

  @RequestMapping("/deletarEvento")
  public String deletarEvento(long codigo) {
    Evento evento = er.findByCodigo(codigo);
    er.delete(evento);
    return "redirect:/eventos";
  }

  @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
  public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,
      BindingResult result, RedirectAttributes attributes) {
    // Retornar error de validação dos os campos no cadastro de Convidado do Evento
    // através do fragmento (view: mensagemValidação) que será incluido no
    // formumário de convidados!
    if (result.hasErrors()) {
      // addFlashAttribute(): Param1: atributo que será passado para view como span |
      // Param2: Mensagem de erro!
      attributes.addFlashAttribute("mensagem", "Verifique os campos!");
      return "redirect:/{codigo}";
    }
    Evento evento = er.findByCodigo(codigo);
    convidado.setEvento(evento);
    cr.save(convidado);
    attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
    return "redirect:/{codigo}";
  }

  @RequestMapping("/deletarConvidado")
  public String deletarConvidado(long codigo) {
    Convidado convidado = cr.findByCodigo(codigo);
    cr.delete(convidado);
    return "redirect:/{codigo}";
  }

}