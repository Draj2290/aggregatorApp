//package personal.project.aggregator.config;
//
//
//import com.google.protobuf.Descriptors;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.stereotype.Component;
//import personal.project.aggregator.grpc.StreamServiceGrpc;
//import personal.project.aggregator.grpc.StreamServiceProto;
//import reactor.core.publisher.Flux;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Slf4j
//@Component
//public class StreamClient {
//    public List<byte[]> getVideoStream(String fileName) {
//        ManagedChannel channel= ManagedChannelBuilder.forAddress("localhost",50051)
//                .usePlaintext()
//                .build();
//
//        StreamServiceGrpc.StreamServiceBlockingStub stub= StreamServiceGrpc.newBlockingStub(channel);
//        StreamServiceProto.VideoFileRequest request= StreamServiceProto.VideoFileRequest.newBuilder()
//                .setName(fileName)
//                .build();
//         byte[] buffer=new byte[1024];
//         log.info("Requesting video stream for file: {}", fileName);
//        Iterator<StreamServiceProto.StreamResponse> returnBytes=stub.getVideo(request);
//        List<byte[]> videoChunks = new ArrayList<>();
//
//        while(returnBytes.hasNext()){
//            byte[] bytes= returnBytes.next().getContent().toByteArray();
//            videoChunks.add(bytes);
//            log.info("Received video chunk of size: {}", bytes.length);
//        }
//
//
//        log.info("Received video stream for file: {}", fileName);
//
//        return videoChunks;
//
//    }
//}
