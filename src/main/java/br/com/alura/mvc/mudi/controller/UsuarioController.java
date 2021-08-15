package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private PedidoRepository repository;

    @GetMapping("pedido")
    public String home(Model model, Principal principal){
        List<Pedido> result = repository.findAllByUser(principal.getName());
        model.addAttribute ("pedidos", result);
        return "usuario/home";
    }

    @GetMapping("pedido/{status}")
    public String byStatus(@PathVariable("status") String status, Model model, Principal principal){
        List<Pedido> result = repository
                .findByStatusAndUser(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)), principal.getName());
        model.addAttribute("status", status);
        model.addAttribute ("pedidos", result);
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/usuario/home";
    }
}
