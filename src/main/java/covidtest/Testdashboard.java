package covidtest;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Testdashboard_table")
public class Testdashboard {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long inspectionId;
        private String patientName;
        private String status;
        private String result;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getInspectionId() {
            return inspectionId;
        }

        public void setInspectionId(Long inspectionId) {
            this.inspectionId = inspectionId;
        }
        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

}
