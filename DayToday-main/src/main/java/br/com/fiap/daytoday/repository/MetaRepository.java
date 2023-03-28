package br.com.fiap.daytoday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.daytoday.models.metas;

public interface MetaRepository extends JpaRepository<metas, Long> {
    
}
