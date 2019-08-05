package webserver.view;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Disabled("뷰 랜더링을 확인하기 위한 테스트")
class HandlebarViewResolverTest {
    @Test
    void handlebarView() throws Exception {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);

        Template template = handlebars.compile("user/profile");

        HandlebarViewResolver viewResolver = new HandlebarViewResolver();

        User user = new User("jeonghoon", "password", "정훈", "jeonghoon1107@gmail.com");

        viewResolver.render("user/profile", Collections.singletonMap("user", user));

        String profilePage = template.apply(user);
        System.out.println(profilePage);
    }

    @Test
    void handlebarUsersView() throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);

        Template template = handlebars.compile("user/list");

        User user1 = new User("javajigi", "password", "자바지기", "javajigi@gmail.com");
        User user2 = new User("test", "test", "test", "test");
        List<User> users = Arrays.asList(user1, user2);
        String usersPage = template.apply(Collections.singletonMap("users", users));
        System.out.println(usersPage);
    }
}