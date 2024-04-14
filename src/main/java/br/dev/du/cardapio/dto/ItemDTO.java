package br.dev.du.cardapio.dto;

import br.dev.du.cardapio.entities.Item;

public class ItemDTO {

    private Long id;
    private Double preco;
    private String urlImg;
    private String descricao;
    private String nome;

    public ItemDTO(Long id, Double preco, String urlImg, String descricao, String nome) {
        this.id = id;
        this.preco = preco;
        this.urlImg = urlImg;
        this.descricao = descricao;
        this.nome = nome;
    }
    public ItemDTO(Item entity) {
        this.id = entity.getId();
        this.preco = entity.getPreco();
        this.urlImg = entity.getUrlImg();
        this.descricao = entity.getDescricao();
        this.nome = entity.getNome();
    }
    public ItemDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
