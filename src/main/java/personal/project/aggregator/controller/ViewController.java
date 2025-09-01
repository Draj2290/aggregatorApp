package personal.project.aggregator.controller;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.client.RPCClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Controller
public class ViewController {

    @GetMapping(value={"index.html","index","home"})
    public String homePage(){
        return "index";
    }

    @GetMapping("/getvideo/{fileName}")
    public void streamVideo(@PathVariable("fileName") String fileName, HttpServletResponse servletResponse){

        Flux<byte[]> videoBytes= RPCClient.getVideo(fileName);
        servletResponse.setContentType("video/mp4");
        servletResponse.setStatus(206);
        servletResponse.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".mp4\"");
        try(ServletOutputStream servletOutputStream= servletResponse.getOutputStream()) {
            videoBytes.subscribe(bytes->{
                try {
                    servletOutputStream.write(bytes);
                    servletOutputStream.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
