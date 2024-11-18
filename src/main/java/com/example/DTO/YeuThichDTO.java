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

