package br.dev.du.cardapio.repositories;

import br.dev.du.cardapio.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}