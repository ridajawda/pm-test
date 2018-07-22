package zfp.com.pmtest.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import zfp.com.pmtest.entity.Employee;
import zfp.com.pmtest.repository.EmployeeRepository;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final EmployeeRepository employeeRepository;

    public ImageServiceImpl( EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {

        try {
            Employee employee = employeeRepository.findById(id).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            employee.setImage(byteObjects);

            employeeRepository.save(employee);
        } catch (IOException e) {
            //todo handle better
                e.printStackTrace();
        }
    }
}
