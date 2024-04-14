package br.dev.du.cardapio.services;


import br.dev.du.cardapio.dto.ItemDTO;
import br.dev.du.cardapio.entities.Item;
import br.dev.du.cardapio.repositories.ItemRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public ItemService(){}
    @Transactional(readOnly = true)
    public Page<ItemDTO> findAllPerPage(PageRequest page){
        return repository.findAll(page).map(ItemDTO::new);
    }

    @Transactional(readOnly = true)
    public ItemDTO findById(Long id){
        return new ItemDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Exist")));
    }

    @Transactional
    public ItemDTO insert(ItemDTO dto){

        try{
            Item item = new Item();
            itemToItemDto(item, dto);
            item = repository.save(item);
            return new ItemDTO(item);
        }
        catch (IllegalArgumentException | OptimisticLockException e){
            throw new RuntimeException(e.getMessage());
        }

    }
    @Transactional
    public ItemDTO update(ItemDTO dto, Long id){

        try{
            Item item = repository.getReferenceById(id);
            itemToItemDto(item, dto);
            item = repository.save(item);
            return new ItemDTO(item);
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

    private void itemToItemDto(Item item, ItemDTO dto) {
        item.setDescricao(dto.getDescricao());
        item.setId(dto.getId());
        item.setNome(dto.getNome());
        item.setPreco(dto.getPreco());
        item.setUrlImg(dto.getUrlImg());
    }

}
