package com.xhb.blog;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.xhb.blog.filter.LoginFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.activation.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.xhb.blog.mapper")
public class BlogApplication {

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new LoginFilter());
        frb.addUrlPatterns("/blog/*");
        frb.setName("loginFilter");
        return frb;
    }



    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }


    @Bean
    @ConfigurationProperties(prefix="spring.datasource")   //加载配置，在配置文件中带有spring.datasource 的配置项
    public DruidDataSource druid(){
        DruidDataSource dds = new DruidDataSource();
        return dds;
    }
//用于显示后台界面
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建StatViewServlet 绑定/druid/ 路径
        ServletRegistrationBean srb = new ServletRegistrationBean (new StatViewServlet(),"/druid/*");
        Map<String,String> param = new HashMap<String ,String>();
        param.put("loginUsername","admin");   //设置登录名
        param.put("loginPassword","123456");  //设置登录密码
        param.put("allow","");   //允许那些ip用户可以查看，“” 代表所有
        param.put("deny","192.168.13.13");   //禁止ip用户查看
        srb.setInitParameters(param);
        return srb;
    }
//用于监听获取应用的数据，Filter用于收集数据，Servlet用于心事数据
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean  frb=new FilterRegistrationBean ();
        frb.setFilter(new WebStatFilter());
        frb.addUrlPatterns("/*");   //设置要过滤的路径
        Map<String ,String> param = new HashMap<String ,String>();
        param.put("exclusions","*.jpg,*.woff,*.js,*.css,/druid/*");   //排除不过滤的请求路径
        frb.setInitParameters(param);
        return frb;
    }

}
