package dfomenko.controller;


import dfomenko.entity.Placement;
import dfomenko.service.FilesStorageService;
import dfomenko.service.PlacementService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class PlacementController {

    private final PlacementService placementService;
    private final FilesStorageService imagesStorageService;

    @GetMapping("/placements")
    public String listPlacements(@ModelAttribute("error") String error,
                                 Model model,
                                 HttpSession session) {

        model.addAttribute("placements", placementService.findAllPlacements());

        return "placements";
    }

    @GetMapping("/create_placement_form")
    public String createPlacementForm(Model model) {
        Placement placement = new Placement();
        model.addAttribute("placement", placement);

        return "create_placement_form";
    }

    @PostMapping("/create_placement")
    public String createPlacement(@ModelAttribute("placement") Placement placement,
                                  @RequestParam("imageFile") MultipartFile imageFile,
                                  Model model) throws IOException {
// TODO: 24.05.2023 ПРОВЕРИТЬ НА КОЛЛИЗИИ ИМЁН РАССТАНОВОК
        String newFileName = String.valueOf(System.currentTimeMillis());// + "."+ imageFileExtention;

        try {
            imagesStorageService.save(imageFile, newFileName);
        } catch (Exception e) {
            model.addAttribute("error", "Неможливо завантажити зображення");
            return "redirect:/create_placement_form";
        }

        placement.setImageName(newFileName);

        placementService.createPlacement(placement);

        return "redirect:/placements";
    }

    @GetMapping("/update_placement_form")
    public String updatePlacementForm(@ModelAttribute("placementId") Long placementId,
                                      @ModelAttribute("placement") Placement placement,
                                      @ModelAttribute("error") String error,
                                      Model model) {

        model.addAttribute("placement", placementService.findPlacementById(placementId));
        if (placement.getName() != null) {
            model.addAttribute("placement", placement);
        } else {
            model.addAttribute("placement", placementService.findPlacementById(placementId));
        }

        return "update_placement_form";
    }


    @PostMapping("/update_placement")
    public String updatePlacement(@ModelAttribute("placement") Placement placement,
                                  @RequestParam("imageFile") MultipartFile imageFile,
                                  @ModelAttribute("placementId") Long placementId,
                                  RedirectAttributes redirectAttributes) {

//        Placement existingPlacement = placementService.findPlacementById(placementId);
//
//        existingPlacement.setName(placement.getName());
//
//        if (!imageFile.isEmpty()) {
//            String newFileName = String.valueOf(System.currentTimeMillis());// + "."+ imageFileExtention;
//            try {
//                imagesStorageService.save(imageFile, newFileName);
//            } catch (Exception e) {
//                model.addAttribute("error", "Неможливо завантажити зображення");
//            }
//            String oldImageFileName = existingPlacement.getImageName();
//            imagesStorageService.delete(oldImageFileName);
//            existingPlacement.setImageName(newFileName);
//        }
//
//        placementService.updatePlacement(existingPlacement);


        Placement existingPlacement = placementService.findPlacementById(placementId);
        Placement placementBackup = placementService.findPlacementById(placementId);

        existingPlacement.setName(placement.getName());

        if (!imageFile.isEmpty()) {
            String newFileName = String.valueOf(System.currentTimeMillis());
            String oldFileName = existingPlacement.getImageName();
            try {
                existingPlacement.setImageName(newFileName);
                placementService.updatePlacement(existingPlacement);
            } catch (DataIntegrityViolationException e) {
                redirectAttributes.addAttribute("error", "Розстановка з такою назвою вже існує");
                redirectAttributes.addAttribute("placementId", placementId);
                redirectAttributes.addFlashAttribute("placement", placement);
                return "redirect:/update_placement_form";
            }
            try {
                imagesStorageService.save(imageFile, newFileName);
            } catch (Exception e) {
                // Rollback update
                placementService.updatePlacement(placementBackup);
                redirectAttributes.addAttribute("error", "Неможливо завантажити зображення");
                redirectAttributes.addAttribute("placementId", placementId);
                redirectAttributes.addFlashAttribute("placement", placement);
                return "redirect:/update_placement_form";
            }
            imagesStorageService.delete(oldFileName);
        } else {
            try {
                placementService.updatePlacement(existingPlacement);
            } catch (DataIntegrityViolationException e) {
                redirectAttributes.addAttribute("error", "Розстановка з такою назвою вже існує");
                redirectAttributes.addAttribute("placementId", placementId);
                redirectAttributes.addFlashAttribute("placement", placement);
                return "redirect:/update_placement_form";
            }
        }

        return "redirect:/placements";
    }


    @PostMapping("/delete_placement")
    public String deletePlacement(@ModelAttribute("placementId") Long placementId,
                                  RedirectAttributes redirectAttributes) throws IOException {

        Placement existingPlacement = placementService.findPlacementById(placementId);

        try {
            placementService.deletePlacementById(existingPlacement.getId());
            imagesStorageService.delete(existingPlacement.getImageName());
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("error",
                                            "Цю розстановку неможливо видалити, бо в неї входять існуючі місця. " +
                                                    "Розстановку можна буде видалити після видалення місць, які їй належать.");
        }
        return "redirect:/placements";
    }

}
