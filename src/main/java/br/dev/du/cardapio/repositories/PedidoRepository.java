package br.dev.du.cardapio.repositories;

import br.dev.du.cardapio.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
