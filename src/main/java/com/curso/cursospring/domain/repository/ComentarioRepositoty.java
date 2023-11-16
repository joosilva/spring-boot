package com.curso.cursospring.domain.repository;

import com.curso.cursospring.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositoty extends JpaRepository<Comentario, Long> {



}
