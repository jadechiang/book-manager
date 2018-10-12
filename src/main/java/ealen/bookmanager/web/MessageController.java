package ealen.bookmanager.web;

import ealen.bookmanager.model.LeaveMessage;
import ealen.bookmanager.service.MessageService;
import ealen.bookmanager.util.PageBean;
import ealen.bookmanager.util.PageUtils;
import ealen.bookmanager.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 10:26
 */
@Controller
@RequestMapping("book")
public class MessageController extends BaseController {
    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/messageIndex.html", method = RequestMethod.GET)
    public ModelAndView messageIndex() {
        return new ModelAndView("book/messageIndex");
    }

    @PostMapping("/addInformation")
    public ModelAndView addMessage(@RequestParam("addInformation") String information) {
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setUsername(this.getUser().getUsername());
        leaveMessage.setCreateTime(StringUtils.getCurrentDateString());
        leaveMessage.setContent(information);
        messageService.addInformation(leaveMessage);
        return new ModelAndView("book/messageIndex");
    }

    @RequestMapping(value = "/message/json", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView messageJson(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("book/messageJson");
        PageBean pageBean = messageService.getAllMessage(PageUtils.setPageParam(request));
        mv.addObject("pageBean", pageBean);
        return mv;
    }

    @RequestMapping(value = "/message/nextPage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView nextPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("book/messageJson");
        PageBean pageBean = messageService.getAllMessage(PageUtils.setPageParam(request));
        mv.addObject("pageBean", pageBean);
        return mv;
    }

}
