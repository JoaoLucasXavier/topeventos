package com.topeventos.repository;

import com.topeventos.models.Convidado;

import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
}