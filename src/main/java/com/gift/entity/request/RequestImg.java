package com.gift.entity.request;

import com.gift.entity.request.Request;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="request_img")
@Getter @Setter
public class RequestImg {

    @Id
    @Column(name="request_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="request_img_name")
    private String requestImgName;

    @Column(name="request_ori_img_name")
    private String requestOriImgName;

    @Column(name="request_img_url")
    private String requestImgUrl;

    @Column(name="request_rep_img_yn")
    private String requestRepImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="request_id")
    private Request request;

    public void updateRequestImg(String requestOriImgName, String requestImgName, String requestImgUrl) {
        this.requestOriImgName = requestOriImgName;
        this.requestImgName = requestImgName;
        this.requestImgUrl = requestImgUrl;
    }
}
