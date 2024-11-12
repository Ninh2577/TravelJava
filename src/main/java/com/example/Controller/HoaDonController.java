//package com.example.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.Entity.HoaDon;
//import com.example.service.HoaDonService;
//import com.example.service.HotelsService;
//
//@RestController
//@RequestMapping("/api/hoadon")
//@CrossOrigin(origins = "http://localhost/3000")
//public class HoaDonController {
//
//	@Autowired
//	private HoaDonService hoaDonService;
//	
////	@GetMapping
////	public ResponseEntity<List<HoaDon>> getAllHoaDon(){
////		List<HoaDon> hoaDons = hoaDonService.getAllHoaDon();
////		return ResponseEntity.ok(hoaDons);
////	}
//	  @GetMapping("/user")
//	    public ResponseEntity<List<HoaDon>> getHoaDonsByUserId(@RequestParam Long userId) {
//	        List<HoaDon> hoaDons = hoaDonService.findHoaDonByUserId(userId);
//	        return ResponseEntity.ok(hoaDons);
//	    }
//}

package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.HoaDon;
import com.example.service.HoaDonService;

@RestController
@RequestMapping("/api/hoadon")
@CrossOrigin(origins = "http://localhost:3000")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/user")
    public ResponseEntity<List<HoaDon>> getHoaDonsByUserId(@RequestParam Long userId) {
        List<HoaDon> hoaDons = hoaDonService.findHoaDonByUserId(userId);
        if (hoaDons.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(hoaDons);
    }
}
