package covidtest;

import covidtest.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TestdashboardViewHandler {


    @Autowired
    private TestdashboardRepository testdashboardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInspected_then_CREATE_1 (@Payload Inspected inspected) {
        try {
            if (inspected.isMe()) {
                // view 객체 생성
                Testdashboard testdashboard = new Testdashboard();
                // view 객체에 이벤트의 Value 를 set 함
                testdashboard.setInspectionId(inspected.getId());
                testdashboard.setPatientName(inspected.getPatientName());
                testdashboard.setStatus(inspected.getStatus());
                testdashboard.setResult(inspected.getResult());
                // view 레파지 토리에 save
                testdashboardRepository.save(testdashboard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenInspectCancelled_then_UPDATE_1(@Payload InspectCancelled inspectCancelled) {
        try {
            if (inspectCancelled.isMe()) {
                // view 객체 조회
                List<Testdashboard> testdashboardList = testdashboardRepository.findByInspectionId(inspectCancelled.getId());
                for(Testdashboard testdashboard : testdashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    testdashboard.setStatus(inspectCancelled.getStatus());
                    // view 레파지 토리에 save
                    testdashboardRepository.save(testdashboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDiagnosed_then_UPDATE_2(@Payload Diagnosed diagnosed) {
        try {
            if (diagnosed.isMe()) {
                // view 객체 조회
                List<Testdashboard> testdashboardList = testdashboardRepository.findByInspectionId(diagnosed.getInspectionId());
                for(Testdashboard testdashboard : testdashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    testdashboard.setStatus(diagnosed.getStatus());
                    testdashboard.setResult(diagnosed.getResult());
                    // view 레파지 토리에 save
                    testdashboardRepository.save(testdashboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}