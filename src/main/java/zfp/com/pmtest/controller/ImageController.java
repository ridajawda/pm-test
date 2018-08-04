package zfp.com.pmtest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import zfp.com.pmtest.entity.Employee;
import zfp.com.pmtest.services.EmployeeService;
import zfp.com.pmtest.services.ImageService;

/**
 * Created by jt on 7/3/17.
 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final EmployeeService employeeService;

   
    public ImageController(ImageService imageService, EmployeeService employeeService) {
		super();
		this.imageService = imageService;
		this.employeeService = employeeService;
	}

	@GetMapping("employee/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("employee", employeeService.findById(Long.valueOf(id)));

        return "employee/imageuploadform";
    }

    @PostMapping("employee/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/employee/" + id + "/show";
    }

    @GetMapping("employee/{id}/employeeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        Employee employee = employeeService.findById(Long.valueOf(id));

        if (employee.getImage() != null) {
            byte[] byteArray = new byte[employee.getImage().length];
            int i = 0;

            for (Byte wrappedByte : employee.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
