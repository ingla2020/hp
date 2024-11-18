package hd.controller;

import hd.entity.HdEmpresas;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelpdeskController {

    @Autowired
   private EmpresasRepository empresasRepository;


    @GetMapping("/empresas")
    public String getAll(Model model, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "6") int size,
                         @RequestParam(defaultValue = "id,asc") String[] sort) {

        try {
            List<HdEmpresas> empresas = new ArrayList<HdEmpresas>();

            String sortField = sort[0];
            String sortDirection = sort[1];

            Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, sortField);

            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

            Page<HdEmpresas> pageTuts;
            if (keyword == null) {
                pageTuts = empresasRepository.findAll(pageable);
            } else {
                pageTuts = empresasRepository.findByDescripcionContainingIgnoreCase(keyword, pageable);
                model.addAttribute("keyword", keyword);
            }

            empresas = pageTuts.getContent();

            model.addAttribute("empresas", empresas);
            model.addAttribute("currentPage", pageTuts.getNumber() + 1);
            model.addAttribute("totalItems", pageTuts.getTotalElements());
            model.addAttribute("totalPages", pageTuts.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDirection", sortDirection);
            model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "empresas";
    }

    @GetMapping("/empresas/new")
    public String addTutorial(Model model) {
        HdEmpresas empresa = new HdEmpresas();


        model.addAttribute("empresa", empresa);
        model.addAttribute("pageTitle", "Create new Empresa");

        return "empresa_form";
    }


    @PostMapping("/empresas/save")
    public String saveTutorial(HdEmpresas empresas, RedirectAttributes redirectAttributes) {
        try {
            empresasRepository.save(empresas);

            redirectAttributes.addFlashAttribute("message", "The Tutorial has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/empresas";
    }

    @GetMapping("/empresas/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            HdEmpresas tutorial = empresasRepository.findById(id).get();

            model.addAttribute("empresa", tutorial);
            model.addAttribute("pageTitle", "Edit Empresa (ID: " + id + ")");

            return "empresa_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/empresas";
        }
    }


    @GetMapping("/empresas/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            empresasRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The Tutorial with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/empresas";
    }
}
