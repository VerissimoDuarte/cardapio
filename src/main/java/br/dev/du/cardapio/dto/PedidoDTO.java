package br.dev.du.cardapio.dto;

import br.dev.du.cardapio.entities.Enum.StatusPagamento;
import br.dev.du.cardapio.entities.Enum.StatusPedido;
import br.dev.du.cardapio.entities.Item;
import br.dev.du.cardapio.entities.Pedido;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    private Long id;
    private int mesa;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
    private Instant horaPedido;
    private List<Item> itens = new ArrayList<Item>();

    public PedidoDTO(){}

    public PedidoDTO(Long id, int mesa, StatusPedido statusPedido, StatusPagamento statusPagamento, Instant horaPedido) {
        this.id = id;
        this.mesa = mesa;
        this.statusPedido = statusPedido;
        this.statusPagamento = statusPagamento;
        this.horaPedido = horaPedido;
    }

    public PedidoDTO(Pedido entity) {
        this.id = entity.getId();
        this.mesa = entity.getMesa();
        this.statusPedido = entity.getStatusPedido();
        this.statusPagamento = entity.getStatusPagamento();
        this.horaPedido = entity.getHoraPedido();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Instant getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Instant horaPedido) {
        this.horaPedido = horaPedido;
    }

    public void addItem(Item obj){
        itens.add(obj);
    }
    public void removeItem(Item obj){
        itens.remove(obj);
    }
    public List<Item> getItens(){
        return itens;
    }
}
