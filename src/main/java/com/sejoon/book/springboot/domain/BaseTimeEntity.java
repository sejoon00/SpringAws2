package com.sejoon.book.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


//Auditing이란?
//Entity의 생성, 수정 시간 데이터는 중요하다. 하지만 모든 Entity에서 시간을 기록하는 메서드를 작성한다면
//여간 번거로운 일이 아니다. JPA는 Auditing을 통해 시간을 저장하는 클래스를 만들어 상속시킨다.

@Getter
@MappedSuperclass
//JPA Entity들이 이 클래스를 상속할 경우 createdDate, LastModifiedDate도 column으로 인식하게 한다.
@EntityListeners(AuditingEntityListener.class)
//이 클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity {
    //BaseTimeEntity는 모든 Entity들의 상위클래스가 되어
    // Entity들의 createdDate, modiefiedDate를 자도응로 관리한느 역할을 한다.

    @CreatedDate
    //Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate
    //조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;
}
