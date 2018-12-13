package co.axelrod.protobuf.rest;

import co.axelrod.protobuf.model.WorkerData;
import co.axelrod.protobuf.worker.WorkerClient;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CommonsLog
public class WorkerRestController {
    private final WorkerClient workerClient;

    public WorkerRestController(WorkerClient workerClient) {
        this.workerClient = workerClient;
    }

    @GetMapping("/getWorkerStatus")
    WorkerData getWorkerStatus(@RequestParam String name) {
        WorkerData response = new WorkerData();
        response.setData(workerClient.getData(name).getMessage());
        return response;
    }

}
