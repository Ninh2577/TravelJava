package com.example.DTO;

public class YeuThichDTO {
    private Integer userId;
    private Integer tourId;

    // Constructor mặc định (nếu cần)
    public YeuThichDTO() {}

    // Constructor với tham số
    public YeuThichDTO(Integer userId, Integer tourId) {
        this.userId = userId;
        this.tourId = tourId;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }
}



//import java.util.Date;
//
//public class YeuThichDTO {
//
//    private Integer userId;
//    private Integer tourId;
//    private Date startDate; // Ngày bắt đầu
//    private Date endDate; 
//    // Constructor, Getters, Setters
//    public YeuThichDTO(Integer userId, Integer tourId, Date startDate) {
//        this.userId = userId;
//        this.tourId = tourId;
//        this.startDate = startDate;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getTourId() {
//        return tourId;
//    }
//
//    public void setTourId(Integer tourId) {
//        this.tourId = tourId;
//    }
//
//    // Thêm phương thức getter và setter cho startDate
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//    
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//}
//
