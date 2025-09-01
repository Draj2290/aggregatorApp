//package personal.project.aggregator.controller;
//
//
//import jakarta.servlet.ServletOutputStream;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.websocket.server.PathParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import personal.project.aggregator.config.StreamClient;
//import reactor.core.publisher.Flux;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.ByteBuffer;
//import java.util.Iterator;
//import java.util.List;
//
//@Slf4j
//@Controller
//public class StreamController {
//
//    @Autowired
//    StreamClient streamClient;
//
//    @GetMapping("stream")
//    public void getVideo(@RequestParam("fileName") String fileName, HttpServletResponse response)  {
//        response.setContentType("video/mp4");
//        response.setStatus(206); // Partial Content
//        response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".mp4\"");
//
//        List<byte[]> videoFlux=streamClient.getVideoStream(fileName);
//        Iterator<byte[]>  videoIterator= videoFlux.iterator();
//
//        try(ServletOutputStream servletOutputStream= response.getOutputStream()) {
//                while(videoIterator.hasNext()){
//                    byte[] videoChunk = videoIterator.next();
//                    servletOutputStream.write(videoChunk);
//                    servletOutputStream.flush();
//                    log.info("Sent video chunk of size: {}", videoChunk.length);
//                }
//
//
//
//        }catch(Exception e){
//            log.error("Unexpected error while streaming video: {}", e.getMessage());
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//
//
//    }
//}
