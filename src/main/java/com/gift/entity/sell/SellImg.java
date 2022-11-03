package com.gift.entity.sell;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="sell_img")
@Getter
@Setter
public class SellImg {

    @Id
    @Column(name="sell_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="sell_img_name")
    private String sellImgName;

    @Column(name="sell_ori_img_name")
    private String sellOriImgName;

    @Column(name="sell_img_url")
    private String sellImgUrl;

    @Column(name="sell_rep_img_yn")
    private String sellRepImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sell_id")
    private Sell sell;

    public void updateSellImg(String sellOriImgName, String sellImgName, String sellImgUrl) {
        this.sellOriImgName = sellOriImgName;
        this.sellImgName = sellImgName;
        this.sellImgUrl = sellImgUrl;
    }
}
