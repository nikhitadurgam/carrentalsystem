import com.hexaware.dao.CarLeaseRepositoryImpl;
import com.hexaware.exception.CarNotFoundException;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.LeaseNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;


public class DAOTest {

    @Test
    public void testCarId() {
        var carLeaseRepo = new CarLeaseRepositoryImpl();
        try {
            assertEquals(carLeaseRepo.findCarById(2).getVehicleID(), 2);
        } catch (CarNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLeaseId() {
        var carLeaseRepo = new CarLeaseRepositoryImpl();
        assertEquals(carLeaseRepo.returnCar(2).getLeaseID(), 2);
    }

    @Test
    public void testCustomerId() {
        var carLeaseRepo = new CarLeaseRepositoryImpl();
        try {
            assertEquals(carLeaseRepo.findCustomerById(2).getCustomerID(), 2);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
