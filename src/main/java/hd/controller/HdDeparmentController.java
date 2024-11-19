package hd.controller;

import hd.entity.HdDepartment;
import hd.entity.HdEmpresas;
import hd.repository.DeparmentRepository;
import hd.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HdDeparmentController {

    @Autowired
    private EmpresasRepository empresasRepository;

    @GetMapping("/deparment/empresa/{id}")
    public String editTutorial(@PathVariable("id") String id,
                               Model model,
                               RedirectAttributes redirectAttributes,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "6") int size,
                               @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        try {

            HdEmpresas emp = empresasRepository.findById(id).get();
            List<HdDepartment> depar =  emp.getHddeparment();


            String sortField = sort[0];
            String sortDirection = sort[1];

            Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, sortField);

            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

           // Page<HdEmpresas> empresa =  empresasRepository.findByHdEmpresas (hd, pageable);

            //List<HdDepartment> depar = empresa.get().findFirst().get().getHddeparment();

            model.addAttribute("departamento", depar);
            model.addAttribute("pageTitle", "Edit Empresa (ID: " + id + ")");

            return "empresa_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/empresas";
        }
    }
}
