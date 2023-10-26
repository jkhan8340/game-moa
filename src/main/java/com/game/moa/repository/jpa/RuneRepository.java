package com.game.moa.repository.jpa;

import com.game.moa.entity.Rune;
import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuneRepository extends JpaRepository<Rune, Long> {

    @EntityGraph(attributePaths = {"runeOptions"}, type = EntityGraph.EntityGraphType.FETCH)
    @OrderBy(clause = "number asc")
    List<Rune> findByEnabledOrderByNumberAsc(boolean enabled);

}
