package com.example.football.dto;

import com.example.football.entity.Team;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 100, message = "Tên phải từ 5 đến 100 ký tự")
    @Pattern(regexp = "^[a-zA-ZÀ-ỹ\\s]+$", message = "Tên không được chứa ký tự đặc biệt hoặc số")
    private String name;

    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @PositiveOrZero(message = "Kinh nghiệm phải là số nguyên dương")
    private Integer experience;

    @NotBlank(message = "Vị trí không được để trống")
    @Pattern(
            regexp = "^(trung vệ|hậu vệ|tiền vệ|tiền đạo|thủ môn)$",
            message = "Vị trí phải là một trong các giá trị: trung vệ, hậu vệ, tiền vệ, tiền đạo hoặc thủ môn"
    )
    private String position;

    private String image;

    @NotNull(message = "Cầu thủ phải thuộc một đội bóng")
    private Team team;
    public int getAge() {
        return LocalDate.now().getYear() - dob.getYear();
    }
}
