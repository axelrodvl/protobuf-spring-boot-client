package co.axelrod.protobuf.worker;

import co.axelrod.protobuf.GetWorkerDataGrpc;
import co.axelrod.protobuf.GetWorkerDataRequest;
import co.axelrod.protobuf.GetWorkerDataResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@CommonsLog
public class WorkerClient {
    private GetWorkerDataGrpc.GetWorkerDataBlockingStub blockingStub;

    public WorkerClient() {
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        ManagedChannel channel = ManagedChannelBuilder.forAddress("workers", 50051)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build();
        this.blockingStub = GetWorkerDataGrpc.newBlockingStub(channel);
    }

    public GetWorkerDataResponse getData(String name) {
        log.info("Will try to get data " + name + " ...");
        GetWorkerDataRequest request = GetWorkerDataRequest.newBuilder().setName(new Date().toString()).build();
        return blockingStub.getWorkerData(request);
    }
}
