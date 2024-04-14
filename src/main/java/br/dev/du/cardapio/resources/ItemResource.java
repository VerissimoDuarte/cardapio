package br.dev.du.cardapio.resources;

import br.dev.du.cardapio.dto.ItemDTO;
import br.dev.du.cardapio.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemResource {
    @Autowired
    private ItemService service;
    @GetMapping
    ResponseEntity<Page<ItemDTO>> findAllPerPage(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "descricao") String orderBy
    ){
        PageRequest pageResponse = PageRequest.of(pageNumber, pageSize, Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.ok().body(service.findAllPerPage(pageResponse));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ItemDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    ResponseEntity<ItemDTO> insert(@RequestBody ItemDTO dto){
        return ResponseEntity.ok(service.insert(dto));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<ItemDTO> update(@RequestBody ItemDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.update(dto, id));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Object> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }
}
