package com.topeventos.repository;

import com.topeventos.models.Evento;

import org.springframework.data.repository.CrudRepository;

/* CLASSE QUE IMPLEMENTA DA INTERFACE 'CrudRepository' MÉTODOS PARA CRUD(cadastrar, listar, atualizar, deletar) de  */
public interface EventoRepository extends CrudRepository<Evento, String> {
  // Notacao para buscar código para detalhes evento
  Evento findByCodigo(long codigo);
}