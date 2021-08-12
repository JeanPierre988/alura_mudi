package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public String home(Model model, Principal principal){
        List<Pedido> result = repository.findAllByUser(principal.getName());
        model.addAttribute ("pedidos", result);
        return "home";
    }

    @GetMapping("/{status}")
    public String byStatus(@PathVariable("status") String status, Model model){
        List<Pedido> result = repository.findByStatus(StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)));
        model.addAttribute("status", status);
        model.addAttribute ("pedidos", result);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }
}
