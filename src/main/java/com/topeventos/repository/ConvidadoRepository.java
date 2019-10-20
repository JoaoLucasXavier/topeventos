package com.topeventos.repository;

import com.topeventos.models.Convidado;
import com.topeventos.models.Evento;

import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
  // Buscar lista de convidados de determinado Evento
  Iterable<Convidado> findByEvento(Evento evento);
  Convidado findByCodigo(long codigo);
}