package com.gift.entity.exchange;

import com.gift.entity.request.Request;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="exchange_img")
@Getter
@Setter
public class ExchangeImg {

    @Id
    @Column(name="exchange_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="exchange_img_name")
    private String exchangeImgName;

    @Column(name="exchange_ori_img_name")
    private String exchangeOriImgName;

    @Column(name="exchange_img_url")
    private String exchangeImgUrl;

    @Column(name="exchange_rep_img_yn")
    private String exchangeRepImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exchange_id")
    private Exchange exchange;

    public void updateRequestImg(String exchangeOriImgName, String exchangeImgName, String exchangeImgUrl) {
        this.exchangeOriImgName = exchangeOriImgName;
        this.exchangeImgName = exchangeImgName;
        this.exchangeImgUrl = exchangeImgUrl;
    }
}
