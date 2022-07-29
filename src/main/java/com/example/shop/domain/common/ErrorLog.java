package com.example.shop.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50)
    private String serviceName;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime errorTime;

    @Column(length = 30)
    private String username;

    @Column(length = 5)
    private Integer errorStatus;

    @Column(length = 20)
    private String errorCode;

    @Column(length = 200)
    private String errorMessage;

    @Column(length = 100)
    private String className;

    @Column(length = 100)
    private String methodName;

    @Column(length = 5)
    private Integer lineNumber;

}
