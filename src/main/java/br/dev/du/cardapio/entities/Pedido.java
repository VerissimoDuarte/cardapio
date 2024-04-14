package br.dev.du.cardapio.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.dev.du.cardapio.entities.Enum.StatusPagamento;
import br.dev.du.cardapio.entities.Enum.StatusPedido;
import jakarta.persistence.*;
@Table(name ="tb_pedido")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mesa;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant horaPedido;
    @OneToMany(targetEntity = Item.class)
    private List<Item> itens = new ArrayList<Item>();

    public Pedido() {}

    public Pedido(Long id, int mesa, StatusPedido statusPedido, StatusPagamento statusPagamento, Instant horaPedido) {
        this.id = id;
        this.mesa = mesa;
        this.statusPedido = statusPedido;
        this.statusPagamento = statusPagamento;
        this.horaPedido = horaPedido;
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

    public void addItem (Item item){
        itens.add(item);
    }

    public void removeItem(Item item){
        itens.remove(item);
    }
    public List<Item> getItens(){
        return itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
