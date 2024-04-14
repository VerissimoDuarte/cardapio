package br.dev.du.cardapio.resources;

import br.dev.du.cardapio.dto.ItemDTO;
import br.dev.du.cardapio.dto.PedidoDTO;
import br.dev.du.cardapio.services.ItemService;
import br.dev.du.cardapio.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/pedido")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoResource {
    @Autowired
    private PedidoService service;
    @GetMapping
    ResponseEntity<Page<PedidoDTO>> findAllPerPage(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "mesa") String orderBy
    ){
        PageRequest pageResponse = PageRequest.of(pageNumber, pageSize, Sort.Direction.valueOf(direction), orderBy);
        return ResponseEntity.ok().body(service.findAllPerPage(pageResponse));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<PedidoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO dto){
        return ResponseEntity.ok(service.insert(dto));
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<PedidoDTO> update(@RequestBody PedidoDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.update(dto, id));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Object> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }
}
