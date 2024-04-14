package br.dev.du.cardapio.services;

import br.dev.du.cardapio.dto.ItemDTO;
import br.dev.du.cardapio.dto.PedidoDTO;
import br.dev.du.cardapio.entities.Item;
import br.dev.du.cardapio.entities.Pedido;
import br.dev.du.cardapio.repositories.PedidoRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    public PedidoService() {
    }

    @Transactional(readOnly = true)
    public Page<PedidoDTO> findAllPerPage(PageRequest page){
        return repository.findAll(page).map(PedidoDTO::new);
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        return new PedidoDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Exist")));
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto){

        try{
            Pedido pedido = new Pedido();
            pedidoToPedidoDto(pedido, dto);
            pedido = repository.save(pedido);
            return new PedidoDTO(pedido);
        }
        catch (IllegalArgumentException | OptimisticLockException e){
            throw new RuntimeException(e.getMessage());
        }

    }
    @Transactional
    public PedidoDTO update(PedidoDTO dto, Long id){

        try{
            Pedido pedido = repository.getReferenceById(id);
            pedidoToPedidoDto(pedido, dto);
            pedido = repository.save(pedido);
            return new PedidoDTO(pedido);
        }
        catch (IllegalArgumentException | OptimisticLockException e){
            throw new RuntimeException(e.getMessage());
        }


    }
    @Transactional
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch (IllegalArgumentException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private void pedidoToPedidoDto(Pedido pedido, PedidoDTO dto) {
        pedido.setHoraPedido(dto.getHoraPedido());
        pedido.setId(dto.getId());
        pedido.setStatusPedido(dto.getStatusPedido());
        pedido.setMesa(dto.getMesa());
        pedido.setStatusPagamento(dto.getStatusPagamento());

        List<Item> list = dto.getItens();
        list.forEach(pedido::addItem);

    }

}
