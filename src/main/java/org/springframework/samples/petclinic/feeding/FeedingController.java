package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/feedings")
public class FeedingController {

    private final FeedingService feedingService;

    @Autowired
    public FeedingController(FeedingService feedingService) {
        this.feedingService = feedingService;
    }

    private static final String VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateProductForm";

    @GetMapping("/create")
    public String initCreationForm(ModelMap modelMap) {
        String view = VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
        modelMap.addAttribute("feeding", new Feeding());
        modelMap.addAttribute("feedingType", this.feedingService.getAllFeedingTypes());
        return view;
    }

    @PostMapping("/create")
    public String processCreationForm(@Valid Feeding f, BindingResult b, ModelMap m) throws UnfeasibleFeedingException {
        String view = "welcome";
        if(b.hasErrors()){
            m.addAttribute("feeding", f);
            m.addAttribute("feedingType", this.feedingService.getAllFeedingTypes());
            return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
        }else{
            feedingService.save(f);
            m.addAttribute("message", "Feeding succesfully saved!");

        }
        return view;
    }
}
