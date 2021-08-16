package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Null;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public String home(Model model, Principal principal){
        Sort sort = Sort.by("dataEntregaProduto").descending();
        PageRequest pageable = PageRequest.of(1, 1, sort);

        StatusPedido status = StatusPedido.ENTREGUE;

        List<Pedido> result = repository.findByStatus(status, pageable);
        model.addAttribute ("pedidos", result);
        return "home";
    }
}
