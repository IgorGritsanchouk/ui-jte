package com.ui.controllers;

import com.ui.model.CurrentPage;
import com.ui.util.FINAL;
import com.ui.util.InterMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gg.jte.TemplateEngine;
import com.ui.service.ItemService;
import com.ui.result.PaginatedResult;
import java.util.Locale;

@Controller
public class ItemController extends ParentController {

    private final Logger logger= LoggerFactory.getLogger(ItemController.class);
    public ItemController(ItemService itemService,
                          TemplateEngine templateEngine,
                          MessageSource messageSource){

        super(messageSource);
        this.itemService= itemService;
        this.templateEngine= templateEngine;
    }

    private ItemService itemService;

    private TemplateEngine templateEngine;

    private InterMessage interMessage;

    @GetMapping("/items-vm")
    public String geItemList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size,
                               HttpServletRequest request, Model model){

        String lang= (String)request.getSession().getAttribute(FINAL.LANGUAGE);
        CurrentPage currentPage= new CurrentPage("Items with pagination", "pages-jte/items-vm", lang);
        request.getSession().setAttribute(FINAL.CURRENT_PAGE, currentPage);
        PaginatedResult itemPaginatedResult = itemService.getPaginatedItems(page, size);

        model.addAttribute(FINAL.CURRENT_PAGE, currentPage);
        model.addAttribute("itemPaginatedResult", itemPaginatedResult);

        Locale locale= getLocale(request);
        interMessage= new InterMessage(messageSource, locale);
        model.addAttribute("interMessage", interMessage);

        logger.info("Lang: "+ lang + "  Jte Page Name: "+ currentPage.getJteName());
        return "layout/master-vm";
    }

}
