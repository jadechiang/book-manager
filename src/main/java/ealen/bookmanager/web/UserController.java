package ealen.bookmanager.web;

import ealen.bookmanager.model.SafeUser;
import ealen.bookmanager.model.User;
import ealen.bookmanager.service.BookService;
import ealen.bookmanager.service.UserService;
import ealen.bookmanager.util.SendEmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static ealen.bookmanager.util.Constants.*;

/**
 * #Author : EalenXie
 * #CreateTime : 2017-04-05.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private BookService bookService;

    @Resource
    private SendEmailUtil sender;

    @GetMapping("/listBook.html")                                                //显示所有借阅的书籍
    public ModelAndView listBook() {                                            //首页
        return new ModelAndView("/user/list");
    }

    @GetMapping("/sendEmail.html")
    public ModelAndView sendEmail() {
        return new ModelAndView("/user/sendEmail");
    }

    @PostMapping("/sendMessage")                                                //发送邮件给系统管理员EalenXie
    public ModelAndView sendMessage(@RequestParam("emailTitle") String emailTitle,
                                    @RequestParam("emailContent") String emailContent,
                                    @RequestParam("emailAttachment") MultipartFile emailAttachment) throws Exception {
        sender.sendMessage(emailTitle, emailContent, emailAttachment);
        return new ModelAndView("/user/manager");
    }


    @GetMapping("/manager.html")
    public ModelAndView manager() {                                            //管理员入口
        User user = this.getUser();
        ModelAndView v = new ModelAndView();
        if (user.getRole().equals("admin")) {
            v.setViewName("/user/manager");
        } else {
            v.addObject("msg", NOT_ADMIN);
            v.setViewName(ERROR);
        }
        return v;
    }

    @PostMapping("/list/json")
    public ModelAndView json() {                                             //显示借阅的
        ModelAndView mv = new ModelAndView("/user/json");
        mv.addObject("pageInfo", userService.getBorrowBook(Integer.parseInt(getUserId())));
        return mv;
    }

    @PostMapping("/all/users")
    public ModelAndView allUsers(@RequestParam(value = "page", defaultValue = "1") int pageNo,
                                 @RequestParam(value = "rows", defaultValue = "10") int pageSize) {
        ModelAndView m = new ModelAndView("/user/users");
        SafeUser u = new SafeUser();
        u.setPage(pageNo);
        u.setRows(pageSize);
        m.addObject("pageInfo", userService.getUsers(u));
        return m;
    }

    @GetMapping("/{id}/giveBack")
    public ModelAndView giveBack(@PathVariable Integer id) {                            //归还
        bookService.giveBack(id);
        return new ModelAndView("/user/list");
    }

    @GetMapping("/{id}/borrow")
    public ModelAndView borrow(@PathVariable Integer id) {                              //借阅
        if (bookService.getBookById(id).getStatus().equals("F")) {
            String status = bookService.borrowBook(id, Integer.parseInt(getUserId()));
            if (!status.equals(SUCCESS)) return new ModelAndView("/error").addObject("msg", status);
        }
        return new ModelAndView("/index");
    }

    @GetMapping("/{id}/lookOver")
    public ModelAndView lookOver(@PathVariable Integer id) {                             //查看
        ModelAndView m = new ModelAndView("/user/lookOver");
        m.addObject("book", bookService.getBookById(id));
        return m;
    }

    @GetMapping("/userInfo.html")
    public ModelAndView userInfo() {                                                    //用户信息
        ModelAndView mv = new ModelAndView("/user/userInfo");
        mv.addObject("userInfo", this.getUser());
        return mv;
    }

    @GetMapping("/setting.html")
    public ModelAndView setting() {                                                     //设置
        ModelAndView mv = new ModelAndView("/user/setting");
        mv.addObject("setUser", this.getUser());
        return mv;
    }

    @PostMapping("/updateSetting")                                                      //更改设置
    public ModelAndView updateSetting(@RequestParam(value = "gender") String gender,
                                      @RequestParam(value = "email") String email,
                                      @RequestParam(value = "province") String province,
                                      @RequestParam(value = "city") String city,
                                      @RequestParam(value = "birth") String birth,
                                      @RequestParam(value = "signature") String signature,
                                      @RequestParam(value = "telephone") String telephone) {
        ModelAndView mv = new ModelAndView("/user/userInfo");
        User securityUser = this.getUser();
        securityUser.setSafeProperty(gender, birth, telephone, email, province + city, signature);
        userService.settingUser(securityUser);
        mv.addObject("userInfo", this.getUser());
        return mv;
    }

    @GetMapping("/{id}/delete")                                               //删除用户
    public ModelAndView deleteUser(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        String msg = userService.deleteUser(id);
        if (msg.equals(SUCCESS)) {
            mv.setViewName("/user/manager");
        } else {
            mv.addObject("msg", msg);
            mv.setViewName(ERROR);
        }
        return mv;
    }


    @PostMapping("/modify")                                                         //修改用户
    public ModelAndView modifyUser(@RequestParam(value = "id") Integer id,
                                   @RequestParam(value = "username") String username,
                                   @RequestParam(value = "gender") String gender,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "province") String province,
                                   @RequestParam(value = "city") String city,
                                   @RequestParam(value = "birth") String birth,
                                   @RequestParam(value = "telephone") String telephone) {
        ModelAndView mv = new ModelAndView();
        User u = new User();
        u.setId(id);
        u.setUsername(username);
        u.setSafeProperty(gender, birth, telephone, email, province + city, null);
        userService.modifyUser(u);
        mv.setViewName("/user/manager");
        return mv;
    }




    @PostMapping("/addUser")                                                    //添加用户
    public ModelAndView addUser(@RequestParam(value = "username") String username,
                                @RequestParam(value = "gender") String gender,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "province") String province,
                                @RequestParam(value = "city") String city,
                                @RequestParam(value = "birth") String birth,
                                @RequestParam(value = "telephone") String telephone) {
        ModelAndView mv = new ModelAndView();
        if (userService.findUserByName(username) != null) {
            mv.addObject("msg", USER_EXITS);
            mv.setViewName(ERROR);
        }
        User u = new User();
        u.setUsername(username);
        u.setSafeProperty(gender, birth, telephone, email, province + city, null);
        userService.addUser(u);
        mv.setViewName("/user/manager");
        return mv;
    }
}
