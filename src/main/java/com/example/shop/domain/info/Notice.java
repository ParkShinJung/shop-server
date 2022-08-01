package com.example.shop.domain.info;

import com.example.shop.common.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "not_id")
    @GenericGenerator(
            name = "not_id",
            strategy = "com.example.shop.common.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "Not"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })

    @Column
    private String notId;

    @Column
    private String notWriter;

    @Column
    private String notPw;

    @Column
    private String notTitle;

    @Column
    private String notContent;

    @Column
    private String notFile;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime notRegDate;
}
